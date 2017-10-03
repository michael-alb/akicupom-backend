package br.unifor.akicupom.BO;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.unifor.akicupom.DAO.CategoriaDAO;
import br.unifor.akicupom.entities.Categoria;

@Stateless
public class CategoriaBO {

	@EJB
	private CategoriaDAO categoriaDAO;
	
	public void inserirCategoria(Categoria categoria){
		categoria.setNome("Restaurantes");
		categoriaDAO.salvar(categoria);
	}
	
	public Categoria atualizarCategoria(Categoria categoria){
		return categoriaDAO.atualizar(categoria);
	}
	
	public void removerCategoria(Categoria categoria){
		Categoria categoriaARemover = categoriaDAO.buscarPorId(categoria.getId());
		categoriaDAO.remover(categoriaARemover);
	}
	
	public Categoria buscarPorId(Long id){
		return categoriaDAO.buscarPorId(id);
	}
	
	public List<Categoria> verTodasCategorias(){
		return categoriaDAO.buscarTodos();
	}
}
