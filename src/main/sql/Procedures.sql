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



delimiter $
create procedure list_student_status()
begin
	select * from student_status;
end $
delimiter ;

delimiter $
create procedure add_student_status(
    in in_name varchar(50)
)
begin
	insert into student_status values
    (null, in_name);
end $
delimiter ;

delimiter $
create procedure find_student_status(
    in in_id int
)
begin
	select * from student_status
    where id = in_id;
end $
delimiter ;

delimiter $
create procedure update_student_status(
	in in_id int,
    in in_name varchar(50)
)
begin
	update student_status
    set
		name = in_name
    where id = in_id;
end $
delimiter ;

delimiter $
create procedure delete_student_status(
    in in_id int
)
begin
	delete from student_status
    where id = in_id;
end $
delimiter ;

drop procedure list_tuition_status;

delimiter $
create procedure list_tuition_status()
begin
	select * from tuition_status;
end $
delimiter ;

delimiter $
create procedure add_tuition_status(
    in in_name varchar(50)
)
begin
	insert into student_status values
    (null, in_name);
end $
delimiter ;

drop procedure find_tuition_status;

delimiter $
create procedure find_tuition_status(
    in in_id int
)
begin
	select * from tuition_status
    where id = in_id;
end $
delimiter ;

delimiter $
create procedure update_tuition_status(
	in in_id int,
    in in_name varchar(50)
)
begin
	update student_status
    set
		name = in_name
    where id = in_id;
end $
delimiter ;

delimiter $
create procedure delete_tuition_status(
    in in_id int
)
begin
	delete from student_status
    where id = in_id;
end $
delimiter ;



delimiter $
create procedure list_exam_sessions()
begin
	select * from exam_session;
end $
delimiter ;

delimiter $
create procedure add_exam_session(
    in in_name varchar(50),
    in in_exam_date date
)
begin
	insert into exam_session values
    (null, in_name, in_exam_date);
end $
delimiter ;

delimiter $
create procedure find_exam_session (
    in in_id int
)
begin
	select * from exam_session
    where id = in_id;
end $
delimiter ;

delimiter $
create procedure update_exam_session(
	in in_id int,
    in in_name varchar(50),
    in in_exam_date date
)
begin
	update exam_session
    set
		name = in_name,
        exam_date = in_exam_date
    where id = in_id;
end $
delimiter ;

delimiter $
create procedure delete_exam_session(
    in in_id int
)
begin
	delete from exam_session
    where id = in_id;
end $
delimiter ;



delimiter $
create procedure list_exam_results()
begin
	select * from exam_result;
end $
delimiter ;

delimiter $
create procedure add_exam_result(
    in in_session_id int,
    in in_student_id int,
    in in_theory_score decimal(5,2),
    in in_practical_score decimal(5,2)
)
begin
	insert into exam_result values
    (in_session_id, in_student_id, in_theory_score, in_practical_score);
end $
delimiter ;

delimiter $
create procedure find_exam_result (
    in in_session_id int,
    in in_student_id int
)
begin
	select * from exam_session
    where exam_session_id = in_session_id and student_id = in_student_id;
end $
delimiter ;

delimiter $
create procedure update_exam_result(
	in in_exam_session_id int,
	in in_student_id int,
    in in_theory_score decimal(5,2),
    in in_practical_score decimal(5,2)
)
begin
	update exam_result
    set
		theory_score = in_theory_score,
        practical_score = in_practical_score
    where exam_session_id = in_exam_session_id and student_id = in_student_id;
end $
delimiter ;

delimiter $
create procedure delete_exam_result(
    in in_exam_session_id int,
	in in_student_id int
)
begin
	delete from exam_result
    where exam_session_id = in_exam_session_id and student_id = in_student_id;
end $
delimiter ;









drop procedure Insert_User;

DELIMITER $$
CREATE PROCEDURE Insert_User(
    IN p_Email VARCHAR(100),
    IN p_Password VARCHAR(255),
    IN p_PhoneNumber VARCHAR(20),
    IN p_FullName VARCHAR(100),
    IN p_DateOfBirth DATE,
    IN p_Address VARCHAR(255),
    IN p_Identity VARCHAR(50),
    IN p_Role_ID INT,
    OUT user_ID INT

)
BEGIN
    INSERT INTO User (
        Email, Password, PhoneNumber, FullName, DateOfBirth, Address, Identity, Role_ID
    ) VALUES (
        p_Email, p_Password, p_PhoneNumber, p_FullName, p_DateOfBirth, p_Address, p_Identity, p_Role_ID
        );
    SET user_ID = LAST_INSERT_ID();
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE Update_User(
    IN p_ID INT,
    IN p_Email VARCHAR(100),
    IN p_Password VARCHAR(255),
    IN p_PhoneNumber VARCHAR(20),
    IN p_FullName VARCHAR(100),
    IN p_DateOfBirth DATE,
    IN p_Address VARCHAR(255),
    IN p_Identity VARCHAR(50),
    IN p_Role_ID INT
)
BEGIN
    UPDATE User
    SET
        Email = p_Email,
        Password = p_Password,
        PhoneNumber = p_PhoneNumber,
        FullName = p_FullName,
        DateOfBirth = p_DateOfBirth,
        Address = p_Address,
        Identity = p_Identity,
        Role_ID = p_Role_ID
    WHERE
        ID = p_ID;
END$$

DELIMITER ;
