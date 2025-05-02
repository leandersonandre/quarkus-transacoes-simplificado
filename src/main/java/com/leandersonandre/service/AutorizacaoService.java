package com.leandersonandre.service;

import com.leandersonandre.restclient.AutorizacaoClient;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class AutorizacaoService {
    @RestClient
    AutorizacaoClient autorizacaoClient;


    public boolean validarAutorizacao(){
        var response = autorizacaoClient.getAutorizacao();
        return response.isAutorizado();
    }

}
