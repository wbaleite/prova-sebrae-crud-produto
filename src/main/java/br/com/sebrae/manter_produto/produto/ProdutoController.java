package br.com.sebrae.manter_produto.produto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1/produto")
@Tag(name = "Produto", description = "API para gerenciamento de produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Operation(summary = "Obter registro de produto", description = "Obter os dados de um produto existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obter dados de produto com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado!"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoRecord> obterProdutoPorID (@PathVariable Long id) {
        var produtoLocalizado = produtoService.obterPorID(id);
        return ResponseEntity.ok(produtoLocalizado);
    }

    @Operation(summary = "Obter todos os produtos", description = "Retorna uma lista de todas os produtos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<ProdutoRecord>> obterTodosProduto () {
        var listaProduto = produtoService.obterTodosProdutos();
        return ResponseEntity.ok(listaProduto);
    }

    @Operation(summary = "Deletar um produto", description = "Deleta um produto cadastrado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deletarProduto (@PathVariable Long id) {
        produtoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Cadastrar produto", description = "Cadastrar um produto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso!")
    })
    @PostMapping
    public ResponseEntity cadastrarProduto (@RequestBody ProdutoCadastroRecord produtoCadastroRecord) {
        var produtoCadastrado = produtoService.cadastrarProduto(produtoCadastroRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCadastrado);
    }

    @Operation(summary = "Atualizar produto", description = "Atualizar o cadastro de um produto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos!")
    })
    @PutMapping("/{id}")
    public ResponseEntity atualizarProduto (
            @Parameter(description = "ID do produto a ser atualizado", required = true)
            @PathVariable Long id,
            @RequestBody ProdutoCadastroRecord produtoCadastroRecord) {

        produtoService.atualizarProduto(id, produtoCadastroRecord);
        return ResponseEntity.noContent().build();
    }
}

