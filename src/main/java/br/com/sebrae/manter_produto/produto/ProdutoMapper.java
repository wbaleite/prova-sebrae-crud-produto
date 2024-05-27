package br.com.sebrae.manter_produto.produto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoRecord map(Produto entidade);

    Produto map(ProdutoCadastroRecord record);

    void map(ProdutoCadastroRecord source, @MappingTarget Produto target);
}
