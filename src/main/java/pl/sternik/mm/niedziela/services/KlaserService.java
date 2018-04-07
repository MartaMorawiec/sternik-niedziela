package pl.sternik.mm.niedziela.services;

import pl.sternik.mm.niedziela.entities.Moneta;

import java.util.List;
import java.util.Optional;

public interface KlaserService {
    List<Moneta> findAll();

    List<Moneta> findAllToSell();

    Optional<Moneta> findById(Long id);

    Optional<Moneta> create(Moneta moneta);

    Optional<Moneta> edit(Moneta moneta);

    Optional<Boolean> deleteById(Long id);

    List<Moneta> findLatest3();
}