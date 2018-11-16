package br.com.fakebank.customValidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;


public class DominioCodigoValidator implements ConstraintValidator<DominioCodigoValid, Integer>{

    @Override
    public boolean isValid(Integer codigo, ConstraintValidatorContext context) {

        if (null == codigo || StringUtils.isEmpty(codigo) || codigo <= 0) {
            return false;
        }
        
        return true;
    }

}
