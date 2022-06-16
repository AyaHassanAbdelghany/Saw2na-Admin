package com.example.mcommerceadminapp

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Transition
import android.util.Log
import android.view.Window
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Explode
import com.example.mcommerceadminapp.databinding.ActivityMainBinding
import com.example.mcommerceadminapp.model.remote_source.coupon.CouponRemoteSource
import com.example.mcommerceadminapp.model.shopify_repository.coupon.CouponRepo
import com.example.mcommerceadminapp.view.Coupon.viewmodel.CouponViewModel
import com.example.mcommerceadminapp.view.Coupon.viewmodel.CouponViewModelFactory
import com.example.mcommerceadminapp.view.inventory.view.InventoryActivity
import com.example.mcommerceadminapp.view.products.all_products.view.ProductsActivity
import com.example.mcommerceadminapp.view.Coupon.view.PriceRuleActivity

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.discountsBtn.setOnClickListener{
            startActivity(Intent(this, PriceRuleActivity::class.java))
        }

        binding.productsBtn.setOnClickListener {
            startActivity(Intent(this,ProductsActivity::class.java),ActivityOptions.makeSceneTransitionAnimation(this,binding.productsBtn , " root").toBundle())
        }

        binding.investoryBtn.setOnClickListener {
            startActivity(Intent(this, InventoryActivity::class.java))
        }

    }
}