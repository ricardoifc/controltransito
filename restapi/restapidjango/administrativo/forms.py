from django.db.models.base import Model
from django.forms import ModelForm
from django.utils.translation import gettext_lazy as _
from django import forms

from administrativo.models import Multa


class MultaForm(ModelForm):
    class Meta:
        model = Multa
        fields = ['placa','cedula', 'fecha', 'hora', 'modelo', 'nombre', 'velocidad']
        labels = {
            'placa': _('Ingrese placa por favor'),
            'cedula': _('Ingrese cedula por favor'),
            'fecha': _('Ingrese fecha por favor'),
            'hora': _('Ingrese hora por favor'),
            'modelo': _('Ingrese modelo por favor'),
            'nombre': _('Ingrese nombre por favor'),
            'velocidad': _('Ingrese velocidad por favor'),

        }
