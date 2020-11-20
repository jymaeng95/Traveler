drop table message;
drop SEQUENCE tmp_seq;


---------------------보낸메세지
drop table msg_send;
create table msg_send (
    mid_send int,
    targetId_send varchar2(50),
    sender_send varchar2(50),
    mcontent_send varchar2(1000),
    senddate_send varchar2(50) default to_char(sysdate,'yyyy.mm.dd hh24:mi'),
    readstatus_send varchar2(50) default '0',
    status_send varchar2(50) default '0',
    bno_send varchar2(50) default '0',
    primary key(mid_send),
    CONSTRAINT msg_fk1 foreign key(targetId_send)
    REFERENCES MEMBER(USERID) ON DELETE CASCADE
    );
    
drop table msg_rcv;
create table msg_rcv (
    mid_rcv int,
    targetId_rcv varchar2(50),
    sender_rcv varchar2(50),
    mcontent_rcv varchar2(1000),
    senddate_rcv varchar2(50) default to_char(sysdate,'yyyy.mm.dd hh24:mi'),
    readstatus_rcv varchar2(50) default '0',
    status_rcv varchar2(50) default '0',
    bno_rcv varchar2(50) default '0', -- 0?씪?떆 ?룞?뻾?떊泥??씠 ?븘?떂--
    primary key(mid_rcv),
    CONSTRAINT msg_fk2 foreign key(targetId_rcv)
    REFERENCES MEMBER(USERID) ON DELETE CASCADE
    );
---------------------받은 메세지
 
CREATE SEQUENCE mid_send START WITH 1 INCREMENT BY 1 NOCYCLE NOCACHE;
CREATE SEQUENCE mid_rcv START WITH 1 INCREMENT BY 1 NOCYCLE NOCACHE;

