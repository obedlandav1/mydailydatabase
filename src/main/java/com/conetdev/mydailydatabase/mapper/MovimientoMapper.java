package com.conetdev.mydailydatabase.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.conetdev.mydailydatabase.model.Movimiento;
import com.conetdev.mydailydatabase.request.MovimientoRequest;
import com.conetdev.mydailydatabase.response.MovimientoResponse;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {

    MovimientoResponse toMovimientoResponse(Movimiento movimiento);

    Movimiento toMovimientoEntity(MovimientoRequest request);

    void toMovimientoEntity(MovimientoRequest request, @MappingTarget Movimiento movimiento);
}
