package br.com.sebrae.manter_produto.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoCadastroRecord (
        String nome,
        String descricao,
        BigDecimal preco
) {
}
