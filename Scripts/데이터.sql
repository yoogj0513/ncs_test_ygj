-- title
insert title values
("T001", "사장"),
("T002", "부장"),
("T003", "과장"),
("T004", "대리"),
("T005", "사원");

-- department
insert department values
("D001", "마케팅", 10),
("D002", "개발", 9),
("D003", "인사", 6),
("D004", "총무", 7),
("D005", "경영", 4);


select * from employee;
-- employee
insert employee values
("E017001", "나사장", "T001", 5000000, 1, "D005", '2017-03-01'),
("E017002", "나부장", "T002", 4000000, 0, "D001", '2017-03-01'),
("E017003", "나과장", "T003", 3500000, 1, "D003", '2017-04-01'),
("E017004", "나대리", "T004", 3000000, 0, "D002", '2017-04-01'),
("E017005", "나사원", "T005", 2500000, 1, "D004", '2017-04-01');