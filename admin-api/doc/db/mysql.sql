CREATE TABLE `admin_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(100) NOT NULL COMMENT '菜单名字',
  `parent_menu_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '上级ID',
  `order_num` int(8) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `url` varchar(100) NOT NULL DEFAULT '' COMMENT '菜单URL',
  `perms` varchar(500) NOT NULL DEFAULT '' COMMENT '权限值',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '菜单类型：1- 一级菜单不带url，2-二级菜单带url,3-三级菜单/按钮级',
  `icon` varchar(100) NOT NULL DEFAULT '' COMMENT '菜单图标',
  `ui_display` int(11) NOT NULL DEFAULT '1' COMMENT '界面显示：1-显示，0-不显示',
  `created_at` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建者',
  `updated_at` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `upcated_by` varchar(100) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

CREATE TABLE `admin_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(50) NOT NULL COMMENT '角色代码',
  `role_name` varchar(100) NOT NULL COMMENT '名字',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `created_at` timestamp NOT NULL COMMENT '创建时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建者',
  `updated_at` timestamp NOT NULL COMMENT '更新时间',
  `updated_by` varchar(100) NOT NULL COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_code_UNIQUE` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

CREATE TABLE `admin_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id_menu_id_UNIQUE` (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单表';

CREATE TABLE `admin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) NOT NULL,
  `user_name` varchar(100) DEFAULT '',
  `user_pwd` varchar(100) NOT NULL,
  `enable` varchar(2) NOT NULL DEFAULT '00' COMMENT '数据逻辑状态：01-有效，00-无效',
  `last_login_time` timestamp NOT NULL COMMENT '最后登录时间',
  `last_login_type` varchar(2) DEFAULT NULL COMMENT '最后登录类型：00-用户名密码，01-微信，02-钉钉，03-邮箱，04-手机短信，05-手机密码',
  `created_at` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建者',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `upcated_by` varchar(100) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `admin_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';
