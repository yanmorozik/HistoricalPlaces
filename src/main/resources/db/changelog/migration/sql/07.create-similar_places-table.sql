create table similar_places
(
    id            bigserial primary key,
    attraction_id int8 references attractions (id)
);