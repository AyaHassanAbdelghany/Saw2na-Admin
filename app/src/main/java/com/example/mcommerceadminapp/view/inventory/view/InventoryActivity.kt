package com.example.mcommerceadminapp.view.inventory.view

import android.animation.LayoutTransition
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Transition
import android.view.View
import android.view.Window
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.AutoTransition
import androidx.transition.Explode
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.mcommerceadminapp.databinding.ActivityInventoryBinding
import com.example.mcommerceadminapp.model.remote_source.products.ProductsRemoteSource
import com.example.mcommerceadminapp.model.shopify_repository.products.ProductsRepo
import com.example.mcommerceadminapp.view.inventory.view.adapter.InventoryAdapter
import com.example.mcommerceadminapp.view.inventory.view.adapter.InventoryCommunicator
import com.example.mcommerceadminapp.view.inventory.view_model.InventoryViewModel
import com.example.mcommerceadminapp.view.inventory.view_model.factory.InventoryViewModelFactory
import com.example.mcommerceadminapp.view.products.all_products.view_model.ProductsViewModel
import com.example.mcommerceadminapp.view.products.all_products.view_model.factory.ProductsViewModelFactory

class InventoryActivity : AppCompatActivity() ,InventoryCommunicator{

    lateinit var binding: ActivityInventoryBinding
    private lateinit var viewModel: InventoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInventoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = InventoryAdapter(this,this)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL

        binding.recycleViewInventory.layoutManager = llm
        binding.recycleViewInventory.adapter = adapter

        val factory = InventoryViewModelFactory(ProductsRepo.getInstance(ProductsRemoteSource.getInstance()))

        viewModel = ViewModelProvider(this,factory)[InventoryViewModel::class.java]

        viewModel.products.observe(this){
            adapter.setData(it)
            TransitionManager.beginDelayedTransition( binding.recycleViewInventory, Slide())
            binding.loadingProgressBar.visibility = View.INVISIBLE

        }


        viewModel.getAllProduct()


    }

    override fun setDefaultAddress(addressID: String) {
    }

    override fun deleteProduct(addressID: String) {
    }
}