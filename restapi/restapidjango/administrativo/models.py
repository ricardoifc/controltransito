from django.db import models
from django.db.models.fields import CharField

# Create your models here.

class Multa(models.Model):
    placa = models.CharField(max_length=10)
    cedula = models.CharField(max_length=60)
    fecha = models.CharField(max_length=15)
    hora = models.CharField(max_length=15)
    modelo = models.CharField(max_length=15)
    nombre = models.CharField(max_length=15)
    velocidad = models.CharField(max_length=15)
    

    
    def __str__(self):
        return "%s %s %s %s" % (self.placa,
                self.cedula,
                self.fecha,
                self.hora,
                self.modelo,
                self.nombre,
                self.velocidad)   
    

