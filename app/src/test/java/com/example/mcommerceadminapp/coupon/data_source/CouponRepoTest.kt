package com.example.mcommerceadminapp.coupon.data_source


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mcommerceadminapp.model.remote_source.coupon.CouponRemoteSource
import com.example.mcommerceadminapp.model.shopify_repository.coupon.CouponRepo
import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules
import getOrAwaitValue
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

@Config(sdk = [30])
@RunWith(AndroidJUnit4::class)
class CouponRepoTest :TestCase(){

     private lateinit var couponRepo : CouponRepo
    private lateinit var remotSource : FakeDataSource

    private val pricRuleActual = PriceRules(title = "SUMMERSALE10OFF")
    private val allPriceRuleActual = listOf(pricRuleActual)

    private val pricRuleExpected = PriceRules(title = "SUMMERSALE10OF")
    private val allPriceRuleExpected = listOf(pricRuleExpected)

    private val discountCodeActual = DiscountCodes(code = "aya")
    private val allDiscountCodeActual = listOf(discountCodeActual)

    private val discountCodeExpected = DiscountCodes(code = "ayaa")
    private val allDiscountCodeExpected = listOf(discountCodeExpected)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createRepository() {
        remotSource =  FakeDataSource.getInstance()
        couponRepo = CouponRepo.getInstance(
           remotSource
    )
        remotSource.setPriceRule(allPriceRuleActual.toMutableList())
        remotSource.setDiscountCode(allDiscountCodeActual.toMutableList())

    }


//   @Test
//   fun getAllPriceRules_postValueInAllPriceRules()  =  runBlocking {
//    //Given repo
//       couponRepo.getAllPriceRules()
//    // Then
//       val value = CouponRepo.allPriceRules.getOrAwaitValue()
//       junit.framework.TestCase.assertEquals((allPriceRuleExpected.get(0).title),value.get(0).title)
//   }

    @Test
    fun getAllDiscountCode_priceRuleID_postValueInAllDiscountCode()  =  runBlocking {
        //Given repo
        couponRepo.getAllDiscountCode("992172277898")
        // Then
        val value = CouponRepo.allDiscountCode.getOrAwaitValue()
        junit.framework.TestCase.assertEquals((allDiscountCodeExpected.get(0).code),value.get(0).code)

    }
}