package com.leandersonandre.restclient;

public record AutorizacaoDTO(String status) {

    public boolean isAutorizado(){
        return status.equals("success");
    }

}
