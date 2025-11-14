package com.purpurina.controller;

import com.purpurina.entity.Cliente;
import com.purpurina.entity.Pedido;
import com.purpurina.service.ClienteService;
import com.purpurina.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private ClienteService clienteService;
    
    // Listar todos os pedidos
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pedidos", pedidoService.listarTodos());
        return "pedidos/lista";
    }
    
    // Ver detalhes de um pedido
    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);
        if (pedido.isPresent()) {
            model.addAttribute("pedido", pedido.get());
            return "pedidos/detalhes";
        }
        return "redirect:/pedidos";
    }
    
    // Formulário novo pedido
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("estatuses", new String[]{"Pendente", "Confirmado", "Enviado", "Entregue", "Cancelado"});
        return "pedidos/formulario";
    }
    
    // Salvar novo pedido
    @PostMapping
    public String salvar(@ModelAttribute Pedido pedido) {
        Pedido pedidoSalvo = pedidoService.salvar(pedido);
        return "redirect:/pedidos/" + pedidoSalvo.getId();
    }
    
    // Formulário edição de pedido
    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);
        if (pedido.isPresent()) {
            model.addAttribute("pedido", pedido.get());
            model.addAttribute("clientes", clienteService.listarTodos());
            model.addAttribute("estatuses", new String[]{"Pendente", "Confirmado", "Enviado", "Entregue", "Cancelado"});
            return "pedidos/formulario";
        }
        return "redirect:/pedidos";
    }
    
    // Atualizar pedido
    @PostMapping("/{id}/atualizar")
    public String atualizar(@PathVariable Long id, @ModelAttribute Pedido pedido) {
        pedidoService.atualizar(id, pedido);
        return "redirect:/pedidos/" + id;
    }
    
    // Deletar pedido
    @GetMapping("/{id}/deletar")
    public String deletar(@PathVariable Long id) {
        pedidoService.deletar(id);
        return "redirect:/pedidos";
    }
}
