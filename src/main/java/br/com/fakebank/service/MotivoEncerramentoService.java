package br.com.fakebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.DominioEnum;
import br.com.fakebank.domain.MotivoEncerramento;
import br.com.fakebank.domain.commands.DominioCriacaoCommand;
import br.com.fakebank.domain.commands.DominioEdicaoCommand;
import br.com.fakebank.exceptions.DominioExclusaoException;
import br.com.fakebank.exceptions.DominioUniqueException;
import br.com.fakebank.exceptions.NotFoundException;
import br.com.fakebank.repository.MotivoEncerramentoRepository;

@Service
public class MotivoEncerramentoService extends DominioService {

	@Autowired
	MotivoEncerramentoRepository repository;

	public MotivoEncerramentoService() {

	}

	public Page<MotivoEncerramento> listar(Pageable pageable) {
		Page<MotivoEncerramento> motivo = repository.findAll(pageable);

		if (motivo.getSize() == 0)
			throw new NotFoundException();

		return motivo;
	}

	public MotivoEncerramento consultaPorCodigo(Integer codigo) {

		return repository.findById(codigo).orElseThrow(() -> new NotFoundException());
	}

	public MotivoEncerramento incluir(DominioCriacaoCommand comando) {

		if (dominioExiste(comando.getCodigo())) {
			throw new DominioUniqueException();
		}

		MotivoEncerramento motivo = MotivoEncerramento.criar(comando);
		return repository.save(motivo);
	}

	public MotivoEncerramento editar(DominioEdicaoCommand comando, Integer codigo) {
		MotivoEncerramento motivo = consultaPorCodigo(codigo);
		if (motivo == null)
			return motivo;
		motivo.editar(comando);
		return repository.save(motivo);
	}

	private boolean dominioExiste(Integer valor) {
		return dominioExiste(valor, DominioEnum.MOTIVO.toString());
	}

	public void excluirMotivoEncerramento(Integer codigo) {

		MotivoEncerramento motivo = consultaPorCodigo(codigo);
		try {
			repository.delete(motivo);
		} catch (DataIntegrityViolationException e) {
			throw new DominioExclusaoException();
		}

	}
}
