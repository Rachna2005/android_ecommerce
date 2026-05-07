package io.kess.ecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.kess.ecommerce.R
import io.kess.ecommerce.model.Product

class ProductAdapter(private val list: List<Product>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.imgProduct)
        val category = itemView.findViewById<TextView>(R.id.txtCategory)
        val name = itemView.findViewById<TextView>(R.id.txtName)
        val price = itemView.findViewById<TextView>(R.id.txtPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false) // card file
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = list[position]

        holder.img.setImageResource(product.image)
        holder.category.text = product.category
        holder.name.text = product.name
        holder.price.text = "$${product.price}"
    }

    override fun getItemCount(): Int = list.size

}

