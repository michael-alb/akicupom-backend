package br.unifor.akicupom.DAO;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.unifor.akicupom.entities.Categoria;

@Stateless
public class CategoriaDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Categoria categoria){
		em.persist(categoria);
	}
	
	public Categoria atualizar(Categoria categoria){
		return em.merge(categoria);
	}
	
	public void remover(Categoria categoria){
		em.remove(categoria);
	}
	
	public Categoria buscarPorId(Long id){
		return em.find(Categoria.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Categoria> buscarTodos(){
		String consulta = "select * from categoria";
		TypedQuery<Categoria> query = (TypedQuery<Categoria>) em.createNativeQuery(consulta);
		return query.getResultList();
	}
}