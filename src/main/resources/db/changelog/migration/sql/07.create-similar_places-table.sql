create table similar_places
(
    attraction_id    int8 references attractions (id),
    similar_place_id int8 references attractions (id)
);