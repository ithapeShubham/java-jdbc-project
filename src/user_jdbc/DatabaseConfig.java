package user_jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
	
    private  String url;
    private  String user;
    private  String password;

    public DatabaseConfig() {
        init();
    }


	private void init() {
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("jdbc/application.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }
            prop.load(input);
            
            this.url = prop.getProperty("jdbc.url");
            this.user = prop.getProperty("jdbc.user");
            this.password = prop.getProperty("jdbc.password");
        } catch (IOException ex) {
        	
            ex.printStackTrace();
        }
	}


    public  Connection getConnection() throws SQLException {
        return DriverManager.getConnection( this.url, this.user , this.password);
    }
}
