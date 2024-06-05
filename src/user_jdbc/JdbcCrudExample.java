package user_jdbc;

import java.sql.SQLException;

public class JdbcCrudExample {

	public static void main(String[] args) {

		try {
			// Create a new user
			UserDao userDao = new UserDao();
			String name = "John Doe";
			String email = "john@example.com";
			int userId = userDao.createUser(name, email);
			
			System.out.println("User created with ID: " + userId);

			// Read user by ID
			User user = userDao.getUserById(userId);
			
			System.out.println("User Read with ID: " + user);


			// Update user
			name = "Jane Doe";
			email = "jane@example.com";
			System.out.println("Updated User with id : " + userId);
			userDao.updateUser(userId, name, email);

			
			// Delete user
			
			userDao.deleteUser(userId);
			System.out.println("Deleted User with ID: " + userId);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
