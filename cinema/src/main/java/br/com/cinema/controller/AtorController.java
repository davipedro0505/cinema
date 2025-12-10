package br.com.cinema.controller;

@Controller
@RequestMapping("/atores")
public class AtorController {

    @Autowired
    private AtorRepository atorRepository;

    @GetMapping("/novo")
    public String formulario(Model model) {
        model.addAttribute("ator", new Ator());
        return "ator-form";
    }

    @PostMapping("/salvar")
    public String salvar(Ator ator) {
        atorRepository.save(ator);
        return "redirect:/atores/novo";
    }
}
