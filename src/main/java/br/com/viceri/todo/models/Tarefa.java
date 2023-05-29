package br.com.viceri.todo.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data
@Table(name = "TAREFAS")
public class Tarefa {

  @Id
  @Column(name = "IDTAREFA")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "PRIORIDADE")
  @Enumerated(EnumType.STRING)
  private Prioridade prioridade;

  @Column(name = "DESCRICAO")
  private String descricao;

  @Column(name = "DTATAREFA")
  private Date dtaTarefa;

  @Column(name = "concluida")
  private Boolean concluida;

  @Column(name = "DTACONCLUIDA")
  private Date dtaConcluida;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "IDUSUARIO")
  private Usuario usuario;

  @Column(name = "DTAINSERT", nullable = false, updatable = false)
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date dtaInsert;

  @Column(name = "DTAUPDATE", nullable = false)
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date dtaUpdate;

}
