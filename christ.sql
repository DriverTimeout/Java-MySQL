create database wrightDB;

create table employees
(
EmpID int auto_increment primary key,
EmpLname varchar(50),
EmpFname varchar(50),
EmpAge int
);

insert into employees (EmpLname, EmpFname, EmpAge)
values
("Cortez", "Justin", 54),
("Blaek", "jose", 12),
("numer", "kaor", 45),
("buerir", "lopkm", 24);

select * from employees;