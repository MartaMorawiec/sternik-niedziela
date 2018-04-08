package pl.sternik.mm.niedziela.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.sternik.mm.niedziela.entities.Moneta;
import pl.sternik.mm.niedziela.repositories.springdata.MonetaRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("spring-data")
public class KlaserServiceJPAImpl implements KlaserService {

    @Autowired
    private MonetaRepository bazaDanych;

    @Override
    public List<Moneta> findAll() {
        List<Moneta> l = new ArrayList<>();
        for (Moneta item : bazaDanych.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public List<Moneta> findAllToSell() {
        List<Moneta> l = new ArrayList<>();
        for (Moneta item : bazaDanych.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public Optional<Moneta> findById(Long id) {
        return Optional.ofNullable(bazaDanych.findByNumerKatalogowy(id));
    }

    @Override
    public Optional<Moneta> create(Moneta moneta) {
        return Optional.of(bazaDanych.save(moneta));
    }

    @Override
    public Optional<Moneta> edit(Moneta moneta) {
        return Optional.of(bazaDanych.save(moneta));
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        bazaDanych.delete(id);
        return Optional.of(Boolean.TRUE);
    }

    @Override
    public List<Moneta> findLatest3() {
        return Collections.emptyList();
    }

}