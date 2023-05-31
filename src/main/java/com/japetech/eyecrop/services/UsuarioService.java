package com.japetech.eyecrop.services;

import com.japetech.eyecrop.models.UsuarioModel;
import com.japetech.eyecrop.repositories.UsuarioRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService extends GenericService<UsuarioModel, Long>{

        private UsuarioRepository usuarioRepository;

    public UsuarioService(JpaRepository<UsuarioModel, Long> repository, UsuarioRepository usuarioRepository) {
        super(repository);
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioModel> findByemail(String email){
        return ((UsuarioRepository) repository).findByemail(email);
    }

}
