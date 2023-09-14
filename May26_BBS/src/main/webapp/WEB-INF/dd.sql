create table may26_bbs(
	b_no number(3) primary key,
	b_title varchar2(100 char) not null,
	b_txt varchar2(500 char) not null,
	b_date date not null
);

create sequence may26_bbs_seq;

select *
from MAY26_BBS
order by b_date;

insert into MAY26_BBS values(may26_bbs_seq.nextval, '테스트', '테스트1234', sysdate);

select *
from (
	select rownum as rn, b_no, b_title, b_date 
	from (
		select b_no, b_title, b_date 
		from may26_bbs
		order by b_date desc
	) 
)
where rn >= 11 and rn <= 20;

delete from may26_bbs
where b_no = 25;

update may26_bbs
set b_title = ?, b_txt = ?
where b_no = ?;

-- 파일은 결국 웹서버에 있어야 사용자가 다운받을 수 있고
-- DB서버 -> 웹서버 -> 사용자???
-- DB에 파일 - x
-- DB서버에는 파일명만, 파일은 웹서버에
create table may30_dataroom(
	d_no number(3) primary key,
	d_title varchar2(100 char) not null,
	d_file varchar2(200 char) not null,
	d_date date not null
);
create sequence may30_dataroom_seq;

select *
from(
	select rownum as rn, d_no, d_title, d_date
	from(
		select d_no, d_title, d_date
		from may30_dataroom
		order by d_date desc
	)
)