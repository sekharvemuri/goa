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

create table commodity (
	ID int primary key auto_increment,
	NAME varchar(10) unique
);

insert into commodity (NAME) values ('ALLUMINIUM');
insert into commodity (NAME) values ('ALUMINI');
insert into commodity (NAME) values ('COPPER');
insert into commodity (NAME) values ('COPPERM');
insert into commodity (NAME) values ('LEAD');
insert into commodity (NAME) values ('LEADMINI');
insert into commodity (NAME) values ('NICKEL');
insert into commodity (NAME) values ('NICKELM');
insert into commodity (NAME) values ('SILVER');
insert into commodity (NAME) values ('SILVERM');
insert into commodity (NAME) values ('SILVERMIC');
insert into commodity (NAME) values ('ZINC');
insert into commodity (NAME) values ('ZINCMINI');

create table sub_type_commodities (
	sub_type_id int,
	cmdty_id int,
	max_interval decimal(6,2),
	sub_interval_1 decimal(6,2),
	sub_interval_2 decimal(6,2),
	sub_interval_3 decimal(6,2),
	primary key (sub_type_id, cmdty_id)
);

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
	sell_interval decimal(6,2),
	buy_interval decimal(6,2),
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

create table recent_executions (
	sub_type_id int, 
	group_id int, 
	cmdty_id int, 
	interval_amt decimal(5,2), 
	recent_exec_date timestamp default 0
);

