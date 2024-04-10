CREATE TABLE benchmarks (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE countries (
    id SERIAL PRIMARY KEY,
    country VARCHAR(255) NOT NULL,
    region VARCHAR(255)
);

CREATE TABLE cases (
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    total VARCHAR(255) NOT NULL,
    new VARCHAR(255) NOT NULL,
    country_id INTEGER REFERENCES countries(id)
);

CREATE TABLE deaths (
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    total VARCHAR(255) NOT NULL,
    new VARCHAR(255) NOT NULL,
    country_id INTEGER REFERENCES countries(id)
);