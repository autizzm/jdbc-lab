package example.com.jdbc.lab.prepared_statement.dao;

import java.util.List;

public interface PreparedStatementDao<T> {
	T getById(Long id);

	void insert(T t);

	void update(T t);

	void delete(Long id);
}