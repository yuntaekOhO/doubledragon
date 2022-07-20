create table member(
 mem_num number not null primary key,
 id varchar2(20) unique not null,
 auth number(1) default 2 not null,
);

create table member_level(
 mem_num number not null primary key,
 level number(4) default 0 not null,
 point number default 0 not null,
 icon varchar2(150)
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
 the_hits number default 0 not null,

);
create sequence theme_seq;