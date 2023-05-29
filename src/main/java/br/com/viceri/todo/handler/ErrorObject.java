package br.com.viceri.todo.handler;

public record ErrorObject(
        String field,
        String message,
        Object parameter) {

}
