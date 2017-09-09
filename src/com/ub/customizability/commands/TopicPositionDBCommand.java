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
public class TopicPositionDBCommand {
	public boolean saveTopicPositionOnHomePage(List<Integer> topicOrder, String credentialPath, String userId) {

		GoogleSheetService gs = new GoogleSheetService();
		Sheets service;
		try {
			System.out.println("In saveTopicPositionOnHomePage - userId :" + userId);
			System.out.println("In saveTopicPositionOnHomePage - topicOrder :");
			System.out.println(topicOrder);
			service = gs.getService(credentialPath);
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			List<Request> requests = new ArrayList<>();
			List<CellData> values1 = new ArrayList<>();
			values1.add(new CellData()
					.setUserEnteredValue(new ExtendedValue().setStringValue(Integer.toString(topicOrder.indexOf(1)+1))));
			String rowIdOfUser = StaticCustomMathUtil.getUserRowId((Integer.parseInt(userId)));
			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(Integer
											.parseInt(StaticAuthenticationConstants.POSOFTOPIC1ONHOMEPAGE)))
							.setRows(Arrays.asList(new RowData().setValues(values1))).setFields("userEnteredValue")));

			List<CellData> values2 = new ArrayList<>();
			values2.add(new CellData().setUserEnteredValue(
					new ExtendedValue().setStringValue(Integer.toString(topicOrder.indexOf(2)+1))));

			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(Integer
											.parseInt(StaticAuthenticationConstants.POSOFTOPIC2ONHOMEPAGE)))
							.setRows(Arrays.asList(new RowData().setValues(values2))).setFields("userEnteredValue")));

			List<CellData> values3 = new ArrayList<>();
			values3.add(new CellData().setUserEnteredValue(
					new ExtendedValue().setStringValue(Integer.toString(topicOrder.indexOf(3)+1))));
			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(Integer
											.parseInt(StaticAuthenticationConstants.POSOFTOPIC3ONHOMEPAGE)))
							.setRows(Arrays.asList(new RowData().setValues(values3))).setFields("userEnteredValue")));
			
			List<CellData> values4 = new ArrayList<>();
			values4.add(new CellData().setUserEnteredValue(
					new ExtendedValue().setStringValue(Integer.toString(topicOrder.indexOf(4)+1))));
			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(Integer
											.parseInt(StaticAuthenticationConstants.POSOFTOPIC4ONHOMEPAGE)))
							.setRows(Arrays.asList(new RowData().setValues(values4))).setFields("userEnteredValue")));

			

			BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest()
					.setRequests(requests);
			service.spreadsheets().batchUpdate(spreadsheetId, batchUpdateRequest).execute();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
	public boolean saveTopicPositionOnSettingsPage(List<Integer> topicOrder, String credentialPath, String userId) {

		GoogleSheetService gs = new GoogleSheetService();
		Sheets service;
		try {
			System.out.println("In saveTopicPositionOnSettingsPage - userId :" + userId);
			System.out.println("In saveTopicPositionOnSettingsPage - topicOrder :");
			System.out.println(topicOrder);
			service = gs.getService(credentialPath);
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			List<Request> requests = new ArrayList<>();
			String rowIdOfUser = StaticCustomMathUtil.getUserRowId((Integer.parseInt(userId)));
			List<CellData> values1 = new ArrayList<>();
			List<CellData> values2 = new ArrayList<>();
			List<CellData> values3 = new ArrayList<>();
			List<CellData> values4 = new ArrayList<>();
			if(topicOrder==null){
				values1.add(new CellData()
						.setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values2.add(new CellData().setUserEnteredValue(
						new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values3.add(new CellData().setUserEnteredValue(
						new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				values4.add(new CellData().setUserEnteredValue(
						new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
				
			}else{
				
				values1.add(new CellData()
						.setUserEnteredValue(new ExtendedValue().setStringValue(Integer.toString(topicOrder.indexOf(1)+1))));
				
				values2.add(new CellData().setUserEnteredValue(
						new ExtendedValue().setStringValue(Integer.toString(topicOrder.indexOf(2)+1))));
				values3.add(new CellData().setUserEnteredValue(
						new ExtendedValue().setStringValue(Integer.toString(topicOrder.indexOf(3)+1))));
				values4.add(new CellData().setUserEnteredValue(
						new ExtendedValue().setStringValue(StaticAuthenticationConstants.NOT_APPLICABLE)));
			}

			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(Integer
											.parseInt(StaticAuthenticationConstants.POSITIONOFTOPIC1ONSETTINGSPAGE)))
							.setRows(Arrays.asList(new RowData().setValues(values1))).setFields("userEnteredValue")));

			
			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(Integer
											.parseInt(StaticAuthenticationConstants.POSITIONOFTOPIC2ONSETTINGSPAGE)))
							.setRows(Arrays.asList(new RowData().setValues(values2))).setFields("userEnteredValue")));

			
			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(Integer
											.parseInt(StaticAuthenticationConstants.POSITIONOFTOPIC3ONSETTINGSPAGE)))
							.setRows(Arrays.asList(new RowData().setValues(values3))).setFields("userEnteredValue")));
			
			
			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(Integer
											.parseInt(StaticAuthenticationConstants.POSITIONOFTOPIC4ONSETTINGSPAGE)))
							.setRows(Arrays.asList(new RowData().setValues(values4))).setFields("userEnteredValue")));

			

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
