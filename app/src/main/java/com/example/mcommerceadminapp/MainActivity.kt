package com.example.mcommerceadminapp

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mcommerceadminapp.databinding.ActivityMainBinding
import com.example.mcommerceadminapp.view.coupon.view.PriceRuleActivity
import com.example.mcommerceadminapp.view.coupon.viewmodel.CouponViewModel
import com.example.mcommerceadminapp.view.coupon.viewmodel.CouponViewModelFactory
import com.example.mcommerceadminapp.view.inventory.view.InventoryActivity
import com.example.mcommerceadminapp.view.products.all_products.view.ProductsActivity

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var couponVM: CouponViewModel
    private lateinit var couponVMFactory: CouponViewModelFactory

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