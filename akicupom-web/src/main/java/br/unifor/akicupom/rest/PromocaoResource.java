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

import br.unifor.akicupom.BO.PromocaoBO;
import br.unifor.akicupom.entities.Promocao;


@RequestScoped
@Path("akicupom/promocao")
public class PromocaoResource {

	@Inject
	private PromocaoBO promocaoBO;

	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarPromocoes() {
		Collection<Promocao> promocao = promocaoBO.verTodasPromocoes();
		if(promocao == null || promocao.isEmpty()) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(promocao).build();
	}


	/* Post em relacao a categoria esta errado */

	@POST
	@Path("/novo/{nome}/{descricao}/{valor_promocao}/{dataValidade}/{capa}/{status}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response novaPromocao(
			@PathParam("nome") String nome,
			@PathParam("descricao") String descricao,
			@PathParam("valor_promocao") Long valor_promocao,
			@PathParam("dataValidade") String dataValidade,
			@PathParam("capa") String capa,
			@PathParam("status") boolean status){
		Promocao promocao = new Promocao();
		promocao.setNome(nome);
		promocao.setDescricao(descricao);
		promocao.setValor_promocao(valor_promocao);
		promocao.setDataValidade(dataValidade);
		promocao.setCapa(capa);
		promocao.setStatus(status);
		return Response.ok().build();	
	}
}