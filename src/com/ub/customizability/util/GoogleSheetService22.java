/**
 * 
 */
package com.ub.customizability.util;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.ub.customizability.util.AuthenticationConstants.StaticAuthenticationConstants;

/**
 * @author mohitakhakharia
 *
 */
public class GoogleSheetService22 {
	

public static class StaticGoogleSheetService {
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

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in = GoogleSheetService22.class.getResourceAsStream(StaticAuthenticationConstants.SECRETJSONPATH);
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setApprovalPrompt("force").setAccessType("offline")
                .build();
        
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }
    
//    public static GoogleCredential authorize() throws IOException {
//        // Load client secrets.
////    	GoogleCredential credential;
////    	String curDir = System.getProperty("user.dir");
////    	File GradeList = new File("ServiceAccReal.json");
////    	System.out.println("Current sys dir: " + curDir);
////    	System.out.println("Current abs dir: " + GradeList.getAbsolutePath());
////    	BufferedReader br = new BufferedReader(new FileReader("ServiceAccReal.json"));
////    	try {
////    	    StringBuilder sb = new StringBuilder();
////    	    String line = br.readLine();
////
////    	    while (line != null) {
////    	        sb.append(line);
////    	        sb.append(System.lineSeparator());
////    	        line = br.readLine();
////    	        System.out.println("Inside WHile");
////    	    }
////    	    String everything = sb.toString();
////    	} finally {
////    	    br.close();
////    	}
//    	//InputStream is = new FileInputStream(ServletActionContext.getServletContext().getRealPath("file.txt"));
////    	System.out.println(ServletActionContext.getServletContext().getRealPath("/WEB-INF/ServiceAccReal.json"));
////    	
////    	InputStream is = new FileInputStream(ServletActionContext.getServletContext().getRealPath("/WEB-INF/ServiceAccReal.json"));    	
////    	credential= GoogleCredential.fromStream(is).createScoped(SCOPES);
////    	return credential;
//    	
//    	//Password-notasecret
//    	
//		try {
////			URI keyUri = StaticGoogleSheetService.class.getClassLoader().getResource("ServiceAccReal.p12").toURI();
////			GoogleCredential credential;
////			credential = new GoogleCredential.Builder()
////				    .setTransport(new NetHttpTransport())
////				    .setJsonFactory(new JacksonFactory())
////				    .setServiceAccountId("unified-spider-148802@appspot.gserviceaccount.com")
//////			    .setServiceAccountPrivateKey(privateKey)
////				    .setServiceAccountPrivateKeyFromP12File(new File(keyUri))
////				    .setServiceAccountScopes(SCOPES)
////				    .setServiceAccountUser("ubpolcomresearch@gmail.com")
////				    .build();
////			return credential;
//
////			GoogleCredential credential = new GoogleCredential.Builder()
////	                .setTransport(new NetHttpTransport())
////	                .setJsonFactory(new JacksonFactory())
////	                .setServiceAccountId("unified-spider-148802@appspot.gserviceaccount.com")
////	                .setServiceAccountPrivateKeyFromP12File(new File(ServletActionContext.getServletContext().getRealPath("/WEB-INF/ServiceAccReal.p12")))
////	                .setServiceAccountScopes(SCOPES).build();
////			return credential;
//			GoogleCredential credential = new GoogleCredential.Builder()
//	                .setTransport(new NetHttpTransport())
//	                .setJsonFactory(new JacksonFactory())
//	                .setServiceAccountId("unified-spider-148802@appspot.gserviceaccount.com")
//	   			 .setServiceAccountPrivateKeyFromP12File(
//	                     new File("src/com/ub/customizability/util/ServiceAccReal.p12"))
//	   			 .setServiceAccountScopes(SCOPES).build();
//			return credential;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//		
//		
//
//    }
//
//    
//    public static Credential authorize() throws Exception {
////		byte[] keyBytes = "-----BEGIN PRIVATE KEY-----\nMIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJnC8OW2y6R1x9sl\nRUKSLBzInZ6E+1+UaCYoP4LCptYeOcq9m60CpjgpOoa6XlNCl346G69iPET8toT+\nK03mlRG8XUQmGekvQuh9TP/WkJddsSGkp0Di5S5O7BrjVoh5b16cGW/gjbd7iMpE\n9k/oluKLXWellBWT0lIieMUUh7+DAgMBAAECgYBwIOjrm9IFrJ6CATvwwAfLNcE7\nyF5H5UHOEau+/5QKsSAT6RGNqVDtw6MafO4saXNwaY+m5kxtQp0XfwK1XGF33Clr\njTz8rMvWeq66L6eE96p/RcWIkc26uaURsJJBMzoHrlzoKz5S7Qyj4oY8vs2CKXLc\nO/tj+6uiQFxFHQGIAQJBAMpMqsJ5D7SiZ2ySdqT8nVqvz4y8ys12CWp2GQhHLIc8\nC2mUH4/gEEMp0p0ujt0R/CnlWsiUpWo3WcEgdkt0wUMCQQDCk9uEfkHmkb7S0mvk\nLXLyCdqenjFlGxYcmUHC3sFOL+yQV8UAVpZGg7rPPGbxPRL+4bGYDOTawlHgn9eF\nPwTBAkEAiWJVmSqUAu1D5ZT15Af2aO76ua5A4BWoQVegmmGTmOerLHE+mfvlr2cI\ntFw5A2NCZ8VtyMhCKgca+jAshgPtdwJASAb7x7wFIRjqjpgeIEVCu54yM21T052H\nPebtlPlojUzoQTZ8Une7ATdaO8ScRsjY80kQuLxtKx2ruX7yBP0jAQJAFzASBb5j\nZNNvHawlUEPw/Mgrw97S4owKO5M3BFGTE90Kj8OWH9ga1TZ5/ZSFxn6QYFQdUzH0\ni3Ghd+dUpcFetw\u003d\u003d\n-----END PRIVATE KEY-----\n".getBytes();
////		PrivateKey privateKey = SecurityUtils.getRsaKeyFactory().generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
//
//		URI keyUri = StaticGoogleSheetService.class.getClassLoader().getResource("TestProjectThingAndStuff-6de8fea199c1.p12").toURI();
//		GoogleCredential credential = new GoogleCredential.Builder()
//			    .setTransport(new NetHttpTransport())
//			    .setJsonFactory(new JacksonFactory())
//			    .setServiceAccountId("unified-spider-148802@appspot.gserviceaccount.com")
////			    .setServiceAccountPrivateKey(privateKey)
//			    .setServiceAccountPrivateKeyFromP12File(new File(keyUri))
//			    .setServiceAccountScopes(SCOPES)
//			    .setServiceAccountUser("mcmathe1@asu.edu")
//			    .build();
//		
//		return credential;
//	}
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
}
}