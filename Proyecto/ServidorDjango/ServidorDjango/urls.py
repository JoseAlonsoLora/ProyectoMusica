"""ServidorDjango URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.11/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url
from django.contrib import admin
from modelo.views import *

urlpatterns = [
    url(r'^admin/', admin.site.urls),
    url(r'^artista/', ArtistaList.as_view()),
    url(r'^genero/', GeneroList.as_view()),
    url(r'^listaReproduccion/', ListareproduccionList.as_view()),
    url(r'^usuario/', UsuarioList.as_view()),
    url(r'^crearAlbum/', guardarAlbum),
    url(r'^biblioteca/', BibliotecaList.as_view()),
    url(r'^crearUsuario/', guardarUsuario),
    url(r'^canciones/', obtenerCancionesLista),
    url(r'^todasCanciones/', obtenerTodasCanciones),
    url(r'^todosLosAlbumes/', obtenerTodosAlbum),
    url(r'^AlbumesPorArtista/', obtenerAlbumesArtista),
    url(r'^cancionesPorAlbum/', obtenerCancionesAlbum),
    url(r'^albumesPorBiblioteca/', obtenerAlbumPorBiblioteca),
    url(r'^cancionesPorBiblioteca/', obtenerCancionesPorBiblioteca),
    url(r'^agregarALista/', agregarCancionLista),
    url(r'^iniciarSesion/', iniciarSesion),
    url(r'^obtenerAlbumPorGenero/', obtenerAlbumPorGenero),
    url(r'^bibliotecaPrivada/', bibliotecaPrivada),
    url(r'^bibliotecaPublica/', bibliotecaPublica),
]
