package com.example.testcrudprodutos.controller;

import com.example.testcrudprodutos.model.Produto;
import com.example.testcrudprodutos.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProdutoService produtoService;

    @Test
    void listarTodos_DeveRetornarListaDeProdutos() throws Exception {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Teste");
        produto.setDescricao("Descrição do produto");
        produto.setResponsavel("João");
        produto.setCategoria("Eletrônicos");

        Page<Produto> produtos = new PageImpl<>(List.of(produto));
        when(produtoService.listarTodos(any(PageRequest.class))).thenReturn(produtos);

        mockMvc.perform(get("/api/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].nome").value("Teste"))
                .andExpect(jsonPath("$.content[0].descricao").value("Descrição do produto"))
                .andExpect(jsonPath("$.content[0].responsavel").value("João"))
                .andExpect(jsonPath("$.content[0].categoria").value("Eletrônicos"));
    }

    @Test
    void buscarPorId_DeveRetornarProduto() throws Exception {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Teste");
        produto.setDescricao("Descrição do produto");
        produto.setResponsavel("João");
        produto.setCategoria("Eletrônicos");

        when(produtoService.buscarPorId(1L)).thenReturn(produto);

        mockMvc.perform(get("/api/produtos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Teste"))
                .andExpect(jsonPath("$.descricao").value("Descrição do produto"))
                .andExpect(jsonPath("$.responsavel").value("João"))
                .andExpect(jsonPath("$.categoria").value("Eletrônicos"));
    }

    @Test
    void criar_DeveCriarNovoProduto() throws Exception {
        Produto produto = new Produto();
        produto.setNome("Teste");
        produto.setDescricao("Descrição do produto");
        produto.setResponsavel("João");
        produto.setCategoria("Eletrônicos");

        Produto produtoSalvo = new Produto();
        produtoSalvo.setId(1L);
        produtoSalvo.setNome("Teste");
        produtoSalvo.setDescricao("Descrição do produto");
        produtoSalvo.setResponsavel("João");
        produtoSalvo.setCategoria("Eletrônicos");

        when(produtoService.criar(any(Produto.class))).thenReturn(produtoSalvo);

        mockMvc.perform(post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Teste"))
                .andExpect(jsonPath("$.descricao").value("Descrição do produto"))
                .andExpect(jsonPath("$.responsavel").value("João"))
                .andExpect(jsonPath("$.categoria").value("Eletrônicos"));
    }
} 