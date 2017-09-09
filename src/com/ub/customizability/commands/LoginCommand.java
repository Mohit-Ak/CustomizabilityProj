/**
 * 
 */
package com.ub.customizability.commands;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.GridCoordinate;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.RowData;
import com.google.api.services.sheets.v4.model.UpdateCellsRequest;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.ub.customizability.util.AuthenticationConstants.StaticAuthenticationConstants;
import com.ub.customizability.util.CustomMathUtil.StaticCustomMathUtil;
import com.ub.customizability.util.GoogleSheetService;

/**
 * @author mohitakhakharia
 *
 */
public class LoginCommand {
	public String isUserAuthenticated(String userId, String password, String credentialPath){
		String rowId=StaticCustomMathUtil.getUserRowId(Integer.parseInt(userId));
		
		GoogleSheetService gs = new GoogleSheetService();
		try {
			Sheets service = gs.getService(credentialPath);
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			String range = "A" + rowId + ":D" + rowId;
			ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
			List<List<Object>> values = response.getValues();
			if (values == null || values.size() == 0) {
				System.out.println("No data found.");
			} else {
				for (@SuppressWarnings("rawtypes") List row : values) {
					System.out.printf("%s,%s\n", row.get(0), row.get(1), row.get(2), row.get(3));
					if(password.equals(row.get(1).toString()) && row.get(2).toString().equals("")){
						setLoggedInTime(userId, credentialPath);
						return row.get(3).toString();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private boolean setLoggedInTime(String userId, String credentialPath){
		GoogleSheetService gs = new GoogleSheetService();
		Sheets service;
		try {
			System.out.println("In setLoggedInTime");
			service = gs.getService(credentialPath);
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			List<Request> requests = new ArrayList<>();
			List<CellData> values = new ArrayList<>();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			
			values.add(new CellData()
					.setUserEnteredValue(new ExtendedValue().setStringValue(dateFormat.format(date))));
			String rowIdOfUser = StaticCustomMathUtil.getUserRowId((Integer.parseInt(userId)));
			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(Integer
											.parseInt(StaticAuthenticationConstants.PARTICIPATION_DATE)))
							.setRows(Arrays.asList(new RowData().setValues(values))).setFields("userEnteredValue")));
			BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest()
					.setRequests(requests);
			service.spreadsheets().batchUpdate(spreadsheetId, batchUpdateRequest).execute();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
