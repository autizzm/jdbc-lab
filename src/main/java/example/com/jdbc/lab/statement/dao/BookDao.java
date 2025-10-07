package example.com.jdbc.lab.statement.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import example.com.jdbc.lab.connection.ConnectionManager;
import example.com.jdbc.lab.statement.model.Book;
import example.com.jdbc.lab.statement.model.ShopBook;

public class BookDao implements StatementDao<Book> {
	private static final Logger logger = Logger.getLogger(BookDao.class.getName());

	@Override
	public void clearTable() {
		try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
			stmt.executeUpdate("DELETE FROM books");
		} catch (SQLException e) {
			logger.severe("Ошибка: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insert(Book book) {
		try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
			stmt.executeUpdate("INSERT INTO books (title, author_id, year_of_publication)" + "VALUES ('"
					+ book.getTitle() + "', '" + book.getAuthorId() + "', '" + book.getYearOfPublication() + "')");
		} catch (SQLException e) {
			logger.severe("Ошибка: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public void update(long bookId, String newName){
		try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
			stmt.executeUpdate("UPDATE books SET title = '" + newName + "' WHERE id = "
					+ bookId + ";");
		} catch (SQLException e) {
			logger.severe("Ошибка: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}


	@Override
	public int getCount() {
		int count = 0;
		try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
			ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM books");
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.severe("Ошибка: " + e.getMessage());
			throw new RuntimeException(e);
		}
		return count;
	}

	@Override
	public List<Book> getAll() {
		List<Book> books = new ArrayList<>();
		try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM books");
			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getLong("id"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthorId(resultSet.getLong("author_id"));
				book.setYearOfPublication(resultSet.getInt("year_of_publication"));
				books.add(book);
			}
		} catch (SQLException e) {
			logger.severe("Ошибка: " + e.getMessage());
			throw new RuntimeException(e);
		}
		return books;
	}

	@Override
	public Book getById(Long id) {
		Book book = null;
		try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM books WHERE id = " + id);
			if (resultSet.next()) {
				book = new Book();
				book.setId(resultSet.getLong("id"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthorId(resultSet.getLong("author_id"));
				book.setYearOfPublication(resultSet.getInt("year_of_publication"));
			}
		} catch (SQLException e) {
			logger.severe("Ошибка: " + e.getMessage());
			throw new RuntimeException(e);
		}
		return book;
	}
}