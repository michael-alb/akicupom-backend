package br.unifor.akicupom.rest;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
	@Path("/listarCategorias")
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public Response listarCategorias() {
		Collection<Categoria> categoria = categoriaBO.verTodasCategorias();
		if(categoria == null || categoria.isEmpty()) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(categoria).build();
	}
	
}
