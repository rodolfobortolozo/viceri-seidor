package br.com.viceri.todo.repositories;

import br.com.viceri.todo.models.Prioridade;
import br.com.viceri.todo.models.Tarefa;
import br.com.viceri.todo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

  List<Tarefa> findTarefaByUsuario(Usuario usuario);

  Optional<Tarefa> findTarefaByUsuarioAndId(Usuario usuario, Long id);

  @Query(value = "SELECT t FROM Tarefa t WHERE t.concluida = false AND t.prioridade LIKE %?1% AND t.usuario = ?2 ")
  List<Tarefa> findByConcluidaIsFalse(Prioridade prioridade, Usuario usuario);

}
