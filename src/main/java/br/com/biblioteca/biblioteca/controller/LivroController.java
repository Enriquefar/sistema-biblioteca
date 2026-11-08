package br.com.biblioteca.biblioteca.controller;

import br.com.biblioteca.biblioteca.model.Livro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LivroController {

    // Aula 6: lista em memória (volátil). Será substituída por Repository na Aula 7.
    private final List<Livro> livros = new ArrayList<>();

    @GetMapping("/livros/novo")
    public String novoLivro(Model model) {
        model.addAttribute("livro", new Livro());
        return "livros/form";
    }

    // CREATE
    @PostMapping("/livros")
    public String cadastrar(@ModelAttribute Livro livro) {
        livros.add(livro);
        return "redirect:/livros";
    }

    // READ
    @GetMapping("/livros")
    public String listar(Model model) {
        model.addAttribute("livros", livros);
        return "livros/lista";
    }

    // DELETE (por índice)
    @GetMapping("/livros/excluir/{indice}")
    public String excluir(@PathVariable int indice) {
        if (indice >= 0 && indice < livros.size()) {
            livros.remove(indice);
        }
        return "redirect:/livros";
    }
}
