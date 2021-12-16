package com.ricardoifc.controltransito

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_filtrado.*



class FiltradoActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtrado)
        setup()
    }
    private fun setup() {
        btn_mostrarTodo.setOnClickListener() {
            val mostrarTodoIntent: Intent = Intent(this, MostrarTodoActivity::class.java)
            startActivity(mostrarTodoIntent)
        }
        btn_contravenciones.setOnClickListener() {
            val ontravencionesIntent: Intent = Intent(this, ContravencionesActivity::class.java)
            startActivity(ontravencionesIntent)
        }
        btn_buscar.setOnClickListener() {

            var buscar = et_buscar.text.toString()
            val listaMultas = mutableListOf<Multas>()

            db.collection("multas").get().addOnSuccessListener { resultado ->
                for (documento in resultado) {

                    val multa = documento.toObject(Multas::class.java)
                    listaMultas.add(multa)
                }
                var textx = "No hay resultado"
                for (lista in listaMultas) {
                    var placa:String = lista.placa
                    Log.d("placa: ", placa)
                    Log.d("buscar: ", buscar)
                    if (buscar.equals("${placa}")) {
                        textx = (
                            "Velocidad: ${lista.velocidad}km/h\n" +
                                    "Placa y modelo: ${lista.placa}(${lista.modelo})\n" +
                                    "Nombre y Cedula: ${lista.nombre}(${lista.cedula})\n" +
                                    "Fecha y hora: ${lista.fecha}(${lista.hora})"
                        )
                    }
                    tv_mostrarVeh.setText(textx)
                }
            }
        }
    }
}