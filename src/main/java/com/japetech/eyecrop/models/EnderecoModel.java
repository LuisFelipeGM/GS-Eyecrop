package com.japetech.eyecrop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "T_ECP_ENDERECO")
public class EnderecoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 80)
    private String logradouro;

    @Column(nullable = false, length = 50)
    private Integer numero;

    @Column(nullable = false, length = 80)
    private String cidade;

    @Column(nullable = false, length = 80)
    private String estado;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "FK_ENDERECO_USUARIO"))
    @JsonIgnore
    private UsuarioModel usuario;



}
