package com.example.Ej14Testing.infrastructure.repository;

import com.example.Ej14Testing.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonaRepository extends JpaRepository <Persona, Integer> {
    @Query("select p from Persona p where p.usuario = ?1")
    Persona findByUsuario(String usuario);
}
