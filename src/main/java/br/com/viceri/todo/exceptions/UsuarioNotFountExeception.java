package br.com.viceri.todo.exceptions;

public class UsuarioNotFountExeception extends RuntimeException {

  public UsuarioNotFountExeception(String msg){
    super(msg);
  }
  public UsuarioNotFountExeception(String msg, Throwable cause){
    super(msg, cause);
  }
}
