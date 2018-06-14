from django.shortcuts import render
from modelo.models import *
from modelo.serializers import *
from rest_framework import generics
from django.http import JsonResponse
from rest_framework.response import Response
from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.parsers import JSONParser
from django.core import serializers
import json
import os
import zipfile
import io
import zlib

# Create your views here.

class ArtistaList(generics.ListCreateAPIView):
	queryset =  Artista.objects.all()
	serializer_class = ArtistaSerializer

class GeneroList(generics.ListCreateAPIView):
	queryset =  Genero.objects.all()
	serializer_class = GeneroSerializer

class ListareproduccionList(generics.ListCreateAPIView):
	queryset =  Listareproduccion.objects.all()
	serializer_class = ListareproduccionSerializer

class UsuarioList(generics.ListCreateAPIView):
	queryset =  Usuario.objects.all()
	serializer_class = UsuarioSerializer

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

@api_view(['POST'])
def guardarUsuario(request):
	if request.method == 'POST':
		diccionario = {}
		try:
			diccionario = request.data
		except:
			pass
		usuario = Usuario()
		usuario.nombreusuario = diccionario.get("nombreUsuario")
		usuario.contrasena = diccionario.get("contrasena")
		usuario.nombres = diccionario.get("nombres")
		usuario.apellidos = diccionario.get("apellidos")
		usuario.correo = diccionario.get("correo")
		usuario.save()

		biblioteca = Biblioteca()
		biblioteca.publica = 1
		biblioteca.usuario_nombreusuario = usuario
		biblioteca.save()
		return Response({'result':'ok'}, status=status.HTTP_201_CREATED)

@api_view(['GET'])
def obtenerCancionesLista(request):
	if request.method == 'GET':
		try:
			idLista = request.GET['id']
		except:
			pass
		listaCanciones = {}
		listaCanciones = ListareproduccionHasCancion.objects.filter(listareproduccion_idlistareproduccion = idLista).values()
		listaFinal = []
		for lis in listaCanciones:
			cancion = Cancion.objects.get(pk = lis.get("cancion_idcancion_id"))
			serializer = CancionSerializer(cancion)
			listaFinal.append(serializer.data)

		#lista = list(listaCanciones)
		#print(lista)
		#for lis in lista:
		#	serializar = CancionSerializer(lis.cancion_idcancion,many=True)
		#	print(serializar.data)
		
		return Response(listaFinal)