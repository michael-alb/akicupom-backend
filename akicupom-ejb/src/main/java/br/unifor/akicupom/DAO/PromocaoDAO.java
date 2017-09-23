package br.unifor.akicupom.DAO;

import java.util.Collection;

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
	public Collection<Promocao> buscarTodos(){
		String consulta = "select * from promocao";		
		TypedQuery<Promocao> query = 
				(TypedQuery<Promocao>) em.createNativeQuery(consulta);
		return query.getResultList();
	}
}