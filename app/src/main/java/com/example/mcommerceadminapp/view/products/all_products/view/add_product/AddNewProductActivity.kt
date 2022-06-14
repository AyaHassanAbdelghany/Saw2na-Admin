package com.example.mcommerceadminapp.view.products.all_products.view.add_product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mcommerceadminapp.databinding.ActivityAddNewProductBinding

class AddNewProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNewProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.confirmAddAddress.setOnClickListener {
            if (isValid()) {
                val intent = Intent()
                intent.putExtra("title", binding.titleEditText.text.toString())
                intent.putExtra("vendor", binding.vendorEditText.text.toString())
                intent.putExtra("product_type", binding.productTypeEditText.text.toString())

                setResult(2, intent)
                finish()
            }
        }

    }


    private fun isValid(): Boolean {

        var res = true
        if(binding.titleEditText.text.toString().isEmpty()){
            binding.titleEditText.error = "not valid"
            res = false
        }
        if(binding.productTypeEditText.text.toString().isEmpty()){
            binding.productTypeEditText.error = "not valid"
            res = false
        }
        if(binding.vendorEditText.text.toString().isEmpty()){
            binding.vendorEditText.error = "not valid"
            res = false
        }

        return res

    }

}