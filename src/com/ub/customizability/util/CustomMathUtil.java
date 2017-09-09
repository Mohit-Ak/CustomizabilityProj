package com.ub.customizability.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.ub.customizability.beans.NoOfArticlesBean;

/**
 * @author mohitakhakharia
 *
 */
public class CustomMathUtil {

	public static class StaticCustomMathUtil {

		public static List<Integer> getTopicsOrderForHomePage() {
			Integer[] arr = new Integer[4];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = i+1;
			}
			Collections.shuffle(Arrays.asList(arr));
			System.out.println(Arrays.toString(arr));
			return Arrays.asList(arr);
		}
		public static List<Integer> getTopicsOrderForSettingsPage() {
			Integer[] arr = new Integer[3];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = i+1;
			}
			Collections.shuffle(Arrays.asList(arr));
			System.out.println(Arrays.toString(arr));
			return Arrays.asList(arr);
		}
		public static List<ArrayList<Integer>> getLiberalConsOrderInTopics() {
			List<ArrayList<Integer>> outputList= new ArrayList<ArrayList<Integer>>();
			for(int j=0; j<3;j++){
				Integer[] arr = new Integer[2];
				for (int i = 0; i < arr.length; i++) {
					arr[i] = i;
				}
				Collections.shuffle(Arrays.asList(arr));
				ArrayList<Integer> intList = new ArrayList<Integer>(Arrays.asList(arr));
				outputList.add(intList);
				
			}
			
			return outputList;
		}

		public static NoOfArticlesBean getNoOfArticlesForAllTopics() {
			NoOfArticlesBean noab = new NoOfArticlesBean();
			int random = (int) (Math.random() * 4 + 1);
			noab.setNoOfEconomyLiberal(random);
			noab.setNoOfEconomyConservative(random);

			random = (int) (Math.random() * 4 + 1);
			noab.setNoOfHealthCareLiberal(random);
			noab.setNoOfHealthCareConservative(random);

			random = (int) (Math.random() * 4 + 1);
			noab.setNoOfCrimeLiberal(random);
			noab.setNoOfCrimeConservative(random);

			return noab;
		}

		public static String getUserRowId(int userId) {
			Integer rowId = new Integer(userId - 998);
			return rowId.toString();
		}
		
		public static Integer getShownOrNotArticleCol(int articleId) {
			Integer rowId = new Integer(articleId+77);
			return rowId;
		}
		public static Integer getClickedOrNotArticleCol(int articleId) {
			Integer rowId = new Integer(articleId+109);
			return rowId;
		}
		
		public static String getExcelColumnName (int columnNumber) 
	    {     
	        int dividend = columnNumber;   
	        int i;
	        String columnName = "";     
	        int modulo;     
	        while (dividend > 0)     
	        {        
	            modulo = (dividend - 1) % 26;         
	            i = 65 + modulo;
	            columnName = new Character((char)i).toString() + columnName;        
	            dividend = (int)((dividend - modulo) / 26);    
	        }       
	        return columnName; 
	    }  
		
		public static Integer getTimeOnArticleCol(Integer articleId) {
			Integer rowId = new Integer(articleId+5);
			return rowId;
		}
		public static Integer getPosOfArticleCol(Integer articleId) {
			Integer rowId = new Integer(articleId+38-1);
			return rowId;
		}
		
		public static String getAlphabetToNo(String str) {

			char[] ch = str.toCharArray();
			String output="";
			for (char c : ch) {
				int temp = (int) c;
				int temp_integer = 64;
				if (temp <= 90 & temp >= 65){
					System.out.print(temp - temp_integer);
					output+=temp - temp_integer;
				}
			}
			return output;
		}
	}

	public static void main(String[] args) {
		//StaticCustomMathUtil.getAlphabetToNo("EAB");
		//System.out.println(StaticCustomMathUtil.getLiberalConsOrderInTopics());
		System.out.println(StaticCustomMathUtil.getExcelColumnName(1));
	}

}