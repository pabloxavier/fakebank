package br.com.fakebank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.Agencia;
import br.com.fakebank.domain.commands.AgenciaEdicaoCommand;
import br.com.fakebank.domain.commands.AgenciaInclusaoCommand;
import br.com.fakebank.domain.specifications.AgenciaSpecifications;
import br.com.fakebank.exceptions.NaoEncontradoException;
import br.com.fakebank.repository.AgenciaRepository;

@Service
public class AgenciaService {

    @Autowired
    private AgenciaRepository repository;
    
    public List<Agencia> listar(){
        return repository.findAll();
    }
    
    public Agencia consultarPorCodigo(Integer codigo){
        return repository.findById(codigo)
                       //.orElse(null);
                         .orElseThrow(() -> new NaoEncontradoException());
    }
    
    public List<Agencia> filtrar(String nome, String cnpj, Integer numero){
        Specification<Agencia> criterio = Specification
                                            .where(AgenciaSpecifications.agenciaPorParteNome(nome)
                                            .and(AgenciaSpecifications.agenciaPorNumero(numero))
                                            .and(AgenciaSpecifications.agenciaPorParteCnpj(cnpj)));
        return repository.findAll(criterio);
    }
    
    public Agencia salvar(AgenciaInclusaoCommand comando){
        Agencia agencia = Agencia.criar(comando);
        return repository.save(agencia);
    }
    
    public Agencia salvar(Integer codigo, AgenciaEdicaoCommand comando){
        Agencia agencia = consultarPorCodigo(codigo);
        
        if (agencia == null)
            return agencia;
        
        agencia.editar(comando);
        return repository.save(agencia);
    }
    
    public boolean excluir(Integer codigo){
        Agencia agencia = consultarPorCodigo(codigo);
        
        if (agencia == null)
            return false;
        
        repository.deleteById(codigo);
        return true;
    }

    public boolean cnpjJaExiste(String cnpj){
        Specification<Agencia> criterio = AgenciaSpecifications.agenciaPorParteCnpj(cnpj);
        Optional<Agencia> agencia = repository.findOne(criterio);
        
        return agencia.isPresent();
            
    }
}
