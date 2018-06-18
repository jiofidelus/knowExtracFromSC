package org.imogene.web.server.validation;

import java.text.ParseException;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.imogene.web.server.util.DateUtil;

public class BeforeValidator implements ConstraintValidator<Before, Date> {

	private String limitDate;

	public void initialize(Before constraintAnnotation) {
		limitDate = constraintAnnotation.value();
	}

	public boolean isValid(Date object, ConstraintValidatorContext constraintContext) {

		if (object == null)
			return true;

		try {
			Date limit = DateUtil.parseDate(limitDate);
			if (object.before(limit))
				return true;
			else
				return false;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

}