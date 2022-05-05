SET MODE PostgreSQL;

CREATE DATABASE hero;
\c hero;
CREATE TABLE hero (id SERIAL PRIMARY KEY, name VARCHAR, age int, specialpower VARCHAR, weakness varchar, squadId int);
CREATE TABLE squads (id SERIAL PRIMARY KEY, name VARCHAR, size int, cause VARCHAR);
CREATE DATABASE hero_test WITH TEMPLATE hero;