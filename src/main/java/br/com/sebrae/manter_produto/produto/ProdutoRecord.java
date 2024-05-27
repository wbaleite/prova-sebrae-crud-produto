package br.com.sebrae.manter_produto.produto;

import java.math.BigDecimal;

public record ProdutoRecord(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco
) {
}
