package com.ricardoifc.controltransito

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }
    private fun setup(){


        btn_fotoradar.setOnClickListener(){

            val fotoradarIntent: Intent = Intent(this,FotoradarActivity::class.java)
            startActivity(fotoradarIntent)
        }
        btn_filtrado.setOnClickListener(){

            val filtradoIntent: Intent = Intent(this,FiltradoActivity::class.java)
            startActivity(filtradoIntent)
        }
        btn_irDetectarPlaca.setOnClickListener(){
            val detectarPlacaIntent = Intent(this, DetectarPlacaActivity::class.java)
            startActivity(detectarPlacaIntent)
        }
    }
}