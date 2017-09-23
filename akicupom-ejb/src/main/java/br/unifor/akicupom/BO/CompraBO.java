package br.unifor.akicupom.BO;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.unifor.akicupom.DAO.CompraDAO;
import br.unifor.akicupom.entities.Compra;

@Stateless
public class CompraBO {

	@EJB
	private CompraDAO compraDAO;
	
	public void inserirCompra(Compra compra){
		compraDAO.salvar(compra);
	}
	
	public Compra atualizarCompra(Compra compra){
		return compraDAO.atualizar(compra);
	}
	
	public void removerCompra(Compra compra){
		Compra compraARemover = compraDAO.buscarPorId(compra.getId());
		compraDAO.remover(compraARemover);
	}
	
	public Compra buscarPorId(Long id){
		return compraDAO.buscarPorId(id);
	}
	
	public Collection<Compra> verTodasCompras(){
		return compraDAO.buscarTodos();
	}
}