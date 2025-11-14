package com.purpurina.service;

import com.purpurina.entity.ItemPedido;
import com.purpurina.entity.Pedido;
import com.purpurina.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    // Listar todos os itens
    public List<ItemPedido> listarTodos() {
        return itemPedidoRepository.findAll();
    }
    
    // Buscar item por ID
    public Optional<ItemPedido> buscarPorId(Long id) {
        return itemPedidoRepository.findById(id);
    }
    
    // Buscar itens por pedido
    public List<ItemPedido> buscarPorPedido(Pedido pedido) {
        return itemPedidoRepository.findByPedido(pedido);
    }
    
    // Salvar novo item
    public ItemPedido salvar(ItemPedido item) {
        return itemPedidoRepository.save(item);
    }
    
    // Atualizar item existente
    public ItemPedido atualizar(Long id, ItemPedido itemAtualizado) {
        Optional<ItemPedido> itemExistente = itemPedidoRepository.findById(id);
        if (itemExistente.isPresent()) {
            ItemPedido item = itemExistente.get();
            if (itemAtualizado.getQuantidade() != null) {
                item.setQuantidade(itemAtualizado.getQuantidade());
            }
            if (itemAtualizado.getPrecoUnitario() != null) {
                item.setPrecoUnitario(itemAtualizado.getPrecoUnitario());
            }
            return itemPedidoRepository.save(item);
        }
        throw new RuntimeException("Item não encontrado com ID: " + id);
    }
    
    // Deletar item
    public void deletar(Long id) {
        itemPedidoRepository.deleteById(id);
    }
}
