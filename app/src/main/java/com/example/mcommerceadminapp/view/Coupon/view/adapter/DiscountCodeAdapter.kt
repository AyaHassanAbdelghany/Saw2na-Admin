package com.example.mcommerceadminapp.view.Coupon.view.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.mcommerceadminapp.databinding.ItemDiscountCodeBinding
import com.example.mcommerceadminapp.databinding.ItemPriceRuleBinding
import com.example.mcommerceadminapp.pojo.coupon.discount_code.DiscountCodes
import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules

class DiscountCodeAdapter (var listner : OnClickListner) : RecyclerView.Adapter<DiscountCodeAdapter.ViewHolder>(){
    var discountCodeList: ArrayList<DiscountCodes> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDiscountCodeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = discountCodeList[position]
        holder.binding.apply {

            codeText.text = currentItem.code
            usageCountText.text = currentItem.usageCount.toString()

        }
        holder.binding.deleteImag.setOnClickListener {
            listner.onClick(currentItem.id,"DELETE")
            discountCodeList.remove(currentItem)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = discountCodeList.count()


    @SuppressLint("NotifyDataSetChanged")
    fun setData(discountCodeList: ArrayList<DiscountCodes>){
        this.discountCodeList = discountCodeList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemDiscountCodeBinding): RecyclerView.ViewHolder(binding.root)
}