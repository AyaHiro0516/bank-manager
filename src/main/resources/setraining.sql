/*
Navicat MySQL Data Transfer

Source Server         : mysql8
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : setraining

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-06-15 10:03:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for allowcheckbean
-- ----------------------------
DROP TABLE IF EXISTS `allowcheckbean`;
CREATE TABLE `allowcheckbean` (
  `userName` varchar(20) NOT NULL,
  `missNum` int(11) DEFAULT NULL,
  `isAllow` tinyint(20) DEFAULT NULL,
  `role` varchar(30) DEFAULT NULL,
  `permission` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for creditaccount
-- ----------------------------
DROP TABLE IF EXISTS `creditaccount`;
CREATE TABLE `creditaccount` (
  `userId` bigint(20) NOT NULL,
  `passWord` varchar(255) NOT NULL,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `personId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `balance` double(20,0) NOT NULL,
  `ceiling` double(20,0) DEFAULT NULL,
  `accountType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `userName_fkca` (`userName`),
  CONSTRAINT `userName_fkca` FOREIGN KEY (`userName`) REFERENCES `allowcheckbean` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for loancreditaccount
-- ----------------------------
DROP TABLE IF EXISTS `loancreditaccount`;
CREATE TABLE `loancreditaccount` (
  `userId` bigint(20) NOT NULL,
  `passWord` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `personId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `balance` double(20,0) NOT NULL,
  `ceiling` double(20,0) DEFAULT NULL,
  `loan` double(20,0) DEFAULT NULL,
  `accountType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `userName_fklca` (`userName`),
  CONSTRAINT `userName_fklca` FOREIGN KEY (`userName`) REFERENCES `allowcheckbean` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for loansavingaccount
-- ----------------------------
DROP TABLE IF EXISTS `loansavingaccount`;
CREATE TABLE `loansavingaccount` (
  `userId` bigint(20) NOT NULL,
  `passWord` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `personId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `balance` double(20,0) NOT NULL,
  `loan` double(20,0) DEFAULT NULL,
  `accountType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `userName_fklsa` (`userName`),
  CONSTRAINT `userName_fklsa` FOREIGN KEY (`userName`) REFERENCES `allowcheckbean` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for savingaccount
-- ----------------------------
DROP TABLE IF EXISTS `savingaccount`;
CREATE TABLE `savingaccount` (
  `userId` bigint(20) NOT NULL,
  `passWord` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `personId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `balance` double(20,0) NOT NULL,
  `accountType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `userName_fksa` (`userName`),
  CONSTRAINT `userName_fksa` FOREIGN KEY (`userName`) REFERENCES `allowcheckbean` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

