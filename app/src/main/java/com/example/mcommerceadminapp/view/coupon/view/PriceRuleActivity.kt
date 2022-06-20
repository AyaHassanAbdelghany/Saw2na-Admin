package com.example.mcommerceadminapp.view.coupon.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mcommerceadminapp.databinding.ActivityPriceRuleBinding
import com.example.mcommerceadminapp.model.remote_source.coupon.CouponRemoteSource
import com.example.mcommerceadminapp.model.shopify_repository.coupon.CouponRepo
import com.example.mcommerceadminapp.network.MyConnectivityManager
import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules
import com.example.mcommerceadminapp.view.coupon.view.adapter.OnClickListner
import com.example.mcommerceadminapp.view.coupon.view.adapter.PriceRuleAdapter
import com.example.mcommerceadminapp.view.coupon.viewmodel.CouponViewModel
import com.example.mcommerceadminapp.view.coupon.viewmodel.CouponViewModelFactory

class PriceRuleActivity : OnClickListner ,AppCompatActivity() {

    private lateinit var binding: ActivityPriceRuleBinding
    private lateinit var couponVM: CouponViewModel
    private lateinit var couponVMFactory: CouponViewModelFactory
    private lateinit var priceRuleAdapter :PriceRuleAdapter
    private var isConnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPriceRuleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addBtn.setOnClickListener{
            val intent = Intent(this,AddEditPriceRuleActivity::class.java)
            intent.putExtra("TYPE","ADD")
            startActivityForResult(intent,1)

        }
        init()

        MyConnectivityManager.state.observe(this) {

            if (it) {
                Toast.makeText(this, "Connection is restored", Toast.LENGTH_SHORT).show()
                isConnected = true
                couponVM.getAllPriceRules()
                binding.loadingProgressBar.visibility = View.VISIBLE
                binding.noNetworkLayout.visibility = View.INVISIBLE
                binding.loadingProgressBar.visibility = View.VISIBLE
                binding.priceRuleRecycler.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "Connection is lost", Toast.LENGTH_SHORT).show()
                isConnected = false
                binding.noNetworkLayout.visibility = View.VISIBLE
                binding.loadingProgressBar.visibility = View.INVISIBLE
                binding.loadingProgressBar.visibility = View.INVISIBLE
                binding.priceRuleRecycler.visibility = View.INVISIBLE
            }
        }

    }

    override fun onResume() {
        super.onResume()
        couponVM.allPriceRules.removeObservers(this)
        couponVM.allPriceRules.observe(this){
            if(it !=null) {
                binding.loadingProgressBar.visibility = View.INVISIBLE
                priceRuleAdapter.setData(it)
                binding.priceRuleRecycler.adapter = priceRuleAdapter
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1){
            val priceRule = PriceRules()
            if (data != null) {
                priceRule.title = data.getStringExtra("title")
                priceRule.value = data.getStringExtra("value")
                priceRule.usageLimit = data.getStringExtra("usageLimit")
                priceRule.startsAt = data.getStringExtra("startAt")
                priceRule.endsAt = data.getStringExtra("endAt")

                if (isConnected)
                couponVM.createPriceRule(priceRule)
            }
        }

       else if (requestCode == 2){
            val priceRule = PriceRules()
            if (data != null) {
                priceRule.title = data.getStringExtra("title")
                priceRule.value = data.getStringExtra("value")
                priceRule.usageLimit = data.getStringExtra("usageLimit")
                priceRule.startsAt = data.getStringExtra("startAt")
                priceRule.endsAt = data.getStringExtra("endAt")

                if (isConnected)
                couponVM.updatePriceRule(data.getStringExtra("id").toString(),priceRule)
            }
        }
    }

    private fun init(){
        priceRuleAdapter = PriceRuleAdapter( this)
        binding.priceRuleRecycler.adapter = priceRuleAdapter
        couponVMFactory = CouponViewModelFactory(
            CouponRepo.getInstance(CouponRemoteSource.getInstance()),
        )
        couponVM = ViewModelProvider(this, couponVMFactory)[CouponViewModel::class.java]

    }
    override fun onClick(id: String?,type:String) {
        if(type == "DELETE") {
            if (isConnected)
            couponVM.deletePriceRuleID(id.toString())
        }
        else{
            val intent = Intent(this, DiscountCodeActivity::class.java)
        intent.putExtra("PRICERULE_ID", id)
        startActivity(intent)
        }

    }

    override fun <T> onClickEdit(typeObject: T, type: String) {
        val intent = Intent(this,AddEditPriceRuleActivity::class.java)
        val priceRule = typeObject as PriceRules
        intent.putExtra("TYPE",type)
        intent.putExtra("title",priceRule.title)
        intent.putExtra("value",priceRule.value)
        intent.putExtra("usageLimit",priceRule.usageLimit)
        intent.putExtra("startAt",priceRule.startsAt)
        intent.putExtra("endAt",priceRule.endsAt)

        intent.putExtra("id",priceRule.id)
        startActivityForResult(intent,2)
    }
}


