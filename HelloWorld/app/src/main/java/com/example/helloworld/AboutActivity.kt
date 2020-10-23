package com.example.helloworld

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AboutActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    //like static section in java
    companion object {
        fun start(context: Context){
            val intent = Intent(context, AboutActivity::class.java)
            context.startActivity(intent)
        }
    }

}