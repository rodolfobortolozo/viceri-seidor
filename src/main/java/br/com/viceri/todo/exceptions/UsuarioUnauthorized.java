package br.com.viceri.todo.exceptions;

public class UsuarioUnauthorized extends RuntimeException{

  public UsuarioUnauthorized(String msg){
    super(msg);
  }
  public UsuarioUnauthorized(String msg, Throwable cause){
    super(msg, cause);
  }
}
