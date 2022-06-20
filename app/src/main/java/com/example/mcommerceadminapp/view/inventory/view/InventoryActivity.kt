package com.example.mcommerceadminapp.view.inventory.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.AutoTransition
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.mcommerceadminapp.databinding.ActivityInventoryBinding
import com.example.mcommerceadminapp.model.remote_source.products.ProductsRemoteSource
import com.example.mcommerceadminapp.model.shopify_repository.products.ProductsRepo
import com.example.mcommerceadminapp.network.MyConnectivityManager
import com.example.mcommerceadminapp.view.inventory.view.adapter.InventoryAdapter
import com.example.mcommerceadminapp.view.inventory.view.adapter.InventoryCommunicator
import com.example.mcommerceadminapp.view.inventory.view_model.InventoryViewModel
import com.example.mcommerceadminapp.view.inventory.view_model.factory.InventoryViewModelFactory

class InventoryActivity : AppCompatActivity() ,InventoryCommunicator{

    lateinit var binding: ActivityInventoryBinding
    private lateinit var viewModel: InventoryViewModel
    private lateinit var adapter:InventoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInventoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = InventoryAdapter(this,this)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL

        binding.recycleViewInventory.layoutManager = llm
        binding.recycleViewInventory.adapter = adapter

        val factory = InventoryViewModelFactory(ProductsRepo.getInstance(ProductsRemoteSource.getInstance()))

        viewModel = ViewModelProvider(this,factory)[InventoryViewModel::class.java]


        MyConnectivityManager.state.observe(this) {

            if (it) {
                Toast.makeText(this, "Connection is restored", Toast.LENGTH_SHORT).show()
                viewModel.getAllProduct()
                binding.noNetworkLayout.visibility = View.INVISIBLE
                TransitionManager.beginDelayedTransition( binding.noNetworkLayout, AutoTransition())
                binding.loadingProgressBar.visibility = View.VISIBLE
                binding.recycleViewInventory.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "Connection is lost", Toast.LENGTH_SHORT).show()
                binding.noNetworkLayout.visibility = View.VISIBLE
                TransitionManager.beginDelayedTransition( binding.noNetworkLayout, AutoTransition())
                binding.loadingProgressBar.visibility = View.INVISIBLE
                binding.recycleViewInventory.visibility = View.INVISIBLE
            }
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.products.removeObservers(this)
        viewModel.products.observe(this){
            adapter.setData(it)
            TransitionManager.beginDelayedTransition( binding.recycleViewInventory, Slide())
            binding.loadingProgressBar.visibility = View.INVISIBLE

        }
    }

    override fun setInventoryLevel(inventoryID:Long,amount:Int) {
        viewModel.setInventoryLevel(inventoryID,amount)
    }

    override fun deleteProduct(addressID: String) {
    }
}