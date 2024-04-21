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
import org.json.JSONObject

class ListViewModel(application: Application): AndroidViewModel(application) {
    val gameLD = MutableLiveData<ArrayList<Game>>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null


    fun refresh() {
        loadingLD.value = true
        studentLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.73.27/hobby_uts/game.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                Log.d("apiresult", it.toString())
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object: TypeToken<List<Game>>(){}.type
                    gameLD.value = Gson().fromJson(data.toString(), sType) as ArrayList<Game>?
                    loadingLD.value = false
                    Log.d("showvoley", gameLD.value.toString())
                }


            },
            {
                Log.d("showvoley", it.toString())
                studentLoadErrorLD.value = false
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)


    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}