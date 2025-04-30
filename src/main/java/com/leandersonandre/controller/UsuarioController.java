package com.leandersonandre.controller;

import com.leandersonandre.entity.Usuario;
import com.leandersonandre.repository.UsuarioRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.beans.Transient;
import java.util.List;

@Path("/usuario")
public class UsuarioController {

    @Inject
    UsuarioRepository usuarioRepository;

    @GET
    public List<Usuario> obterTodos(){
        return usuarioRepository.listAll();
    }

    @POST
    @Transactional
    public Response salvar(Usuario usuario){
        System.out.println(usuario);
        usuarioRepository.persist(usuario);
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }

}
