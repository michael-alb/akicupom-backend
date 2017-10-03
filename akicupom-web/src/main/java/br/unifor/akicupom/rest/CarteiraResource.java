package br.unifor.akicupom.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.unifor.akicupom.BO.CarteiraBO;
import br.unifor.akicupom.entities.Carteira;
import br.unifor.akicupom.entities.Cupom;

@RequestScoped
@Path("/akicupom/carteira")
public class CarteiraResource {
	
	@Inject
	private CarteiraBO carteiraBO;	
	
	@GET
	@Path("/listar")
	@Produces("aplication/json")
	public Response listarCarteiras(){
		List<Carteira> carteira = carteiraBO.verTodasCarteiras();
		if(carteira == null || carteira.isEmpty()){
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(carteira, MediaType.APPLICATION_JSON).build();
	}
	
	
	@POST
	@Path("/novo/{id}/{qtdCupons}")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public Response novaCarteira(
			@PathParam("id") Long id,
			@PathParam("qtdCupons") int qtdCupons) {
		Carteira carteira = new Carteira();
		Cupom cupom = new Cupom();
		cupom.setId(id);
		carteira.setCupoms((List<Cupom>) cupom);
		carteira.setQtdCupons(qtdCupons);
		carteiraBO.inserirCarteira(carteira);
		return Response.ok().build();		
	}
	
	@SuppressWarnings("unchecked")
	@PUT
	@Path("/atualizar/{id}/{codigoCupom}/{qtdCupons}")
	public Response atualizarCarteira(
			@PathParam("id") Long id,
			@PathParam("codigoCupom") String codigoCupom,
			@PathParam("qtdCupons") int qtdCupons) {
		Carteira carteira = carteiraBO.buscarPorId(id);
		Cupom cupom = new Cupom();
		cupom.setCodigoCupom(codigoCupom);
		carteira.setCupoms((List<Cupom>) cupom);
		carteira.setQtdCupons(qtdCupons);
		return Response.ok().build();
	}
	
	@PUT
	@Path("/atualizar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarCarteira(
			@PathParam("id") Long id, Carteira carteira){
		Carteira carteiraExistente = carteiraBO.buscarPorId(id);
		if(carteiraExistente == null) {
			throw new WebApplicationException(Status.NOT_MODIFIED);
		}
		carteiraExistente.setCupoms(carteira.getCupoms());
		carteiraExistente.setQtdCupons(carteira.getQtdCupons());
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