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

import br.unifor.akicupom.BO.PromocaoBO;
import br.unifor.akicupom.entities.Categoria;
import br.unifor.akicupom.entities.Fornecedor;
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
		List<Promocao> promocao = promocaoBO.verTodasPromocoes();
		if(promocao == null || promocao.isEmpty()) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(promocao).build();
	}

	@POST
	@Path("/novo/{nome}/{descricao}/{valor_promocao}/{dataValidade}/{capa}/{status}/{categoria}/{fornecedor}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response novaPromocao(
			@PathParam("nome") String nome,
			@PathParam("descricao") String descricao,
			@PathParam("valor_promocao") Long valor_promocao,
			@PathParam("dataValidade") String dataValidade,
			@PathParam("capa") String capa,
			@PathParam("status") boolean status,
			@PathParam("categoria") Long idCategoria,
			@PathParam("fornecedor") Long idFornecedor){
		Promocao promocao = new Promocao();
		promocao.setNome(nome);
		promocao.setDescricao(descricao);
		promocao.setValor_promocao(valor_promocao);
		promocao.setDataValidade(dataValidade);
		promocao.setCapa(capa);
		promocao.setStatus(status);
		Categoria categoria = new Categoria();
		categoria.setId(idCategoria);
		promocao.setCategoria(categoria);
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(idFornecedor);
		promocao.setFornecedor(fornecedor);
		promocaoBO.inserirPromocao(promocao);
		return Response.ok().build();	
	}
	
	@PUT
	@Path("/atualizar/{id}/{nome}/{descricao}/{valor_promocao}/{dataValidade}/{capa}/{status}/{categoria}/{fornecedor}")
	public Response atualizarPromocao(@PathParam("id") Long id,
			@PathParam("nome") String nome,
			@PathParam("descricao") String descricao,
			@PathParam("valor_promocao") Long valor_promocao,
			@PathParam("dataValidade") String dataValidade,
			@PathParam("capa") String capa,
			@PathParam("status") boolean status,
			@PathParam("categoria") String nomeCategoria,
			@PathParam("fornecedor") Long idFornecedor){
		Promocao promocaoExistente = promocaoBO.buscarPorId(id);
		promocaoExistente.setNome(nome);
		promocaoExistente.setDescricao(descricao);
		promocaoExistente.setValor_promocao(valor_promocao);
		promocaoExistente.setDataValidade(dataValidade);
		promocaoExistente.setCapa(capa);
		promocaoExistente.setStatus(status);
		Categoria categoria = new Categoria();
		categoria.setNome(nomeCategoria);
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(idFornecedor);
		promocaoExistente.setFornecedor(fornecedor);
		promocaoExistente.setCategoria(categoria);
		promocaoBO.atualizarPromocao(promocaoExistente);
		return Response.ok().build();
	}
	
	/* @PUT com consumes ainda com problemas */
	
	@PUT
	@Path("/atualizar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarPromocao(@PathParam("id") Long id, 
			Promocao promocao) {
		Promocao promocaoExistente = promocaoBO.buscarPorId(id);
		if(promocaoExistente == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		promocaoExistente.setNome(promocao.getNome());
		promocaoExistente.setDescricao(promocao.getDescricao());
		promocaoExistente.setValor_promocao(promocao.getValor_promocao());
		promocaoExistente.setDataValidade(promocao.getDataValidade());
		promocaoExistente.setCapa(promocao.getCapa());
		promocaoExistente.setStatus(promocao.isStatus());
		promocaoExistente.setCategoria(promocao.getCategoria());		
		promocaoBO.atualizarPromocao(promocaoExistente);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/remover/{id}")
	public Response removerPromocao(@PathParam("id") Long id) {
		Promocao promocao = promocaoBO.buscarPorId(id);
		if(promocao == null) {
			throw new WebApplicationException(Status.NOT_MODIFIED);
		}
		promocaoBO.removerPromocao(promocao);		
		return Response.ok().build();
	}	
}