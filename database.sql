create database lib2
use lib2




create table book(
bookid int primary key ,
title varchar(20),
author varchar(20),
subject varchar(20),
status1 int,

);

create table user1
(
id int primary key,
pass int,
name nvarchar(max),
phn nvarchar(max),
addr nvarchar(max),
pos nvarchar(max),
finestat nvarchar(max)

);


create table loan
(
loanid int primary key,
bid int foreign key references book(bookid),
pid int foreign key references user1(id) ,
issdate date,
retdate date,
status1 nvarchar(max)

);

insert into book
values(1,'harrypotter','jk rowling','scific',1)
insert into book
values(4,'harrypotter','jk rowling','scific',1)
insert into book
values(2,'harrypotter2','jk rowling','scific',1)
insert into book
values(3,'harrypotter3','jk rowling','scific',1)
insert into book
values(5,'harrypotter5','jk rowling','scific',0)

insert into user1
values (1,123,'ali','03351474782','blablabla','librarian','paid')

insert into user1
values (2,123,'hamza','03351474782','blablabla','clerk','paid')

insert into user1
values (3,123,'saad','03351474782','blablabla','borrower','paid')

insert into user1
values (4,123,'dania','03351474782','blablabla','borrower','paid')

insert into user1
values (6,123,'paracha','03351474782','blablabla','borrower','paid')


insert into loan
values(1,5,6,getdate(),'2018-10-25','checkedout')


select * from book

select *
from user1

select *
from loan


--update book 
-- Set status1=1
-- where bookid=4
 
-- update book 
-- set status1=1,title='ali baba'
-- where bookid=2