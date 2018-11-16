package br.com.fakebank.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.Movimentacao;
import br.com.fakebank.domain.commands.MovimentacaoDepositoCommand;
import br.com.fakebank.domain.commands.MovimentacaoSaqueCommand;
import br.com.fakebank.domain.commands.MovimentacaoTransferenciaCommand;
import br.com.fakebank.domain.specifications.MovimentacaoSpecifications;
import br.com.fakebank.exceptions.NotFoundException;
import br.com.fakebank.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository repository;
    
    public Page<Movimentacao> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }
    
    public Page<Movimentacao> listarMovimentacoesPorConta (Integer conta, Pageable pageable) {
        Specification<Movimentacao> criterio = Specification
                                              .where(MovimentacaoSpecifications.movimentacaoPorCodigoConta(conta)); 
        
        
        return repository.findAll(criterio, pageable);
    }

    public Movimentacao consultarPorCodigo(Integer codigo) {
        return repository.findById(codigo)
                         .orElseThrow(() -> new NotFoundException());
    }
    
    public Page<Movimentacao> filtrar (Pageable pageable, Integer conta, double valorMovimentacao, Integer tipoMovimentacao, LocalDate dataInicio, LocalDate dataFinal) {
        Specification<Movimentacao> criterio = Specification
                                              .where(MovimentacaoSpecifications.movimentacaoPorCodigoConta(conta)
                                              .and(MovimentacaoSpecifications.movimentacaoPorTipo(tipoMovimentacao))
                                              .and(MovimentacaoSpecifications.movimentacaoPorPeriodo(dataInicio, dataFinal))); 
        
        
        return repository.findAll(criterio, pageable);
    }

    public Movimentacao transferir(MovimentacaoTransferenciaCommand comando) {
        Movimentacao movimentacao = Movimentacao.criar(comando);
        return repository.save(movimentacao);
    }

    public Movimentacao sacar(MovimentacaoSaqueCommand comando) {
        Movimentacao movimentacao = Movimentacao.criar(comando);
        return repository.save(movimentacao);
    }

    public Movimentacao depositar(MovimentacaoDepositoCommand comando) {
        Movimentacao movimentacao = Movimentacao.criar(comando);
        return repository.save(movimentacao);
    }

}
