package project.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
	Connection getConnection() throws SQLException;

	List<T> findAll();

	boolean add(T object);

	T findById(int id);

	boolean update(T object);

	boolean remove(int id);
}
