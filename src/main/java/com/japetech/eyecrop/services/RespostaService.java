package com.japetech.eyecrop.services;

import com.japetech.eyecrop.dtos.RespostaDto;
import com.japetech.eyecrop.models.FotoModel;
import com.japetech.eyecrop.models.RespostaModel;
import com.japetech.eyecrop.repositories.FotoRepository;
import com.japetech.eyecrop.repositories.RespostaRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RespostaService extends GenericService<RespostaModel, Long>{

    private final RespostaRepository respostaRepository;

    private final FotoRepository fotoRepository;

    public RespostaService(JpaRepository<RespostaModel, Long> repository, RespostaRepository respostaRepository, FotoRepository fotoRepository) {
        super(repository);
        this.respostaRepository = respostaRepository;
        this.fotoRepository = fotoRepository;
    }

    public RespostaModel adicionarResposta(RespostaDto respostaDto){
        try {
            Optional<FotoModel> fotoOptional = fotoRepository.findById(respostaDto.getIdFoto());

            if (fotoOptional.isPresent()){
                FotoModel foto = fotoOptional.get();
                RespostaModel res = new RespostaModel();
                res.setResposta(respostaDto.getResposta());
                res.setFoto(foto);
                return repository.save(res);
            } else {
                throw new NoSuchElementException("Foto não encontrada");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getRootCause().getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public RespostaModel patchResposta(RespostaDto respostaDto, Long id){
        try {
            Optional<RespostaModel> respostaOptional = respostaRepository.findById(id);
            if (respostaOptional.isPresent()){
                RespostaModel res = respostaOptional.get();
                if(respostaDto.getResposta() != null){
                    res.setResposta(respostaDto.getResposta());
                }
                RespostaModel updateResposta = repository.save(res);
                return updateResposta;
            } else{
                throw new NoSuchElementException("Resposta não encontrada");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getRootCause().getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
