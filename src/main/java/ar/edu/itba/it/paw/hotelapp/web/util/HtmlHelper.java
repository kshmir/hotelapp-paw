package ar.edu.itba.it.paw.hotelapp.web.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HtmlHelper {

	/**
	 * Loads a jsp file from with the parameters given.
	 * 
	 * @param jspFile
	 *            JSP file to load
	 * @param request
	 *            Response to send
	 * @param response
	 *            Response to send
	 */
	public static void render(final String jspFile,
			final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(jspFile);

		request.setAttribute("page", jspFile);
		request.setAttribute("basePath", request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/static/shared/layout.jsp")
				.forward(request, response);
	}
}
