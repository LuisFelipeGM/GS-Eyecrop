package com.japetech.eyecrop.repositories;

import com.japetech.eyecrop.models.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {

    List<EnderecoModel> findByestado(String estado);

    List<EnderecoModel> findBycidade(String cidade);

}
