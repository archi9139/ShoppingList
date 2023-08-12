package com.example.shoppinglist.domain

interface ShopListRepository {

    fun getShopList(): List<ShopItem>

    fun addShopList(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun getShopItem(id: Int): ShopItem
}