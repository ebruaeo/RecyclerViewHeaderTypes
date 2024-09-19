package com.example.recyclerviewheader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroceryAdapter(private val items: List<Product>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_STATIC_HEADER = 0
        private const val VIEW_TYPE_PRODUCT = 1
    }

    override fun getItemViewType(position: Int): Int {
        // The first item (position 0) will be the static header
        return if (position == 0) VIEW_TYPE_STATIC_HEADER else VIEW_TYPE_PRODUCT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_STATIC_HEADER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_static_header, parent, false)
            StaticHeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
            ProductViewHolder(view)
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StaticHeaderViewHolder) {
            holder.bind("My Shopping List")  // Static header title
        } else if (holder is ProductViewHolder) {
            // Adjust position for the product list since the header takes position 0
            holder.bind(items[position - 1])
        }
    }
    override fun getItemCount(): Int = items.size + 1  // +1 for the static header

    // ViewHolder for the static header
    class StaticHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val headerTitle: TextView = view.findViewById(R.id.headerTitle)
        fun bind(title: String) {
            headerTitle.text = title
        }
    }

    // ViewHolder for products
    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val productName: TextView = view.findViewById(R.id.productName)
        fun bind(product: Product) {
            productName.text = product.name
        }
    }
}
