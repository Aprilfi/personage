# Host: 127.0.0.1  (Version: 5.5.15)
# Date: 2018-06-04 16:51:23
# Generator: MySQL-Front 5.3  (Build 4.269)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "table_comment"
#

DROP TABLE IF EXISTS `table_comment`;
CREATE TABLE `table_comment` (
  `comment_id` varchar(40) NOT NULL,
  `user_id` varchar(40) NOT NULL,
  `content` text NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "table_comment"
#

INSERT INTO `table_comment` VALUES ('1100','2200','don\'t waste my time','2017-08-09 20:12:10');

#
# Structure for table "table_memo"
#

DROP TABLE IF EXISTS `table_memo`;
CREATE TABLE `table_memo` (
  `memo_id` varchar(50) NOT NULL,
  `edit_time` datetime NOT NULL,
  `send_time` datetime NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `state` int(2) NOT NULL,
  `memo_content` varchar(255) NOT NULL,
  PRIMARY KEY (`memo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "table_memo"
#


#
# Structure for table "table_user"
#

DROP TABLE IF EXISTS `table_user`;
CREATE TABLE `table_user` (
  `user_id` varchar(50) NOT NULL COMMENT '用户Id',
  `user_nickname` varchar(20) NOT NULL COMMENT '用户昵称',
  `user_password` varchar(32) NOT NULL COMMENT '用户密码',
  `user_email` varchar(50) NOT NULL COMMENT '用户邮箱\r\n	',
  `acti_state` int(11) NOT NULL COMMENT '激活状态，0表示未激活，1表示激活',
  `acti_code` varchar(50) NOT NULL COMMENT '随机验证码',
  `salt` varchar(50) NOT NULL COMMENT '随机盐，用于加密密码',
  `token_exptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用于判断邮箱链接有效时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "table_user"
#

