package com.example.mcommerceadminapp.view.inventory.view_model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mcommerceadminapp.model.shopify_repository.products.ProductsRepo
import com.example.mcommerceadminapp.view.inventory.view_model.InventoryViewModel

class InventoryViewModelFactory(private val repo: ProductsRepo) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            InventoryViewModel(repo) as T
        } else {
            throw IllegalArgumentException("Error")
        }
    }
}