package ar.edu.itba.it.paw.hotelapp.web.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HtmlHelper {

	public static void render(final String jspFile,
			final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		System.out.println(jspFile);

		request.setAttribute("page", jspFile);
		request.getRequestDispatcher("/WEB-INF/static/shared/layout.jsp")
				.forward(request, response);
	}

	public static void bodyTagOpen(final PrintWriter writer) {
		writer.write("<body>");
	}

	public static void bodyTagClose(final PrintWriter writer) {
		writer.write("</body>");
	}

	public static void htmlTagOpen(final PrintWriter writer) {
		writer.write("<html>");
	}

	public static void htmlTagClose(final PrintWriter writer) {
		writer.write("</html>");
	}

	public static String stylesheetTag(final String url) {
		return "<link href=\"" + url
				+ "\" rel=\"stylesheet\" type=\"text/css\"/>";
	}

	public static String scriptTag(final String url) {
		return "<script type=\"text/javascript\" href=\"" + url
				+ "\"></script>";
	}

	public static String linkTo(final String url, final String text) {
		return "<a href=\"" + url + "\">" + text + "</a>";
	}
}
