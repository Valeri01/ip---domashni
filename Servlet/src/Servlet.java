

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> queries = new HashMap<>(); 
    /**
     * Default constructor. 
     */
    public Servlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>\r\n"
				+ "  <head></head>\r\n"
				+ "  <body>\r\n"
				+ "    <form method = \"post\">\r\n" + 
				"      Key:<br>\r\n" + 
				"      <input type=\"text\" name=\"key\"><br>\r\n" + 
				"      Value:<br>\r\n" + 
				"      <input type=\"text\" name=\"value\">\r\n" +
				"      <input type=\"submit\" value=\"Submit\">\r\n" +
				"    </form>\r\n" +
				"Key  ,   Value\r\n");
		for(Map.Entry<String, String> entry : queries.entrySet()) {
			out.println(entry.getKey() + " :" + entry.getValue() + "\r\n");
		}
		out.println("  </body>\r\n<html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		queries.put(key, value);
		doGet(request, response);
	}

}
