package com.example.mcommerceadminapp.product.data_source

import android.os.Looper.getMainLooper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mcommerceadminapp.model.shopify_repository.products.ProductsRepo
import com.example.mcommerceadminapp.pojo.products.Products
import com.example.mcommerceadminapp.view.products.all_products.view_model.ProductsViewModel
import getOrAwaitValue
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@Config(sdk = [30])
@RunWith(AndroidJUnit4::class)
@LooperMode(LooperMode.Mode.PAUSED)
@ExperimentalCoroutinesApi

class ProductViewModelTest :TestCase() {

    private lateinit var productRepo : ProductsRepo
    private lateinit var productFakeDataSource : ProductFakeDataSource

    private val productActual = Products(title = "ADIDAS | CLASSIC BACKPACK")
    private val allProductActual = listOf(productActual)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createRepository() {
        productFakeDataSource =  ProductFakeDataSource.getInstance()
        productRepo = ProductsRepo.getInstance(
            productFakeDataSource
        )
        productFakeDataSource.setProduct(allProductActual.toMutableList())

    }
    @Test
    fun getAllProducts_postValueInProducts() = runBlocking {
        //Given viewModel
      val productViewModel = ProductsViewModel(productRepo)
        productViewModel.getAllProduct()
        val value = productViewModel.products.getOrAwaitValue()
        // Then
        assertEquals(1,value.count())
    }
}