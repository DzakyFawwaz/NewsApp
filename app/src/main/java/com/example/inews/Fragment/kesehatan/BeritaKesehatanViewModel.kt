package com.example.inews.Fragment.kesehatan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.inews.pojo.kesehatan.ResponseKesehatan

class BeritaKesehatanViewModel : ViewModel() {
    private val respondataberitakesehatan = MutableLiveData<ResponseKesehatan>()
    fun getResponseDataBeritaKesehatan(): LiveData<ResponseKesehatan> {
        return respondataberitakesehatan

    }

    val dataBeritaKesehatan: Unit
        get() {
            AndroidNetworking.get("http://newsapi.org/v2/top-headlines?country=id&category=health&apiKey=6302d5c9ee20476b8f11df692f3a818c")
                .setPriority(Priority.MEDIUM).build()
                .getAsObject(ResponseKesehatan::class.java, object :
                    ParsedRequestListener<ResponseKesehatan> {
                    override fun onResponse(response: ResponseKesehatan?) {
                        respondataberitakesehatan.postValue(response)
                    }

                    override fun onError(anError: ANError?) {
                        Log.e("Error", anError.toString())
                    }
                })
        }
}
