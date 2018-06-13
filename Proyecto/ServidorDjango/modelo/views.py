from django.shortcuts import render
from modelo.models import *
from modelo.serializers import *
from rest_framework import generics
from django.http import JsonResponse
from rest_framework.response import Response
from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.parsers import JSONParser
import json
import os

# Create your views here.

class ArtistaList(generics.ListCreateAPIView):
	queryset =  Artista.objects.all()
	serializer_class = ArtistaSerializer

class GeneroList(generics.ListCreateAPIView):
	queryset =  Genero.objects.all()
	serializer_class = GeneroSerializer

@api_view(['POST'])
def guardarAlbum(request):
	if request.method == 'POST':
		diccionario = {}
		try:
			diccionario = request.data
		except:
			pass
		album = Album()
		album.nombre = diccionario.get("nombre")
		album.anoLanzamiento = diccionario.get("anoLanzamiento")
		album.compania = diccionario.get("compania")
		album.genero_idGenero = Genero.objects.get(pk = diccionario.get("idGenero"))
		album.artista_idArtista = Artista.objects.get(pk = diccionario.get("idArtista"))
		album.biblioteca_idBiblioteca = Biblioteca.objects.get(pk = diccionario.get("idBiblioteca"))
		album.save()
		canciones = {}
		canciones = diccionario.get("listaCanciones")
		for cancion in canciones:
			cancionModelo = Cancion()
			cancionModelo.nombre = cancion.get("nombre")
			cancionModelo.calificacion = cancion.get("calificacion")
			cancionModelo.nombrearchivo = cancion.get("nombrearchivo")
			cancionModelo.album_idalbum = album
			cancionModelo.save()
		
		return Response({'result':'ok'}, status=status.HTTP_201_CREATED)

class BibliotecaList(generics.ListCreateAPIView):
	queryset =  Biblioteca.objects.all()
	serializer_class = BibliotecaSerializer