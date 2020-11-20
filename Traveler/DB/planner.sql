DROP TABLE PLANNER;
DROP SEQUENCE PLANNO;
CREATE SEQUENCE PLANNO
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOCYCLE;

CREATE TABLE PLANNER (
    PLANNO NUMBER,
    USERID VARCHAR2(50),
    PLANTITLE VARCHAR2(200),
    INFO VARCHAR2(1000),
    PLANIMG VARCHAR2(300) default  'https://www.dorinpark.co.uk/wp-content/uploads/2016/03/no_img.jpg',
    PRIMARY KEY(PLANNO,USERID),
    CONSTRAINT PLAN_FK_USERID FOREIGN KEY(USERID)
    REFERENCES MEMBER(USERID) ON DELETE CASCADE
    );
    
INSERT INTO PLANNER(PLANNO,USERID,PLANTITLE,INFO)
VALUES (PLANNO.NEXTVAL,'423412314','제주여행','테스트');

----------쿼리 테스트 -------------

select planno.currval from dual;
select planno.nextval from dual;
select last_number from user_sequences where sequence_name = 'planno';
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'PLANNER';

select planno from planner;

select planner.planno, planner.userid, userplan.plandate, userplan.planday
from planner, userplan 
where planner.planno = userplan.planno and planner.userid = userplan.userid and planner.planno = 1;

	SELECT * 
		FROM (
			SELECT ROWNUM RN, A.* 
				FROM (
						SELECT PLANNER.PLANNO, PLANNER.PLANTITLE, PLANNER.PLANIMG, PLANNER.INFO, PLANNER.USERID
						FROM planner, BUDGET
                        WHERE BUDGET.PLANNO = PLANNER.PLANNO AND BUDGET.IS_PUBLIC='Y'
						GROUP BY PLANNER.PLANNO
                        ORDER BY PLANNER.planno DESC 
						) A
				)
	WHERE RN BETWEEN 5 and 12;
    
    
    
	SELECT * 
		FROM (
			SELECT ROWNUM RN, A.* 
				FROM (
						SELECT planno, reg_date
						FROM  BUDGET
                        WHERE BUDGET.IS_PUBLIC='Y'
						GROUP BY PLANNO, reg_date
                        ORDER BY planno ASC 
						) A
				)
	WHERE RN BETWEEN 1 AND 10;
    
	select *
   		from
   			(
   			select rownum rn, planno
   			from budget 
   			where is_public='Y' and
            rownum <= 1 * 10
        
   			)
   		where rn > 0 * 10
      group by planno 
   			
   		order by planno desc;
        
select *
from planner 
where userid='aa';

select *
from planner 
where planno=82;


