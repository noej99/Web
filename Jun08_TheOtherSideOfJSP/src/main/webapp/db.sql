create table jun08_coffee(
	jc_name varchar2(10 char) primary key,
	jc_price number(5) not null
);

insert into jun08_coffee values('아메리카노', 3000);
insert into jun08_coffee values('아이스아메리카노', 3500);
insert into jun08_coffee values('사이즈업', 1000);
insert into jun08_coffee values('카페라떼', 4000);
insert into jun08_coffee values('아이스카페라떼', 4500);

select * from jun08_coffee


create table jun08_snack(
	js_name varchar2(10 char) primary key,
	js_price number(5) not null,
	js_brand varchar2(10 char) not null
);

select * from jun08_snack

insert into jun08_snack values('새우깡', 1600, '농심');
insert into jun08_snack values('감자깡', 1500, '농심');
insert into jun08_snack values('고구마깡', 1500, '농심');
insert into jun08_snack values('새우깡', 1600, '농심');
insert into jun08_snack values('새우깡', 1600, '농심');
insert into jun08_snack values('새우깡', 1600, '농심');