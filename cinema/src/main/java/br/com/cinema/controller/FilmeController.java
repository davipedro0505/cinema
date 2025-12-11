package br.com.cinema.controller;

import br.com.cinema.model.Filme;
import br.com.cinema.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


import java.util.List;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository filmeRepository;

    // listar filmes com filtro opcional por título
    @GetMapping("/listar")
    public String listar(@RequestParam(required = false) String titulo, Model model) {

        List<Filme> filmes;

        if (titulo != null && !titulo.isEmpty()) {
            filmes = filmeRepository.findByTituloContainingIgnoreCase(titulo);
        } else {
            filmes = filmeRepository.findAll();
        }

        model.addAttribute("filmes", filmes);
        model.addAttribute("titulo", titulo);

        return "filme-lista";
    }
}
