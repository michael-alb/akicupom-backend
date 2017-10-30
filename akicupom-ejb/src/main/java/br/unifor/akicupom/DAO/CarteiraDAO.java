package br.unifor.akicupom.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.unifor.akicupom.entities.Carteira;

@Stateless
public class CarteiraDAO {
	
	@PersistenceContext
	private EntityManager em;

	public void salvar(Carteira carteira){
		em.persist(carteira);
	}
	
	public Carteira atualizar(Carteira carteira){
		return em.merge(carteira);
	}
	
	public void remover(Carteira carteira){
		em.remove(carteira);
	}
	
	public Carteira buscarPorId(Long id){
		return em.find(Carteira.class, id);
	}
	
	public List<Carteira> buscarTodos(){
		String consulta = "select c from Carteira c";
		TypedQuery<Carteira> query = em.createQuery(consulta, Carteira.class);
		return query.getResultList();
	}
}