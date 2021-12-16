package com.ricardoifc.controltransito
import com.google.firebase.firestore.GeoPoint


data class Multas(var placa: String= "",
                  var cedula: String= "",
                  var fecha: String= "",
                  var hora: String= "",
                  var modelo: String= "",
                  var nombre: String= "",
                  var velocidad: String= "")

