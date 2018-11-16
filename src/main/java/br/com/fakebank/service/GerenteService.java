package br.com.fakebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.Gerente;
import br.com.fakebank.domain.commands.GerenteEdicaoCommand;
import br.com.fakebank.domain.commands.GerenteInclusaoCommand;
import br.com.fakebank.domain.specifications.GerenteSpecifications;
import br.com.fakebank.exceptions.NotFoundException;
import br.com.fakebank.repository.GerenteRepository;

@Service
public class GerenteService {
    
    @Autowired
    private GerenteRepository repository;

    public Page<Gerente> listar(Pageable pageable){
    	Page<Gerente> gerentes = repository.findAll(pageable);
    	
    	if (gerentes.getSize() == 0) throw new NotFoundException();
    	
        return gerentes;
    }
    
    public Gerente getGerenteById(Integer codigo) {
        return repository.findById(codigo).orElse(null);
    }
    
    public List<Gerente> filtrar(boolean isAtivo){
        Specification<Gerente> criterio = Specification.where(GerenteSpecifications.porSituacao(isAtivo));
        return repository.findAll(criterio);
    }
    
    public Gerente salvar(GerenteInclusaoCommand comando) {
        Gerente gerente = Gerente.criar(comando);
        return repository.save(gerente);
    }
    
    public Gerente salvar(Integer codigo, GerenteEdicaoCommand comando) {
        Gerente gerente = getGerenteById(codigo);
        
        if (gerente == null) 
            return gerente ;
        
        gerente.editar(comando);
        return repository.save(gerente);
    }
    
    public boolean excluir(Integer codigo) {
        Gerente gerente = getGerenteById(codigo);
        
        if (gerente == null)
            return false;
        
        repository.deleteById(codigo);
        return true;
    }
}
