package com.leandersonandre.controller;

import com.leandersonandre.service.TransferenciaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/transferencia")
public class TransferenciaController {

    @Inject
    TransferenciaService transferenciaService;

    @POST
    public void transferencia(TransferenciaDTO transferenciaDTO) {
        transferenciaService.transferencia(transferenciaDTO);
    }

}
