package com.leandersonandre.service;

import com.leandersonandre.entity.Usuario;
import com.leandersonandre.exception.UsuarioNotFoundException;
import com.leandersonandre.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    public Usuario obterPeloId(Long id) {
        return usuarioRepository.findByIdOptional(id)
                .orElseThrow(() ->  new UsuarioNotFoundException("Usuário não encontrador. Id: " + id));
    }

}
