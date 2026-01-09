package com.conetdev.mydailydatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conetdev.mydailydatabase.model.RazonesSociales;

@Repository
public interface RazonSocialRepository extends JpaRepository<RazonesSociales, Long> {
    // Additional query methods can be defined here if needed
}