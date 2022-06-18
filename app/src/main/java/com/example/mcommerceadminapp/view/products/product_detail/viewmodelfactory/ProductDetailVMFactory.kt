package com.example.mcommerceadminapp.view.products.product_detail.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mcommerceadminapp.model.shopify_repository.products.ProductsRepo
import com.example.mcommerceadminapp.view.products.product_detail.viewmodel.ProductDetailVM

class ProductDetailVMFactory(
    private val iProducts: ProductsRepo,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProductDetailVM::class.java)) {
            ProductDetailVM(iProducts) as T
        } else {
            throw IllegalArgumentException("Error")
        }
    }
}