package com.example.mcommerceadminapp.view.inventory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mcommerceadminapp.databinding.ActivityInventoryBinding

class InventoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityInventoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInventoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}