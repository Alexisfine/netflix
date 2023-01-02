create table role (
    id varchar(32) not null primary key,
    name varchar(128) null,
    title varchar(128) null,
    created_time timestamp without time zone not null,
    updated_time timestamp without time zone not null
)