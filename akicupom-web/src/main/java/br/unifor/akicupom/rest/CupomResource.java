package br.unifor.akicupom.rest;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.unifor.akicupom.BO.CupomBO;
import br.unifor.akicupom.entities.Cupom;

@RequestScoped
@Path("/akicupom/cupom")
public class CupomResource {

	@Inject
	private CupomBO cupomBO;
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listar() {
		Collection<Cupom> cupom = cupomBO.verTodosCupons();
		if(cupom == null || cupom.isEmpty()) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(cupom).build();
	}
	
	@GET
	@Path("/listar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listar(@PathParam("id") Long id) {
		Cupom cupom = cupomBO.buscarPorId(id);
		if(cupom == null) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(cupom).build();
	}
	
	@POST
	@Path("/novo/{titulo}/{nome}/{dataValidade}/{dataGeracao}/{codigoCupom}")
	public Response novoCupom(
			@PathParam("titulo") String titulo,
			@PathParam("nome") String nome,
			@PathParam("dataValidade") String dataValidade,
			@PathParam("dataGeracao") String dataGeracao,
			@PathParam("codigoCupom") String codigoCupom) {
		Cupom cupom = new Cupom();
		cupom.setTitulo(titulo);
		cupom.setNome(nome);
		cupom.setDataValidade(dataValidade);
		cupom.setDataGeracao(dataGeracao);
		cupom.setCodigoCupom(codigoCupom);
		cupomBO.inserirCupom(cupom);
		return Response.ok().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
