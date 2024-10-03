package com.example.postsapp

import android.app.Application
import com.example.postsapp.roomdatabase.MyDatabase
import com.example.postsapp.roomdatabase.MyDatabaseComment


class App: Application()  {
    override fun onCreate() {
        super.onCreate()

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



        MyDatabase.init(this)
        MyDatabaseComment.getDatabase(this)

    }

}