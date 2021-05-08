/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/5/7 17:02:30                            */
/*==============================================================*/


drop table if exists account;

drop table if exists admin;

drop table if exists attention;

drop table if exists blog;

drop table if exists comment;

drop table if exists feedback;

drop table if exists fond;

drop table if exists log;

drop table if exists lose;

drop table if exists quest;

drop table if exists say;

drop table if exists sys_information;

drop table if exists sys_message;

drop table if exists together;

drop table if exists usr;

/*==============================================================*/
/* Table: account                                               */
/*==============================================================*/
create table account
(
   account_id           int not null auto_increment,
   account_admin        varchar(15),
   account_usr          int,
   account_snum         varchar(20),
   account_password     varchar(30),
   account_reg_time     datetime,
   account_authority    int,
   account_access       tinyint,
   primary key (account_id)
) charset=utf8, engine=innodb;

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   admin_name           varchar(15) not null,
   admin_regtime        datetime,
   admin_password       varchar(30),
   admin_touch          varchar(30),
   admin_token          varchar(15),
   admin_authority      int,
   primary key (admin_name)
) charset=utf8, engine=innodb;

/*==============================================================*/
/* Table: attention                                             */
/*==============================================================*/
create table attention
(
   attention_id         int not null auto_increment,
   attention_target     int,
   attention_sender     int,
   attention_time       date,
   primary key (attention_id)
) charset=utf8, engine=innodb;

/*==============================================================*/
/* Table: blog                                                  */
/*==============================================================*/
create table blog
(
   blog_id              int not null auto_increment,
   blog_content         varchar(5000),
   blog_title           varchar(30),
   blog_brief           varchar(100),
   blog_keyword         varchar(30),
   blog_points          bigint,
   blog_status          tinyint,
   blog_date            date,
   blog_author          int,
   primary key (blog_id)
) charset=utf8, engine=innodb;

/*==============================================================*/
/* Table: comment                                               */
/*==============================================================*/
create table comment
(
   comment_id           int not null auto_increment,
   comment_sender       int,
   comment_target       int,
   comment_content      varchar(50),
   comment_points       int,
   comment_date         date,
   primary key (comment_id)
);

/*==============================================================*/
/* Table: feedback                                              */
/*==============================================================*/
create table feedback
(
   feedback_id          int not null auto_increment,
   feedback_sender      int,
   feedback_target      int,
   feedback_handler     varchar(15),
   feedback_reason      varchar(100),
   feedback_date        date,
   feedback_status      tinyint,
   feedback_result      varchar(30),
   feedback_handle_time datetime,
   primary key (feedback_id)
);

/*==============================================================*/
/* Table: fond                                                  */
/*==============================================================*/
create table fond
(
   fond_id              int not null auto_increment,
   fond_sender          int,
   fond_target          int,
   fond_date            date,
   primary key (fond_id)
);

/*==============================================================*/
/* Table: log                                                   */
/*==============================================================*/
create table log
(
   log_id               int not null auto_increment,
   log_admin            varchar(15),
   log_time             datetime,
   log_ip               varchar(15),
   log_desc             varchar(30),
   primary key (log_id)
);

/*==============================================================*/
/* Table: lose                                                  */
/*==============================================================*/
create table lose
(
   lose_id              int not null auto_increment,
   lose_sender          int,
   lose_item            varchar(30),
   lose_touch           varchar(30),
   lose_place           varchar(30),
   lose_time            datetime,
   lose_noname          tinyint,
   lose_status          tinyint,
   primary key (lose_id)
);

/*==============================================================*/
/* Table: quest                                                 */
/*==============================================================*/
create table quest
(
   quest                int not null auto_increment,
   quest_acceptor       int,
   quest_assignor       int,
   quest_time           datetime,
   quest_place          varchar(30),
   quest_ddl            datetime,
   quest_reward         decimal,
   quest_status         int,
   quest_touch          varchar(30),
   primary key (quest)
);

/*==============================================================*/
/* Table: say                                                   */
/*==============================================================*/
create table say
(
   say_id               int not null auto_increment,
   say_sender           int,
   say_content          varchar(200),
   say_time             datetime,
   say_points           int,
   say_critical         int,
   primary key (say_id)
);

/*==============================================================*/
/* Table: "sys_information"                                  */
/*==============================================================*/
create table sys_information
(
   sysi_id              int not null auto_increment,
   sysi_users           int,
   sysi_active          int,
   sysi_blogs           int,
   sysi_io              int,
   sysi_login           int,
   sysi_point           int,
   sysi_date            date,
   primary key (sysi_id)
);

/*==============================================================*/
/* Table: "sys_message"                                      */
/*==============================================================*/
create table sys_message
(
   sysm_id              int not null auto_increment,
   admin_name           varchar(15),
   sysm_context         varchar(30),
   sysm_date            date,
   sysm_last            int,
   primary key (sysm_id)
);

/*==============================================================*/
/* Table: together                                              */
/*==============================================================*/
create table together
(
   together_id          int not null auto_increment,
   together_sender      int,
   together_things      varchar(30),
   together_touch       varchar(30),
   together_place       varchar(30),
   together_ddl         datetime,
   together_time        datetime,
   together_noname      tinyint,
   primary key (together_id)
);

/*==============================================================*/
/* Table: usr                                                  */
/*==============================================================*/
create table usr
(
   usr_id               int not null auto_increment,
   usr_snum             varchar(20),
   usr_alias            varchar(15),
   usr_name             varchar(15),
   usr_gender           tinyint,
   usr_major            varchar(30),
   usr_imgaddr          varchar(50),
   usr_phone            varchar(15),
   usr_point            bigint,
   usr_attention        int,
   usr_credit           int,
   usr_blog             int,
   usr_finish           int,
   usr_accept           int,
   usr_publish          tinyint,
   primary key (usr_id)
);

