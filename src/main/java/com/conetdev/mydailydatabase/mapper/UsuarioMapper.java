package com.conetdev.mydailydatabase.mapper;

import com.conetdev.mydailydatabase.dto.UsuarioRequest;
import com.conetdev.mydailydatabase.model.TipoIdentidad;
import com.conetdev.mydailydatabase.model.TipoRol;
import com.conetdev.mydailydatabase.model.Usuario;
import com.conetdev.mydailydatabase.response.UsuarioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper de MapStruct que convierte entre entidades y DTOs.
 */
@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    /* ---------- Entity → Response DTO (lectura) ---------- */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombreUsuario", target = "nombre")
    @Mapping(source = "apellidoUsuario", target = "apellido")
    @Mapping(source = "identidadUsuario", target = "identidad")
    @Mapping(source = "celularUsuario", target = "celular")
    @Mapping(source = "estado", target = "estado")
    UsuarioResponse toUsuarioResponse(Usuario usuario);

    UsuarioResponse.RoleDto toUsuarioResponseRole(TipoRol rol);

    UsuarioResponse.TipoIdentidadDto toUsuarioResponseTipoIdentidad(TipoIdentidad tipo);

    /* ---------- DTO → Entity (creación) ---------- */
    @Mapping(source = "nombre", target = "nombreUsuario")
    @Mapping(source = "apellido", target = "apellidoUsuario")
    @Mapping(source = "identidad", target = "identidadUsuario")
    @Mapping(source = "celular", target = "celularUsuario")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "password", target = "passwordUsuario")
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "tipoIdentidad", target = "tipoIdentidad")
    @Mapping(target = "razonesSociales", ignore = true)
    @Mapping(target = "id", ignore = true) // id is managed by JPA
    Usuario toUsuarioEntity(UsuarioRequest dto);

    /* ---------- DTO → Entity (actualización) ---------- */
    @Mapping(source = "nombre", target = "nombreUsuario")
    @Mapping(source = "apellido", target = "apellidoUsuario")
    @Mapping(source = "identidad", target = "identidadUsuario")
    @Mapping(source = "celular", target = "celularUsuario")
    @Mapping(source = "estado", target = "estado")
    // @Mapping(source = "password", target = "passwordUsuario")
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "tipoIdentidad", target = "tipoIdentidad")
    @Mapping(target = "razonesSociales", ignore = true)
    void toUsuarioEntity(UsuarioRequest dto, @MappingTarget Usuario entity);

    /* ---------- Sub‑mappings ---------- */
    default List<TipoRol> mapRoles(Set<Long> roleIds) {
        if (roleIds == null)
            return null;
        return roleIds.stream()
                .map(this::toTipoRol)
                .collect(Collectors.toList());
    }

    default TipoRol toTipoRol(Long id) {
        TipoRol rol = new TipoRol();
        rol.setId(id);
        return rol;
    }

    default TipoIdentidad toTipoIdentidad(Long id) {
        TipoIdentidad ti = new TipoIdentidad();
        ti.setId(id);
        return ti;
    }
}