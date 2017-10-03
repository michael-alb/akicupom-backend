package br.unifor.akicupom.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.unifor.akicupom.entities.Cupom;

@Stateless
public class CupomDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Cupom cupom){
		em.persist(cupom);
	}
	
	public Cupom atualizar(Cupom cupom){
		return em.merge(cupom);
	}
	
	public void remover(Cupom cupom){
		em.remove(cupom);
	}
	
	public Cupom buscarPorId(Long id){
		return em.find(Cupom.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cupom> buscarTodos(){
		String consulta = "select c from Cupom c";
		TypedQuery<Cupom> query = (TypedQuery<Cupom>) 
		em.createQuery(consulta);
		return query.getResultList();		
	}	
}