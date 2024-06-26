package com.ubaya.studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ubaya.studentapp.model.Student
import com.android.volley.Request
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application): AndroidViewModel(application) {
    val studentsLD = MutableLiveData<ArrayList<Student>>()
        //buat liat loding ato gk
    val loadingLD = MutableLiveData<Boolean>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue?= null
    fun refresh(){


        //karna dummy jdi gk mungkin salah
        //studentLoadErrorLD.value = false
        //loadingLD.value = false

        studentLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {//callback jk voley succes
                loadingLD.value =false
                Log.d("show_volley",it)
                val sType = object: TypeToken<List<Student>>(){}.type
                //gson berusaha mengubah json sesuai dengan list student
                val result = Gson().fromJson<List<Student>>(it,sType)
                studentsLD.value = result as ArrayList<Student>


            },
            {
                loadingLD.value =false
                Log.e("show_volley",it.toString())
            }
            )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    //klo viewmodel g dipake
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}