select student.name,
student.age,
faculty."name"
from student
join faculty  on faculty.id = student.faculty_id;

select *
from student
join avatar on student.id = avatar.student_id;