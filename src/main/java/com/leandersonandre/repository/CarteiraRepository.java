package com.leandersonandre.repository;

import com.leandersonandre.entity.Carteira;
import com.leandersonandre.entity.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CarteiraRepository implements PanacheRepository<Carteira> {
}
