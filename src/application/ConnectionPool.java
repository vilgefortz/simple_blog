package application;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
/**
 * Singleton do pobierania po³¹czenia z sesji
 * 
 */
public class ConnectionPool {

	private String databaseUrl = "jdbc:mysql://localhost/carpenter_blog?user=root&password=toor";

	private ConnectionPool () {
		JdbcPooledConnectionSource connectionSource;
		try {
			connectionSource = new JdbcPooledConnectionSource(databaseUrl);
			connectionSource.setMaxConnectionAgeMillis(1000*240);
			connection = connectionSource;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private ConnectionSource connection;
	private static ConnectionPool cp = new ConnectionPool ();
	private ConnectionSource getFromPool () {
		return cp.connection;
	}
	public static ConnectionSource getConnection() {
		return cp.getFromPool();
	}
}
