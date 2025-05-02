package com.leandersonandre.restclient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.POST;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@RegisterRestClient(baseUri = "https://util.devi.tools/api/v1/notify")
public interface NotificacaoClient {

    @POST
    void enviarNotificacao();

}
