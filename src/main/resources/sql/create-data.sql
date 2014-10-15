-- delete old data
delete from Article;
delete from User;
 
-- add few Users
insert into employee values(1, 'management', 'Steve Jobs');
insert into employee values(2, 'management', 'Bill Gates');
 
-- add few Articles
insert into manager values(1, 'Eric Schmidt');
insert into manager values(2, 'Steve Ballmer');
 
-- connect tasks to some employees
insert into task_employee values (1, 1);
insert into task_employee values (1, 3);
insert into task_employee values (1, 4);
insert into task_employee values (2, 2);
insert into task_employee values (2, 1);
 
-- create some timesheets on tasks
insert into timesheet values(1,
    5, -- hours
    1, -- first task
    1 -- employee steve jobs
);
 
insert into timesheet values(2,
    8, -- hours
    2, -- second task
    3 -- employee bill gates
);