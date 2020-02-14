select user(), database();

select * from employee;
select * from department;
select * from title;

insert into title values ("T006", "인턴");
update title set title_name = "무기계약직" where title_no = "T006";
delete from title where title_no = "T006";

select title_no, title_name from title;
select title_no, title_name from title where title_no = "T006";

select * 
	from employee e join title t on e.title = t.title_no;