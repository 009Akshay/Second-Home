/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.22 : Database - hostel-mgt-boot
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hostel-mgt-boot` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `hostel-mgt-boot`;

/*Table structure for table `allotment` */

DROP TABLE IF EXISTS `allotment`;

CREATE TABLE `allotment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_datetime` datetime(6) DEFAULT NULL,
  `hostel_id` bigint NOT NULL,
  `room_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `hostel` bigint NOT NULL,
  `room` bigint NOT NULL,
  `user` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqarqpr2w81phi5xvdxm517p8m` (`hostel`),
  KEY `FK3qbws21yxjwv8ftllh4k8qbbk` (`room`),
  KEY `FKmcolnieywrwhw7he6miiaekt` (`user`),
  CONSTRAINT `FK3qbws21yxjwv8ftllh4k8qbbk` FOREIGN KEY (`room`) REFERENCES `room` (`id`),
  CONSTRAINT `FKmcolnieywrwhw7he6miiaekt` FOREIGN KEY (`user`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqarqpr2w81phi5xvdxm517p8m` FOREIGN KEY (`hostel`) REFERENCES `hostel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `allotment` */

insert  into `allotment`(`id`,`created_by`,`created_datetime`,`modified_by`,`modified_datetime`,`hostel_id`,`room_id`,`user_id`,`hostel`,`room`,`user`) values 
(1,NULL,NULL,NULL,NULL,1,1,3,1,1,3);

/*Table structure for table `application` */

DROP TABLE IF EXISTS `application`;

CREATE TABLE `application` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_datetime` datetime(6) DEFAULT NULL,
  `address` varchar(225) DEFAULT NULL,
  `description` varchar(225) DEFAULT NULL,
  `hostel_id` bigint NOT NULL,
  `qualification` varchar(225) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `hostel` bigint NOT NULL,
  `user` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKntw1rlwl9lsxytq5sxca5h95l` (`hostel`),
  KEY `FKmilj69pu4uu1gdd607pscfo5j` (`user`),
  CONSTRAINT `FKmilj69pu4uu1gdd607pscfo5j` FOREIGN KEY (`user`) REFERENCES `user` (`id`),
  CONSTRAINT `FKntw1rlwl9lsxytq5sxca5h95l` FOREIGN KEY (`hostel`) REFERENCES `hostel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `application` */

/*Table structure for table `hostel` */

DROP TABLE IF EXISTS `hostel`;

CREATE TABLE `hostel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_datetime` datetime(6) DEFAULT NULL,
  `address` varchar(225) DEFAULT NULL,
  `contact_no` varchar(225) DEFAULT NULL,
  `description` varchar(225) DEFAULT NULL,
  `fee` varchar(225) DEFAULT NULL,
  `name` varchar(225) DEFAULT NULL,
  `type` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `hostel` */

insert  into `hostel`(`id`,`created_by`,`created_datetime`,`modified_by`,`modified_datetime`,`address`,`contact_no`,`description`,`fee`,`name`,`type`) values 
(1,NULL,NULL,NULL,NULL,'Officiis tempor inci','8585858585','At in soluta cum mol','250000','Galena Harris','Boys');

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_datetime` datetime(6) DEFAULT NULL,
  `description` varchar(225) DEFAULT NULL,
  `facilities` varchar(225) DEFAULT NULL,
  `hostel_id` bigint NOT NULL,
  `room_no` varchar(225) DEFAULT NULL,
  `hostel` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK15qff1klo2srt27hvnpm9d8sg` (`hostel`),
  CONSTRAINT `FK15qff1klo2srt27hvnpm9d8sg` FOREIGN KEY (`hostel`) REFERENCES `hostel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `room` */

insert  into `room`(`id`,`created_by`,`created_datetime`,`modified_by`,`modified_datetime`,`description`,`facilities`,`hostel_id`,`room_no`,`hostel`) values 
(1,NULL,NULL,NULL,NULL,'ergergreg','ergergerg',1,'101',1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_datetime` datetime(6) DEFAULT NULL,
  `address` varchar(225) DEFAULT NULL,
  `city` varchar(225) DEFAULT NULL,
  `confirm_password` varchar(255) DEFAULT NULL,
  `contact_no` varchar(225) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email_id` varchar(225) DEFAULT NULL,
  `first_name` varchar(225) DEFAULT NULL,
  `gender` varchar(225) DEFAULT NULL,
  `last_name` varchar(225) DEFAULT NULL,
  `password` varchar(225) DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`created_by`,`created_datetime`,`modified_by`,`modified_datetime`,`address`,`city`,`confirm_password`,`contact_no`,`dob`,`email_id`,`first_name`,`gender`,`last_name`,`password`,`role_id`,`user_name`) values 
(1,NULL,NULL,NULL,NULL,'efvefvergvergveg','BIBIIBBII',NULL,'9685455868','1997-10-10','Admin@gmail.com','Admin','Male','Admi','Admin@123',1,'Admin123'),
(2,NULL,NULL,NULL,NULL,'Sit est saepe dolor ','Numquam pariatur Qu',NULL,'9685456585','1997-10-10','bagifuqid@mailinator.com','Sean','Female','Garrison','Pa$$w0rd!',3,'vemyzywyv123'),
(3,NULL,NULL,NULL,NULL,'Et nulla aliquip und','Et aliqua Sit nisi',NULL,'9685748574','1997-10-10','nogoj@mailinator.com','Orson','Male','Wallace','Pa$$w0rd!',2,'ryhajijal123');

/*Table structure for table `visitor` */

DROP TABLE IF EXISTS `visitor`;

CREATE TABLE `visitor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_datetime` datetime(6) DEFAULT NULL,
  `address` varchar(225) DEFAULT NULL,
  `contact_no` varchar(225) DEFAULT NULL,
  `name` varchar(225) DEFAULT NULL,
  `purpose` varchar(225) DEFAULT NULL,
  `relation` varchar(225) DEFAULT NULL,
  `student_name` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `visitor` */

insert  into `visitor`(`id`,`created_by`,`created_datetime`,`modified_by`,`modified_datetime`,`address`,`contact_no`,`name`,`purpose`,`relation`,`student_name`) values 
(1,NULL,NULL,NULL,NULL,'Molestiae blanditiis','9685456585','Jaime Bentley','Excepteur nulla vel ','Vitae enim odio expe','Darrel Dickerson');

/*Table structure for table `warden` */

DROP TABLE IF EXISTS `warden`;

CREATE TABLE `warden` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_datetime` datetime(6) DEFAULT NULL,
  `hostel_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `hostel` bigint NOT NULL,
  `warden` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7brj76ar7v4oko43h1r3daqdb` (`hostel`),
  KEY `FKrj9bm7th6nehq5pefxwmk4lvt` (`warden`),
  CONSTRAINT `FK7brj76ar7v4oko43h1r3daqdb` FOREIGN KEY (`hostel`) REFERENCES `hostel` (`id`),
  CONSTRAINT `FKrj9bm7th6nehq5pefxwmk4lvt` FOREIGN KEY (`warden`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `warden` */

insert  into `warden`(`id`,`created_by`,`created_datetime`,`modified_by`,`modified_datetime`,`hostel_id`,`user_id`,`hostel`,`warden`) values 
(1,NULL,NULL,NULL,NULL,1,2,1,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
