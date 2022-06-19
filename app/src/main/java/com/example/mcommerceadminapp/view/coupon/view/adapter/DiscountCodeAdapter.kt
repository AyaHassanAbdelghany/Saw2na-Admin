package com.example.mcommerceadminapp.view.coupon.view.adapter

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.mcommerceadminapp.R
import com.example.mcommerceadminapp.databinding.ItemDiscountCodeBinding
import com.example.mcommerceadminapp.pojo.coupon.discount_code.DiscountCodes

class DiscountCodeAdapter (var listner : OnClickListner) : RecyclerView.Adapter<DiscountCodeAdapter.ViewHolder>(){
    var discountCodeList: ArrayList<DiscountCodes> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDiscountCodeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = discountCodeList[position]
        holder.binding.apply {

            codeText.setText( currentItem.code)
            usageCountText.text = currentItem.usageCount.toString()

        }
        holder.binding.deleteImag.setOnClickListener {
            listner.onClick(currentItem.id,"DELETE")
            discountCodeList.remove(currentItem)
            notifyDataSetChanged()
        }

        holder.binding.editImag.setOnClickListener {
            holder.binding.codeText.isEnabled = true
            holder.binding.editImag.visibility = ImageView.GONE
            holder.binding.codeText.isPressed = true
            holder.binding.confirmImag.visibility = ImageView.VISIBLE
        }
        holder.binding.confirmImag.setOnClickListener{
            currentItem.code = holder.binding.codeText.text.toString()
            holder.binding.codeText.isEnabled = false
            holder.binding.codeText.isPressed = false
            holder.binding.editImag.visibility = ImageView.VISIBLE
            holder.binding.confirmImag.visibility = ImageView.GONE
            listner.onClickEdit(currentItem,"EDIT")
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