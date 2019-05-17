-- MySQL Workbench Forward Engineering
DROP schema `hospital_db`;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema hospital_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hospital_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hospital_db` DEFAULT CHARACTER SET utf8 ;
USE `hospital_db` ;

-- -----------------------------------------------------
-- Table `hospital_db`.`Patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital_db`.`Role` (
  `ID` INT NOT NULL,
  `NAME` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;
  
CREATE TABLE IF NOT EXISTS `hospital_db`.`Patient` (
  `ID` INT NOT NULL,
  `NAME` VARCHAR(100) NOT NULL,
  `TELEPHONE` VARCHAR(11) NULL,
  `GENDER` VARCHAR(6) NOT NULL,
  `REGISTERATIONDATE` DATETIME NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `hospital_db`.`PATIENTALERGIES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital_db`.`PATIENTALERGIES`(
	`PATIENTID` INT NOT NULL,
	`PATIENTALERGIE` VARCHAR(45) NOT NULL,	
	PRIMARY KEY (`PATIENTID`, `PATIENTALERGIE`),
	CONSTRAINT `fk_PATIENTALERGIES_1`
    FOREIGN KEY (`PATIENTID`)
    REFERENCES `hospital_db`.`Patient` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
	
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital_db`.`STAFF`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `hospital_db`.`STAFF` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `ROLE` VARCHAR(15) NOT NULL,
  `TELEPHONE` VARCHAR(12) NOT NULL UNIQUE,
  `SALARY` DOUBLE NOT NULL,
  `PASS` VARCHAR(45) DEFAULT '1',
  `HIRINGTDATE` DATETIME NULL,
  PRIMARY KEY (`ID`))
  
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `hospital_db`.`APPOINTMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital_db`.`APPOINTMENT` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `PATIENTID` INT NOT NULL,
  `DOCTORID` INT NOT NULL,
  `HOUR` VARCHAR(10) NULL,
  `APPOINTMENTDATE` DATETIME NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_APPOINTMENT_1_idx` (`PATIENTID` ASC),
  INDEX `fk_APPOINTMENT_2_idx` (`DOCTORID` ASC),
  CONSTRAINT `fk_APPOINTMENT_1`
    FOREIGN KEY (`PATIENTID`)
    REFERENCES `hospital_db`.`Patient` (`ID`)
    ON DELETE cascade
    ON UPDATE cascade,
  CONSTRAINT `fk_APPOINTMENT_2`
    FOREIGN KEY (`DOCTORID`)
    REFERENCES `hospital_db`.`STAFF` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital_db`.`MEDICATION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital_db`.`MEDICATION` (
  `PRICE` INT NOT NULL,
  `NAME` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`NAME`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital_db`.`PATIENTCASE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital_db`.`PATIENTCASE` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `PATIENTID` INT NOT NULL,
  `DISEASE` VARCHAR(45) NOT NULL,
  `MEDICATION` VARCHAR(20) NOT NULL,
  `CASEDATE` DATETIME NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_PATIENTCASE_1_idx` (`PATIENTID` ASC),
  INDEX `fk_PATIENTCASE_2_idx` (`MEDICATION` ASC),
  CONSTRAINT `fk_PATIENTCASE_1`
    FOREIGN KEY (`PATIENTID`)
    REFERENCES `hospital_db`.`Patient` (`ID`)
    ON DELETE cascade
    ON UPDATE cascade,
  CONSTRAINT `fk_PATIENTCASE_2`
    FOREIGN KEY (`MEDICATION`)
    REFERENCES `hospital_db`.`MEDICATION` (`NAME`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `hospital_db`.`AVAILABILITY` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `DOCTORID` INT NOT NULL,
  `DATE` date NOT NULL,
  `HOUR` time NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_AVAILABILITY_1`
    FOREIGN KEY (`DOCTORID`)
    REFERENCES `hospital_db`.`STAFF` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_AVAILABILITY_2` UNIQUE (DATE,HOUR)
    )
ENGINE = InnoDB;




SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
