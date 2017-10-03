package br.unifor.akicupom.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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

import br.unifor.akicupom.BO.FornecedorBO;
import br.unifor.akicupom.entities.Fornecedor;

@RequestScoped
@Path("akicupom/fornecedor")
public class FornecedorResource{
	
	@Inject
	private FornecedorBO fornecedorBO;
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarFornecedor() {
		List<Fornecedor> fornecedor = fornecedorBO.verTodosFornecedores();
		if(fornecedor == null) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(fornecedor).build();
	}
	
	@POST
	@Path("/novo/{nome}/{cnpj}/{endereco}/{telefone}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response novoFornecedor(
			@PathParam("nome") String nome,
			@PathParam("cnpj") String cnpj,
			@PathParam("endereco") String endereco,
			@PathParam("telefone") String telefone) {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome(nome);
		fornecedor.setCnpj(cnpj);
		fornecedor.setTelefone(telefone);
		fornecedor.setEndereco(endereco);
		fornecedorBO.inserirFornecedor(fornecedor);
		return Response.ok().build();		
	}
	
	@PUT
	@Path("/atualizar/{id}/{nome}/{cnpj}/{endereco}/{telefone}")
	public Response atualizarFornecedor(
			@PathParam("id") Long id,
			@PathParam("nome") String nome,
			@PathParam("cnpj") String cnpj,
			@PathParam("endereco") String endereco,
			@PathParam("telefone") String telefone) {
		Fornecedor fornecedor = fornecedorBO.buscarPorId(id);
		fornecedor.setNome(nome);
		fornecedor.setCnpj(cnpj);
		fornecedor.setEndereco(endereco);
		fornecedor.setTelefone(telefone);
		fornecedorBO.atualizarFornecedor(fornecedor);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/remover/{id}")
	public Response removerFornecedor(@PathParam("id") Long id) {
		Fornecedor fornecedorExistente = fornecedorBO.buscarPorId(id);
		if(fornecedorExistente == null) {
			throw new WebApplicationException(Status.NOT_MODIFIED);
		}
		fornecedorBO.removerFornecedor(fornecedorExistente);
		return Response.ok().build();		
	}
}