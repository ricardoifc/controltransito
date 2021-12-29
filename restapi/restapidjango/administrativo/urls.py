"""
    Manejo de urls para la aplicación
    administrativo
"""
from django.urls import path
# se importa las vistas de la aplicación
from . import views


urlpatterns = [
        path('', views.index, name='index'),
        path('multas/', views.multa, name="multa"),
        path('multa/crear/', views.crear_multa, name="crear_multa"),
        path('eliminar/multa/<int:id>', views.eliminar_multa,name='eliminar_multa'),
        path('editar_multa/<int:id>', views.editar_multa, name='editar_multa'),
        path('saliendo/logout/', views.logout_view, name="logout_view"),
        path('entrando/login/', views.ingreso, name="login"),
        
 ]
