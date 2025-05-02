package com.leandersonandre.service;

import com.leandersonandre.restclient.NotificacaoClient;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class NotificacaoService {

    @RestClient
    private NotificacaoClient notificacaoClient;

    public void enviarNotificacao() {
        notificacaoClient.enviarNotificacao();
    }

}
