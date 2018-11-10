package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import br.com.fakebank.customValidators.ContaForeignKeyGerente;
import br.com.fakebank.domain.validators.CommandValidator;

public class ContaPoupancaInclusaoCommand extends ContaCorrenteInclusaoCommand {
   
    private Integer diaAniversarioPoupanca;
    
    public ContaPoupancaInclusaoCommand () {
        
    }

    public Integer getDiaAniversarioPoupanca() {
        return diaAniversarioPoupanca;
    }

    public void setDiaAniversarioPoupanca(Integer diaAniversarioPoupanca) {
        this.diaAniversarioPoupanca = diaAniversarioPoupanca;
    }
    
    public void validate() {
    	CommandValidator<ContaPoupancaInclusaoCommand> validator =
        		new CommandValidator<ContaPoupancaInclusaoCommand>();
        validator.validate(this);
    }

}
