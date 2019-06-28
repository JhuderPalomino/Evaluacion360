package pe.edu.unsch.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unsch.dao.ModuloDao;
import pe.edu.unsch.entities.Modulo;

@Service("moduloService")
@Transactional
public class ModuloServiceImpl implements ModuloServie {
	
	@Autowired
	private ModuloDao moduloDao;

	@Override
	public List<Modulo> listarPorPerfil(String usuario) {
		return moduloDao.listarPorPerfil(usuario);
	}

	@Override
	public List<Modulo> encontrarTodo() {
		// TODO Auto-generated method stub
		return null;
	}

}
