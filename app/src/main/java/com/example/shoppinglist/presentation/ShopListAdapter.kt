package com.example.shoppinglist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter : Adapter<ShopListAdapter.ShopItemViewHolder>() {

    private var count = 0
    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        Log.d("ShopListAdapter", "onCreateViewHolder count: ${++count}")
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type $viewType")
        }
        val view =
            LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        holder.titleTextView.text = shopItem.name
        holder.countTextView.text = shopItem.count.toString()
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = shopList[position]
        if (shopItem.enabled)
            return VIEW_TYPE_ENABLED
        return VIEW_TYPE_DISABLED
    }

    override fun getItemCount(): Int = shopList.size

    inner class ShopItemViewHolder(view: View) : ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val countTextView: TextView = view.findViewById(R.id.countTextView)
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE = 10
    }
}