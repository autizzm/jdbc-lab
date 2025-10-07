package example.com.jdbc.lab.prepared_statement.dao;

import example.com.jdbc.lab.connection.ConnectionManager;
import example.com.jdbc.lab.prepared_statement.model.Student;
import example.com.jdbc.lab.prepared_statement.model.University;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UniversityDao
implements PreparedStatementDao<University> {
    private static final Logger logger = Logger.getLogger(UniversityDao.class.getName());

    @Override
    public University getById(Long id) {
        University university = null;
        final String query = "SELECT * FROM universities WHERE id = ? LIMIT 1";
        try (PreparedStatement pStmt = ConnectionManager.getConnection().prepareStatement(query)) {
            pStmt.setLong(1, id);
            ResultSet resultSet = pStmt.executeQuery();
            if (resultSet.next()) {
                university = new University();
                university.setId(resultSet.getLong("id"));
                university.setName(resultSet.getString("name"));
                university.setCountry(resultSet.getString("country"));
                university.setCity(resultSet.getString("city"));
            }
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return university;
    }

    @Override
    public void insert(University university) {
        final String query = "INSERT INTO universities (name, city,country) VALUES (?, ?, ?)";
        try (PreparedStatement pStmt = ConnectionManager.getConnection().prepareStatement(query)) {
            pStmt.setString(1, university.getName());
            pStmt.setString(2, university.getCity());
            pStmt.setString(3, university.getCountry());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(University university) {
        final String query = "UPDATE universities " + "SET name = ?, city = ?, age = ?, country = ?"
                + "WHERE id = ?";
        try (PreparedStatement pStmt = ConnectionManager.getConnection().prepareStatement(query)) {
            pStmt.setString(1, university.getName());
            pStmt.setString(2, university.getCity());
            pStmt.setString(3, university.getCountry());
            pStmt.setLong(4, university.getId());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        final String query = "DELETE FROM universities WHERE id = ?";
        try (PreparedStatement pStmt = ConnectionManager.getConnection().prepareStatement(query)) {
            pStmt.setLong(1, id);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<University> getAll() {
        List<University> universities;
        final String query = "SELECT * FROM universities";
        try (PreparedStatement pStmt = ConnectionManager.getConnection().prepareStatement(query)) {
            ResultSet resultSet = pStmt.executeQuery();
            universities = getUniversityListByResultSet(resultSet);
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return universities;
    }

    private List<University> getUniversityListByResultSet(ResultSet resultSet) throws
            SQLException {
        List<University> universities = new ArrayList<>();
        while (resultSet.next()) {
            University university = new University();
            university.setId(resultSet.getLong("id"));
            university.setName(resultSet.getString("name"));
            university.setCity(resultSet.getString("city"));
            university.setCountry(resultSet.getString("country"));
            universities.add(university);
        }
        return universities;
    }
}
