package com.example.mcommerceadminapp.view.Coupon.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mcommerceadminapp.databinding.ActivityAddEditPriceRuleBinding
import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules
import java.text.SimpleDateFormat
import java.util.*


class AddEditDiscountCodeActivity :AppCompatActivity() {


    private lateinit var binding: ActivityAddEditPriceRuleBinding
    private  var startDate :String = ""
    private lateinit var type :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddEditPriceRuleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        type = intent.getStringExtra("TYPE").toString()
        when (type) {
            "EDIT" -> {
                binding.addEditBtn.text = "Edit"
                binding.titleText.setText(intent.getStringExtra("title"))
                binding.valueText.setText(intent.getStringExtra("value"))
                binding.usageLimitText.setText(intent.getStringExtra("usageLimit"))
                val spf = SimpleDateFormat("yyyy-MM-dd")
                startDate = spf.format(spf.parse(intent.getStringExtra("startAt")))
            }
        }
        val today = Calendar.getInstance()
        binding.datePicker.init(
            today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)

        ) { view, year, month, day ->
            val month = month + 1
            var cal = Calendar.getInstance()
            cal.set(year, month, day)
            val spf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            startDate = spf.format(cal.time)
        }

        binding.addEditBtn.setOnClickListener {
            when (type) {
                "EDIT" -> {
                    val intentEdit = Intent()
                    intentEdit.putExtra("title", binding.titleText.text.toString())
                    intentEdit.putExtra("value", binding.valueText.text.toString())
                    intentEdit.putExtra("usageLimit", binding.usageLimitText.text.toString())
                    intentEdit.putExtra("startAt", intent.getStringExtra("startAt"))
                    intentEdit.putExtra("id", intent.getStringExtra("id"))

                    setResult(2, intentEdit)
                    finish()
                }
                "ADD" -> {
                    if (binding.titleText.text.isNotEmpty()
                        && binding.valueText.text.isNotEmpty()
                        && binding.usageLimitText.text.isNotEmpty()
                        && startDate.isNotEmpty()
                    ) {

                        val intent = Intent()
                        intent.putExtra("title", binding.titleText.text.toString())
                        intent.putExtra("value", binding.valueText.text.toString())
                        intent.putExtra("usageLimit", binding.usageLimitText.text.toString())
                        intent.putExtra("startAt", startDate)
                        setResult(1, intent)
                        finish()
                    }
                }
            }
        }
    }
}