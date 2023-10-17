package com.example.pcolortest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.ImageView
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import kotlin.math.round

class ImageSelectActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView // 이미지뷰 위젯을 나타내는 변수

    // 제스쳐 이벤트 감지하는 변수
    private var mScaleGestureDetector: ScaleGestureDetector? = null
    private var scaleFactor = 1.0f


    // companion object를 사용하여 상수 정의
    companion object{
        val IMAGE_REQUEST_CODE = 100 // 이미지를 선택하는 요청 코드
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_select)

        imageView = findViewById(R.id.img_save)

        pickImageGallery()

        mScaleGestureDetector = ScaleGestureDetector(this, ScaleListener())

        val btn4 = findViewById<Button>(R.id.btn_ok)
        btn4.setOnClickListener{
            // 각 계절의 피부 색상 평균
            val sprColor = 18.59296
            val sumColor = 12.5
            val falColor = 27.13987
            val winColor = 16.73913


        }
    }

    // 갤러리에서 이미지를 선택하기 위한 함수
    private fun pickImageGallery(){
        val intent = Intent(Intent.ACTION_PICK) // 이미지 선택을 위한 Intent 생성
        intent.type = "image/*" // 이미지 타입의 파일만 선택 가능하도록 설정
        startActivityForResult(intent, ImageSelectActivity.IMAGE_REQUEST_CODE) // 이미지 선택 액티비티를 열고 결과를 처리할 때 IMAGE_REQUEST_CODE를 사용
    }

    // 이미지 선택 액티비티 결과를 처리하는 함수
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ImageSelectActivity.IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            imageView.setImageURI(data?.data) // 선택한 이미지의 URI를 ImageView에 설정하여 이미지를 표시

        }
    }

    // 제스쳐 이벤트가 발생하면 실행되는 메소드
    override fun onTouchEvent(motionevent: MotionEvent?): Boolean {
        // 제스쳐 이벤트를 처리하는 메소드를 호출
        mScaleGestureDetector?.onTouchEvent(motionevent as MotionEvent)
        when (motionevent?.action) {
            MotionEvent.ACTION_DOWN -> {
                val x = motionevent.x.toInt()
                val y = motionevent.y.toInt()
                if (imageView.drawable != null && x >= 0 && y >= 0) {
                    val bitmap = (imageView.drawable as BitmapDrawable).bitmap
                    if (x < bitmap.width && y < bitmap.height) {
                        val pixelColor = bitmap.getPixel(x, y)

                        val hsv = FloatArray(3)
                        Color.colorToHSV(pixelColor, hsv)

                        val hue = round(hsv[0]) // Hue
                        val saturation = round(hsv[1] * 100) // Saturation (0-100%)
                        val value = round(hsv[2] * 100) // Value (0-100%)

                        // HSV 변수를 사용하여 선택한 좌표의 색상을 처리
                        // 예: Log.d("Color", "Hue: $hue, Saturation: $saturation%, Value: $value%")
                        Log.d("Color", "Hue: $hue, Saturation: $saturation%, Value: $value%")
                    }
                }
            }
        }
        return true
    }

    // 제스쳐 이벤트를 처리하는 클래스
    inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener(){
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean{
            scaleFactor *= scaleGestureDetector.scaleFactor

            // 최소 0.5, 최대 2배
            scaleFactor = Math.max(0.5f, Math.min(scaleFactor, 2.0f))

            // 이미지에 적용
            imageView.scaleX = scaleFactor
            imageView.scaleY = scaleFactor
            return true
        }
    }
}
