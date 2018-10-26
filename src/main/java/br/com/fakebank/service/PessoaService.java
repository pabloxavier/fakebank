package br.com.fakebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.Pessoa;
import br.com.fakebank.domain.specifications.PessoaSpecifications;
import br.com.fakebank.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;
    
    public List<Pessoa> listar(){
        return repository.findAll();
    }
    
    public Pessoa getPessoaById(Integer codigo){
        return repository.findById(codigo).orElse(null);
    }
    
    public List<Pessoa> filtrar(String documento, String tipo, String nome){
        Specification<Pessoa> criterio  = Specification.where(PessoaSpecifications.porParteNome(nome)
                                                       .and(PessoaSpecifications.porParteDocumento(documento))
                                                       .and(PessoaSpecifications.porTipo(tipo)));
        return repository.findAll(criterio);
    }
    
}
