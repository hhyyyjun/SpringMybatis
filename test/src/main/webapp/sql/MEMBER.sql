CREATE TABLE MEMBER(
	MID VARCHAR(50) PRIMARY KEY,
	MPW VARCHAR(50) NOT NULL,
	NAME VARCHAR(50) NOT NULL,
	ROLE VARCHAR(50) NOT NULL
);

INSERT INTO MEMBER VALUES('qqqq','1234','dw','ADMIN');
INSERT INTO MEMBER VALUES('qqqq','1234','dw','�Ϲ�ȸ��');
SELECT * FROM MEMBER;
SELECT * FROM BOARD;
SELECT * FROM USER_TABLES;
SELECT * FROM CMEMBER;
DROP TABLE MEMBER;