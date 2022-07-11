insert into course_details(id, name, created_date, last_updated_date,is_deleted)
values(10001,'course 1', CURRENT_DATE(), CURRENT_DATE(),false);
insert into course_details(id, name, created_date, last_updated_date,is_deleted)
values(10002,'course 2', CURRENT_DATE(), CURRENT_DATE(),false);
insert into course_details(id, name, created_date, last_updated_date,is_deleted)
values(10003,'course 3', CURRENT_DATE(), CURRENT_DATE(),false);

insert into passport(id,number)
values(40001,'passport 1');
insert into passport(id,number)
values(40002,'passport 2');
insert into passport(id,number)
values(40003,'passport 3');

insert into student(id,name,passport_id)
values(20001,'student 1',40001);
insert into student(id,name,passport_id)
values(20002,'student 2',40002);
insert into student(id,name,passport_id)
values(20003,'student 3',40003);

insert into review(id,rating,description,course_id)
values(50001,'FIVE', 'five star rating',10001);
insert into review(id,rating,description,course_id)
values(50002,'FOUR', 'four star rating',10001);
insert into review(id,rating,description,course_id)
values(50003,'FOUR', 'five star rating',10003);

insert into student_course(student_id,course_id)
values(20001,10001);
insert into student_course(student_id,course_id)
values(20002,10001);
insert into student_course(student_id,course_id)
values(20003,10001);
insert into student_course(student_id,course_id)
values(20001,10003);