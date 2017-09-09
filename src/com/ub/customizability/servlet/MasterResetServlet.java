package com.ub.customizability.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ub.customizability.init.CustomizabilityIniitialize;
import com.ub.customizability.util.AuthenticationConstants.StaticAuthenticationConstants;
/**
 * @author mohitakhakharia
 */
@SuppressWarnings("serial")
public class MasterResetServlet extends HttpServlet {
	static String credentialPath;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("In doGet - MasterReset");
		String nextJSP;
		credentialPath = req.getSession().getServletContext().getRealPath(StaticAuthenticationConstants.SECRETP12PATH);
		try {
			String password=req.getParameter("password");
			if(password.equals("adminpass")){
				CustomizabilityIniitialize.setClickedOrNotDefault(null, credentialPath);
				CustomizabilityIniitialize.setPositionOfTopicOnSettingsPageDefault(null, credentialPath);
				CustomizabilityIniitialize.setPosOnArticleDefault(null, credentialPath);
				CustomizabilityIniitialize.setShownOrNotDefault(null, credentialPath);
				CustomizabilityIniitialize.setTimeOnArticleDefault(null, credentialPath);
				CustomizabilityIniitialize.setTimeOnSettingsHomePageDefault(null, credentialPath);
				nextJSP = "/jsp/MasterReset.jsp";
				
			}else{
				nextJSP = "/jsp/Error.jsp";
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(req, resp);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}


}