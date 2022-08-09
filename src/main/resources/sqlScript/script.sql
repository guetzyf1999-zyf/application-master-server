
-- 创建应用全局自增ID序列
create sequence application_seq;

-- 重置应用全局序列化数字!慎用
alter sequence application_seq restart with 1;

--创建用户账户数据库序列
create sequence username_seq;

-- 用户账户从6000 四位数开始
alter sequence username_seq restart with 6000;

-- 用户表
create table app_user
(
    id                      int4 primary key default nextval('application_seq'::regclass),
    username                varchar(255) unique,
    password                varchar(255),
    nick_name               varchar(255),
    email                   varchar(255),
    telephone               varchar(255) unique,
    register_date           date,
    account_non_expired     boolean          default false,
    account_non_locked      boolean          default false,
    credentials_non_expired boolean          default false,
    enabled                 boolean          default false
);

-- 用户权限表
create table app_user_authorities
(
    app_user_id int8 references app_user (id), -- 外键关联用户ID
    authority   varchar(255) unique
);