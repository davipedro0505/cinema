package br.com.cinema.controller;

import br.com.cinema.model.Ator;
import br.com.cinema.repository.AtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/atores")
public class AtorController {

    @Autowired
    private AtorRepository atorRepository;

    @GetMapping("/novo") // abre o formulário para cadastrar um ator
    public String formulario(Model model) {
        model.addAttribute("ator", new Ator());
        return "ator-form";
    }

    @PostMapping("/salvar") // recebe o formulário e salva no banco
    public String salvar(Ator ator) {
        atorRepository.save(ator);
        return "redirect:/atores/listar";
    }

    // listar atores com filtro opcional por nome
    @GetMapping("/listar") // lista todos os atores e permite filtrar por nome
    public String listar(@RequestParam(required = false) String nome, Model model) {

        List<Ator> atores;

        if (nome != null && !nome.isEmpty()) {
            atores = atorRepository.findByNomeContainingIgnoreCase(nome);
        } else {
            atores = atorRepository.findAll();
        }

        model.addAttribute("atores", atores);
        model.addAttribute("nome", nome);

        return "ator-lista";
    }

    // EDITAR ATOR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Ator ator = atorRepository.findById(id).orElse(null);
        model.addAttribute("ator", ator);
        return "ator-form";
    }

    // EXCLUIR ATOR
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        atorRepository.deleteById(id);
        return "redirect:/atores/listar";
    }
}
