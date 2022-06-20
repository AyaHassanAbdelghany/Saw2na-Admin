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
private val pricRule = PriceRules(title = "SUMMERSALE10OFF")

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createRepository() {
    // Get a reference to the class under test
        couponRepo = CouponRepo.getInstance(
        FakeDataSource.getInstance()
    )
   }


   @Test
   fun getAllPriceRules_postValueInAllPriceRules()  =  runBlocking {
    //Given repo
       couponRepo.getAllPriceRules()
    // Then
       val value = CouponRepo.allPriceRules.getOrAwaitValue()
       MatcherAssert.assertThat(
           value, CoreMatchers.nullValue())
       //Assert.assertEquals(allPriceRules.data.get(0).title,pricRule.title )
   }
}