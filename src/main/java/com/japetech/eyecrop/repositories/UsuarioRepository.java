package com.japetech.eyecrop.repositories;

import com.japetech.eyecrop.models.UsuarioModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    @Query("FROM UsuarioModel c " + "WHERE LOWER(c.nome) like %:searchTerm% " + "OR LOWER(c.email) like %:searchTerm%")
    Page<UsuarioModel> search(@Param("searchTerm") String searchTerm, Pageable pageable);



}
