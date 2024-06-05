package user_jdbc;

public class CrudQuery {
	
	private CrudQuery() {
		// don't create object 
	}
	
	public static final String READ_USER = "SELECT * FROM users WHERE id = ?";
	public static final String UPDATA_USER = "UPDATE users SET name = ?, email = ? WHERE id = ?";
	public static final String INSERT_USER = "INSERT INTO users (name, email) VALUES (?, ?)";
	public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";

}
