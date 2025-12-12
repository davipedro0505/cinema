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

    // FORMULÁRIO PARA NOVO CLIENTE
    @GetMapping("/novo")
    public String novoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-form";
    }

    // SALVAR CLIENTE (CRIAR OU EDITAR)
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/clientes/listar";
    }

    // EDITAR CLIENTE
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        model.addAttribute("cliente", cliente);
        return "cliente-form";
    }

    // EXCLUIR CLIENTE
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes/listar";
    }
}
