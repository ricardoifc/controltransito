from django.contrib import admin

# Register your models here.
from administrativo.models import Multa

class MultaAdmin(admin.ModelAdmin):

    list_display = ('placa','cedula', 'fecha', 'hora', 'modelo', 'nombre', 'velocidad')

    search_fields = ('placa','cedula')
    
admin.site.register(Multa, MultaAdmin)




