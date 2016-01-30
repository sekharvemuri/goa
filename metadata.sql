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
	primary key (group_id, cmdty_id)
);

insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group A A'), (select id from commodity where NAME='ALLUMINIUM'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group A A'), (select id from commodity where NAME='COPPER'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group A A'), (select id from commodity where NAME='LEAD'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group A A'), (select id from commodity where NAME='NICKEL'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group A A'), (select id from commodity where NAME='SILVER'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group A A'), (select id from commodity where NAME='SILVERM'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group A A'), (select id from commodity where NAME='ZINC'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group A'), (select id from commodity where NAME='ALLUMINIUM'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group A'), (select id from commodity where NAME='COPPER'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group A'), (select id from commodity where NAME='LEAD'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group A'), (select id from commodity where NAME='NICKEL'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group A'), (select id from commodity where NAME='SILVER'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group A'), (select id from commodity where NAME='SILVERM'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group A'), (select id from commodity where NAME='ZINC'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group B'), (select id from commodity where NAME='ALLUMINIUM'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group B'), (select id from commodity where NAME='COPPERM'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group B'), (select id from commodity where NAME='LEAD'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group B'), (select id from commodity where NAME='NICKEL'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group B'), (select id from commodity where NAME='SILVERM'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group B'), (select id from commodity where NAME='ZINC'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group C'), (select id from commodity where NAME='ALLUMINIUM'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group C'), (select id from commodity where NAME='COPPER'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group C'), (select id from commodity where NAME='LEAD'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group C'), (select id from commodity where NAME='NICKEL'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group C'), (select id from commodity where NAME='SILVERM'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group C'), (select id from commodity where NAME='ZINC'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group D'), (select id from commodity where NAME='ALUMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group D'), (select id from commodity where NAME='COPPERM'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group D'), (select id from commodity where NAME='LEADMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group D'), (select id from commodity where NAME='NICKELM'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group D'), (select id from commodity where NAME='SILVERMIC'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group D'), (select id from commodity where NAME='ZINCMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group E'), (select id from commodity where NAME='ALUMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group E'), (select id from commodity where NAME='COPPERM'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group E'), (select id from commodity where NAME='LEADMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group E'), (select id from commodity where NAME='NICKELM'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group E'), (select id from commodity where NAME='SILVERMIC'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group E'), (select id from commodity where NAME='ZINCMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group F'), (select id from commodity where NAME='ALUMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group F'), (select id from commodity where NAME='COPPERM'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group F'), (select id from commodity where NAME='LEADMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group F'), (select id from commodity where NAME='NICKELM'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group F'), (select id from commodity where NAME='SILVERMIC'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group F'), (select id from commodity where NAME='ZINCMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group G'), (select id from commodity where NAME='LEADMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group H'), (select id from commodity where NAME='LEADMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group I'), (select id from commodity where NAME='LEADMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group J'), (select id from commodity where NAME='LEADMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group K'), (select id from commodity where NAME='LEADMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group L'), (select id from commodity where NAME='LEADMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group M'), (select id from commodity where NAME='LEADMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group N'), (select id from commodity where NAME='LEAD'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group O'), (select id from commodity where NAME='LEADMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group P'), (select id from commodity where NAME='LEADMINI'));
insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME='Group Q'), (select id from commodity where NAME='LEAD'));

create table candidates (
	id int primary key,
	first_name varchar(50),
	last_name varchar(20),
	email varchar(50),
	mobile varchar(10)
);

insert into candidates (id) values (8712);
insert into candidates (id) values (69641);
insert into candidates (id) values (69756);
insert into candidates (id) values (69757);
insert into candidates (id) values (90104);
insert into candidates (id) values (90104);
insert into candidates (id) values (90111);
insert into candidates (id) values (90119);
insert into candidates (id) values (90120);
insert into candidates (id) values (90121);
insert into candidates (id) values (90124);
insert into candidates (id) values (90125);
insert into candidates (id) values (90127);
insert into candidates (id) values (90129);
insert into candidates (id) values (90130);
insert into candidates (id) values (90131);
insert into candidates (id) values (90133);
insert into candidates (id) values (90135);
insert into candidates (id) values (90137);
insert into candidates (id) values (90138);
insert into candidates (id) values (90139);
insert into candidates (id) values (90140);
insert into candidates (id) values (90141);
insert into candidates (id) values (90142);
insert into candidates (id) values (90143);
insert into candidates (id) values (90144);
insert into candidates (id) values (90145);
insert into candidates (id) values (90146);
insert into candidates (id) values (90147);
insert into candidates (id) values (90148);
insert into candidates (id) values (90149);
insert into candidates (id) values (90152);
insert into candidates (id) values (90153);
insert into candidates (id) values (90154);
insert into candidates (id) values (90156);
insert into candidates (id) values (90157);
insert into candidates (id) values (90159);
insert into candidates (id) values (90160);
insert into candidates (id) values (90162);
insert into candidates (id) values (90163);
insert into candidates (id) values (90164);
insert into candidates (id) values (90168);
insert into candidates (id) values (90170);
insert into candidates (id) values (90171);
insert into candidates (id) values (90173);
insert into candidates (id) values (90174);
insert into candidates (id) values (90177);
insert into candidates (id) values (90178);
insert into candidates (id) values (90180);
insert into candidates (id) values (90181);
insert into candidates (id) values (90183);

create table group_candidates (
	group_id int references groups.id,
	cand_id int references candidates.id,
	primary key (group_id, cand_id)
);

insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group A A'), 69641);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group A'), 90178);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group C'), 90177);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group D'), 90125);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group D'), 90138);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group D'), 90121);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group D'), 90162);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group D'), 90129);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group D'), 8712);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group E'), 69757);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group E'), 90140);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group E'), 90142);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group E'), 90127);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group E'), 90141);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group E'), 90173);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group E'), 90183);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group E'), 90164);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group F'), 69756);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group F'), 90135);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group F'), 90120);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group F'), 90137);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group F'), 90139);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group F'), 90174);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group F'), 90160);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group F'), 90104);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group F'), 90152);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group F'), 90154);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group F'), 90181);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group H'), 90149);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group H'), 90157);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group H'), 90159);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group H'), 90147);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group H'), 90167);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group H'), 90180);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group I'), 90111);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group I'), 90153);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group I'), 90144);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group I'), 90145);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group I'), 90170);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group I'), 90124);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group I'), 90163);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group J'), 90143);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group J'), 90156);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group J'), 90130);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group J'), 90146);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group J'), 90171);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group K'), 69757);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group K'), 90140);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group K'), 90142);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group K'), 90127);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group K'), 90141);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group K'), 69756);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group K'), 90137);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group K'), 90135);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group K'), 90125);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group K'), 90138);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group K'), 90120);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group K'), 90139);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group L'), 90133);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group L'), 90119);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group L'), 90148);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group M'), 90131);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group N'), 90140);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group N'), 90142);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group N'), 90137);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group N'), 90139);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group P'), 90153);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group P'), 90111);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group P'), 90143);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group P'), 90156);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group P'), 90121);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group P'), 90104);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group P'), 90162);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group Q'), 90120);
insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME='Group Q'), 90125);

create table work_order (
	id int primary key auto_increment, 
	GROUP_ID int references GROUPS.ID,
	CMDTY_ID int references COMMODITY.ID,
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

create table next_work_order (
	id int primary key, 
	GROUP_ID int,
	CMDTY_ID int,
	order_type varchar(10),
	candidate_id int,
	order_quantity int,
	order_amount decimal(9,2), 
	order_time timestamp,
	expiry_date timestamp
);

create table previous_work_order (
	id int primary key, 
	GROUP_ID int,
	CMDTY_ID int,
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

insert into recent_executions (sub_type_id, group_id, cmdty_id, interval_amt)
(select stg.sub_type_id, gc.group_id, gc.cmdty_id, '50' as interval_amt from sub_type_groups stg, group_commodity gc where stg.group_id=gc.group_id);
