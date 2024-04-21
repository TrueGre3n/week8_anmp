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
import com.ubaya.uts_160421038.model.User
import org.json.JSONObject

class ProfileViewModel(application: Application): AndroidViewModel(application) {
    val userLD = MutableLiveData<User>()
    val updatePassLD = MutableLiveData<Boolean>()
    val updateDataLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(username: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.73.27/hobby_uts/userWId.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                Log.d("apiprofile", it.toString())
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object : TypeToken<User>() {}.type
                    userLD.value = Gson().fromJson(data[0].toString(), sType) as User
                    Log.d("showprofile", userLD.value.toString())

                }
            },
            {
                Log.d("showprofile", it.toString())
            })
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }
    fun updateData(username:String, firstName:String, lastName:String ){

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.73.27/hobby_uts/updateData.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                Log.d("apichangeddata", it.toString())
                val obj = JSONObject(it)
                if (obj.getString("result") == "success") {
                    updateDataLD.value = true
                }
            },
            {
                Log.d("showchangeddata", it.toString())
                updateDataLD.value = false
            })
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["first_name"] = firstName
                params["last_name"] = lastName
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    fun updatePassword(username: String, password:String){

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.73.27/hobby_uts/updatePassword.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                Log.d("apichangepassword", it.toString())
                val obj = JSONObject(it)
                if (obj.getString("result") == "success") {
                    updatePassLD.value = true
                }
            },
            {
                Log.d("showpassword", it.toString())
                updatePassLD.value = false
            })
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
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