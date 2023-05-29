package br.com.viceri.todo.mappers;

import br.com.viceri.todo.models.Usuario;
import br.com.viceri.todo.models.dto.UsuarioLogin;
import br.com.viceri.todo.models.dto.UsuarioReq;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {
  UsuarioMapper INSTANCE = Mappers.getMapper( UsuarioMapper.class );

  Usuario reqToUsuario (UsuarioReq usuarioReq);

  Usuario loginToUsuario(UsuarioLogin usuarioLogin);

}
