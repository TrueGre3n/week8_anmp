package com.ubaya.uts_160421038.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ubaya.uts_160421038.model.User
import org.json.JSONObject

class RegisterViewModel(application: Application): AndroidViewModel(application) {

    val userLD = MutableLiveData<User>()
    val registerLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(username: String,  firstName:String, lastName:String,email:String, password:String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.73.27/hobby_uts/register.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                Log.d("apiregister", it.toString())
                val obj = JSONObject(it)
                if (obj.getString("result") == "success") {
                    Log.d("showregister", it.toString())
                    registerLD.value = true
                }
            },
            {
                Log.d("showregister", it.toString())
                registerLD.value = false
            })
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["first_name"] = firstName
                params["last_name"] = lastName
                params["email"] = email
                params["password"] = password
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}