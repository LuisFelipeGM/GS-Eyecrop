package com.japetech.eyecrop.repositories;

import com.japetech.eyecrop.models.RespostaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaRepository  extends JpaRepository<RespostaModel, Long> {
}
