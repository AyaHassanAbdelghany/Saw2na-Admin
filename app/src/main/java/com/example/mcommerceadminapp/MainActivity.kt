package com.example.mcommerceadminapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mcommerceadminapp.databinding.ActivityMainBinding
import com.example.mcommerceadminapp.view.coupon.view.PriceRuleActivity

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.discountsBtn.setOnClickListener{
            startActivity(Intent(this, PriceRuleActivity::class.java))
        }

    }
}