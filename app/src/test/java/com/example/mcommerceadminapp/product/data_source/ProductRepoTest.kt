package com.example.mcommerceadminapp.product.data_source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mcommerceadminapp.model.shopify_repository.products.ProductsRepo
import com.example.mcommerceadminapp.pojo.products.Products
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [30])
@RunWith(AndroidJUnit4::class)
class ProductRepoTest :TestCase() {

    private lateinit var productRepo : ProductsRepo
    private lateinit var productFakeDataSource : ProductFakeDataSource

    private val productActual = Products(title = "ADIDAS | CLASSIC BACKPACK")
    private val allProductActual = listOf(productActual)

    private val productExpected = Products(title = "ADIDAS | CLASSIC BACKPACK")
    private val allProductExpected = listOf(productExpected)



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
    fun getAllProducts_requestProductList()  =  runBlocking {
        //Given repo
        val products = productRepo.getAllProducts()
        // Then
      assertEquals((allProductExpected.get(0).title),products.get(0).title)
    }

}