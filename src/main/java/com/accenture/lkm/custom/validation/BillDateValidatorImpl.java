package com.accenture.lkm.custom.validation;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BillDateValidatorImpl implements ConstraintValidator<BillDateValidator, Date> {

	@Override
	public boolean isValid(Date date, ConstraintValidatorContext arg1) {
		if (date.before(new Date()))
			return true;
		else {
			return false;
		}
	}

	@Override
	public void initialize(BillDateValidator constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}
}
