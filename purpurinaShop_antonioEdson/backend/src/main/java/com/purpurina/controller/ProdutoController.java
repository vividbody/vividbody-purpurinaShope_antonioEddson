package com.purpurina.controller;

import com.purpurina.entity.Produto;
import com.purpurina.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", produtoService.listarTodos());
        return "produtos/lista";
    }

    @GetMapping("/{id}")
    public String visualizar(@PathVariable Long id, Model model) {
        Optional<Produto> produto = produtoService.buscarPorId(id);
        if (produto.isPresent()) {
            model.addAttribute("produto", produto.get());
            return "produtos/detalhes";
        }
        return "redirect:/produtos";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("produto", new Produto());
        return "produtos/formulario";
    }

    @PostMapping
    public String salvar(@ModelAttribute Produto produto) {
        produtoService.salvar(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Optional<Produto> produto = produtoService.buscarPorId(id);
        if (produto.isPresent()) {
            model.addAttribute("produto", produto.get());
            return "produtos/formulario";
        }
        return "redirect:/produtos";
    }

    @PostMapping("/{id}/atualizar")
    public String atualizar(@PathVariable Long id, @ModelAttribute Produto produto) {
        produtoService.atualizar(id, produto);
        return "redirect:/produtos/" + id;
    }

    @GetMapping("/{id}/deletar")
    public String deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return "redirect:/produtos";
    }
}
