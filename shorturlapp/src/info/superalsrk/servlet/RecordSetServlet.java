package info.superalsrk.servlet;

import info.superalsrk.utils.JdbcUtils;
import info.superalsrk.utils.ShortUrlGenerator;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecordSetServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 处理中文参数
		String ss = new String(req.getParameter("url").getBytes("ISO-8859-1"),
				"UTF-8");
		
		resp.setContentType("text/html");
		
		if(ss.isEmpty()) resp.sendRedirect("/shorturlapp/index.html");
		

		Connection conn = null;
		Statement stmt = null;
		String shorturl = null;
		String query = null;

		try {
			conn = JdbcUtils.getConnection();
			stmt = conn.createStatement();
			shorturl = ShortUrlGenerator.ShortText(ss)[0];
			query = "insert into mappedurl values('" + shorturl + "','" + ss + "')";
			stmt.execute(query);
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(query);
		System.out.println(ss);

		
		PrintWriter out = resp.getWriter();
		out.println("<HTML>");
		out.println("<HEAD><TITLE>The Short Url is</TITLE></HEAD>");
		out.println("<BODY>");

		out.println("<h1>");
		out.println("The short url is:   localhost:8080/shorturlapp/" + shorturl);
		out.println("</h1>");
		out.println("</BODY></HTML>");

	}
}
