package com.example.mcommerceadminapp.coupon.data_source


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mcommerceadminapp.model.shopify_repository.coupon.CouponRepo
import com.example.mcommerceadminapp.pojo.coupon.discount_code.DiscountCodes
import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules
import getOrAwaitValue
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [30])
@RunWith(AndroidJUnit4::class)
class CouponRepoTest :TestCase(){

    private lateinit var couponRepo : CouponRepo
    private lateinit var couponFakeDataSource : CouponFakeDataSource

    private val pricRuleActual = PriceRules(title = "SUMMERSALE10OFF")
    private val allPriceRuleActual = listOf(pricRuleActual)

    private val pricRuleExpected = PriceRules(title = "SUMMERSALE10OFF")
    private val allPriceRuleExpected = listOf(pricRuleExpected)

    private val discountCodeActual = DiscountCodes(code = "aya")
    private val allDiscountCodeActual = listOf(discountCodeActual)

    private val discountCodeExpected = DiscountCodes(code = "aya")
    private val allDiscountCodeExpected = listOf(discountCodeExpected)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createRepository() {
        couponFakeDataSource =  CouponFakeDataSource.getInstance()
        couponRepo = CouponRepo.getInstance(
           couponFakeDataSource
    )
        couponFakeDataSource.setPriceRule(allPriceRuleActual.toMutableList())
        couponFakeDataSource.setDiscountCode(allDiscountCodeActual.toMutableList())

    }


   @Test
   fun getAllPriceRules_postValueInAllPriceRules()  =  runBlocking {
    //Given repo
       couponRepo.getAllPriceRules()
    // Then
       val value = CouponRepo.allPriceRules.getOrAwaitValue()
      assertEquals((allPriceRuleExpected.get(0).title),value.get(0).title)
   }

    @Test
    fun getAllDiscountCode_priceRuleID_postValueInAllDiscountCode()  =  runBlocking {
        //Given repo
        couponRepo.getAllDiscountCode("992172277898")
        // Then
        val value = CouponRepo.allDiscountCode.getOrAwaitValue()
        assertEquals((allDiscountCodeExpected.get(0).code),value.get(0).code)
    }
}