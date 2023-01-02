create table user_table (
    id varchar(32) not null primary key,
    username varchar(64) not null ,
    email varchar(64) not null,
    nickname varchar(64) null,
    gender varchar(255) null,
    locked smallint default 0 not null,
    enabled smallint default 1 not null,
    last_login_ip varchar(64) null,
    last_login_time timestamp without time zone null,
    profilePic varchar(255) default '',
    created_time timestamp without time zone not null,
    updated_time timestamp without time zone not null,
    constraint user_table_username_uk
                        unique (username),
    constraint user_table_email_uk
                        unique (email)
)