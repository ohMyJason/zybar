/*
Navicat MySQL Data Transfer

Source Server         : ljs
Source Server Version : 50726
Source Host           : 120.79.30.62:3306
Source Database       : jybar

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-09-08 17:10:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_app
-- ----------------------------
DROP TABLE IF EXISTS `tb_app`;
CREATE TABLE `tb_app` (
  `app_id` int(10) NOT NULL AUTO_INCREMENT,
  `photo_url` varchar(300) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_app
-- ----------------------------

-- ----------------------------
-- Table structure for tb_big_data
-- ----------------------------
DROP TABLE IF EXISTS `tb_big_data`;
CREATE TABLE `tb_big_data` (
  `big_data_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `content` varchar(20000) DEFAULT NULL,
  `update_time` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`big_data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_big_data
-- ----------------------------

-- ----------------------------
-- Table structure for tb_caopan
-- ----------------------------
DROP TABLE IF EXISTS `tb_caopan`;
CREATE TABLE `tb_caopan` (
  `paper_id` int(10) NOT NULL AUTO_INCREMENT,
  `pdf_url` varchar(300) DEFAULT NULL,
  `update_time` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`paper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_caopan
-- ----------------------------

-- ----------------------------
-- Table structure for tb_inve_edu
-- ----------------------------
DROP TABLE IF EXISTS `tb_inve_edu`;
CREATE TABLE `tb_inve_edu` (
  `paper_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `pdf_url` varchar(300) DEFAULT NULL,
  `select_name` int(2) DEFAULT NULL,
  PRIMARY KEY (`paper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_inve_edu
-- ----------------------------

-- ----------------------------
-- Table structure for tb_jianerhuo
-- ----------------------------
DROP TABLE IF EXISTS `tb_jianerhuo`;
CREATE TABLE `tb_jianerhuo` (
  `j_id` int(10) NOT NULL AUTO_INCREMENT,
  `photo_url` varchar(300) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL,
  `update_time` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`j_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_jianerhuo_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_jianerhuo
-- ----------------------------

-- ----------------------------
-- Table structure for tb_live
-- ----------------------------
DROP TABLE IF EXISTS `tb_live`;
CREATE TABLE `tb_live` (
  `live_id` int(10) NOT NULL AUTO_INCREMENT,
  `live_img_url` varchar(255) NOT NULL,
  `live_url` varchar(255) NOT NULL,
  `live_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`live_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_live
-- ----------------------------
INSERT INTO `tb_live` VALUES ('10', '/file/img/20190906114550_liveImg1.jpg', 'http://mudu.tv/watch/3289725 ', '《风云看点》-机会早知道  主讲人：秦建镐  节目时间：19:30-20:10 ');
INSERT INTO `tb_live` VALUES ('11', '/file/img/20190906114635_liveImg2.jpg', 'http://mudu.tv/watch/3140023', '20:45分-21:50分《期市狙击VIP》 吴老师直播室盘中实时分析');

-- ----------------------------
-- Table structure for tb_multi_quanti
-- ----------------------------
DROP TABLE IF EXISTS `tb_multi_quanti`;
CREATE TABLE `tb_multi_quanti` (
  `multi_quanti_id` int(255) NOT NULL AUTO_INCREMENT,
  `time` varchar(255) NOT NULL COMMENT '时间',
  `variety` varchar(255) NOT NULL COMMENT '品种',
  `direction` varchar(255) NOT NULL DEFAULT '' COMMENT '方向',
  `point` varchar(255) NOT NULL DEFAULT '' COMMENT '点位',
  `category` int(10) NOT NULL COMMENT '1-焦炭  2-甲醛  3-螺纹钢 4-沥青  5-热卷 6-焦煤',
  PRIMARY KEY (`multi_quanti_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_multi_quanti
-- ----------------------------
INSERT INTO `tb_multi_quanti` VALUES ('2', '2019-08-15 00:00:00', 'ji0', '买', '2000', '1');
INSERT INTO `tb_multi_quanti` VALUES ('3', '2019-08-29 00:00:00', 'JL0', '买', '2009', '2');
INSERT INTO `tb_multi_quanti` VALUES ('4', '2019-08-22 00:00:00', 'jl0', '卖', '2444', '3');
INSERT INTO `tb_multi_quanti` VALUES ('5', '2019-08-08 00:00:00', 'ji0', '平', '2983', '4');
INSERT INTO `tb_multi_quanti` VALUES ('6', '2019-08-07 00:00:00', 'ji0', '卖', '2093', '5');
INSERT INTO `tb_multi_quanti` VALUES ('7', '2019-08-25 00:00:00', 'jl0', '卖', '2093', '6');
INSERT INTO `tb_multi_quanti` VALUES ('8', '2019-08-14 00:00:00', 'ji0', '买', '2003', '1');
INSERT INTO `tb_multi_quanti` VALUES ('9', '2019-08-29 10:13:30', '焦炭', '买', '1120', '1');
INSERT INTO `tb_multi_quanti` VALUES ('10', '2019-09-04 10:24:19', '焦炭', '买', '1102', '1');
INSERT INTO `tb_multi_quanti` VALUES ('11', '2019-08-13 00:00:00', 'J-1', '买', '2335', '1');
INSERT INTO `tb_multi_quanti` VALUES ('12', '2019-08-20 00:00:00', 'J00', '卖', '1231', '1');

-- ----------------------------
-- Table structure for tb_paper
-- ----------------------------
DROP TABLE IF EXISTS `tb_paper`;
CREATE TABLE `tb_paper` (
  `paper_id` int(10) NOT NULL AUTO_INCREMENT,
  `pdf_url` varchar(999) DEFAULT NULL,
  `paper_name` varchar(100) DEFAULT NULL,
  `update_time` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`paper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_paper
-- ----------------------------
INSERT INTO `tb_paper` VALUES ('54', '/file/pdf/20190906131559_平安期货费率收取速查一览表20190815.pdf', '啊啊 ', '19-13-16 13:16:09');

-- ----------------------------
-- Table structure for tb_plan
-- ----------------------------
DROP TABLE IF EXISTS `tb_plan`;
CREATE TABLE `tb_plan` (
  `plan_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL,
  `update_time` varchar(30) DEFAULT NULL,
  `content` varchar(20000) DEFAULT NULL,
  PRIMARY KEY (`plan_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_plan_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_plan
-- ----------------------------

-- ----------------------------
-- Table structure for tb_popular_ranking
-- ----------------------------
DROP TABLE IF EXISTS `tb_popular_ranking`;
CREATE TABLE `tb_popular_ranking` (
  `popular_ranking_id` int(11) NOT NULL AUTO_INCREMENT,
  `popular_ranking_num` int(255) NOT NULL,
  `popular_ranking_name` varchar(255) NOT NULL,
  PRIMARY KEY (`popular_ranking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_popular_ranking
-- ----------------------------
INSERT INTO `tb_popular_ranking` VALUES ('5', '300', '黑金战队');
INSERT INTO `tb_popular_ranking` VALUES ('6', '200', '风云战队');
INSERT INTO `tb_popular_ranking` VALUES ('7', '400', '狙击战队');

-- ----------------------------
-- Table structure for tb_strategy
-- ----------------------------
DROP TABLE IF EXISTS `tb_strategy`;
CREATE TABLE `tb_strategy` (
  `s_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `stock_name` varchar(30) NOT NULL COMMENT '股票名',
  `update_time` varchar(100) NOT NULL COMMENT '更新时间',
  `point` double(100,0) NOT NULL COMMENT '点位',
  `target` double(100,0) NOT NULL COMMENT '目标',
  `stop_loss` double(100,0) NOT NULL COMMENT '止损',
  `source` varchar(30) NOT NULL COMMENT '策略来源',
  `operating` varchar(30) NOT NULL COMMENT '策略操作',
  `select_name` int(10) NOT NULL COMMENT '战队名',
  PRIMARY KEY (`s_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_strategy_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_strategy
-- ----------------------------
INSERT INTO `tb_strategy` VALUES ('6', '2107', '随时随地的完全', '2019-09-03 01:41:10', '2333', '2233', '2333', '金山期货', '卖出平仓', '1');
INSERT INTO `tb_strategy` VALUES ('7', '2107', '嗡嗡嗡的', '2019-09-03 01:41:54', '123', '123', '123', '金山期货', '卖出平仓', '1');
INSERT INTO `tb_strategy` VALUES ('8', '2107', '212的', '2019-09-03 01:42:24', '123', '13', '2312', '金山期货', '买入建仓', '1');
INSERT INTO `tb_strategy` VALUES ('9', '2107', '狙击战队策略', '2019-09-04 04:14:57', '1231', '231', '1231', '金山数据', '卖出平仓', '2');
INSERT INTO `tb_strategy` VALUES ('10', '2107', '风云策略', '2019-09-04 04:15:22', '12312', '3123', '123', '金山期货', '买入建仓', '3');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `role` int(2) DEFAULT NULL COMMENT '1-普通用户  2-老师',
  `photo_url` varchar(300) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `level` int(255) DEFAULT '1' COMMENT '等级（暂时不用）',
  `if_hj` int(10) NOT NULL DEFAULT '0' COMMENT '是否能进黑金直播间',
  `if_jj` int(10) NOT NULL DEFAULT '0' COMMENT '是否进入狙击直播间',
  `if_fw` int(10) NOT NULL DEFAULT '0' COMMENT '是否进入风云直播间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2117 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('2106', '用户小刘', '18030202941', '1', '/file/userImg/20190908090855_84d9afb3734101cd1080e4bd0fb72f40_r.jpg', '123456', '1', '1', '1', '1');
INSERT INTO `tb_user` VALUES ('2107', 'teacherLiu', '18030202941', '2', '/file/userImg/20190908112852_t01d2ef0e78dbb0a811.jpg', '123456', '1', '1', '1', '1');
INSERT INTO `tb_user` VALUES ('2112', 'qwe', '123456', '1', '/file/userImg/20190908112759_t012545b1915bf33c18.jpg', 'eee', '1', '1', '1', '1');
INSERT INTO `tb_user` VALUES ('2113', 'qwe', '12314', '1', '/file/userImg/20190908112926_t01877ec796ee4cc2d1.png', '1234', '1', '1', '1', '1');
INSERT INTO `tb_user` VALUES ('2114', '123', '123', '1', null, '123', '1', '0', '0', '0');
INSERT INTO `tb_user` VALUES ('2115', '超级管理员', '#', '3', '/file/userImg/20190908091144_gaki_2.jpg', '123456', '1', '1', '1', '1');
INSERT INTO `tb_user` VALUES ('2116', '测试添加', '158665659594', '1', null, '123456', '1', '0', '0', '0');

-- ----------------------------
-- Table structure for tb_winning_rate_ranking
-- ----------------------------
DROP TABLE IF EXISTS `tb_winning_rate_ranking`;
CREATE TABLE `tb_winning_rate_ranking` (
  `winning_rate_ranking_id` int(11) NOT NULL AUTO_INCREMENT,
  `winning_rate_ranking_name` varchar(255) NOT NULL,
  `winning_rate_ranking_rate` float(11,0) NOT NULL,
  PRIMARY KEY (`winning_rate_ranking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_winning_rate_ranking
-- ----------------------------
INSERT INTO `tb_winning_rate_ranking` VALUES ('5', '刘佳昇', '99');

-- ----------------------------
-- Table structure for tb_zy_view
-- ----------------------------
DROP TABLE IF EXISTS `tb_zy_view`;
CREATE TABLE `tb_zy_view` (
  `zy_view_id` int(11) NOT NULL AUTO_INCREMENT,
  `zy_view_url` varchar(999) NOT NULL,
  `zy_view_creat_tiome` varchar(30) NOT NULL,
  `zy_view_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`zy_view_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_zy_view
-- ----------------------------

-- ----------------------------
-- Table structure for tb_zyvoice
-- ----------------------------
DROP TABLE IF EXISTS `tb_zyvoice`;
CREATE TABLE `tb_zyvoice` (
  `voice_id` int(10) NOT NULL AUTO_INCREMENT,
  `mp3_url` varchar(300) NOT NULL,
  `photo_url` varchar(300) DEFAULT NULL,
  `mp3_name` varchar(30) DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`voice_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_zyvoice
-- ----------------------------
INSERT INTO `tb_zyvoice` VALUES ('16', '/file/mp3/20190906042510_lyq17781.mp3', '/file/userImg/20190906042410_1_1.jpg', '猴子叫', '2107');
