package br.com.fakebank.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;


public class DominioDescricaoValidator implements ConstraintValidator<DominioDescricaoValid, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (null == value || StringUtils.isEmpty(value) || value.length() > 200) {
            return false;
        }
        
        return true;
    }

}
