package br.unifor.akicupom.BO;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.unifor.akicupom.DAO.UsuarioDAO;
import br.unifor.akicupom.entities.Usuario;

@Stateless
public class UsuarioBO {
	
	@EJB
	private UsuarioDAO usuarioDAO;
	
	public void inserirUsuario(Usuario usuario){
		usuarioDAO.salvar(usuario);
	}
	
	public Usuario atualizarUsuario(Usuario usuario){
		return usuarioDAO.atualizar(usuario);
	}
	
	public void removerUsuario(Usuario usuario){
		Usuario usuarioARemover = usuarioDAO.buscarPorId(usuario.getId());
		usuarioDAO.remover(usuarioARemover);
	}
	
	public Usuario buscarPorId(Long id){
		return usuarioDAO.buscarPorId(id);
	}
	
	public List<Usuario> verTodosUsuarios(){
		return usuarioDAO.buscarTodos();
	}
	
	public List<Usuario> verTodosUsuariosNome(){
		return usuarioDAO.buscarTodosPorNome();
	}
}