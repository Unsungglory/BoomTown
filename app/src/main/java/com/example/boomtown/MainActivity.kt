package com.example.boomtown

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//MainActivity will load the fragment container that will contain all character buttons
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}