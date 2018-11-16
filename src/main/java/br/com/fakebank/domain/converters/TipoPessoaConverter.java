package br.com.fakebank.domain.converters;

import javax.persistence.Convert;

import br.com.fakebank.domain.TipoPessoa;
import org.springframework.core.convert.converter.Converter;

import javax.persistence.AttributeConverter;

@Convert
public class TipoPessoaConverter implements
        AttributeConverter<TipoPessoa, String>, Converter<String, TipoPessoa> {

    public String convertToDatabaseColumn(TipoPessoa value) {
        switch (value) {
        case FISICA:
            return "F";
        case JURIDICA:
            return "J";
        default:
            return null;
        }
    }

    public TipoPessoa convertToEntityAttribute(String value) {
        switch (value.toUpperCase()) {
        case "F":
            return TipoPessoa.FISICA;
        case "J":
            return TipoPessoa.JURIDICA;
        default:
            return TipoPessoa.INDEFINIDO;
        }
    }

    @Override
    public TipoPessoa convert(String value) {
        switch (value.toUpperCase()) {
            case "PESSOA-FISICA":
                return TipoPessoa.FISICA;
            case "PESSOA-JURIDICA":
                return TipoPessoa.JURIDICA;
            default:
                return TipoPessoa.INDEFINIDO;
        }
    }
}
