package user_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDao {

	private static final Logger logger = LogManager.getLogger(UserDao.class);

	DatabaseConfig dbConfig = new DatabaseConfig();

	public int createUser(String name, String email) {

		try (Connection connection = dbConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CrudQuery.INSERT_USER,
						Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);
			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			}
		} catch (SQLException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}

	public User getUserById(int id) throws SQLException {
	    User user = null;
	    try (Connection connection = dbConfig.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(CrudQuery.READ_USER)) {
	        preparedStatement.setInt(1, id);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            int userId = resultSet.getInt("id");
	            String name = resultSet.getString("name");
	            String email = resultSet.getString("email");
	            user = new User(userId, name, email);
	        }
	    }
	    return user;
	}


	public void updateUser(int id, String name, String email) throws SQLException {

		try (Connection connection = dbConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CrudQuery.UPDATA_USER)) {
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);
			preparedStatement.setInt(3, id);
			preparedStatement.executeUpdate();
		}
	}

	public void deleteUser(int id) throws SQLException {

		try (Connection connection = dbConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CrudQuery.DELETE_USER)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		}
	}

	

}
