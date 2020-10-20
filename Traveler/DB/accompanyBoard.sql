DROP SEQUENCE ACC_BNO;
DROP TABLE ACCOMPANY_BOARD;

CREATE SEQUENCE ACC_BNO
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOCYCLE;

CREATE TABLE ACCOMPANY_BOARD (
    ACC_BNO NUMBER,
    PLANNO NUMBER, 
    HOSTID VARCHAR2(50),
    BOARD_TITLE VARCHAR2(200),
    TITLE VARCHAR2(200),
    PRIMARY KEY(ACC_BNO,PLANNO, HOSTID, TITLE),
    CONSTRAINT ACCBOARD_FK_USERPLAN FOREIGN KEY(PLANNO, HOSTID, TITLE)
    REFERENCES USERPLAN(PLANNO, USERID, TITLE) ON DELETE CASCADE
    );