package uk.co.parkers.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import uk.co.parkers.domain.web.jpa.PersonCriteria;
import uk.co.parkers.model.jpa.Person;
import uk.co.parkers.model.jpa.ValidSetValue;
import uk.co.parkers.model.jpa.Person.Sex;
import uk.co.parkers.repository.jpa.ValidSetValueRepository;
import uk.co.parkers.validation.annotation.ValidSetValues;

public abstract class AbstractValidator<T, R extends PagingAndSortingRepository<?, ?>> implements Validator {

	R repository;
	
	T supportedType;
	
	@SuppressWarnings("unchecked")
	public AbstractValidator(R repository) throws ClassNotFoundException {
		// TODO Auto-generated constructor stub
		this.repository = repository;
		
		Type tas = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		supportedType = (T) Class.forName(tas.getTypeName());
	}
	
	@Resource
	ValidSetValueRepository vsvRepo;

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean supports(Class<?> arg0) {
		return ((Class)supportedType).isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO do javaxValidation
		// target should be an entity object (fieldName / value)
		// need to map the error back to the original object
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		javax.validation.Validator validator = factory.getValidator();
		// use repository to convert the entity to the model object then validate it
		
		// will be a criteria object, for this sample we assume Person and only name and sex. 
		PersonCriteria pc = (PersonCriteria) target;
		Person person = new Person();
		person.setName("Bob");
		person.setSex(Sex.MALE);
		
		Set<ConstraintViolation<Person>> violations = validator.validate(person);
		violations.forEach(v -> {
			System.err.println(v.getMessage());
			errors.rejectValue(v.getPropertyPath().toString(), v.getMessageTemplate(), v.getMessage());
		});


		validateValidSetValues(pc, errors);
		doExtraValidation(pc, errors);
		pc.setDomainObject(person);
	}

	private String createMessage(String message, List<String> toMatch) {
		StringBuilder sb = new StringBuilder();
		toMatch.forEach(s -> sb.append(s).append(", "));
		String prettyList = sb.length() > 0 ? "[" + sb.substring(0, sb.length() - 2) + "]" : "";
		return message.replace("{0}", prettyList);
	}

	private void validateValidSetValues(Object target, Errors errors) {
		Set<Field> vsvFields = findFields(target.getClass(), ValidSetValues.class);
		vsvFields.forEach(f -> {
			f.setAccessible(true);
			try {
				Object value = f.get(target);
				if (value != null) {
					ValidSetValues a = f.getAnnotation(ValidSetValues.class);
					ValidSetValue vsv = vsvRepo.findOne(a.key());
					if (vsv == null && !a.ignoreEmptyValidSetValues()) {
						errors.rejectValue(f.getName(), a.messageKey(), "Cannot find valid set values in database");
					} else {
						boolean found = false;
						for (String v : vsv.getValue()) {
							found = a.ignoreCase() ? v.equalsIgnoreCase(value.toString()) : v.equals(value.toString());
							if (found) {
								break;
							}
						}
						if (!found) {
							String message = createMessage(a.message(), vsv.getValue());
							errors.rejectValue(f.getName(), a.messageKey(), message);
						}
					}
				}
			} catch (Exception e) {
				// don't care
			}
		});
	}

	public static Set<Field> findFields(Class<?> classs, Class<? extends Annotation> ann) {
		Set<Field> set = new HashSet<>();
		Class<?> c = classs;
		while (c != null) {
			for (Field field : c.getDeclaredFields()) {
				if (field.isAnnotationPresent(ann)) {
					set.add(field);
				}
			}
			c = c.getSuperclass();
		}
		return set;
	}

	/**
	 * Do any extra validation here.
	 * 
	 * @param target
	 *            the incoming object.
	 * @param errors
	 *            any errorss
	 */
	public void doExtraValidation(Object target, Errors errors){
		
	}
}
