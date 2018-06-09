-- MySQL Script generated by MySQL Workbench
-- Fri Jun  8 17:07:37 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema proyectoMusica
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `proyectoMusica` ;

-- -----------------------------------------------------
-- Schema proyectoMusica
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `proyectoMusica` DEFAULT CHARACTER SET utf8 ;
USE `proyectoMusica` ;

-- -----------------------------------------------------
-- Table `proyectoMusica`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyectoMusica`.`usuario` ;

CREATE TABLE IF NOT EXISTS `proyectoMusica`.`usuario` (
  `nombreUsuario` VARCHAR(20) NOT NULL,
  `contrasena` VARCHAR(64) NOT NULL,
  `nombres` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nombreUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectoMusica`.`artista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyectoMusica`.`artista` ;

CREATE TABLE IF NOT EXISTS `proyectoMusica`.`artista` (
  `idArtista` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idArtista`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectoMusica`.`genero`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyectoMusica`.`genero` ;

CREATE TABLE IF NOT EXISTS `proyectoMusica`.`genero` (
  `idGenero` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idGenero`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectoMusica`.`biblioteca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyectoMusica`.`biblioteca` ;

CREATE TABLE IF NOT EXISTS `proyectoMusica`.`biblioteca` (
  `idBiblioteca` INT NOT NULL AUTO_INCREMENT,
  `usuario_nombreUsuario` VARCHAR(20) NOT NULL,
  `publica` TINYINT NOT NULL,
  PRIMARY KEY (`idBiblioteca`, `usuario_nombreUsuario`),
  INDEX `fk_biblioteca_usuario1_idx` (`usuario_nombreUsuario` ASC),
  CONSTRAINT `fk_biblioteca_usuario1`
    FOREIGN KEY (`usuario_nombreUsuario`)
    REFERENCES `proyectoMusica`.`usuario` (`nombreUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectoMusica`.`album`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyectoMusica`.`album` ;

CREATE TABLE IF NOT EXISTS `proyectoMusica`.`album` (
  `idAlbum` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `anoLanzamiento` VARCHAR(4) NULL,
  `compania` VARCHAR(45) NULL,
  `artista_idArtista` INT NOT NULL,
  `genero_idGenero` INT NOT NULL,
  `biblioteca_idBiblioteca` INT NOT NULL,
  `biblioteca_usuario_nombreUsuario` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idAlbum`, `artista_idArtista`, `genero_idGenero`, `biblioteca_idBiblioteca`, `biblioteca_usuario_nombreUsuario`),
  INDEX `fk_album_artista_idx` (`artista_idArtista` ASC),
  INDEX `fk_album_genero1_idx` (`genero_idGenero` ASC),
  INDEX `fk_album_biblioteca1_idx` (`biblioteca_idBiblioteca` ASC, `biblioteca_usuario_nombreUsuario` ASC),
  CONSTRAINT `fk_album_artista`
    FOREIGN KEY (`artista_idArtista`)
    REFERENCES `proyectoMusica`.`artista` (`idArtista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_album_genero1`
    FOREIGN KEY (`genero_idGenero`)
    REFERENCES `proyectoMusica`.`genero` (`idGenero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_album_biblioteca1`
    FOREIGN KEY (`biblioteca_idBiblioteca` , `biblioteca_usuario_nombreUsuario`)
    REFERENCES `proyectoMusica`.`biblioteca` (`idBiblioteca` , `usuario_nombreUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectoMusica`.`cancion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyectoMusica`.`cancion` ;

CREATE TABLE IF NOT EXISTS `proyectoMusica`.`cancion` (
  `idCancion` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `calificacion` INT NULL,
  `nombreArchivo` VARCHAR(45) NOT NULL,
  `album_idAlbum` INT NOT NULL,
  `album_artista_idArtista` INT NOT NULL,
  `album_genero_idGenero` INT NOT NULL,
  `album_biblioteca_idBiblioteca` INT NOT NULL,
  `album_biblioteca_usuario_nombreUsuario` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idCancion`, `album_idAlbum`, `album_artista_idArtista`, `album_genero_idGenero`, `album_biblioteca_idBiblioteca`, `album_biblioteca_usuario_nombreUsuario`),
  INDEX `fk_cancion_album1_idx` (`album_idAlbum` ASC, `album_artista_idArtista` ASC, `album_genero_idGenero` ASC, `album_biblioteca_idBiblioteca` ASC, `album_biblioteca_usuario_nombreUsuario` ASC),
  CONSTRAINT `fk_cancion_album1`
    FOREIGN KEY (`album_idAlbum` , `album_artista_idArtista` , `album_genero_idGenero` , `album_biblioteca_idBiblioteca` , `album_biblioteca_usuario_nombreUsuario`)
    REFERENCES `proyectoMusica`.`album` (`idAlbum` , `artista_idArtista` , `genero_idGenero` , `biblioteca_idBiblioteca` , `biblioteca_usuario_nombreUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectoMusica`.`listaReproduccion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyectoMusica`.`listaReproduccion` ;

CREATE TABLE IF NOT EXISTS `proyectoMusica`.`listaReproduccion` (
  `idlistaReproduccion` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `usuario_nombreUsuario` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idlistaReproduccion`, `usuario_nombreUsuario`),
  INDEX `fk_listaReproduccion_usuario1_idx` (`usuario_nombreUsuario` ASC),
  CONSTRAINT `fk_listaReproduccion_usuario1`
    FOREIGN KEY (`usuario_nombreUsuario`)
    REFERENCES `proyectoMusica`.`usuario` (`nombreUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectoMusica`.`listaReproduccion_has_cancion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyectoMusica`.`listaReproduccion_has_cancion` ;

CREATE TABLE IF NOT EXISTS `proyectoMusica`.`listaReproduccion_has_cancion` (
  `listaReproduccion_idlistaReproduccion` INT NOT NULL,
  `listaReproduccion_usuario_nombreUsuario` VARCHAR(20) NOT NULL,
  `cancion_idCancion` INT NOT NULL,
  `cancion_album_idAlbum` INT NOT NULL,
  `cancion_album_artista_idArtista` INT NOT NULL,
  `cancion_album_genero_idGenero` INT NOT NULL,
  `cancion_album_biblioteca_idBiblioteca` INT NOT NULL,
  `cancion_album_biblioteca_usuario_nombreUsuario` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`listaReproduccion_idlistaReproduccion`, `listaReproduccion_usuario_nombreUsuario`, `cancion_idCancion`, `cancion_album_idAlbum`, `cancion_album_artista_idArtista`, `cancion_album_genero_idGenero`, `cancion_album_biblioteca_idBiblioteca`, `cancion_album_biblioteca_usuario_nombreUsuario`),
  INDEX `fk_listaReproduccion_has_cancion_cancion1_idx` (`cancion_idCancion` ASC, `cancion_album_idAlbum` ASC, `cancion_album_artista_idArtista` ASC, `cancion_album_genero_idGenero` ASC, `cancion_album_biblioteca_idBiblioteca` ASC, `cancion_album_biblioteca_usuario_nombreUsuario` ASC),
  INDEX `fk_listaReproduccion_has_cancion_listaReproduccion1_idx` (`listaReproduccion_idlistaReproduccion` ASC, `listaReproduccion_usuario_nombreUsuario` ASC),
  CONSTRAINT `fk_listaReproduccion_has_cancion_listaReproduccion1`
    FOREIGN KEY (`listaReproduccion_idlistaReproduccion` , `listaReproduccion_usuario_nombreUsuario`)
    REFERENCES `proyectoMusica`.`listaReproduccion` (`idlistaReproduccion` , `usuario_nombreUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_listaReproduccion_has_cancion_cancion1`
    FOREIGN KEY (`cancion_idCancion` , `cancion_album_idAlbum` , `cancion_album_artista_idArtista` , `cancion_album_genero_idGenero` , `cancion_album_biblioteca_idBiblioteca` , `cancion_album_biblioteca_usuario_nombreUsuario`)
    REFERENCES `proyectoMusica`.`cancion` (`idCancion` , `album_idAlbum` , `album_artista_idArtista` , `album_genero_idGenero` , `album_biblioteca_idBiblioteca` , `album_biblioteca_usuario_nombreUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- User 'usuarioAredespacio'
-- -----------------------------------------------------
DROP USER IF EXISTS 'usuarioMusica'@'localhost';
CREATE USER 'usuarioMusica'@'localhost' IDENTIFIED BY '#GdrtOP=)-$47"we';
GRANT SELECT,INSERT, UPDATE, DELETE ON proyectoMusica . * TO 'usuarioMusica'@'localhost';
FLUSH PRIVILEGES;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
