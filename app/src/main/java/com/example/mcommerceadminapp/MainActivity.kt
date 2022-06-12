package com.example.mcommerceadminapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.mcommerceadminapp.databinding.ActivityMainBinding
import com.example.mcommerceadminapp.model.remote_source.coupon.CouponRemoteSource
import com.example.mcommerceadminapp.model.shopify_repository.coupon.CouponRepo
import com.example.mcommerceadminapp.view.Coupon.viewmodel.CouponViewModel
import com.example.mcommerceadminapp.view.Coupon.viewmodel.CouponViewModelFactory

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var couponVM: CouponViewModel
    private lateinit var couponVMFactory: CouponViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
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

    }
}