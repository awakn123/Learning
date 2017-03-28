package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by 曹云 on 2017/3/4.
 */
public class Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Enumeration names = req.getHeaderNames();
		System.out.println("===================================================================");
		while(names.hasMoreElements()){
			String name = (String) names.nextElement();
			System.out.println(name + ":" + req.getHeader(name));
		}
		System.out.println("===================================================================");
		try(BufferedReader isr = new BufferedReader(new InputStreamReader(req.getInputStream()))) {
			String read;
			while ((read = isr.readLine()) != null) {
				System.out.println(read);
			}
		}

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
}
