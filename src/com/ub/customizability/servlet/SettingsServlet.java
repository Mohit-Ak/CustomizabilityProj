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
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ub.customizability.beans.NoOfArticlesBean;
import com.ub.customizability.commands.HomeCommand;
import com.ub.customizability.commands.TopicPositionDBCommand;
import com.ub.customizability.util.AuthenticationConstants.StaticAuthenticationConstants;
import com.ub.customizability.util.CustomMathUtil.StaticCustomMathUtil;
/**
 * @author mohitakhakharia
 */
@SuppressWarnings("serial")
public class SettingsServlet extends HttpServlet {
  static String credentialPath;
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      String nextJSP = "/jsp/SettingsPage.jsp";
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
      credentialPath = req.getSession().getServletContext().getRealPath(StaticAuthenticationConstants.SECRETP12PATH);
      try {
    	String userId=req.getSession().getAttribute("userId").toString();
    	String userType=req.getSession().getAttribute("userType").toString();
    	HomeCommand hc = new HomeCommand();
    	if((userType.equals(StaticAuthenticationConstants.USER_TYPE2_USR) || (userType.equals(StaticAuthenticationConstants.USER_TYPE4_USROVR)))){
    		NoOfArticlesBean noab=new NoOfArticlesBean();
        	noab=hc.getNoOfArticlesForAllTopicsFromDB(userId, credentialPath);
        	req.setAttribute("noOfArticlesBean",noab);
    	}
    	if(req.getSession().getAttribute("settingsTopicsOrderList")==null){
    		List<Integer> topicsorderList=StaticCustomMathUtil.getTopicsOrderForSettingsPage();
        	TopicPositionDBCommand tpdbc=new TopicPositionDBCommand();
        	tpdbc.saveTopicPositionOnSettingsPage(topicsorderList,credentialPath, userId);
        	req.getSession().setAttribute("settingsTopicsOrderList",topicsorderList);
        	req.getSession().setAttribute("liberalConsOrderInTopics", StaticCustomMathUtil.getLiberalConsOrderInTopics());	
    	}
    	resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    	resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    	resp.setHeader("Expires", "0");
		dispatcher.forward(req,resp);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
  }
  
  }