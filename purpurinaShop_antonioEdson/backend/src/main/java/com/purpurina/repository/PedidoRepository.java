package com.purpurina.repository;

import com.purpurina.entity.Cliente;
import com.purpurina.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    // Buscar pedidos por cliente
    List<Pedido> findByCliente(Cliente cliente);
    
    // Buscar pedidos por status
    List<Pedido> findByStatus(String status);
    
    // Buscar pedidos por cliente e status
    List<Pedido> findByClienteAndStatus(Cliente cliente, String status);
}
