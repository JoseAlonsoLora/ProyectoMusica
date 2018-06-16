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
		album.genero_idgenero = Genero.objects.get(pk = diccionario.get("idGenero"))
		album.artista_idartista = Artista.objects.get(pk = diccionario.get("idArtista"))
		album.biblioteca_idbiblioteca = Biblioteca.objects.get(pk = diccionario.get("idBiblioteca"))
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
		aux = {}
		aux = ListareproduccionHasCancion.objects.all()
		print(aux)
		listaCanciones = ListareproduccionHasCancion.objects.filter(listareproduccion_idlistareproduccion = idLista).values()
		listaFinal = []
		for lis in listaCanciones:

			cancion = Cancion.objects.get(pk = lis.get("cancion_idcancion_id"))
			serializer = CancionSerializer(cancion)
			listaFinal.append(serializer.data)		
		return Response(listaFinal)


@api_view(['GET'])
def obtenerTodasCanciones(request):
	canciones = Cancion.objects.all()
	serializer = CancionSerializer(canciones,many=True)
	listaCanciones = []
	for cancionJson in serializer.data:
		cancionAux = {}
		album = Album.objects.get(pk = cancionJson['album_idalbum'])
		if (album.biblioteca_idbiblioteca.publica == 1):
			cancionAux["nombre"] = cancionJson['nombre']
			cancionAux["idcancion"] = cancionJson['idcancion']
			cancionAux['calificacion'] = cancionJson['calificacion']
			cancionAux['nombrearchivo'] = cancionJson['nombrearchivo']
			cancionAux['nombreAlbum'] = album.nombre		
			cancionAux['nombreArtista'] = album.artista_idartista.nombre
			listaCanciones.append(cancionAux)
	return Response(listaCanciones)


@api_view(['GET'])
def obtenerTodosAlbum(request):
	albumes = Album.objects.all()
	listaAlbumes = []
	serializer = AlbumSerializer(albumes,many=True)
	for album in serializer.data:
		biblioteca = Biblioteca.objects.get(pk = album.get('biblioteca_idbiblioteca'))
		if (biblioteca.publica == 1):
			listaAlbumes.append(album)
	return Response(listaAlbumes)


@api_view(['GET'])
def obtenerAlbumesArtista(request):
	idArtista = request.GET['id']
	listaAlbumes = {}
	listaAlbumesFinal = []
	listaAlbumes = Album.objects.filter(artista_idartista = idArtista).values()
	for album in listaAlbumes:
		biblioteca = Biblioteca.objects.get(pk = album.get('biblioteca_idbiblioteca_id'))
		if (biblioteca.publica == 1):
			listaAlbumesFinal.append(album)
	return Response(listaAlbumesFinal)


@api_view(['GET'])
def obtenerCancionesAlbum(request):
	idAlbum = request.GET['id']
	listaCanciones = {}
	listaCanciones = Cancion.objects.filter(album_idalbum = idAlbum).values()
	return Response(listaCanciones)


@api_view(['GET'])
def obtenerAlbumPorBiblioteca(request):
	idBiblioteca = request.GET['id']
	listaAlbumes = {}
	listaAlbumes = Album.objects.filter(biblioteca_idbiblioteca = idBiblioteca).values()
	return Response(listaAlbumes)


@api_view(['GET'])
def obtenerCancionesPorBiblioteca(request):
	idBiblioteca = request.GET['id']
	listaAlbumes = {}
	listaAlbumes = Album.objects.filter(biblioteca_idbiblioteca = idBiblioteca).values()
	listaCanciones = []
	for album in listaAlbumes:
		serializer = {}
		serializer = Cancion.objects.filter(album_idalbum = album.get('idalbum')).values()		
		for cancionJson in serializer:
			cancionAux = {}
			cancionAux["nombre"] = cancionJson['nombre']
			cancionAux["idcancion"] = cancionJson['idcancion']
			cancionAux['calificacion'] = cancionJson['calificacion']
			cancionAux['nombrearchivo'] = cancionJson['nombrearchivo']
			cancionAux['nombreAlbum'] = album.get("nombre")
			artista = Artista.objects.get(pk = album.get("artista_idartista_id"))
			cancionAux['nombreArtista'] = artista.nombre
			listaCanciones.append(cancionAux)
	return Response(listaCanciones)

@api_view(['POST'])
def agregarCancionLista(request):
	diccionario = {}
	diccionario = request.data
	lista = Listareproduccion.objects.get(pk = diccionario.get("idLista"))
	cancion = Cancion.objects.get(pk = diccionario.get("idCancion"))
	listahascancion = ListareproduccionHasCancion()
	listahascancion.listareproduccion_idlistareproduccion = lista
	listahascancion.cancion_idcancion = cancion
	listahascancion.save()
	return Response({'result':'ok'}, status=status.HTTP_201_CREATED)