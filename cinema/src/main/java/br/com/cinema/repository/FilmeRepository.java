package br.com.cinema.repository;

import br.com.cinema.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface FilmeRepository extends JpaRepository<Filme, Long> {
    List<Filme> findByTituloContainingIgnoreCase(String titulo);

}
