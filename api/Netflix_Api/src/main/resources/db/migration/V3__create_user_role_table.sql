create table user_role (
    user_id varchar(32) not null,
    role_id varchar(32) not null,
    constraint c_user_id foreign key (user_id) references user_table (id),
    constraint c_role_id foreign key (role_id) references role (id)
)