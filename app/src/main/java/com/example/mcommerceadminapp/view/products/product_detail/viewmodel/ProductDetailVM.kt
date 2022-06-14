package com.example.mcommerceadminapp.view.products.product_detail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mcommerceadminapp.model.shopify_repository.products.ProductsRepo
import com.example.mcommerceadminapp.pojo.products.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailVM(
    private val repo: ProductsRepo
) : ViewModel() {


    private val _productDetail = MutableLiveData<Products>()
    var productDetail: LiveData<Products> = _productDetail

    fun getProductDetail(id: String) {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }


}