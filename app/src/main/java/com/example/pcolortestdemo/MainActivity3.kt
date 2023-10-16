package com.example.pcolortestdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.objdetect.CascadeClassifier
import android.content.Intent
import android.app.Activity
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
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

class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
    }
}