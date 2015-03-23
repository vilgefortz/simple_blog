package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Bootstrap {
	public static void main(String[] args) throws Exception {
		System.out.println("Enter administrator login : ");
		Scanner sc = new Scanner(System.in);
		String login = sc.nextLine();
		String p1, p2="";
		do {
			System.out.println("Enter password : ");
			p1 = sc.nextLine();
			System.out.println("Repeat password : ");
			p2 = sc.nextLine();
			if (!p1.equals(p2))
				System.out.println("Passwords not match");
		} while (!p1.equals(p2));
		User user = new User ();
		user.setLogin(login);
		user.setPassword (Login.encrypt(p1));
		user.setRole("admin");
		user.getDao().create(user);
		//creating first article if it does not exists
		BlogEntry be = new BlogEntry();
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd");
		String date = formatter.format(today);
		be.setDate(date);
		be.setContent("First article content");
		be.setTitle("First article title");
		be.getDao().create(be);
	}
}
