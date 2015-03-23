package application;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import sun.misc.BASE64Encoder;

public class Login {
	private static final String NO_USER_FOUND = "User not found";
	private static final String WRONG_LOGIN_DATA = "Wrong login data";
	public static final String LOGGED = "Logged in";
	public static final String LOGOUT = "Logged out";

	public Login() {

	}

	/**
	 * To bootstrap this application run this class as java application
	 */
	@SuppressWarnings("unchecked")
	
	
	public static String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setAttribute("user", null);
		return LOGOUT;
	}

	public static User getSessionUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.getAttribute("user");
		return (User) session.getAttribute("user");
	}

	@SuppressWarnings("serial")
	public static String login(HttpServletRequest request, String login,
			String password) {
		try {
			HttpSession session = request.getSession(true);
			// ConnectionPool.setSession(session);
			User user = new User();

			@SuppressWarnings("unchecked")
			List<User> list = user.getDao().queryForEq("login", login);
			if (list.size() < 1) {
				throw new Exception() {
					public String getMessage() {
						return NO_USER_FOUND;
					};
				};
			}
			user = list.get(0);

			String hash = encrypt(password);
			if (hash.equals(user.getPassword())) {
				session.setAttribute("user", user);
				return LOGGED;
			} else {
				throw new Exception() {
					public String getMessage() {
						return WRONG_LOGIN_DATA;
					};
				};

			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public static String encrypt(String plaintext) throws Exception {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA"); // step 2
		} catch (NoSuchAlgorithmException e) {
			throw new Exception(e.getMessage());
		}
		try {
			md.update(plaintext.getBytes("UTF-8")); // step 3
		} catch (UnsupportedEncodingException e) {
			throw new Exception(e.getMessage());
		}

		byte raw[] = md.digest(); // step 4
		String hash = (new BASE64Encoder()).encode(raw); // step 5
		return hash; // step 6
	}
}
