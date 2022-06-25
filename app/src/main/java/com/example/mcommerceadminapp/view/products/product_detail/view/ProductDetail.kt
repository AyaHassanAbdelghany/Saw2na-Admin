package com.example.mcommerceadminapp.view.products.product_detail.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mcommerceadminapp.databinding.ActivityProductDetailBinding
import com.example.mcommerceadminapp.model.Keys
import com.example.mcommerceadminapp.pojo.products.Products
import com.example.mcommerceapp.view.ui.product_detail.ImageSlideAdapter
import com.example.mcommerceapp.view.ui.product_detail.adapter.ColorAdapter
import com.example.mcommerceapp.view.ui.product_detail.adapter.OnClickListener
import com.example.mcommerceapp.view.ui.product_detail.adapter.SizeAdapter
import com.google.gson.Gson


class ProductDetail : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityProductDetailBinding
    private lateinit var imageSliderPager: ImageSlideAdapter
    private lateinit var sizeAdapter: SizeAdapter
    lateinit var colorAdapter: ColorAdapter

    private lateinit var color: String
    private lateinit var size: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val intent = intent.getStringExtra("product")

        val gson = Gson()
        val product: Products = gson.fromJson(intent, Products::class.java)

        binding.toolbar.title = product.title

        binding.contentDetail.ProductPriceTxt.text = "${
            product.variants[0].price
        }"
        binding.contentDetail.ProductRating.rating =
            (product.variants[0].inventoryQuantity)!!.toFloat()
        imageSliderPager = ImageSlideAdapter(this, product.images)
        binding.viewPagerMain.adapter = imageSliderPager
        binding.indicator.setViewPager(binding.viewPagerMain)


        sizeAdapter = SizeAdapter(this, this)
        binding.contentDetail.sizeRecycleView.adapter = sizeAdapter
        sizeAdapter.setSizeList(product.variants)
        binding.contentDetail.sizeRecycleView.adapter = sizeAdapter

        colorAdapter = ColorAdapter(this, this)
        binding.contentDetail.colorRecycleView.adapter = colorAdapter

        val set = hashSetOf<String>()
        product.variants.forEach { color ->
            color.option2?.let { set.add(it) }
        }
        colorAdapter.setColorList(set)

        binding.contentDetail.readMore.text = product.bodyHtml

        binding.contentDetail.card1.reviewerNameTxt.text = Keys.REVIEWS[0].name
        binding.contentDetail.card1.reviewerDateTxt.text = Keys.REVIEWS[0].date
        binding.contentDetail.card1.reviewerRaring.rating = Keys.REVIEWS[0].rate
        binding.contentDetail.card1.reviewerDescTxt.text = Keys.REVIEWS[0].desc

        binding.contentDetail.card2.reviewerNameTxt.text = Keys.REVIEWS[1].name
        binding.contentDetail.card2.reviewerDateTxt.text = Keys.REVIEWS[1].date
        binding.contentDetail.card2.reviewerRaring.rating = Keys.REVIEWS[1].rate
        binding.contentDetail.card2.reviewerDescTxt.text = Keys.REVIEWS[1].desc
    }


    override fun onClickColor(color: String) {
        this.color = color
    }

    override fun onClickSize(size: String) {
        this.size = size
    }

}