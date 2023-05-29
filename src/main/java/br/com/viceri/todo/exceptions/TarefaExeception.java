package br.com.viceri.todo.exceptions;

public class TarefaExeception extends RuntimeException{

  public TarefaExeception(String msg){
    super(msg);
  }
  public TarefaExeception(String msg, Throwable cause){
    super(msg, cause);
  }
}
