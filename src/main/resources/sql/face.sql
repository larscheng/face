/*
Navicat MySQL Data Transfer

Source Server         : lcoal
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : face

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-12-20 16:02:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for face_class
-- ----------------------------
DROP TABLE IF EXISTS `face_class`;
CREATE TABLE `face_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(64) DEFAULT NULL,
  `class_tea_id` int(11) DEFAULT NULL,
  `class_begin` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `class_end` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `class_week` tinyint(4) DEFAULT NULL,
  `class_state` tinyint(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `class_name` (`class_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of face_class
-- ----------------------------

-- ----------------------------
-- Table structure for face_sign
-- ----------------------------
DROP TABLE IF EXISTS `face_sign`;
CREATE TABLE `face_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `stu_id` int(11) DEFAULT NULL,
  `sign_date` varchar(16) DEFAULT NULL COMMENT '打卡日期',
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of face_sign
-- ----------------------------

-- ----------------------------
-- Table structure for face_user
-- ----------------------------
DROP TABLE IF EXISTS `face_user`;
CREATE TABLE `face_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) DEFAULT NULL,
  `user_code` varchar(32) DEFAULT NULL,
  `user_phone` varchar(32) DEFAULT NULL,
  `user_password` varchar(64) DEFAULT NULL,
  `user_state` tinyint(1) DEFAULT '1' COMMENT '用户类型，0禁用1启用',
  `user_type` tinyint(1) DEFAULT NULL COMMENT '用户类型，1管理员2教师3学生',
  `stu_sign_times` int(11) DEFAULT '0' COMMENT '成功签到次数',
  `stu_absentee_times` int(11) DEFAULT '0' COMMENT '缺勤次数',
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of face_user
-- ----------------------------
INSERT INTO `face_user` VALUES ('1', 'zql', 'admin', '13108018752', '123456', '1', '1', '0', '0', '2018-12-20 14:35:57', null);
SET FOREIGN_KEY_CHECKS=1;
