CREATE DATABASE tsellpa;

USE tsellpa;

CREATE TABLE board(
    boardNo INT PRIMARY KEY AUTO_INCREMENT,                                             -- 게시글 번호
    boardType VARCHAR(20) NOT NULL,                                                     -- free 자유게시판 / qna 문의게시판 / upload 자료게시판
    title VARCHAR(200) NOT NULL,                                                        -- 게시글 제목
    content VARCHAR(2000),                                                              -- 게시글 내용
    author VARCHAR(20),                                                                 -- 작성자 ID
    createAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,                              -- 작성일시
    updateAT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- 수정일시
    hasFile BOOLEAN DEFAULT FALSE,                                                      -- 파일의 존재 여부
    hasResponse BOOLEAN DEFAULT FALSE,                                                   -- 게시글 댓글 존재 여부
    authority VARCHAR(300) DEFAULT 'ALL',                                               -- 게시글 열람 권한
    visited INT DEFAULT 0                                                               -- 게시글 조회수
);

CREATE TABLE fileData(
    fileNo INT PRIMARY KEY AUTO_INCREMENT,
    tableName VARCHAR(100),
    boardNo INT,
    originName VARCHAR(255),
    saveName VARCHAR(255),
    savePath VARCHAR(255),
    fileType VARCHAR(100),
    authority VARCHAR(300) DEFAULT 'ALL',                                               -- 게시글 열람 권한
);

CREATE TABLE response(
    responseNo INT PRIMARY KEY AUTO_INCREMENT,   					                    -- 답글 번호
	author VARCHAR(20) NOT NULL,   									                    -- 답글 작성자
	content VARCHAR(2000) NOT NULL,   								                    -- 답글 내용
	createAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,   	                        -- 작성 일시
    updateAT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 수정 일시
    authority VARCHAR(300) DEFAULT 'ALL',                                               -- 열람 권한
	boardNo INT NOT NULL, 											                    -- 해당 게시글 번호
	root INT,                                                                           -- 답글의 원본
	depth INT                                                                           -- 답글 깊이
);

CREATE TABLE product(
    productNo INT PRIMARY KEY AUTO_INCREMENT,
    tableName VARCHAR(100) NOT NULL,
    categoryNo INT,
    title VARCHAR(200) NOT NULL,
    content VARCHAR(2000) NOT NULL,
    imageFile INT,              -- 썸네일 이미지
    price INT NOT NULL,
    createAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,                              -- 작성일시
    updateAT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- 수정일시
    baseAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,    -- 정렬 기준 시간
    isFree BOOLEAN DEFAULT FALSE,       -- 무료 여부
    status VARCHAR(50) DEFAULT 'sale'   -- 판매중(sale), 예약(reserved), 완료(sold)
);

CREATE TABLE LIKES(
    likeNo INT PRIMARY KEY AUTO_INCREMENT,
    tableName VARCHAR(100) NOT NULL,
    tableNo INT NOT NULL,
    id VARCHAR(20) NOT NULL
);

CREATE TABLE used(
    usedNo INT PRIMARY KEY AUTO_INCREMENT,
    productNo INT UNIQUE KEY,
    userId VARCHAR(20) NOT NULL,
    isTpay BOOLEAN DEFAULT FALSE,       -- 안전 거래 여부
    addr1 VARCHAR(100),     -- 거래장소 주소
    addr2 VARCHAR(200),     -- 거래장소 상세 주소
    isDiscount BOOLEAN DEFAULT FALSE,       -- 가격 제안 가능 여부
    visited INT DEFAULT 0                   -- 게시글 조회수
);

CREATE TABLE usedProduct(
    usedNo INT PRIMARY KEY AUTO_INCREMENT,
    categoryNo INT,                                             -- 카테고리 번호
    userId VARCHAR(20) NOT NULL,                                -- 판매자 ID
    title VARCHAR(200) NOT NULL,                                -- 이름
    content VARCHAR(2000) NOT NULL,                             -- 설명
    price INT NOT NULL,                                         -- 가격
    free BOOLEAN NOT NULL DEFAULT FALSE,                               -- 무료 여부
    tpay BOOLEAN NOT NULL DEFAULT FALSE,                               -- 안전 결제 여부
    discount BOOLEAN NOT NULL DEFAULT FALSE,                           -- 가격 제안 가능 여부
    addr1 VARCHAR(100),                                         -- 거래장소 주소
    addr2 VARCHAR(200),                                         -- 거래장소 상세 주소
    createAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,      -- 작성일시
    updateAT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,      -- 수정일시
    baseAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,        -- 정렬 기준 시간
    status VARCHAR(50) DEFAULT 'sale',                          -- 판매중(sale), 예약(reserved), 완료(sold)
    visited INT DEFAULT 0                                       -- 게시글 조회수
);

use tsellpa;
CREATE TABLE chatRoom (
    roomNo INT PRIMARY KEY AUTO_INCREMENT,
    userId VARCHAR(20) NOT NULL,
    usedNo INT NOT NULL,
    status VARCHAR(50) DEFAULT 'ON',
    -- userId와 usedNo를 묶어서 UNIQUE 제약 설정
    UNIQUE (userId, usedNo)
);

CREATE TABLE chatMessage(
    chatNo INT PRIMARY KEY AUTO_INCREMENT,      -- 채팅 번호
    type VARCHAR(20) NOT NULL,                  -- 채팅 타입: ENTER, TALK, LEAVE, NOTICE
    roomNo INT NOT NULL,                        -- 채팅방 번호
    sender VARCHAR(20) NOT NULL,                -- 송신자
    message VARCHAR(2000) NOT NULL,             -- 채팅 메시지
    status VARCHAR(50) DEFAULT 'UNREAD',        -- 읽음 여부
    time TIMESTAMP DEFAULT CURRENT_TIMESTAMP    -- 채팅 발송 시간
);



DELIMITER //
CREATE TRIGGER update_content_trigger BEFORE UPDATE ON board
    FOR EACH ROW
BEGIN
    IF NEW.content != OLD.content OR NEW.title != OLD.title THEN
        SET NEW.updateAt = CURRENT_TIMESTAMP();
    END IF;
END;
//
DELIMITER ;


/*
DELIMITER //
CREATE TRIGGER update_product_trigger BEFORE UPDATE ON product
    FOR EACH ROW
BEGIN
    IF NEW.content != OLD.content OR NEW.title != OLD.title THEN
        SET NEW.updateAt = CURRENT_TIMESTAMP();
    END IF;
END;
//
DELIMITER ;*/


-- 강사님 테이블
CREATE TABLE book(
  seq BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  author VARCHAR(255) NOT NULL,
  publisher VARCHAR(255) NOT NULL,
  publishAt DATE
);

CREATE TABLE user(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userId VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    pw VARCHAR(300) NOT NULL,
    tel VARCHAR(20),
    email VARCHAR(200),
    crateAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    UNIQUE KEY userIdEmail(userId, email)
);