create table member(
 mem_num number not null primary key,
 id varchar2(20) unique not null,
 auth number(1) default 2 not null
);

create table member_level(
 mem_num number not null primary key,
 mem_level number default 0 not null,
 mem_point number default 0 not null,
 icon varchar2(150)
);

create table member_detail (
 mem_num number not null primary key,
 nick varchar2(20) not null,
 name varchar2(15) not null,
 passwd varchar2(15) not null,
 cell varchar2(15) not null,
 email varchar2(50) not null,
 zipcode varchar2(5) not null,
 addr1 varchar2(100) not null,
 addr2 varchar2(100),
 reg_date date default sysdate not null,
 music varchar2(20),
 birthday date,
 route varchar2(150),
 photo varchar2(150)
);

create table theme_board(
 the_num number not null primary key,
 the_title varchar2(50) not null,
 the_writer varchar2(20) not null,
 the_content clob not null,
 the_date date default sysdate not null,
 the_modify_date date,
 the_img varchar2(150),
 the_code number not null,
 the_video varchar2(150),
 the_url varchar2(150),
 the_genre varchar2(10),
 the_recommend number default 0 not null,
 the_hits number default 0 not null
);

create table free_board(
 free_num number not null primary key,
 free_title varchar2(50) not null,
 free_writer varchar2(20) not null,
 free_content clob not null,
 free_date date default sysdate not null,
 free_modify_date date,
 free_img varchar2(150),
 free_recommend number default 0 not null,
 free_hits number default 0 not null,
 free_code number not null
);


create table inquiry_board(
 inq_num number not null primary key,
 inq_title varchar2(50) not null,
 inq_writer varchar2(20) not null,
 inq_question clob not null, 
 inq_answer clob not null,
 inq_date date default sysdate not null,
 inq_modify_date date,
 inq_img varchar2(150)
);

create table notice_board(
 not_num number not null primary key,
 not_title varchar2(50) not null,
 not_writer varchar2(20) not null,
 not_content clob not null,
 not_date date default sysdate not null,
 not_modify_date date,
 not_img varchar2(150),
 not_hits number default 0
);

create table theme_comment(
treply_num number not null primary key,
the_num number not null,
treply_writer varchar2(20) not null,
treply_content clob not null,
treply_date date default sysdate not null,
trepla_modify_date date,
constraint theme_comment_fk foreign key (the_num) 
                             references theme_board (the_num)
);

create table free_comment(
 frely_num number not null primary key,
 free_num number not null,
 frely_writer varchar2(20) not null,
 frely_content clob not null,
 frely_date date default sysdate not null,
 frely_modify_date date,
 constraint free_comment_fk foreign key (free_num) references free_board (free_num)
);

create table music (
 mus_num number primary key,
 the_num number not null,
 mus_img varchar2(150),
 mus_album varchar2(20),
 mus_title varchar2(50) not null,
 mus_genre varchar2(10),
 mus_singer varchar2(20) not null,
 mus_date date,
 mus_composer varchar2(20),
 mus_songwriter varchar2(20),
 mus_recommend number default 0 not null,
 mus_hits number default 0 not null,
 constraint music_fk foreign key (the_num) references theme_board (the_num)
);

create table playlist(
 pl_num number not null primary key,
 mem_num number not null,
 mus_num number not null,
 constraint playlist_fk foreign key(mem_num) references member (mem_num),
 constraint playlist_fk2 foreign key(mus_num) references music (mus_num)
);

create sequence member_seq;
create sequence board_seq;
create sequence board_comment;
create sequence notice_seq;
create sequence music_seq;
create sequence playlist_seq;
