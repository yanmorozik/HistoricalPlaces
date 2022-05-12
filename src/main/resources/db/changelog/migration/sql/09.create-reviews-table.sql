create table reviews
(
    id              bigserial primary key,
    user_id         int8 references users (id),
    attraction_id   int8 references attractions (id),
    review          varchar(255),
    grade           int
);