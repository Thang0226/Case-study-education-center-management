use center_management;

DELIMITER $$
CREATE PROCEDURE Insert_User(
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
    INSERT INTO User (
        Email, Password, PhoneNumber, FullName, DateOfBirth, Address, Identity, Role_ID
    ) VALUES (
                 p_Email, p_Password, p_PhoneNumber, p_FullName, p_DateOfBirth, p_Address, p_Identity, p_Role_ID
             );
END $$

DELIMITER ;