package com.ubaya.studentapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ubaya.studentapp.R
import com.ubaya.studentapp.databinding.ActivityMainBinding
import com.ubaya.studentapp.util.createNotificationChannel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //program yg dijalankan saat construktor diciptakan
    init{
        instance = this
    }
    //bisa diakses tanpa menciptakan objecknya
    //di companion gk bisa akses contect objek
    companion object{
        private var instance:MainActivity ?= null
        fun showNotification(title:String, content:String, icon:Int) {
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
            val  builder = NotificationCompat.Builder(instance!!.applicationContext,channelId)
                .apply{
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority = NotificationCompat.PRIORITY_DEFAULT
                    setAutoCancel(true)
                }
            val notif = NotificationManagerCompat.from(instance!!.applicationContext)
            notif.notify(1001,builder.build())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, true, getString(R.string.app_name), "App channel")

//        val observable = Observable.just("a stream of data","hellow","world")
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(observer)
//            .subscribe(
//                {Log.d("Messages", "data captured: ${it}")},
//                {Log.d("Messages", "error: ${message.toString()}")},
//                {Log.d("Messages", "Complete Subscribe")}
//            )

        //objek yang subscrivbe/listen observabe
//        val observer = object: Observer<String> {
//            //ngasi tau klo observer muali sbucribe
//            override fun onSubscribe(d: Disposable) {
//                Log.d("Messages", "Begin Subscribe")
//            }
//
//            //muncul klo ada data yg dimunculkan observer
//            override fun onNext(t: String) {
//                Log.d("Messages", "data captured: ${t.toString()}")
//            }
//            override fun onError(e: Throwable) {
//                Log.d("Messages", "error: ${e.message.toString()}")
//            }
//
//            override fun onComplete() {
//                Log.d("Messages", "Complete Subscribe")
//            }
//
//        }

    }
}