package info.superalsrk.servlet;

import info.superalsrk.utils.JdbcUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecordObtainServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String ss = req.getParameter("url");

		Connection conn = null;
		Statement stmt = null;
		String query = null;
		String result = null;

		try {
			conn = JdbcUtils.getConnection();
			stmt = conn.createStatement();

			query = "select * from mappedurl where shortcode='" + ss + "'";

			System.out.println(query);

			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				result = rs.getString("longurl");

				/*
				 * sendRedirect跳转时，url需要http头，若url以http开头，直接跳转
				 * 否则，加http头后再跳转
				 */
				
				if (result.substring(0, 4).equalsIgnoreCase("http"))
					resp.sendRedirect(result);
				else {
					resp.sendRedirect("http://" + result);
				}
			} else {
				resp.sendRedirect("404.html");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(ss);

	}
}
