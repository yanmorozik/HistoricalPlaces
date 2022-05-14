create table attractions
(
    id              bigserial primary key,
    address_id      int8 references addresses (id),
    attraction_name varchar(64),
    description     varchar(1000)
);