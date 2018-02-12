package pl.codeSchool.controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.codeSchool.model.ActiveRecord;
import pl.codeSchool.model.User;

/**
 * Servlet implementation class TestUser
 */
@WebServlet("/testuser")
public class TestUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActiveRecord user = new User();
		user.getById(6);
request.setAttribute("user", user);
		getServletContext().getRequestDispatcher("/WEB-INF/views/form.jsp").forward(request, response);
		// for (String key : user.getFields()) {
		// response.getWriter().append(key + " "+ user.getValue(key)+"<br>");
		// }
		// user.setValue("email","kopytko");
		// for (String key : user.getFields()) {
		// response.getWriter().append(key + " "+ user.getValue(key)+"<br>");
		// }
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActiveRecord user = new User();
		for (String name : user.getFields()) {
			user.setValue(name, request.getParameter(name));
		}
		user.save(); ///powinna zaciągać id po utworzeniu
		response.sendRedirect("testuser");
	}

}
