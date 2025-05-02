package com.leandersonandre.controller;

import java.math.BigDecimal;

public record TransferenciaDTO(BigDecimal value,long payer, long payee) {
}
