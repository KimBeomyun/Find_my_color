package com.example.pcolortestdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.objdetect.CascadeClassifier
import android.content.Intent
import android.app.Activity
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // activity_main 레이아웃을 화면에 표시

        val btn2 = findViewById<Button>(R.id.btn_color)
        btn2.setOnClickListener{
            val intent1 = Intent(this, MainActivity2::class.java)
            startActivity(intent1)

        }
    }
}

// Haar Cascade XML 파일의 경로
//const val cascadeFilePath = "file:///android_asset/haarcascade_frontalface_default.xml"

// 분류기를 로드합니다.
//val cascadeClassifier = CascadeClassifier(cascadeFilePath)

