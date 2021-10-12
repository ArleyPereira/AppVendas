package com.example.appvendas.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appvendas.R
import com.example.appvendas.data.model.Produto
import com.example.appvendas.util.GetMask
import kotlinx.android.synthetic.main.activity_resumo_pedido.*

class ResumoPedidoActivity : AppCompatActivity() {

    private var produtoList: MutableList<Produto> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumo_pedido)

        // Recupera dados passados via intent
        getExtra()

        // Ouvinte de clicks componentes
        configClicks()
    }

    // Ouvinte de clicks componentes
    private fun configClicks() {
        btnFinalizar.setOnClickListener { finalizar() }
    }

    // Recupera dados passados via intent
    private fun getExtra() {
        produtoList = intent.getSerializableExtra("produtoList") as MutableList<Produto>

        txtTotalCompra.text = getString(R.string.valor, GetMask.getValor(totalCompra()))
    }

    // Calcula o valor total dos produtos ( valor * quantidade )
    private fun totalCompra(): Double {
        var total = 0.0
        for (produto in produtoList) {
            total += (produto.valor * produto.quantidade)
        }
        return total
    }

    // Verifica se os valores estão de acordo
    private fun finalizar() {
        val totalPagar: Double
        var desconto = 0.0
        var dinheiro = 0.0
        var debito = 0.0
        var credito = 0.0

        if (edtDesconto.text.toString().trim().isNotEmpty()) {
            desconto = edtDesconto.text.toString().toDouble()
        }

        if (edtDinheiro.text.toString().trim().isNotEmpty()) {
            dinheiro = edtDinheiro.text.toString().toDouble()
        }

        if (edtDebito.text.toString().trim().isNotEmpty()) {
            debito = edtDebito.text.toString().toDouble()
        }

        if (edtCredito.text.toString().trim().isNotEmpty()) {
            credito = edtCredito.text.toString().toDouble()
        }

        totalPagar = desconto + dinheiro + debito + credito

        when {
            totalPagar < totalCompra() -> {
                toast(getString(R.string.valor_pago_menor, GetMask.getValor(totalCompra())))
            }
            totalPagar > totalCompra() -> {
                toast(getString(R.string.valor_pago_maior, GetMask.getValor(totalCompra())))
            }
            else -> {
                toast(getString(R.string.venda_sucesso))
                limparCampos()
            }
        }
    }

    // Exibe Toast em tela
    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    // Limpa os campos e lista
    private fun limparCampos() {
        edtDesconto.text.clear()
        edtDinheiro.text.clear()
        edtDebito.text.clear()
        edtCredito.text.clear()
        produtoList.clear()

        txtTotalCompra.text = getString(R.string.valor, GetMask.getValor(0.0))
    }

}