package com.conetdev.mydailydatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.conetdev.mydailydatabase.model.Banco;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {
}
