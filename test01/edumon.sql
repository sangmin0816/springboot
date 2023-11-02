GRANT ALL PRIVILEGES ON test1.* TO 'test1'@'%';

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