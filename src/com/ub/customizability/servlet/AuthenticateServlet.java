package com.ub.customizability.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ub.customizability.commands.LoginCommand;
import com.ub.customizability.util.AuthenticationConstants.StaticAuthenticationConstants;
/**
 * @author mohitakhakharia
 */
@SuppressWarnings("serial")
public class AuthenticateServlet extends HttpServlet {
  static String credentialPath;
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      try {
  		credentialPath = req.getSession().getServletContext().getRealPath(StaticAuthenticationConstants.SECRETP12PATH);
    	LoginCommand lc=new LoginCommand();
    	String userId = req.getParameter("userId");
    	String password = req.getParameter("password");
    	Boolean isAuthenticated=false;
    	String userType;
    	userType=lc.isUserAuthenticated(userId, password,credentialPath);
    	if(userType!=null){
    		//User is authenticated
    		req.getSession().setAttribute("userId", userId);
    		req.getSession().setAttribute("userType", userType);
    		isAuthenticated=true;
    	}
    	
    	req.getSession().setAttribute("isAuthenticated", isAuthenticated);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		String response="{ \"isAuthenticated\":"+isAuthenticated+", \"userType\":\""+userType+"\"}";
		resp.getWriter().write(response); 
	} catch (Exception e) {
		e.printStackTrace();
	}
    
  }
  
  }