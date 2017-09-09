/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ub.customizability.servlet;

// [START simple_includes]
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ub.customizability.beans.UserSettingsBean;
import com.ub.customizability.commands.SettingsCommand;
import com.ub.customizability.util.AuthenticationConstants.StaticAuthenticationConstants;
/**
 * @author mohitakhakharia
 */
@SuppressWarnings("serial")
public class SaveSettingsServlet extends HttpServlet {
  
	  static String credentialPath;
	  @Override
	  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	      try {
	  		credentialPath = req.getSession().getServletContext().getRealPath(StaticAuthenticationConstants.SECRETP12PATH);
	    	String userId = req.getSession().getAttribute("userId").toString();
	    	Boolean isSaved=false;
	    	UserSettingsBean usb=new UserSettingsBean();
	    	usb.setEconomyLiberal(req.getParameter("el"));
	    	usb.setEconomyConservative(req.getParameter("ec"));
	    	usb.setHealthCareLiberal(req.getParameter("hl"));
	    	usb.setHealthCareConservative(req.getParameter("hc"));
	    	usb.setCrimeLiberal(req.getParameter("cl"));
	    	usb.setCrimeConservative(req.getParameter("cc"));
	    	
	    	SettingsCommand sc=new SettingsCommand();
	    	isSaved=sc.saveUserSettings(usb,credentialPath,userId);
	    	
	    	
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			String response="{ \"isSaved\":"+isSaved+"}";
			resp.getWriter().write(response); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	  }
  }