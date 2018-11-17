package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import br.com.fakebank.common.validations.CommandValidator;
import br.com.fakebank.common.validations.FieldName;

public class ContaPoupancaInclusaoCommand extends ContaInclusaoCommand {
    
	@NotNull
	@Range(min = 1, max = 31)
	@FieldName("Dia Aniversário")
    private Integer diaAniversarioPoupanca;
    
    public ContaPoupancaInclusaoCommand () {
    	super();        
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
