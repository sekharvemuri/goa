create database goadb;
use goadb;

create table TYPES (
	ID int primary key auto_increment,
	NAME varchar(20) unique,
	DESCRIPTION varchar(50)
);

insert into TYPES (NAME) values ('Type A');
insert into TYPES (NAME) values ('Type B');

create table SUB_TYPES (
	ID int primary key auto_increment,
	TYPE_ID int references TYPES.ID,
	NAME varchar(20),
	DESCRIPTION varchar(50),
	unique (TYPE_id, NAME)
);

insert into SUB_TYPES (TYPE_ID, NAME) values ((select ID from TYPES where NAME='Type A'), 'Sub Type A.1');
insert into SUB_TYPES (TYPE_ID, NAME) values ((select ID from TYPES where NAME='Type A'), 'Sub Type A.2');
insert into SUB_TYPES (TYPE_ID, NAME) values ((select ID from TYPES where NAME='Type A'), 'Sub Type A.3');
insert into SUB_TYPES (TYPE_ID, NAME) values ((select ID from TYPES where NAME='Type B'), 'Sub Type B.1');
insert into SUB_TYPES (TYPE_ID, NAME) values ((select ID from TYPES where NAME='Type B'), 'Sub Type B.2');
insert into SUB_TYPES (TYPE_ID, NAME) values ((select ID from TYPES where NAME='Type B'), 'Sub Type B.3');

create table commodity_family (
	ID int primary key auto_increment,
	name varchar(10) unique
);

insert into commodity_family (name) values ('ALLUMINIUM');
insert into commodity_family (name) values ('COPPER');
insert into commodity_family (name) values ('LEAD');
insert into commodity_family (name) values ('NICKEL');
insert into commodity_family (name) values ('SILVER');
insert into commodity_family (name) values ('ZINC');

create table commodity (
	ID int primary key auto_increment,
	NAME varchar(10) unique,
	cmdt_family_id int references commodity_family(id),
	main_interval decimal(6,2), 
	sub_interval_1 decimal(6,2),
	sub_interval_2 decimal(6,2),
	sub_interval_3 decimal(6,2)
);

insert into commodity (NAME, cmdt_family_id) values ('ALUMINI', (select id from commodity_family where name='ALLUMINIUM'));
insert into commodity (NAME, cmdt_family_id) values ('ALUMINIUM', (select id from commodity_family where name='ALLUMINIUM'));
insert into commodity (NAME, cmdt_family_id) values ('COPPER', (select id from commodity_family where name='COPPER'));
insert into commodity (NAME, cmdt_family_id) values ('COPPERM', (select id from commodity_family where name='COPPER'));
insert into commodity (NAME, cmdt_family_id) values ('LEAD', (select id from commodity_family where name='LEAD'));
insert into commodity (NAME, cmdt_family_id) values ('LEADMINI', (select id from commodity_family where name='LEAD'));
insert into commodity (NAME, cmdt_family_id) values ('NICKEL', (select id from commodity_family where name='NICKEL'));
insert into commodity (NAME, cmdt_family_id) values ('NICKELM', (select id from commodity_family where name='NICKEL'));
insert into commodity (NAME, cmdt_family_id) values ('SILVERM', (select id from commodity_family where name='SILVER'));
insert into commodity (NAME, cmdt_family_id) values ('SILVERMIC', (select id from commodity_family where name='SILVER'));
insert into commodity (NAME, cmdt_family_id) values ('ZINC', (select id from commodity_family where name='ZINC'));
insert into commodity (NAME, cmdt_family_id) values ('ZINCMINI', (select id from commodity_family where name='ZINC'));

create table commodity_expiry_date (
	cmdt_id int references commodity.ID,
	expiry_date date,
	primary key (cmdt_id, expiry_date)
);

insert into commodity_expiry_date (cmdt_id, expiry_date) values ((select id from commodity where name=('ALUMINI')), STR_TO_DATE('2016-03-29', '%Y-%m-%d'));
insert into commodity_expiry_date (cmdt_id, expiry_date) values ((select id from commodity where name=('ALUMINIUM')), STR_TO_DATE('2016-03-30', '%Y-%m-%d'));
insert into commodity_expiry_date (cmdt_id, expiry_date) values ((select id from commodity where name=('ALUMINIUM')), STR_TO_DATE('2016-03-31', '%Y-%m-%d'));
insert into commodity_expiry_date (cmdt_id, expiry_date) values ((select id from commodity where name=('COPPER')), STR_TO_DATE('2016-04-28', '%Y-%m-%d'));
insert into commodity_expiry_date (cmdt_id, expiry_date) values ((select id from commodity where name=('COPPER')), STR_TO_DATE('2016-04-29', '%Y-%m-%d'));
insert into commodity_expiry_date (cmdt_id, expiry_date) values ((select id from commodity where name=('COPPER')), STR_TO_DATE('2016-04-30', '%Y-%m-%d'));

create table groups (
	id int primary key auto_increment,
	NAME varchar(10) unique
);

insert into groups (NAME) values ('Group A A');
insert into groups (NAME) values ('Group A');
insert into groups (NAME) values ('Group B');
insert into groups (NAME) values ('Group C');
insert into groups (NAME) values ('Group D');
insert into groups (NAME) values ('Group E');
insert into groups (NAME) values ('Group F');
insert into groups (NAME) values ('Group G');
insert into groups (NAME) values ('Group H');
insert into groups (NAME) values ('Group I');
insert into groups (NAME) values ('Group J');
insert into groups (NAME) values ('Group K');
insert into groups (NAME) values ('Group L');
insert into groups (NAME) values ('Group M');
insert into groups (NAME) values ('Group N');
insert into groups (NAME) values ('Group O');
insert into groups (NAME) values ('Group P');
insert into groups (NAME) values ('Group Q');

create table sub_type_groups (
	sub_type_id int references SUB_TYPES.id,
	group_id int references groups.id,
	primary key (sub_type_id, group_id)
);

insert into sub_type_groups (sub_type_id, group_id) values ((select sctg.id from SUB_TYPES sctg, TYPES ctg where sctg.type_id = ctg.id and ctg.NAME='Type A' and sctg.NAME='Sub Type A.1'), (select id from groups where NAME='Group A A'));
insert into sub_type_groups (sub_type_id, group_id) values ((select sctg.id from SUB_TYPES sctg, TYPES ctg where sctg.type_id = ctg.id and ctg.NAME='Type A' and sctg.NAME='Sub Type A.1'), (select id from groups where NAME='Group A'));
insert into sub_type_groups (sub_type_id, group_id) values ((select sctg.id from SUB_TYPES sctg, TYPES ctg where sctg.type_id = ctg.id and ctg.NAME='Type A' and sctg.NAME='Sub Type A.1'), (select id from groups where NAME='Group D'));
insert into sub_type_groups (sub_type_id, group_id) values ((select sctg.id from SUB_TYPES sctg, TYPES ctg where sctg.type_id = ctg.id and ctg.NAME='Type A' and sctg.NAME='Sub Type A.2'), (select id from groups where NAME='Group E'));
insert into sub_type_groups (sub_type_id, group_id) values ((select sctg.id from SUB_TYPES sctg, TYPES ctg where sctg.type_id = ctg.id and ctg.NAME='Type A' and sctg.NAME='Sub Type A.3'), (select id from groups where NAME='Group F'));
insert into sub_type_groups (sub_type_id, group_id) values ((select sctg.id from SUB_TYPES sctg, TYPES ctg where sctg.type_id = ctg.id and ctg.NAME='Type B' and sctg.NAME='Sub Type B.1'), (select id from groups where NAME='Group H'));
insert into sub_type_groups (sub_type_id, group_id) values ((select sctg.id from SUB_TYPES sctg, TYPES ctg where sctg.type_id = ctg.id and ctg.NAME='Type B' and sctg.NAME='Sub Type B.2'), (select id from groups where NAME='Group I'));
insert into sub_type_groups (sub_type_id, group_id) values ((select sctg.id from SUB_TYPES sctg, TYPES ctg where sctg.type_id = ctg.id and ctg.NAME='Type B' and sctg.NAME='Sub Type B.3'), (select id from groups where NAME='Group J'));

create table group_commodity (
	group_id int,
	cmdty_id int,
	sell_interval decimal(6,2) default 0,
	buy_interval decimal(6,2) default 0,
	primary key (group_id, cmdty_id)
);

create table candidates (
	id int primary key,
	first_name varchar(50),
	last_name varchar(20),
	email varchar(50),
	mobile varchar(10)
);

create table group_candidates (
	group_id int references groups.id,
	cand_id int references candidates.id,
	primary key (group_id, cand_id)
);

create table work_order (
	id int primary key auto_increment, 
	group_id int references GROUPS.ID,
	cmdty_id int references COMMODITY.ID,
	order_type varchar(10),
	candidate_id int,
	prev_sell_price decimal(9, 2),
	prev_sell_date timestamp,
	prev_sell_qty int,
	order_quantity int,
	order_amount decimal(9,2), 
	order_time timestamp,
	expiry_date date,
	executed_amount decimal(9,2), 
	executed_quantity int,
	executed_time timestamp null
);

create table next_work_order (
	id int primary key auto_increment, 
	group_id int,
	cmdty_id int,
	prev_sell_price decimal(9, 2),
	prev_sell_date timestamp,
	prev_sell_qty int,
	order_type varchar(10),
	candidate_id int,
	order_quantity int,
	order_amount decimal(9,2), 
	order_time timestamp,
	expiry_date timestamp
);

create table previous_work_order (
	id int primary key, 
	group_id int,
	cmdty_id int,
	prev_sell_price decimal(9, 2),
	prev_sell_date timestamp,
	prev_sell_qty int,
	order_type varchar(10),
	candidate_id int,
	order_quantity int,
	order_amount decimal(9,2), 
	order_time timestamp,
	expiry_date timestamp,
	executed_amount decimal(9,2), 
	executed_quantity int,
	executed_time timestamp null
);

create table recent_traded_sub_types (
	sub_type_id int, 
	group_id int, 
	cmdt_family_id int, 
	recent_exec_date timestamp default 0
);

