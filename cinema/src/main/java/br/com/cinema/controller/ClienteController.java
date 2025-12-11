package br.com.cinema.controller;

import br.com.cinema.model.Cliente;
import br.com.cinema.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // listar clientes com filtro opcional por id
    @GetMapping("/listar")
    public String listar(@RequestParam(required = false) Long id, Model model) {

        if (id != null) {
            Optional<Cliente> cliente = clienteRepository.findById(id);
            model.addAttribute("clientes",
                    cliente.isPresent() ? List.of(cliente.get()) : List.of());
            model.addAttribute("id", id);
        } else {
            model.addAttribute("clientes", clienteRepository.findAll());
        }

        return "cliente-lista";
    }
}
