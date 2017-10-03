package br.unifor.akicupom.rest;

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

import br.unifor.akicupom.BO.CupomBO;
import br.unifor.akicupom.entities.Cupom;
import br.unifor.akicupom.entities.Promocao;
import br.unifor.akicupom.entities.Usuario;

@RequestScoped
@Path("/akicupom/cupom")
public class CupomResource {

	@Inject
	private CupomBO cupomBO;
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listar() {
		List<Cupom> cupom = cupomBO.verTodosCupons();
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
	@Path("/novo/{titulo}/{nome}/{dataValidade}/{dataGeracao}/{codigoCupom}/{usuario}/{promocao}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response novoCupom(
			@PathParam("titulo") String titulo,
			@PathParam("nome") String nome,
			@PathParam("dataValidade") String dataValidade,
			@PathParam("dataGeracao") String dataGeracao,
			@PathParam("codigoCupom") String codigoCupom,
			@PathParam("usuario") Long idUsuario,
			@PathParam("promocao") Long idPromocao) {
		Cupom cupom = new Cupom();
		cupom.setTitulo(titulo);
		cupom.setNome(nome);
		cupom.setDataValidade(dataValidade);
		cupom.setDataGeracao(dataGeracao);
		cupom.setCodigoCupom(codigoCupom);
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		cupom.setUsuario(usuario);
		Promocao promocaoObj = new Promocao();
		promocaoObj.setId(idPromocao);
		cupom.setPromocao(promocaoObj);
		cupomBO.inserirCupom(cupom);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/remover/{id}")
	public Response removerCupom(@PathParam("id") Long id) {
		Cupom cupom = cupomBO.buscarPorId(id);
		if(cupom == null) {
			throw new WebApplicationException(Status.NO_CONTENT);
		}
		cupomBO.removerCupom(cupom);
		return Response.ok().build();
	}
}