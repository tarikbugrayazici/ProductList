package com.example.productlist.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productlist.R
import com.example.productlist.data.entity.Product
import com.example.productlist.data.entity.ProductList
import com.example.productlist.databinding.MainRvItemBinding
import com.example.productlist.util.extension.loadImage

class ProductListAdapter(
    private var list: ArrayList<ProductList>,
    private val onClick: (Int, Int) -> Unit,
    private val favOnclick: (ProductList) -> Unit
) :
    RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val itemBinding =
            MainRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ProductListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val product = list[position]
        with(holder.binding) {
            productImage.loadImage(product.imageUrl)
            productName.text = product.displayName
            val favIcon = if (product.isFav) R.drawable.fav else R.drawable.un_fav
            fav.setImageResource(favIcon)
        }
        holder.itemView.setOnClickListener {
            product.productId.let { it1 -> onClick.invoke(it1, position) }
        }
        holder.binding.fav.setOnClickListener {
            product.isFav = !product.isFav
            product.productId.let {
                favOnclick.invoke(product)
            }
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<ProductList>? = null) {
        if (list != null) this.list.addAll(list)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<ProductList>? = null) {
        if (list != null) this.list = ArrayList(list)
        notifyDataSetChanged()
    }

    class ProductListViewHolder(val binding: MainRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}