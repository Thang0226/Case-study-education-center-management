package project.service;

import java.util.List;

public interface IService<T> {
	List<T> findAll();

	boolean add(T object);

	T findById(int id);

	boolean update(T object);

	boolean remove(int id);
}
