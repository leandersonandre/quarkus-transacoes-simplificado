package com.leandersonandre.service;

import com.leandersonandre.entity.Carteira;
import com.leandersonandre.exception.CarteiraNotFoundException;
import com.leandersonandre.repository.CarteiraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CarteiraService {
    @Inject
    CarteiraRepository carteiraRepository;

    public Carteira obterCarteiraDoUsuarioPorId(Long userId) {
        return carteiraRepository.find("usuario.id",userId).firstResultOptional()
                .orElseThrow(() ->  new CarteiraNotFoundException("Carteira não encontradado para o usuário. Id: " + userId));

    }

    public void salvar(Carteira carteira) {
        carteiraRepository.persist(carteira);
    }
}
