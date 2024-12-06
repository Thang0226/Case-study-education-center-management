use center_management;

delimiter $
create procedure list_students()
begin
	select * from student;
end $
delimiter ;

delimiter $
create procedure add_student(
    in in_user_id int,
    in in_tuition_status_id int,
    in in_student_status_id int,
    in in_class_id int
)
begin
	insert into student values
    (null, in_user_id, in_tuition_status_id, in_student_status_id, in_class_id);
end $
delimiter ;

delimiter $
create procedure find_student(
    in in_id int
)
begin
	select * from student
    where id = in_id;
end $
delimiter ;

delimiter $
create procedure update_student(
	in in_id int,
    in in_user_id int,
    in in_tuition_status_id int,
    in in_student_status_id int,
    in in_class_id int
)
begin
	update student 
    set
		user_id = in_user_id,
        tuition_status_id = in_tuition_status_id,
        student_status_id = in_student_status_id,
        class_id = in_class_id
    where id = in_id;
end $
delimiter ;

delimiter $
create procedure delete_student(
    in in_id int
)
begin
	delete from student
    where id = in_id;
end $
delimiter ;

















