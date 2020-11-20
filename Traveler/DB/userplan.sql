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
    PLANDATE VARCHAR2(20),
    PLANDAY VARCHAR2(30),
    PLANTOTALDATE VARCHAR2(30),
    STARTDATE VARCHAR2(20),
    ENDDATE VARCHAR2(20),
    DESCRIPT VARCHAR2(1000),
    OVERVIEW VARCHAR2(4000),
    IS_INSERTAFTER VARCHAR(1) DEFAULT 'N',
    ISACC VARCHAR(1) DEFAULT 'N',
    PRIMARY KEY(PLANNO,USERID, TITLE),
    CONSTRAINT USERPLAN_FK_USERID FOREIGN KEY(PLANNO,USERID)
    REFERENCES PLANNER(PLANNO,USERID) ON DELETE CASCADE
    );

INSERT INTO USERPLAN (PLANNO, USERID, TITLE, CONTENTID, CONTENTTYPEID, ADDR,IMG_SRC,
MAPX, MAPY, PLANTITLE, PLANDATE, PLANDAY, PLANTOTALDATE)
VALUES (1,'jymaeng95', '휴애리자연생활공원', '322836','12','제주특별자치도 서귀포시 남원읍 신례동로 256', 
'http://tong.visitkorea.or.kr/cms/resource/47/2615547_image2_1.bmp', '33.3085171454','126.6344317363',
'제주여행', '2020-08-20', '1', '3');

INSERT INTO USERPLAN (PLANNO, USERID, TITLE, CONTENTID, CONTENTTYPEID, ADDR,IMG_SRC,
MAPX, MAPY, PLANDATE,PLANDAY, PLANTOTALDATE)
VALUES (1,'423412314', '성산일출봉', '123456','13','제주특별자치도 서귀포시 남원읍 신례동로 256', 
'http://tong.visitkorea.or.kr/cms/resource/47/2615547_image2_1.bmp', '33.3085171454','126.6344317363',
TO_CHAR(SYSDATE,'HH24-mi'), '2', '3');

-----쿼리 테스트-----------

select to_char(plandate, 'YYYY-MM-DD')
from userplan;

SELECT * FROM USERPLAN;

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

update userplan 
set plandate=to_date('2020-08-21','YY-MM-DD');
delete userplan;
select * from userplan where userid='jymaeng95' and planNo=1;

select planno, plantitle, userid 
from userplan
where userid='jymaeng95'
group by planno,plantitle,userid;

select * from userplan where planno = 1 and userid = '1358223290';

select max(distinct planno)+1
from userplan;

select count(distinct planno) +1
from userplan;

select nvl(max(planno),0)+1
from userplan;


select title, planDate, planTotalDate
from userplan   
where planno=1 and userid='1358223290' and rownum = 1
order by planDate;

select  distinct planDate, planTotalDate, planday, rownum
from userplan 
where planno = 161 
order by planday;

select planDate, planTotalDate, planDay 
from (select distinct planDate as planDate, planTotalDate as planTotalDate, planday as planDay
        from userplan 
        where planno = 161
        order by planday
        )
        where rownum = 1;

select planno
from userplan 
where userid='1358223290' and planDate > sysDate
group by planno;

select *
from userplan 
where planno=83  and planDate > sysDate
order by planDate asc;

select planno 
from userplan 
where planTotalDate = '3' 
group by planno;

select count(isacc) 
from userplan 
where planNo = 163 and isacc='N';

