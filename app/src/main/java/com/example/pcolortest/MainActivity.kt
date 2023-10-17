package com.example.pcolortest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btn_info)
        btn1.setOnClickListener{
            val intent1 = Intent(this, ColorInfoActivity::class.java)
            startActivity(intent1)
        }

        val btn2 = findViewById<Button>(R.id.btn_color)
        btn2.setOnClickListener{
            val intent2 = Intent(this, ImageSelectActivity::class.java)
            startActivity(intent2)
        }

        val btn3 = findViewById<Button>(R.id.btn_mypage)
        btn3.setOnClickListener{
            val intent3 = Intent(this, MyPageActivity::class.java)
            startActivity(intent3)
        }
    }
}