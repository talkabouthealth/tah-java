-- MySQL dump 10.13  Distrib 5.1.45, for Win32 (ia32)
--
-- Host: localhost    Database: talkmidb
-- ------------------------------------------------------
-- Server version	5.1.45-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `conv_history`
--

DROP TABLE IF EXISTS `conv_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conv_history` (
  `conv_hist_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `join_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `uid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`conv_hist_id`),
  KEY `FK_conv_uid` (`uid`) USING BTREE,
  CONSTRAINT `FK_conv_uid` FOREIGN KEY (`uid`) REFERENCES `talkers` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `login_history`
--

DROP TABLE IF EXISTS `login_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_history` (
  `uid` varchar(25) NOT NULL,
  `login_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `noti_histoty`
--

DROP TABLE IF EXISTS `noti_histoty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `noti_histoty` (
  `noti_hist_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `noti_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `uid` int(10) unsigned NOT NULL,
  `topic_id` bigint(20) unsigned NOT NULL,
  `noti_sc` int(10) unsigned NOT NULL,
  PRIMARY KEY (`noti_hist_id`),
  KEY `FK_uid` (`uid`),
  CONSTRAINT `FK_noti_uid` FOREIGN KEY (`uid`) REFERENCES `talkers` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `talkers`
--

DROP TABLE IF EXISTS `talkers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `talkers` (
  `uid` int(10) unsigned NOT NULL,
  `uname` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `gender` char(1) NOT NULL DEFAULT '0',
  `dob` datetime NOT NULL,
  `time_stamp` datetime NOT NULL,
  `zipcode` varchar(45) NOT NULL,
  `Marital_Stat` varchar(45) NOT NULL,
  `Child_Stat` varchar(45) NOT NULL,
  `Lang` varchar(45) NOT NULL,
  `Yahoo` varchar(45) NOT NULL,
  `liveID` varchar(45) NOT NULL,
  `AOL` varchar(45) NOT NULL,
  `Gmail` varchar(45) NOT NULL,
  `Skype` varchar(45) DEFAULT NULL,
  `notifyfrequency` int(10) unsigned NOT NULL,
  `notifytime` int(10) unsigned NOT NULL,
  `PrimaryIM` varchar(45) NOT NULL,
  PRIMARY KEY (`uid`,`uname`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topics`
--

DROP TABLE IF EXISTS `topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topics` (
  `topic_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(10) unsigned NOT NULL,
  `topic` varchar(45) NOT NULL,
  `creation_date` datetime NOT NULL,
  `display_time` datetime NOT NULL,
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-04-23 17:07:47
