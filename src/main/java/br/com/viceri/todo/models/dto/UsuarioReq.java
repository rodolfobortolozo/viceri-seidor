package br.com.viceri.todo.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UsuarioReq {

  @JsonProperty("Nome")
  @NotEmpty(message = "Nome do usuário obrigatório")
  @Schema(name = "Nome", example = "Rodolfo R Bortolozo")
  private String Nome;

  @JsonProperty("Email")
  @Email(message = "Email Inválido")
  @NotEmpty(message = "Email Obrigatório")
  @Schema(name = "Email", example = "rodolfobortolozo@gmail.com")
  private String email;

  @JsonProperty("Senha")
  @NotEmpty(message = "Senha do usuário obrigatório")
  @Schema(name = "Senha", example = "123456")
  private String senha;
}
