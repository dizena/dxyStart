/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : dxystart

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-11-19 17:00:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_oauth_token
-- ----------------------------
DROP TABLE IF EXISTS `base_oauth_token`;
CREATE TABLE `base_oauth_token` (
  `id` varchar(100) NOT NULL,
  `companyId` varchar(100) DEFAULT NULL COMMENT '企业ID',
  `userId` varchar(100) DEFAULT NULL COMMENT '用户ID',
  `startTime` datetime DEFAULT NULL COMMENT '开始授权时间',
  `eename` varchar(100) DEFAULT NULL COMMENT '企业名称',
  `flag` varchar(10) DEFAULT NULL COMMENT '审核标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_oauth_token
-- ----------------------------

-- ----------------------------
-- Table structure for base_outh_company
-- ----------------------------
DROP TABLE IF EXISTS `base_outh_company`;
CREATE TABLE `base_outh_company` (
  `id` varchar(100) NOT NULL COMMENT '授权ID',
  `secret` varchar(150) DEFAULT NULL COMMENT '授权密码',
  `resposeUrl` varchar(200) DEFAULT NULL COMMENT '返回地址',
  `eename` varchar(100) DEFAULT NULL COMMENT '企业名称',
  `eelogo` varchar(200) DEFAULT NULL COMMENT '企业LOGO的地址',
  `eeurl` varchar(100) DEFAULT NULL,
  `bindUserid` varchar(100) DEFAULT NULL,
  `flag` varchar(10) DEFAULT NULL COMMENT '审核标记',
  `systime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_outh_company
-- ----------------------------
INSERT INTO `base_outh_company` VALUES ('JGA144CMW176QDU693KSP0350880', 'GEN144VJA176WBF694DCI6309601', 'http://www.mm.com/dxylogin2', '测试', '/upload/image/20150908/20150908150605_981.jpg', 'http://www.mm.com', null, '1', '2015-09-09 10:49:06');

-- ----------------------------
-- Table structure for base_ress
-- ----------------------------
DROP TABLE IF EXISTS `base_ress`;
CREATE TABLE `base_ress` (
  `id` varchar(100) NOT NULL,
  `resType` varchar(50) DEFAULT NULL,
  `resName` varchar(50) DEFAULT NULL,
  `resUrl` varchar(300) DEFAULT NULL,
  `resSort` int(20) DEFAULT NULL,
  `resLevel` int(11) DEFAULT NULL,
  `resPid` varchar(100) DEFAULT NULL,
  `resAuth` varchar(50) DEFAULT NULL,
  `resIcon` varchar(300) DEFAULT NULL,
  `locked` varchar(2) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_sys_resource_parent_id` (`resSort`),
  KEY `idx_sys_resource_parent_ids` (`resLevel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_ress
-- ----------------------------
INSERT INTO `base_ress` VALUES ('CJG144PBD170TUS293ZNO9932453', '1', '三方授权', 'javascript:;', '5', '1', '0', 'none', 'fa fa-hand-pointer-o', '0');
INSERT INTO `base_ress` VALUES ('CNV144GXD170IQB295LJR969772', '1', '授权企业', '/master/ocomp/ocompList', '501', '2', 'CJG144PBD170TUS293ZNO9932453', 'none', 'fa fa-star', '0');
INSERT INTO `base_ress` VALUES ('EHX144TJM064PDO898BAC6060140', '1', '资源管理', '/master/ressLists', '201', '2', 'WJN144IRT064QDY653GFM0920510', 'none', 'fa fa-star', '0');
INSERT INTO `base_ress` VALUES ('JRV144CUT064KEX901SBG6408488', '1', '在线用户', '/master/online/list', '204', '2', 'WJN144IRT064QDY653GFM0920510', 'none', 'fa fa-star', '0');
INSERT INTO `base_ress` VALUES ('SOA144YBZ064JMW858EDT3855148', '1', '用户管理', '/master/userLists', '203', '2', 'WJN144IRT064QDY653GFM0920510', 'none', 'fa fa-star', '0');
INSERT INTO `base_ress` VALUES ('UKD144EQV064ZWM164AJY033058', '1', '首页', '/master/index', '1', '1', '0', 'none', 'fa fa-dashboard', '0');
INSERT INTO `base_ress` VALUES ('WJN144IRT064QDY653GFM0920510', '1', '系统配置', 'javascript:;', '2', '1', '0', 'none', 'fa fa-gear', '0');
INSERT INTO `base_ress` VALUES ('XIT144NLP064UCG904DYO3435667', '1', 'URL权限', '/master/urlauth/lists', '205', '2', 'WJN144IRT064QDY653GFM0920510', 'none', 'fa fa-star', '0');
INSERT INTO `base_ress` VALUES ('XZM144TAH064LCB894YGW5650232', '1', '角色管理', '/master/roleLists', '202', '2', 'WJN144IRT064QDY653GFM0920510', 'none', 'fa fa-star', '0');

-- ----------------------------
-- Table structure for base_role
-- ----------------------------
DROP TABLE IF EXISTS `base_role`;
CREATE TABLE `base_role` (
  `id` varchar(100) NOT NULL,
  `role` varchar(20) DEFAULT NULL,
  `state` varchar(200) DEFAULT NULL,
  `locked` varchar(10) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_role
-- ----------------------------
INSERT INTO `base_role` VALUES ('EDI144XUW072RPV854THF8420728', 'user', '普通的用户', '0');
INSERT INTO `base_role` VALUES ('TQS144UFZ073EDO118LBY863124', 'master', '系统管理员', '0');

-- ----------------------------
-- Table structure for base_roleres
-- ----------------------------
DROP TABLE IF EXISTS `base_roleres`;
CREATE TABLE `base_roleres` (
  `id` varchar(100) NOT NULL,
  `roleId` varchar(100) DEFAULT NULL,
  `ressId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_role_resource_ids` (`ressId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_roleres
-- ----------------------------
INSERT INTO `base_roleres` VALUES ('BEO144ZFR109CJD428UIS2275925', 'EDI144XUW072RPV854THF8420728', 'UKD144EQV064ZWM164AJY033058');
INSERT INTO `base_roleres` VALUES ('BGW144OSN109FVD428XHJ2279560', 'EDI144XUW072RPV854THF8420728', 'ICN144ADV065BGQ113OZX9019147');
INSERT INTO `base_roleres` VALUES ('BSJ144IQY462NVX975ADU233278', 'TQS144UFZ073EDO118LBY863124', 'NWS144UKY065MCE130DAZ5840609');
INSERT INTO `base_roleres` VALUES ('ERM144ITQ462XCO975LDZ2318877', 'TQS144UFZ073EDO118LBY863124', 'WJN144IRT064QDY653GFM0920510');
INSERT INTO `base_roleres` VALUES ('EYT144JSC462KNO975BUQ2330257', 'TQS144UFZ073EDO118LBY863124', 'XOL144HBJ065SGR120QCN5796125');
INSERT INTO `base_roleres` VALUES ('FEP144ALH462YTS975BGW2323933', 'TQS144UFZ073EDO118LBY863124', 'JRV144CUT064KEX901SBG6408488');
INSERT INTO `base_roleres` VALUES ('HJY144LOT462CGW975NDR2327473', 'TQS144UFZ073EDO118LBY863124', 'RNA144QBX109YWU562SMI7434348');
INSERT INTO `base_roleres` VALUES ('LVR144TYE109JOM428UQD2281983', 'EDI144XUW072RPV854THF8420728', 'XOL144HBJ065SGR120QCN5796125');
INSERT INTO `base_roleres` VALUES ('NVA144WXM462TGE975YLP2336529', 'TQS144UFZ073EDO118LBY863124', 'CNV144GXD170IQB295LJR969772');
INSERT INTO `base_roleres` VALUES ('OEH144KDB109RZN428IUP2278288', 'EDI144XUW072RPV854THF8420728', 'ZMR144QLK074XDB457AHW061010');
INSERT INTO `base_roleres` VALUES ('PQN144JUC462DGO975VZX2328339', 'TQS144UFZ073EDO118LBY863124', 'ICN144ADV065BGQ113OZX9019147');
INSERT INTO `base_roleres` VALUES ('RLB144CTS462FWU975YJI2325112', 'TQS144UFZ073EDO118LBY863124', 'XRE144TFD065MBP095NUZ6999688');
INSERT INTO `base_roleres` VALUES ('THR144WAV109DGO428SXU228243', 'EDI144XUW072RPV854THF8420728', 'NWS144UKY065MCE130DAZ5840609');
INSERT INTO `base_roleres` VALUES ('TXE144OPJ462KUC975LFH2317134', 'TQS144UFZ073EDO118LBY863124', 'UKD144EQV064ZWM164AJY033058');
INSERT INTO `base_roleres` VALUES ('UCE144YWO462BGV975XNP2315786', 'TQS144UFZ073EDO118LBY863124', '0');
INSERT INTO `base_roleres` VALUES ('UFJ144SBA109EYW428PNM2272140', 'EDI144XUW072RPV854THF8420728', '0');
INSERT INTO `base_roleres` VALUES ('ULT144ZMG462DAH975YIP2320335', 'TQS144UFZ073EDO118LBY863124', 'XZM144TAH064LCB894YGW5650232');
INSERT INTO `base_roleres` VALUES ('URQ144AMK462HNW975VDS2321294', 'TQS144UFZ073EDO118LBY863124', 'SOA144YBZ064JMW858EDT3855148');
INSERT INTO `base_roleres` VALUES ('VNE144XUH462SQM975ABI2326688', 'TQS144UFZ073EDO118LBY863124', 'ZMR144QLK074XDB457AHW061010');
INSERT INTO `base_roleres` VALUES ('XAY144GWK462MQJ975ZHV2319502', 'TQS144UFZ073EDO118LBY863124', 'EHX144TJM064PDO898BAC6060140');
INSERT INTO `base_roleres` VALUES ('XIM144OZL462BPU975ADY2324548', 'TQS144UFZ073EDO118LBY863124', 'XIT144NLP064UCG904DYO3435667');
INSERT INTO `base_roleres` VALUES ('XZG144FLQ462OIB975EHD233799', 'TQS144UFZ073EDO118LBY863124', 'YWE144HPO462FCX951MLQ7997234');
INSERT INTO `base_roleres` VALUES ('YDT144GNR109UBV428IWO2277368', 'EDI144XUW072RPV854THF8420728', 'XRE144TFD065MBP095NUZ6999688');
INSERT INTO `base_roleres` VALUES ('YXI144AUC462QLG975TEJ233479', 'TQS144UFZ073EDO118LBY863124', 'CJG144PBD170TUS293ZNO9932453');
INSERT INTO `base_roleres` VALUES ('ZYR144KFQ462MOC975GPH2339607', 'TQS144UFZ073EDO118LBY863124', 'FOL144GDK462JQW972VPS0274199');

-- ----------------------------
-- Table structure for base_uriauth
-- ----------------------------
DROP TABLE IF EXISTS `base_uriauth`;
CREATE TABLE `base_uriauth` (
  `id` varchar(100) NOT NULL,
  `uname` varchar(100) DEFAULT NULL,
  `url` varchar(100) NOT NULL,
  `roles` varchar(100) DEFAULT NULL,
  `auths` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_uriauth
-- ----------------------------

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user` (
  `id` varchar(100) NOT NULL COMMENT '主键',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `passwd` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) DEFAULT NULL COMMENT '盐',
  `roles` varchar(511) DEFAULT NULL COMMENT '角色',
  `auths` varchar(511) DEFAULT NULL COMMENT '权限',
  `locked` varchar(10) DEFAULT NULL COMMENT '是否锁定',
  `regTime` datetime DEFAULT NULL COMMENT '注册时间',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `icon` varchar(300) DEFAULT NULL COMMENT '图标',
  `location` varchar(300) DEFAULT NULL COMMENT '地点',
  `flag` varchar(30) DEFAULT NULL COMMENT 'sina,qq,dxy',
  `token` varchar(120) DEFAULT NULL COMMENT 'token',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(30) DEFAULT NULL COMMENT '电话',
  `truename` varchar(30) DEFAULT NULL COMMENT '真实姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `minzu` varchar(30) DEFAULT NULL COMMENT '民族',
  `cardid` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `addressNow` varchar(150) DEFAULT NULL COMMENT '现居地址',
  `addressHome` varchar(150) DEFAULT NULL COMMENT '家庭住址',
  `addressJob` varchar(150) DEFAULT NULL COMMENT '单位地址',
  `salaryRange` varchar(100) DEFAULT NULL COMMENT '薪资范围',
  `systime` datetime DEFAULT NULL,
  `qt1` varchar(100) DEFAULT NULL,
  `answer1` varchar(100) DEFAULT NULL,
  `qt2` varchar(100) DEFAULT NULL,
  `answer2` varchar(100) DEFAULT NULL,
  `num01` int(11) DEFAULT NULL,
  `str01` varchar(100) DEFAULT NULL,
  `str02` varchar(100) DEFAULT NULL,
  `str03` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_user
-- ----------------------------
INSERT INTO `base_user` VALUES ('1', 'admin', '937a9d3997245b24355295134bf49742', 'RPNODI0NjkxMjMzMTEyNDA5Nzg23KB', 'master', '*', '0', '2015-07-09 00:00:00', '淡香雅', '男', '/upload/image/20150820/20150820124756_342.jpeg', null, null, null, '1066031245@qq.com', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
-- ----------------------------
-- Table structure for base_user1info
-- ----------------------------
DROP TABLE IF EXISTS `base_user1info`;
CREATE TABLE `base_user1info` (
  `id` varchar(100) NOT NULL,
  `uid` varchar(100) DEFAULT NULL,
  `xinzuo` varchar(100) DEFAULT NULL COMMENT '星座',
  `birthday` varchar(30) DEFAULT NULL COMMENT '生日',
  `shengxiao` varchar(30) DEFAULT NULL COMMENT '生效',
  `lovetype` varchar(100) DEFAULT NULL COMMENT '爱好类型',
  `lovebook` varchar(100) DEFAULT NULL COMMENT '喜爱的书',
  `lovemusic` varchar(100) DEFAULT NULL,
  `lovevideo` varchar(100) DEFAULT NULL,
  `loveweb` varchar(100) DEFAULT NULL,
  `loveother` varchar(150) DEFAULT NULL,
  `ismarry` varchar(10) DEFAULT NULL,
  `ischildren` varchar(10) DEFAULT NULL,
  `cartype` varchar(100) DEFAULT NULL COMMENT '薪资范围',
  `house` varchar(100) DEFAULT NULL,
  `systime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_user1info
-- ----------------------------

-- ----------------------------
-- Table structure for base_user2edu
-- ----------------------------
DROP TABLE IF EXISTS `base_user2edu`;
CREATE TABLE `base_user2edu` (
  `id` varchar(100) NOT NULL,
  `uid` varchar(100) DEFAULT NULL,
  `startTime` varchar(30) DEFAULT NULL,
  `endTime` varchar(30) DEFAULT NULL,
  `grade` varchar(100) DEFAULT NULL,
  `school` varchar(100) DEFAULT NULL COMMENT '学校',
  `dept` varchar(100) DEFAULT NULL COMMENT '系别',
  `className` varchar(100) DEFAULT NULL COMMENT '班级',
  `education` varchar(100) DEFAULT NULL COMMENT '学历',
  `systime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_user2edu
-- ----------------------------

-- ----------------------------
-- Table structure for base_user3job
-- ----------------------------
DROP TABLE IF EXISTS `base_user3job`;
CREATE TABLE `base_user3job` (
  `id` varchar(100) NOT NULL,
  `uid` varchar(100) DEFAULT NULL,
  `eename` varchar(100) DEFAULT NULL COMMENT '企业名称',
  `jobName` varchar(100) DEFAULT NULL COMMENT '职业名称',
  `salary` int(11) DEFAULT NULL COMMENT '薪资待遇',
  `startTime` varchar(100) DEFAULT NULL COMMENT '开始时间',
  `endTime` varchar(100) DEFAULT NULL COMMENT '结束时间',
  `industry` varchar(100) DEFAULT NULL COMMENT '行业范畴',
  `jobcontext` varchar(1000) DEFAULT NULL COMMENT '工作内容介绍',
  `systime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_user3job
-- ----------------------------

-- ----------------------------
-- Table structure for base_user4log
-- ----------------------------
DROP TABLE IF EXISTS `base_user4log`;
CREATE TABLE `base_user4log` (
  `id` varchar(100) NOT NULL,
  `uid` varchar(100) DEFAULT NULL,
  `loginTime` varchar(30) DEFAULT NULL,
  `loginPlace` varchar(100) DEFAULT NULL,
  `loginIp` varchar(50) DEFAULT NULL,
  `loginDevice` varchar(30) DEFAULT NULL,
  `loginCount` bigint(20) DEFAULT NULL,
  `loginDuration` bigint(20) DEFAULT NULL COMMENT '在线时长',
  `systime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_user4log
-- ----------------------------
