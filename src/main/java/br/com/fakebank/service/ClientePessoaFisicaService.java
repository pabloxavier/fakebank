package br.com.fakebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.Cliente;
import br.com.fakebank.domain.TipoPessoa;
import br.com.fakebank.domain.commands.ClientePessoaFisicaEdicaoCommand;
import br.com.fakebank.domain.commands.ClientePessoaFisicaInclusaoCommand;
import br.com.fakebank.domain.specifications.ClienteSpecifications;
import br.com.fakebank.repository.ClienteRepository;

@Service
public class ClientePessoaFisicaService {

    @Autowired
    private ClienteRepository repository;
    
    @Autowired
    ClienteService serviceCliente;
   
   
    public Cliente salvar(ClientePessoaFisicaInclusaoCommand comando){
        Cliente cliente = Cliente.criarClientePessoaFisica(comando);
        return repository.save(cliente);
    }
    
    public Cliente salvar(Integer codigo, ClientePessoaFisicaEdicaoCommand comando){
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
