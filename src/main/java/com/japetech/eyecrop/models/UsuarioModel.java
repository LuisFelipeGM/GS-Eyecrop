package com.japetech.eyecrop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "T_ECP_USUARIO")
public class UsuarioModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderColumn(name = "1")
    private Long id;

    @Column(nullable = false, length = 80)
    @OrderColumn(name = "2")
    private String nome;

    @Email
    @Column(nullable = false, length = 100)
    @OrderColumn(name = "3")
    private String email;

    @Column(nullable = false, length = 30)
    @OrderColumn(name = "4")
    private String senha;

    @OneToOne(mappedBy = "usuario")
    private EnderecoModel endereco;

}
