package com.ricardoifc.controltransito


import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.android.synthetic.main.activity_detectar_placa.*
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.firebase.ml.vision.FirebaseVision


class DetectarPlacaActivity : AppCompatActivity() {
    var imageView: ImageView? = null
    var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detectar_placa)

        imageView = findViewById(R.id.imageId)
        //find textview
        textView = findViewById(R.id.textId)
        // permiso de camara
        //check app level permission is granted for Camera
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //grant the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 101)
        }
    }
    fun doProcess(view: View?) {
        //open the camera => create an Intent object
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 101)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val bundle = data!!.extras
        //from bundle, extract the image
        val bitmap = bundle!!["data"] as Bitmap?
        //set image in imageview
        imageView!!.setImageBitmap(bitmap)
        //process the image
        //1. create a FirebaseVisionImage object from a Bitmap object
        val firebaseVisionImage = FirebaseVisionImage.fromBitmap(bitmap!!)
        //2. Get an instance of FirebaseVision
        val firebaseVision = FirebaseVision.getInstance()
        //3. Create an instance of FirebaseVisionTextRecognizer
        val firebaseVisionTextRecognizer = FirebaseVision.getInstance()
            .onDeviceTextRecognizer
        //4. Create a task to process the image
        val task = firebaseVisionTextRecognizer.processImage(firebaseVisionImage)
        //5. if task is success
        task.addOnSuccessListener { firebaseVisionText ->
            val s = firebaseVisionText.text
            textView!!.text = s
        }
        //6. if task is failure
        task.addOnFailureListener { e ->
            Toast.makeText(
                applicationContext,
                e.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }




}