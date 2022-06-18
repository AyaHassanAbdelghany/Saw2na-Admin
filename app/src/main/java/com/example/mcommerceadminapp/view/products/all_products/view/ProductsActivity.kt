package com.example.mcommerceadminapp.view.products.all_products.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mcommerceadminapp.databinding.ActivityProductsBinding
import com.example.mcommerceadminapp.model.remote_source.products.ProductsRemoteSource
import com.example.mcommerceadminapp.model.shopify_repository.products.ProductsRepo
import com.example.mcommerceadminapp.pojo.products.Products
import com.example.mcommerceadminapp.view.products.all_products.view.adapter.ProductsAdapter
import com.example.mcommerceadminapp.view.products.all_products.view.adapter.ProductsCommunicator
import com.example.mcommerceadminapp.view.products.all_products.view.add_product.AddNewProductActivity
import com.example.mcommerceadminapp.view.products.all_products.view_model.ProductsViewModel
import com.example.mcommerceadminapp.view.products.all_products.view_model.factory.ProductsViewModelFactory
import com.example.mcommerceadminapp.view.products.product_detail.view.ProductDetail
import com.google.gson.Gson

class ProductsActivity : AppCompatActivity() , ProductsCommunicator {
    private lateinit var binding : ActivityProductsBinding
    private lateinit var viewModel:ProductsViewModel
    val REQUEST_CODE = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ProductsViewModelFactory(ProductsRepo.getInstance(ProductsRemoteSource.getInstance()))

        viewModel = ViewModelProvider(this,factory)[ProductsViewModel::class.java]

        val adapter = ProductsAdapter(this,this)
        adapter.setData(ArrayList())

        binding.recycleViewProducts.adapter = adapter

        viewModel.products.observe(this){
           binding.loadingProgressBar.visibility = View.INVISIBLE
            adapter.setData(it)
        }



        viewModel.getAllProduct()

        binding.addProductButton.setOnClickListener {
            startActivityForResult(Intent(this,AddNewProductActivity::class.java),2)
        }


    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 2) {

            val product = Products()
            if (data != null) {
                product.title = data.getStringExtra("title")
                product.vendor = data.getStringExtra("vendor")
                product.productType = data.getStringExtra("product_type")

                viewModel.addProduct(product)

            }
        }
    }
    override fun setDefaultAddress(addressID: String) {
    }

    override fun deleteProduct(productID: String) {
        viewModel.deleteProductByID(productID)
    }

    override fun showDetails(product: String) {
       val intent = Intent(this,ProductDetail::class.java)
        intent.putExtra("product",product)
      //  startActivity(intent)
    }

}