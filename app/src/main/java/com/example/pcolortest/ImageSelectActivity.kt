package com.example.pcolortest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.ImageView
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.Toast
import kotlin.math.round
import kotlin.math.abs

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

        Toast.makeText(this@ImageSelectActivity, "과한 필터나 조명에 따라 결과가 바뀔 수 있습니다.", Toast.LENGTH_LONG).show()

        Toast.makeText(this@ImageSelectActivity, "과한 악세서리나 화장이 진한 사진은 삼가바랍니다.", Toast.LENGTH_LONG).show()

        Toast.makeText(this@ImageSelectActivity, "이미지 선택 후 볼을 터치해주세요.", Toast.LENGTH_LONG).show()

        val btn4 = findViewById<Button>(R.id.btn_back)
        btn4.setOnClickListener{
            pickImageGallery()
        }
    }

    // 갤러리에서 이미지를 선택하기 위한 함수
    private fun pickImageGallery(){
        val intent = Intent(Intent.ACTION_PICK) // 이미지 선택을 위한 Intent 생성
        intent.type = "image/*" // 이미지 타입의 파일만 선택 가능하도록 설정
        startActivityForResult(intent, ImageSelectActivity.IMAGE_REQUEST_CODE)
        // 이미지 선택 액티비티를 열고 결과를 처리할 때 IMAGE_REQUEST_CODE를 사용
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

                        val sprColor = 18.59296
                        val sumColor = 12.5
                        val falColor = 27.13987
                        val winColor = 16.73913

                        if(abs(saturation-sprColor)<abs(saturation-sumColor)&&abs(saturation-sprColor)<abs(saturation-falColor)&&abs(saturation-sprColor)<abs(saturation-winColor)){
                            // 'SpringActivity'로 이동하는 인텐트 생성
                            val intent4 = Intent(this, SpringActivity::class.java)
                            // 생성한 인텐트 실행하여 화면 전환
                            startActivity(intent4)
                        }
                        if(abs(saturation-sumColor)<abs(saturation-sprColor)&&abs(saturation-sumColor)<abs(saturation-falColor)&&abs(saturation-sumColor)<abs(saturation-winColor)){
                            // 'SummerActivity'로 이동하는 인텐트 생성
                            val intent5 = Intent(this, SummerActivity::class.java)
                            // 생성한 인텐트 실행하여 화면 전환
                            startActivity(intent5)
                        }
                        if(abs(saturation-falColor)<abs(saturation-sumColor)&&abs(saturation-falColor)<abs(saturation-sprColor)&&abs(saturation-falColor)<abs(saturation-winColor)){
                            // 'FallActivity'로 이동하는 인텐트 생성
                            val intent6 = Intent(this, FallActivity::class.java)
                            // 생성한 인텐트 실행하여 화면 전환
                            startActivity(intent6)
                        }
                        if(abs(saturation-winColor)<abs(saturation-sumColor)&&abs(saturation-winColor)<abs(saturation-falColor)&&abs(saturation-winColor)<abs(saturation-sprColor)){
                            // 'WinterActivity'로 이동하는 인텐트 생성
                            val intent7 = Intent(this, WinterActivity::class.java)
                            // 생성한 인텐트 실행하여 화면 전환
                            startActivity(intent7)
                        }

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
