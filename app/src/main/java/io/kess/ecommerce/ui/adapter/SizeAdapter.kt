package io.kess.ecommerce.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import io.kess.ecommerce.R
import io.kess.ecommerce.model.ProductVariant
import android.graphics.Color

class SizeAdapter(
    private val onClick: (ProductVariant) -> Unit
) : ListAdapter<ProductVariant, SizeAdapter.ViewHolder>(DiffCallback()) {

    private var selectedId: String? = null

    fun setSelected(id: String) {
        selectedId = id
        notifyDataSetChanged()
    }

    class DiffCallback : DiffUtil.ItemCallback<ProductVariant>() {
        override fun areItemsTheSame(
            oldItem: ProductVariant,
            newItem: ProductVariant
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ProductVariant,
            newItem: ProductVariant
        ): Boolean = oldItem == newItem
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button: MaterialButton = view.findViewById(R.id.btnVariant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.varaint_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.button.text = item.size

        if (item.id == selectedId) {
            holder.button.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.primary)
            )
            holder.button.setTextColor(
                ContextCompat.getColor(holder.itemView.context, R.color.white)
            )
        } else {
            holder.button.setBackgroundColor(Color.TRANSPARENT)
            holder.button.setTextColor(
                ContextCompat.getColor(holder.itemView.context, R.color.black)
            )
        }

        holder.button.setOnClickListener {
            selectedId = item.id
            notifyDataSetChanged()
            onClick(item)
        }
    }
}