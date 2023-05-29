package br.com.viceri.todo.services;

import br.com.viceri.todo.exceptions.TarefaExeception;
import br.com.viceri.todo.exceptions.TarefaNotFountExeception;
import br.com.viceri.todo.helpers.UsuarioAutorization;
import br.com.viceri.todo.mappers.TarefaMapper;
import br.com.viceri.todo.models.Prioridade;
import br.com.viceri.todo.models.Tarefa;
import br.com.viceri.todo.models.Usuario;
import br.com.viceri.todo.models.dto.TarefaReq;
import br.com.viceri.todo.models.dto.TarefaRes;
import br.com.viceri.todo.repositories.TarefaRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TarefaService {

  private final TarefaRepository tarefaRepository;

  private final UsuarioAutorization usuario;

  public TarefaService(TarefaRepository tarefaRepository, UsuarioAutorization usuario) {
    this.tarefaRepository = tarefaRepository;
    this.usuario = usuario;
  }

  public ResponseEntity<Object> getAll(String authorization){

    Usuario authUsuario = usuario.getIdUsuario(authorization);

    return ResponseEntity.status(HttpStatus.OK).body(
            this.tarefaRepository.findTarefaByUsuario(authUsuario)
                    .stream()
                    .map(TarefaMapper.INSTANCE::tarefatoRes)
    );

  }

  @Transactional
  public ResponseEntity<TarefaRes> save(TarefaReq tarefaReq) {

    Usuario authUsuario = usuario.getIdUsuario(tarefaReq.getAuthorization());

    Tarefa tarefa =  TarefaMapper.INSTANCE.reqToTarefa(tarefaReq);
    tarefa.setConcluida(false);
    tarefa.setUsuario(authUsuario);
    Tarefa newTarefa = this.tarefaRepository.save(tarefa);

    return ResponseEntity.status(HttpStatus.CREATED).body(
            TarefaMapper.INSTANCE.tarefatoRes(newTarefa)
    );
  }

  @Transactional
  public ResponseEntity<Object> deleteById(Long id, String authorization)  {

    Usuario authUsuario = usuario.getIdUsuario(authorization);

    return this.tarefaRepository.findTarefaByUsuarioAndId(authUsuario, id)
            .map( tarefa -> {
              this.tarefaRepository.deleteById(tarefa.getId());
              return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

            }).orElseThrow(() -> new TarefaNotFountExeception("Nenhuma Tarefa foi encontrada com o id: "+id));

  }

  @Transactional
  public ResponseEntity<TarefaRes> update(Long id, TarefaReq tarefaReq, String authorization){

    Usuario authUsuario = usuario.getIdUsuario(authorization);

    return  ResponseEntity.status(HttpStatus.OK).body(
                    this.tarefaRepository.findTarefaByUsuarioAndId(authUsuario, id)
                            .map(tarefa -> {
                              Tarefa updateTarefa = TarefaMapper.INSTANCE.reqToTarefa(tarefaReq);

                              updateTarefa.setId(tarefa.getId());
                              updateTarefa.setConcluida(tarefa.getConcluida());
                              updateTarefa.setDtaUpdate(new Date());
                              updateTarefa.setDtaInsert(tarefa.getDtaInsert());
                              updateTarefa.setDtaConcluida(tarefa.getDtaConcluida());
                              updateTarefa.setUsuario(authUsuario);

                              Tarefa newTarefa = this.tarefaRepository.save(updateTarefa);
                              return TarefaMapper.INSTANCE.tarefatoRes(newTarefa);

                            })
                            .orElseThrow(() -> new TarefaNotFountExeception("Nenhuma Tarefa foi encontrada com o id: "+id))
    );

  }

  public ResponseEntity<Object> getTarefasPendente(Prioridade prioridade, String authorization){

    Usuario authUsuario = usuario.getIdUsuario(authorization);

    return ResponseEntity.status(HttpStatus.OK).body(
            this.tarefaRepository.findByConcluidaIsFalse(prioridade, authUsuario)
                    .stream()
                    .map(TarefaMapper.INSTANCE::tarefatoRes)
    );

  }

  @Transactional
  public ResponseEntity<TarefaRes> updateTarefaConcluida(Long id, String authorization ){

    Usuario authUsuario = usuario.getIdUsuario(authorization);

    return  ResponseEntity.status(HttpStatus.OK).body(
            this.tarefaRepository.findTarefaByUsuarioAndId(authUsuario, id)
                    .map(tarefa -> {

                      if(tarefa.getConcluida()){
                        throw new TarefaExeception("Tarefa ja foi marcada como concluída.");
                      }

                      Tarefa updateTarefa = tarefa;
                      updateTarefa.setConcluida(true);
                      updateTarefa.setDtaConcluida(new Date());
                      Tarefa newTarefa = this.tarefaRepository.save(updateTarefa);
                      return TarefaMapper.INSTANCE.tarefatoRes(newTarefa);

                    }).orElseThrow(() -> new TarefaNotFountExeception("Nenhuma Tarefa foi encontrada com o id: "+id))
    );

  }

}
