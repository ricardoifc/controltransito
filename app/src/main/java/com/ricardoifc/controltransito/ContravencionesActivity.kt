package com.ricardoifc.controltransito

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_fotoradar.*
import com.google.firebase.firestore.FirebaseFirestore
import com.ricardoifc.controltransito.R
import kotlinx.android.synthetic.main.activity_contravenciones.*
import kotlinx.android.synthetic.main.activity_mostrar_todo.*


class ContravencionesActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contravenciones)
        setup()
    }
    private fun setup(){
        val listaMultas = mutableListOf<Multas>()

        db.collection("multas").get().addOnSuccessListener { resultado->
            for(documento in resultado){

                val multa = documento.toObject(Multas::class.java)
                listaMultas.add(multa)

            }

            val  listaVehiculos = ArrayList<String>()
            val  listaPLacas = ArrayList<String>()
            for (lista in listaMultas){
                var velocidad = lista.velocidad.toInt()
                if(velocidad >= 100){
                    val datosPlaca = "${lista.placa}"
                    val datosMulta = "Velocidad: ${lista.velocidad}km/h\n" +
                            "Placa y modelo: ${lista.placa}(${lista.modelo})\n" +
                            "Nombre y Cedula: ${lista.nombre}(${lista.cedula})\n" +
                            "Fecha y hora: ${lista.fecha}(${lista.hora})"
                    listaVehiculos.add(datosMulta)
                    listaPLacas.add(datosPlaca)
                }

            }
            val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaPLacas)
            listViewCOntravencion.adapter = adaptador
            listViewCOntravencion.setOnItemClickListener{parent, view, position, id->
                Toast.makeText(this, listaVehiculos[position], Toast.LENGTH_LONG).show()
                tv_datosVehi.setText(listaVehiculos[position])
            }

        }

    }
}