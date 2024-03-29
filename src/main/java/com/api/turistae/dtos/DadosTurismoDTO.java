package com.api.turistae.dtos;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosTurismoDTO {

    // Atributos
    private long id;
    private String nome;
    private long telefone;
    private int numeroLocal;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String cadastroNacionalPessoasJuridicas;
    private String descricao;

    // Relacionamentos
    private UsuarioDTO usuario;
    private CategoriaDTO categoria;

    private List<ImagemDTO> imagens;
    private List<CurtidaDTO> curtidas;
    private List<ReviewDTO> reviews;

    // Timestamps
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;

}
