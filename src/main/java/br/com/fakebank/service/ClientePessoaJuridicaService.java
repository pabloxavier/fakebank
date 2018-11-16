package br.com.fakebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.Cliente;
import br.com.fakebank.domain.TipoPessoa;
import br.com.fakebank.domain.commands.ClientePessoaJuridicaEdicaoCommand;
import br.com.fakebank.domain.commands.ClientePessoaJuridicaInclusaoCommand;
import br.com.fakebank.domain.specifications.ClienteSpecifications;
import br.com.fakebank.repository.ClienteRepository;

@Service
public class ClientePessoaJuridicaService {

    @Autowired
    private ClienteRepository repository;
    
    @Autowired
    ClienteService serviceCliente;
   
   
    public Cliente salvar(ClientePessoaJuridicaInclusaoCommand comando){
        Cliente cliente = Cliente.criarClientePessoaJuridica(comando);
        return repository.save(cliente);
    }
    
    public Cliente salvar(Integer codigo, ClientePessoaJuridicaEdicaoCommand comando){
        Cliente cliente = serviceCliente.getClienteById(codigo);
        
        if (cliente == null) 
            return cliente;
        
        cliente.editar(comando);
        return repository.save(cliente);
    }
    
    public List<Cliente> listar(TipoPessoa tipo){
        Specification<Cliente> criterio = Specification.where(ClienteSpecifications.clientePorTipo(tipo));
        return repository.findAll(criterio);    
    }
   
}
