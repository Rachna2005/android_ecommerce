package io.kess.ecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.kess.ecommerce.R
import io.kess.ecommerce.model.Category
import io.kess.ecommerce.model.Product

class ProductAdapter(
    private val productList: List<Product>,
    private val categoryList: List<Category>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val categoryMap = categoryList.associateBy { it.id }
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
        val product = productList[position]

        Glide.with(holder.itemView.context).load(product.image).into(holder.img)
        holder.name.text = product.name
        holder.price.text = "$${product.price}"
        val categoryName = categoryMap[product.categoryID]?.name ?: "Unknown"
        holder.category.text = categoryName
    }

    override fun getItemCount(): Int = productList.size

}

