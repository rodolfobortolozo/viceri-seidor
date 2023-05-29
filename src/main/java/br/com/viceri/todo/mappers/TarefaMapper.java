package br.com.viceri.todo.mappers;

import br.com.viceri.todo.models.Tarefa;
import br.com.viceri.todo.models.dto.TarefaReq;
import br.com.viceri.todo.models.dto.TarefaRes;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TarefaMapper {
  TarefaMapper INSTANCE = Mappers.getMapper( TarefaMapper.class );

  Tarefa reqToTarefa (TarefaReq tarefaReq);

  TarefaRes tarefatoRes (Tarefa tarefa);
}
