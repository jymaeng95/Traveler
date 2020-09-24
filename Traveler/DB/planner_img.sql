drop table planner_img;

create table planner_img (
 userid varchar2(50),
 planno number,
 org_filename  varchar2(300) not null,
 save_filename varchar2(300) not null, 
 full_path varchar2(1000) not null,
 file_size  number,
 save_date Date default sysdate not null,
    PRIMARY KEY(PLANNO, USERID),
    CONSTRAINT PLANNER_IMG_FK_PLANNER FOREIGN KEY(USERID,PLANNO)
    REFERENCES PLANNER(USERID,PLANNO) ON DELETE CASCADE
 );
 