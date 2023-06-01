package com.japetech.eyecrop.services;

import com.japetech.eyecrop.dtos.EnderecoDto;
import com.japetech.eyecrop.models.EnderecoModel;
import com.japetech.eyecrop.models.UsuarioModel;
import com.japetech.eyecrop.repositories.EnderecoRepository;
import com.japetech.eyecrop.repositories.UsuarioRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EnderecoService extends GenericService<EnderecoModel, Long>{

    private final EnderecoRepository enderecoRepository;

    private final UsuarioRepository usuarioRepository;

    public EnderecoService(JpaRepository<EnderecoModel, Long> repository, EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository) {
        super(repository);
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<EnderecoModel> findByestado(String estado){
        return ((EnderecoRepository) repository).findByestadoContainingIgnoreCase(estado);
    }

    public List<EnderecoModel> findBycidade(String cidade){
        return ((EnderecoRepository) repository).findBycidadeContainingIgnoreCase(cidade);
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
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Erro ao salvar o endereço", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o endereço", e);
        }



    }

}
