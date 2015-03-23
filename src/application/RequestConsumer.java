package application;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface RequestConsumer {
	public void processRequest (HttpServletRequest request, HttpServletResponse response, 
			PrintWriter writer, HttpSession session);
}
