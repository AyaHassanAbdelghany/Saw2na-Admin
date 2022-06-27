package com.example.mcommerceadminapp.view.coupon.view.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.mcommerceadminapp.R
import com.example.mcommerceadminapp.databinding.ItemPriceRuleBinding
import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class PriceRuleAdapter(var listner: OnClickListner) :
    RecyclerView.Adapter<PriceRuleAdapter.ViewHolder>() {
    var priceRuleList: ArrayList<PriceRules> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPriceRuleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        if (currentItem.startsAt?.let {
                currentItem.endsAt?.let { it1 ->
                    checkCurrentTimeIsBetweenGivenString(
                        formatDate(it),
                        formatDate(it1)
                    )
                }
            } != true) {
            holder.itemView.setBackgroundResource(R.drawable.card_border_colored)
        }

        holder.binding.cardPriceRule.setOnClickListener {
            listner.onClick(currentItem, "")
        }
        holder.binding.deleteImag.setOnClickListener {
            listner.onClick(currentItem, "DELETE")
        }
        holder.binding.editImag.setOnClickListener {
            listner.onClickEdit(currentItem, "EDIT")
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = priceRuleList.count()


    @SuppressLint("NotifyDataSetChanged")
    fun setData(productList: ArrayList<PriceRules>) {
        this.priceRuleList = productList
        notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatDate(date: String): String {
        val odt = OffsetDateTime.parse(date)
        val dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        println("ISA : ${dtf.format(odt)}")
        return dtf.format(odt)
    }

    private fun checkCurrentTimeIsBetweenGivenString(s1: String, s2: String): Boolean {

        println("Testing: $s1")
        println("Testing: $s2")
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")

        val date1: Date = simpleDateFormat.parse(s1)

        val date2: Date = simpleDateFormat.parse(s2)

        println("TestingStart: $date1")
        println("TestingEnd: $date2")
        val d = Date()

        val s3 = simpleDateFormat.format(d)

        val date3 = simpleDateFormat.parse(s3)
        println("TestingNow: $date3")
        println("TestingCheck: ${date3 >= date1 && date3 <= date2}")
        return date3 >= date1 && date3 <= date2
    }


    class ViewHolder(val binding: ItemPriceRuleBinding) : RecyclerView.ViewHolder(binding.root)
}