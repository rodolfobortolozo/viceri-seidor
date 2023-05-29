package br.com.viceri.todo.controllers;

import br.com.viceri.todo.exceptions.UsuarioExeception;
import br.com.viceri.todo.exceptions.UsuarioUnauthorized;
import br.com.viceri.todo.mappers.UsuarioMapper;
import br.com.viceri.todo.models.dto.UsuarioLogin;
import br.com.viceri.todo.models.dto.UsuarioReq;
import br.com.viceri.todo.util.JWTUtil;
import br.com.viceri.todo.models.Usuario;
import br.com.viceri.todo.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@Tag(name="Registro Usuário")
public class AuthController {

  private final UsuarioRepository usuarioRepository;
  private final JWTUtil jwtUtil;
  private final AuthenticationManager authManager;
  private final PasswordEncoder passwordEncoder;

  public AuthController(UsuarioRepository usuarioRepository, JWTUtil jwtUtil, AuthenticationManager authManager, PasswordEncoder passwordEncoder) {
    this.usuarioRepository = usuarioRepository;
    this.jwtUtil = jwtUtil;
    this.authManager = authManager;
    this.passwordEncoder = passwordEncoder;
  }

  @Operation(summary = "Cadastro de Usuario", description = "Cadastra Usuário e retorna o token")
  @PostMapping("/registro")
  public ResponseEntity<Object> registrarUsuario(@Valid @RequestBody UsuarioReq usuarioReq){
    Optional<Usuario> optUsuario = this.usuarioRepository.findByEmail(usuarioReq.getEmail());

    if(optUsuario.isPresent()){
      throw new UsuarioExeception("Email já cadastrado");
    }

    Usuario usuario = UsuarioMapper.INSTANCE.reqToUsuario(usuarioReq);

    String encodedPass = passwordEncoder.encode(usuarioReq.getSenha());
    usuario.setSenha(encodedPass);
    Usuario newUsuario = usuarioRepository.save(usuario);
    String token = jwtUtil.generateToken(newUsuario);

    return ResponseEntity.status(HttpStatus.CREATED)
            .body(Collections.singletonMap("viceri-token", token));

  }

  @Operation(summary = "Login", description = "Login para retornar o token")
  @PostMapping("/login")
  public ResponseEntity<Object> loginUsuario(@Valid @RequestBody UsuarioLogin usuarioLogin){
    try {

      Usuario usuario = UsuarioMapper.INSTANCE.loginToUsuario(usuarioLogin);

      UsernamePasswordAuthenticationToken authInputToken =
              new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha());

      authManager.authenticate(authInputToken);

      String token = jwtUtil.generateToken(usuario);

      return ResponseEntity.status(HttpStatus.OK)
              .body(Collections.singletonMap("viceri-token", token));

    }catch (AuthenticationException authExc){

      throw new UsuarioUnauthorized("Credenciais Inválidas");
    }
  }


}
