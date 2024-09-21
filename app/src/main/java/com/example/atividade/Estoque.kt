package com.example.atividade

class Estoque {

    companion object{
        val listaProdutos = mutableListOf<Produto>()

        fun calcularValorTotalEstoque(): Double {
            return listaProdutos.sumOf { it.preco * it.qtdEstoque }
        }

        fun calcularQuantidadeTotalProdutos(): Int {
            return listaProdutos.sumOf { it.qtdEstoque }
        }
    }
}