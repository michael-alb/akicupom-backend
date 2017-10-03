package br.unifor.akicupom.DAO;

import java.util.List;

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
	public List<Categoria> buscarTodos(){
		String consulta = "select c from Categoria c";
		TypedQuery<Categoria> query = (TypedQuery<Categoria>) em.createQuery(consulta);
		return query.getResultList();
	}
}