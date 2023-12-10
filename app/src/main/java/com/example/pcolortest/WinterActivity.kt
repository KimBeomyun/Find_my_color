package com.example.pcolortest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class WinterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winter)

        val btn1 = findViewById<Button>(R.id.w_mycolor_btn)
        btn1.setOnClickListener{
            Toast.makeText(this@WinterActivity, "마이 컬러가 저장되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}