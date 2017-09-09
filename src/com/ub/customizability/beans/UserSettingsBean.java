package com.ub.customizability.beans;
/**
 * @author mohitakhakharia
 * Used to save / retrieve User Settings
 */
public class UserSettingsBean {
	// Economy Liberal
	private String economyLiberal;
	// Economy Conservative
	private String economyConservative;
	// HealthCare Liberal
	private String healthCareLiberal;
	// HealthCare Conservative
	private String healthCareConservative;
	// Crime Liberal
	private String crimeLiberal;
	// Crime Conservative
	private String crimeConservative;
		
	
	/**
	 * @return the economyLiberal
	 */
	public String getEconomyLiberal() {
		return economyLiberal;
	}
	/**
	 * @param economyLiberal the economyLiberal to set
	 */
	public void setEconomyLiberal(String economyLiberal) {
		this.economyLiberal = economyLiberal;
	}
	/**
	 * @return the economyConservative
	 */
	public String getEconomyConservative() {
		return economyConservative;
	}
	/**
	 * @param economyConservative the economyConservative to set
	 */
	public void setEconomyConservative(String economyConservative) {
		this.economyConservative = economyConservative;
	}
	/**
	 * @return the healthCareLiberal
	 */
	public String getHealthCareLiberal() {
		return healthCareLiberal;
	}
	/**
	 * @param healthCareLiberal the healthCareLiberal to set
	 */
	public void setHealthCareLiberal(String healthCareLiberal) {
		this.healthCareLiberal = healthCareLiberal;
	}
	/**
	 * @return the healthCareConservative
	 */
	public String getHealthCareConservative() {
		return healthCareConservative;
	}
	/**
	 * @param healthCareConservative the healthCareConservative to set
	 */
	public void setHealthCareConservative(String healthCareConservative) {
		this.healthCareConservative = healthCareConservative;
	}
	/**
	 * @return the crimeLiberal
	 */
	public String getCrimeLiberal() {
		return crimeLiberal;
	}
	/**
	 * @param crimeLiberal the crimeLiberal to set
	 */
	public void setCrimeLiberal(String crimeLiberal) {
		this.crimeLiberal = crimeLiberal;
	}
	/**
	 * @return the crimeConservative
	 */
	public String getCrimeConservative() {
		return crimeConservative;
	}
	/**
	 * @param crimeConservative the crimeConservative to set
	 */
	public void setCrimeConservative(String crimeConservative) {
		this.crimeConservative = crimeConservative;
	}
	
	@Override
	public String toString() {
		return "UserSettingsBean [economyLiberal=" + economyLiberal + ", economyConservative=" + economyConservative
				+ ", healthCareLiberal=" + healthCareLiberal + ", healthCareConservative=" + healthCareConservative
				+ ", crimeLiberal=" + crimeLiberal + ", crimeConservative=" + crimeConservative + "]";
	}
	
	
}
