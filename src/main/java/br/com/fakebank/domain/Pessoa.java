package br.com.fakebank.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.fakebank.domain.converters.TipoPessoaConverter;


@Entity
@Table(name = "PESSOA", schema = "DBO")
public class Pessoa {

    @Id
    @Column(name = "CD_PESSOA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    
    @Column(name = "NM_PESSOA")
    private String nome;
    
    @Column(name = "TP_PESSOA")
    @Convert(converter = TipoPessoaConverter.class)
    private TipoPessoa tipoPessoa;
    
    @Column(name = "NR_DOCUMENTO")
    private String numeroDocumento;
    
    @Column(name = "DT_NASCIMENTO")
    private LocalDate dataNascimento;
    
    @Column(name = "DT_ABERTURA")
    private LocalDate dataAbertura;
    
    public Pessoa() {
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
}
