package br.com.fakebank.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.fakebank.common.exceptions.NotFoundException;
import br.com.fakebank.domain.Movimentacao;
import br.com.fakebank.domain.commands.MovimentacaoDepositoCommand;
import br.com.fakebank.domain.commands.MovimentacaoSaqueCommand;
import br.com.fakebank.domain.commands.MovimentacaoTransferenciaCommand;
import br.com.fakebank.domain.specifications.MovimentacaoSpecifications;
import br.com.fakebank.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository repository;

    public Page<Movimentacao> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Movimentacao> listarMovimentacoesPorConta(String codigoConta, Pageable pageable) {
        Specification<Movimentacao> criterio = Specification
                .where(MovimentacaoSpecifications.movimentacaoPorCodigoConta(codigoConta));

        return repository.findAll(criterio, pageable);
    }

    public Movimentacao consultarPorCodigo(Integer codigo) {
        return repository.findById(codigo).orElseThrow(() -> new NotFoundException());
    }

    public Page<Movimentacao> filtrar(Pageable pageable, String conta, double valorMovimentacao,
            Integer tipoMovimentacao, LocalDate dataInicio, LocalDate dataFinal) {
        Specification<Movimentacao> criterio = Specification.where(MovimentacaoSpecifications
                .movimentacaoPorCodigoConta(conta).and(MovimentacaoSpecifications.movimentacaoPorTipo(tipoMovimentacao))
                .and(MovimentacaoSpecifications.movimentacaoPorPeriodo(dataInicio, dataFinal)));

        return repository.findAll(criterio, pageable);
    }

    public List<Movimentacao> transferir(MovimentacaoTransferenciaCommand comando) {
        
        Movimentacao movimentacaoSaque = sacar(convertToSaque(comando));
        
        if(movimentacaoSaque == null) {
            return null;
        }
        Movimentacao movimentacaoDeposito = depositar(convertToDeposito(comando));
        
        List<Movimentacao> listaMovimentacao = new ArrayList<>();
        listaMovimentacao.add(movimentacaoSaque);
        listaMovimentacao.add(movimentacaoDeposito);
        return listaMovimentacao;
    }

    public Movimentacao sacar(MovimentacaoSaqueCommand comando) {
        Movimentacao ultimaMovimentacao = ultimaMovimentacao(comando.getConta());

        if (!isSaldoSuficiente(ultimaMovimentacao.getValorSaldoAtual(), comando.getValor())) {  
            return null;
        }
            ultimaMovimentacao.setValorSaldoAnterior(ultimaMovimentacao.getValorSaldoAtual());
            ultimaMovimentacao.setValorSaldoAtual(ultimaMovimentacao.getValorSaldoAtual() - comando.getValor());

            Movimentacao movimentacao = Movimentacao.criar(comando);
            return repository.save(movimentacao);
    }

    public Movimentacao depositar(MovimentacaoDepositoCommand comando) {
        Movimentacao ultimaMovimentacao = ultimaMovimentacao(comando.getConta());
        ultimaMovimentacao.setValorSaldoAnterior(ultimaMovimentacao.getValorSaldoAtual());
        ultimaMovimentacao.setValorSaldoAtual(ultimaMovimentacao.getValorSaldoAtual() + comando.getValor());
        
        Movimentacao movimentacao = Movimentacao.criar(comando);
        return repository.save(movimentacao);
    }

    public Movimentacao ultimaMovimentacao(String codigoConta) {
        Specification<Movimentacao> criterio = Specification
                .where(MovimentacaoSpecifications.movimentacaoPorCodigoConta(codigoConta));

        Pageable pageable = PageRequest.of(0, 1, new Sort(Direction.DESC, "dataMovimentacao"));

        return (Movimentacao) repository.findAll(criterio, pageable);
    }

    private boolean isSaldoSuficiente(Double saldoAtual, Double valorSaque) {

        if (saldoAtual >= valorSaque) {
            return true;
        }

        return false;
    }
    
    private MovimentacaoSaqueCommand convertToSaque(MovimentacaoTransferenciaCommand comando) {
        MovimentacaoSaqueCommand saqueCommand = new MovimentacaoSaqueCommand();
        saqueCommand.setConta(comando.getContaOrigem());
        saqueCommand.setValor(comando.getValor());
        return saqueCommand;
    }
    
    private MovimentacaoDepositoCommand convertToDeposito(MovimentacaoTransferenciaCommand comando) {
        MovimentacaoDepositoCommand depositoCommand = new MovimentacaoDepositoCommand();
        depositoCommand.setConta(comando.getContaDestino());
        depositoCommand.setValor(comando.getValor());
        return depositoCommand;
    }
}
