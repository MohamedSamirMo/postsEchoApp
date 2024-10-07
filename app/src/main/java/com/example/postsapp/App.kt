  package com.example.postsapp

import android.app.Application
import com.example.postsapp.roomdatabase.MyDatabase
import dagger.hilt.android.HiltAndroidApp

  @HiltAndroidApp
class App: Application()  {
//    override fun onCreate() {
        //
//                // تفعيل StrictMode
//                val policy = StrictMode.ThreadPolicy.Builder()
//                    .detectAll() // اكتشاف جميع المشكلات الممكنة
//                    .penaltyLog() // تسجيل المشكلات في Logcat
//                    .build()
//
//                val vmPolicy = StrictMode.VmPolicy.Builder()
//                    .detectAll() // اكتشاف جميع المشكلات الممكنة
//                    .penaltyLog() // تسجيل المشكلات في Logcat
//                    .build()
//
//                StrictMode.setThreadPolicy(policy)
//                StrictMode.setVmPolicy(vmPolicy)



//        super.onCreate()
//
//
//
//        MyDatabase.init(this)
//        MyDatabaseComment.getDatabase(this)}

}