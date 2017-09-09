package com.ub.customizability.init;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.GridCoordinate;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.RowData;
import com.google.api.services.sheets.v4.model.UpdateCellsRequest;
import com.ub.customizability.util.AuthenticationConstants.StaticAuthenticationConstants;
import com.ub.customizability.util.GoogleSheetService;
/**
 * @author mohitakhakharia
 * DO NOT RUN THIS
 * This class was a test class which was used to initialize the database with default values. 
 * DO NOT RUN THIS
 */
public class CustomizabilityIniitialize {

	public static void main(String[] args) {
		GoogleSheetService gs = new GoogleSheetService();
		Sheets service;
		try {
			service = gs.getService(StaticAuthenticationConstants.SECRETP12PATH_FOR_JAVASTANDALONE_RUN);
			//Run All or any of the methods to initialize the columns to their default value
			setTimeOnArticleDefault(service,null);
			setPosOnArticleDefault(service,null);
			setShownOrNotDefault(service,null);
			setClickedOrNotDefault(service,null);
			setPositionOfTopicOnSettingsPageDefault(service,null);
			setTimeOnSettingsHomePageDefault(service,null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void setPosOnArticleDefault(Sheets service, String credentialPath){
		try {
			//If being called from MasterResetServlet, the service will be null but will contain the web credential path
			if(service==null){
				GoogleSheetService gs = new GoogleSheetService();
				service = gs.getService(credentialPath);	
			}
			
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			List<Request> requests = new ArrayList<>();

			for (int i = 2; i <= 2002; i++) {
				List<CellData> values = new ArrayList<>();
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));

				requests.add(new Request().setUpdateCells(new UpdateCellsRequest()
						.setStart(new GridCoordinate().setSheetId(0).setRowIndex(i-1).setColumnIndex(38))
						.setRows(Arrays.asList(new RowData().setValues(values))).setFields("userEnteredValue")));
			}

			BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest()
					.setRequests(requests);
			service.spreadsheets().batchUpdate(spreadsheetId, batchUpdateRequest).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setTimeOnArticleDefault(Sheets service, String credentialPath){
		try {
			//If being called from MasterResetServlet, the service will be null but will contain the web credential path
			if(service==null){
				GoogleSheetService gs = new GoogleSheetService();
				service = gs.getService(credentialPath);	
			}
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			List<Request> requests = new ArrayList<>();

			for (int i = 2; i <= 2002; i++) {
				List<CellData> values = new ArrayList<>();
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.ZERO)));

				requests.add(new Request().setUpdateCells(new UpdateCellsRequest()
						.setStart(new GridCoordinate().setSheetId(0).setRowIndex(i-1).setColumnIndex(6))
						.setRows(Arrays.asList(new RowData().setValues(values))).setFields("userEnteredValue")));
			}

			BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest()
					.setRequests(requests);
			service.spreadsheets().batchUpdate(spreadsheetId, batchUpdateRequest).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Shown or Not
	public static void setShownOrNotDefault(Sheets service, String credentialPath){
		try {
			//If being called from MasterResetServlet, the service will be null but will contain the web credential path
			if(service==null){
				GoogleSheetService gs = new GoogleSheetService();
				service = gs.getService(credentialPath);	
			}
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			List<Request> requests = new ArrayList<>();

			for (int i = 2; i <= 2002; i++) {
				List<CellData> values = new ArrayList<>();
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));

				requests.add(new Request().setUpdateCells(new UpdateCellsRequest()
						.setStart(new GridCoordinate().setSheetId(0).setRowIndex(i-1).setColumnIndex(78))
						.setRows(Arrays.asList(new RowData().setValues(values))).setFields("userEnteredValue")));
			}

			BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest()
					.setRequests(requests);
			service.spreadsheets().batchUpdate(spreadsheetId, batchUpdateRequest).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setClickedOrNotDefault(Sheets service, String credentialPath){
		try {
			//If being called from MasterResetServlet, the service will be null but will contain the web credential path
			if(service==null){
				GoogleSheetService gs = new GoogleSheetService();
				service = gs.getService(credentialPath);	
			}
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			List<Request> requests = new ArrayList<>();

			for (int i = 2; i <= 2002; i++) {
				List<CellData> values = new ArrayList<>();
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NO)));

				requests.add(new Request().setUpdateCells(new UpdateCellsRequest()
						.setStart(new GridCoordinate().setSheetId(0).setRowIndex(i-1).setColumnIndex(110))
						.setRows(Arrays.asList(new RowData().setValues(values))).setFields("userEnteredValue")));
			}

			BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest()
					.setRequests(requests);
			service.spreadsheets().batchUpdate(spreadsheetId, batchUpdateRequest).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setPositionOfTopicOnSettingsPageDefault(Sheets service, String credentialPath){
		try {
			//If being called from MasterResetServlet, the service will be null but will contain the web credential path
			if(service==null){
				GoogleSheetService gs = new GoogleSheetService();
				service = gs.getService(credentialPath);	
			}
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			List<Request> requests = new ArrayList<>();

			for (int i = 2; i <= 2002; i++) {
				List<CellData> values = new ArrayList<>();
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));

				requests.add(new Request().setUpdateCells(new UpdateCellsRequest()
						.setStart(new GridCoordinate().setSheetId(0).setRowIndex(i-1).setColumnIndex(74))
						.setRows(Arrays.asList(new RowData().setValues(values))).setFields("userEnteredValue")));
			}

			BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest()
					.setRequests(requests);
			service.spreadsheets().batchUpdate(spreadsheetId, batchUpdateRequest).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setTimeOnSettingsHomePageDefault(Sheets service, String credentialPath){
		try {
			//If being called from MasterResetServlet, the service will be null but will contain the web credential path
			if(service==null){
				GoogleSheetService gs = new GoogleSheetService();
				service = gs.getService(credentialPath);	
			}
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			List<Request> requests = new ArrayList<>();

			for (int i = 2; i <= 2002; i++) {
				List<CellData> values = new ArrayList<>();
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue("0")));
				values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue("0")));
				

				requests.add(new Request().setUpdateCells(new UpdateCellsRequest()
						.setStart(new GridCoordinate().setSheetId(0).setRowIndex(i-1).setColumnIndex(4))
						.setRows(Arrays.asList(new RowData().setValues(values))).setFields("userEnteredValue")));
			}

			BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest()
					.setRequests(requests);
			service.spreadsheets().batchUpdate(spreadsheetId, batchUpdateRequest).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
