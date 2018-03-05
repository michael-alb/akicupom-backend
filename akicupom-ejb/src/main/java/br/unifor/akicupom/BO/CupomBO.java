package br.unifor.akicupom.BO;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.unifor.akicupom.DAO.CupomDAO;
import br.unifor.akicupom.entities.Cupom;

@Stateless
public class CupomBO {
	
	@EJB
	private CupomDAO cupomDAO;
	
	public void inserirCupom(Cupom cupom){
		cupomDAO.salvar(cupom);
	}
	
	public Cupom atualizarCupom(Cupom cupom){
		return cupomDAO.atualizar(cupom);
	}
	
	public void removerCupom(Cupom cupom){
		Cupom cupomARemover = cupomDAO.buscarPorId(cupom.getId());
		cupomDAO.remover(cupomARemover);
	}
	
	public Cupom buscarPorId(Long id){
		return cupomDAO.buscarPorId(id);
	}
	
	public List<Cupom> verTodosCupons(){
		return cupomDAO.buscarTodos();
	}
}