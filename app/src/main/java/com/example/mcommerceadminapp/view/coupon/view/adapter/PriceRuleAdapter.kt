package com.example.mcommerceadminapp.view.coupon.view.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.mcommerceadminapp.databinding.ItemPriceRuleBinding
import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PriceRuleAdapter ( var listner : OnClickListner) : RecyclerView.Adapter<PriceRuleAdapter.ViewHolder>(){
    var priceRuleList: ArrayList<PriceRules> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPriceRuleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = priceRuleList[position]
        holder.binding.apply {
            titlePriceRuleText.text = currentItem.title
            limitPriceRuleText.text = currentItem.usageLimit
            valuePriceRuleText.text = currentItem.value
            val spf = SimpleDateFormat("yyyy-MM-dd")
           val startDate = spf.format(spf.parse(currentItem.startsAt))
           startDateText.text = startDate
            val endDate = spf.format(spf.parse(currentItem.endsAt))
            endDateText.text = endDate

        }
        holder.binding.cardPriceRule.setOnClickListener {
            listner.onClick(currentItem.id,"")
        }
        holder.binding.deleteImag.setOnClickListener {
            listner.onClick(currentItem.id,"DELETE")
            priceRuleList.remove(currentItem)
            notifyDataSetChanged()
        }
        holder.binding.editImag.setOnClickListener {
            listner.onClickEdit(currentItem,"EDIT")
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = priceRuleList.count()


    @SuppressLint("NotifyDataSetChanged")
    fun setData(productList: ArrayList<PriceRules>){
        this.priceRuleList = productList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemPriceRuleBinding): RecyclerView.ViewHolder(binding.root)
}