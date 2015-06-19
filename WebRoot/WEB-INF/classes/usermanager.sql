/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : usermanager

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2015-06-19 13:32:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(10) DEFAULT NULL,
  `password` char(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Franklin', '1234');
INSERT INTO `user` VALUES ('2', 'Lina', '1234');
INSERT INTO `user` VALUES ('4', 'Kitty', '1234');
INSERT INTO `user` VALUES ('5', 'Mary', '1234');
INSERT INTO `user` VALUES ('7', 'Lary', '1234');
INSERT INTO `user` VALUES ('9', 'Lily', '1234');
INSERT INTO `user` VALUES ('10', 'Jery', '1234');
INSERT INTO `user` VALUES ('11', 'Penny', '1234');
INSERT INTO `user` VALUES ('12', 'Kine', '1234');
INSERT INTO `user` VALUES ('20', 'Keen', '12345');
INSERT INTO `user` VALUES ('21', 'Jack', '3232');
INSERT INTO `user` VALUES ('22', 'Torry', '3209');
INSERT INTO `user` VALUES ('23', 'Hony', '3323');
INSERT INTO `user` VALUES ('24', 'Han', '2039');
INSERT INTO `user` VALUES ('25', 'Karry', '32324');
INSERT INTO `user` VALUES ('26', 'Jackson', '23241');
INSERT INTO `user` VALUES ('27', 'Smith', '232049');
INSERT INTO `user` VALUES ('28', 'Yeep', '3323109');
INSERT INTO `user` VALUES ('29', 'Usoner', '32398');
INSERT INTO `user` VALUES ('30', 'Son', '23232');
INSERT INTO `user` VALUES ('31', 'Pany', '1');
