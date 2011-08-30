package com.ke.matatu.overlays;

public class Matatu {

	public Matatu(String routeNumber, String busStop) {
		setRouteNumber(routeNumber);
		setBusStop(busStop);
	}
	/**
	 * Matatu number e.g. 15 Langata
	 * 
	 * @param routeNumber
	 */
	private void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;		
	}
	
	/**
	 * Matatus pickup point
	 * 
	 * @param busStop
	 */
	private void setBusStop(String busStop) {
		this.busStop = busStop;
	}
	
	public String getBusStop() {
		return busStop;
	}
	
	public String getRouteNumber() {
		return routeNumber;
	}
	
	public String getRouteName() {
		return routeName;
	}
	
	private String routeNumber = null; //e.g 15
	private String routeName = null; // e.g. Town - Otiende
	private String busStop = null; // e.g. Bus Station
}
