package br.com.fakebank.common.validations;

import java.lang.reflect.Field;
import java.util.Iterator;

import javax.validation.ConstraintViolation;
import javax.validation.ElementKind;
import javax.validation.Path;

public abstract class AbstractValidator {
    
    //A classe ConstraintViolation é a classe responsável por
    //permitir que possamos acessar as regras dos Beans Validations
    private ConstraintViolation<?> rule;
    
    //Aqui, estamos expondo o Set para o ConstraintViolation
    public void setRule(ConstraintViolation<?> rule) {
        this.rule = rule;
    }
    
    //Para obter a mensagem de erro de validação
    protected String getMessageError() {
        return this.rule.getMessage();
    }

    //Para obter o o campo em que o erro ocorreu 
    protected String getFieldError() {
        
        //Obtemos dois valores do ConstraintViolation
        //Path (que é o caminho até a propriedade)
        //Object (que é a propriedade em si)
        Path path = this.rule.getPropertyPath();
        Object obj = this.rule.getLeafBean();
        
        //Agora, vamos procurar o Annotation de FieldName
        FieldName annotation = null;

        for (Iterator<Path.Node> it = path.iterator(); it.hasNext();) {
            Path.Node node = it.next();

            if (ElementKind.PROPERTY.equals(node.getKind())) {
                Field f = getField(obj.getClass(), node.getName());

                if (f != null) {
                    annotation = f.getAnnotation(FieldName.class);
                    break;
                }
            }
        }

        //Caso, encontrado o FieldName, este será retornado
        //Caso contrário, será retornado NULO
        return annotation != null ? annotation.value() : null;
    }

    //Método privado para procurar o FieldName de forma recursiva
    private Field getField(Class<?> type, String fieldName) {
        while (type != null) {
            try {
                return type.getDeclaredField(fieldName);
            } catch (NoSuchFieldException nsfe) {
                type = type.getSuperclass();
            }
        }

        return null;
    }
}
