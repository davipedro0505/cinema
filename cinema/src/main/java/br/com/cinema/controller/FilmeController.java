package br.com.cinema.controller;

import br.com.cinema.model.Filme;
import br.com.cinema.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository filmeRepository;

    // LISTAR FILMES (COM FILTRO POR TÍTULO)
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

    // FORMULÁRIO PARA NOVO FILME
    @GetMapping("/novo")
    public String novoFilme(Model model) {
        model.addAttribute("filme", new Filme());
        return "filme-form";
    }

    // SALVAR FILME (CRIAR OU EDITAR)
    
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Filme filme) {
        filmeRepository.save(filme);
        return "redirect:/filmes/listar";
    }

    // EDITAR FILME
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Filme filme = filmeRepository.findById(id).orElse(null);
        model.addAttribute("filme", filme);
        return "filme-form";
    }

    // EXCLUIR FILME
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        filmeRepository.deleteById(id);
        return "redirect:/filmes/listar";
    }
}
