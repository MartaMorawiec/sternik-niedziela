package pl.sternik.mm.niedziela.repositories.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.sternik.mm.niedziela.entities.Moneta;
import pl.sternik.mm.niedziela.repositories.MonetyRepository;

@Repository
public interface MonetaRepository extends JpaRepository<Moneta, Long> {
    public Moneta findByNumerKatalogowy(Long id);
}