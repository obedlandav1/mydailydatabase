package com.conetdev.mydailydatabase.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipoidentidad_id")
    private TipoIdentidad tipoIdentidad;

    @Column(name = "nombreusuario")
    private String nombreUsuario;

    @Column(name = "apellidousuario")
    private String apellidoUsuario;

    @Column(name = "identidadusuario")
    private String identidadUsuario;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "passwordusuario")
    private String passwordUsuario;

    @Column(name = "celularusuario")
    private String celularUsuario;

    private Integer estado;

    // --- RELACIÓN MANY TO MANY CON TIPO ROL ---
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rolesxusuarios", joinColumns = @JoinColumn(name = "usuarios_id"), inverseJoinColumns = @JoinColumn(name = "tiporol_id"))
    private List<TipoRol> roles;

    // --- RELACIÓN MANY TO MANY CON RAZON SOCIAL ---
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "razonxusuarios", joinColumns = @JoinColumn(name = "usuarios_id"), inverseJoinColumns = @JoinColumn(name = "razonsocial_id"))
    private List<RazonSocial> razonesSociales;

    public boolean isActivo() {
        // Consider `estado` == 1 as active, otherwise inactive
        return this.estado != null && this.estado == 1;
    }

}