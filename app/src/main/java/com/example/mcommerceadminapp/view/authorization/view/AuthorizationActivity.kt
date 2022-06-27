package com.example.mcommerceadminapp.view.authorization.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mcommerceadminapp.MainActivity
import com.example.mcommerceadminapp.databinding.ActivityAuthorizationBinding
import com.example.mcommerceadminapp.view.authorization.view_model.AuthorizationViewModel
import com.example.mcommerceadminapp.view.products.all_products.view_model.ProductsViewModel

class AuthorizationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthorizationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences = getSharedPreferences("user", 0)
        if(sharedPreferences.getBoolean("loggedIn", false)){
            finish()
            startActivity(Intent(this,MainActivity::class.java))
        }


        val viewModel =  ViewModelProvider(this)[AuthorizationViewModel::class.java]

        viewModel.result.observe(this){
            if (it){
                sharedPreferences.edit().putBoolean("loggedIn", true).apply()
                finish()
                startActivity(Intent(this,MainActivity::class.java))
            }else{
                binding.codeEditText.error = "Wrong Code"
            }
        }

        binding.loginButton.setOnClickListener {
            viewModel.checkAuthorization(binding.codeEditText.text.toString())
        }


    }
}