create table users
(
    id            bigserial primary key,
    credential_id int8 references credentials (id),
    first_name    varchar(64),
    surname       varchar(64),
    role          varchar(20) default 'ROLE_USER',
    status        varchar(20) default 'ACTIVE'
);