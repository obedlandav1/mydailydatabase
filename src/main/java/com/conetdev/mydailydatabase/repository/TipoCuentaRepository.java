package com.conetdev.mydailydatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.conetdev.mydailydatabase.model.TipoCuenta;

@Repository
public interface TipoCuentaRepository extends JpaRepository<TipoCuenta, Long> {
}
