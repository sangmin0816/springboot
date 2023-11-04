CREATE DATABASE tsellpa;

USE tsellpa;

CREATE TABLE board(
    boardNo INT PRIMARY KEY AUTO_INCREMENT,                                             -- 게시글 번호
    boardType VARCHAR(20) NOT NULL,                                                     -- free 자유게시판 / qna 문의게시판 / upload 자료게시판
    title VARCHAR(200) NOT NULL,                                                        -- 게시글 제목
    content VARCHAR(2000),                                                              -- 게시글 내용
    author VARCHAR(20),                                                                 -- 작성자 ID
    createAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,                              -- 작성일시
    updateAT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 수정일시
    hasFile BOOLEAN DEFAULT FALSE,                                                      -- 파일의 존재 여부
    hasResponse BOOLEAN DEFAULT FALSE,                                                   -- 게시글 댓글 존재 여부
    authority VARCHAR(300) DEFAULT 'ALL',                                               -- 게시글 열람 권한
    visited INT DEFAULT 0                                                               -- 게시글 조회수
);

CREATE TABLE fileData(
    fileNo INT PRIMARY KEY AUTO_INCREMENT,
    boardNo INT,
    originName VARCHAR(255),
    saveName VARCHAR(255),
    savePath VARCHAR(255),
    fileType VARCHAR(100)
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