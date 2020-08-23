DROP TABLE USERPLAN;

CREATE TABLE USERPLAN (
    PLANNO NUMBER,
    USERID VARCHAR2(50),
    TITLE VARCHAR2(200),
    CONTENTID VARCHAR2(10),
    CONTENTTYPEID VARCHAR2(5),
    ADDR VARCHAR2(200),
    IMG_SRC VARCHAR2(300),
    MAPX VARCHAR2(20),
    MAPY VARCHAR2(20),
    PLANTITLE VARCHAR2(200),
    PLANDATE DATE, 
    PLANDAY VARCHAR2(30),
    PLANTOTALDATE VARCHAR2(30),
    PRIMARY KEY(PLANNO,USERID, TITLE),
    CONSTRAINT USERPLAN_FK_USERID FOREIGN KEY(USERID)
    REFERENCES MEMBER(USERID) ON DELETE CASCADE
    );

INSERT INTO USERPLAN (PLANNO, USERID, TITLE, CONTENTID, CONTENTTYPEID, ADDR,IMG_SRC,
MAPX, MAPY, PLANTITLE, PLANDATE,PLANDAY, PLANTOTALDATE)
VALUES (1,'jymaeng95', '휴애리자연생활공원', '322836','12','제주특별자치도 서귀포시 남원읍 신례동로 256', 
'http://tong.visitkorea.or.kr/cms/resource/47/2615547_image2_1.bmp', '33.3085171454','126.6344317363',
'제주여행', '2020-08-19', '1', '3');

INSERT INTO USERPLAN (PLANNO, USERID, TITLE, CONTENTID, CONTENTTYPEID, ADDR,IMG_SRC,
MAPX, MAPY, PLANTITLE, PLANDATE,PLANDAY, PLANTOTALDATE)
VALUES (1,'jymaeng95', '성산일출봉', '123456','13','제주특별자치도 서귀포시 남원읍 신례동로 256', 
'http://tong.visitkorea.or.kr/cms/resource/47/2615547_image2_1.bmp', '33.3085171454','126.6344317363',
'제주여행', '2020-08-20', '2', '3');

MERGE INTO  userplan 
USING dual
  ON (planno=1 AND userid='jymaeng95' AND title='휴애리자연생활공원')
WHEN MATCHED THEN
 UPDATE SET plantitle='테스트여행1'
WHEN NOT MATCHED THEN
  INSERT (planno, userid, title) VALUES (1, 'jymaeng95', '테스트케이스2');


MERGE INTO  userplan 
USING dual
  ON (planno=1 AND userid='jymaeng95' AND title='휴애리자연생활공원')
WHEN MATCHED THEN
 UPDATE SET plantitle='테스트여행324' , planday='2'
WHEN NOT MATCHED THEN
  INSERT (planno, userid, title) VALUES (1, 'jymaeng95', '테스트케이스2');
  
SELECT COUNT(DISTINCT PLANNO) FROM USERPLAN;
INSERT INTO USERPLAN (PLANNO, USERID,TITLE) 
VALUES ((SELECT COUNT(DISTINCT PLANNO)+1 FROM USERPLAN) , 'jymaeng95','휴애리자연생활공원');

select * from userplan where userid='jymaeng95' and planNo=1;

select planno, plantitle, userid 
from userplan
where userid='jymaeng95'
group by planno,plantitle,userid;