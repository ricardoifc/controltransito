package com.ricardoifc.controltransito


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fotoradar.*
import com.google.firebase.firestore.FirebaseFirestore


class FotoradarActivity : AppCompatActivity() {


    private val db = FirebaseFirestore.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fotoradar)
        setup()


    }
    private fun setup(){
        btn_registrarFotomulta.setOnClickListener(){

            db.collection("multas").
            document(et_placa.text.toString()).set(hashMapOf("placa" to et_placa.text.toString(),"velocidad" to et_velocidad.text.toString(),
                "fecha" to et_fecha.text.toString(),"hora" to et_hora.text.toString(),
                "cedula" to et_cedula.text.toString(),"nombre" to et_nombrePersona.text.toString(),
                "modelo" to et_marcaModelo.text.toString()))

            et_placa.setText("")
            et_velocidad.setText("")
            et_fecha.setText("")
            et_hora.setText("")
            et_cedula.setText("")
            et_nombrePersona.setText("")
            et_marcaModelo.setText("")

        }
    }


}