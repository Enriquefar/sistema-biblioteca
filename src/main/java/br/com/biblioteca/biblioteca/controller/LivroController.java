package br.com.biblioteca.biblioteca.controller;

import br.com.biblioteca.biblioteca.model.Livro;
import br.com.biblioteca.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LivroController {

    // Aula 7: a List em memória foi substituída pelo Repository (injeção por construtor).
    private final LivroRepository repository;

    public LivroController(LivroRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/livros/novo")
    public String novoLivro(Model model) {
        model.addAttribute("livro", new Livro());
        return "livros/form";
    }

    @PostMapping("/livros")
    public String cadastrarLivro(@ModelAttribute Livro livro) {
        repository.save(livro);
        return "redirect:/livros";
    }

    @GetMapping("/livros")
    public String listarLivros(Model model) {
        List<Livro> livros = repository.findAll();
        model.addAttribute("livros", livros);
        return "livros/lista";
    }

    // Excluir/Editar: CRUD completo na Aula 8 (deleteById / findById com ID único).
}
