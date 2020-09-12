DROP TABLE MESSAGE;
create table message (
    mid int,
    userId varchar2(50) REFERENCES member(userId),
    sender varchar2(50) REFERENCES member(userId),
    mcontent varchar2(1000),
    senddate varchar2(50) default to_char(sysdate,'yyyy.mm.dd hh24:mi'),
    readstatus varchar2(50) default '0',
    status varchar2(50) default '0',
    primary key(mid,userId),
    CONSTRAINT MESSAGE_FK_USERID FOREIGN KEY(USERID)
    REFERENCES MEMBER(USERID) ON DELETE CASCADE
    );
    DROP SEQUENCE TMP_SEQ;
    CREATE SEQUENCE tmp_seq START WITH 1 INCREMENT BY 1 NOCYCLE NOCACHE;
    