package uk.co.parkers.rest.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import uk.co.parkers.domain.web.jpa.PersonCriteria;
import uk.co.parkers.model.jpa.Person;
import uk.co.parkers.service.JpaService;
import uk.co.parkers.validation.ValidationError;
import uk.co.parkers.validation.ValidationError.FieldErrorMessage;
import uk.co.parkers.validation.jpa.PersonValidator;

@RestController
@RequestMapping(path = "/rest/jpa")
public class PersonController{

	@Resource
	JpaService jpaService;
	
	@Resource
	PersonValidator personValidator;

	@RequestMapping(path = "/person/{name}", method = RequestMethod.GET)
	public Person getPersonByName(@PathVariable ("name") String name) {
		return jpaService.findPersonByName(name);
	}
	
	
	@RequestMapping(path = "/person/add", method = RequestMethod.POST)
	public Person addPerson(@Valid @RequestBody PersonCriteria person) {
		return jpaService.savePerson(person.getDomainObject());
	}
	
	
	@InitBinder("personCriteria")
	public void setupBinder(WebDataBinder binder) {
	    binder.addValidators(personValidator);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationError handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

		List<FieldErrorMessage> fieldErrors = ex.getBindingResult().getFieldErrors().stream().map(f -> {
			FieldErrorMessage fem = new FieldErrorMessage(f.getField(), f.getRejectedValue());
			fem.messages.add(f.getDefaultMessage());
			return fem;
		}).collect(Collectors.toMap(FieldErrorMessage::getField, e -> e, (a, b) -> {
			a.messages.addAll(b.messages);
			return a;
		})).values().stream().collect(Collectors.toList());

		List<String> globalErrors = new ArrayList<String>();
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			globalErrors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST, "Validation Error", fieldErrors,
				globalErrors);
		return validationError;
	}
}
