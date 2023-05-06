package com.api.turistae.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.turistae.dtos.CategoriaDTO;
import com.api.turistae.dtos.DadosCategoriaDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.services.CategoriaService;
import com.api.turistae.utils.DataUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    // Atributos
    private CategoriaService categoriaService;
    private final Logger logger;
    private static final String MASCARA_DATA = "yyyy-MM-dd-HH-mm-ss";

    // Construtor
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
        this.logger = LoggerFactory.getLogger(CategoriaController.class);
        logger.info("Categoria Controller iniciado.");
    }

    // HttpGet
    @GetMapping
    public List<DadosCategoriaDTO> getCategorias() {
        logger.info("Get todas categorias.");
        return categoriaService.getAll();
    }

    @GetMapping("{id}")
    public DadosCategoriaDTO getCategoriaPorId(@PathVariable Long id) {
        logger.info("Get categoria id: {}", id);
        return categoriaService.getById(id);
    }

    // HttpPost
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long postCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {

        categoriaDTO.setDataCriacao(DataUtils.getDataAtualComMascara(MASCARA_DATA));
        categoriaDTO.setDataEdicao(DataUtils.getDataAtualComMascara(MASCARA_DATA));

        logger.info("Post categoria: {}", categoriaDTO);

        // Retorno do cadastro
        Long id = 0l;
        try {
            id = categoriaService.post(categoriaDTO);
        } catch(DataIntegrityViolationException e) {
            throw new RegraNegocioException("Categoria indisponível.");
        }
        
        return id;
    }

    // HttpPut
    @PutMapping("{id}")
    public void putCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaDTO categoriaDTO) {

        categoriaDTO.setDataEdicao(DataUtils.getDataAtualComMascara(MASCARA_DATA));

        logger.info("Put categoria id {}: {}", id, categoriaDTO);

        try {
            categoriaService.put(id, categoriaDTO);
        } catch(DataIntegrityViolationException e) {
            throw new RegraNegocioException("Categoria indisponível.");
        }

    }

    // HttpDelete
    @DeleteMapping("{id}")
    public void deleteCategoria(@PathVariable Long id) {

        logger.info("Delete categoria id {}", id);

        categoriaService.delete(id);
    }

}
