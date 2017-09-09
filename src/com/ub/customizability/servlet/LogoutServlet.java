package com.ub.customizability.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ub.customizability.commands.LogoutCommand;
import com.ub.customizability.util.AuthenticationConstants.StaticAuthenticationConstants;
/**
 * @author mohitakhakharia
 */
public class LogoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = 29464293681672820L;
	static String credentialPath;
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		credentialPath = req.getSession().getServletContext().getRealPath(StaticAuthenticationConstants.SECRETP12PATH);
		String userId=req.getSession().getAttribute("userId").toString();
		String isTimeOut="false";
		if(req.getParameter("isTimeOut")!=null && !req.getParameter("isTimeOut").equals("")){
			isTimeOut=req.getParameter("isTimeOut");
		}
		LogoutCommand lc=new LogoutCommand();
		lc.setLoggedOut(userId, credentialPath);
		HttpSession session = req.getSession();
		session.invalidate();
		res.sendRedirect("/UserLogin?isTimeOut="+isTimeOut);

		
	}
}