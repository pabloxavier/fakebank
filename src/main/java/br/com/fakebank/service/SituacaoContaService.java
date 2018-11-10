package br.com.fakebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.DominioEnum;
import br.com.fakebank.domain.SituacaoConta;
import br.com.fakebank.domain.commands.DominioCriacaoCommand;
import br.com.fakebank.domain.commands.DominioEdicaoCommand;
import br.com.fakebank.exceptions.DominioUniqueException;
import br.com.fakebank.exceptions.NotFoundException;
import br.com.fakebank.repository.SituacaoContaRepository;

@Service
public class SituacaoContaService extends DominioService{

    @Autowired
    SituacaoContaRepository repository;
    
    public SituacaoContaService() {
        
    }
    
    public List<SituacaoConta> listar() {
        
        return repository.findAll();
    }
    
    public SituacaoConta consultaPorCodigo(Integer codigo) {
        
        return repository.findById(codigo).orElseThrow(() -> new NotFoundException());
    }

    public SituacaoConta salvar(DominioCriacaoCommand comando){
        if (dominioExiste(comando.getValor())) {
			throw new DominioUniqueException();
		}
        SituacaoConta situacaoConta = SituacaoConta.criar(comando);
        return repository.save(situacaoConta);
    }
    

    public SituacaoConta salvar(Integer codigo, DominioEdicaoCommand comando){
        SituacaoConta situacaoConta = consultaPorCodigo(codigo);
        
        if (situacaoConta == null) {
            return situacaoConta;
        }
        
        situacaoConta.editar(comando);
        return repository.save(situacaoConta);
        
    }
    
    private boolean dominioExiste(String valor){
    	return dominioExiste(valor, DominioEnum.SIT_CONTA.toString());
    }
}
