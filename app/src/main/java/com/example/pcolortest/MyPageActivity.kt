package com.example.pcolortest

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kakao.sdk.common.KakaoSdk

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        // 버튼1에 대한 클릭 리스너 설정
        val btn1 = findViewById<Button>(R.id.btn_go_spr)
        btn1.setOnClickListener{
            // 'ColorInfoActivity'로 이동하는 인텐트 생성
            val intent1 = Intent(this, SpringActivity::class.java)
            // 생성한 인텐트 실행하여 화면 전환
            startActivity(intent1)
        }

        val btn2 = findViewById<Button>(R.id.btn_go_sum)
        btn2.setOnClickListener{
            // 'ImageSelectActivity'로 이동하는 인텐트 생성
            val intent2 = Intent(this, SummerActivity::class.java)
            startActivity(intent2)
        }

        val btn3 = findViewById<Button>(R.id.btn_go_fal)
        btn3.setOnClickListener {
            // 'MyPageActivity'로 이동하는 인텐트 생성
            val intent3 = Intent(this, FallActivity::class.java)
            startActivity(intent3)
        }

        val btn4 = findViewById<Button>(R.id.btn_go_win)
        btn4.setOnClickListener {
            // 'MyPageActivity'로 이동하는 인텐트 생성
            val intent4 = Intent(this, WinterActivity::class.java)
            startActivity(intent4)
        }

        val btn5 = findViewById<Button>(R.id.btn_logout)
        btn5.setOnClickListener {
            // 'MyPageActivity'로 이동하는 인텐트 생성
            val intent5 = Intent(this, MainActivity::class.java)
            startActivity(intent5)
        }
    }
}