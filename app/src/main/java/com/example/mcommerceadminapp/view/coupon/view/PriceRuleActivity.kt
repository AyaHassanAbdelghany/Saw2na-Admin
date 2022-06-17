package com.example.mcommerceadminapp.view.coupon.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mcommerceadminapp.databinding.ActivityPriceRuleBinding
import com.example.mcommerceadminapp.model.remote_source.coupon.CouponRemoteSource
import com.example.mcommerceadminapp.model.shopify_repository.coupon.CouponRepo
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
        couponVM.getAllPriceRules()
    }

    override fun onResume() {
        super.onResume()
        couponVM.allPriceRules.removeObservers(this)
        couponVM.allPriceRules.observe(this){
            priceRuleAdapter.setData(it)
            binding.priceRuleRecycler.adapter = priceRuleAdapter
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
                couponVM.updatePriceRule(data.getStringExtra("id").toString(),priceRule)
            }
        }
    }

    private fun init(){
        priceRuleAdapter = PriceRuleAdapter( this)
        binding.priceRuleRecycler.adapter = priceRuleAdapter
        couponVMFactory = CouponViewModelFactory(
            CouponRepo.getInstance(CouponRemoteSource()),
        )
        couponVM = ViewModelProvider(this, couponVMFactory)[CouponViewModel::class.java]

    }
    override fun onClick(id: String?,type:String) {
        if(type == "DELETE") {
            couponVM.deletePriceRuleID(id.toString())
        }
        else{
            val intent = Intent(this, DiscountCodeActivity::class.java)
        intent.putExtra("PRICERULE_ID", id)
        startActivity(intent)
        }

    }

    override fun onClickEdit(priceRule: PriceRules, type: String) {

        val intent = Intent(this,AddEditPriceRuleActivity::class.java)
        intent.putExtra("TYPE",type)
        intent.putExtra("title",priceRule.title)
        intent.putExtra("value",priceRule.value)
        intent.putExtra("usageLimit",priceRule.usageLimit)
        intent.putExtra("startAt",priceRule.startsAt)
        intent.putExtra("id",priceRule.id)
        startActivityForResult(intent,2)
        }
}