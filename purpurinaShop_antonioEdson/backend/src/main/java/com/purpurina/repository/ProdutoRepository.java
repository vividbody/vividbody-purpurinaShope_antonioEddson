package com.purpurina.repository;

import com.purpurina.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByMarcaContainingIgnoreCase(String marca);
    List<Produto> findByMetal(String metal);
}
