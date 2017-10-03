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

import br.unifor.akicupom.BO.CategoriaBO;
import br.unifor.akicupom.entities.Categoria;

@RequestScoped
@Path("akicupom/categoria")
public class CategoriaResource {

	@Inject
	private CategoriaBO categoriaBO;
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarCategorias() {
		List<Categoria> categoria = categoriaBO.verTodasCategorias();
		if(categoria == null || categoria.isEmpty()) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(categoria).build();
	}
	
	@POST
	@Path("/novo/{nome}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response novaCategoria(@PathParam("nome") String nome) {
		Categoria categoria = new Categoria();
		categoria.setNome(nome);
		categoriaBO.inserirCategoria(categoria);
		return Response.ok().build();		
	}
	
	@DELETE
	@Path("/remover/{id}")
	public Response removerCategoria(@PathParam("id") Long id) {
		Categoria categoria = categoriaBO.buscarPorId(id);
		if(categoria == null) {
			throw new WebApplicationException(Status.NOT_MODIFIED);
		}
		categoriaBO.removerCategoria(categoria);
		return Response.ok().build();
	}
	
	
	
}
