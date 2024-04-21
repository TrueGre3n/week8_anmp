package com.ubaya.uts_160421038.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.uts_160421038.model.Game
import com.ubaya.uts_160421038.model.Page
import org.json.JSONObject

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val listPage = MutableLiveData<ArrayList<Page>>()
    val gameLD = MutableLiveData<Game>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun fetch(id:Int) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.73.27/hobby_uts/gameWId.php?id="+id.toString()

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                Log.d("apiresult2", it.toString())
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object: TypeToken<Game>(){}.type
                    gameLD.value = Gson().fromJson(data[0].toString(), sType) as Game
                    Log.d("showvoley2", gameLD.value.toString())
                    detailNews(id)

                }
            },
            {
                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun detailNews(id:Int) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.73.27/hobby_uts/detailNews.php?id="+id.toString()

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                Log.d("apiresult3", it.toString())
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object: TypeToken<List<Page>>(){}.type
                    listPage.value = Gson().fromJson(data.toString(), sType) as ArrayList<Page>?
                    Log.d("showvoley3", listPage.value.toString())
                }
            },
            {
                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}