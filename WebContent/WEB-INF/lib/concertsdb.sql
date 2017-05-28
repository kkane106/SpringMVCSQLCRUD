-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema concerteventdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `concerteventdb` ;

-- -----------------------------------------------------
-- Schema concerteventdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `concerteventdb` DEFAULT CHARACTER SET utf8 ;
USE `concerteventdb` ;

-- -----------------------------------------------------
-- Table `venue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `venue` ;

CREATE TABLE IF NOT EXISTS `venue` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concert`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `concert` ;

CREATE TABLE IF NOT EXISTS `concert` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `performer_id` VARCHAR(100) NOT NULL,
  `venue_id` INT NOT NULL,
  `date` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_concert_venue_idx` (`venue_id` ASC),
  CONSTRAINT `fk_concert_venue`
    FOREIGN KEY (`venue_id`)
    REFERENCES `venue` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `performer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `performer` ;

CREATE TABLE IF NOT EXISTS `performer` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NULL,
  `photo_url` VARCHAR(500) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concert_has_performer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `concert_has_performer` ;

CREATE TABLE IF NOT EXISTS `concert_has_performer` (
  `concert_id` INT NOT NULL,
  `performer_id` INT NOT NULL,
  PRIMARY KEY (`concert_id`, `performer_id`),
  INDEX `fk_concert_has_performer_performer1_idx` (`performer_id` ASC),
  INDEX `fk_concert_has_performer_concert1_idx` (`concert_id` ASC),
  CONSTRAINT `fk_concert_has_performer_concert1`
    FOREIGN KEY (`concert_id`)
    REFERENCES `concert` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_concert_has_performer_performer1`
    FOREIGN KEY (`performer_id`)
    REFERENCES `performer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concert_has_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `concert_has_user` ;

CREATE TABLE IF NOT EXISTS `concert_has_user` (
  `concert_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`concert_id`, `user_id`),
  INDEX `fk_concert_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_concert_has_user_concert1_idx` (`concert_id` ASC),
  CONSTRAINT `fk_concert_has_user_concert1`
    FOREIGN KEY (`concert_id`)
    REFERENCES `concert` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_concert_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO concertgoer@localhost;
 DROP USER concertgoer@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'concertgoer'@'localhost' IDENTIFIED BY 'Chowchow21';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'concertgoer'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `venue`
-- -----------------------------------------------------
START TRANSACTION;
USE `concerteventdb`;
INSERT INTO `venue` (`id`, `name`, `city`) VALUES (1, 'Red Rocks', 'Morrison ');
INSERT INTO `venue` (`id`, `name`, `city`) VALUES (2, 'Fillmore Auditorium', 'Denver');
INSERT INTO `venue` (`id`, `name`, `city`) VALUES (3, 'Larimer Lounge', 'Denver');
INSERT INTO `venue` (`id`, `name`, `city`) VALUES (4, 'Sports Authority Field', 'Denver');
INSERT INTO `venue` (`id`, `name`, `city`) VALUES (5, 'Ogden Theater', 'Denver');

COMMIT;


-- -----------------------------------------------------
-- Data for table `concert`
-- -----------------------------------------------------
START TRANSACTION;
USE `concerteventdb`;
INSERT INTO `concert` (`id`, `performer_id`, `venue_id`, `date`) VALUES (1, '1', 1, '05/29/2017');
INSERT INTO `concert` (`id`, `performer_id`, `venue_id`, `date`) VALUES (2, '2, 3', 1, '06/01/2017');
INSERT INTO `concert` (`id`, `performer_id`, `venue_id`, `date`) VALUES (3, '4, 5', 1, '06/18/2017');
INSERT INTO `concert` (`id`, `performer_id`, `venue_id`, `date`) VALUES (4, '6', 1, '07/04/2017');
INSERT INTO `concert` (`id`, `performer_id`, `venue_id`, `date`) VALUES (5, '7', 1, '07/05/2017');
INSERT INTO `concert` (`id`, `performer_id`, `venue_id`, `date`) VALUES (6, '8', 1, '07/11/2017');
INSERT INTO `concert` (`id`, `performer_id`, `venue_id`, `date`) VALUES (7, '9', 1, '07/26/2017');
INSERT INTO `concert` (`id`, `performer_id`, `venue_id`, `date`) VALUES (8, '10, 11', 1, '08/05/2017');
INSERT INTO `concert` (`id`, `performer_id`, `venue_id`, `date`) VALUES (9, '12', 1, '08/07/2017');
INSERT INTO `concert` (`id`, `performer_id`, `venue_id`, `date`) VALUES (10, '13', 1, '08/25/2017');
INSERT INTO `concert` (`id`, `performer_id`, `venue_id`, `date`) VALUES (11, '14', 1, '09/27/2017');
INSERT INTO `concert` (`id`, `performer_id`, `venue_id`, `date`) VALUES (12, '15', 2, '06/06/2017');
INSERT INTO `concert` (`id`, `performer_id`, `venue_id`, `date`) VALUES (13, '16', 1, '10/06/2017');
INSERT INTO `concert` (`id`, `performer_id`, `venue_id`, `date`) VALUES (14, '17, 18', 1, '05/26/2017');
INSERT INTO `concert` (`id`, `performer_id`, `venue_id`, `date`) VALUES (15, '19', 3, '06/05/2017');
INSERT INTO `concert` (`id`, `performer_id`, `venue_id`, `date`) VALUES (16, '20', 4, '06/07/2017');
INSERT INTO `concert` (`id`, `performer_id`, `venue_id`, `date`) VALUES (17, '21', 5, '12/03/2017');

COMMIT;


-- -----------------------------------------------------
-- Data for table `performer`
-- -----------------------------------------------------
START TRANSACTION;
USE `concerteventdb`;
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (1, 'Tom Petty & The Heartbreakers', 'http://highlightmagazine.net/wp-content/uploads/2014/05/1323982745tompetty_img03_hires.jpg');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (2, 'Chromeo', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRS08W6QU7guuipOQ49sLhMdXhDgnnD-FBpAhH3_PKDSIygFcIv');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (3, 'Rufus Du Sol', 'https://bettyandkora.files.wordpress.com/2016/01/rufus.jpg');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (4, 'Portugal. The Man', 'https://fanart.tv/api/download.php?type=download&image=58871&section=2');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (5, 'Local Natives', 'https://s-media-cache-ak0.pinimg.com/originals/3a/57/0c/3a570cc200b6d5ac7ba882287fa75720.jpg');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (6, 'Blues Traveler', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQoF_C3apK1blJar7t7efMvOzAubQh6V1Y-2HVyVEQqAtvF4QOq');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (7, 'Flume', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQG1xbyDFU6PksiG3VL3STqL_tIcP4UsEB0tRI2VYq8K9VRTJd8');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (8, 'Beck', 'http://cdn.pitchfork.com/media/beck1_1.jpg');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (9, 'Glass Animals', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRrkJlxwZV5K7EqjAv4t75XGuqCT0i9gfFXfEJs3OhkA9P1Z54m0A');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (10, 'Thievery Corporation', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDwH40LhJy1cb92TJuasxaNmqm54P20lXGRtDTj4Wm4RFVwD6myw');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (11, 'Devotchka', 'http://www.anti.com/media/press/1b5d0d1dde5c2b266017256d9dd10cec.jpg');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (12, 'Alt-J', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHSe9Cng14ruHKxLJg8wcIT1i0gaFj0IBYM2nIPAziX5hh1NJCkQ');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (13, 'Father John Misty', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYeb54_rf6MiiDkres0aXGGUrW4Owz2m4PzFuVgH8DfEefWg5WkA');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (14, 'Fleet Foxes', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGA4LuUp5SwTRomnl62FHAvQwb5qMSMN7gYLx5yX7EnA0w3ok8');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (15, 'Modest Mouse', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRw1AlSa_zg1XEa6eVF4AYT3H19ZYdYpck8awbthZiCBqExW9x4Tw');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (16, 'Milky Chance', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSn3da0awPH3zpMxg5PPno-gBrtrQq564g20R3vyjImOBYsN-6P');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (17, 'Jethro Tull', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSG0GHm2Z-U0_2c3W32DT2Ta7Yy7AT2Pdw4TaGH6iQIgAuJtlYdvw');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (18, 'Ian Anderson', 'https://s-media-cache-ak0.pinimg.com/originals/b5/94/18/b59418e0b048f8ff2611b8ec4f8d506a.jpg');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (19, 'Surfer Blood', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTiXkuMV1m0pYp2vhByvk4DeHkosZBe3Lm1cR0m91RcgLn65S6d');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (20, 'Metallica', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfEcF75E_mh4Hv-K95A3-EjTIyqiCXAmd37JDKVrHgDkOcvnkd');
INSERT INTO `performer` (`id`, `name`, `photo_url`) VALUES (21, 'Grizzly Bear', 'http://cps-static.rovicorp.com/3/JPG_400/MI0001/435/MI0001435753.jpg?partner=allrovi.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `concert_has_performer`
-- -----------------------------------------------------
START TRANSACTION;
USE `concerteventdb`;
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (1, 1);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (2, 2);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (3, 4);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (4, 6);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (5, 7);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (6, 8);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (7, 9);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (8, 10);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (9, 12);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (10, 13);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (11, 14);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (12, 15);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (13, 16);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (14, 17);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (15, 19);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (16, 20);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (17, 21);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (2, 3);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (8, 11);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (14, 18);
INSERT INTO `concert_has_performer` (`concert_id`, `performer_id`) VALUES (3, 5);

COMMIT;

