-- Getting Adhoc Group-IDs
select g.id as groupId, g.name as groupName from groups g where g.id not in (select group_id from sub_type_groups);

-- Getting Group-IDs by sub-types
select 	g.id as groupId, g.name as groupName, 
		c.id as commodityId, c.name as commodityName, 
		stg.sub_type_id as subTypeId,
		wo.order_type as orderTyp, wo.order_quantity as orderQuantity, wo.order_amount as orderAmount 
from groups g, commodity c, work_order wo, sub_type_groups stg 
where g.id=wo.group_id and c.id=wo.cmdty_id and g.id=stg.group_id;

select 	g.id as groupId, g.name as groupName, 
		c.id as commodityId, c.name as commodityName, 
		wo.order_type as orderTyp, wo.order_quantity as orderQuantity, wo.order_amount as orderAmount 
from groups g, commodity c, work_order wo 
where g.id=wo.group_id and c.id=wo.cmdty_id;


select sell.* from (
select s.group_name as groupName, s.symbol_name as commodityName, avg(s.order_amount) as orderAmt, s.order_quantity as orderQty, s.order_time as orderTime
	from work_order s 
	where s.order_type='SELL' and s.executed_time is null
	group by s.group_name, s.symbol_name
	order by orderTime desc
	) as sell;


--Group A
update work_order set executed_amount=340.10, executed_quantity=1, execute_time=STR_TO_DATE('2015-10-30 02:20pm', '%Y-%m-%d %h:%i%p') 
 where group_name='Group A' and symbol_name='COPPER' and order_type='SELL' and 

insert into work_order (group_name, candidate_id, symbol_name, order_type, order_amount, order_quantity, order_time, expiry_date)
 values ('Group A', '90129', 'COPPER', 'SELL', 340.10, 1, STR_TO_DATE('2015-10-30 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-30', '%Y-%m-%d'));
insert into work_order (group_name, candidate_id, symbol_name, order_type, order_amount, order_quantity, order_time, expiry_date)
 values ('Group A', '90129', 'COPPER', 'SELL', 361.10, 1, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-30', '%Y-%m-%d'));
insert into work_order (group_name, candidate_id, symbol_name, order_type, order_amount, order_quantity, order_time, expiry_date)
 values ('Group A', '90129', 'LEAD', 'SELL', 119.6, 1, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-30', '%Y-%m-%d'));

--Group B
insert into work_order (group_name, candidate_id, symbol_name, order_type, order_amount, order_quantity, order_time, expiry_date)
 values ('Group B', '69757', 'COPPERM', 'SELL', 368.20, 1, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-30', '%Y-%m-%d'));
insert into work_order (group_name, candidate_id, symbol_name, order_type, order_amount, order_quantity, order_time, expiry_date)
 values ('Group B', '90140', 'COPPERM', 'SELL', 368.20, 1, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-30', '%Y-%m-%d'));
insert into work_order (group_name, candidate_id, symbol_name, order_type, order_amount, order_quantity, order_time, expiry_date)
 values ('Group B', '90141', 'COPPERM', 'SELL', 368.20, 1, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-30', '%Y-%m-%d'));

insert into work_order (group_name, candidate_id, symbol_name, order_type, order_amount, order_quantity, order_time, expiry_date)
 values ('Group B', '69757', 'LEADMINI', 'SELL', 120.20, 1, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-30', '%Y-%m-%d'));
insert into work_order (group_name, candidate_id, symbol_name, order_type, order_amount, order_quantity, order_time, expiry_date)
 values ('Group B', '90140', 'LEADMINI', 'SELL', 120.20, 1, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-30', '%Y-%m-%d'));
insert into work_order (group_name, candidate_id, symbol_name, order_type, order_amount, order_quantity, order_time, expiry_date)
 values ('Group B', '90141', 'LEADMINI', 'SELL', 120.20, 1, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-30', '%Y-%m-%d'));

--Group C
insert into work_order (group_name, candidate_id, symbol_name, order_type, order_amount, order_quantity, order_time, expiry_date)
 values ('Group C', '69756', 'COPPERM', 'SELL', 354.00, 1, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-30', '%Y-%m-%d'));
insert into work_order (group_name, candidate_id, symbol_name, order_type, order_amount, order_quantity, order_time, expiry_date)
 values ('Group C', '90137', 'COPPERM', 'SELL', 354.00, 1, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-30', '%Y-%m-%d'));
insert into work_order (group_name, candidate_id, symbol_name, order_type, order_amount, order_quantity, order_time, expiry_date)
 values ('Group C', '90135', 'COPPERM', 'SELL', 354.00, 1, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-30', '%Y-%m-%d'));

===============================================================================================================================

select c.id as commodityId, c.NAME as commodityName from commodity c
select g.id as groupId, g.NAME as groupName from groups groupId g 

select c.id as commodityId, c.NAME as commodityName, g.id as groupId, g.NAME as groupName from commodity c, groups g, group_commodity gc
 where gc.group_id = g.id and gc.cmdty_id = c.id;
 
select ctg.id as ctgId, sctg.id as subCtgId, scgrp.group_id as groupId, scgrp.recent_exec_date as recentExecDate
 from TYPES ctg, SUB_TYPES sctg, sub_type_groups scgrp
 where ctg.id = sctg.ctg_id and sctg.id = scgrp.sub_type_id
 group by ctgId
 order by ctgId, subCtgId, recentExecDate
 
 
 
 
 
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date) values
 ((select id from groups where name='Group A'), (select id from commodity where name='ALLUMINIUM'), 'SELL', 90129, 1, 103, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-29 02:20pm', '%Y-%m-%d %h:%i%p'));
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date) values
 ((select id from groups where name='Group A'), (select id from commodity where name='COPPER'), 'SELL', 90129, 1, 347.50, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-04-29 02:20pm', '%Y-%m-%d %h:%i%p'));

insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date) values
 ((select id from groups where name='Group B'), (select id from commodity where name='ALUMINI'), 'SELL', 69757, 1, 109.60, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-29 02:20pm', '%Y-%m-%d %h:%i%p'));

insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date) values
 ((select id from groups where name='Group C'), (select id from commodity where name='ALUMINI'), 'SELL', 69756, 1, 106.30, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-29 02:20pm', '%Y-%m-%d %h:%i%p'));

insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date) values
 ((select id from groups where name='Group E'), (select id from commodity where name='LEDMINI'), 'SELL', 69756, 1, 106.30, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-29 02:20pm', '%Y-%m-%d %h:%i%p'));

insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date) values
 ((select id from groups where name='Group F'), (select id from commodity where name='LEDMINI'), 'SELL', 90139, 1, 120.50, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-29 02:20pm', '%Y-%m-%d %h:%i%p'));

insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date) values
 ((select id from groups where name='Group G'), (select id from commodity where name='LEDMINI'), 'SELL', 90143, 1, 112.70, STR_TO_DATE('2015-11-01 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-01-29 02:20pm', '%Y-%m-%d %h:%i%p'));
