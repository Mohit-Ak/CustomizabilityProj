package com.ub.customizability.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ub.customizability.beans.ArticleBean;
import com.ub.customizability.commands.ArticleCommand;
import com.ub.customizability.commands.ArticlePositionDBCommand;
import com.ub.customizability.commands.HomeCommand;
import com.ub.customizability.commands.TopicPositionDBCommand;
import com.ub.customizability.util.AuthenticationConstants.StaticAuthenticationConstants;
import com.ub.customizability.util.CustomMathUtil.StaticCustomMathUtil;
/**
 * @author mohitakhakharia
 */
@SuppressWarnings("serial")
public class HomePageServlet extends HttpServlet {
	static String credentialPath;

  @SuppressWarnings("unchecked")
@Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	  //Landing Page
      String nextJSP = "/jsp/HomePage.jsp";
      //Path to the Credential File
      credentialPath = req.getSession().getServletContext().getRealPath(StaticAuthenticationConstants.SECRETP12PATH);
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
      try {
    	// Getting the headlines for the homepage for the given user.
    	ArrayList<String> headlineIds=new ArrayList<String>();
    	HomeCommand hc=new HomeCommand();
    	String userId=req.getSession().getAttribute("userId").toString();
    	String userType=req.getSession().getAttribute("userType").toString();
    	
    	ArrayList<ArticleBean> abList=new ArrayList<ArticleBean>();
    	abList=(ArrayList<ArticleBean>) req.getSession().getAttribute("articleBeanList");
    	req.setAttribute("userId",userId);
    	if(abList==null){
    		String experimentTime="0";
    		if(req.getParameter("experimentTime")!=null && !req.getParameter("experimentTime").equals("")){
        		experimentTime=req.getParameter("experimentTime");	
        	}
	    	headlineIds=hc.getHeadLineIds(userId,userType,credentialPath);
	    	ArticleCommand ac=new ArticleCommand();
	    	abList=ac.getHeadLines(headlineIds, credentialPath);
	    	ac.setShownOrNotArticle(headlineIds, credentialPath, userId);
	    	ac.setClickedOrNotArticleBatch(abList, "NO", credentialPath, userId);
	    	System.out.println("Printing ArticleBeanList");
	    	System.out.println(abList);
	//    	req.setAttribute("articleBeanList",abList);
	    	req.getSession().setAttribute("articleBeanList", abList);
	    	
	    	//Getting Random Order For the topics on the home page.
	    	List<Integer> topicsorderList=StaticCustomMathUtil.getTopicsOrderForHomePage();
	    	TopicPositionDBCommand tpdbc=new TopicPositionDBCommand();
	    	tpdbc.saveTopicPositionOnHomePage(topicsorderList,credentialPath, userId);
	    	//tpdbc.saveTopicPositionOnSettingsPage(null, credentialPath, userId);
	    	req.getSession().setAttribute("topicsOrderList", topicsorderList);
	    	ArticlePositionDBCommand apdbc=new ArticlePositionDBCommand();
	    	req.getSession().setAttribute("experimentTime", experimentTime);
	    	apdbc.saveArticlePositionOnHomePage(abList, topicsorderList, credentialPath, userId);
	//    	req.setAttribute("topicsOrderList",topicsorderList);
    	}
    	resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    	resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    	resp.setHeader("Expires", "0");
		dispatcher.forward(req,resp);
	} catch (ServletException e) {
		e.printStackTrace();
	}
    
  }

}