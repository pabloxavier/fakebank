package br.com.fakebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fakebank.domain.Agencia;

public interface AgenciaRepository extends JpaRepository<Agencia, Integer>, 
                                           JpaSpecificationExecutor<Agencia>{
    
    //Agencia findByNumero(Integer numero);
    
}
