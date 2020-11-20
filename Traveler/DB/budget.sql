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
    planTotalDate varchar2(30),
    is_public char(1) default 'N',
    reg_date VARCHAR2(30) default to_char(sysdate,'yyyy-mm-dd'),
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
 
 select planno, reg_date
from budget
 where is_public = 'Y'
 group by planno, reg_date
 order by planno asc;
 
 select planno, total ,sum(income), sum(expend)
 from budget
 where is_public='Y'
 group by planno, total
 order by planno asc;
 
select cat, count(cat)
from budget
where is_public ='Y' 
group by cat
order by cat;
 
select min(x),max(x), avg(x), min(y), max(y), avg(y), min(z), max(z), avg(z)
from (
    select planno, total as z,  sum(expend) as x, sum(income) as y
    from budget
    where is_public='Y'
    group by planno, total
    order by planno, total asc
    );

select count(x) 
from (
select planno as x
from budget 
where is_public='Y' and planTotaldate='1'
group by planno
);

select distinct(is_public)
from budget
where planno = '22';

select * 
from budget
where is_public='Y' and planno = '1';

select planno
from budget 
where is_public='Y' and planTotalDate='1';

select distinct(planno), reg_date, total
from budget
where total = (
    select max(total) from budget
    where is_public='Y' and planTotalDate='1' 
    ) and rownum = 1
    order by reg_date, planno desc;
    
    	select planno
		from budget 
		where plantotaldate = '1' and is_public='Y'
		group by planno
		order by planno;

	SELECT * 
		FROM (
			SELECT ROWNUM RN, A.* 
				FROM (
						SELECT planno, reg_date
						FROM  BUDGET
                        WHERE BUDGET.IS_PUBLIC='Y' and planTotalDate ='3'
						GROUP BY PLANNO, reg_date
                        ORDER BY planno ASC 
						) A
				)
	WHERE RN BETWEEN 1 AND 10;   
        
