package br.unifor.akicupom.rest;

import java.util.Collection;

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

import br.unifor.akicupom.BO.UsuarioBO;
import br.unifor.akicupom.entities.Usuario;

@RequestScoped
@Path("/akicupom/usuario")
public class UsuarioResource {

	@Inject
	private UsuarioBO usuarioBO;
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarUsuarios(){
		Collection<Usuario> usuario = usuarioBO.verTodosUsuarios();
		if(usuario == null || usuario.isEmpty()){
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(usuario).build();
	}
	
	@GET
	@Path("/listar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarUsuariosID(@PathParam("id") Long id){
		Usuario usuario = usuarioBO.buscarPorId(id);
		if(usuario == null){
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(usuario).build();
	}	
	
	@GET
	@Path("/listarPorNome/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarUsuariosNome(){
		Collection<Usuario> usuario = usuarioBO.verTodosUsuariosNome();
		if(usuario == null){
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(usuario).build();
	}
	
	@POST
	@Path("/novo/{nome}/{email}/{senha}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response novoUsuario(
			@PathParam("nome") String nome, 
			@PathParam("email") String email,
			@PathParam("senha") String senha){
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuarioBO.inserirUsuario(usuario);
		return Response.ok().build();
	}
		
	@PUT
	@Path("/atualizar/{id}/{nome}/{email}")
	public Response atualizarUsuario(
			@PathParam("id") Long id,
			@PathParam("nome") String nome,
			@PathParam("email") String email){
		Usuario usuario = usuarioBO.buscarPorId(id);
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuarioBO.atualizarUsuario(usuario);
		return Response.ok().build();
	}
	
	@PUT
	@Path("/atualizar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarUsuario(
			@PathParam("id") Long id, Usuario usuario){
		Usuario usuarioExistente = usuarioBO.buscarPorId(id);
		if(usuarioExistente == null){
			throw new WebApplicationException(Status.NOT_MODIFIED);
		}
		usuarioExistente.setNome(usuario.getNome());
		usuarioExistente.setEmail(usuario.getEmail());
		usuarioBO.atualizarUsuario(usuarioExistente);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/remover/{id}")
	public Response removerUsuario(@PathParam("id") Long id){
		Usuario usuarioExistente = usuarioBO.buscarPorId(id);
		if(usuarioExistente == null){
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		usuarioBO.removerUsuario(usuarioExistente);
		return Response.ok().build();
	}
}