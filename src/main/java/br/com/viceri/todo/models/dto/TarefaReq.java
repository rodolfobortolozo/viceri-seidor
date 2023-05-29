package br.com.viceri.todo.models.dto;

import br.com.viceri.todo.models.Prioridade;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class TarefaReq {

  @JsonProperty("Prioridade")
  @NotNull(message = "Prioridade obrigatório")
  @Schema(name = "Prioridade", example = "ALTA")
  private Prioridade prioridade;

  @JsonProperty("Descricao")
  @NotEmpty(message = "Descrição obrigatório")
  @Schema(name = "Descricao", example = "Readme.md Viceri")
  private String descricao;

  @JsonFormat(pattern="dd/MM/yyyy")
  @NotNull(message = "Data da Tarefa Obrigatória")
  @JsonProperty("DtaTarefa")
  @Schema(name = "DtaTarefa", type = "string", example = "27/05/2023")
  private Date dtaTarefa;

  @JsonIgnore
  private String authorization;

}
