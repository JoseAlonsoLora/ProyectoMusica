-- MySQL Script generated by MySQL Workbench
-- sáb 16 jun 2018 11:49:41 CDT
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
  `publica` TINYINT NOT NULL,
  `usuario_nombreUsuario` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idBiblioteca`),
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
  PRIMARY KEY (`idAlbum`),
  INDEX `fk_album_artista_idx` (`artista_idArtista` ASC),
  INDEX `fk_album_genero1_idx` (`genero_idGenero` ASC),
  INDEX `fk_album_biblioteca1_idx` (`biblioteca_idBiblioteca` ASC),
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
    FOREIGN KEY (`biblioteca_idBiblioteca`)
    REFERENCES `proyectoMusica`.`biblioteca` (`idBiblioteca`)
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
  `nombreArchivo` VARCHAR(100) NOT NULL,
  `album_idAlbum` INT NOT NULL,
  PRIMARY KEY (`idCancion`),
  INDEX `fk_cancion_album1_idx` (`album_idAlbum` ASC),
  CONSTRAINT `fk_cancion_album1`
    FOREIGN KEY (`album_idAlbum`)
    REFERENCES `proyectoMusica`.`album` (`idAlbum`)
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
  PRIMARY KEY (`idlistaReproduccion`),
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
  `cancion_idCancion` INT NOT NULL,
  `idReproduccionCancion` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_listaReproduccion_has_cancion_cancion1_idx` (`cancion_idCancion` ASC),
  INDEX `fk_listaReproduccion_has_cancion_listaReproduccion1_idx` (`listaReproduccion_idlistaReproduccion` ASC),
  PRIMARY KEY (`idReproduccionCancion`),
  CONSTRAINT `fk_listaReproduccion_has_cancion_listaReproduccion1`
    FOREIGN KEY (`listaReproduccion_idlistaReproduccion`)
    REFERENCES `proyectoMusica`.`listaReproduccion` (`idlistaReproduccion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_listaReproduccion_has_cancion_cancion1`
    FOREIGN KEY (`cancion_idCancion`)
    REFERENCES `proyectoMusica`.`cancion` (`idCancion`)
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
