drop table userimg;
drop sequence idx;

create sequence idx 
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOCYCLE;

create table userimg (
 idx number,
 userid varchar2(50),
 org_filename  varchar2(300) not null,
 save_filename varchar2(300) not null, 
 full_path varchar2(1000) not null,
 file_size  number,
 save_date Date default sysdate not null,
    PRIMARY KEY(USERID),
    CONSTRAINT USERIMG_FK_USERID FOREIGN KEY(USERID)
    REFERENCES MEMBER(USERID) ON DELETE CASCADE
 );
