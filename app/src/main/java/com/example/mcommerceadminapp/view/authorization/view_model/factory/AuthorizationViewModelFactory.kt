package com.example.mcommerceadminapp.view.authorization.view_model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mcommerceadminapp.model.shopify_repository.products.ProductsRepo
import com.example.mcommerceadminapp.view.authorization.view_model.AuthorizationViewModel

class AuthorizationViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AuthorizationViewModel::class.java)) {
            AuthorizationViewModel() as T
        } else {
            throw IllegalArgumentException("Error")
        }
    }
}