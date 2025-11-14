package com.purpurina.service;

import com.purpurina.entity.Cliente;
import com.purpurina.entity.Pedido;
import com.purpurina.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ClienteService clienteService;
    
    // Listar todos os pedidos
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }
    
    // Buscar pedido por ID
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }
    
    // Buscar pedidos por cliente
    public List<Pedido> buscarPorCliente(Long clienteId) {
        Optional<Cliente> cliente = clienteService.buscarPorId(clienteId);
        if (cliente.isPresent()) {
            return pedidoRepository.findByCliente(cliente.get());
        }
        return List.of();
    }
    
    // Buscar pedidos por status
    public List<Pedido> buscarPorStatus(String status) {
        return pedidoRepository.findByStatus(status);
    }
    
    // Buscar pedidos por cliente e status
    public List<Pedido> buscarPorClienteEStatus(Long clienteId, String status) {
        Optional<Cliente> cliente = clienteService.buscarPorId(clienteId);
        if (cliente.isPresent()) {
            return pedidoRepository.findByClienteAndStatus(cliente.get(), status);
        }
        return List.of();
    }
    
    // Salvar novo pedido
    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
    
    // Atualizar pedido existente
    public Pedido atualizar(Long id, Pedido pedidoAtualizado) {
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);
        if (pedidoExistente.isPresent()) {
            Pedido pedido = pedidoExistente.get();
            if (pedidoAtualizado.getCliente() != null) {
                pedido.setCliente(pedidoAtualizado.getCliente());
            }
            if (pedidoAtualizado.getStatus() != null) {
                pedido.setStatus(pedidoAtualizado.getStatus());
            }
            if (pedidoAtualizado.getObservacoes() != null) {
                pedido.setObservacoes(pedidoAtualizado.getObservacoes());
            }
            return pedidoRepository.save(pedido);
        }
        throw new RuntimeException("Pedido não encontrado com ID: " + id);
    }
    
    // Deletar pedido
    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }
}
