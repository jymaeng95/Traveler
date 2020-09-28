drop table budget;
DROP SEQUENCE BUDGET_NO;

CREATE SEQUENCE BUDGET_NO
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOCYCLE;

create table budget (
    BUDGET_NO NUMBER,
    planno number,
    userid varchar2(50),
    TITLE VARCHAR2(200),
    cat varchar2(20),
    income number,
    expend number,
    total number,
    descript varchar2(1000),
    plandate varchar2(20),
    PRIMARY KEY(BUDGET_NO,PLANNO,USERID,title),
    CONSTRAINT PLANNER_FK FOREIGN KEY(PLANNO,USERID)
    REFERENCES PLANNER(PLANNO,USERID) ON DELETE CASCADE
    );
    
 INSERT INTO BUDGET(bno,planno,userid,title,cat,income,expend,total) values (BNO.NEXTVAL, 1,'1358223290','휴애리자연생활공원','관광',1000,0,5000);    
 INSERT INTO BUDGET(bno,planno,userid,title,cat,income,expend,total) values (bno.NEXTVAL, 1,'1358223290','테스트','테스트',2000,0,5000);    
 INSERT INTO BUDGET(bno,planno,userid,title,cat,income,expend,total) values (bno.NEXTVAL, 1,'1358223290','BUDGET ONLY','관광',2000,0,5000);    
 
 --------쿼리 테스트 --------------
 
 select *
 from budget;
 
 select budget.*, userplan.planday,userplan.plandate, userplan.descript
 from budget, userplan
 where budget.planno = userplan.planno and budget.planno = 1;