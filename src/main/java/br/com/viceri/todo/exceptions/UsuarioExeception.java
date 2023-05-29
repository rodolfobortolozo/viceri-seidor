package br.com.viceri.todo.exceptions;

public class UsuarioExeception extends RuntimeException{

  public UsuarioExeception(String msg){
    super(msg);
  }
  public UsuarioExeception(String msg, Throwable cause){
    super(msg, cause);
  }
}
