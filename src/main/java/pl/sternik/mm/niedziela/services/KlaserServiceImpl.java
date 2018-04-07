package pl.sternik.mm.niedziela.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.sternik.mm.niedziela.entities.Moneta;
import pl.sternik.mm.niedziela.repositories.MonetaAlreadyExistsException;
import pl.sternik.mm.niedziela.repositories.MonetyRepository;
import pl.sternik.mm.niedziela.repositories.NoSuchMonetaException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("tablica")
public class KlaserServiceImpl implements KlaserService {

    @Autowired
    @Qualifier("tablica")
    private MonetyRepository bazaDanych;

    @Override
    public List<Moneta> findAll() {
        return bazaDanych.findAll();
    }

    @Override
    public List<Moneta> findAllToSell() {
        return bazaDanych.findAll();
    }

    @Override
    public Optional<Moneta> findById(Long id) {
        try {
            return Optional.of(bazaDanych.readById(id));
        } catch (NoSuchMonetaException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Moneta> create(Moneta moneta) {
        try {
            return Optional.of(bazaDanych.create(moneta));
        } catch (MonetaAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Moneta> edit(Moneta moneta) {
        try {
            return Optional.of(bazaDanych.update(moneta));
        } catch (NoSuchMonetaException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            bazaDanych.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchMonetaException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Moneta> findLatest3() {
        //TODO:
        return Collections.emptyList();
    }

}
