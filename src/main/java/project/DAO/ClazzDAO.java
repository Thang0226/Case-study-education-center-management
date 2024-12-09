package project.DAO;

import project.model.Clazz;
import project.model.DTO.StudentAvgScoreDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClazzDAO implements IClazzDAO{
    private static final String SELECT_ALL_CLASSES = "SELECT * FROM clazz";
    private static final String INSERT_CLASS = "INSERT INTO clazz (name, tutor_id, subject_id) VALUES (?, ?, ?)";
    private static final String SELECT_CLASS_BY_ID = "SELECT * FROM clazz WHERE id = ?";
    private static final String UPDATE_CLASS = "UPDATE clazz SET name = ?, tutor_id = ?, subject_id = ? WHERE id = ?";
    private static final String DELETE_CLASS = "DELETE FROM clazz WHERE id = ?";

    private static final String CLASS_LIST_WITH_STUDENT_AVG_SCORE = "call class_with_student_count_and_avg_score()";
//    Optional SQL (cuon xuong duoi cung xem method lien quan):
//    private static final String SELECT_CLASSES_BY_TUTOR = "SELECT * FROM clazz WHERE tutor_id = ?";
//    private static final String SELECT_CLASSES_BY_SUBJECT = "SELECT * FROM clazz WHERE subject_id = ?";

    @Override
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
            throw new SQLException("JDBC Driver not found", e);
        }
        String jdbcURL = "jdbc:mysql://localhost:3306/center_management";
        String jdbcUsername = "root";
        String jdbcPassword = "123456";
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    public List<Clazz> findAll() {
        List<Clazz> classes = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLASSES);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Clazz clazz = new Clazz(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("tutor_id"),
                        rs.getInt("subject_id")
                );
                classes.add(clazz);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return classes;
    }

    @Override
    public boolean add(Clazz clazz) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLASS)) {

            preparedStatement.setString(1, clazz.getName());
            preparedStatement.setInt(2, clazz.getTutorID());
            preparedStatement.setInt(3, clazz.getSubjectID());

            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                throw new SQLException("Failed to add class");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    clazz.setId(generatedKeys.getInt(1));
                }
            }

            return true;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    @Override
    public Clazz findById(int id) {
        Clazz clazz = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLASS_BY_ID)) {

            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    int tutorId = rs.getInt("tutor_id");
                    int subjectId = rs.getInt("subject_id");

                    clazz = new Clazz(id, name, tutorId, subjectId);
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return clazz;
    }

    @Override
    public boolean update(Clazz clazz) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLASS)) {

            preparedStatement.setString(1, clazz.getName());
            preparedStatement.setInt(2, clazz.getTutorID());
            preparedStatement.setInt(3, clazz.getSubjectID());
            preparedStatement.setInt(4, clazz.getId());

            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                throw new SQLException("Failed to update class");
            }
            return true;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    @Override
    public boolean remove(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLASS)) {

            preparedStatement.setInt(1, id);
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                throw new SQLException("Class with id " + id + " was not found");
            }
            return true;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    @Override
    public List<StudentAvgScoreDTO> getClassListWithStudentAvgScoreDTO() {
        List<StudentAvgScoreDTO> studentAvgScoreDTOs = new ArrayList<>();
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(CLASS_LIST_WITH_STUDENT_AVG_SCORE);
             ResultSet rs = callableStatement.executeQuery()) {
            while (rs.next()) {
                int classId = rs.getInt("classid");
                String className = rs.getString("class_name");
                String tutorName = rs.getString("tutor_name");
                String subjectName = rs.getString("subject_name");
                int studentNumber = rs.getInt("number_of_student");
                BigDecimal avgScore = rs.getBigDecimal("avg_score");
                studentAvgScoreDTOs.add(new StudentAvgScoreDTO(classId, className, tutorName, subjectName, studentNumber, avgScore));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return studentAvgScoreDTOs;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
            }
        }
    }

//    Optional methods
/*Find by Tutor Id*/
//    public List<Clazz> findByTutorId(int tutorId) {
//        List<Clazz> classes = new ArrayList<>();
//
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLASSES_BY_TUTOR)) {
//
//            preparedStatement.setInt(1, tutorId);
//
//            try (ResultSet rs = preparedStatement.executeQuery()) {
//                while (rs.next()) {
//                    Clazz clazz = new Clazz(
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getInt("tutor_id"),
//                            rs.getInt("subject_id")
//                    );
//                    classes.add(clazz);
//                }
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//
//        return classes;
//    }

/*Find by Subject Id*/
//    public List<Clazz> findBySubjectId(int subjectId) {
//        List<Clazz> classes = new ArrayList<>();
//
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLASSES_BY_SUBJECT)) {
//
//            preparedStatement.setInt(1, subjectId);
//
//            try (ResultSet rs = preparedStatement.executeQuery()) {
//                while (rs.next()) {
//                    Clazz clazz = new Clazz(
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getInt("tutor_id"),
//                            rs.getInt("subject_id")
//                    );classes.add(clazz);
//                }
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//
//        return classes;
//    }



}
