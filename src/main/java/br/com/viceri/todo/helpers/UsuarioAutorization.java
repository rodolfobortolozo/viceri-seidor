package br.com.viceri.todo.helpers;

import br.com.viceri.todo.exceptions.UsuarioNotFountExeception;
import br.com.viceri.todo.models.Usuario;
import br.com.viceri.todo.services.UsuarioDetailsService;
import br.com.viceri.todo.util.JWTUtil;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioAutorization {

  private final JWTUtil jwtUtil;

  private final UsuarioDetailsService usuarioDetailsService;

  public UsuarioAutorization(JWTUtil jwtUtil, UsuarioDetailsService usuarioDetailsService) {
    this.jwtUtil = jwtUtil;
    this.usuarioDetailsService = usuarioDetailsService;
  }

  public Usuario getIdUsuario(String autorization){
    String token = autorization.substring(7);
    String email = jwtUtil.validateTokenAndRetrieveSubject(token);

    Optional<Usuario> usuario = this.usuarioDetailsService.findByEmail(email);

    if(usuario.isEmpty()){
      throw  new UsuarioNotFountExeception("Usuário não encontrado");
    }

    return usuario.get();
  }
}
