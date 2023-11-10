CREATE TABLE IF NOT EXISTS passwordRecord (
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255),
    url VARCHAR(255),
    email VARCHAR(255),
    notes TEXT
);

