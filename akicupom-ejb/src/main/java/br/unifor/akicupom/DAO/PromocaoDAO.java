package br.unifor.akicupom.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.unifor.akicupom.entities.Promocao;

@Stateless
public class PromocaoDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Promocao promocao) {
		em.persist(promocao);
	}
	
	public Promocao atualizar(Promocao promocao) {
		return em.merge(promocao);
	}
	
	public void remover(Promocao promocao) {
		em.remove(promocao);
	}
	
	public Promocao buscarPorId(Long id) {
		return em.find(Promocao.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Promocao> buscarTodos(){
		String consulta = "select p from Promocao p";		
		TypedQuery<Promocao> query = 
				(TypedQuery<Promocao>) em.createQuery(consulta);
		return query.getResultList();
	}
}