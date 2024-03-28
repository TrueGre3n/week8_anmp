package com.ubaya.studentapp.viewmodel

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
import com.ubaya.studentapp.model.Song
import com.ubaya.studentapp.model.Student

class SongListViewModel(application: Application):AndroidViewModel(application) {
    val songsLD = MutableLiveData<ArrayList<Song>>()
    //buat liat loding ato gk
    val loadingLD = MutableLiveData<Boolean>()
    val songLoadErrorLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue?= null
    fun refresh(){


        songLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.152.27/songs.json"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {//callback jk voley succes
                loadingLD.value =false
                Log.d("show_volley",it)
                val sType = object: TypeToken<List<Song>>(){}.type
                //gson berusaha mengubah json sesuai dengan list student
                val result = Gson().fromJson<List<Song>>(it,sType)
                songsLD.value = result as ArrayList<Song>


            },
            {
                loadingLD.value =false
                Log.e("show_volley",it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}