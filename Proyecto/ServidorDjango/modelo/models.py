# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey has `on_delete` set to the desired behavior.
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from __future__ import unicode_literals

from django.db import models


class Album(models.Model):
    idalbum = models.AutoField(db_column='idAlbum', primary_key=True)  # Field name made lowercase.
    nombre = models.CharField(max_length=45)
    anolanzamiento = models.CharField(db_column='anoLanzamiento', max_length=4, blank=True, null=True)  # Field name made lowercase.
    compania = models.CharField(max_length=45, blank=True, null=True)
    artista_idartista = models.ForeignKey('Artista', models.DO_NOTHING, db_column='artista_idArtista')  # Field name made lowercase.
    genero_idgenero = models.ForeignKey('Genero', models.DO_NOTHING, db_column='genero_idGenero')  # Field name made lowercase.
    biblioteca_idbiblioteca = models.ForeignKey('Biblioteca', models.DO_NOTHING, db_column='biblioteca_idBiblioteca')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'album'


class Artista(models.Model):
    idartista = models.AutoField(db_column='idArtista', primary_key=True)  # Field name made lowercase.
    nombre = models.CharField(max_length=45)

    class Meta:
        managed = False
        db_table = 'artista'


class Biblioteca(models.Model):
    idbiblioteca = models.AutoField(db_column='idBiblioteca', primary_key=True)  # Field name made lowercase.
    publica = models.IntegerField()
    usuario_nombreusuario = models.ForeignKey('Usuario', models.DO_NOTHING, db_column='usuario_nombreUsuario')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'biblioteca'


class Cancion(models.Model):
    idcancion = models.AutoField(db_column='idCancion', primary_key=True)  # Field name made lowercase.
    nombre = models.CharField(max_length=45)
    calificacion = models.IntegerField(blank=True, null=True)
    nombrearchivo = models.CharField(db_column='nombreArchivo', max_length=100)  # Field name made lowercase.
    album_idalbum = models.ForeignKey(Album, models.DO_NOTHING, db_column='album_idAlbum')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'cancion'


class Genero(models.Model):
    idgenero = models.AutoField(db_column='idGenero', primary_key=True)  # Field name made lowercase.
    nombre = models.CharField(max_length=45)

    class Meta:
        managed = False
        db_table = 'genero'


class Listareproduccion(models.Model):
    idlistareproduccion = models.AutoField(db_column='idlistaReproduccion', primary_key=True)  # Field name made lowercase.
    nombre = models.CharField(max_length=45)
    usuario_nombreusuario = models.ForeignKey('Usuario', models.DO_NOTHING, db_column='usuario_nombreUsuario')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'listaReproduccion'


class ListareproduccionHasCancion(models.Model):
    listareproduccion_idlistareproduccion = models.ForeignKey(Listareproduccion, models.DO_NOTHING, db_column='listaReproduccion_idlistaReproduccion')  # Field name made lowercase.
    cancion_idcancion = models.ForeignKey(Cancion, models.DO_NOTHING, db_column='cancion_idCancion')  # Field name made lowercase.
    idreproduccioncancion = models.AutoField(db_column='idReproduccionCancion', primary_key=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'listaReproduccion_has_cancion'


class Usuario(models.Model):
    nombreusuario = models.CharField(db_column='nombreUsuario', primary_key=True, max_length=20)  # Field name made lowercase.
    contrasena = models.CharField(max_length=64)
    nombres = models.CharField(max_length=45)
    apellidos = models.CharField(max_length=45)
    correo = models.CharField(max_length=45)

    class Meta:
        managed = False
        db_table = 'usuario'
