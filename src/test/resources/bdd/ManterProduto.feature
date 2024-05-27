# language: pt

Funcionalidade: Manter produto

  Cenario: Cadastro com sucesso
    Dado que não exista o produto
    Quando for cadastrado um produto com nome "fone de ouvido", descrição "intra auricular" com o valor de 2700.00
    Entao o sistema terá o produto "fone de ouvido", descrição "intra auricular" com o valor de 2700.00

  Cenario: Exclusão de produto
    Dado que o produto exista
    Quando for solicitado exclusão do produto com nome "fone de ouvido", descrição "intra auricular" e valor de 2700.00
    Entao sistema exclui o produto
