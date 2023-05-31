package com.japetech.eyecrop.repositories;

import com.japetech.eyecrop.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    List<UsuarioModel> findByemail(String email);

}
