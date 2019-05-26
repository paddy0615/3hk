CREATE DATABASE /*!32312 IF NOT EXISTS*/`faqsDate208` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `faqsDate208`;

/*Table structure for table `faqs_category` */

DROP TABLE IF EXISTS `faqs_category`;

CREATE TABLE `faqs_category` (
  `cat_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `cat_lang_id` int(11) NOT NULL COMMENT 'faq语言ID',
  `cat_title` varchar(500) NOT NULL COMMENT '类别名称',
  `cat_createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `cat_updatedate` datetime DEFAULT NULL COMMENT '修改时间',
  `cat_createuser` int(11) DEFAULT NULL COMMENT '创建人',
  `cat_updateuser` int(11) DEFAULT NULL COMMENT '修改人',
  `cat_status` int(11) DEFAULT '0' COMMENT '状态（1发布，0未发布）',
  `cat_ordertopdate` datetime DEFAULT NULL COMMENT '置顶排序（按时间）',
  PRIMARY KEY (`cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `faqs_category` */

/*Table structure for table `faqs_detailed` */

DROP TABLE IF EXISTS `faqs_detailed`;

CREATE TABLE `faqs_detailed` (
  `dl_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dl_lang_id` int(11) NOT NULL COMMENT 'faq语言ID',
  `dl_cat_id` int(11) NOT NULL COMMENT 'faq类别ID',
  `dl_title` varchar(500) NOT NULL COMMENT '详情标题',
  `dl_content` text COMMENT '详情内容,带html',
  `dl_contenttxt` text COMMENT '详情内容,纯文本',
  `dl_createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `dl_updatedate` datetime DEFAULT NULL COMMENT '修改时间',
  `dl_createuser` int(11) DEFAULT NULL COMMENT '创建人',
  `dl_updateuser` int(11) DEFAULT NULL COMMENT '修改人',
  `dl_status` int(11) DEFAULT '0' COMMENT '状态（1发布，0未发布）',
  `dl_ordertopdate` datetime DEFAULT NULL COMMENT '置顶排序（按时间）',
  PRIMARY KEY (`dl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=202 DEFAULT CHARSET=utf8;

/*Data for the table `faqs_detailed` */

/*Table structure for table `faqs_dl_hotspot` */

DROP TABLE IF EXISTS `faqs_dl_hotspot`;

CREATE TABLE `faqs_dl_hotspot` (
  `dlh_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dlh_dl_id` int(11) NOT NULL COMMENT 'faq详情ID',
  `dlh_search_count` int(11) NOT NULL DEFAULT '0' COMMENT '搜索数量',
  PRIMARY KEY (`dlh_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `faqs_dl_hotspot` */

insert  into `faqs_dl_hotspot`(`dlh_id`,`dlh_dl_id`,`dlh_search_count`) values (4,13,1);

/*Table structure for table `faqs_language` */

DROP TABLE IF EXISTS `faqs_language`;

CREATE TABLE `faqs_language` (
  `lang_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `lang_title` varchar(100) NOT NULL COMMENT '语言名称',
  `lang_problem` varchar(100) DEFAULT NULL COMMENT '语言问题',
  `lang_createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `lang_updatedate` datetime DEFAULT NULL COMMENT '修改时间',
  `lang_createuser` int(11) DEFAULT NULL COMMENT '创建人',
  `lang_updateuser` int(11) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`lang_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `faqs_language` */

insert  into `faqs_language`(`lang_id`,`lang_title`,`lang_problem`,`lang_createdate`,`lang_updatedate`,`lang_createuser`,`lang_updateuser`) values (1,'香港 (繁體)','常見問題','2018-10-30 09:55:32','2018-10-30 09:55:32',1,1),(2,'中国 (简体)','常见问题','2018-10-30 09:55:32','2018-10-30 09:55:32',1,1),(3,'中國台灣 (繁體)','常見問題','2018-10-30 09:55:32','2018-11-19 14:12:25',1,1),(4,'日本 (日本語))','よくあるご質問','2018-10-30 09:55:32','2018-10-30 09:55:32',1,1),(5,'대한민국(한국어)','자주묻는 질문','2018-10-30 09:55:32','2018-10-30 09:55:32',1,1),(6,'Hong Kong (EN)','FAQ','2018-10-30 09:55:32','2018-10-30 09:55:32',1,1);

/*Table structure for table `faqs_monitor` */

DROP TABLE IF EXISTS `faqs_monitor`;

CREATE TABLE `faqs_monitor` (
  `m_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `m_clientip` varchar(200) DEFAULT NULL COMMENT 'ip',
  `m_lang_id` int(11) DEFAULT NULL COMMENT 'faq语言ID',
  `m_cat_id` int(11) DEFAULT NULL COMMENT 'faq类别ID',
  `m_dl_id` int(11) DEFAULT NULL COMMENT 'faq答案ID',
  `m_createdate` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB AUTO_INCREMENT=963 DEFAULT CHARSET=utf8;


/*Data for the table `faqs_monitor` */


/*Table structure for table `faqs_user` */

DROP TABLE IF EXISTS `faqs_user`;

CREATE TABLE `faqs_user` (
  `usr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `usr_login_id` varchar(100) NOT NULL COMMENT '账号',
  `usr_password` varchar(100) NOT NULL COMMENT '密码',
  `usr_role` varchar(100) NOT NULL COMMENT '角色（admin管理员用户，ordinary普通用户）',
  PRIMARY KEY (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `faqs_user` */

insert  into `faqs_user`(`usr_id`,`usr_login_id`,`usr_password`,`usr_role`) values (1,'admin','admin','admin');

-- 标签表

DROP TABLE IF EXISTS `faqs_detailed_tags`;
CREATE TABLE `faqs_detailed_tags` (
 `dt_id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
 `dt_title` VARCHAR(200) NULL COMMENT 'tags名',
 `dt_createdate` DATETIME COMMENT '创建时间',
  PRIMARY KEY (`dt_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

-- 标签和问题的关系表

DROP TABLE IF EXISTS `faqs_dtags_relation`;
CREATE TABLE `faqs_dtags_relation` (
 `dr_id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
 `dr_dt_id` INT NULL COMMENT '详细标签ID',
 `dr_dl_id` INT NULL COMMENT 'faq详细ID',
 `dr_order` INT NULL COMMENT '排序序号',
 `dr_createdate` DATETIME COMMENT '创建时间',
  PRIMARY KEY (`dr_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `faqs_detailed_feedback`;
CREATE TABLE `faqs_detailed_feedback` (
 `df_id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
 `df_type` INT NULL COMMENT '类别(1支持,2反对)',
 `df_dl_id` INT NULL COMMENT 'faq详细ID',
 `df_ip` VARCHAR(100) NULL COMMENT 'ip',
 `df_nay_content` VARCHAR(100) NULL COMMENT '反对内容',
 `df_createdate` DATETIME COMMENT '创建时间',
  PRIMARY KEY (`df_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

--------------- 2.2版

DROP TABLE IF EXISTS `faqs_no_tags`;
CREATE TABLE `faqs_no_tags` (
 `nt_id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
 `nt_lang_id` INT NULL COMMENT '语言Id',
 `nt_ip` VARCHAR(100) NULL COMMENT 'IP',
 `nt_title` VARCHAR(100) NULL COMMENT '标题',
 `nt_count` INT NULL COMMENT '数量',
 `nt_createdate` DATETIME COMMENT '创建时间',
  PRIMARY KEY (`nt_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;


-- faqs_detailed_feedback表添加,email收集,电话收集
ALTER TABLE faqs_detailed_feedback ADD df_nay_email VARCHAR(100) NULL;
ALTER TABLE faqs_detailed_feedback ADD df_nay_number VARCHAR(100) NULL;



DROP TABLE IF EXISTS `faqs_librabry`;
CREATE TABLE `faqs_librabry` (
 `fl_id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
 `fl_title` VARCHAR(100) NULL COMMENT '标题',
 `fl_remarks` VARCHAR(200) NULL COMMENT '备注',
 `fl_createdate` DATETIME COMMENT '创建时间',
 `fl_updatedate` DATETIME COMMENT '更新时间',
  PRIMARY KEY (`fl_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

-- 添加父级字段
ALTER TABLE faqs_detailed ADD dl_fl_id INT DEFAULT 0;
--  修改faqs_detailed表, dl_cat_id可以为空 , 防止出错
ALTER TABLE faqs_detailed MODIFY COLUMN dl_cat_id INT DEFAULT NULL COMMENT '类别可以为空,字段已不用';

-- 添加权重, 用搜索排序
ALTER TABLE faqs_detailed ADD dl_weights INT DEFAULT 0;


-- 父级数据错乱
UPDATE faqs_librabry SET fl_title = CONCAT('Q',fl_id);

SET @rownum=0;
UPDATE faqs_librabry
SET fl_title = CONCAT('Q',(
SELECT @rownum := @rownum +1 AS nid)
);

-- 反对内容字段变大
ALTER TABLE faqs_detailed_feedback MODIFY COLUMN df_nay_content VARCHAR(500) NULL COMMENT '反对内容';

-- 反馈表,添加状态(有反馈信息case)
ALTER TABLE faqs_detailed_feedback ADD df_nay_status INT DEFAULT 0;

-- 添加父级(用程序去跑添加 , 时间不超一天.)
ALTER TABLE faqs_monitor ADD m_dl_id_father INT DEFAULT 0;



-- e-form 地区名
DROP TABLE IF EXISTS `e_area_name`;
CREATE TABLE `e_area_name` (
 `a_id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
 `a_group_hk` VARCHAR(200) NULL COMMENT '标题分类-繁体',
 `a_group_cn` VARCHAR(200) NULL COMMENT '标题分类-简体',
 `a_group_en` VARCHAR(200) NULL COMMENT '标题分类-英文',
 `a_key` VARCHAR(100) NULL COMMENT '表示',
 `a_title_hk` VARCHAR(200) NULL COMMENT '繁体-名',
 `a_title_cn` VARCHAR(200) NULL COMMENT '简体-名',
 `a_title_en` VARCHAR(200) NULL COMMENT '英文-名',
 `a_updatedate` TIMESTAMP  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`a_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT  INTO `e_area_name`(`a_group_hk`,`a_group_cn`,`a_group_en`,`a_key`,`a_title_hk`,`a_title_cn`,`a_title_en`)
VALUES ('香港特別行政區','中国香港','Hong Kong SAR','HKG','香港(HKG)','香港(HKG)','Hong Kong(HKG)'),
('柬埔寨','柬埔寨','Cambodia','REP','暹粒(REP)','暹粒(REP)','Siem Reap(REP)'),
('中國大陸','中国大陆','Mainland China','NGB','寧波(NGB)','宁波(NGB)','Ningbo(NGB)'),
('日本','日本','Japan','FUK','福岡(FUK)','福冈(FUK)','Fukuoka(FUK)'),
('日本','日本','Japan','HIJ','廣島(HIJ)','广岛(HIJ)','Hiroshima(HIJ)'),
('日本','日本','Japan','ISG','石垣島(ISG)','石垣岛(ISG)','Ishigaki(ISG)'),
('日本','日本','Japan','KOJ','鹿兒島(KOJ)','鹿儿岛(KOJ)','Kagoshima(KOJ)'),
('日本','日本','Japan','KMJ','熊本(KMJ)','熊本(KMJ)','Kumamoto(KMJ)'),
('日本','日本','Japan','NGS','長崎(NGS)','长崎(NGS)','Nagasaki(NGS)'),
('日本','日本','Japan','NGO','名古屋(NGO)','名古屋(NGO)','Nagoya-Chubu(NGO)'),
('日本','日本','Japan','KIX','大阪-關西(KIX)','大阪-关西(KIX)','Osaka-Kansai(KIX)'),
('日本','日本','Japan','SHI','宮古 (下地島)(SHI)','宫古（下地岛）(SHI)','Miyako (Shimojishima)(SHI)'),
('日本','日本','Japan','TAK','高松(TAK)','高松(TAK)','Takamatsu(TAK)'),
('日本','日本','Japan','HND','東京-羽田(HND)','东京-羽田(HND)','Tokyo-Haneda(HND)'),
('日本','日本','Japan','NRT','東京-成田(NRT)','东京-成田(NRT)','Tokyo-Narita(NRT)'),
('韓國','韩国','South Korea','PUS','釜山(PUS)','釜山(PUS)','Busan-Gimhae(PUS)'),
('韓國','韩国','South Korea','CJU','濟州(CJU)','济州(CJU)','Jeju(CJU)'),
('韓國','韩国','South Korea','ICN','首爾-仁川(ICN)','首尔-仁川(ICN)','Seoul-Incheon(ICN)'),
('中國台灣','中国台湾','Taiwan, China','RMQ','台中(RMQ)','台中(RMQ)','Taichung(RMQ)'),
('泰國','泰国','Thailand','BKK','曼谷(BKK)','曼谷(BKK)','Bangkok(BKK)'),
('泰國','泰国','Thailand','CNX','清邁(CNX)','清迈(CNX)','Chiang Mai(CNX)'),
('泰國','泰国','Thailand','CEI','清萊(CEI)','清莱(CEI)','Chiang Rai(CEI)'),
('泰國','泰国','Thailand','HKT','布吉(HKT)','普吉(HKT)','Phuket(HKT)'),
('美國屬地','美国属地','Northern Mariana Islands','DAD','峴港(DAD)','岘港(DAD)','Da Nang(DAD)'),
('越南','越南','Vietnam','CXR','芽莊(CXR)','芽庄(CXR)','Nha Trang(CXR)'),
('越南','越南','Vietnam','SPN','塞班(SPN)','塞班(SPN)','Saipan(SPN)');




-- e_currency 货币
DROP TABLE IF EXISTS `e_currency`;
CREATE TABLE `e_currency` (
 `ec_id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
 `ec_title` VARCHAR(200) NULL COMMENT '标题',
  PRIMARY KEY (`ec_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;


-- e-form 记录表
DROP TABLE IF EXISTS `e_form`;
CREATE TABLE `e_form` (
 `e_id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
 `e_lang_id` INT NULL COMMENT '语言Id',
 `e_type` INT(11) NOT NULL COMMENT '类型(1:Resend Itinerary重新發送行程單 , 2:Duplicate Mulitiple Booking重復訂單,
				3:Request for Certificate證明申請 , 4:Name Correction姓名修正 ,
				5:Payment Failure支付失敗 , 6:Reconfirm Flight 確認航班 )',
 `e_pnr` VARCHAR(100) NULL COMMENT 'PNR',
 `e_first_name` VARCHAR(50) NULL COMMENT '名',
 `e_last_name` VARCHAR(50) NULL COMMENT '性',
 `e_email` VARCHAR(200) NULL COMMENT '邮箱',
 `e_trip_type` INT(11) DEFAULT 0 COMMENT '旅行类型[1返往(默认)，2单程, 3多城市]',
 `e_trip_departing` VARCHAR(200) NULL COMMENT '出发地',
 `e_trip_going` VARCHAR(200) NULL COMMENT '目的地',
 `e_trip_departingdate` DATETIME COMMENT '出发日期',
 `e_trip_goingdate` DATETIME COMMENT '回程日期',
 `e_trip_departing_new` VARCHAR(200) NULL COMMENT '出发地1',
 `e_trip_going_new` VARCHAR(200) NULL COMMENT '目的地1',
 `e_relation` INT(11) DEFAULT 0 COMMENT '是否有关系(0无,1第一,2第二)',
 `e_relation_eid` INT(11) DEFAULT 0 COMMENT '关系的id',
 `e_certificate_type` INT(11) DEFAULT 0 COMMENT '证书类型表',
 `e_first_name_new` VARCHAR(50) NULL COMMENT '新-名',
 `e_last_name_new` VARCHAR(50) NULL COMMENT '新-性',
 `e_flie` VARCHAR(200) NULL COMMENT '文件名',
 `e_payment_type` INT(11) DEFAULT 0 COMMENT '支付类型(1:支付宝,2:信用卡,3:微信)',
 `e_payment_transaction` VARCHAR(200) NULL COMMENT '交易号',
 `e_payment_amount` VARCHAR(200) NULL COMMENT '支付金额',
 `e_currency_id` INT(11) DEFAULT 0 NULL COMMENT 'e_currency货币外表',
 `e_payment_accounts` VARCHAR(200) NULL COMMENT '账号',
 `e_random` INT(11) DEFAULT 0 NULL COMMENT 'e_currency货币外表',
 `e_updatedate` TIMESTAMP  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`e_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

-- e_form_result 对接口返回记录
DROP TABLE IF EXISTS `e_form_result`;
CREATE TABLE `e_form_result` (
 `er_id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
 `er_e_id` INT NULL COMMENT 'e-form表单ID',
 `er_result` VARCHAR(100) NULL COMMENT '解析接口返回值(0:Matched;其它值表示Not Matched)',
 `er_result_xml` VARCHAR(200) NULL COMMENT '接口返回值',
  PRIMARY KEY (`er_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;


-- e_form_type 分类
DROP TABLE IF EXISTS `e_form_type`;
CREATE TABLE `e_form_type` (
 `et_id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
 `et_title_hk` VARCHAR(200) NULL COMMENT '繁体-名',
 `et_title_cn` VARCHAR(200) NULL COMMENT '简体-名',
 `et_title_en` VARCHAR(200) NULL COMMENT '英文-名',
  PRIMARY KEY (`et_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT  INTO `e_form_type`(`et_title_hk`,`et_title_cn`,`et_title_en`)
VALUES ('重新發送行程單','重新发送行程单','Request for itinerary'),
('重復訂單','重复订单','Duplicate Booking'),
('證明申請','证明申请','Request for Certificate'),
('姓名修正','姓名修正','Name Correction'),
('支付失敗','支付失敗','Payment Failure'),
('確認航班','确认航班','Reconfirm Flight');

-- e-form和问题的关系表
DROP TABLE IF EXISTS `faqs_eform_relation`;
CREATE TABLE `faqs_eform_relation` (
 `er_id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
 `er_et_id` INT NULL COMMENT 'e-form类型',
 `er_dl_id` INT NULL COMMENT 'faq详细ID',
  PRIMARY KEY (`er_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;


-- e_certificate 证书类别
DROP TABLE IF EXISTS `e_certificate`;
CREATE TABLE `e_certificate` (
 `ec_id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
 `ec_title_hk` VARCHAR(200) NULL COMMENT '繁体-名',
 `ec_title_cn` VARCHAR(200) NULL COMMENT '简体-名',
 `ec_title_en` VARCHAR(200) NULL COMMENT '英文-名',
  PRIMARY KEY (`ec_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT  INTO `e_certificate`(`ec_title_hk`,`ec_title_cn`,`ec_title_en`)
VALUES ('航班延誤證明','航班延误证明','Flight Delay Certificate'),
('搭乘航班證明','搭乘航班证明','Travel Certificate'),
('無搭乘航班證明','无搭乘航班证明','Confirmation Letter of No show'),
('航班取消證明','航班取消证明','Flight Cancel Certificate');


-- e-form ,添加dlId 从哪条问题进
ALTER TABLE e_form ADD e_dl_id INT DEFAULT 0;
