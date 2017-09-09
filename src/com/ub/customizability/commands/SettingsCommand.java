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
import com.ub.customizability.beans.UserSettingsBean;
import com.ub.customizability.util.AuthenticationConstants.StaticAuthenticationConstants;
import com.ub.customizability.util.CustomMathUtil.StaticCustomMathUtil;
import com.ub.customizability.util.GoogleSheetService;

/**
 * @author mohitakhakharia
 *
 */
public class SettingsCommand {
	public boolean saveUserSettings(UserSettingsBean userSettingsBean, String credentialPath, String userId) {

		GoogleSheetService gs = new GoogleSheetService();
		Sheets service;
		try {
			System.out.println("In Save user settings - userId :" + userId);
			System.out.println("In Save user settings - userSettingsBean :");
			System.out.println(userSettingsBean);
			service = gs.getService(credentialPath);
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			List<Request> requests = new ArrayList<>();
			
			List<CellData> values1 = new ArrayList<>();
			values1.add(new CellData()
					.setUserEnteredValue(new ExtendedValue().setStringValue(userSettingsBean.getEconomyLiberal())));
			String rowIdOfUser = StaticCustomMathUtil.getUserRowId((Integer.parseInt(userId)));
			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(Integer
											.parseInt(StaticAuthenticationConstants.FINALSETTINGONSCALE1ONTOPIC1)))
							.setRows(Arrays.asList(new RowData().setValues(values1))).setFields("userEnteredValue")));

			List<CellData> values2 = new ArrayList<>();
			values2.add(new CellData().setUserEnteredValue(
					new ExtendedValue().setStringValue(userSettingsBean.getEconomyConservative())));

			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(Integer
											.parseInt(StaticAuthenticationConstants.FINALSETTINGONSCALE2ONTOPIC1)))
							.setRows(Arrays.asList(new RowData().setValues(values2))).setFields("userEnteredValue")));

			List<CellData> values3 = new ArrayList<>();
			values3.add(new CellData().setUserEnteredValue(
					new ExtendedValue().setStringValue(userSettingsBean.getHealthCareLiberal())));
			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(Integer
											.parseInt(StaticAuthenticationConstants.FINALSETTINGONSCALE1ONTOPIC2)))
							.setRows(Arrays.asList(new RowData().setValues(values3))).setFields("userEnteredValue")));

			List<CellData> values4 = new ArrayList<>();
			values4.add(new CellData()
					.setUserEnteredValue(new ExtendedValue().setStringValue(userSettingsBean.getHealthCareConservative())));
			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(Integer
											.parseInt(StaticAuthenticationConstants.FINALSETTINGONSCALE2ONTOPIC2)))
							.setRows(Arrays.asList(new RowData().setValues(values4))).setFields("userEnteredValue")));

			List<CellData> values5 = new ArrayList<>();
			values5.add(new CellData()
					.setUserEnteredValue(new ExtendedValue().setStringValue(userSettingsBean.getCrimeLiberal())));
			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(Integer
											.parseInt(StaticAuthenticationConstants.FINALSETTINGONSCALE1ONTOPIC3)))
							.setRows(Arrays.asList(new RowData().setValues(values5))).setFields("userEnteredValue")));

			List<CellData> values6 = new ArrayList<>();
			values6.add(new CellData().setUserEnteredValue(
					new ExtendedValue().setStringValue(userSettingsBean.getCrimeConservative())));
			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(Integer
											.parseInt(StaticAuthenticationConstants.FINALSETTINGONSCALE2ONTOPIC3)))
							.setRows(Arrays.asList(new RowData().setValues(values6))).setFields("userEnteredValue")));

			BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest()
					.setRequests(requests);
			service.spreadsheets().batchUpdate(spreadsheetId, batchUpdateRequest).execute();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
	// public static void main(String[] args) {
	// GoogleSheetService gs = new GoogleSheetService();
	// Sheets service;
	// try {
	// service =
	// gs.getService("src/com/ub/customizability/util/ServiceAccReal.p12");
	// String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
	// List<Request> requests = new ArrayList<>();
	// List<CellData> values = new ArrayList<>();
	// values.add(new CellData().setUserEnteredValue(new
	// ExtendedValue().setStringValue("test1")));
	// //requests.add(new Request().set)
	// requests.add(new Request().setUpdateCells(new UpdateCellsRequest()
	// .setStart(new
	// GridCoordinate().setSheetId(0).setRowIndex(2).setColumnIndex(143))
	// .setRows(Arrays.asList(new RowData().setValues(values)))
	// .setFields("userEnteredValue")));
	//
	// BatchUpdateSpreadsheetRequest batchUpdateRequest = new
	// BatchUpdateSpreadsheetRequest()
	// .setRequests(requests);
	// service.spreadsheets().batchUpdate(spreadsheetId,
	// batchUpdateRequest).execute();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
}
