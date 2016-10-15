DROP SCHEMA IF EXISTS amtproject;
CREATE SCHEMA amtproject;
USE amtproject;

CREATE TABLE user (
    username VARCHAR(45) UNIQUE NOT NULL,
    password VARCHAR(45) NOT NULL,
    quote VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
