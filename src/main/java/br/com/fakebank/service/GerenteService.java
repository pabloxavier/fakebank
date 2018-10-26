package br.com.fakebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.Cliente;
import br.com.fakebank.domain.Gerente;
import br.com.fakebank.domain.commands.ClienteEdicaoCommand;
import br.com.fakebank.domain.commands.ClienteInclusaoCommand;
import br.com.fakebank.domain.commands.GerenteEdicaoCommand;
import br.com.fakebank.domain.commands.GerenteInclusaoCommand;
import br.com.fakebank.domain.specifications.GerenteSpecifications;
import br.com.fakebank.repository.GerenteRepository;

@Service
public class GerenteService {
	
	@Autowired
	private GerenteRepository repository;

	public List<Gerente> listar() {
		return repository.findAll();
	}
	
	public Gerente getGerenteById(Integer codigo) {
		return repository.findById(codigo).orElse(null);
	}
	
	public List<Gerente> filtrar(boolean isAtivo){
		Specification<Gerente> criterio = Specification.where(GerenteSpecifications.porSituacao(isAtivo));
		return repository.findAll(criterio);	
	}
	
	public Gerente salvar(GerenteInclusaoCommand comando){
		Gerente gerente = Gerente.criar(comando);
		return repository.save(gerente);
	}
	
	public Gerente salvar(Integer codigo, GerenteEdicaoCommand comando){
		Gerente gerente = getGerenteById(codigo);
		
		if (gerente == null) 
			return gerente;
		
		gerente.editar(comando);
		return repository.save(gerente);
	}
}
