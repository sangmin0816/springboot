CREATE DATABASE tmall;
USE tmall;

-- user 회원
CREATE TABLE user(
	id VARCHAR(20) PRIMARY KEY,                             -- (아이디)
	pw VARCHAR(300) NOT NULL,                               -- (비밀번호)
	name VARCHAR(100) NOT NULL,                             -- (이름)
	email VARCHAR(320),                                     -- (이메일)
	tel VARCHAR(13),                                        -- (전화번호)
	addr1 VARCHAR(100),                                     -- (주소)
	addr2 VARCHAR(100),                                     -- (상세 주소)
	postcode VARCHAR(10),                                   -- (우편번호)
	regdate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,   -- (가입일)
	birth DATE,                                             -- (생년월일)
	point INT DEFAULT 0,                                    -- (포인트)
	imageFile INT DEFAULT 0,                                -- (프로필 이미지) storage 테이블 번호
	level varchar(30) default 'student',                    -- (회원 유형) admin 관리자, teacher 선생님, student 학생
	active BOOLEAN DEFAULT TRUE,                            -- (활동 여부)
	verify BOOLEAN DEFAULT FALSE                            -- (인증 여부)
);

INSERT INTO user(id, pw, name, level, verify) VALUES('admin', '$2a$10$oS1.3wpbnpIanIW4RoXxSOea/vGIijBMpLUBxZqurQqNjjMiJHgGa', '관리자', 'admin', TRUE);

INSERT INTO user(id, pw, name, level, verify) VALUES('sam1', '$2a$10$oS1.3wpbnpIanIW4RoXxSOea/vGIijBMpLUBxZqurQqNjjMiJHgGa', '쌤1', 'teacher', TRUE);
INSERT INTO user(id, pw, name, level, verify) VALUES('sam2', '$2a$10$oS1.3wpbnpIanIW4RoXxSOea/vGIijBMpLUBxZqurQqNjjMiJHgGa', '쌤2', 'teacher', TRUE);
INSERT INTO user(id, pw, name, level, verify) VALUES('sam3', '$2a$10$oS1.3wpbnpIanIW4RoXxSOea/vGIijBMpLUBxZqurQqNjjMiJHgGa', '쌤3', 'teacher', TRUE);

INSERT INTO user(id, pw, name, level, verify) VALUES('kim1', '$2a$10$oS1.3wpbnpIanIW4RoXxSOea/vGIijBMpLUBxZqurQqNjjMiJHgGa', '학생1', 'student', TRUE);
INSERT INTO user(id, pw, name, level, verify) VALUES('kim2', '$2a$10$oS1.3wpbnpIanIW4RoXxSOea/vGIijBMpLUBxZqurQqNjjMiJHgGa', '학생2', 'student', TRUE);
INSERT INTO user(id, pw, name, level, verify) VALUES('kim3', '$2a$10$oS1.3wpbnpIanIW4RoXxSOea/vGIijBMpLUBxZqurQqNjjMiJHgGa', '학생3', 'student', TRUE);


-- storage 파일 저장소
CREATE TABLE storage(
    fileNo INT PRIMARY KEY AUTO_INCREMENT,                  -- (파일 번호)
    originName VARCHAR(300),                                -- (파일 원본 이름) 업로드 시 이름
    saveName VARCHAR(300),                                  -- (저장된 이름)
    savePath VARCHAR(1000),                                 -- (저장 경로)
    boardName VARCHAR(50),                                  -- (관련 게시판)
    boardNo INT,                                            -- (관련 게시판의 게시글) 자료실과 같이 여러 파일을 업로드하는 경우 지정
    regdate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP    -- (파일 등록일)
);


-- 결제
CREATE TABLE payment(
    payNo INT PRIMARY KEY AUTO_INCREMENT,           -- (결제 번호)
    id VARCHAR(20) NOT NULL,                        -- (결제 아이디)
    method VARCHAR(10),                             -- (결제 수단)
    pcom VARCHAR(100),                              -- (결제 회사)
    paccount VARCHAR(100),                          -- (결제 계좌)
    price INT DEFAULT 0,                            -- (결제 금액)
    amount INT DEFAULT 1,                           -- (결제 수량)
    status INT DEFAULT 0,                           -- (결제 상태)
    regdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP     -- (결제일)
);