package uk.co.parkers.validation.annotation.impl;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import uk.co.parkers.model.jpa.ValidSetValue;
import uk.co.parkers.repository.jpa.ValidSetValueRepository;
import uk.co.parkers.validation.annotation.ValidSetValueConstraintValidator;

public class ValidSetValuesConstraint implements ConstraintValidator<ValidSetValueConstraintValidator, String> {

	@Resource
	ValidSetValueRepository vsvRepo;

	ValidSetValueConstraintValidator vsvcv;
	
	@Override
	public void initialize(ValidSetValueConstraintValidator arg0) {
		vsvcv = arg0;
	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		if (arg0 == null){
			return true;
		}
		ValidSetValue vsv = vsvRepo.findOne(vsvcv.key());
		
		return vsv.getValue().contains(arg0);
	}

}
