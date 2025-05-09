package com.example.testcrudprodutos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "produtos")
@Where(clause = "deleted = false")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @Column(nullable = false)
    private String nome;
    
    @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres")
    @Column(length = 1000)
    private String descricao;
    
    @NotBlank(message = "O responsável é obrigatório")
    @Size(min = 3, max = 100, message = "O nome do responsável deve ter entre 3 e 100 caracteres")
    @Column(nullable = false)
    private String responsavel;
    
    @NotBlank(message = "A categoria é obrigatória")
    @Size(min = 2, max = 50, message = "A categoria deve ter entre 2 e 50 caracteres")
    @Column(name = "tipo")
    private String categoria;
    
    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;
    
    @UpdateTimestamp
    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;
    
    @Column(name = "deleted")
    private boolean deleted = false;
} 