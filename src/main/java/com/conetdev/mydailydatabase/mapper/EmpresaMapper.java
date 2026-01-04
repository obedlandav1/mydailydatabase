package com.conetdev.mydailydatabase.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.conetdev.mydailydatabase.model.RazonSocial;
import com.conetdev.mydailydatabase.model.TipoIdentidad;
import com.conetdev.mydailydatabase.request.EmpresaRequest;
import com.conetdev.mydailydatabase.response.EmpresaResponse;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {
    EmpresaResponse toEmpresaResponse(RazonSocial entity);

    EmpresaResponse.TipoIdentidadDto toEmpresaResponseTipoIdentidad(TipoIdentidad tipo);

    @Mapping(source = "tipoIdentidadId", target = "tipoIdentidad")
    RazonSocial toEmpresaEntity(EmpresaRequest dto);

    @Mapping(source = "tipoIdentidadId", target = "tipoIdentidad")
    void toEmpresaEntity(EmpresaRequest dto, @MappingTarget RazonSocial entity);

    default TipoIdentidad toTipoIdentidad(Long id) {
        if (id == null) {
            return null;
        }
        TipoIdentidad ti = new TipoIdentidad();
        ti.setId(id);
        return ti;
    }
}
