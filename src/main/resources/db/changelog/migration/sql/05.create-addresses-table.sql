create table addresses
(
    id          bigserial primary key,
    locality_id int8 references settlements (id),
    country_id  int8 references countries (id),
    street      varchar(64),
    house       varchar(64),
    apartment   int8
);