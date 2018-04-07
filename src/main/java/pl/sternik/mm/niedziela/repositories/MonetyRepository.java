package pl.sternik.mm.niedziela.repositories;

import pl.sternik.mm.niedziela.entities.Moneta;
import java.util.List;

public interface MonetyRepository {
    Moneta create(Moneta moneta) throws MonetaAlreadyExistsException;
    Moneta readById(Long id) throws NoSuchMonetaException;
    Moneta update(Moneta moneta) throws NoSuchMonetaException;
    void deleteById(Long id) throws NoSuchMonetaException;
    List<Moneta> findAll();
}