package br.unifor.akicupom.DAO;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.unifor.akicupom.entities.Fornecedor;

@Stateless
public class FornecedorDAO {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Fornecedor fornecedor){
		em.persist(fornecedor);
	}

	public Fornecedor atualizar(Fornecedor fornecedor){
		return em.merge(fornecedor);
	}

	public void remover(Fornecedor fornecedor){
		em.remove(fornecedor);
	}

	public Fornecedor buscarPorId(Long id){
		return em.find(Fornecedor.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Fornecedor> buscarTodos(){
		String consulta = "select * from fornecedor";
		TypedQuery<Fornecedor> query = (TypedQuery<Fornecedor>) em.createNativeQuery(consulta);
		return query.getResultList();
	}
}
