create table bookings
(
    id            bigserial primary key,
    user_id       int8 references users (id),
    attraction_id int8 references attractions (id),
    booking_date  timestamp without time zone
);