/*
Navicat MySQL Data Transfer

Source Server         : ljs
Source Server Version : 50726
Source Host           : 120.79.30.62:3306
Source Database       : mymsg

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-08-04 00:40:30
*/





--用户表
CREATE TABLE `tb_user`
(
    `user_id` int(10) NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(30) NOT NULL,
    `phone` VARCHAR(30) NOT NULL,
    `role` int(2), -- 老师1 或者 普通用户0
    `photo_url` VARCHAR(300),
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--状元之声
CREATE TABLE `tb_zyvoice`
(
    `voice_id` int(10)NOT NULL AUTO_INCREMENT,
    `mp3_url` VARCHAR(300) NOT NULL,
    `photo_url` VARCHAR(300),
    `mp3_name` VARCHAR(30),
    `user_id` int(10), 
    PRIMARY KEY (`voice_id`),
	KEY `user_id` (`user_id`),
	CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 机构研报
CREATE TABLE `tb_paper`(
    `paper_id` int(10) NOT NULL AUTO_INCREMENT,
    `pdf_url` VARCHAR(30),
    `paper_name` VARCHAR(100),
    `update_time` DATETIME,
    PRIMARY KEY (`paper_id`),
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 手机app
CREATE TABLE `tb_app`(
    `app_id` int(10) NOT NULL AUTO_INCREMENT,
    `photo_url` VARCHAR(300),
    `name` VARCHAR(30),
    PRIMARY KEY(`app_id`),
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- 尖儿货
CREATE TABLE `tb_jianerhuo`(
    `j_id` int(10) NOT NULL AUTO_INCREMENT,
    `photo_url` VARCHAR(300),
    `name` VARCHAR(30),
    `user_id` int(10),
    `update_time` DATETIME,
    PRIMARY KEY (`j_id`),
	KEY `user_id` (`user_id`),
    CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 操盘经典
CREATE TABLE `tb_caopan`(
    `paper_id` int(10) NOT NULL AUTO_INCREMENT,
    `pdf_url` VARCHAR(300),
    `update_time` DATETIME,
    `name` VARCHAR(30),
    PRIMARY KEY (paper_id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 布局计划
CREATE TABLE `tb_plan`(
    `plan_id` int(10) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(30),
    `user_id` int(10), -- 作者
    `update_time` DATETIME,
    `content` VARCHAR(3000), -- 文章内容，带图文，这里要再考虑一下如何存储
    PRIMARY KEY (`plan_id`),
	KEY `user_id` (`user_id`),
    CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- 投资者教育
CREATE TABLE `tb_inve_edu`(
    `paper_id` int(10) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(30),
    `pdf_url` VARCHAR(300),
    `select_name` int(2),--有三个选择 分别是 防洗钱1，风险提示2，基础知识3  
    PRIMARY KEY (`paper_id`),
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--大数据云
CREATE TABLE `tb_big_data`(
   `paper_id` int(10) NOT NULL AUTO_INCREMENT,
   `name` VARCHAR(30),
   `pdf_url` VARCHAR(30),
    `update_time` DATETIME,
    PRIMARY KEY (`paper_id`),
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


--多维量化  备注：有待商议
CREATE TABLE `tb_muti_quanti`(
    `muti_id` int(10) NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`muti_id`),
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 人气排名  这里考虑是否建表

-- 近十单胜率排名  这里考虑是否建表


-- 聚宝盆 策略参考
CREATE TABLE `tb_strategy`(
    `s_id` int(10) NOT NULL AUTO_INCREMENT,
    `user_id` int(10), -- 策略作者
    `stock_name` VARCHAR(30), --股票名字
    `update_time` DATETIME,
    `point` FLOAT(30), -- 浮点数，点位
    `target` FLOAT(30), -- 目标
    `stop_loss` FLOAT(30), --止损
    `source`  VARCHAR(30),  -- 策略来源
    `operating` VARCHAR(30),  --策略操作
    PRIMARY KEY (`s_id`),
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

