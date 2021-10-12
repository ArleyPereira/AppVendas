package com.example.appvendas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appvendas.data.model.Produto
import com.example.appvendas.view.adapter.ProdutoAdapter
import com.example.appvendas.view.ui.ResumoPedidoActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_produto.view.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var produtoAdapter: ProdutoAdapter
    private var produtoList: MutableList<Produto> = mutableListOf()

    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicia RecyclerView
        configRv()

        // Ouvinte de clicks componentes
        configClicks()
    }

    // Ouvinte de clicks componentes
    private fun configClicks() {
        btnContinuar.setOnClickListener {
            if(produtoList.isNotEmpty()){
                val intent = Intent(this, ResumoPedidoActivity::class.java)
                intent.putExtra("produtoList", produtoList as Serializable)
                startActivity(intent)
            }else {
                Toast.makeText(this, getString(R.string.list_vazia), Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Inicia RecyclerView
    private fun configRv() {
        rvProdutos.layoutManager = LinearLayoutManager(this)
        rvProdutos.setHasFixedSize(true)
        produtoAdapter = ProdutoAdapter(Produto.getList()) { produto ->
            showDialog(produto)
        }
        rvProdutos.adapter = produtoAdapter
    }

    // Exibe dialog para inserir quantidade e observação
    private fun showDialog(produto: Produto) {
        val builder = AlertDialog.Builder(this)

        val view = LayoutInflater.from(this).inflate(R.layout.dialog_produto, null)
        val edtQtd = view.edtQtd
        val edtObs = view.edtObs

        view.btnAdc.setOnClickListener {
            if(edtQtd.text.toString().trim().isNotEmpty()){
                produto.quantidade = edtQtd.text.toString().toInt()
            }else {
                produto.quantidade = 1
            }

            if(edtObs.text.toString().trim().isNotEmpty()){
                produto.observacao = edtObs.text.toString()
            }else {
                produto.observacao = ""
            }

            produtoList.add(produto)
            dialog.dismiss()

        }

        builder.setView(view)

        dialog = builder.create()
        dialog.show()
    }

}