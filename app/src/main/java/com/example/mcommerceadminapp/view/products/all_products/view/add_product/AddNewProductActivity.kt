package com.example.mcommerceadminapp.view.products.all_products.view.add_product

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mcommerceadminapp.databinding.ActivityAddNewProductBinding
import java.io.ByteArrayOutputStream
import java.io.IOException


class AddNewProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNewProductBinding
    private lateinit var selectedImageBitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.confirmAddAddress.setOnClickListener {
//            if (isValid()) {
//                val intent = Intent()
//                intent.putExtra("title", binding.titleEditText.text.toString())
//                intent.putExtra("vendor", binding.vendorEditText.text.toString())
//                intent.putExtra("product_type", binding.productTypeEditText.text.toString())
//
//                setResult(2, intent)
//                finish()
//            }
//        }

        binding.chooseImageTxt.setOnClickListener {
            imageChooser()

        }
    }

    private fun imageChooser() {
        val i = Intent()
        i.type = "image/*"
        i.action = Intent.ACTION_GET_CONTENT
        launchSomeActivity.launch(i)
    }

    private var launchSomeActivity = registerForActivityResult(
        StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode
            == RESULT_OK
        ) {
            val data = result.data
            // do your operation from here....
            if (data != null
                && data.data != null
            ) {
                val selectedImageUri = data.data

                try {
                    selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                        this.contentResolver,
                        selectedImageUri
                    )
                } catch (e: IOException) {
                    e.printStackTrace()
                }
//                binding.productImage.setImageBitmap(
//                    selectedImageBitmap
//                )
                binding.chooseImageTxt.visibility = View.INVISIBLE
                val byteArrayOutputStream = ByteArrayOutputStream()
                selectedImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
                val encoded: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
                val image = Base64.decode(encoded, Base64.DEFAULT)
                Glide.with(this)
                    .asBitmap()
                    .load(image)
                    .into(binding.productImage)
            }
        }
    }


    private fun isValid(): Boolean {

        var res = true
        if (binding.titleEditText.text.toString().isEmpty()) {
            binding.titleEditText.error = "not valid"
            res = false
        }
//        if(binding.productTypeEditText.text.toString().isEmpty()){
//            binding.productTypeEditText.error = "not valid"
//            res = false
//        }
//        if(binding.vendorEditText.text.toString().isEmpty()){
//            binding.vendorEditText.error = "not valid"
//            res = false
//        }

        return res

    }

}