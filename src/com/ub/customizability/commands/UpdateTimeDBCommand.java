package com.ub.customizability.commands;

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
import com.google.api.services.sheets.v4.model.ValueRange;
import com.ub.customizability.util.AuthenticationConstants.StaticAuthenticationConstants;
import com.ub.customizability.util.CustomMathUtil.StaticCustomMathUtil;
import com.ub.customizability.util.GoogleSheetService;
/**
 * @author mohitakhakharia
 *
 */
public class UpdateTimeDBCommand {
	public boolean updateTime(Integer newTimeValue, String columnNo, String userId, String credentialPath) {

		GoogleSheetService gs = new GoogleSheetService();
		Sheets service;
		try {
			System.out.println("In updateTime - userId :" + userId);
			service = gs.getService(credentialPath);
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			String existingTime=getExistingTime(columnNo,userId,credentialPath);
			Integer totalTime=Integer.parseInt(existingTime)+newTimeValue;
			List<Request> requests = new ArrayList<>();
			List<CellData> values = new ArrayList<>();
			values.add(new CellData()
					.setUserEnteredValue(new ExtendedValue().setStringValue(totalTime.toString())));
			String rowIdOfUser = StaticCustomMathUtil.getUserRowId((Integer.parseInt(userId)));
			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(Integer.parseInt(columnNo)))
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
	
	@SuppressWarnings("rawtypes")
	private String getExistingTime(String columnNo, String userId, String credentialPath) throws IOException {
		System.out.println("In getExistingTime");
		GoogleSheetService gs = new GoogleSheetService();
		Sheets service = gs.getService(credentialPath);
		String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
		String rowIdOfUser = StaticCustomMathUtil.getUserRowId((Integer.parseInt(userId)));
		String colName=StaticCustomMathUtil.getExcelColumnName(Integer.parseInt(columnNo)+1);
		String range = colName + rowIdOfUser;
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		
		List<List<Object>> values = response.getValues();
		String existingTime="";
		if (values == null || values.size() == 0) {
			System.out.println("No data found.");
		} else {
			for (List row : values) {
				System.out.printf("%s, The exisitng time returned -\n", row.get(0));
				existingTime=row.get(0).toString();
			}
			return existingTime;
		}
		return null;

	}
	
}
