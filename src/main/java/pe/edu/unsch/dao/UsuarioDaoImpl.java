package pe.edu.unsch.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import pe.edu.unsch.entities.Usuario;

@Repository("usuarioDao")
public class UsuarioDaoImpl implements UsuarioDao {
	
	@PersistenceContext    
	private EntityManager entityManager;

	@Override
	public Usuario login(String usuario, String password) {
		return (Usuario) entityManager     
				.createQuery("from  Usuario "        
						+ "where  usuario  =  :usuario  and "              
						+ "password  =  :password")       
				.setParameter("usuario", usuario)     
				.setParameter("password", password)     
				.getSingleResult(); 
	}

}
