package br.com.viceri.todo.controllers;

import br.com.viceri.todo.models.Prioridade;
import br.com.viceri.todo.models.dto.TarefaReq;
import br.com.viceri.todo.models.dto.TarefaRes;
import br.com.viceri.todo.services.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/tarefas"})
@Tag(name="Tarefas")
public class TarefaController {

  private final TarefaService tarefaService;

  public TarefaController(TarefaService tarefaService) {
    this.tarefaService = tarefaService;
  }

  @Operation(summary = "Buscar Tarefas", description = "Busca todas as tarefas")
  @SecurityRequirement(name = "Bearer Authentication")
  @GetMapping
  public ResponseEntity<Object> getAll(@RequestHeader(value = "Authorization") String authorization){

    return this.tarefaService.getAll(authorization);
  }

  @Operation(summary = "Salvar Tarefa", description = "Salva a tarefa")
  @SecurityRequirement(name = "Bearer Authentication")
  @PostMapping
  public ResponseEntity<TarefaRes> save(@Valid @RequestBody TarefaReq tarefaReq,
                                        @RequestHeader(value = "Authorization") String authorization){
    tarefaReq.setAuthorization(authorization);
    return this.tarefaService.save(tarefaReq);
  }

  @Operation(summary = "Deletar Tarefa", description = "Deleta a tarefa")
  @SecurityRequirement(name = "Bearer Authentication")
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Object> delete(@PathVariable(name = "id") Long id,
                                       @RequestHeader(value = "Authorization") String authorization){

    return this.tarefaService.deleteById(id, authorization);
  }

  @Operation(summary = "Atualizar Tarefa", description = "Atualiza as tarefas")
  @SecurityRequirement(name = "Bearer Authentication")
  @PutMapping(path = "/{id}")
  public ResponseEntity<TarefaRes> update(@PathVariable(name = "id") Long id,
                                          @Valid @RequestBody TarefaReq tarefaReq,
                                          @RequestHeader(value = "Authorization") String authorization){

    return this.tarefaService.update(id, tarefaReq, authorization);
  }

  @Operation(summary = "Lista Tarefas Pendentes", description = "Busca todas as tarefas pendentes")
  @SecurityRequirement(name = "Bearer Authentication")
  @GetMapping("/pendentes")
  public ResponseEntity<Object> getTarefasPendente(@RequestParam(name = "prioridade", required = false) Prioridade prioridade,
                                                   @RequestHeader(value = "Authorization") String authorization){

    return this.tarefaService.getTarefasPendente(prioridade, authorization);
  }

  @Operation(summary = "Conclui Tarefas Pendentes", description = "Altera o status para Concluído das Tarefas")
  @SecurityRequirement(name = "Bearer Authentication")
  @PostMapping("/{id}/concluida")
  public ResponseEntity<TarefaRes> updateTarefaConcluida(@PathVariable(name = "id") Long id,
                                                         @RequestHeader(value = "Authorization") String authorization){

    return this.tarefaService.updateTarefaConcluida(id, authorization);
  }
}
