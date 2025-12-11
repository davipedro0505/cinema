package br.com.cinema.repository;

import br.com.cinema.model.Ator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtorRepository extends JpaRepository<Ator, Long> {
    List<Ator> findByNomeContainingIgnoreCase(String nome);
}
