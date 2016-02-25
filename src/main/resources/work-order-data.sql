insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group A A'), (select id from commodity where name='COPPER'), 'BUY', 69641, 2, 294.40, STR_TO_DATE('2016-02-08 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-04-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group A A'), (select id from commodity where name='LEAD'), 'BUY', 69641, 2, 111.50, STR_TO_DATE('2016-02-08 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-03-31 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group A A'), (select id from commodity where name='LEAD'), 'BUY', 69641, 2, 110.40, STR_TO_DATE('2016-02-08 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-03-31 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group A A'), (select id from commodity where name='NICKEL'), 'BUY', 69641, 2, 537.40, STR_TO_DATE('2016-02-08 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group A A'), (select id from commodity where name='ZINC'), 'BUY', 69641, 2, 110.20, STR_TO_DATE('2016-02-08 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-03-31 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group A A'), (select id from commodity where name='ZINC'), 'BUY', 69641, 2, 97.80, STR_TO_DATE('2016-02-08 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);


insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group A'), (select id from commodity where name='COPPER'), 'BUY', 90178, 1, 294.40, STR_TO_DATE('2016-02-08 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-04-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group A'), (select id from commodity where name='LEAD'), 'BUY', 90178, 1, 111.50, STR_TO_DATE('2016-02-08 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-03-31 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group A'), (select id from commodity where name='LEAD'), 'BUY', 90178, 1, 110.40, STR_TO_DATE('2016-02-08 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-03-31 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group A'), (select id from commodity where name='NICKEL'), 'BUY', 90178, 1, 537.40, STR_TO_DATE('2016-02-08 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group A'), (select id from commodity where name='ZINC'), 'BUY', 90178, 1, 110.20, STR_TO_DATE('2016-02-08 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-03-31 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group A'), (select id from commodity where name='ZINC'), 'BUY', 90178, 1, 97.80, STR_TO_DATE('2016-02-08 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);

insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group B'), (select id from commodity where name='ALUMINIUM'), 'BUY', 90181, 1, 98.70, STR_TO_DATE('2016-02-08 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-03-31 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group B'), (select id from commodity where name='COPPER'), 'BUY', 90181, 1, 300.40, STR_TO_DATE('2016-02-08 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-04-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group B'), (select id from commodity where name='LEAD'), 'BUY', 90181, 1, 105.50, STR_TO_DATE('2016-02-08 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group B'), (select id from commodity where name='SILVERM'), 'SELL', 90181, 1, 36410.00, STR_TO_DATE('2016-02-08 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group B'), (select id from commodity where name='ZINC'), 'BUY', 90181, 1, 102.70, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-03-31 00:00am', '%Y-%m-%d %h:%i%p'), null);

insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group E'), (select id from commodity where name='LEADMINI'), 'BUY', 90183, 1, 105.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group E'), (select id from commodity where name='SILVERMIC'), 'SELL', 90183, 1, 36410.00, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group E'), (select id from commodity where name='LEADMINI'), 'BUY', 69757, 1, 105.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group E'), (select id from commodity where name='SILVERMIC'), 'SELL', 69757, 1, 36410.00, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group E'), (select id from commodity where name='LEADMINI'), 'BUY', 90140, 1, 105.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group E'), (select id from commodity where name='SILVERMIC'), 'SELL', 90140, 1, 36410.00, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group E'), (select id from commodity where name='LEADMINI'), 'BUY', 90142, 1, 105.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group E'), (select id from commodity where name='SILVERMIC'), 'SELL', 90142, 1, 36410.00, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group E'), (select id from commodity where name='LEADMINI'), 'BUY', 90127, 1, 105.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group E'), (select id from commodity where name='SILVERMIC'), 'SELL', 90127, 1, 36410.00, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group E'), (select id from commodity where name='LEADMINI'), 'BUY', 90141, 1, 105.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group E'), (select id from commodity where name='SILVERMIC'), 'SELL', 90141, 1, 36410.00, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);

insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group M'), (select id from commodity where name='LEADMINI'), 'SELL', 90156, 1, 124.40, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-03-31 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group M'), (select id from commodity where name='LEADMINI'), 'SELL', 90130, 1, 124.40, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-03-31 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group M'), (select id from commodity where name='LEADMINI'), 'SELL', 90150, 1, 124.40, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-03-31 00:00am', '%Y-%m-%d %h:%i%p'), null); 
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group M'), (select id from commodity where name='LEADMINI'), 'BUY', 90156, 1, 107.80, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group M'), (select id from commodity where name='LEADMINI'), 'BUY', 90130, 1, 107.80, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group M'), (select id from commodity where name='LEADMINI'), 'BUY', 90150, 1, 107.80, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);

insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group O'), (select id from commodity where name='LEADMINI'), 'BUY', 69757, 1, 111.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group O'), (select id from commodity where name='LEADMINI'), 'BUY', 90140, 1, 111.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group O'), (select id from commodity where name='LEADMINI'), 'BUY', 90142, 1, 111.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group O'), (select id from commodity where name='LEADMINI'), 'BUY', 90127, 1, 111.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group O'), (select id from commodity where name='LEADMINI'), 'BUY', 90141, 1, 111.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group O'), (select id from commodity where name='LEADMINI'), 'BUY', 69756, 1, 111.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group O'), (select id from commodity where name='LEADMINI'), 'BUY', 90137, 1, 111.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group O'), (select id from commodity where name='LEADMINI'), 'BUY', 90135, 1, 111.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group O'), (select id from commodity where name='LEADMINI'), 'BUY', 90125, 1, 111.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group O'), (select id from commodity where name='LEADMINI'), 'BUY', 90138, 1, 111.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group O'), (select id from commodity where name='LEADMINI'), 'BUY', 90120, 1, 111.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group O'), (select id from commodity where name='LEADMINI'), 'BUY', 90139, 1, 111.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group O'), (select id from commodity where name='LEADMINI'), 'BUY', 8712, 1, 111.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group O'), (select id from commodity where name='LEADMINI'), 'BUY', 90183, 1, 111.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group O'), (select id from commodity where name='LEADMINI'), 'BUY', 90160, 1, 111.50, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);

insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group R'), (select id from commodity where name='LEAD'), 'BUY', 90140, 1, 111.20, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group R'), (select id from commodity where name='LEAD'), 'BUY', 90142, 1, 111.20, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group R'), (select id from commodity where name='LEAD'), 'BUY', 90137, 1, 111.20, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group R'), (select id from commodity where name='LEAD'), 'BUY', 90139, 1, 111.20, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group R'), (select id from commodity where name='LEAD'), 'BUY', 90120, 1, 111.20, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group R'), (select id from commodity where name='LEAD'), 'BUY', 90125, 1, 111.20, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group R'), (select id from commodity where name='LEAD'), 'BUY', 69757, 1, 111.20, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group R'), (select id from commodity where name='LEAD'), 'BUY', 90135, 1, 111.20, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group R'), (select id from commodity where name='LEAD'), 'BUY', 90160, 1, 111.20, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group R'), (select id from commodity where name='LEAD'), 'BUY', 90183, 1, 111.20, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);

insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group U'), (select id from commodity where name='LEADMINI'), 'BUY', 90181, 1, 103.30, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group U'), (select id from commodity where name='SILVERMIC'), 'BUY', 90181, 1, 34410.00, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group U'), (select id from commodity where name='ZINCMINI'), 'BUY', 90181, 1, 95.60, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);

insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group V'), (select id from commodity where name='LEADMINI'), 'SELL', 90127, 1, 128.40, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-03-31 00:00am', '%Y-%m-%d %h:%i%p'), null); 
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group V'), (select id from commodity where name='LEADMINI'), 'SELL', 90141, 1, 128.40, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-03-31 00:00am', '%Y-%m-%d %h:%i%p'), null); 
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group V'), (select id from commodity where name='LEADMINI'), 'SELL', 90166, 1, 128.40, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-03-31 00:00am', '%Y-%m-%d %h:%i%p'), null); 
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group V'), (select id from commodity where name='LEADMINI'), 'SELL', 90124, 1, 128.40, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-03-31 00:00am', '%Y-%m-%d %h:%i%p'), null); 
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group V'), (select id from commodity where name='LEADMINI'), 'SELL', 90150, 1, 128.40, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-03-31 00:00am', '%Y-%m-%d %h:%i%p'), null); 

insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group W'), (select id from commodity where name='ZINCMINI'), 'SELL', 90127, 1, 120.40, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group W'), (select id from commodity where name='ZINCMINI'), 'SELL', 90141, 1, 120.40, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);

insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group X'), (select id from commodity where name='NICKELM'), 'SELL', 69757, 1, 588.40, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group X'), (select id from commodity where name='NICKELM'), 'SELL', 90140, 1, 588.40, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group X'), (select id from commodity where name='NICKELM'), 'SELL', 90142, 1, 588.40, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group X'), (select id from commodity where name='NICKELM'), 'SELL', 90127, 1, 588.40, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group X'), (select id from commodity where name='NICKELM'), 'SELL', 90141, 1, 588.40, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, executed_time) values (
 (select id from groups where name='Group X'), (select id from commodity where name='NICKELM'), 'SELL', 90183, 1, 588.40, STR_TO_DATE('2016-03-31 02:20pm', '%Y-%m-%d %h:%i%p'), STR_TO_DATE('2016-02-29 00:00am', '%Y-%m-%d %h:%i%p'), null);
 