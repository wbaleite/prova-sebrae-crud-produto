package br.com.sebrae.manter_produto.produto;

import br.com.sebrae.manter_produto.comum.exception.NegocioException;
import br.com.sebrae.manter_produto.comum.exception.RecursoNaoEncontradoException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;
    private final Validator validator;

    @Transactional(readOnly = true)
    public ProdutoRecord obterPorID(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(RecursoNaoEncontradoException::new);

        return produtoMapper.map(produto);
    }

    @Transactional(readOnly = true)
    public List<ProdutoRecord> obterTodosProdutos() {
        return produtoRepository.findAll().stream()
                .map(produtoMapper::map)
                .toList();
    }

    @Transactional
    public void deletarPorId(Long id) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(RecursoNaoEncontradoException::new);

        produtoRepository.deleteById(produto.getId());
    }

    @Transactional
    public ProdutoRecord cadastrarProduto(ProdutoCadastroRecord produtoCadastroRecord) {
        var produto = produtoMapper.map(produtoCadastroRecord);

        validarEntidade(produto);

        produtoRepository.save(produto);
        return produtoMapper.map(produto);
    }

    @Transactional
    public void atualizarProduto(Long id, ProdutoCadastroRecord produtoCadastroRecord) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(RecursoNaoEncontradoException::new);

        produtoMapper.map(produtoCadastroRecord, produto);

        validarEntidade(produto);

        produtoRepository.save(produto);
    }

    private void validarEntidade(Produto entidade) {
        var error = validator.validate(entidade)
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        if (StringUtils.hasText(error)) {
            throw new NegocioException(error);
        }
    }
}
