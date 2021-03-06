package br.com.fakebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fakebank.common.exceptions.NotFoundException;
import br.com.fakebank.domain.Cliente;
import br.com.fakebank.domain.Conta;
import br.com.fakebank.domain.ContaEncerrada;
import br.com.fakebank.domain.commands.ContaEncerradaCommand;
import br.com.fakebank.repository.ClienteRepository;
import br.com.fakebank.repository.ContaEncerradaRepository;
import br.com.fakebank.repository.ContaRepository;

@Service
public class ContaEncerradaService {
    
    @Autowired
    private ContaRepository repository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ContaEncerradaRepository repositoryEncerramento;
    
	public Conta ContaPorCodigo(String codigo) {
		return repository.findById(codigo).orElseThrow(() -> new NotFoundException());
	}
  
    public ContaEncerrada encerrar(String codigo, ContaEncerradaCommand command){
        Conta conta = ContaPorCodigo(codigo);

        Cliente cliente = clienteRepository.findById(command.getClienteSolicitante()).orElseThrow(() -> new NotFoundException());
        
        ContaEncerrada dadosEncerramento = conta.encerrarConta(command, cliente);
        
        return repositoryEncerramento.save(dadosEncerramento);
    }
    
}
