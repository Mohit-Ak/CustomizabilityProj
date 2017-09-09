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

import java.io.File;
// [START simple_includes]
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.ub.customizability.beans.ArticleBean;
/**
 * @author mohitakhakharia
 */
@SuppressWarnings("serial")
public class TestServlet extends HttpServlet {
  static String fileName;
  
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//      String type = req.getParameter("type");
      System.out.println("In Post - Sending mail...");
      fileName = req.getSession().getServletContext().getRealPath("/WEB-INF/ServiceAccReal.p12");
      //System.out.println(absolutePathToIndexJSP);
      Sheets service = getSheetsService();
      
      readArticle(req.getParameter("articleId").toString(),service);
      
    
  }
  
  
  /** Application name. */
private static final String APPLICATION_NAME ="Google Customizability Sheets";

/** Directory to store user credentials for this application. */
private static final java.io.File DATA_STORE_DIR = new java.io.File(
   System.getProperty("user.home"), ".credentials/2/sheets.googleapis.com-java-quickstart");

/** Global instance of the {@link FileDataStoreFactory}. */
private static FileDataStoreFactory DATA_STORE_FACTORY;

/** Global instance of the JSON factory. */
private static final JsonFactory JSON_FACTORY =
   JacksonFactory.getDefaultInstance();

/** Global instance of the HTTP transport. */
private static HttpTransport HTTP_TRANSPORT;

/** Global instance of the scopes required by this quickstart.
*
* If modifying these scopes, delete your previously saved credentials
* at ~/.credentials/sheets.googleapis.com-java-quickstart
*/
private static final List<String> SCOPES =
   Arrays.asList(SheetsScopes.SPREADSHEETS,SheetsScopes.DRIVE);

static {
   try {
       HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
       DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
   } catch (Throwable t) {
       t.printStackTrace();
       System.exit(1);
   }
}

public static GoogleCredential authorize() throws IOException {

	//Password-notasecret
	
	try {
		//ServletContext context = getServletContext();
		//String filename = "/WEB-INF/ServiceAccReal.p12";
//		String filename ="src/com/sample/servlet/ServiceAccReal.p12";
		
		GoogleCredential credential = new GoogleCredential.Builder()
               .setTransport(new NetHttpTransport())
               .setJsonFactory(new JacksonFactory())
               .setServiceAccountId("unified-spider-148802@appspot.gserviceaccount.com")
               .setServiceAccountPrivateKeyFromP12File(new File(fileName))
  			 .setServiceAccountScopes(SCOPES).build();
		return credential;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
}

/**
* Build and return an authorized Sheets API client service.
* @return an authorized Sheets API client service
* @throws IOException
*/
public static Sheets getSheetsService() throws IOException {
   Credential credential;
	try {
		credential = authorize();
		 return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
	                .setApplicationName(APPLICATION_NAME)
	                .build();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
  
}

//public static void main(String[] args) throws IOException {
//    // Build a new authorized API client service.
//    Sheets service = getSheetsService();
////
////    // Prints the names and majors of students in a sample spreadsheet:
////    // https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
////    String spreadsheetId = "1iQQTg7XbYUyWLvdfDBatIOZjWyWKlkal9fQg16ET_G0";
////    //old
////    //String spreadsheetId = "18oNJwHVc2mAXD3pPawOcfL9Yf40Hdimnkv5fyCky-OQ";
//    MailServlet sq=new MailServlet();
//    sq.readArticle("3",service);
//}

@SuppressWarnings("rawtypes")
public ArticleBean readArticle(String ArticleId,Sheets service) throws IOException{
	//Sheets service = StaticGoogleSheetService.getSheetsService();
	String spreadsheetId = "1iQQTg7XbYUyWLvdfDBatIOZjWyWKlkal9fQg16ET_G0";
	System.out.println("Article Id in ArticleCommand readArticle "+ArticleId);
	String range = "A"+ArticleId+":C"+ArticleId;
    ValueRange response = service.spreadsheets().values()
        .get(spreadsheetId, range)
        .execute();
    List<List<Object>> values = response.getValues();
    if (values == null || values.size() == 0) {
        System.out.println("No data found.");
    } else {
      System.out.println("ArticleId, Type, Headline, Content");
      ArticleBean ab=new ArticleBean();
      for (List row : values) {
        // Print columns A and E, which correspond to indices 0 and 4.
    	  ab.setArticleId(Integer.parseInt(ArticleId));
    	  ab.setArticleType(row.get(0).toString());
    	  ab.setArticleHeadLine(row.get(1).toString());
    	  ab.setArticleContent(row.get(2).toString());
        System.out.printf("%s,%s, %s\n", row.get(0),row.get(1), row.get(2));
      }
    }
    return null;

}

  
}