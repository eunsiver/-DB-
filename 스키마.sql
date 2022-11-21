-- MySQL Workbench Forward Engineering
DROP DATABASE IF EXISTS  madang;
DROP USER IF EXISTS  madang@localhost;


create user madang@localhost identified WITH mysql_native_password  by 'madang';
create database madang;
grant all privileges on madang.* to madang@localhost with grant option;
commit;

USE `madang`;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- -----------------------------------------------------
-- Table `madang`.`Movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `madang`.`Movie` (
  `movieId` INT NOT NULL AUTO_INCREMENT,
  `movieName` VARCHAR(100) NULL,
  `runningTime` VARCHAR(10) NULL,
  `rating` FLOAT NULL,
  `director` VARCHAR(45) NULL,
  `actor` VARCHAR(45) NULL,
  `genre` VARCHAR(45) NULL,
  `introduction` VARCHAR(500) NULL,
  `releaseDate` DATE NULL,
  PRIMARY KEY (`movieId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `madang`.`Theater`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `madang`.`Theater` (
  `theaterId` INT NOT NULL,
  `seatCapacity` INT NULL,
  `isTheaterUsed` VARCHAR(1) NULL DEFAULT 'O',
  PRIMARY KEY (`theaterId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `madang`.`Schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `madang`.`Schedule` (
  `scheduleId` INT NOT NULL AUTO_INCREMENT,
  `movieId` INT NOT NULL,
  `theaterId` INT NOT NULL,
  `date` DATE NULL,
  `day` VARCHAR(5) NULL,
  `session` INT NULL,
  `startTime` VARCHAR(45) NULL,
  PRIMARY KEY (`scheduleId`),
  INDEX `fk_Schedule_Movie_idx` (`movieId` ASC) VISIBLE,
  INDEX `fk_Schedule_Theater1_idx` (`theaterId` ASC) VISIBLE,
  CONSTRAINT `fk_Schedule_Movie`
    FOREIGN KEY (`movieId`)
    REFERENCES `madang`.`Movie` (`movieId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Schedule_Theater1`
    FOREIGN KEY (`theaterId`)
    REFERENCES `madang`.`Theater` (`theaterId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `madang`.`Seat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `madang`.`Seat` (
  `seatId` INT NOT NULL,
  `theaterId` INT NOT NULL,
  `isSeated` VARCHAR(1) NULL DEFAULT 'X',
  PRIMARY KEY (`seatId`),
  INDEX `fk_Seats_Theater1_idx` (`theaterId` ASC) VISIBLE,
  CONSTRAINT `fk_Seats_Theater1`
    FOREIGN KEY (`theaterId`)
    REFERENCES `madang`.`Theater` (`theaterId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `madang`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `madang`.`User` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NULL,
  `phoneNum` VARCHAR(45) NULL,
  `mail` VARCHAR(100) NULL,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `madang`.`Reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `madang`.`Reservation` (
  `reservationId` INT NOT NULL AUTO_INCREMENT,
  `paymentMethod` VARCHAR(45) NULL,
  `isPaid` VARCHAR(1) NULL DEFAULT 'O',
  `price` INT NULL,
  `userId` INT NOT NULL,
  `paymentDate` TIMESTAMP NULL,
  PRIMARY KEY (`reservationId`),
  INDEX `fk_Reservation_User1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_Reservation_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `madang`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `madang`.`Ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `madang`.`Ticket` (
  `ticketId` INT NOT NULL AUTO_INCREMENT,
  `scheduleId` INT NOT NULL,
  `theaterId` INT NOT NULL,
  `seatId` INT NOT NULL,
  `reservationId` INT NULL,
  `isTicketed` VARCHAR(1) NULL DEFAULT 'X',
  `standartPrice` INT NULL DEFAULT 13000,
  `salePrice` INT NULL,
  PRIMARY KEY (`ticketId`),
  INDEX `fk_Ticket_Schedule1_idx` (`scheduleId` ASC) VISIBLE,
  INDEX `fk_Ticket_Theater1_idx` (`theaterId` ASC) VISIBLE,
  INDEX `fk_Ticket_Seats1_idx` (`seatId` ASC) VISIBLE,
  INDEX `fk_Ticket_Reservation1_idx` (`reservationId` ASC) VISIBLE,
  CONSTRAINT `fk_Ticket_Schedule1`
    FOREIGN KEY (`scheduleId`)
    REFERENCES `madang`.`Schedule` (`scheduleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ticket_Theater1`
    FOREIGN KEY (`theaterId`)
    REFERENCES `madang`.`Theater` (`theaterId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ticket_Seats1`
    FOREIGN KEY (`seatId`)
    REFERENCES `madang`.`Seat` (`seatId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ticket_Reservation1`
    FOREIGN KEY (`reservationId`)
    REFERENCES `madang`.`Reservation` (`reservationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;