package br.com.fakebank.representations;

import java.time.LocalDate;

import br.com.fakebank.domain.ContaEncerrada;

public class ContaEncerradaRepresentation {

    private String conta;
    private LocalDate dataEncerramento;
	private String observacoes;
	private Integer motivo;
	private Integer codigoCliente;
	private String clienteSolicitante;
	
    public static ContaEncerradaRepresentation from(ContaEncerrada contaEncerrada){
    	ContaEncerradaRepresentation model = new ContaEncerradaRepresentation();
        model.setConta(contaEncerrada.getConta());
        model.setDataEncerramento(contaEncerrada.getDataEncerramento());
        model.setObservacoes(contaEncerrada.getObservacoes());
    	model.setMotivo(contaEncerrada.getMotivo());
        model.setCodigoCliente(contaEncerrada.getClienteSolicitante().getCodigo());
        model.setClienteSolicitante(contaEncerrada.getClienteSolicitante().getPessoa().getNome());
        
        return model;
    }

    /*public static List<ContaEncerradaRepresentation> from(List<ContaEncerrada> contaEncerrada){
    	return
    		contaEncerrada
    			.stream()
    			.map(item -> from(item))
    			.collect(Collectors.toList());

    }*/
    
	
    
    public Integer getMotivo() {
		return motivo;
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public void setMotivo(Integer motivo) {
		this.motivo = motivo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getClienteSolicitante() {
		return clienteSolicitante;
	}

	public void setClienteSolicitante(String clienteSolicitante) {
		this.clienteSolicitante = clienteSolicitante;
	}

	public LocalDate getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(LocalDate dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	
}

