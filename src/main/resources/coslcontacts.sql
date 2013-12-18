-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.16 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4156
-- Date/time:                    2013-12-15 20:21:03
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for mcc
CREATE DATABASE IF NOT EXISTS `mcc` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mcc`;


-- Dumping structure for table mcc.app_user
CREATE TABLE IF NOT EXISTS `app_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `address` varchar(150) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `postal_code` varchar(15) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL,
  `credentials_expired` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `account_enabled` bit(1) DEFAULT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `password_hint` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table mcc.app_user: ~2 rows (approximately)
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` (`id`, `account_expired`, `account_locked`, `address`, `city`, `country`, `postal_code`, `province`, `credentials_expired`, `email`, `account_enabled`, `first_name`, `last_name`, `password`, `password_hint`, `phone_number`, `username`, `version`, `website`) VALUES
	(-2, b'00000000', b'00000000', '', 'Denver', 'US', '80210', 'CO', b'00000000', 'matt@raibledesigns.com', b'10000000', 'Matt', 'Raible', 'a40546cc4fd6a12572828bb803380888ad1bfdab', 'Not a female kitty.', '', 'admin', 1, 'http://raibledesigns.com'),
	(-1, b'00000000', b'00000000', '', 'Denver', 'US', '80210', 'CO', b'00000000', 'matt_raible@yahoo.com', b'10000000', 'Tomcat', 'User', 'b6b1f4781776979c0775c71ebdd8bdc084aac5fe', 'A male kitty.', '', 'user', 1, 'http://tomcat.apache.org');
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;


-- Dumping structure for table mcc.car
CREATE TABLE IF NOT EXISTS `car` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table mcc.car: ~0 rows (approximately)
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
/*!40000 ALTER TABLE `car` ENABLE KEYS */;


-- Dumping structure for table mcc.department
CREATE TABLE IF NOT EXISTS `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_ch` varchar(150) DEFAULT NULL,
  `name_es` varchar(150) DEFAULT NULL,
  `ord` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Dumping data for table mcc.department: ~9 rows (approximately)
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`id`, `name_ch`, `name_es`, `ord`) VALUES
	(1, '班子成员', 'Altos directivos', 1),
	(2, '行政人事部', 'Dept. de Administracion & RH', 2),
	(3, '物资装备部', 'Dept. de  Equipos y Procura', 3),
	(4, '市场营运部', 'Dept. de Marketing', 4),
	(5, '作业安全环保部', 'Dept. de Operacion y HSE', 5),
	(6, '计划财务部', 'Dept. de Planeacion y Finanzas', 6),
	(7, '墨西哥作业项目组', 'Dept. de Equipos Modulares', 7),
	(8, 'COSL', 'Confidence', 8),
	(9, '海洋石油936', 'HYSY 936', 9);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;


-- Dumping structure for table mcc.opt
CREATE TABLE IF NOT EXISTS `opt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_edit_persons_time` timestamp NULL DEFAULT NULL,
  `who_last_edit_persons` varchar(155) DEFAULT NULL,
  `changes` int(11) NOT NULL DEFAULT '0',
  `other` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table mcc.opt: ~1 rows (approximately)
/*!40000 ALTER TABLE `opt` DISABLE KEYS */;
INSERT INTO `opt` (`id`, `last_edit_persons_time`, `who_last_edit_persons`, `changes`, `other`) VALUES
	(1, '2013-12-15 13:20:17', 'user', 1, 'other');
/*!40000 ALTER TABLE `opt` ENABLE KEYS */;


-- Dumping structure for table mcc.person
CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `ext` varchar(255) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `skype` varchar(100) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `ord` int(10) DEFAULT '9999',
  `department_id` bigint(20) DEFAULT NULL,
  `position_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8E4887755E21D835` (`department_id`),
  KEY `FK8E488775DE0A2795` (`position_id`),
  CONSTRAINT `FK8E4887755E21D835` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FK8E488775DE0A2795` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- Dumping data for table mcc.person: ~22 rows (approximately)
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`id`, `email`, `ext`, `first_name`, `last_name`, `mobile`, `skype`, `user_name`, `ord`, `department_id`, `position_id`) VALUES
	(2, 'liuxm@cosl.mx', '800', '刘小敏', 'Liu Xiaoming', '93812321', '', NULL, 1, 1, 1),
	(3, 'contractcost2@cosl.mx', '632', 'Octavio Reyes', 'Santos', '93817736262', 'osreyes', NULL, 9999, 4, 4),
	(4, 'yichao@cosl.mx', '234', '朱已超', 'Yichoa Zhu', '342345345', 'yichao', NULL, 9999, 1, 1),
	(5, 'rert', '543', 'ererwe', 'trert', '534', '4444445', NULL, 9999, 1, 1),
	(6, '453', '435', '345', '4534', '3453', '4345', NULL, 2, 1, 1),
	(7, 'ter', 'erter', 'hgfg', 'hfgh', 'erter', 'tertddd', NULL, 9999, 2, 1),
	(8, 'ter', 'ert', 'erw', 'erwe', 'tre', 'tertddde', NULL, 9999, 3, 3),
	(9, 'tyur', 'tyutry', 'retre', 'ytry', 'rtyu', 'tyur', NULL, 9999, 2, 2),
	(10, 'tyer', 'rty', 'yityuiyu', 'ityui', 'rtyer', 'tyrty', NULL, 3, 3, 3),
	(11, 'kossell@gmail.com', 'rtyu', 'yichao', 'x', '529381201200', 'tyru', NULL, 9999, 1, 3),
	(12, 'tr', 'rty', 'yichaoz', 'try', 'rtyerrt', 'ytr', NULL, 9999, 1, 1),
	(13, 'ertwert', '453', '我wrer', 'Werwerw', 'rewerwret', 'reter', NULL, 9999, 6, 1),
	(14, 'socorocer@hotmail.com', '332', 'kkk', 'jjjj', '9381201200', 'sdsd', NULL, 9999, 5, 1),
	(15, 'hgf43', '34', 'rtert', 'yyyy', '56457', 'skk.aa', NULL, 9999, 4, 2),
	(16, '33423', '12', 'aaa', 'bbb', 'ee', 'sdsf', NULL, 9999, 5, 1),
	(17, 'hfgh', 'fgh', 'ds', 'gh', 'fdsgfd', 'ghfh', NULL, 9999, 5, 1),
	(18, 'yichao@cosl.mx', 'yr', 'Yichao', 'Zhu', '529381182398', 'ytr', NULL, 9999, 5, 1),
	(19, 'fdg', 'df', 'gdfg', 'fgdgd', 'dfgdf', 'dfg', NULL, 9999, 3, 1),
	(20, 'kossell@gmail.com', '45', 'yichao', 'x', '529381201200', '6', NULL, 9999, 3, 1),
	(21, 'kossell@gmail.com', '45', 'yichao', 'x', '529381201200', '6', NULL, 9999, 3, 1),
	(22, 'kossell@gmail.com', '65', 'yichao', 'x', '529381201200', '5', NULL, 9999, 4, 1),
	(23, '453', '345', 'Yichao', 'Zhu', '529381182398', '345', NULL, 9999, 4, 2);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;


-- Dumping structure for table mcc.pet
CREATE TABLE IF NOT EXISTS `pet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK138FF33574AD5` (`person_id`),
  CONSTRAINT `FK138FF33574AD5` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table mcc.pet: ~0 rows (approximately)
/*!40000 ALTER TABLE `pet` DISABLE KEYS */;
/*!40000 ALTER TABLE `pet` ENABLE KEYS */;


-- Dumping structure for table mcc.position
CREATE TABLE IF NOT EXISTS `position` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_ch` varchar(150) DEFAULT NULL,
  `name_es` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table mcc.position: ~4 rows (approximately)
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` (`id`, `name_ch`, `name_es`) VALUES
	(1, '翻译', 'interpretee'),
	(2, '经理', 'Gerente'),
	(3, '总裁', 'Presidente'),
	(4, '设备监督 ', 'Supev Equipos');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;


-- Dumping structure for table mcc.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table mcc.product: ~0 rows (approximately)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;


-- Dumping structure for table mcc.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(64) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table mcc.role: ~2 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `description`, `name`) VALUES
	(-2, 'Default role for all Users', 'ROLE_USER'),
	(-1, 'Administrator role (can edit Users)', 'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- Dumping structure for table mcc.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK143BF46A720A3DF5` (`role_id`),
  KEY `FK143BF46A173501D5` (`user_id`),
  CONSTRAINT `FK143BF46A173501D5` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK143BF46A720A3DF5` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table mcc.user_role: ~2 rows (approximately)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
	(-1, -2),
	(-2, -1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
