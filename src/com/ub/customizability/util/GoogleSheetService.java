package com.ub.customizability.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
/**
 * @author mohitakhakharia
 */
public class GoogleSheetService {

	static String fileName;

	public Sheets getService(String credentialPath) throws IOException {
		fileName = credentialPath;

		try {
			Sheets service = getSheetsService();
			System.out.println(service);
			return service;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/** Application name. */
	private static final String APPLICATION_NAME = "Google Customizability Sheets";

	/** Directory to store user credentials for this application. */
	// private static final java.io.File DATA_STORE_DIR = new java.io.File(
	// System.getProperty("user.home"),
	// ".credentials/2/sheets.googleapis.com-java-quickstart");

	/// ** Global instance of the {@link FileDataStoreFactory}. */
	// private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/**
	 * Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials at
	 * ~/.credentials/sheets.googleapis.com-java-quickstart
	 */
	private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS, SheetsScopes.DRIVE);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			// DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static GoogleCredential authorize() throws IOException {

		// Password-notasecret

		try {
			// ServletContext context = getServletContext();
			// String filename = "/WEB-INF/ServiceAccReal.p12";
			// String filename ="src/com/sample/servlet/ServiceAccReal.p12";

			GoogleCredential credential = new GoogleCredential.Builder().setTransport(new NetHttpTransport())
					.setJsonFactory(new JacksonFactory())
					.setServiceAccountId("unified-spider-148802@appspot.gserviceaccount.com")
					.setServiceAccountPrivateKeyFromP12File(new File(fileName)).setServiceAccountScopes(SCOPES).build();
			return credential;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Build and return an authorized Sheets API client service.
	 * 
	 * @return an authorized Sheets API client service
	 * @throws IOException
	 */
	public static Sheets getSheetsService() throws IOException {
		Credential credential;
		try {
			credential = authorize();
			return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME)
					.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}


}