drop table member;

create table member (
    userId varchar2(50) primary key,
    userPw varchar2(50),
    email varchar2(500),
    nickname varchar2(100),
    is_kakao char(1) default 'N',
    user_img varchar2(500) default 'https://www.dorinpark.co.uk/wp-content/uploads/2016/03/no_img.jpg'
    );
delete member;
insert into member (userid, userpw, email, nickname, is_kakao, user_img)
values ('jymaeng95', '1234', '¸ÍÁØ', 'abcd@abcd.com', 'N','https://www.dorinpark.co.uk/wp-content/uploads/2016/03/no_img.jpg');

insert into member (userid, nickname, is_kakao)
values ('423412314', 'Ä«Ä«¿À', 'Y');