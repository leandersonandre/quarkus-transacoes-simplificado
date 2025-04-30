package com.leandersonandre.service;

import com.leandersonandre.controller.TransferenciaDTO;
import com.leandersonandre.entity.TipoDeUsuario;
import com.leandersonandre.entity.Usuario;
import com.leandersonandre.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;

@ApplicationScoped
public class TransferenciaService {

    @Inject
    UsuarioService usuarioService;
    @Inject
    CarteiraService carteiraService;

    public void transferencia(TransferenciaDTO transferenciaDTO) {
        var pagador = usuarioService.obterPeloId(transferenciaDTO.payer());
        var recebedor = usuarioService.obterPeloId(transferenciaDTO.payee());
        validarTipoDeUsuarioDoPagador(pagador);
        validarSaldoDoPagador(pagador,transferenciaDTO.valor());
    }

    private void validarSaldoDoPagador(Usuario pagador, BigDecimal valor) {
        var carteira = carteiraService.obterCarteiraDoUsuarioPorId(pagador.getId());
        if(valor.compareTo(carteira.getValor()) > 0) {
            throw new IllegalArgumentException("Transação não autorizada, saldo insuficiente");
        }
    }

    private void validarTipoDeUsuarioDoPagador(Usuario usuario) {
        if(TipoDeUsuario.LOJISTA.equals(usuario.getTipoDeUsuario())) {
            throw new IllegalArgumentException("Transação não autorizada para esse tipo de usuario");
        }
    }

}
