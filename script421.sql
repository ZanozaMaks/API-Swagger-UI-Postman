alter table
student add CONSTRAINT
student_student_is_over_sixteen_years
CHECK (age >= 16);

alter table student
add constraint student_name_is_not_null_and_unique
unique(name);

alter table student
alter column name set not null;

alter table faculty
add constraint name_unique
unique(name);

alter table faculty
add constraint color_unique
unique(color);

alter table student
alter column age set default 20;