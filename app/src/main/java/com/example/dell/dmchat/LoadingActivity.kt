package com.example.dell.dmchat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

//author : Phat Doan
//This is the first activity that the user will see when start the app

class LoadingActivity : AppCompatActivity() {

    private val splashtimeout : Long = 2500
    private var mHandler : Handler? = null

    internal val mRunnable : Runnable = Runnable {
        if(!isFinishing) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        mHandler = Handler()
        mHandler!!.postDelayed(mRunnable, splashtimeout)
    }

    override fun onDestroy() {
        if(mHandler != null) {
            mHandler!!. removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }
}
