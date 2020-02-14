-- ncs테스트_ygj
DROP SCHEMA IF EXISTS ncs_test_ygj;

-- ncs테스트_ygj
CREATE SCHEMA ncs_test_ygj;

-- 부서
CREATE TABLE ncs_test_ygj.department (
	dept_no   VARCHAR(10) NOT NULL COMMENT '부서번호', -- 부서번호
	dept_name VARCHAR(10) NOT NULL COMMENT '부서명', -- 부서명
	floor     INT(11)     NULL     COMMENT '위치' -- 위치
)
COMMENT '부서';

-- 부서
ALTER TABLE ncs_test_ygj.department
	ADD CONSTRAINT PK_department -- 부서 기본키
		PRIMARY KEY (
			dept_no -- 부서번호
		);

-- 직책
CREATE TABLE ncs_test_ygj.title (
	title_no   VARCHAR(10) NOT NULL COMMENT '직책번호', -- 직책번호
	title_name VARCHAR(20) NOT NULL COMMENT '직책명' -- 직책명
)
COMMENT '직책';

-- 직책
ALTER TABLE ncs_test_ygj.title
	ADD CONSTRAINT PK_title -- 직책 기본키
		PRIMARY KEY (
			title_no -- 직책번호
		);

-- 사원
CREATE TABLE ncs_test_ygj.employee (
	emp_no    VARCHAR(10) NOT NULL COMMENT '사원번호', -- 사원번호
	emp_name  VARCHAR(20) NOT NULL COMMENT '사원명', -- 사원명
	title     VARCHAR(10) NULL     COMMENT '직책', -- 직책
	salary    INT(11)     NULL     COMMENT '급여', -- 급여
	gender    TINYINT     NULL     COMMENT '성별', -- 성별
	dno       VARCHAR(10) NULL     COMMENT '부서', -- 부서
	hire_date DATETIME    NOT NULL COMMENT '입사일' -- 입사일
)
COMMENT '사원';

-- 사원
ALTER TABLE ncs_test_ygj.employee
	ADD CONSTRAINT PK_employee -- 사원 기본키
		PRIMARY KEY (
			emp_no -- 사원번호
		);

-- 사원
ALTER TABLE ncs_test_ygj.employee
	ADD CONSTRAINT FK_title_TO_employee -- 직책 -> 사원
		FOREIGN KEY (
			title -- 직책
		)
		REFERENCES ncs_test_ygj.title ( -- 직책
			title_no -- 직책번호
		);

-- 사원
ALTER TABLE ncs_test_ygj.employee
	ADD CONSTRAINT FK_department_TO_employee -- 부서 -> 사원
		FOREIGN KEY (
			dno -- 부서
		)
		REFERENCES ncs_test_ygj.department ( -- 부서
			dept_no -- 부서번호
		);
		
-- 사용자 추가
drop user if exists 'user_ncs_test_ygj'@'localhost';
grant all privileges on ncs_test_ygj.* to 'user_ncs_test_ygj'@'localhost' identified by 'rootroot';
flush privileges;