package br.unifor.akicupom.BO;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.unifor.akicupom.DAO.FornecedorDAO;
import br.unifor.akicupom.entities.Fornecedor;

@Stateless
public class FornecedorBO {
	
	@EJB
	private FornecedorDAO fornecedorDAO;
	
	public void inserirFornecedor(Fornecedor fornecedor){
		fornecedorDAO.salvar(fornecedor);
	}
	
	public Fornecedor atualizarFornecedor(Fornecedor fornecedor){
		return fornecedorDAO.atualizar(fornecedor);
	}
	
	public void removerFornecedor(Fornecedor fornecedor){
		Fornecedor fornecedorARemover = fornecedorDAO.buscarPorId(fornecedor.getId());
		fornecedorDAO.remover(fornecedorARemover);
	}
	
	public Fornecedor buscarPorId(Long id){
		return fornecedorDAO.buscarPorId(id);
	}
	
	public List<Fornecedor> verTodosFornecedores(){
		return fornecedorDAO.buscarTodos();
	}
}