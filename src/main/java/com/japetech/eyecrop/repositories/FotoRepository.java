package com.japetech.eyecrop.repositories;

import com.japetech.eyecrop.models.FotoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepository extends JpaRepository<FotoModel, Long> {
}
