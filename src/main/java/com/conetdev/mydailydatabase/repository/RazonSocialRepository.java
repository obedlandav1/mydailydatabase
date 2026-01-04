package com.conetdev.mydailydatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conetdev.mydailydatabase.model.RazonSocial;

@Repository
public interface RazonSocialRepository extends JpaRepository<RazonSocial, Long> {
    // Additional query methods can be defined here if needed
}