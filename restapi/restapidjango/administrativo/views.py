from django.shortcuts import render, redirect
from django.http import HttpResponse
from django.template import RequestContext
from django.shortcuts import render
from django.contrib.auth.decorators import login_required, permission_required
from django.contrib.auth import authenticate, login, logout
from django.contrib import messages
from django.contrib.auth.forms import UserCreationForm, AuthenticationForm

#Uso django-rest_framework
from django.contrib.auth.models import User, Group
from rest_framework import viewsets
from rest_framework import permissions
from administrativo.serializers import UserSerializer, GroupSerializer, \
MultaSerializer

# importar las clases de models.py
from administrativo.models import *

# importar las clases de models.py
from administrativo.forms import*

def index(request):
    return render(request, "index.html")


# ingreso de cuenta

def ingreso(request):
    if request.method == "POST":
        form = AuthenticationForm(request=request, data=request.POST)
        print(form.errors)
        if form.is_valid():
            username = form.data.get("username")
            raw_password = form.data.get("password")
            user = authenticate(username=username, password=raw_password)
            if user is not None:
                login(request, user)
                return redirect(index)
    else:
        form = AuthenticationForm()

    informacion_template = {'form': form}
    return render(request, 'registration/login.html', informacion_template)

def logout_view(request):
    logout(request)
    messages.info(request, "Has salido del sistema")
    return redirect(index)

def multa(request):
    """
        Listar los registros del modelo Estudiante,
        obtenidos de la base de datos.
    """
    # a través del ORM de django se obtiene
    # los registros de la entidad; el listado obtenido
    # se lo almacena en una variable llamada
    # estudiantes
    multas = Multa.objects.all()
    # en la variable tipo diccionario llamada informacion_template
    # se agregará la información que estará disponible
    # en el template
    informacion_template = {'multas': multas, 'multa_info': len(multas)}
    return render(request, 'multa.html', informacion_template)

#Fin ingreso


#Crear Casa
@login_required(login_url='/entrando/login/')
def crear_multa(request):
    """
    """
    if request.method=='POST':
        formulario = MultaForm(request.POST)
        print(formulario.errors)
        if formulario.is_valid():
            formulario.save() # se guarda en la base de datos
            return redirect(multa)
    else:
        formulario = MultaForm()
    diccionario = {'formulario': formulario}

    return render(request, 'crear_multa.html', diccionario)

# edicion y eliminacion 
@login_required(login_url='/entrando/login/')
def eliminar_multa(request, id):
    """
    """
    eliminar = Multa.objects.get(pk=id)
    eliminar.delete()
    return redirect(multa)

@login_required(login_url='/entrando/login/')
def editar_multa(request, id):
    """
    """
    multas = Multa.objects.get(pk=id)
    if request.method=='POST':
        formulario = MultaForm(request.POST, instance=multas)
        print(formulario.errors)
        if formulario.is_valid():
            formulario.save()
            return redirect(multa)
    else:
        formulario = MultaForm(instance=multas)
    diccionario = {'formulario': formulario}

    return render(request, 'editarMulta.html', diccionario)
    
# crear vistas a través de viewsets
class UserViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows users to be viewed or edited.
    """
    queryset = User.objects.all().order_by('-date_joined')
    serializer_class = UserSerializer
    # permission_classes = [permissions.IsAuthenticated]


class GroupViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows groups to be viewed or edited.
    """
    queryset = Group.objects.all()
    serializer_class = GroupSerializer
    # permission_classes = [permissions.IsAuthenticated]

class MultaViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows users to be viewed or edited.
    This viewset automatically provides `list`, `create`, `retrieve`,
    `update` and `destroy` actions.
    """
    queryset = Multa.objects.all()
    serializer_class = MultaSerializer
    # permission_classes = [permissions.IsAuthenticated]

