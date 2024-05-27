package br.com.sebrae.manter_produto.features;

import br.com.sebrae.manter_produto.comum.exception.RecursoNaoEncontradoException;
import br.com.sebrae.manter_produto.produto.Produto;
import br.com.sebrae.manter_produto.produto.ProdutoCadastroRecord;
import br.com.sebrae.manter_produto.produto.ProdutoRecord;
import br.com.sebrae.manter_produto.produto.ProdutoService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class ProdutoStepDefinitions {

    private final ProdutoService produtoService;

    private ProdutoRecord produtoLocalizado;
    private Exception exception;

    @Dado("que não exista o produto")
    public void queNaoExistaProduto() {
    }

    @Quando("for cadastrado um produto com nome {string}, descrição {string} com o valor de {bigdecimal}")
    public void forCadastradoUmProdutoComNomeEDescricaoComValor(String nome, String descricao, BigDecimal valor) {
        var produtoCadastroRecord = new ProdutoCadastroRecord(nome, descricao, valor);
        produtoService.cadastrarProduto(produtoCadastroRecord);
    }

    @Entao("o sistema terá o produto {string}, descrição {string} com o valor de {bigdecimal}")
    public void sistemaCadastraOProduto(String nome, String descricao, BigDecimal valor) {
        var listaProdutos = produtoService.obterTodosProdutos();

        Assert.assertEquals(1, listaProdutos.size());
        Assert.assertEquals(nome, listaProdutos.get(0).nome());
        Assert.assertEquals(descricao, listaProdutos.get(0).descricao());
        Assert.assertEquals(0, valor.compareTo(listaProdutos.get(0).preco()));
    }

    @Dado("que o produto exista")
    public void queOProduoExista() {
    }

    @Quando("for solicitado exclusão do produto com nome {string}, descrição {string} e valor de {bigdecimal}")
    public void forSolicitadoExclusao(String nome, String descricao, BigDecimal valor) {
        var produtoLocalizado = produtoService.obterTodosProdutos()
                .stream()
                .filter(produto -> produto.nome().equals(nome)
                        && produto.descricao().equals(descricao)
                        && valor.compareTo(produto.preco()) == 0)
                .findFirst()
                .orElseThrow(RecursoNaoEncontradoException::new);

        this.produtoLocalizado = produtoLocalizado;
        produtoService.deletarPorId(produtoLocalizado.id());
    }

    @Entao("sistema exclui o produto")
    public void sistemaExcluiAPessoa() {
        Exception exception = Assertions.assertThrows(RecursoNaoEncontradoException.class,
                () -> produtoService.obterPorID(produtoLocalizado.id()));

        Assertions.assertTrue(exception.getMessage().contains("Recurso não encontrado!"));
    }
}
