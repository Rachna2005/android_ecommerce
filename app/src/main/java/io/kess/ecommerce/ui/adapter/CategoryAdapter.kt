package io.kess.ecommerce.ui.adapter

import io.kess.ecommerce.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.kess.ecommerce.model.Category

class CategoryAdapter(private val list: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<ImageView>(R.id.image)
        val title = view.findViewById<TextView>(R.id.title)
        val count = view.findViewById<TextView>(R.id.count)
        val container = view.findViewById<LinearLayout>(R.id.textContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(io.kess.ecommerce.R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.title.text = item.name
        holder.count.text = "${item.productCount} Products"
        holder.img.setImageResource(item.image)
        val para = holder.container.layoutParams as RelativeLayout.LayoutParams
        if(item.alignRight){
            para.addRule(RelativeLayout.ALIGN_PARENT_END)
            para.removeRule(RelativeLayout.ALIGN_PARENT_START)
            holder.title.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
            holder.count.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
        }else{
            para.addRule(RelativeLayout.ALIGN_PARENT_START)
            para.removeRule(RelativeLayout.ALIGN_PARENT_END)
            holder.title.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
            holder.count.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
        }
        holder.container.layoutParams = para
    }

    override fun getItemCount(): Int = list.size

}