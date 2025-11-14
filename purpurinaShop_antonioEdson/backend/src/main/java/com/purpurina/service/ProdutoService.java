package com.purpurina.service;

import com.purpurina.entity.Produto;
import com.purpurina.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public List<Produto> buscarPorMarca(String marca) {
        return produtoRepository.findByMarcaContainingIgnoreCase(marca);
    }

    public List<Produto> buscarPorMetal(String metal) {
        return produtoRepository.findByMetal(metal);
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setMarca(produtoAtualizado.getMarca());
            produto.setMetal(produtoAtualizado.getMetal());
            produto.setGema1(produtoAtualizado.getGema1());
            produto.setGema2(produtoAtualizado.getGema2());
            produto.setGema3(produtoAtualizado.getGema3());
            produto.setGema4(produtoAtualizado.getGema4());
            produto.setGema5(produtoAtualizado.getGema5());
            produto.setPeso(produtoAtualizado.getPeso());
            produto.setQuilates(produtoAtualizado.getQuilates());
            produto.setDimensoes(produtoAtualizado.getDimensoes());
            produto.setPrecoCusto(produtoAtualizado.getPrecoCusto());
            produto.setPrecoVenda(produtoAtualizado.getPrecoVenda());
            return produtoRepository.save(produto);
        }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }
}
