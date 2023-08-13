package com.example.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.presentation.ShopListAdapter.Companion.MAX_POOL_SIZE
import com.example.shoppinglist.presentation.ShopListAdapter.Companion.VIEW_TYPE_DISABLED
import com.example.shoppinglist.presentation.ShopListAdapter.Companion.VIEW_TYPE_ENABLED

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter
    private lateinit var shopItemRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopListLD.observe(this) {
            shopListAdapter.shopList = it
        }
    }

    private fun setUpRecyclerView() {
        shopListAdapter = ShopListAdapter()
        shopItemRecyclerView = findViewById(R.id.itemsRecyclerView)
        shopItemRecyclerView.apply {
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(VIEW_TYPE_ENABLED, MAX_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(VIEW_TYPE_DISABLED, MAX_POOL_SIZE)
        }
    }
}