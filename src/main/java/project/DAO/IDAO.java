package project.DAO;

import java.util.List;

public interface IDAO<T> {
	List<T> findAll();

	boolean add(T object);

	T findById(int id);

	boolean update(int id, T object);

	boolean remove(int id);
}
