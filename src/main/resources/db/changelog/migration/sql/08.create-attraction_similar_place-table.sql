create table attraction_similar_place
(
    attraction_id int8 references attractions (id),
    similar_place_id int8 references similar_places (id)
);