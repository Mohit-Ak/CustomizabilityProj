/**
 * 
 */
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
import com.ub.customizability.util.AuthenticationConstants.StaticAuthenticationConstants;
import com.ub.customizability.util.CustomMathUtil.StaticCustomMathUtil;
import com.ub.customizability.util.GoogleSheetService;

/**
 * @author mohitakhakharia
 *
 */
public class LogoutCommand {
	
	public boolean setLoggedOut(String userId, String credentialPath){
		GoogleSheetService gs = new GoogleSheetService();
		Sheets service;
		try {
			System.out.println("In setLoggedOut");
			service = gs.getService(credentialPath);
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			List<Request> requests = new ArrayList<>();
			List<CellData> values = new ArrayList<>();
			
			
			values.add(new CellData()
					.setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.YES)));
			String rowIdOfUser = StaticCustomMathUtil.getUserRowId((Integer.parseInt(userId)));
			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(Integer
											.parseInt(StaticAuthenticationConstants.LOGGEDOUT_SUCCESSFULLY)))
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
