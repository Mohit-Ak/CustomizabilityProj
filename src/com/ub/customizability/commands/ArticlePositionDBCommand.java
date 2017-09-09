package com.ub.customizability.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.GridCoordinate;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.RowData;
import com.google.api.services.sheets.v4.model.UpdateCellsRequest;
import com.ub.customizability.beans.ArticleBean;
import com.ub.customizability.util.AuthenticationConstants.StaticAuthenticationConstants;
import com.ub.customizability.util.CustomMathUtil.StaticCustomMathUtil;
import com.ub.customizability.util.GoogleSheetService;
/**
 * @author mohitakhakharia
 *
 */
public class ArticlePositionDBCommand {
	public boolean saveArticlePositionOnHomePage(ArrayList<ArticleBean> abList, List<Integer> topicsorderList,
			String credentialPath, String userId) {

		GoogleSheetService gs = new GoogleSheetService();
		Sheets service;
		try {
			System.out.println("In saveArticlePositionOnHomePage - userId :" + userId);
			service = gs.getService(credentialPath);
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			List<Request> requests = new ArrayList<>();
			List<CellData> values;
			List<Integer> topicsorderListDBRevised=new ArrayList<Integer>();
			topicsorderListDBRevised.add(topicsorderList.indexOf(1));
			topicsorderListDBRevised.add(topicsorderList.indexOf(2));
			topicsorderListDBRevised.add(topicsorderList.indexOf(3));
			topicsorderListDBRevised.add(topicsorderList.indexOf(4));
			System.out.println("In saveArticlePositionOnHomePage - topicsorderListDBRevised :"+topicsorderListDBRevised);

			Map<String, String> articleOrderMap = getArticlePosMap(abList, topicsorderListDBRevised);
			for (Map.Entry<String, String> articlePosBean : articleOrderMap.entrySet()) {
				//System.out.println(articlePosBean.getKey() + "/" + articlePosBean.getValue());
				values = new ArrayList<>();
				String tempPos=Integer.toString(Integer.parseInt(articlePosBean.getValue())+1);
				values.add(new CellData()
						.setUserEnteredValue(new ExtendedValue().setStringValue(tempPos)));
				String rowIdOfUser = StaticCustomMathUtil.getUserRowId((Integer.parseInt(userId)));
				requests.add(new Request().setUpdateCells(new UpdateCellsRequest()
						.setStart(new GridCoordinate().setSheetId(0).setRowIndex(Integer.parseInt(rowIdOfUser) - 1)
								.setColumnIndex(StaticCustomMathUtil.getPosOfArticleCol(Integer.parseInt(articlePosBean.getKey()))))
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

	public Map<String, String> getArticlePosMap(ArrayList<ArticleBean> abList, List<Integer> topicsorderList) {
		Map<String, String> outputMap=new HashMap<String, String>();
		List<String> economyList=new ArrayList<String>();
		List<String> healthCareList=new ArrayList<String>();
		List<String> crimeList=new ArrayList<String>();
		List<String> entertainmentList=new ArrayList<String>();
		
		Integer economyAdder=0;
		Integer healthCareAdder=0;
		Integer crimeAdder=0;
		Integer entertainmentAdder=0;
		
		for(ArticleBean ab : abList){
			if(ab.getArticleTopic().equalsIgnoreCase(StaticAuthenticationConstants.ECONOMY)){
				economyList.add(Integer.toString(ab.getArticleId()));
			}else if(ab.getArticleTopic().equalsIgnoreCase(StaticAuthenticationConstants.HEALTHCARE)){
				healthCareList.add(Integer.toString(ab.getArticleId()));
			}else if(ab.getArticleTopic().equalsIgnoreCase(StaticAuthenticationConstants.CRIME)){
				crimeList.add(Integer.toString(ab.getArticleId()));
			}else{
				entertainmentList.add(Integer.toString(ab.getArticleId()));
			}
		}
		

		// Economy Adder
		if(topicsorderList.get(0)==0){
			economyAdder=0;
		}else if(topicsorderList.get(0)==1){
			Integer firstList=topicsorderList.indexOf(0);
			if(firstList==1){
				economyAdder=healthCareList.size();
			}else if(firstList==2){
				economyAdder=crimeList.size();
			}else{
				economyAdder=entertainmentList.size();
			}
		}
		else if(topicsorderList.get(0)==2){
			Integer firstList=topicsorderList.indexOf(0);
			Integer secondList=topicsorderList.indexOf(1);
			
			if((firstList==1 & secondList==2) || (firstList==2 & secondList==1)){
				economyAdder=healthCareList.size()+crimeList.size();
			}else if((firstList==1 & secondList==3) || (firstList==3 & secondList==1)){
				economyAdder=healthCareList.size()+entertainmentList.size();
			}else if((firstList==2 & secondList==3) || (firstList==3 & secondList==2)){
				economyAdder=entertainmentList.size()+crimeList.size();
			}
		}else if(topicsorderList.get(0)==3){
			economyAdder=healthCareList.size()+crimeList.size()+entertainmentList.size();
		}
		
		// Healthcare Adder		
		if(topicsorderList.get(1)==0){
			healthCareAdder=0;
		}else if(topicsorderList.get(1)==1){
			Integer firstList=topicsorderList.indexOf(0);
			
			if(firstList==0){
				healthCareAdder=economyList.size();
			}else if(firstList==2){
				healthCareAdder=crimeList.size();
			}else{
				healthCareAdder=entertainmentList.size();
			}
		}
		else if(topicsorderList.get(1)==2){
			Integer firstList=topicsorderList.indexOf(0);
			Integer secondList=topicsorderList.indexOf(1);
			
			if((firstList==0 & secondList==2) || (firstList==2 & secondList==0)){
				healthCareAdder=economyList.size()+crimeList.size();
			}else if((firstList==0 & secondList==3) || (firstList==3 & secondList==0)){
				healthCareAdder=economyList.size()+entertainmentList.size();
			}else if((firstList==2 & secondList==3) || (firstList==3 & secondList==2)){
				healthCareAdder=crimeList.size()+entertainmentList.size();
			}
		}else if(topicsorderList.get(1)==3){
			healthCareAdder=economyList.size()+crimeList.size()+entertainmentList.size();
		}
		
		// crimeAdder Adder
		if(topicsorderList.get(2)==0){
			crimeAdder=0;
		}else if(topicsorderList.get(2)==1){
			Integer firstList=topicsorderList.indexOf(0);
			if(firstList==0){
				crimeAdder=economyList.size();
			}else if(firstList==1){
				crimeAdder=healthCareList.size();
			}else{
				crimeAdder=entertainmentList.size();
			}
		}else if(topicsorderList.get(2)==2){
			Integer firstList=topicsorderList.indexOf(0);
			Integer secondList=topicsorderList.indexOf(1);
			
			if((firstList==0 & secondList==1) || (firstList==1 & secondList==0)){
				crimeAdder=economyList.size()+healthCareList.size();
			}else if((firstList==1 & secondList==3) || (firstList==3 & secondList==1)){
				crimeAdder=healthCareList.size()+entertainmentList.size();
			}else if((firstList==0 & secondList==3) || (firstList==3 & secondList==0)){
				crimeAdder=economyList.size()+entertainmentList.size();
			}
		}else if(topicsorderList.get(2)==3){
			crimeAdder=economyList.size()+healthCareList.size()+entertainmentList.size();
		}
		
		// entertainmentAdder
		if(topicsorderList.get(3)==0){
			entertainmentAdder=0;
		}else if(topicsorderList.get(3)==1){
			Integer firstList=topicsorderList.indexOf(0);
			if(firstList==0){
				entertainmentAdder=economyList.size();
			}else if(firstList==1){
				entertainmentAdder=healthCareList.size();
			}else{
				entertainmentAdder=crimeList.size();
			}
		}else if(topicsorderList.get(3)==2){
			Integer firstList=topicsorderList.indexOf(0);
			Integer secondList=topicsorderList.indexOf(1);
			
			if((firstList==0 & secondList==1) || (firstList==1 & secondList==0)){
				entertainmentAdder=economyList.size()+healthCareList.size();
			}else if((firstList==1 & secondList==2) || (firstList==2 & secondList==1)){
				entertainmentAdder=healthCareList.size()+crimeList.size();
			}else if((firstList==0 & secondList==2) || (firstList==2 & secondList==0)){
				entertainmentAdder=economyList.size()+crimeList.size();
			}
		}
		else if(topicsorderList.get(3)==3){
			entertainmentAdder=economyList.size()+healthCareList.size()+crimeList.size();
		}
		/////////
		
		for(String eObject : economyList){
			outputMap.put(eObject,Integer.toString(economyList.indexOf(eObject)+economyAdder));
		}
		for(String hObject : healthCareList){
			outputMap.put(hObject,Integer.toString(healthCareList.indexOf(hObject)+healthCareAdder));
		}
		for(String cObject : crimeList){
			outputMap.put(cObject,Integer.toString(crimeList.indexOf(cObject)+crimeAdder));
		}
		for(String enObject : entertainmentList){
			outputMap.put(enObject,Integer.toString(entertainmentList.indexOf(enObject)+entertainmentAdder));
		}
		return outputMap;
	}
}
