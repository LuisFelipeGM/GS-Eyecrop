package com.japetech.eyecrop.repositories;

import com.japetech.eyecrop.models.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel, Long> {
    UserDetails findByLogin(String login);
}
