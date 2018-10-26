package br.com.fakebank.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.Movimentacao;
import br.com.fakebank.domain.commands.MovimentacaoDepositoCommand;
import br.com.fakebank.domain.commands.MovimentacaoSaqueCommand;
import br.com.fakebank.domain.commands.MovimentacaoTransferenciaCommand;
import br.com.fakebank.domain.specifications.MovimentacaoSpecifications;
import br.com.fakebank.exceptions.NaoEncontradoException;
import br.com.fakebank.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository repository;
    
    public List<Movimentacao> listar() {
        return repository.findAll();
    }

    public Movimentacao consultarPorCodigo(Integer codigo) {
        return repository.findById(codigo)
                         .orElseThrow(() -> new NaoEncontradoException());
    }
    
    public List<Movimentacao> filtrar (Integer conta, double valorMovimentacao, Integer tipoMovimentacao, LocalDate dataInicio, LocalDate dataFinal) {
        Specification<Movimentacao> criterio = Specification
                                              .where(MovimentacaoSpecifications.movimentacaoPorCodigoConta(conta)
                                              .and(MovimentacaoSpecifications.movimentacaoPorTipo(tipoMovimentacao))
                                              .and(MovimentacaoSpecifications.movimentacaoPorPeriodo(dataInicio, dataFinal))); 
        
        
        return repository.findAll(criterio);
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
