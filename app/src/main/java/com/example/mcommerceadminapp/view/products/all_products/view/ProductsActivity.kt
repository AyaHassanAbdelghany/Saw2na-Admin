package com.example.mcommerceadminapp.view.products.all_products.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mcommerceadminapp.databinding.ActivityProductsBinding
import com.example.mcommerceadminapp.model.remote_source.products.ProductsRemoteSource
import com.example.mcommerceadminapp.model.shopify_repository.products.ProductsRepo
import com.example.mcommerceadminapp.network.MyConnectivityManager
import com.example.mcommerceadminapp.pojo.products.Products
import com.example.mcommerceadminapp.view.products.all_products.view.adapter.ProductsAdapter
import com.example.mcommerceadminapp.view.products.all_products.view.adapter.ProductsCommunicator
import com.example.mcommerceadminapp.view.products.all_products.view.add_product.AddNewProductActivity
import com.example.mcommerceadminapp.view.products.all_products.view_model.ProductsViewModel
import com.example.mcommerceadminapp.view.products.all_products.view_model.factory.ProductsViewModelFactory
import com.example.mcommerceadminapp.view.products.product_detail.view.ProductDetail
import com.google.gson.Gson

class ProductsActivity : AppCompatActivity(), ProductsCommunicator {
    private lateinit var binding: ActivityProductsBinding
    private lateinit var viewModel: ProductsViewModel
    private var isConnected = true
    private lateinit var adapter:ProductsAdapter
    val REQUEST_CODE = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory =
            ProductsViewModelFactory(ProductsRepo.getInstance(ProductsRemoteSource.getInstance()))

        viewModel = ViewModelProvider(this, factory)[ProductsViewModel::class.java]

        adapter = ProductsAdapter(this, this)
        adapter.setData(ArrayList())

        binding.recycleViewProducts.adapter = adapter


        MyConnectivityManager.state.observe(this) {

            if (it) {
                Toast.makeText(this, "Connection is restored", Toast.LENGTH_SHORT).show()
                viewModel.getAllProduct()
                isConnected = true
                binding.noNetworkLayout.visibility = View.INVISIBLE
                binding.loadingProgressBar.visibility = View.VISIBLE
                binding.recycleViewProducts.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "Connection is lost", Toast.LENGTH_SHORT).show()
                isConnected = false
                binding.noNetworkLayout.visibility = View.VISIBLE
                binding.loadingProgressBar.visibility = View.INVISIBLE
                binding.recycleViewProducts.visibility = View.INVISIBLE
            }
        }


        binding.addProductButton.setOnClickListener {
            startActivityForResult(Intent(this, AddNewProductActivity::class.java), 2)
        }


    }

    override fun onResume() {
        super.onResume()
        viewModel.products.removeObservers(this)
        viewModel.products.observe(this) {
            binding.loadingProgressBar.visibility = View.INVISIBLE
            adapter.setData(it)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 2) {

            if (data != null) {
                val res = data.getStringExtra("product")
                val product = Gson().fromJson(res,Products::class.java)
                Log.e("TAG", "onActivityResult: ${Gson().toJson(product)}", )
               // if (isConnected)
                 //   viewModel.addProduct(product)

            }
        }
    }


    override fun deleteProduct(productID: String) {
        if (isConnected)
            viewModel.deleteProductByID(productID)
    }

    override fun showDetails(product: String) {
       val intent = Intent(this,ProductDetail::class.java)
        intent.putExtra("product",product)
        startActivity(intent)
    }

}