package com.leandersonandre.controller;

import com.leandersonandre.entity.Carteira;
import com.leandersonandre.repository.CarteiraRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;

@Path("/carteira")
public class CarteiraController {

    @Inject
    CarteiraRepository carteiraRepository;

    @GET
    public List<Carteira> obterTodos(){
        return carteiraRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Carteira obterDoUsuario(@PathParam("id") Long id){
        return carteiraRepository.find("usuario.id",id).firstResult();
    }

}
