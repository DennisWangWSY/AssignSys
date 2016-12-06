-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.10-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema assignsys
--

CREATE DATABASE IF NOT EXISTS assignsys;
USE assignsys;

--
-- Definition of table `askforleavesheet`
--

DROP TABLE IF EXISTS `askforleavesheet`;
CREATE TABLE `askforleavesheet` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(20) NOT NULL,
  `type` varchar(10) NOT NULL,
  `cause` varchar(200) NOT NULL,
  `starttime` datetime NOT NULL,
  `endtime` datetime NOT NULL,
  `status` varchar(20) NOT NULL,
  `evidence` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `askforleavesheet`
--

/*!40000 ALTER TABLE `askforleavesheet` DISABLE KEYS */;
/*!40000 ALTER TABLE `askforleavesheet` ENABLE KEYS */;


--
-- Definition of table `overtimesheet`
--

DROP TABLE IF EXISTS `overtimesheet`;
CREATE TABLE `overtimesheet` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(20) NOT NULL,
  `cause` varchar(200) NOT NULL,
  `starttime` datetime NOT NULL,
  `endtime` datetime NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `overtimesheet`
--

/*!40000 ALTER TABLE `overtimesheet` DISABLE KEYS */;
/*!40000 ALTER TABLE `overtimesheet` ENABLE KEYS */;


--
-- Definition of table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `type` varchar(10) NOT NULL,
  `dept` varchar(50) NOT NULL,
  `age` int(10) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `workedyears` int(10) NOT NULL,
  `arrangedrest` int(10) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2003 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`uid`,`name`,`password`,`type`,`dept`,`age`,`gender`,`workedyears`,`arrangedrest`) VALUES 
 (0,'Troy','d26ad5138469c6c081d0ace055296384','orgzer','none',30,'male',13,0),
 (1,'Donald','d6460d863cc7403c4d48eb8682d87784','topMger','all',53,'male',16,0),
 (1001,'Tony','eee7ac208064d408e84ab5e26d24b278','staff','sales',31,'male',4,0),
 (2001,'Bob','2fc1c0beb992cd7096975cfebf9d5c3b','deptMger','sales',35,'male',10,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
