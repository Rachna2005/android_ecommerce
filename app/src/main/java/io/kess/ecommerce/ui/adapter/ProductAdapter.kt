package io.kess.ecommerce.ui.adapter

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
import io.kess.ecommerce.ui.ProductCardType
import android.graphics.Paint

import kotlin.times

class ProductAdapter(
    private val productList: List<Product>,
    private val type: ProductCardType
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class NormalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.imgProduct)
        val name = itemView.findViewById<TextView>(R.id.txtName)
        val originalPrice = itemView.findViewById<TextView>(R.id.txtOldPrice)
        val discountPrice = itemView.findViewById<TextView>(R.id.txtPrice)

    }

    class DiscountViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.productImage)
        val name = itemView.findViewById<TextView>(R.id.productName)
        val discountPrice = itemView.findViewById<TextView>(R.id.currentPrice)
        val discount = itemView.findViewById<TextView>(R.id.discountBadge)
        val originalPrice = itemView.findViewById<TextView>(R.id.originalPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (type) {
            ProductCardType.NORMAL -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_product, parent, false)
                NormalViewHolder(view)
            }

            ProductCardType.DISCOUNT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.discount_product, parent, false)
                DiscountViewHolder(view)
            }
        }
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = productList[position]
        val discount = product.price * ((product.discountPercentage ?: 0.0) / 100)
        val priceAfterDiscount = product.price - discount
        val hasDiscount = (product.discountPercentage ?: 0.0) > 0

        when (holder) {
            is NormalViewHolder -> {
                Glide.with(holder.itemView.context).load(product.image).into(holder.img)
                holder.name.text = product.name
//                holder.price.text = "$${String.format("%.2f", product.price)}"
                if (hasDiscount) {
                    holder.originalPrice.text = "$${String.format("%.2f", product.price)}"
                    holder.originalPrice.paintFlags =
                        holder.originalPrice.paintFlags or
                                Paint.STRIKE_THRU_TEXT_FLAG
                    holder.originalPrice.visibility = View.VISIBLE
                    holder.discountPrice.text = "$${String.format("%.2f", priceAfterDiscount)}"
                } else {
                    holder.originalPrice.visibility = View.GONE
                    holder.discountPrice.text = "$${String.format("%.2f", product.price)}"
                    holder.originalPrice.paintFlags = holder.originalPrice.paintFlags and
                            Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
            }

            is DiscountViewHolder -> {
                holder.name.text = product.name
                holder.discountPrice.text = "$${String.format("%.2f", priceAfterDiscount)}"
                Glide.with(holder.itemView.context).load(product.image).into(holder.img)
                holder.originalPrice.text = "$${String.format("%.2f", product.price)}"
                holder.originalPrice.paintFlags = holder.originalPrice.paintFlags or
                        Paint.STRIKE_THRU_TEXT_FLAG
                holder.discount.text = "-${product.discountPercentage?.toInt()}%"
            }
        }

    }

    override fun getItemCount(): Int = productList.size

}

