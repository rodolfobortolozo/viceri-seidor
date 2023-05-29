package br.com.viceri.todo.models.dto;

import br.com.viceri.todo.models.Prioridade;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class TarefaRes {

  @JsonProperty("Id")
  @Schema(required = true, name = "Id", example = "1")
  private Long id;

  @JsonProperty("Prioridade")
  @Schema(required = true, name = "Prioridade", example = "ALTA")
  private Prioridade prioridade;

  @JsonProperty("Descricao")
  @Schema(required = true, name = "Descricao", example = "Readme.md Viceri")
  private String descricao;

  @JsonFormat(pattern="dd/MM/yyyy")
  @JsonProperty("DtaTarefa")
  @Schema(name = "DtaTarefa", type = "string", example = "27/05/2023")
  private Date dtaTarefa;

  @JsonProperty("Conculida")
  @Schema(name = "Conculida", example = "true")
  private Boolean concluida;

  @JsonProperty("DtaConcluida")
  @Schema(name = "DtaConcluida", type = "string", example = "28/05/2023 18:10:15")
  private Date dtaConcluida;

  @JsonProperty("DtaInsert")
  @Schema(name = "DtaInsert", type = "string", example = "27/05/2023 15:15:41")
  private Date dtaInsert;

  @JsonProperty("DtaUpdate")
  @Schema(name = "DtaInsert", type = "string", example = "27/05/2023 15:15:41")
  private Date dtaUpdate;
}
