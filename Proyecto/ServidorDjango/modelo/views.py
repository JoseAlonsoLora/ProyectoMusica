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
			cancionAux = {}
			cancion = Cancion.objects.get(pk = lis.get("cancion_idcancion_id"))
			cancionAux["nombre"] = cancion.nombre
			cancionAux["idcancion"] = cancion.idcancion
			cancionAux['calificacion'] = cancion.calificacion
			cancionAux['nombrearchivo'] = cancion.nombrearchivo
			cancionAux['nombreAlbum'] = cancion.album_idalbum.nombre
			cancionAux['album_idalbum'] = cancion.album_idalbum.idalbum
			cancionAux['nombreArtista'] = cancion.album_idalbum.artista_idartista.nombre
			listaFinal.append(cancionAux)		
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
			cancionAux['album_idalbum'] = cancionJson['album_idalbum']
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
			albumJson = {}
			albumJson["nombre"] = album["nombre"]
			albumJson["idalbum"] = album["idalbum"]
			albumJson["artista_idartista"] = album["artista_idartista_id"]
			albumJson["genero_idgenero"] = album["genero_idgenero_id"]
			albumJson["compania"] = album["compania"]
			albumJson["biblioteca_idbiblioteca"] = album["biblioteca_idbiblioteca_id"]
			listaAlbumesFinal.append(albumJson)
	return Response(listaAlbumesFinal)


@api_view(['GET'])
def obtenerCancionesAlbum(request):
	idAlbum = request.GET['id']
	listaCanciones = {}
	listaFinal = []
	listaCanciones = Cancion.objects.filter(album_idalbum = idAlbum).values()
	for lista in listaCanciones:
		cancion = {}
		cancion["nombrearchivo"] = lista["nombrearchivo"]
		cancion["album_idalbum"] = lista["album_idalbum_id"]
		cancion["calificacion"] = lista["calificacion"]
		cancion["nombre"] = lista["nombre"]
		cancion["idcancion"] = lista["idcancion"]
		album = Album.objects.get(pk = lista["album_idalbum_id"])
		cancion['nombreAlbum'] = album.nombre		
		cancion['nombreArtista'] = album.artista_idartista.nombre
		listaFinal.append(cancion)
	return Response(listaFinal)


@api_view(['GET'])
def obtenerAlbumPorBiblioteca(request):
	idBiblioteca = request.GET['id']
	listaAlbumes = {}
	listaAlbumesFinal = []
	listaAlbumes = Album.objects.filter(biblioteca_idbiblioteca = idBiblioteca).values()
	for lista in listaAlbumes:
		albumJson = {}
		albumJson["nombre"] = lista["nombre"]
		albumJson["idalbum"] = lista["idalbum"]
		albumJson["artista_idartista"] = lista["artista_idartista_id"]
		albumJson["genero_idgenero"] = lista["genero_idgenero_id"]
		albumJson["compania"] = lista["compania"]
		albumJson["biblioteca_idbiblioteca"] = lista["biblioteca_idbiblioteca_id"]
		listaAlbumesFinal.append(albumJson)
	return Response(listaAlbumesFinal)


@api_view(['GET'])
def obtenerAlbumPorGenero(request):
	idGenero = request.GET['id']
	listaAlbumesFinal = []
	listaAlbumes = Album.objects.filter(genero_idgenero = idGenero).values()
	for album in listaAlbumes:
		albumJson = {}
		albumJson["nombre"] = album["nombre"]
		albumJson["idalbum"] = album["idalbum"]
		albumJson["artista_idartista"] = album["artista_idartista_id"]
		albumJson["genero_idgenero"] = album["genero_idgenero_id"]
		albumJson["compania"] = album["compania"]
		albumJson["biblioteca_idbiblioteca"] = album["biblioteca_idbiblioteca_id"]
		listaAlbumesFinal.append(albumJson)
	return Response(listaAlbumesFinal)



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
			cancionAux['album_idalbum'] = album.get('idalbum')
			cancionAux['nombreAlbum'] = album.get('nombre')
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

@api_view(['GET'])
def iniciarSesion(request):
	nombreUsuario = request.GET['id']
	contrasenaUsuario = request.GET['password']
	respuesta = {'result':'false'}
	try:
		usuario = Usuario.objects.get(pk = nombreUsuario)
	except:
		return Response(respuesta)
	if usuario:
		if (usuario.contrasena ==  contrasenaUsuario):
			print(usuario)
			respuesta = {'result':'true'}
	return Response(respuesta)


@api_view(['GET'])
def bibliotecaPrivada(request):
	idBiblioteca = request.GET['id']
	biblioteca = Biblioteca.objects.get(pk = idBiblioteca)
	biblioteca.publica = 0
	biblioteca.save()
	return Response({'result':'ok'})


@api_view(['GET'])
def bibliotecaPublica(request):
	idBiblioteca = request.GET['id']
	biblioteca = Biblioteca.objects.get(pk = idBiblioteca)
	biblioteca.publica = 1
	biblioteca.save()
	return Response({'result':'ok'})

@api_view(['GET'])
def obtenerCancionesPorGenero(request):
	idAlbum = request.GET['id']
	listaFinal = []
	album = Album.objects.get(pk = idAlbum)	
	listaAlbumes = Album.objects.filter(genero_idgenero = album.genero_idgenero.idgenero).values()
	for album in listaAlbumes:
		listaCanciones = {}
		listaCanciones = Cancion.objects.filter(album_idalbum = album["idalbum"]).values()
		for lista in listaCanciones:
			cancion = {}
			cancion["nombrearchivo"] = lista["nombrearchivo"]
			cancion["album_idalbum"] = lista["album_idalbum_id"]
			cancion["calificacion"] = lista["calificacion"]
			cancion["nombre"] = lista["nombre"]
			cancion["idcancion"] = lista["idcancion"]
			album = Album.objects.get(pk = lista["album_idalbum_id"])
			cancion['nombreAlbum'] = album.nombre		
			cancion['nombreArtista'] = album.artista_idartista.nombre
			listaFinal.append(cancion)
	return Response(listaFinal)