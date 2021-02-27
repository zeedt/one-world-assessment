create table "user" (
    id varchar(255) not null primary key,
    title varchar(25) not null ,
    first_name varchar(50) not null,
    last_name varchar(50) not null ,
    email varchar(50) not null,
    mobile_number varchar(50) not null ,
    password varchar(255) not null ,
    role varchar(50) not null,
    date_registered timestamp not null,
    verified boolean not null,
    date_verified timestamp,
    date_deactivated timestamp,
    status varchar(20) not null,
    deleted boolean not null ,

    constraint Uq__user__email unique (email)
)