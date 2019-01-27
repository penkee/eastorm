/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : lmcar

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-11-04 21:33:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `biz_car`
-- ----------------------------
DROP TABLE IF EXISTS `biz_car`;
CREATE TABLE `biz_car` (
  `id` int(11) NOT NULL COMMENT '主键',
  `carImageUrl` varchar(200) DEFAULT NULL COMMENT '汽车图片',
  `brandId` int(11) DEFAULT NULL COMMENT '品牌ID',
  `brandName` varchar(10) DEFAULT NULL COMMENT '品牌名称',
  `model` varchar(10) DEFAULT NULL COMMENT '型号',
  `color` varchar(6) DEFAULT NULL COMMENT '汽车颜色',
  `fullLoad` tinyint(4) DEFAULT NULL COMMENT '满载人数',
  `carNo` varchar(10) DEFAULT NULL COMMENT '车牌号',
  `valid` tinyint(4) DEFAULT '1' COMMENT '是否有效（0：无效，1：有效）',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifyTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='车辆表';

-- ----------------------------
-- Records of biz_car
-- ----------------------------

-- ----------------------------
-- Table structure for `biz_city_line`
-- ----------------------------
DROP TABLE IF EXISTS `biz_city_line`;
CREATE TABLE `biz_city_line` (
  `id` int(11) NOT NULL COMMENT '主键',
  `firstCityId` int(11) DEFAULT NULL COMMENT '第一个城市',
  `firstCityName` varchar(20) DEFAULT NULL COMMENT '第一城市名',
  `secondCityId` int(11) DEFAULT NULL COMMENT '第二城市id',
  `secondCityName` varchar(20) DEFAULT NULL COMMENT '第二城市名',
  `status` tinyint(3) unsigned DEFAULT '0' COMMENT '状态（0：待审核，1：开通中，2：暂停运营）',
  `valid` tinyint(3) unsigned DEFAULT '1' COMMENT '是否有效（0：无效，1：有效）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='城市路线表';

-- ----------------------------
-- Records of biz_city_line
-- ----------------------------

-- ----------------------------
-- Table structure for `biz_driver`
-- ----------------------------
DROP TABLE IF EXISTS `biz_driver`;
CREATE TABLE `biz_driver` (
  `id` int(11) NOT NULL COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别（0：未知，1：男，2：女）',
  `cardNo` varchar(32) DEFAULT NULL COMMENT '身份证号',
  `avatarUrl` varchar(200) DEFAULT NULL COMMENT '头像',
  `mobile` char(11) DEFAULT NULL COMMENT '手机号',
  `carId` int(11) DEFAULT NULL COMMENT '当前车id',
  `serviceScore` tinyint(4) DEFAULT NULL COMMENT '服务评分0-5',
  `serviceOrders` int(11) DEFAULT NULL COMMENT '服务订单数',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态（0：待审核，1：正常，2：惩罚暂停）',
  `valid` tinyint(4) DEFAULT '1' COMMENT '是否有效（0：无效，1：有效）',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifyTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='司机表';

-- ----------------------------
-- Records of biz_driver
-- ----------------------------

-- ----------------------------
-- Table structure for `biz_driver_verify`
-- ----------------------------
DROP TABLE IF EXISTS `biz_driver_verify`;
CREATE TABLE `biz_driver_verify` (
  `id` int(11) NOT NULL COMMENT '主键',
  `driverId` int(11) DEFAULT NULL COMMENT '司机id',
  `zhimaScore` int(11) DEFAULT NULL COMMENT '芝麻分',
  `criminalCount` int(11) DEFAULT NULL COMMENT '犯罪次数',
  `education` tinyint(4) DEFAULT NULL COMMENT '学历（0：小学，1：初中，2：高中，3：大专，4：本科，5：研究生，6：博士）',
  `credit` tinyint(4) DEFAULT NULL COMMENT '征信结果（1：正常，2：失信人员）',
  `driverCardUrl` varchar(200) DEFAULT NULL COMMENT '驾照图',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='司机资格表';

-- ----------------------------
-- Records of biz_driver_verify
-- ----------------------------

-- ----------------------------
-- Table structure for `biz_login`
-- ----------------------------
DROP TABLE IF EXISTS `biz_login`;
CREATE TABLE `biz_login` (
  `id` int(11) NOT NULL COMMENT '主键',
  `driverId` int(11) DEFAULT NULL COMMENT '司机表主键',
  `passengerId` int(11) DEFAULT NULL COMMENT '乘客表主键',
  `loginOpenId` varchar(64) NOT NULL COMMENT '登录第三方标识',
  `platform` tinyint(3) unsigned NOT NULL COMMENT '平台（0：支付宝，1：微信，2：手机号密码）',
  `password` char(80) DEFAULT NULL COMMENT '密码，目前只有手机号方式有',
  `avatarUrl` varchar(200) DEFAULT NULL COMMENT '头像',
  `nickName` varchar(20) DEFAULT NULL COMMENT '昵称',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifyTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_loginOpenId_platform` (`loginOpenId`,`platform`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登录表';

-- ----------------------------
-- Records of biz_login
-- ----------------------------

-- ----------------------------
-- Table structure for `biz_order`
-- ----------------------------
DROP TABLE IF EXISTS `biz_order`;
CREATE TABLE `biz_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orderNo` char(18) DEFAULT NULL COMMENT '订单号',
  `carId` int(11) DEFAULT NULL COMMENT '车辆id',
  `carNo` varchar(10) DEFAULT NULL COMMENT '冗余车牌号',
  `driverId` int(11) DEFAULT NULL COMMENT '司机id',
  `passengerId` int(11) DEFAULT NULL COMMENT '用户id',
  `unitPrice` decimal(19,2) DEFAULT NULL COMMENT '单价',
  `person` tinyint(4) DEFAULT NULL COMMENT '人数',
  `discount` decimal(19,2) DEFAULT NULL COMMENT '折扣金额',
  `insuranceAmount` decimal(19,2) DEFAULT NULL COMMENT '保险金额',
  `orderTotal` decimal(19,2) DEFAULT NULL COMMENT '订单总金额',
  `paidAmount` decimal(19,2) DEFAULT NULL COMMENT '支付金额',
  `payStatus` tinyint(4) DEFAULT NULL COMMENT '支付状态（0：未支付，1：支付成功）',
  `payType` tinyint(255) DEFAULT NULL COMMENT '支付类型（0：微信，1：支付宝，2：现金支付）',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态（0：预订，1：完成，2：取消）',
  `cancelReason` varchar(20) DEFAULT NULL COMMENT '取消原因',
  `cancelTime` datetime DEFAULT NULL COMMENT '取消时间',
  `trainTimeId` int(11) DEFAULT NULL COMMENT '车次表id',
  `startCityId` int(11) DEFAULT NULL COMMENT '起点城市id',
  `startCityName` varchar(20) DEFAULT NULL COMMENT '起点城市名',
  `endCityId` int(11) DEFAULT NULL COMMENT '终点城市id',
  `endCityName` varchar(20) DEFAULT NULL COMMENT '终点城市名',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifyTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `modifiedById` int(11) DEFAULT NULL COMMENT '更新人id',
  `valid` tinyint(4) DEFAULT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`),
  KEY `ix_passengerId` (`passengerId`),
  KEY `ix_driverId` (`driverId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of biz_order
-- ----------------------------

-- ----------------------------
-- Table structure for `biz_passenger`
-- ----------------------------
DROP TABLE IF EXISTS `biz_passenger`;
CREATE TABLE `biz_passenger` (
  `id` int(11) NOT NULL COMMENT '主键',
  `mobile` char(11) NOT NULL COMMENT '手机号',
  `valid` tinyint(4) DEFAULT '1' COMMENT '是否有效（0：无效，1：有效）',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifyTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='乘客表';

-- ----------------------------
-- Records of biz_passenger
-- ----------------------------

-- ----------------------------
-- Table structure for `biz_phone_record`
-- ----------------------------
DROP TABLE IF EXISTS `biz_phone_record`;
CREATE TABLE `biz_phone_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `driverId` int(11) DEFAULT NULL COMMENT '司机id',
  `driverMobile` char(11) DEFAULT NULL COMMENT '司机电话',
  `passengerId` int(11) DEFAULT NULL COMMENT '乘客id',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `ix_driverId` (`driverId`),
  KEY `ix_passengerId` (`passengerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='电话记录表';

-- ----------------------------
-- Records of biz_phone_record
-- ----------------------------

-- ----------------------------
-- Table structure for `biz_train_time`
-- ----------------------------
DROP TABLE IF EXISTS `biz_train_time`;
CREATE TABLE `biz_train_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `defId` int(11) DEFAULT NULL COMMENT '对应定义表id',
  `driverId` int(11) DEFAULT NULL COMMENT '司机id',
  `driverName` varchar(20) DEFAULT NULL COMMENT '司机姓名',
  `driverMobile` char(11) DEFAULT NULL COMMENT '司机手机号',
  `driverSex` tinyint(4) DEFAULT NULL COMMENT '司机性别',
  `stock` int(11) DEFAULT NULL COMMENT '剩余座位',
  `startCityId` int(11) DEFAULT NULL COMMENT '出发城市id',
  `startCityName` varchar(20) DEFAULT NULL COMMENT '出发城市名',
  `endCityId` int(11) DEFAULT NULL COMMENT '到达城市id',
  `endCityName` varchar(20) DEFAULT NULL COMMENT '结束城市名',
  `departureTime` datetime DEFAULT NULL COMMENT '发车时间',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `valid` tinyint(4) DEFAULT NULL COMMENT '有效',
  PRIMARY KEY (`id`),
  KEY `ix_driverId` (`driverId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='车次表';

-- ----------------------------
-- Records of biz_train_time
-- ----------------------------

-- ----------------------------
-- Table structure for `biz_train_time_def`
-- ----------------------------
DROP TABLE IF EXISTS `biz_train_time_def`;
CREATE TABLE `biz_train_time_def` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `driverId` int(11) DEFAULT NULL COMMENT '司机表id',
  `startCityId` int(11) DEFAULT NULL COMMENT '起点城市id',
  `endCityId` int(11) DEFAULT NULL COMMENT '终点城市id',
  `departureTime` time DEFAULT NULL COMMENT '发车时间',
  `dayInWeek` int(11) DEFAULT NULL COMMENT '周几发车',
  `daysEvery` int(11) DEFAULT NULL COMMENT '每隔几天发车',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `ix_driverId` (`driverId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='车次定义表';

-- ----------------------------
-- Records of biz_train_time_def
-- ----------------------------
