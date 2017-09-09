package com.ub.customizability.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.gwt.thirdparty.guava.common.base.Functions;
import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.ub.customizability.beans.NoOfArticlesBean;
import com.ub.customizability.beans.UserSettingsBean;
import com.ub.customizability.util.GoogleSheetService;
import com.ub.customizability.util.AuthenticationConstants.StaticAuthenticationConstants;
import com.ub.customizability.util.CustomMathUtil.StaticCustomMathUtil;

/**
 * @author mohitakhakharia
 *
 */
public class HomeCommand {

	public ArrayList<String> getHeadLineIds(String userId, String userType, String credentialPath) {
		if(userType.equalsIgnoreCase(StaticAuthenticationConstants.USER_TYPE1_TR)){
			return getHeadLineIdsForType1(userId, userType, credentialPath);
		}else if(userType.equalsIgnoreCase(StaticAuthenticationConstants.USER_TYPE2_USR)){
			return getHeadLineIdsForType2(userId,credentialPath);
		}else if(userType.equalsIgnoreCase(StaticAuthenticationConstants.USER_TYPE3_RESRCH)){
			return getHeadLineIdsForType3(userId,credentialPath);
		}else if(userType.equalsIgnoreCase(StaticAuthenticationConstants.USER_TYPE4_USROVR)){
			return getHeadLineIdsForType4(userId,credentialPath);
		}else{
			return null;
		}
	}
	
	private ArrayList<String> getHeadLineIdsForType1(String userId, String userType, String credentialPath) {
		ArrayList<String> headlineIds = new ArrayList<String>();
		NoOfArticlesBean noab=StaticCustomMathUtil.getNoOfArticlesForAllTopics();
		//Saving Settings for type 1 even though they don't exist.
		UserSettingsBean usb=new UserSettingsBean();
		usb.setEconomyLiberal(Integer.toString(noab.getNoOfEconomyLiberal()));
		usb.setEconomyConservative(Integer.toString(noab.getNoOfEconomyConservative()));
		usb.setHealthCareLiberal(Integer.toString(noab.getNoOfHealthCareLiberal()));
		usb.setHealthCareConservative(Integer.toString(noab.getNoOfHealthCareConservative()));
		usb.setCrimeLiberal(Integer.toString(noab.getNoOfCrimeLiberal()));
		usb.setCrimeConservative(Integer.toString(noab.getNoOfCrimeConservative()));
		SettingsCommand sc = new SettingsCommand();
		sc.saveUserSettings(usb, credentialPath, userId);
		
		headlineIds=getHeadLineIdsProvidedNumberOfArticles(noab);
		return headlineIds;
	}

	private ArrayList<String> getHeadLineIdsForType2(String userId, String credentialPath) {
		
		ArrayList<String> headlineIds = new ArrayList<String>();
		NoOfArticlesBean noab=getNoOfArticlesForAllTopicsFromDB(userId,credentialPath);
		headlineIds=getHeadLineIdsProvidedNumberOfArticles(noab);
		return headlineIds;
	}

	private ArrayList<String> getHeadLineIdsForType3(String userId, String credentialPath) {
		ArrayList<String> headlineIds = new ArrayList<String>();
		NoOfArticlesBean noab=getNoOfArticlesForAllTopicsFromDB(userId,credentialPath);
		headlineIds=getHeadLineIdsProvidedNumberOfArticles(noab);
		return headlineIds;
	}
	private ArrayList<String> getHeadLineIdsForType4(String userId, String credentialPath) {
		ArrayList<String> headlineIds = new ArrayList<String>();
		NoOfArticlesBean noab=getNoOfArticlesForAllTopicsFromDB(userId,credentialPath);
		headlineIds=getHeadLineIdsProvidedNumberOfArticles(noab);
		return headlineIds;
	}
	public NoOfArticlesBean getNoOfArticlesForAllTopicsFromDB(String userId, String credentialPath) {
		String rowId=StaticCustomMathUtil.getUserRowId(Integer.parseInt(userId));
		GoogleSheetService gs = new GoogleSheetService();
		try {
			Sheets service = gs.getService(credentialPath);
			String spreadsheetId = StaticAuthenticationConstants.USERSHEETID;
			String range = "EM" + rowId + ":ER" + rowId;
			NoOfArticlesBean noab=new NoOfArticlesBean();
			ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
			List<List<Object>> values = response.getValues();
			if (values == null || values.size() == 0) {
				System.out.println("No data found.");
			} else {
				for (@SuppressWarnings("rawtypes") List row : values) {
					System.out.printf("getNoOfArticlesForAllTopicsFromDB \n", row.get(0), row.get(1), row.get(2), row.get(3),row.get(4),row.get(5));
					noab.setNoOfEconomyLiberal(Integer.parseInt(row.get(0).toString()));
					noab.setNoOfEconomyConservative(Integer.parseInt(row.get(1).toString()));
					noab.setNoOfHealthCareLiberal(Integer.parseInt(row.get(2).toString()));
					noab.setNoOfHealthCareConservative(Integer.parseInt(row.get(3).toString()));
					noab.setNoOfCrimeLiberal(Integer.parseInt(row.get(4).toString()));
					noab.setNoOfCrimeConservative(Integer.parseInt(row.get(5).toString()));
				}
				return noab;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
	private ArrayList<String> getHeadLineIdsProvidedNumberOfArticles(NoOfArticlesBean noab){
		ArrayList<String> headlineIds = new ArrayList<String>();
		List<Integer> tempList;
		//Getting Economy Liberal Articles, Shuffling them and adding them to eList
		List<Integer> eList;
		int elcount=noab.getNoOfEconomyLiberal();
		Integer[] elArray = StaticAuthenticationConstants.ECONOMY_LIBERAL;
		Collections.shuffle(Arrays.asList(elArray));
		elArray= Arrays.copyOfRange(elArray, 0,elcount);
		eList=new ArrayList<Integer>(Arrays.asList(elArray));

		//Getting Economy Conservative Articles and Shuffling them. Adding conservative and neutral 
		//articles to eList and shuffling the list ultimately.
		int eccount=noab.getNoOfEconomyConservative();
		Integer[] ecArray = StaticAuthenticationConstants.ECONOMY_CONSERVATIVE;
		Collections.shuffle(Arrays.asList(ecArray));
		ecArray= Arrays.copyOfRange(ecArray, 0,eccount);
		tempList=new ArrayList<Integer>(Arrays.asList(ecArray));
		eList.addAll(tempList);
		eList.add(Integer.parseInt(StaticAuthenticationConstants.ECONOMY_NEUTRAL_1));
		eList.add(Integer.parseInt(StaticAuthenticationConstants.ECONOMY_NEUTRAL_2));
		Collections.shuffle(eList);
		
		
		//Getting Healthcare Liberal Articles, Shuffling them and adding them to hList
		List<Integer> hList=new ArrayList<Integer>();
		int hlcount=noab.getNoOfHealthCareLiberal();
		Integer[] hlArray = StaticAuthenticationConstants.HEALTHCARE_LIBERAL;
		Collections.shuffle(Arrays.asList(hlArray));
		hlArray= Arrays.copyOfRange(hlArray, 0,hlcount);
		hList=new ArrayList<Integer>(Arrays.asList(hlArray));
		
		//Getting Healthcare Conservative Article and Shuffling them. Adding conservative and neutral 
		//articles to hList and shuffling the list ultimately.
		int hccount=noab.getNoOfHealthCareConservative();
		Integer[] hcArray = StaticAuthenticationConstants.HEALTHCARE_CONSERVATIVE;
		Collections.shuffle(Arrays.asList(hcArray));
		hcArray= Arrays.copyOfRange(hcArray, 0,hccount);
		tempList=new ArrayList<Integer>(Arrays.asList(hcArray));
		hList.addAll(tempList);
		hList.add(Integer.parseInt(StaticAuthenticationConstants.HEALTHCARE_NEUTRAL_1));
		hList.add(Integer.parseInt(StaticAuthenticationConstants.HEALTHCARE_NEUTRAL_2));
		Collections.shuffle(hList);
		
		//Getting Crime Liberal Articles, Shuffling them and adding them to cList
		List<Integer> cList=new ArrayList<Integer>();
		int clcount=noab.getNoOfCrimeLiberal();
		Integer[] clArray = StaticAuthenticationConstants.CRIME_LIBERAL;
		Collections.shuffle(Arrays.asList(clArray));
		clArray= Arrays.copyOfRange(clArray, 0,clcount);
		cList=new ArrayList<Integer>(Arrays.asList(clArray));
		
		//Getting Crime Conservative Article and Shuffling them. Adding conservative and neutral 
		//articles to cList and shuffling the list ultimately.
		int cccount=noab.getNoOfCrimeConservative();
		Integer[] ccArray = StaticAuthenticationConstants.CRIME_CONSERVATIVE;
		Collections.shuffle(Arrays.asList(ccArray));
		ccArray= Arrays.copyOfRange(ccArray, 0,cccount);
		tempList=new ArrayList<Integer>(Arrays.asList(ccArray));
		cList.addAll(tempList);
		cList.add(Integer.parseInt(StaticAuthenticationConstants.CRIME_NEUTRAL_1));
		cList.add(Integer.parseInt(StaticAuthenticationConstants.CRIME_NEUTRAL_2));
		Collections.shuffle(cList);
		
		// Economy
		headlineIds.addAll(Lists.transform(eList, Functions.toStringFunction()));
		
		// HealthCare
		headlineIds.addAll(Lists.transform(hList, Functions.toStringFunction()));
		// Crime
		headlineIds.addAll(Lists.transform(cList, Functions.toStringFunction()));
		// Entertainment
		headlineIds.add(StaticAuthenticationConstants.ENTERTAINMENT_1);
		headlineIds.add(StaticAuthenticationConstants.ENTERTAINMENT_2);

		return headlineIds;
	}
	

}
