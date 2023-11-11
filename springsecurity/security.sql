CREATE DATABASE security;
USE security;
CREATE TABLE user(
    authorities INT,
    username VARCHAR(20) NOT NULL PRIMARY KEY,
    password VARCHAR(300) NOT NULL,
    accountNonExpired BOOLEAN DEFAULT TRUE,
    accountNonLocked BOOLEAN DEFAULT TRUE,
    credentialsNonExpired BOOLEAN DEFAULT TRUE,
    enabled BOOLEAN DEFAULT TRUE
);

CREATE TABLE authorities(
    authorityNo INT PRIMARY KEY AUTO_INCREMENT,
    authority VARCHAR(50)
);

INSERT INTO user (authorities, username, password) VALUES (1, 'admin', '$2a$10$oS1.3wpbnpIanIW4RoXxSOea/vGIijBMpLUBxZqurQqNjjMiJHgGa');
INSERT INTO user (authorities, username, password) VALUES (2, 'teacher', '$2a$10$oS1.3wpbnpIanIW4RoXxSOea/vGIijBMpLUBxZqurQqNjjMiJHgGa');
INSERT INTO user (authorities, username, password) VALUES (3, 'student', '$2a$10$oS1.3wpbnpIanIW4RoXxSOea/vGIijBMpLUBxZqurQqNjjMiJHgGa');

INSERT INTO authorities (authority) VALUES ('ADMIN');
INSERT INTO authorities (authority) VALUES ('TEACHER');
INSERT INTO authorities (authority) VALUES ('STUDENT');