package br.com.fakebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.Dominio;
import br.com.fakebank.domain.DominioEnum;
import br.com.fakebank.domain.TipoConta;
import br.com.fakebank.domain.commands.DominioCriacaoCommand;
import br.com.fakebank.domain.commands.DominioEdicaoCommand;
import br.com.fakebank.exceptions.DominioUniqueException;
import br.com.fakebank.exceptions.NaoEncontradoException;
import br.com.fakebank.repository.TipoContaRepository;

@Service
public class TipoContaService extends DominioService{

    @Autowired
    TipoContaRepository repository;
    
    public TipoContaService() {
        
    }
    
    public List<TipoConta> listar() {
        
        return repository.findAll();
    }
    
    public TipoConta consultaPorCodigo(Integer codigo) {
        
        return repository.findById(codigo).orElseThrow(() -> new NaoEncontradoException());
    }
    
    public TipoConta salvar(DominioCriacaoCommand comando){
    	
        if (dominioExiste(comando.getValor())) {
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
    
    private boolean dominioExiste(String valor){
    	return dominioExiste(valor, DominioEnum.TIPO_CONTA.toString());
    }

}
