
SELECT CURRENT_TIMESTAMP();

CREATE DATABASE edumon;

USE edumon;

CREATE TABLE test1(
                      no INT PRIMARY KEY AUTO_INCREMENT,
                      id VARCHAR(20),
                      com varchar(2000)
);


INSERT INTO test1(id, com) VALUES('kim1', 'kim1');
INSERT INTO test1(id, com) VALUES('kim2', 'kim2');

CREATE TABLE user(
    no INT PRIMARY KEY AUTO_INCREMENT,
    id VARCHAR(20) UNIQUE KEY NOT NULL,
    pw VARCHAR(300) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    tel VARCHAR(20),
    regdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    point INT DEFAULT 0,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    type VARCHAR(50) NOT NULL DEFAULT 'student'
);