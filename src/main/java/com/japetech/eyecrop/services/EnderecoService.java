package com.japetech.eyecrop.services;

import com.japetech.eyecrop.dtos.EnderecoDto;
import com.japetech.eyecrop.models.EnderecoModel;
import com.japetech.eyecrop.models.UsuarioModel;
import com.japetech.eyecrop.repositories.EnderecoRepository;
import com.japetech.eyecrop.repositories.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EnderecoService extends GenericService<EnderecoModel, Long>{

    private final EnderecoRepository enderecoRepository;

    private final UsuarioRepository usuarioRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public EnderecoService(JpaRepository<EnderecoModel, Long> repository, EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository) {
        super(repository);
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Page<EnderecoModel> getAll(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    public List<EnderecoModel> findByestado(String estado){
        Query query = entityManager.createQuery("SELECT e FROM EnderecoModel e WHERE LOWER(e.estado) = :estado");
        query.setParameter("estado", estado.toLowerCase());

        return query.getResultList();
    }

    public List<EnderecoModel> findBycidade(String cidade){
        Query query = entityManager.createQuery("SELECT e FROM EnderecoModel e WHERE LOWER(e.cidade) = :cidade");
        query.setParameter("cidade", cidade.toLowerCase());

        return query.getResultList();
    }

    public EnderecoModel adicionarEndereco(EnderecoDto enderecoDto){
        try {
            Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(enderecoDto.getIdUsuario());

            if(usuarioOptional.isPresent()){
                UsuarioModel usu = usuarioOptional.get();
                EnderecoModel end = new EnderecoModel();
                end.setLogradouro(enderecoDto.getLogradouro());
                end.setNumero(enderecoDto.getNumero());
                end.setCidade(enderecoDto.getCidade());
                end.setEstado(enderecoDto.getEstado());
                end.setUsuario(usu);
                return repository.save(end);
            } else {
                throw new NoSuchElementException("Usuário não encontrado");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getRootCause().getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public EnderecoModel putEndereco(EnderecoDto enderecoDto, Long id){
        try {
            Optional<EnderecoModel> enderecoOptional = enderecoRepository.findById(id);
            if (enderecoOptional.isPresent()){
                EnderecoModel end = enderecoOptional.get();
                end.setLogradouro(enderecoDto.getLogradouro());
                end.setNumero(enderecoDto.getNumero());
                end.setCidade(enderecoDto.getCidade());
                end.setEstado(enderecoDto.getEstado());

                EnderecoModel updateEndereco = repository.save(end);
                return updateEndereco;
            }else{
                throw new NoSuchElementException("Endereço não encontrado");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getRootCause().getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public EnderecoModel patchEndereco(EnderecoDto enderecoDto, Long id){
        try {
            Optional<EnderecoModel> enderecoOptional = enderecoRepository.findById(id);
            if (enderecoOptional.isPresent()){
                EnderecoModel end = enderecoOptional.get();
                if (enderecoDto.getLogradouro() != null){
                    end.setLogradouro(enderecoDto.getLogradouro());
                }
                if (enderecoDto.getNumero() != null){
                    end.setNumero(enderecoDto.getNumero());
                }
                if (enderecoDto.getCidade() != null){
                    end.setCidade(enderecoDto.getCidade());
                }
                if (enderecoDto.getEstado() != null){
                    end.setEstado(enderecoDto.getEstado());
                }
                EnderecoModel updateEndereco = repository.save(end);
                return updateEndereco;
            }else{
                throw new NoSuchElementException("Endereço não encontrado");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getRootCause().getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
