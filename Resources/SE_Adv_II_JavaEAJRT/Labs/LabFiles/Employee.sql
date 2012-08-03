create table EMPLOYEE (
	EMPNO char(35) not null primary key,
	FIRST_NAME char(35) not null,
	LAST_NAME char(35) not null,
	WORKDEPT char(35) not null,
	SALARY float not null
);

create table DEPARTMENT (
	DEPTNO char(35) not null primary key,
	DEPTNAME varchar(128) not null,
	LOCATION varchar(256) not null
);

insert into EMPLOYEE values ('E001', 'John', 'Doe', 'D001', 20000.00);
insert into EMPLOYEE values ('E002', 'jane', 'Doe', 'D002', 30000.00);

insert into DEPARTMENT values ('D001', 'Human Resources', 'New York');
insert into DEPARTMENT values ('D002', 'Product Sales', 'New York');
insert into DEPARTMENT values ('D003', 'Engineering', 'Detroit');
insert into DEPARTMENT values ('D004', 'Manufacturing', 'Buffalo');