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

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var couponVM: CouponViewModel
    private lateinit var couponVMFactory: CouponViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {

        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

            // set an exit transition
         //      exitTransition = Explode()


        }

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        couponVMFactory = CouponViewModelFactory(
            CouponRepo.getInstance(CouponRemoteSource()),
        )
        couponVM = ViewModelProvider(this, couponVMFactory)[CouponViewModel::class.java]

        couponVM.allPriceRules.observe(this){
            Log.e("res",it.toString())
        }
        binding.discountsBtn.setOnClickListener(){
            couponVM.getAllPriceRules()

        }

        binding.productsBtn.setOnClickListener {
            startActivity(Intent(this,ProductsActivity::class.java),ActivityOptions.makeSceneTransitionAnimation(this,binding.productsBtn , " root").toBundle())
        }

        binding.investoryBtn.setOnClickListener {
            startActivity(Intent(this, InventoryActivity::class.java))
        }

    }
}