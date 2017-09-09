package com.ub.customizability.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ub.customizability.beans.ArticleBean;
import com.ub.customizability.commands.ArticleCommand;
import com.ub.customizability.util.AuthenticationConstants.StaticAuthenticationConstants;
/**
 * @author mohitakhakharia
 */

@SuppressWarnings("serial")
public class ArticlePageServlet extends HttpServlet {
	static String credentialPath;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("In Post - ArticlePageServlet");
		String nextJSP = "/jsp/ArticlePage.jsp";
		credentialPath = req.getSession().getServletContext().getRealPath(StaticAuthenticationConstants.SECRETP12PATH);
		String articleId = req.getParameter("articleId");
		System.out.println("articleId is...");
		System.out.println(articleId);
		ArticleBean ab = new ArticleBean();
		try {
			ArticleCommand ac = new ArticleCommand();
			ab = ac.getArticle(articleId, credentialPath);
			String userId=req.getSession().getAttribute("userId").toString();
			
			ac.setClickedOrNotArticle(ab.getArticleId(), credentialPath, userId);
			System.out.println("Bean being returned");
			System.out.println(ab);
			req.setAttribute("articleBean", ab);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	    	resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	    	resp.setHeader("Expires", "0");
			dispatcher.forward(req, resp);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}