package com.japetech.eyecrop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "T_ECP_RESPOSTA")
public class RespostaModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 255)
    private String resposta;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_foto", foreignKey = @ForeignKey(name = "FK_RESPOSTA_FOTO"))
    @JsonIgnore
    private FotoModel foto;

}
