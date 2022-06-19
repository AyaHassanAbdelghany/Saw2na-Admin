package com.example.mcommerceadminapp.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MyConnectivityManager {
    companion object{
        private val _state = MutableLiveData<Boolean>()
        val state :LiveData<Boolean> = _state
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
         val networkCallback = object : ConnectivityManager.NetworkCallback() {
            // network is available for use
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                _state.postValue(true)
            }
            // lost network connection
            override fun onLost(network: Network) {
                super.onLost(network)
                _state.postValue(false)
            }
        }
    }

}