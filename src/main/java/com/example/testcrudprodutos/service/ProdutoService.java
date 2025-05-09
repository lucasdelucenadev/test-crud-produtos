package com.example.testcrudprodutos.service;

import com.example.testcrudprodutos.exception.ResourceNotFoundException;
import com.example.testcrudprodutos.model.Produto;
import com.example.testcrudprodutos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Cacheable(value = "produtos")
    public Page<Produto> listarTodos(Pageable pageable) {
        return produtoRepository.findByDeletedFalse(pageable);
    }

    @Cacheable(value = "produto", key = "#id")
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto", "id", id));
    }

    @Transactional
    @CacheEvict(value = {"produtos", "produto"}, allEntries = true)
    public Produto criar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório");
        }
        if (produto.getResponsavel() == null || produto.getResponsavel().trim().isEmpty()) {
            throw new IllegalArgumentException("O responsável é obrigatório");
        }
        if (produto.getCategoria() == null || produto.getCategoria().trim().isEmpty()) {
            throw new IllegalArgumentException("A categoria é obrigatória");
        }
        return produtoRepository.save(produto);
    }

    @Transactional
    @CacheEvict(value = {"produtos", "produto"}, allEntries = true)
    public Produto atualizar(Long id, Produto produto) {
        Produto produtoExistente = buscarPorId(id);
        
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório");
        }
        if (produto.getResponsavel() == null || produto.getResponsavel().trim().isEmpty()) {
            throw new IllegalArgumentException("O responsável é obrigatório");
        }
        if (produto.getCategoria() == null || produto.getCategoria().trim().isEmpty()) {
            throw new IllegalArgumentException("A categoria é obrigatória");
        }

        produto.setId(id);
        produto.setDataCriacao(produtoExistente.getDataCriacao());
        return produtoRepository.save(produto);
    }

    @Transactional
    @CacheEvict(value = {"produtos", "produto"}, allEntries = true)
    public void deletar(Long id) {
        Produto produto = buscarPorId(id);
        
        // Verifica se é o último produto ativo
        long countAtivos = produtoRepository.findByDeletedFalse(Pageable.unpaged()).getTotalElements();
        if (countAtivos <= 1) {
            throw new IllegalStateException("Não é possível excluir o último produto do sistema");
        }
        
        produto.setDeleted(true);
        produtoRepository.save(produto);
    }
} 