package application;
import javax.servlet.http.HttpSession;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

@DatabaseTable(tableName = "user")
public class User  extends BaseDaoEnabled{
	public static final String ID_FIELD = "id";

	public static final String LOGIN_FIELD = "login";

	public static final String PASSWORD_FIELD = "password";

	public static final String ROLE_FIELD = "role";

	private HttpSession session;

	private void init () {
		try {
			ConnectionSource connectionSource = ConnectionPool.getConnection();
			Dao<User, Integer> userDao = DaoManager.createDao(
					connectionSource, User.class);
			this.setDao(userDao);
			TableUtils.createTableIfNotExists(connectionSource, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public User (HttpSession session) {
		this.init ();
	}
	public User() {
		this.init ();
	}
	@DatabaseField(generatedId = true,columnName = ID_FIELD)
	private long id;

	@DatabaseField(canBeNull = false,columnName = LOGIN_FIELD)
	private String login;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	@DatabaseField(canBeNull = false,columnName = PASSWORD_FIELD)
	private String password;

	@DatabaseField(canBeNull = false,columnName = ROLE_FIELD)
	private String role;

}
