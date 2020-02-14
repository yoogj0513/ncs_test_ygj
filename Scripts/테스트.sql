select user(), database();

select * from employee;
select * from department;
select * from title;

-- title
insert into title values ("T006", "인턴");
update title set title_name = "무기계약직" where title_no = "T006";
delete from title where title_no = "T006";

select title_no, title_name from title;
select title_no, title_name from title where title_no = "T006";

select * 
	from employee e join title t on e.title = t.title_no;
	
-- department
select dept_no , dept_name , floor 
	from department
	where dept_no = "D001";
	
select dept_no, dept_name, floor from department;
insert into department values("D006", "회계", 5);
update department set dept_name = "영업", floor = 8 where dept_no = "D006";
delete from department where dept_no = "D006";

-- employee
select emp_no , emp_name , title , salary , gender , dno , hire_date from employee;
select emp_no , emp_name , title , salary , gender , dno , hire_date 
	from employee
	where emp_no = "E017001";

insert into employee values("E017006", "가사원", "T005", 2500000, 1, "D003", '2017-05-01');
update employee 
	set emp_name = "다사원", title = "T004", salary = 3000000, gender = 1, dno = "D004", hire_date = '2017-05-01'
	where emp_no = "E017006";
delete from employee where emp_no = "E017006";