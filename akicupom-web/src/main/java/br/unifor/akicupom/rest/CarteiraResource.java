package br.unifor.akicupom.rest;

import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.unifor.akicupom.BO.CarteiraBO;
import br.unifor.akicupom.BO.CupomBO;
import br.unifor.akicupom.entities.Carteira;
import br.unifor.akicupom.entities.Cupom;
import br.unifor.akicupom.entities.Usuario;

@RequestScoped
@Path("/akicupom/carteira")
public class CarteiraResource {

	@Inject
	private CarteiraBO carteiraBO;	
	
	@Inject
	private CupomBO cupomBO;


	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarCarteiras(){
		List<Carteira> carteira = carteiraBO.verTodasCarteiras();
		if(carteira == null || carteira.isEmpty()){
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(carteira).build();
	}	

	@GET
	@Path("/listar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarCarteiras(@PathParam("id") Long id){
		Carteira carteira = carteiraBO.buscarPorId(id);
		if(carteira == null){
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(carteira).build();
	}	

	@POST
	@Path("novo/{idUsuario}/{idCupom}/{qtdCupons}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response novoCarteira(
			@PathParam("idUsuario") Long idUsuario,
			@PathParam("idCupom") Long idCupom,
			@PathParam("qtdCupons") int qtdCupons) {
		Carteira carteira = new Carteira();
		Usuario usuario = new Usuario();
		Cupom cupom = new Cupom();		
		usuario.setId(idUsuario);
		cupom = cupomBO.buscarPorId(idCupom);
		List<Cupom> cupomLista = new LinkedList<Cupom>();
		cupomLista.add(cupom);
		carteira.setCupons(cupomLista);
		carteira.setUsuario(usuario);
		carteira.setQtdCupons(qtdCupons);
		carteiraBO.inserirCarteira(carteira);
		return Response.ok().build();
	}

	@DELETE
	@Path("/remover/{id}")
	public Response removerCarteira(@PathParam("id") Long id) {
		Carteira carteira = carteiraBO.buscarPorId(id);
		if(carteira == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		carteiraBO.removerCarteira(carteira);
		return Response.ok().build();
	}
}