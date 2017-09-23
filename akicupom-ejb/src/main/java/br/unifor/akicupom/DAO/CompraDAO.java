package br.unifor.akicupom.DAO;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.unifor.akicupom.entities.Compra;

@Stateless
public class CompraDAO {
	
	@PersistenceContext
	private EntityManager em;

	public void salvar(Compra compra){
		em.persist(compra);
	}
	
	public Compra atualizar(Compra compra){
		return em.merge(compra);
	}
	
	public void remover(Compra compra){
		em.remove(compra);
	}
	
	public Compra buscarPorId(Long id){
		return em.find(Compra.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Compra> buscarTodos(){
		String consulta = "select * from compra";
		TypedQuery<Compra> query = (TypedQuery<Compra>) em.createNativeQuery(consulta);
		return query.getResultList();
	}
}