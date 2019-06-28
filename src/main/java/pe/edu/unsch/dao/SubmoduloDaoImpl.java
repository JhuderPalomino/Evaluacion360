package pe.edu.unsch.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import pe.edu.unsch.entities.Submodulo;

@Repository("submoduloDao")
public class SubmoduloDaoImpl implements SubmoduloDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Submodulo> listarPorPerfil(String usuario) {
		return entityManager
				.createQuery("select new Submodulo(m.idmodulo as idmodulo, s.nombre as nombre) "
						+ "from PerfilOpcion po "
						+ "inner join po.opcion o "
						+ "inner join o.submodulo s "
						+ "inner join s.modulo m "
						+ "where po.perfil = some ("
						+ "select p.idperfil from Perfil as p "
						+ "inner join p.usuarioPerfils up "
						+ "inner join up.usuario u "
						+ "where u.nombre= :usuario) "
						+ "group by s.idsubmodulo", Submodulo.class)
				.setParameter("usuario", usuario)
				.getResultList();
	}

}
