package com.japetech.eyecrop.models;

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
    @OrderColumn(name = "1")
    private Long id;

    @Column(nullable = false, length = 80)
    @OrderColumn(name = "2")
    private String logradouro;

    @Column(nullable = false)
    @Size(min = 1, max = 50)
    @OrderColumn(name = "3")
    private Integer numero;

    @Column(nullable = false, length = 80)
    @OrderColumn(name = "4")
    private String cidade;

    @Column(nullable = false, length = 80)
    @OrderColumn(name = "5")
    private String estado;

    @OrderColumn(name = "6")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "FK_USUARIO_ENDERECO"))
    private UsuarioModel usuario;



}
