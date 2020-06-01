-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.36 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for schooldb
CREATE DATABASE IF NOT EXISTS `schooldb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `schooldb`;


-- Dumping structure for table schooldb.application_settings
CREATE TABLE IF NOT EXISTS `application_settings` (
  `applicationSettingId` bigint(20) NOT NULL AUTO_INCREMENT,
  `setting_code` varchar(50) NOT NULL,
  `setting_description` varchar(255) DEFAULT NULL,
  `setting_value` varchar(255) NOT NULL,
  `school_code` varchar(5) NOT NULL,
  PRIMARY KEY (`applicationSettingId`),
  UNIQUE KEY `UKla0d1y4junkd1o4m6i7cklent` (`setting_code`,`school_code`),
  KEY `FKn9qb5d71ausmkpli25b4ud4ww` (`school_code`),
  CONSTRAINT `FKn9qb5d71ausmkpli25b4ud4ww` FOREIGN KEY (`school_code`) REFERENCES `school_profile` (`school_code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Dumping data for table schooldb.application_settings: ~8 rows (approximately)
/*!40000 ALTER TABLE `application_settings` DISABLE KEYS */;
INSERT INTO `application_settings` (`applicationSettingId`, `setting_code`, `setting_description`, `setting_value`, `school_code`) VALUES
	(1, 'EMAIL_STUDENT_DETAILS', 'This parameter use for send email at Mark Student Details.', 'Y', 'ASS'),
	(2, 'EMAIL_STUDENT_ATTENDANCE', 'This parameter use for send email at Mark Student Attendance.', 'Y', 'ASS'),
	(3, 'EMAIL_STUDENT_FEE_RECEIPT', 'This parameter use for send email at Student Fee Receipt.', 'Y', 'ASS'),
	(4, 'EMAIL_USER_DETAILS', 'This parameter use for send email at User Details.', 'Y', 'ASS'),
	(5, 'EMAIL_STUDENT_DETAILS', 'This parameter use for send email at Mark Student Details.', 'Y', 'GWL'),
	(6, 'EMAIL_STUDENT_ATTENDANCE', 'This parameter use for send email at Mark Student Attendance.', 'Y', 'GWL'),
	(7, 'EMAIL_STUDENT_FEE_RECEIPT', 'This parameter use for send email at Student Fee Receipt.', 'Y', 'GWL'),
	(8, 'EMAIL_USER_DETAILS', 'This parameter use for send email at User Details.', 'Y', 'GWL');
/*!40000 ALTER TABLE `application_settings` ENABLE KEYS */;


-- Dumping structure for table schooldb.global_config
CREATE TABLE IF NOT EXISTS `global_config` (
  `config_code` varchar(50) NOT NULL,
  `config_description` varchar(255) DEFAULT NULL,
  `config_value` varchar(255) NOT NULL,
  `school_code` varchar(5) NOT NULL,
  PRIMARY KEY (`school_code`,`config_code`),
  CONSTRAINT `FK7yy2himahen993khblsudq2q2` FOREIGN KEY (`school_code`) REFERENCES `school_profile` (`school_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table schooldb.global_config: ~4 rows (approximately)
/*!40000 ALTER TABLE `global_config` DISABLE KEYS */;
INSERT INTO `global_config` (`config_code`, `config_description`, `config_value`, `school_code`) VALUES
	('MAIL_SERVER', 'uid=userid@hostname.in;pwd=password;mailserver=servername;port=portid\r\n', 'uid=mayank@sansoft.in;pwd=62926543;mailserver=mail.sansoft.in;port=25\r\n', 'ASS'),
	('MAIL_SERVER_ADDRESS', 'MAIL_SERVER', 'Server Name', 'ASS'),
	('MAIL_SERVER', 'uid=userid@hostname.in;pwd=password;mailserver=servername;port=portid\r\n', 'uid=mayank@sansoft.in;pwd=62926543;mailserver=mail.sansoft.in;port=25\r\n', 'GWL'),
	('MAIL_SERVER_ADDRESS', 'MAIL_SERVER', 'Server Name', 'GWL');
/*!40000 ALTER TABLE `global_config` ENABLE KEYS */;


-- Dumping structure for table schooldb.payment_category
CREATE TABLE IF NOT EXISTS `payment_category` (
  `payment_category_code` varchar(5) NOT NULL,
  `payment_category_name` varchar(50) NOT NULL,
  PRIMARY KEY (`payment_category_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table schooldb.payment_category: ~2 rows (approximately)
/*!40000 ALTER TABLE `payment_category` DISABLE KEYS */;
INSERT INTO `payment_category` (`payment_category_code`, `payment_category_name`) VALUES
	('CHQ', 'Cheque'),
	('CSH', 'Cash');
/*!40000 ALTER TABLE `payment_category` ENABLE KEYS */;


-- Dumping structure for table schooldb.unique_id
CREATE TABLE IF NOT EXISTS `unique_id` (
  `template_id` varchar(255) NOT NULL,
  `template_name` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `default_flag` varchar(255) DEFAULT NULL,
  `deleted_flag` varchar(255) DEFAULT NULL,
  `id_width` bigint(20) DEFAULT NULL,
  `last_id` bigint(20) DEFAULT NULL,
  `school_code` varchar(5) NOT NULL,
  PRIMARY KEY (`template_id`,`template_name`),
  KEY `FKhh6xrtolabthhaxdayibtaowh` (`school_code`),
  CONSTRAINT `FKhh6xrtolabthhaxdayibtaowh` FOREIGN KEY (`school_code`) REFERENCES `school_profile` (`school_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table schooldb.unique_id: ~1 rows (approximately)
/*!40000 ALTER TABLE `unique_id` DISABLE KEYS */;
INSERT INTO `unique_id` (`template_id`, `template_name`, `created_by`, `created_on`, `default_flag`, `deleted_flag`, `id_width`, `last_id`, `school_code`) VALUES
	('FEE-RECEIPT', 'RECEIPT-#', NULL, NULL, NULL, NULL, 5, 25, 'ASS');
/*!40000 ALTER TABLE `unique_id` ENABLE KEYS */;


-- Dumping structure for table schooldb.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_role_id` varchar(15) NOT NULL,
  `user_role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table schooldb.user_role: ~5 rows (approximately)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_role_id`, `user_role_name`) VALUES
	('ROLE_ADMIN', 'Admin'),
	('ROLE_STAFF', 'Staff'),
	('ROLE_SUBADMIN', 'Sub Admin'),
	('ROLE_SUPADMIN', 'Super Admin'),
	('ROLE_USER', 'User');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
