from rest_framework import serializers
from modelo.models import *

class AlbumSerializer(serializers.ModelSerializer):
	class Meta:
		model = Album
		fields = ('__all__')

class ArtistaSerializer(serializers.ModelSerializer):
	class Meta:
		model = Artista
		fields = ('__all__')


class BibliotecaSerializer(serializers.ModelSerializer):
	class Meta:
		model = Biblioteca
		fields = ('__all__')

class CancionSerializer(serializers.ModelSerializer):
	class Meta:
		model = Cancion
		fields = ('__all__')

class GeneroSerializer(serializers.ModelSerializer):
	class Meta:
		model = Genero
		fields = ('__all__')

class ListareproduccionSerializer(serializers.ModelSerializer):
	class Meta:
		model = Listareproduccion
		fields = ('__all__')

class ListareproduccionHasCancionSerializer(serializers.ModelSerializer):
	class Meta:
		model = ListareproduccionHasCancion
		fields = ('__all__')

class UsuarioSerializer(serializers.ModelSerializer):
	class Meta:
		model = Usuario
		fields = ('__all__')