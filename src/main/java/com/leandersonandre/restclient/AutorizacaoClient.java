package com.leandersonandre.restclient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@RegisterRestClient(baseUri = "https://util.devi.tools/api/v2/authorize")
public interface AutorizacaoClient {

    @GET
    AutorizacaoDTO getAutorizacao();

}
