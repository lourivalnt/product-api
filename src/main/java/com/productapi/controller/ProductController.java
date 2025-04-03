package com.productapi.controller;

import com.productapi.dto.PageResponse;
import com.productapi.dto.ProductDTO;
import com.productapi.mapper.ProductMapper;
import com.productapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/products")
@Tag(name = "Product Management", description = "Operações para gerenciar produtos")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    @Operation(summary = "Criar um novo produto", description = "Salva um novo produto no banco de dados")
    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDTO.class)))
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        // Converte DTO para entidade
        var product = productMapper.toEntity(productDTO);
        var savedProduct = productService.save(product);
        // Converte entidade salva de volta para DTO
        var savedDto = productMapper.toDTO(savedProduct);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();

        // Retorna a resposta com status 201 Created e a URI no cabeçalho Location
        return ResponseEntity.created(location).body(savedDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Retorna os detalhes de um produto específico")
    @ApiResponse(responseCode = "200", description = "Produto encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDTO.class)))
    @ApiResponse(responseCode = "404", description = "Produto não encontrado",
            content = @Content)
    public ResponseEntity<ProductDTO> findById(
            @Parameter(description = "ID do produto", required = true) @PathVariable Long id) {
        // Busca entidade pelo ID
        var product = productService.findById(id);
        // Converte entidade para DTO
        return ResponseEntity.ok(productMapper.toDTO(product));
    }

    @GetMapping
    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista paginada de produtos")
    @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso",
            content = @Content(mediaType = "application/json"))
    public ResponseEntity<Map<String, Object>> findAll(
            @Parameter(description = "Número da página (inicia em 0)", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamanho da página", example = "10") @RequestParam(defaultValue = "10") int size) {

        var products = productService.findAll(page, size);

        var dtos = products.stream()
                .map(productMapper::toDTO)
                .toList();

        int totalItems = productService.countAll();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        Map<String, Object> response = new HashMap<>();
        response.put("items", dtos);
        response.put("totalItems", totalItems);
        response.put("totalPages", totalPages);
        response.put("pageSize", size);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<PageResponse<ProductDTO>> findTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        var products = productService.findAll(page, size);

        var dtos = products.stream()
                    .map(productMapper::toDTO)
                    .toList();

            // Conta total de registros
            int totalItems = productService.countAll();
            int totalPages = (int) Math.ceil((double) totalItems / size);

            PageResponse<ProductDTO> response = new PageResponse<>(
                    dtos,
                    totalItems,
                    totalPages,
                    page,
                    size
            );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um produto", description = "Remove um produto pelo ID")
    @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso",
            content = @Content)
    @ApiResponse(responseCode = "404", description = "Produto não encontrado",
            content = @Content)
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do produto", required = true) @PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
