package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter : Adapter<ShopListAdapter.ShopItemViewHolder>() {

    private val shopList = listOf<ShopItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_shop_enabled, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        holder.titleTextView.text = shopItem.name
        holder.countTextView.text = shopItem.count.toString()
    }

    override fun getItemCount(): Int = shopList.size

    inner class ShopItemViewHolder(view: View) : ViewHolder(view) {
        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
        val countTextView = view.findViewById<TextView>(R.id.countTextView)
    }
}