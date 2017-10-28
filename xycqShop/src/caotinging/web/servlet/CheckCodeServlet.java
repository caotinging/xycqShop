package caotinging.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String checkCodeStr = (String) session.getAttribute("checkcode_session");
		String checkcode = (String) request.getParameter("checkcode_res");
		boolean flag = false;
		
		if(checkCodeStr.equals(checkcode)) {
			flag = true;
		}
		response.getWriter().write("{\"res\": "+flag+"}");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
