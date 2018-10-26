package br.com.fakebank.domain.converters;

import javax.persistence.Convert;

import br.com.fakebank.domain.TipoPessoa;

import javax.persistence.AttributeConverter;

@Convert
public class TipoPessoaConverter implements
        AttributeConverter<TipoPessoa, String> {

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
}
