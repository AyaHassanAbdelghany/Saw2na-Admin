package com.example.mcommerceadminapp.view.products.all_products.view.add_product

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mcommerceadminapp.databinding.ActivityAddNewProductBinding
import com.example.mcommerceadminapp.model.remote_source.products.ProductsRemoteSource
import com.example.mcommerceadminapp.model.shopify_repository.products.ProductsRepo
import com.example.mcommerceadminapp.pojo.products.Image
import com.example.mcommerceadminapp.pojo.products.Products
import com.example.mcommerceadminapp.pojo.products.Variants
import com.example.mcommerceadminapp.view.products.all_products.view_model.ProductsViewModel
import com.example.mcommerceadminapp.view.products.all_products.view_model.factory.ProductsViewModelFactory
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.ByteArrayOutputStream
import java.io.IOException


class AddNewProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNewProductBinding
    private lateinit var selectedImageBitmap: Bitmap
    private lateinit var image :ByteArray
    private lateinit var encoded: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory =
            ProductsViewModelFactory(ProductsRepo.getInstance(ProductsRemoteSource.getInstance()))

        val viewModel = ViewModelProvider(this, factory)[ProductsViewModel::class.java]


        binding.submitBtn.setOnClickListener {
            if (isValid()) {
                val products = Products()
                products.title = binding.titleEditText.text.toString()
                products.vendor = binding.vendorEditText.text.toString()
                products.productType = binding.productTypeEditText.text.toString()
                products.bodyHtml = binding.descEditText.text.toString()

                val iv: ImageView = binding.productImage as ImageView
                val bitmap = iv.getDrawable().toBitmap()
                val bos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
                val bb = bos.toByteArray()
                val imageString: String = Base64.encodeToString(bb, Base64.DEFAULT)

                val image = Image()
                image.src = imageString
                products.image = image

                products.variants = ArrayList()
                val variant = Variants()
                variant.option1 = binding.sizeEditText.text.toString()
                variant.option2 = binding.vendorEditText.text.toString()
                variant.price = binding.priceEditText.text.toString()

                products.variants.add(variant)

                viewModel.addProduct(products)
            //    finish()
            }
        }

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

                binding.chooseImageTxt.visibility = View.INVISIBLE
                val byteArrayOutputStream = ByteArrayOutputStream()
                selectedImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
                encoded = Base64.encodeToString(byteArray, Base64.DEFAULT)
                image = Base64.decode(encoded, Base64.DEFAULT)
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
        if(binding.productTypeEditText.text.toString().isEmpty()){
            binding.productTypeEditText.error = "not valid"
            res = false
        }
        if(binding.vendorEditText.text.toString().isEmpty()){
            binding.vendorEditText.error = "not valid"
            res = false
        }
        if(binding.descEditText.text.toString().isEmpty()){
            binding.descEditText.error = "not valid"
            res = false
        }
        if(binding.sizeEditText.text.toString().isEmpty()){
            binding.sizeEditText.error = "not valid"
            res = false
        }
        if(binding.colorEditText.text.toString().isEmpty()){
            binding.colorEditText.error = "not valid"
            res = false
        }
        if(binding.priceEditText.text.toString().isEmpty()){
            binding.priceEditText.error = "not valid"
            res = false
        }
        if (!this::image.isInitialized){
            Toast.makeText(this, "select an image", Toast.LENGTH_SHORT).show()
            res = false
        }
        return res

    }

}