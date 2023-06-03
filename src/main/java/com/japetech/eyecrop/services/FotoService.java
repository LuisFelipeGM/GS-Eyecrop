package com.japetech.eyecrop.services;

import com.japetech.eyecrop.dtos.FotoDto;
import com.japetech.eyecrop.models.FotoModel;
import com.japetech.eyecrop.models.UsuarioModel;
import com.japetech.eyecrop.repositories.FotoRepository;
import com.japetech.eyecrop.repositories.UsuarioRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FotoService extends GenericService<FotoModel, Long> {

    private final FotoRepository fotoRepository;

    private final UsuarioRepository usuarioRepository;

    public FotoService(JpaRepository<FotoModel, Long> repository, FotoRepository fotoRepository, UsuarioRepository usuarioRepository) {
        super(repository);
        this.fotoRepository = fotoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public FotoModel adicionarFoto(FotoDto fotoDto){
        try {
            Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(fotoDto.getIdUsuario());

            if(usuarioOptional.isPresent()){
                UsuarioModel usu = usuarioOptional.get();
                FotoModel foto = new FotoModel();
                foto.setFoto(fotoDto.getFoto());
                foto.setUsuario(usu);
                return repository.save(foto);
            } else {
                throw new NoSuchElementException("Usuário não encontrado");
            }
        }  catch (DataAccessException e) {
            throw new RuntimeException(e.getRootCause().getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public FotoModel putFoto(FotoDto fotoDto, Long id){
        try {
            Optional<FotoModel> fotoOptional = fotoRepository.findById(id);
            if(fotoOptional.isPresent()){
                FotoModel foto = fotoOptional.get();
                foto.setFoto(fotoDto.getFoto());

                FotoModel updateFoto = repository.save(foto);
                return updateFoto;
            } else {
                throw new NoSuchElementException("Foto não encontrada");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getRootCause().getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public FotoModel patchFoto(FotoDto fotoDto, Long id){
        try {
            Optional<FotoModel> fotoOptional = fotoRepository.findById(id);
            if(fotoOptional.isPresent()){
                FotoModel foto = fotoOptional.get();
                if(fotoDto.getFoto() != null){
                    foto.setFoto(fotoDto.getFoto());
                }
                FotoModel updateFoto = repository.save(foto);
                return updateFoto;
            }else{
                throw new NoSuchElementException("Foto não encontrado");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getRootCause().getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
