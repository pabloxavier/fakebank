package br.com.fakebank.representations;

import br.com.fakebank.common.util.DateUtil;
import br.com.fakebank.domain.Conta;

public class ContaCorrenteRepresentation {

    private String codigo;
    private String dataAbertura;
    private String gerente;
    private String situacao;
    private String tipo;
    private Double saldo;

    public static ContaCorrenteRepresentation  from(Conta conta) {
        ContaCorrenteRepresentation contaRepresentation = new ContaCorrenteRepresentation();
        contaRepresentation.setCodigo(conta.getCodigoConta());
        contaRepresentation.setSituacao(conta.getSituacaoConta().getDescricao());
        contaRepresentation.setTipo(conta.getTipoConta().getDescricao());       
        contaRepresentation.setSaldo(conta.getValorSaldo());
        contaRepresentation.setDataAbertura(DateUtil.getDateFromString(conta.getDataAbertura()));
        contaRepresentation.setGerente(conta.getGerente().getPessoa().getNome());
        return contaRepresentation;
    }

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getGerente() {
		return gerente;
	}

	public void setGerente(String gerente) {
		this.gerente = gerente;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
    
}
