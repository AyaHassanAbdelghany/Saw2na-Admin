package com.example.mcommerceadminapp.view.inventory.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mcommerceadminapp.model.shopify_repository.products.ProductsRepo
import com.example.mcommerceadminapp.pojo.products.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InventoryViewModel(private val repo: ProductsRepo) : ViewModel() {
    private val _products = MutableLiveData<ArrayList<Products>>()
    val products:LiveData<ArrayList<Products>> = _products

    fun getAllProduct(){
        viewModelScope.launch(Dispatchers.IO) {
            val res = repo.getAllProducts()
            withContext(Dispatchers.Main){
                _products.value = res
            }
        }
    }

    fun addProduct(products: Products){
        viewModelScope.launch(Dispatchers.IO) {
            val res = repo.addProduct(products)
            withContext(Dispatchers.Main){
                val list = _products.value
                list!!.add(res)
                _products.value = list
            }
        }
    }

    fun deleteProductByID(productID:String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteProductByID(productID)
        }
    }
}