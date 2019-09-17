/**
 * 
 */
package com.lh.test.OBR;

/**
 * @author 238630
 *
 */
public enum LocationType {
	COUNTRY, AREA, AIRPORT;
	public String toString(String code) {
		return code;
	}

	public String getDBQuery(String code) {
		switch (this) {
		case AREA:
			return "select airport_co from (SELECT AIRPORT_CO,AIRPORT,AIRPORT.ZONE,AIRPORT.CITY_CO,CITY.COUNTRY_CO,COUNTRY\r\n"
					+ "FROM airport, city, country where (airport.city_co = city.city_co and city.country_co =country.country_co and country.country_co in (SELECT member FROM area_member WHERE area_co = '"
					+ code + "' and MEMBER_TYPE='COUNTRY')))\r\n";

		case COUNTRY:
			return "select airport_co from (SELECT AIRPORT_CO,AIRPORT,AIRPORT.ZONE,AIRPORT.CITY_CO,CITY.COUNTRY_CO,COUNTRY\r\n" + 
					"FROM airport, city, country where (airport.city_co = city.city_co and city.country_co =country.country_co and \r\n" + 
					"country.country_co = '"
					+ code
					+ "'))";
			
		default:
			throw new AssertionError("Unknown Location" + this);
		}

	}
}
