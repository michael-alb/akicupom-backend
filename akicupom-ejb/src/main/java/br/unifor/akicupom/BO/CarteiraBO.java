package br.unifor.akicupom.BO;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.unifor.akicupom.DAO.CarteiraDAO;
import br.unifor.akicupom.entities.Carteira;

@Stateless
public class CarteiraBO {

	@EJB
	private CarteiraDAO carteiraDAO;
	
	public void inserirCarteira(Carteira carteira){
		carteiraDAO.salvar(carteira);
	}
	
	public Carteira atualizarCarteira(Carteira carteira){
		return carteiraDAO.atualizar(carteira);
	}
	
	public void removerCarteira(Carteira carteira){
		Carteira carteiraARemover = carteiraDAO.buscarPorId(carteira.getId());
		carteiraDAO.remover(carteiraARemover);
	}
	
	public Carteira buscarPorId(Long id){
		return carteiraDAO.buscarPorId(id);
	}
	
	public List<Carteira> verTodasCarteiras(){
		return carteiraDAO.buscarTodos();
	}	
}