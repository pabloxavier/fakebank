package br.com.fakebank.domain.converters;

public class TelefoneCoverter {

	public String convertToNumeroFormatado(Integer numero) {
		String num = numero.toString();
		
		if (num.length() != 9 && num.length() != 8) {
			return num;
		}
		
		String p1 = num.substring(0, num.length() - 4);
		String p2 = num.substring(num.length() - 4, num.length());
		return p1 + "-" + p2;
	}

	public String convertToNumeroCompletoFormatado(Short prefixo, Integer numeroCompleto) {

		String dd = prefixo.toString();
		
		return "(" + dd + ") " + convertToNumeroFormatado(numeroCompleto);
	}
}
