package com.example.appvendas.data.model

import com.example.appvendas.R
import java.io.Serializable

data class Produto(
    var nome: String,
    var imagem: Int,
    var valor: Double,
    var observacao: String? = null,
    var quantidade: Int = 0
) : Serializable {
    companion object {
        fun getList(): ArrayList<Produto> {
            return arrayListOf(
                Produto("Mochila Nike Brasília 9.0", R.drawable.produto1, 109.99),
                Produto("SSD Kingston A400", R.drawable.produto2, 216.90),
                Produto("Memória RAM Crucial Ballistix 8GB", R.drawable.produto3, 259.90),
                Produto(
                    "Placa de Vídeo Gigabyte NVIDIA GeForce RTX 2060",
                    R.drawable.produto4,
                    3699.90
                ),
                Produto("SSD XPG S41 TUF, 256GB, M.2", R.drawable.produto5, 299.90),
                Produto("Processador Intel Core i5-10400", R.drawable.produto6, 1219.90),
                Produto("Fonte XPG Core Reactor, 750W", R.drawable.produto7, 999.90),
                Produto("HD Seagate BarraCuda, 4TB", R.drawable.produto8, 598.90),
            )
        }
    }
}