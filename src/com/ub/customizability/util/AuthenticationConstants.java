package com.ub.customizability.util;
/**
 * @author mohitakhakharia
 */
public class AuthenticationConstants {
	public static class StaticAuthenticationConstants {
		
		public static String SECRETJSONPATH="/com/ub/customizability/util/ServiceAccountKey.json";
		public static String SECRETP12PATH_FOR_JAVASTANDALONE_RUN="src/com/ub/customizability/util/ServiceAccReal.p12";
		public static String SECRETP12PATH="/WEB-INF/ServiceAccReal.p12";
		
		public static String USERSHEETID="14JpzwQzzab145ppzHaOSVD-nWtiJ4BgmGDoXGgCvqpE";
		public static String CONTENTSHEETID="1iQQTg7XbYUyWLvdfDBatIOZjWyWKlkal9fQg16ET_G0";
		
		public static String USER_TYPE1_TR="type1-tr";
		public static String USER_TYPE2_USR="type2-usr";
		public static String USER_TYPE3_RESRCH="type3-resrch";
		public static String USER_TYPE4_USROVR="type4-usrovr";
		
		//Topic Names to be used in ContentSheet
		public static String ECONOMY="Economy";
		public static String HEALTHCARE="Healthcare";
		public static String CRIME="Crime";
		public static String ENTERTAINMENT="Entertainment";
		
		// Articles and their position in the ContentSheet
		public static String ECONOMY_LIBERAL_1="1";
		public static String ECONOMY_LIBERAL_2="2";
		public static String ECONOMY_LIBERAL_3="3";
		public static String ECONOMY_LIBERAL_4="4";
		public static Integer[] ECONOMY_LIBERAL={1,2,3,4};
		
		public static String ECONOMY_CONSERVATIVE_1="5";
		public static String ECONOMY_CONSERVATIVE_2="6";
		public static String ECONOMY_CONSERVATIVE_3="7";
		public static String ECONOMY_CONSERVATIVE_4="8";
		public static Integer[] ECONOMY_CONSERVATIVE={5,6,7,8};
		
		public static String ECONOMY_NEUTRAL_1="9";
		public static String ECONOMY_NEUTRAL_2="10";
		
		public static String HEALTHCARE_LIBERAL_1="11";
		public static String HEALTHCARE_LIBERAL_2="12";
		public static String HEALTHCARE_LIBERAL_3="13";
		public static String HEALTHCARE_LIBERAL_4="14";
		public static Integer[] HEALTHCARE_LIBERAL={11,12,13,14};
		
		public static String HEALTHCARE_CONSERVATIVE_1="15";
		public static String HEALTHCARE_CONSERVATIVE_2="16";
		public static String HEALTHCARE_CONSERVATIVE_3="17";
		public static String HEALTHCARE_CONSERVATIVE_4="18";
		public static Integer[] HEALTHCARE_CONSERVATIVE={15,16,17,18};
		
		public static String HEALTHCARE_NEUTRAL_1="19";
		public static String HEALTHCARE_NEUTRAL_2="20";
		
		public static String CRIME_LIBERAL_1="21";
		public static String CRIME_LIBERAL_2="22";
		public static String CRIME_LIBERAL_3="23";
		public static String CRIME_LIBERAL_4="24";
		public static Integer[] CRIME_LIBERAL={21,22,23,24};
		
		public static String CRIME_CONSERVATIVE_1="25";
		public static String CRIME_CONSERVATIVE_2="26";
		public static String CRIME_CONSERVATIVE_3="27";
		public static String CRIME_CONSERVATIVE_4="28";
		public static Integer[] CRIME_CONSERVATIVE={25,26,27,28};
		
		public static String CRIME_NEUTRAL_1="29";
		public static String CRIME_NEUTRAL_2="30";
		
		public static String ENTERTAINMENT_1="31";
		public static String ENTERTAINMENT_2="32";
		
		// FinalSettings and their Column No. in UserSheet
		public static String FINALSETTINGONSCALE1ONTOPIC1="142";
		public static String FINALSETTINGONSCALE2ONTOPIC1="143";
		public static String FINALSETTINGONSCALE1ONTOPIC2="144";
		public static String FINALSETTINGONSCALE2ONTOPIC2="145";
		public static String FINALSETTINGONSCALE1ONTOPIC3="146";
		public static String FINALSETTINGONSCALE2ONTOPIC3="147";
		
		//LoggedOutSuccessfully
		public static String LOGGEDOUT_SUCCESSFULLY="148";
		
		// ParticipationDate TimeStamp
		public static String PARTICIPATION_DATE="2";
		
		//Position of Topic in Home Page
		public static String POSOFTOPIC1ONHOMEPAGE="70";
		public static String POSOFTOPIC2ONHOMEPAGE="71";
		public static String POSOFTOPIC3ONHOMEPAGE="72";
		public static String POSOFTOPIC4ONHOMEPAGE="73";
		
		//Position of Topic in Home Page
		public static String POSITIONOFTOPIC1ONSETTINGSPAGE="74";
		public static String POSITIONOFTOPIC2ONSETTINGSPAGE="75";
		public static String POSITIONOFTOPIC3ONSETTINGSPAGE="76";
		public static String POSITIONOFTOPIC4ONSETTINGSPAGE="77";
		
		//String Constants
		public static String POSITION_VALUE_FOR_ENTERTAINMENT="BOTTOM";
		public static String NOT_APPLICABLE="NA";
		public static String NO="NO";
		public static String YES="YES";
		public static String ZERO="0";
		
		//Add 5
		public static String ADD_5="5";
		
		//TypeOfRequest
		public static String UPDATE_TIME_HOMEPAGE="updateTimeOnHomePage";
		public static String UPDATE_TIME_SETTINGSPAGE="updateTimeOnSettingsPage";
		public static String UPDATE_TIME_ARTICLEPAGE="updateTimeOnArticlePage";
		
		public static String TIMEONSETTINGSPAGE="4";
		public static String TIMEONHOMEPAGE="5";
		
	}
}
