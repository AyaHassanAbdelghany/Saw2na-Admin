package com.example.mcommerceadminapp.view.products.product_detail.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mcommerceadminapp.databinding.ActivityProductDetailBinding
import com.example.mcommerceadminapp.model.Keys
import com.example.mcommerceadminapp.model.remote_source.products.ProductsRemoteSource
import com.example.mcommerceadminapp.model.shopify_repository.products.ProductsRepo
import com.example.mcommerceadminapp.pojo.products.Variants
import com.example.mcommerceapp.view.ui.product_detail.ImageSlideAdapter
import com.example.mcommerceadminapp.view.products.product_detail.adapter.ColorAdapter
import com.example.mcommerceapp.view.ui.product_detail.adapter.OnClickListener
import com.example.mcommerceadminapp.view.products.product_detail.adapter.SizeAdapter
import com.example.mcommerceadminapp.view.products.product_detail.viewmodel.ProductDetailVM
import com.example.mcommerceadminapp.view.products.product_detail.viewmodelfactory.ProductDetailVMFactory


class ProductDetail : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityProductDetailBinding
    private lateinit var imageSliderPager: ImageSlideAdapter
    private lateinit var sizeAdapter: SizeAdapter
    lateinit var colorAdapter: ColorAdapter
    private lateinit var detailVM: ProductDetailVM
    private lateinit var detailVMFactory: ProductDetailVMFactory

    private lateinit var color: String
    private lateinit var size: String
    private lateinit var image: String
    private var id: Long = -1
    private lateinit var variant: ArrayList<Variants>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        detailVMFactory =
            ProductDetailVMFactory(ProductsRepo.getInstance(ProductsRemoteSource.getInstance()))
        detailVM = ViewModelProvider(this, detailVMFactory)[ProductDetailVM::class.java]

        val intent = intent.getStringExtra("PRODUCTS_ID")

        Log.e("Product Details : ", intent.toString())

        if (intent != null) {
            detailVM.getProductDetail(intent)
        }
        detailVM.productDetail.observe(this) {

            binding.toolbar.title = it.title

            binding.contentDetail.ProductPriceTxt.text = "${
                it.variants[0].price
            }"
            binding.contentDetail.ProductRating.rating =
                (it.variants[0].inventoryQuantity)!!.toFloat()
            imageSliderPager = ImageSlideAdapter(this, it.images)
            binding.viewPagerMain.adapter = imageSliderPager
            binding.indicator.setViewPager(binding.viewPagerMain)


            variant = it.variants
            color = variant[0].option2!!
            size = variant[0].option1!!
            image = it.image?.src!!

            sizeAdapter = SizeAdapter(this, this)
            binding.contentDetail.sizeRecycleView.adapter = sizeAdapter
            sizeAdapter.setSizeList(it.variants)
            binding.contentDetail.sizeRecycleView.adapter = sizeAdapter

            colorAdapter = ColorAdapter(this, this)
            binding.contentDetail.colorRecycleView.adapter = colorAdapter

            val set = hashSetOf<String>()
            it.variants.forEach { color ->
                set.add(color.option2!!)
            }
            colorAdapter.setColorList(set)

            binding.contentDetail.readMore.text = it.bodyHtml

            binding.contentDetail.card1.reviewerNameTxt.text = Keys.REVIEWS[0].name
            binding.contentDetail.card1.reviewerDateTxt.text = Keys.REVIEWS[0].date
            binding.contentDetail.card1.reviewerRaring.rating = Keys.REVIEWS[0].rate
            binding.contentDetail.card1.reviewerDescTxt.text = Keys.REVIEWS[0].desc

            binding.contentDetail.card2.reviewerNameTxt.text = Keys.REVIEWS[1].name
            binding.contentDetail.card2.reviewerDateTxt.text = Keys.REVIEWS[1].date
            binding.contentDetail.card2.reviewerRaring.rating = Keys.REVIEWS[1].rate
            binding.contentDetail.card2.reviewerDescTxt.text = Keys.REVIEWS[1].desc
        }
    }

    override fun onClickColor(color: String) {
        this.color = color
    }

    override fun onClickSize(size: String) {
        this.size = size
    }

    private fun getVariant(variant: ArrayList<Variants>, color: String, size: String): Long {
        variant.forEach {
            if (it.option1 == size && it.option2 == color) {
                return it.id!!
            }
        }
        return variant[0].id!!
    }
}