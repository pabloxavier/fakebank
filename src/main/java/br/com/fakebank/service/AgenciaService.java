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
import br.com.fakebank.exceptions.NotFoundException;
import br.com.fakebank.repository.AgenciaRepository;

@Service
public class AgenciaService {

    @Autowired
    private AgenciaRepository repository;
    
    public List<Agencia> listar(){
    	List<Agencia> agencias = repository.findAll();
    	
    	if (agencias.isEmpty()) throw new NotFoundException();
    	
        return agencias;
    }
    
    public Agencia consultarPorCodigo(Integer codigo){
        return repository
        		.findById(codigo)
                .orElseThrow(() -> new NotFoundException());
    }
    
    public List<Agencia> filtrar(String nome, String cnpj, Integer numero){
        Specification<Agencia> criterio =
        		Specification
                	.where(AgenciaSpecifications.agenciaPorParteNome(nome)
                    .and(AgenciaSpecifications.agenciaPorNumero(numero))
                    .and(AgenciaSpecifications.agenciaPorParteCnpj(cnpj)));
        
        List<Agencia> agencias = repository.findAll(criterio);
        
    	if (agencias.isEmpty()) throw new NotFoundException();
    	
        return agencias;
    }
    
    public Agencia salvar(AgenciaInclusaoCommand comando){
        Agencia agencia = Agencia.criar(comando);
        return repository.save(agencia);
    }
    
    public Agencia salvar(Integer codigo, AgenciaEdicaoCommand comando){
        Agencia agencia = consultarPorCodigo(codigo);
        agencia.editar(comando);
        return repository.save(agencia);
    }
    
    public void excluir(Integer codigo){
        Agencia agencia = consultarPorCodigo(codigo);
        repository.deleteById(agencia.getCodigo());
    }

    public boolean cnpjJaExiste(String cnpj){
        Specification<Agencia> criterio = AgenciaSpecifications.agenciaPorParteCnpj(cnpj);
        Optional<Agencia> agencia = repository.findOne(criterio);
        
        return agencia.isPresent();
    }
}
