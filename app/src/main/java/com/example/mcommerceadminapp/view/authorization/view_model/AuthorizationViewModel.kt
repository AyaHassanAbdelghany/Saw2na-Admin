package com.example.mcommerceadminapp.view.authorization.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mcommerceadminapp.model.Keys
import com.example.mcommerceadminapp.model.shopify_repository.products.ProductsRepo

class AuthorizationViewModel() : ViewModel() {

    private val _result = MutableLiveData<Boolean>()
    val result : LiveData<Boolean> = _result

    fun checkAuthorization(key:String){

        _result.value = (key == Keys.Shopify_Access_Token)

    }



}