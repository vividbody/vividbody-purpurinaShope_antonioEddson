package com.purpurina.repository;

import com.purpurina.entity.ItemPedido;
import com.purpurina.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    
    // Buscar itens por pedido
    List<ItemPedido> findByPedido(Pedido pedido);
}
