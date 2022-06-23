package com.example.mcommerceadminapp.view.coupon.view.price_rule

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mcommerceadminapp.databinding.ActivityAddEditPriceRuleBinding
import java.text.SimpleDateFormat
import java.util.*


class AddEditPriceRuleActivity :AppCompatActivity() {


    private lateinit var binding: ActivityAddEditPriceRuleBinding
    private  var startDate :String = ""
    private  var endDate :String = ""
    private lateinit var type :String
    val startToday = Calendar.getInstance()
    val endToday = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddEditPriceRuleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        type = intent.getStringExtra("TYPE").toString()

        when (type) {
            "EDIT" -> {
                binding.priceRuleTxt.text = "Edit " + binding.priceRuleTxt.text
                binding.addEditBtn.text = "Edit"
                binding.titleText.setText(intent.getStringExtra("title"))
                binding.valueText.setText(intent.getStringExtra("value"))
                binding.usageLimitText.setText(intent.getStringExtra("usageLimit"))

                val spf = SimpleDateFormat("yyyy-MM-dd")
                startDate = spf.format(spf.parse(intent.getStringExtra("startAt")))
                var date = startDate.split("-")
                startToday.set(date[0].toInt(),date[1].toInt()-1,date[2].toInt())

                endDate = spf.format(spf.parse(intent.getStringExtra("endAt")))
               date = endDate.split("-")
                endToday.set(date[0].toInt(),date[1].toInt()-1,date[2].toInt())
            }
            else ->{
                binding.priceRuleTxt.text = "Add " + binding.priceRuleTxt.text

            }
        }

      setEndDatePicker()
      setStartDatePicker()

        binding.addEditBtn.setOnClickListener {
            when (type) {
                "EDIT" -> {
                    if (inputIsValid()) {
                        val intentEdit = Intent()
                        intentEdit.putExtra("title", binding.titleText.text.toString())
                        intentEdit.putExtra("value", binding.valueText.text.toString())
                        intentEdit.putExtra("usageLimit", binding.usageLimitText.text.toString())
                        intentEdit.putExtra("startAt", startDate)
                        intentEdit.putExtra("endAt", endDate)

                        intentEdit.putExtra("id", intent.getStringExtra("id"))

                        setResult(2, intentEdit)
                        finish()
                    }
                }
                "ADD" -> {
                    if (inputIsValid()) {
                        val intent = Intent()
                        intent.putExtra("title", binding.titleText.text.toString())
                        intent.putExtra("value", "-"+binding.valueText.text.toString())
                        intent.putExtra("usageLimit", binding.usageLimitText.text.toString())
                        intent.putExtra("startAt", startDate)
                        intent.putExtra("endAt", endDate)

                        setResult(1, intent)
                        finish()
                    }
                }
            }
        }
        binding.backImage.setOnClickListener(){
            finish()
        }
    }

    private fun inputIsValid() :Boolean{
        var flag = true
        if (binding.titleText.text.isEmpty()){
                binding.titleText.error = "not valid"
                flag = false
            }
            if( binding.valueText.text.isEmpty()){
                    binding.valueText.error = "not valid"
                    flag = false
            }
            if( binding.usageLimitText.text.isEmpty())
            {
                binding.usageLimitText.error = "not valid"
                flag = false
            }
           if( startDate.isEmpty())
           {
               binding.startDateTxt.error = "not valid"
               flag = false
           }
           if( endDate.isEmpty()){
               binding.endDateTxt.error = "not valid"
               flag = false
           }
        return  flag
    }
    private fun setEndDatePicker(){
        binding.endDatePicker.init(
            endToday.get(Calendar.YEAR), endToday.get(Calendar.MONTH),
            endToday.get(Calendar.DAY_OF_MONTH)
        )
        { view, year, month, day ->
            val month = month
            var cal = Calendar.getInstance()
            cal.set(year, month, day)
            val spf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            endDate = spf.format(cal.time)
        }
    }
    private  fun setStartDatePicker (){

        binding.startDatePicker.init(
            startToday.get(Calendar.YEAR), startToday.get(Calendar.MONTH),
            startToday.get(Calendar.DAY_OF_MONTH)
        )
        { view, year, month, day ->
            val month = month
            var cal = Calendar.getInstance()
            cal.set(year, month, day)
            val spf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            startDate = spf.format(cal.time)
        }
    }
}