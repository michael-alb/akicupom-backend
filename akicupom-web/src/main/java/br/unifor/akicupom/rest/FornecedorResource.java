package br.unifor.akicupom.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.unifor.akicupom.BO.FornecedorBO;
import br.unifor.akicupom.entities.Fornecedor;

@RequestScoped
@Path ("akicupom/fornecedor")

public class FornecedorResource{
	
	@Inject
	private FornecedorBO fornecedorBO;
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  listarFornecedor() {
		List<Fornecedor> fornecedor = fornecedorBO.verTodosFornecedores();
		if(fornecedor == null) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(fornecedor).build();
	}
}
