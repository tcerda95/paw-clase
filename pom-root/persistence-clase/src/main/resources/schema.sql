CREATE TABLE IF NOT EXISTS users (
    userid SERIAL PRIMARY KEY,
    username VARCHAR(256) UNIQUE,
    password VARCHAR(256)
);
