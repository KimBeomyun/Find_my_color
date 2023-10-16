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
import org.opencv.core.Core
import org.opencv.core.MatOfRect
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.core.Size
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import org.opencv.objdetect.Objdetect
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import kotlin.math.abs
import android.util.Log



class MainActivity2 : AppCompatActivity() {

    private lateinit var imageView: ImageView // 이미지뷰 위젯을 나타내는 변수
    private val TAG = "Activity"

    // companion object를 사용하여 상수 정의
    companion object{
        val IMAGE_REQUEST_CODE = 100 // 이미지를 선택하는 요청 코드
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        imageView = findViewById(R.id.img_save)

        pickImageGallery()

        val btn4 = findViewById<Button>(R.id.btn_ok)
        btn4.setOnClickListener{
            // OpenCV 초기화
            OpenCVLoader.initDebug()

            val cascadeFilePath = "assets/haarcascades"
            val cascadeClassifier = CascadeClassifier(cascadeFilePath)

            if (cascadeClassifier.empty()) {
                Log.e(TAG, "Failed to load Cascade Classifier")
            }
            else {
                // 분류자가 로드되었을 때 감지 작업 수행
                // ImageView로부터 이미지를 불러오고 Mat으로 변환
                val imageView = findViewById<ImageView>(R.id.img_save)
                imageView.isDrawingCacheEnabled = true
                imageView.buildDrawingCache()
                val imageBitmap = imageView.drawingCache
                val imageMat = Mat(imageBitmap.height, imageBitmap.width, CvType.CV_8UC4)
                Utils.bitmapToMat(imageBitmap, imageMat)

                // 얼굴 감지를 위한 Haar Cascade 분류기 로드
                val faceCascade = CascadeClassifier("haarcascade_frontalface_default.xml")
                val scaleFactor = 1.1
                val minNeighbors = 5
                val minSize = Size(30.0, 30.0)

                // 얼굴 감지
                val faces = MatOfRect()
                faceCascade.detectMultiScale(imageMat, faces, scaleFactor, minNeighbors, 0, minSize, imageMat.size())

                // 각 계절의 피부 색상 평균
                val sprColor = 18.59296
                val sumColor = 12.5
                val falColor = 27.13987
                val winColor = 16.73913

                // 얼굴 주변에서 볼 색상 추출
                for (face in faces.toArray()) {
                    val roi = imageMat.submat(face)
                    val hsv = Mat()
                    Imgproc.cvtColor(roi, hsv, Imgproc.COLOR_BGR2HSV)

                    // 볼 영역의 평균 색상 계산
                    val meanColor = Core.mean(hsv)
                    val hue = meanColor.`val`[0]  // 색상
                    val saturation = meanColor.`val`[1]  // 채도
                    val value = meanColor.`val`[2]  // 명도

                    // 봄 색상과의 거리가 가장 짧은 경우
                    if(abs(sprColor - saturation) < abs(sumColor - saturation) && abs(sprColor - saturation) < abs(falColor - saturation) &&
                        abs(sprColor - saturation) < abs(winColor - saturation)){
                        // 봄 색상 페이지로 이동
                        val intent2 = Intent(this, MainActivity3::class.java)
                        startActivity(intent2)
                    }
                    // 여름 색상과의 거리가 가장 짧은 경우
                    else if(abs(sprColor - saturation) < abs(sumColor - saturation) && abs(sprColor - saturation) < abs(falColor - saturation) &&
                        abs(sprColor - saturation) < abs(winColor - saturation)){
                        // 여름 색상 페이지로 이동
                        val intent3 = Intent(this, MainActivity4::class.java)
                        startActivity(intent3)
                    }
                    // 가을 색상과의 거리가 가장 짧은 경우
                    else if(abs(sprColor - saturation) < abs(sumColor - saturation) && abs(sprColor - saturation) < abs(falColor - saturation) &&
                        abs(sprColor - saturation) < abs(winColor - saturation)){
                        // 가을 색상 페이지로 이동
                        val intent4 = Intent(this, MainActivity5::class.java)
                        startActivity(intent4)
                    }
                    // 겨울 색상과의 거리가 가장 짧은 경우
                    else if(abs(sprColor - saturation) < abs(sumColor - saturation) && abs(sprColor - saturation) < abs(falColor - saturation) &&
                        abs(sprColor - saturation) < abs(winColor - saturation)){
                        // 겨울 색상 페이지로 이동
                        val intent5 = Intent(this, MainActivity6::class.java)
                        startActivity(intent5)
                    }
                    else{
                        // 오류 페이지로 이동(얼굴 인식을 하지 못한 경우), 토스트 메세지
                    }
            }

            }
        }

    }

    // 갤러리에서 이미지를 선택하기 위한 함수
    private fun pickImageGallery(){
        val intent = Intent(Intent.ACTION_PICK) // 이미지 선택을 위한 Intent 생성
        intent.type = "image/*" // 이미지 타입의 파일만 선택 가능하도록 설정
        startActivityForResult(intent, MainActivity2.IMAGE_REQUEST_CODE) // 이미지 선택 액티비티를 열고 결과를 처리할 때 IMAGE_REQUEST_CODE를 사용
    }

    // 이미지 선택 액티비티 결과를 처리하는 함수
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == MainActivity2.IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            imageView.setImageURI(data?.data) // 선택한 이미지의 URI를 ImageView에 설정하여 이미지를 표시

        }
    }
}