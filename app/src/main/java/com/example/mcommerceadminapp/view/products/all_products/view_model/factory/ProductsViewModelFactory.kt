package com.example.mcommerceadminapp.view.products.all_products.view_model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mcommerceadminapp.model.shopify_repository.products.ProductsRepo
import com.example.mcommerceadminapp.view.products.all_products.view_model.ProductsViewModel

class ProductsViewModelFactory(private val repo: ProductsRepo) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
            ProductsViewModel(repo) as T
        } else {
            throw IllegalArgumentException("Error")
        }
    }
}