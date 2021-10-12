package com.example.appvendas.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appvendas.R
import com.example.appvendas.data.model.Produto
import kotlinx.android.synthetic.main.item_produto.view.*

class ProdutoAdapter(
    private val produtoList: List<Produto>,
    val produtoClick: (Produto) -> Unit
) :
    RecyclerView.Adapter<ProdutoAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_produto, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val produto = produtoList[position]

        holder.imgProduto.setImageResource(produto.imagem)
        holder.txtProduto.text = produto.nome
        holder.txtValor.text = produto.valor.toString()

        holder.itemView.setOnClickListener { produtoClick(produto) }
    }

    override fun getItemCount() = produtoList.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduto: ImageView = itemView.imgProduto
        val txtProduto: TextView = itemView.txtProduto
        val txtValor: TextView = itemView.txtValor
    }
}