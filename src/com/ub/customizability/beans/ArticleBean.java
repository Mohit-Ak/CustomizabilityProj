package com.ub.customizability.beans;

import java.io.Serializable;
/**
 * @author mohitakhakharia
 * This class is used as a static bean to save/retrieve one article
 */
public class ArticleBean implements Serializable {

	private static final long serialVersionUID = -4287327932256287005L;
	private int ArticleId;
	private String ArticleTopic;
	private String ArticleType;
	private String ArticleHeadLine;
	private String ArticleContent;
	
	
	/**
	 * @return the articleTopic
	 */
	public String getArticleTopic() {
		return ArticleTopic;
	}
	/**
	 * @param articleTopic the articleTopic to set
	 */
	public void setArticleTopic(String articleTopic) {
		ArticleTopic = articleTopic;
	}
	/**
	 * @return the articleId
	 */
	public int getArticleId() {
		return ArticleId;
	}
	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(int articleId) {
		ArticleId = articleId;
	}
	/**
	 * @return the articleType
	 */
	public String getArticleType() {
		return ArticleType;
	}
	/**
	 * @param articleType the articleType to set
	 */
	public void setArticleType(String articleType) {
		ArticleType = articleType;
	}
	/**
	 * @return the articleHeadLine
	 */
	public String getArticleHeadLine() {
		return ArticleHeadLine;
	}
	/**
	 * @param articleHeadLine the articleHeadLine to set
	 */
	public void setArticleHeadLine(String articleHeadLine) {
		ArticleHeadLine = articleHeadLine;
	}
	/**
	 * @return the articleContent
	 */
	public String getArticleContent() {
		return ArticleContent;
	}
	/**
	 * @param articleContent the articleContent to set
	 */
	public void setArticleContent(String articleContent) {
		ArticleContent = articleContent;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArticleBean [ArticleId=" + ArticleId + ", ArticleTopic=" + ArticleTopic + ", ArticleType=" + ArticleType
				+ ", ArticleHeadLine=" + ArticleHeadLine + ", ArticleContent=" + ArticleContent + "]";
	}

	
}
