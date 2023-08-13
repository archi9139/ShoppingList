package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository
import java.util.Random

object ShopListRepositoryImpl : ShopListRepository {

    private var shopList = sortedSetOf(Comparator<ShopItem> {
            o1, o2 -> o1.id.compareTo(o2.id)
    })
    private var autoIncrementId = 1

    private val shopListLiveData = MutableLiveData<List<ShopItem>>()

    init {
        for (i in 1..1000) {
            val item = ShopItem("Name $i", i, Random().nextBoolean())
            addShopItem(item)
        }
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLiveData
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateShopList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateShopList()
    }

    override fun getShopItem(id: Int): ShopItem {
        return shopList.find { it.id == id } ?: throw RuntimeException("Element not found")
    }

    private fun updateShopList() {
        shopListLiveData.value = shopList.toList()
    }
}