package com.leandersonandre.controller;

import com.leandersonandre.service.TransferenciaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

@Path("/transfer")
public class TransferenciaController {

    @Inject
    TransferenciaService transferenciaService;

    @POST
    public void transferencia(TransferenciaDTO transferenciaDTO) {
        transferenciaService.transferencia(transferenciaDTO);
    }

    @ServerExceptionMapper
    public RestResponse<String> mapException(IllegalArgumentException x) {
        return RestResponse.status(Response.Status.BAD_REQUEST, x.getMessage());
    }
    @ServerExceptionMapper
    public RestResponse<String> mapException(BadRequestException x) {
        return RestResponse.status(Response.Status.BAD_REQUEST, x.getMessage());
    }

}
