package com.ub.customizability.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ub.customizability.commands.UpdateTimeDBCommand;
import com.ub.customizability.util.AuthenticationConstants.StaticAuthenticationConstants;
import com.ub.customizability.util.CustomMathUtil.StaticCustomMathUtil;
/**
 * @author mohitakhakharia
 */
@SuppressWarnings("serial")
public class UpdateTimeServlet extends HttpServlet {
  static String credentialPath;
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      try {
  		credentialPath = req.getSession().getServletContext().getRealPath(StaticAuthenticationConstants.SECRETP12PATH);
  		String userId = "";
  		userId=req.getParameter("userId");
  		if(userId==null || userId.equals("")){
  			userId=req.getSession().getAttribute("userId").toString();
  		}
  		String experimentTime="0";
    	if(req.getParameter("experimentTime")!=null && !req.getParameter("experimentTime").equals("")){
    		experimentTime=req.getParameter("experimentTime");	
    	}
    	Integer newTimeValue = Integer.parseInt(req.getParameter("newTimeValue"));
    	String typeOfRequest= req.getParameter("typeOfRequest");
    	Boolean result=false;
    	String columnNo="";
    	UpdateTimeDBCommand upc=new UpdateTimeDBCommand();
    	if(typeOfRequest.equals(StaticAuthenticationConstants.UPDATE_TIME_HOMEPAGE)){
    		columnNo=StaticAuthenticationConstants.TIMEONHOMEPAGE;
        	result=upc.updateTime(newTimeValue, columnNo, userId,credentialPath);
    	}else if(typeOfRequest.equals(StaticAuthenticationConstants.UPDATE_TIME_SETTINGSPAGE)){
    		columnNo=StaticAuthenticationConstants.TIMEONSETTINGSPAGE;
        	result=upc.updateTime(newTimeValue, columnNo, userId,credentialPath);
    	}else if(typeOfRequest.equals(StaticAuthenticationConstants.UPDATE_TIME_ARTICLEPAGE)){
    		String articleId=req.getParameter("articleId");
    		columnNo=StaticCustomMathUtil.getTimeOnArticleCol(Integer.parseInt(articleId)).toString();
        	result=upc.updateTime(newTimeValue, columnNo, userId,credentialPath);
    	}
    	req.getSession().setAttribute("experimentTime", experimentTime);
    	req.getSession().setAttribute("isAuthenticated", "True");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		String response="{ \"result\":"+result+"}";
		resp.getWriter().write(response); 
	} catch (Exception e) {
		e.printStackTrace();
	}
    
  }
  
  }