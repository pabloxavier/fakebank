package br.com.fakebank.representations;

import br.com.fakebank.common.util.DateUtil;
import br.com.fakebank.domain.Conta;

public class ContaSalarioRepresentation {

    private String codigo;
    private String dataAbertura;
    private String gerente;
    private String situacao;
    private String tipo;
    private Double saldo;
    private Integer diaAniversario;
    private String cnpjEmpresa;


    public static ContaSalarioRepresentation from(Conta conta) {
        ContaSalarioRepresentation contaRepresentation = new ContaSalarioRepresentation();    
        contaRepresentation.setCodigo(conta.getCodigoConta());
        contaRepresentation.setSituacao(conta.getSituacaoConta().getDescricao());
        contaRepresentation.setTipo(conta.getTipoConta().getDescricao());        
        contaRepresentation.setSaldo(conta.getValorSaldo());
        contaRepresentation.setCnpjEmpresa(conta.getNumeroCnpjContratoSalario());
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


	public Integer getDiaAniversario() {
		return diaAniversario;
	}


	public void setDiaAniversario(Integer diaAniversario) {
		this.diaAniversario = diaAniversario;
	}


	public String getCnpjEmpresa() {
		return cnpjEmpresa;
	}


	public void setCnpjEmpresa(String cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}
   
}
