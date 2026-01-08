package com.conetdev.mydailydatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.conetdev.mydailydatabase.model.TipoMoneda;

@Repository
public interface TipoMonedaRepository extends JpaRepository<TipoMoneda, Long> {
}
