package br.com.viceri.todo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "USUARIOS")
public class Usuario {

  @Id
  @Column(name = "IDUSUARIO")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NOME")
  private String Nome;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "SENHA")
  private String senha;
}
