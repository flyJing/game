/*
 Navicat MySQL Data Transfer

 Source Server         : first
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : game

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 20/04/2022 16:40:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for game_admin
-- ----------------------------
DROP TABLE IF EXISTS `game_admin`;
CREATE TABLE `game_admin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员主键',
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员姓名',
  `password` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  `jno` bigint(20) NOT NULL COMMENT '管理员工号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of game_admin
-- ----------------------------
INSERT INTO `game_admin` VALUES (1, '涂老师', '123456', 20190217001);

-- ----------------------------
-- Table structure for game_manage_content
-- ----------------------------
DROP TABLE IF EXISTS `game_manage_content`;
CREATE TABLE `game_manage_content`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `manage_id` bigint(20) NOT NULL COMMENT '外键,查询详细内容',
  `static_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传的图片路径',
  `apply_money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报销的费用',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '对申请内容的描述',
  `phone` int(25) NOT NULL COMMENT '申请人电话号码',
  `pay_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '支付方式',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请人电子邮件',
  `reasons_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '如果拒接的申请,需要管理员说明理由',
  `stu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int(255) NOT NULL DEFAULT 0 COMMENT '0',
  `pic_list` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of game_manage_content
-- ----------------------------
INSERT INTO `game_manage_content` VALUES (2, 4, '123', '33444', '五十岁', 12444, '微信', '12343', NULL, 'thejing', 1, NULL);
INSERT INTO `game_manage_content` VALUES (3, 5, '123', '2342', 'qifei', 123, 'zhifuabo', '1231', NULL, '小小军', 0, NULL);
INSERT INTO `game_manage_content` VALUES (4, 16, 'q232', '1000', '你给我通过', 1567689, '银联', '123123', '不符合规范', '小小军', 2, NULL);
INSERT INTO `game_manage_content` VALUES (5, 18, '123,456', '1000', '你给我通过', 1567689, '银联', '123123', NULL, '小小军', 1, NULL);
INSERT INTO `game_manage_content` VALUES (6, 19, NULL, '10000', '很好的项目', 1567689, '银联', '123123', '不符合规范', '小小军', 2, NULL);

-- ----------------------------
-- Table structure for game_manage_list
-- ----------------------------
DROP TABLE IF EXISTS `game_manage_list`;
CREATE TABLE `game_manage_list`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `stu_id` bigint(20) NOT NULL COMMENT '上传表单后的学生id',
  `stu_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传表单后的学生姓名',
  `ad_id` bigint(20) NULL DEFAULT NULL COMMENT '处理项目的管理员id',
  `ad_username` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理项目的管理员姓名',
  `status` int(11) NULL DEFAULT 0 COMMENT '0:审核中, 1:通过审核, 2:未通过',
  `type` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '赛事类型',
  `date` datetime NOT NULL COMMENT '提交时间',
  `project_id` bigint(25) NOT NULL COMMENT '项目号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `stu_id`(`stu_id`) USING BTREE,
  INDEX `ad_id`(`ad_id`) USING BTREE,
  CONSTRAINT `ad_id` FOREIGN KEY (`ad_id`) REFERENCES `game_admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `stu_id` FOREIGN KEY (`stu_id`) REFERENCES `game_student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of game_manage_list
-- ----------------------------
INSERT INTO `game_manage_list` VALUES (1, 1, '小小军', NULL, NULL, 1, '自然科学', '2022-04-13 17:33:33', 123);
INSERT INTO `game_manage_list` VALUES (4, 1, '小小军', NULL, NULL, 0, '自然科学', '2022-04-13 17:34:42', 125);
INSERT INTO `game_manage_list` VALUES (5, 1, '小小军', NULL, NULL, 1, '自然科学', '2022-04-13 17:34:42', 126);
INSERT INTO `game_manage_list` VALUES (6, 1, '小小军', 1, '涂老师', 1, '自然科学', '2022-04-13 17:34:42', 127);
INSERT INTO `game_manage_list` VALUES (7, 2, 'thejing', 1, '涂老师', 2, '自然科学', '2022-04-13 17:34:42', 167);
INSERT INTO `game_manage_list` VALUES (8, 2, 'thejing', NULL, NULL, 0, '自然科学', '2022-04-13 17:34:42', 124);
INSERT INTO `game_manage_list` VALUES (9, 1, '小小军', NULL, NULL, 0, '自然科学', '2022-04-13 17:34:42', 325);
INSERT INTO `game_manage_list` VALUES (10, 2, 'thejing', NULL, NULL, 0, '自然科学', '2022-04-13 17:34:42', 234);
INSERT INTO `game_manage_list` VALUES (11, 2, 'thejing', NULL, NULL, 0, '自然科学', '2022-04-13 17:34:42', 176);
INSERT INTO `game_manage_list` VALUES (12, 1, '小小军', NULL, NULL, 0, '自然科学', '2022-04-13 17:34:42', 1345);
INSERT INTO `game_manage_list` VALUES (16, 1, '小小军', 1, '涂老师', 2, '人文自然', '2022-04-17 14:12:50', 1234);
INSERT INTO `game_manage_list` VALUES (18, 1, '小小军', 1, '涂老师', 1, '人文自然', '2022-04-17 14:34:04', 1789);
INSERT INTO `game_manage_list` VALUES (19, 1, '小小军', 1, '涂老师', 2, '人文自然', '2022-04-19 12:28:05', 1340);

-- ----------------------------
-- Table structure for game_student
-- ----------------------------
DROP TABLE IF EXISTS `game_student`;
CREATE TABLE `game_student`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '学生主键',
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生姓名',
  `password` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生密码',
  `sno` bigint(20) NOT NULL COMMENT '学生学号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of game_student
-- ----------------------------
INSERT INTO `game_student` VALUES (1, '小小军', '123', 2019);
INSERT INTO `game_student` VALUES (2, 'thejing', '123', 2018);

-- ----------------------------
-- Table structure for game_student_table
-- ----------------------------
DROP TABLE IF EXISTS `game_student_table`;
CREATE TABLE `game_student_table`  (
  `id` bigint(20) NOT NULL,
  `project_id` bigint(25) NOT NULL,
  `stu_id` bigint(20) NOT NULL,
  `stu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` int(25) NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `apply_money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pay_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `decription` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of game_student_table
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
