/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : garbage

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 18/12/2019 15:11:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gg_item
-- ----------------------------
DROP TABLE IF EXISTS `gg_item`;
CREATE TABLE `gg_item` (
  `id` bigint(20) NOT NULL COMMENT '分类标签id',
  `name` varchar(100) NOT NULL COMMENT '分类名称',
  `logo` varchar(500) DEFAULT NULL COMMENT '分类logo，图片的html地址',
  `desc` text COMMENT '分类详细信息',
  `matters` varchar(500) DEFAULT NULL COMMENT '主要事项',
  `disposal` varchar(500) DEFAULT NULL COMMENT '处理方式',
  `QR_code` varchar(100) DEFAULT NULL COMMENT '二维码',
  `cid` bigint(10) NOT NULL COMMENT '所属类目的id',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '商品状态，1-正常，2-删除',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  KEY `status` (`status`),
  KEY `updated` (`updated`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='垃圾分类项表';

-- ----------------------------
-- Table structure for gg_item_cat
-- ----------------------------
DROP TABLE IF EXISTS `gg_item_cat`;
CREATE TABLE `gg_item_cat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `name_cn` varchar(50) DEFAULT NULL COMMENT '中文名称',
  `name_en` varchar(50) DEFAULT NULL COMMENT '英文名称',
  `logo` varchar(100) DEFAULT NULL COMMENT 'logo地址',
  `desc` text COMMENT '描述信息',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父类目ID=0时，代表的是一级的类目',
  `status` int(1) DEFAULT '1' COMMENT '状态。可选值:1(正常),2(删除)',
  `sort_order` int(4) DEFAULT NULL COMMENT '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
  `is_parent` tinyint(1) DEFAULT '1' COMMENT '该类目是否为父类目，1为true，0为false',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`,`status`) USING BTREE,
  KEY `sort_order` (`sort_order`)
) ENGINE=InnoDB AUTO_INCREMENT=1183 DEFAULT CHARSET=utf8 COMMENT='垃圾分类目录';

-- ----------------------------
-- Table structure for gg_label
-- ----------------------------
DROP TABLE IF EXISTS `gg_label`;
CREATE TABLE `gg_label` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '标签id',
  `content` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标签内容',
  `status` int(10) DEFAULT '1' COMMENT '状态：1、正常，2、删除',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '订单更新时间',
  PRIMARY KEY (`id`),
  KEY `create_time` (`create_time`),
  KEY `status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='标签表';

-- ----------------------------
-- Table structure for gg_label_item
-- ----------------------------
DROP TABLE IF EXISTS `gg_label_item`;
CREATE TABLE `gg_label_item` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `item_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分类项id',
  `label_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标签id',
  PRIMARY KEY (`id`),
  KEY `item_id` (`item_id`),
  KEY `label_id` (`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for gg_user
-- ----------------------------
DROP TABLE IF EXISTS `gg_user`;
CREATE TABLE `gg_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码，加密存储',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `type` int(10) NOT NULL DEFAULT '1' COMMENT '1、普通用户；0、root用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='用户表';

SET FOREIGN_KEY_CHECKS = 1;
