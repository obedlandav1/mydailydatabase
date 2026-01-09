package com.conetdev.mydailydatabase.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.conetdev.mydailydatabase.model.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    
    @Query("SELECT c FROM Cuenta c WHERE c.razonSocial.id = :razonSocialId AND c.estado = 1 ORDER BY c.numeroCuenta ASC")
    List<Cuenta> findByRazonSocialIdAndEstadoActivo(@Param("razonSocialId") Long razonSocialId);

    @Query("SELECT c FROM Cuenta c WHERE c.estado = 1 ORDER BY c.razonSocial.nombreCorto, c.numeroCuenta ASC")
    List<Cuenta> findAllActivas();
}
