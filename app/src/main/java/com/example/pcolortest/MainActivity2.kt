package com.example.pcolortest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // 버튼1에 대한 클릭 리스너 설정
        val btn1 = findViewById<Button>(R.id.btn_info)
        btn1.setOnClickListener{
            // 'ColorInfoActivity'로 이동하는 인텐트 생성
            val intent1 = Intent(this, ColorInfoActivity::class.java)
            // 생성한 인텐트 실행하여 화면 전환
            startActivity(intent1)
        }

        val btn2 = findViewById<Button>(R.id.btn_color)
        btn2.setOnClickListener{
            // 'ImageSelectActivity'로 이동하는 인텐트 생성
            val intent2 = Intent(this, ImageSelectActivity::class.java)
            startActivity(intent2)
        }

        val btn3 = findViewById<Button>(R.id.btn_mypage)
        btn3.setOnClickListener{
            // 'MyPageActivity'로 이동하는 인텐트 생성
            val intent3 = Intent(this, MyPageActivity::class.java)
            startActivity(intent3)
        }
    }
    class GlobalApplication : Application() {
        override fun onCreate() {
            super.onCreate()
            KakaoSdk.init(this, "843146d232af3549d0f8ebf7f286d50e")
        }
    }
}