/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-07-23 16:17:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `monitor_push`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_push`;
CREATE TABLE `monitor_push` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fileUrl` varchar(100) DEFAULT NULL,
  `fileName` varchar(30) DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL COMMENT '1-未推送  2-推送',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_push
-- ----------------------------
INSERT INTO `monitor_push` VALUES ('1', 'D:\\\\桌面文件', '常识.txt', '2');
INSERT INTO `monitor_push` VALUES ('2', 'D:\\\\桌面文件', '日记-每日事.txt', '2');
INSERT INTO `monitor_push` VALUES ('3', 'D:\\\\桌面文件', '问题.txt', '2');
