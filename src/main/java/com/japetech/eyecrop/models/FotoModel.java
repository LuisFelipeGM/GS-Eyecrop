package com.japetech.eyecrop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "T_ECP_FOTO")
public class FotoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 255)
    private String foto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "FK_FOTO_USUARIO"))
    @JsonIgnore
    private UsuarioModel usuario;

}
