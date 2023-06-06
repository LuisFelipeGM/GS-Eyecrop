package com.japetech.eyecrop.services;

import com.japetech.eyecrop.dtos.UsuarioDto;
import com.japetech.eyecrop.models.UsuarioModel;
import com.japetech.eyecrop.repositories.UsuarioRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioService extends GenericService<UsuarioModel, Long>{

        private final UsuarioRepository usuarioRepository;

    public UsuarioService(JpaRepository<UsuarioModel, Long> repository, UsuarioRepository usuarioRepository) {
        super(repository);
        this.usuarioRepository = usuarioRepository;
    }

    public Page<UsuarioModel> getAll(Pageable paginacao){
        return repository.findAll(paginacao);
    }

    public Page<UsuarioModel> search(String searchTerm, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");

        return usuarioRepository.search(searchTerm.toLowerCase(), pageRequest);
    }


    public UsuarioModel adicionarUsuario(UsuarioDto usuarioDto){
        try {
            UsuarioModel model = new UsuarioModel();
            model.setNome(usuarioDto.getNome());
            model.setEmail(usuarioDto.getEmail());
            model.setSenha(usuarioDto.getSenha());

            return repository.save(model);
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getRootCause().getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public UsuarioModel putUsuario(UsuarioDto usuarioDto, Long id){
        try {
            Optional<UsuarioModel> usuarioOptional  = usuarioRepository.findById(id);
            if(usuarioOptional.isPresent()){
                UsuarioModel usu = usuarioOptional.get();
                usu.setNome(usuarioDto.getNome());
                usu.setEmail(usuarioDto.getEmail());
                usu.setSenha(usuarioDto.getSenha());

                UsuarioModel updateUsuario = repository.save(usu);
                return updateUsuario;
            }else{
                throw new NoSuchElementException("Usuário não encontrado");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getRootCause().getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    public UsuarioModel patchUsuario(UsuarioDto usuarioDto, Long id){
        try {
            Optional<UsuarioModel> usuarioOptional  = usuarioRepository.findById(id);
            if (usuarioOptional.isPresent()){
                UsuarioModel usu = usuarioOptional.get();
                if(usuarioDto.getNome() != null){
                    usu.setNome(usuarioDto.getNome());
                }
                if(usuarioDto.getEmail() != null){
                    usu.setEmail(usuarioDto.getEmail());
                }
                if(usuarioDto.getSenha() != null){
                    usu.setSenha(usuarioDto.getSenha());
                }
                UsuarioModel updatedUsuario = repository.save(usu);
                return updatedUsuario;
            }else{
                throw new NoSuchElementException("Usuário não encontrado");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getRootCause().getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }



}
