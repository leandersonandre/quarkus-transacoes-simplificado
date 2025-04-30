package com.leandersonandre.controller;

import java.math.BigDecimal;

public record TransferenciaDTO(BigDecimal valor,long payer, long payee) {
}
