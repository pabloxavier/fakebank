package br.com.fakebank.infrastructure.converters;

import br.com.fakebank.domain.TipoTelefone;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TipoTelefoneConverter implements AttributeConverter<TipoTelefone, String> {

    @Override
    public String convertToDatabaseColumn(TipoTelefone value) {
        switch (value) {
            case RESIDENCIAL:
                return "R";
            case COMERCIAL:
                return "C";
            case CELULAR:
                return "M";
        }
        return null;
    }

    @Override
    public TipoTelefone convertToEntityAttribute(String value) {
        switch (value.toUpperCase()) {
            case "R":
                return TipoTelefone.RESIDENCIAL;
            case "C":
                return TipoTelefone.COMERCIAL;
            case "M":
                return TipoTelefone.CELULAR;
        }
        return null;
    }
}
