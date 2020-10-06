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
    is_public char(1) default 'N',
    reg_date VARCHAR2(30) default to_char(sysdate,'yyyy-mm-dd'),
    PRIMARY KEY(BUDGET_NO,PLANNO,USERID,title),
    CONSTRAINT PLANNER_FK FOREIGN KEY(PLANNO,USERID)
    REFERENCES PLANNER(PLANNO,USERID) ON DELETE CASCADE
    );
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

    