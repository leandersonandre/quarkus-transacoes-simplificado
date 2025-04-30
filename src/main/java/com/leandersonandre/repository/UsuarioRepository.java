package com.leandersonandre.repository;

import com.leandersonandre.entity.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
}
