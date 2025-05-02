package com.leandersonandre.service;

import com.leandersonandre.controller.TransferenciaDTO;
import com.leandersonandre.entity.Carteira;
import com.leandersonandre.entity.TipoDeUsuario;
import com.leandersonandre.entity.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import org.jboss.resteasy.reactive.ClientWebApplicationException;

import java.math.BigDecimal;

@ApplicationScoped
public class TransferenciaService {

    @Inject
    UsuarioService usuarioService;
    @Inject
    CarteiraService carteiraService;
    @Inject
    AutorizacaoService autorizacaoService;
    @Inject
    NotificacaoService notificacaoService;

    @Transactional
    public void transferencia(TransferenciaDTO transferenciaDTO) {
        var pagador = usuarioService.obterPeloId(transferenciaDTO.payer());
        var recebedor = usuarioService.obterPeloId(transferenciaDTO.payee());
        validarTipoDeUsuarioDoPagador(pagador);

        validarSaldoDoPagador(pagador,transferenciaDTO.value());
        validarAutorizao();

        var carteiraPagador = carteiraService.obterCarteiraDoUsuarioPorId(pagador.getId());
        var carteiraRecebedor = carteiraService.obterCarteiraDoUsuarioPorId(recebedor.getId());
        carteiraPagador.setValor(carteiraPagador.getValor().subtract(transferenciaDTO.value()));
        carteiraRecebedor.setValor(carteiraRecebedor.getValor().add(transferenciaDTO.value()));
        atualizarSaldoDaCarteira(carteiraPagador);
        atualizarSaldoDaCarteira(carteiraRecebedor);

        enviarNotificacao();

    }

    private void enviarNotificacao() {
        try {
            notificacaoService.enviarNotificacao();
        }catch (ClientWebApplicationException e){
            throw new BadRequestException(e.getMessage());
        }
    }

    private void atualizarSaldoDaCarteira(Carteira carteira) {
        carteiraService.salvar(carteira);
    }

    private void validarAutorizao(){
        try {
            if (!autorizacaoService.validarAutorizacao()) {
                throw new IllegalArgumentException("Autorização negada.");
            }
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
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
