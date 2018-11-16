package br.com.fakebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.DominioEnum;
import br.com.fakebank.domain.TipoConta;
import br.com.fakebank.domain.commands.DominioCriacaoCommand;
import br.com.fakebank.domain.commands.DominioEdicaoCommand;
import br.com.fakebank.exceptions.DominioExclusaoException;
import br.com.fakebank.exceptions.DominioUniqueException;
import br.com.fakebank.exceptions.NotFoundException;
import br.com.fakebank.repository.TipoContaRepository;

@Service
public class TipoContaService extends DominioService{

    @Autowired
    TipoContaRepository repository;
    
    public TipoContaService() {
        
    }
    
    public Page<TipoConta> listar(Pageable pageable){
    	Page<TipoConta> tiposConta = repository.findAll(pageable);
    	
    	if (tiposConta.getSize() == 0) throw new NotFoundException();
    	
        return tiposConta;
    }
    
    
    public TipoConta consultaPorCodigo(Integer codigo) {
        
        return repository.findById(codigo).orElseThrow(() -> new NotFoundException());
    }
    
    public TipoConta salvar(DominioCriacaoCommand comando){
    	
        if (dominioExiste(comando.getCodigo())) {
			throw new DominioUniqueException();
		}
    	
        TipoConta tipoConta = TipoConta.criar(comando);
        return repository.save(tipoConta);
    }
    

    public TipoConta salvar(Integer codigo, DominioEdicaoCommand comando){
        TipoConta tipoConta = consultaPorCodigo(codigo);
        
        if (tipoConta == null) {
            return tipoConta;
        }
        
        tipoConta.editar(comando);
        return repository.save(tipoConta);
        
    }
    
    private boolean dominioExiste(Integer valor){
    	return dominioExiste(valor, DominioEnum.TIPO_CONTA.toString());
    }
    
    public void excluir(Integer codigo){
    	TipoConta tipoConta = consultaPorCodigo(codigo);
        
        try {
        	repository.deleteById(tipoConta.getCodigo());
		} catch (DataIntegrityViolationException e) {
			throw new DominioExclusaoException();
		}
    }


}
