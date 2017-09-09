package com.ub.customizability.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.GridCoordinate;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.RowData;
import com.google.api.services.sheets.v4.model.UpdateCellsRequest;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.ub.customizability.beans.ArticleBean;
import com.ub.customizability.util.AuthenticationConstants.StaticAuthenticationConstants;
import com.ub.customizability.util.CustomMathUtil.StaticCustomMathUtil;
import com.ub.customizability.util.GoogleSheetService;

/**
 * @author mohitakhakharia
 *
 */
public class ArticleCommand {

	@SuppressWarnings("rawtypes")
	public ArticleBean getArticle(String ArticleId, String credentialPath) throws IOException {
		GoogleSheetService gs = new GoogleSheetService();
		Sheets service = gs.getService(credentialPath);
		String spreadsheetId = StaticAuthenticationConstants.CONTENTSHEETID;
		System.out.println("Article Id in ArticleCommand readArticle " + ArticleId);
		String range = "A" + ArticleId + ":D" + ArticleId;
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		if (values == null || values.size() == 0) {
			System.out.println("No data found.");
		} else {
			System.out.println("ArticleId, Topic, Type, Headline, Content");
			ArticleBean ab = new ArticleBean();
			for (List row : values) {
				System.out.printf("%s,%s,%s, %s\n", row.get(0), row.get(1), row.get(2), row.get(3));
				ab.setArticleId(Integer.parseInt(ArticleId));
				ab.setArticleTopic(row.get(0).toString());
				ab.setArticleType(row.get(1).toString());
				ab.setArticleHeadLine(row.get(2).toString());
				ab.setArticleContent(row.get(3).toString());
			}
			return ab;
		}
		return null;

	}

	@SuppressWarnings("rawtypes")
	public ArrayList<ArticleBean> getHeadLines(ArrayList<String> headlineIds, String credentialPath) throws IOException {
		GoogleSheetService gs = new GoogleSheetService();
		Sheets service = gs.getService(credentialPath);
		String spreadsheetId = StaticAuthenticationConstants.CONTENTSHEETID;
		
		ArrayList<String> rangeStrings=new ArrayList<String>();
		for(int j=0;j<headlineIds.size();j++){
			String indvidualRange = "A" + headlineIds.get(j) + ":C" + headlineIds.get(j);
			rangeStrings.add(indvidualRange);
		}
		
		ArrayList<ArticleBean> abList = new ArrayList<ArticleBean>();
		BatchGetValuesResponse response = service.spreadsheets().values().batchGet(spreadsheetId).setRanges(rangeStrings)
				.execute();
		List<ValueRange> rangeValuesList = response.getValueRanges();
		for (int i = 0; i < rangeValuesList.size(); i++) {
			List<List<Object>> values = rangeValuesList.get(i).getValues();
			if (values == null || values.size() == 0) {
				System.out.println("No data found.");
			} else {
				ArticleBean ab = new ArticleBean();
				for (List row : values) {
					ab.setArticleId(Integer.parseInt(headlineIds.get(i)));
					ab.setArticleTopic(row.get(0).toString());
					ab.setArticleType(row.get(1).toString());
					ab.setArticleHeadLine(row.get(2).toString());
//					System.out.println(ab);
				}
				abList.add(ab);
			}
		}
		return abList;
	}
	
	public boolean saveTimeOnArticle(Integer articleNo, Integer timeOnArticle, String credentialPath, String userId) {

		GoogleSheetService gs = new GoogleSheetService();
		Sheets service;
		try {
			System.out.println("In saveTimeOnArticle - userId :" + userId);
			service = gs.getService(credentialPath);
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			List<Request> requests = new ArrayList<>();
			List<CellData> values1 = new ArrayList<>();
			articleNo=StaticCustomMathUtil.getTimeOnArticleCol(articleNo);
			values1.add(new CellData()
					.setUserEnteredValue(new ExtendedValue().setStringValue(Integer.toString(timeOnArticle))));
			String rowIdOfUser = StaticCustomMathUtil.getUserRowId((Integer.parseInt(userId)));
			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(articleNo))
							.setRows(Arrays.asList(new RowData().setValues(values1))).setFields("userEnteredValue")));

			BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest()
					.setRequests(requests);
			service.spreadsheets().batchUpdate(spreadsheetId, batchUpdateRequest).execute();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean setShownOrNotArticle(List<String> articleIds,String credentialPath, String userId) {

		GoogleSheetService gs = new GoogleSheetService();
		Sheets service;
		try {
			System.out.println("In setShownOrNotArticle - userId :" + userId);
			service = gs.getService(credentialPath);
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			List<Request> requests = new ArrayList<>();
			
			for(int i=0; i<articleIds.size();i++){
				List<CellData> values = new ArrayList<>();
				Integer articleRowId=StaticCustomMathUtil.getShownOrNotArticleCol(Integer.parseInt(articleIds.get(i)));
				//System.out.println("Aritcle No -"+articleIds.get(i)+" - RowId - "+articleRowId);
				values.add(new CellData()
						.setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.YES)));
				String rowIdOfUser = StaticCustomMathUtil.getUserRowId((Integer.parseInt(userId)));
				requests.add(
						new Request().setUpdateCells(new UpdateCellsRequest()
								.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
										.setColumnIndex(articleRowId))
								.setRows(Arrays.asList(new RowData().setValues(values))).setFields("userEnteredValue")));
	
			}
			
			BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest()
					.setRequests(requests);
			service.spreadsheets().batchUpdate(spreadsheetId, batchUpdateRequest).execute();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean setClickedOrNotArticle(Integer articleNo, String credentialPath, String userId) {

		GoogleSheetService gs = new GoogleSheetService();
		Sheets service;
		try {
			System.out.println("In setClickedOrNotArticle - userId :" + userId);
			service = gs.getService(credentialPath);
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			List<Request> requests = new ArrayList<>();
			List<CellData> values = new ArrayList<>();
			Integer articleRowId=StaticCustomMathUtil.getClickedOrNotArticleCol(articleNo);
			values.add(new CellData()
					.setUserEnteredValue(new ExtendedValue().setStringValue(StaticAuthenticationConstants.YES)));
			String rowIdOfUser = StaticCustomMathUtil.getUserRowId((Integer.parseInt(userId)));
			requests.add(
					new Request().setUpdateCells(new UpdateCellsRequest()
							.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
									.setColumnIndex(articleRowId))
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
	
	public boolean setClickedOrNotArticleBatch(ArrayList<ArticleBean> articleNos, String value, String credentialPath, String userId) {

		GoogleSheetService gs = new GoogleSheetService();
		Sheets service;
		try {
			System.out.println("In setClickedOrNotArticle - userId :" + userId);
			service = gs.getService(credentialPath);
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			List<Request> requests = new ArrayList<>();
			
			String rowIdOfUser = StaticCustomMathUtil.getUserRowId((Integer.parseInt(userId)));
			
			for(ArticleBean articleBean : articleNos){
				
				List<CellData> values = new ArrayList<>();
				Integer articleColId=StaticCustomMathUtil.getClickedOrNotArticleCol(articleBean.getArticleId());
				System.out.println("Article Id -"+articleColId);
				values.add(new CellData()
						.setUserEnteredValue(new ExtendedValue().setStringValue(value)));
				requests.add(
						new Request().setUpdateCells(new UpdateCellsRequest()
								.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser)-1)
										.setColumnIndex(articleColId))
								.setRows(Arrays.asList(new RowData().setValues(values))).setFields("userEnteredValue")));
			}
			
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