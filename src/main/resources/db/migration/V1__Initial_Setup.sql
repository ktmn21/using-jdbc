CREATE TABLE customer(
    id BIGSERIAL primary key,
    name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    age INT
)