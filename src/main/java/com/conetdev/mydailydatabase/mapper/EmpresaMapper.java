package com.conetdev.mydailydatabase.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.conetdev.mydailydatabase.model.RazonesSociales;
import com.conetdev.mydailydatabase.model.TipoIdentidades;
import com.conetdev.mydailydatabase.request.EmpresaRequest;
import com.conetdev.mydailydatabase.response.EmpresaResponse;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {
    EmpresaResponse toEmpresaResponse(RazonesSociales entity);

    EmpresaResponse.TipoIdentidadDto toEmpresaResponseTipoIdentidad(TipoIdentidades tipo);

    @Mapping(source = "tipoIdentidadId", target = "tipoIdentidad")
    RazonesSociales toEmpresaEntity(EmpresaRequest dto);

    @Mapping(source = "tipoIdentidadId", target = "tipoIdentidad")
    void toEmpresaEntity(EmpresaRequest dto, @MappingTarget RazonesSociales entity);

    default TipoIdentidades toTipoIdentidad(Long id) {
        if (id == null) {
            return null;
        }
        TipoIdentidades ti = new TipoIdentidades();
        ti.setId(id);
        return ti;
    }
}
