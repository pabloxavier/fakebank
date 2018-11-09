package br.com.fakebank.customValidators;

import java.util.InputMismatchException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CnpjValidValidator implements ConstraintValidator<CnpjValid, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }
        
        if (value.trim().isEmpty()) {
            return true;
        }
        
        return cnpjIsValid(value);
    }

    private boolean cnpjIsValid(String numero) {

        if (numero.equals("00000000000000") || numero.equals("11111111111111") || numero.equals("22222222222222")
                || numero.equals("33333333333333") || numero.equals("44444444444444") || numero.equals("55555555555555")
                || numero.equals("66666666666666") || numero.equals("77777777777777") || numero.equals("88888888888888")
                || numero.equals("99999999999999") || (numero.length() != 14))
            return (false);

        char dig13, dig14;
        int sm, i, r, num, peso;

        try {

            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {

                num = (int) (numero.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else
                dig13 = (char) ((11 - r) + 48);

            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (numero.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else
                dig14 = (char) ((11 - r) + 48);

            if ((dig13 == numero.charAt(12)) && (dig14 == numero.charAt(13)))
                return (true);
            else
                return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }
}
