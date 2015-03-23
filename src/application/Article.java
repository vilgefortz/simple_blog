package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.j256.ormlite.stmt.PreparedQuery;

/**
 * Servlet implementation class Article
 */
@WebServlet("/Article")
public class Article extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession(true);
		String action = null;
		if ((action = request.getParameter("action")) == null)
			return;
		HashMap<String, RequestConsumer> requestDispatcher = 
				new HashMap<String,RequestConsumer> ();
		requestDispatcher.put("delete", this::delete);
		requestDispatcher.put("create", this::create);
		requestDispatcher.put("save", this::save);
		requestDispatcher.put("getArticles", this::getArticles);
		requestDispatcher.put("getArticle", this::getArticle);
		RequestConsumer consumer = requestDispatcher.get(action);
		if (consumer!=null ) {
			consumer.processRequest(request, response, writer, session);
		}
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void delete(HttpServletRequest request,
			HttpServletResponse response, PrintWriter writer, HttpSession session) {
		User user;
		if ((user = Login.getSessionUser(request))==null || !user.getRole().equals("admin"))
			return;
		try {
			int id = Integer.parseInt((String) request.getParameter("id"));
			if (id == 1)
				return; // not deleting first article
			BlogEntry entry = new BlogEntry();
			List<BlogEntry> list = entry.getDao().queryForEq("id", id);
			if (list.size() < 1)
				throw new Exception("");
			entry = list.get(0);
			entry.getDao().delete(entry);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			// nothong to do, no id on response
			return;
		}
	}

	protected void create(HttpServletRequest request,
			HttpServletResponse response, PrintWriter writer, HttpSession session) {
		User user;
		if ((user = Login.getSessionUser(request))==null || !user.getRole().equals("admin"))
			return;
		try {
			BlogEntry entry = new BlogEntry();
			entry.setTitle("Enter title here");
			entry.setContent("Enter content here");
			LocalDate today = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern("yyyy-MM-dd");
			String date = formatter.format(today);
			entry.setDate(date);
			entry.getDao().create(entry);
			writer.write("" + entry.getId());
			return;
		} catch (Exception e) {
			e.printStackTrace();
			// nothong to do, no id on response
			return;
		}
	}

	protected void getArticles(HttpServletRequest request,
			HttpServletResponse response, PrintWriter writer, HttpSession session) {
		try {
			BlogEntry entry = new BlogEntry();
			List<BlogEntry> list = entry.getDao().queryForAll();
			if (list.size() < 1)
				throw new Exception("");
			JSONObject result = new JSONObject();
			for (BlogEntry e : list) {
				JSONObject obj = new JSONObject();
				obj.put("id", e.getId());
				obj.put("title", e.getTitle());
				obj.put("date", e.getDate());
				result.put("" + e.getId(), obj.toString());
			}
			String json = result.toString();
			writer.write(json);
		} catch (Exception e) {
			writer.write("{\"error\":\"Article does not exists\"}");
			e.printStackTrace();
		}
	}

	protected void save(HttpServletRequest request,
			HttpServletResponse response, PrintWriter writer, HttpSession session) {
		try {
			User user;
			if ((user = Login.getSessionUser(request))==null || !user.getRole().equals("admin"))
				return;
			if (request.getParameter("id") == null)
				return;
			int id = Integer.parseInt((String) request.getParameter("id"));
			BlogEntry entry = new BlogEntry();
			List<BlogEntry> list = entry.getDao().queryForEq("id", id);
			if (list.size() < 1)
				throw new Exception("");
			entry = list.get(0);
			entry.setTitle(request.getParameter("title"));
			entry.setContent(request.getParameter("content"));
			entry.setDate(request.getParameter("date"));
			entry.getDao().update(entry);
			writer.write("OK");
		} catch (Exception e) {
			writer.write("{\"error\":\"Article does not exists\"}");
			// e.printStackTrace();
		}
		return;
	}

	protected void getArticle(HttpServletRequest request,
			HttpServletResponse response, PrintWriter writer, HttpSession session) {
		try {
			if (request.getParameter("id") == null)
				return;
			int id = Integer.parseInt((String) request.getParameter("id"));
			BlogEntry entry = new BlogEntry();
			List<BlogEntry> list = entry.getDao().queryForEq("id", id);
			if (list.size() < 1) {
				if (id == 1) {
					//we are not sure if this is request for article with id=1
					//or first article
					list = entry.getDao().queryForAll();
				}
				else {
					throw new Exception("");
				}
			}
			entry = list.get(0);
			JSONObject result = new JSONObject();
			result.put("title", entry.getTitle());
			result.put("content", entry.getContent());
			result.put("date", entry.getDate());
			result.put("error", "");
			String json = result.toString();
			writer.write(json);

		} catch (Exception e) {
			writer.write("{\"error\":\"Article does not exists\"}");
			// e.printStackTrace();
		}

	}
}
