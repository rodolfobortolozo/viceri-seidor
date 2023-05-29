package br.com.viceri.todo.exceptions;

public class TarefaNotFountExeception extends RuntimeException {

  public TarefaNotFountExeception(String msg){
    super(msg);
  }
  public TarefaNotFountExeception(String msg, Throwable cause){
    super(msg, cause);
  }
}
