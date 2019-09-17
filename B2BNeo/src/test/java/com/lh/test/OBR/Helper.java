package com.lh.test.OBR;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.lh.test.framework.handlers.DBConnectionHandler;
import com.lh.test.model.Market;

public class Helper {

	public Logger Log = Logger.getLogger(Helper.class.getName());
	
	public List<Market> createMarketLstFromInput(String marketLstStr, String otherMarketLstStr) {
		List<Market> marketLst = new ArrayList<Market>();
		String[] marketLstStrArray = null;
		if (marketLstStr != null && !marketLstStr.isEmpty()) {
			marketLstStrArray = marketLstStr.split(",");
			for (String marketStr : marketLstStrArray) {
				Market market = new Market();
				String[] marketStrArray = marketStr.split("-");
				market.setMarketOrgn(marketStrArray[0]);
				market.setMarketDstn(marketStrArray[1]);
				marketLst.add(market);
			}
		}

		if (otherMarketLstStr != null && !otherMarketLstStr.isEmpty()) {
			marketLstStrArray = otherMarketLstStr.split(",");
			for (String marketStr : marketLstStrArray) {
				Market market = new Market();
				String[] marketStrArray = marketStr.split("-");
				market.setMarketOrgn(marketStrArray[0]);
				market.setMarketDstn(marketStrArray[1]);
				market.setAwayMarket(true);
				marketLst.add(market);
			}
		}
		return marketLst;
	}

	public Map<Integer, Market> getOBR_IDAndMarketMap(List<Integer> OBR_IDLst, List<Market> marketLst) {
		Map<Integer, Market> OBR_IDAndMarketMap = new HashMap<Integer, Market>();
		for (int i = 0; i < OBR_IDLst.size(); i++) {
			OBR_IDAndMarketMap.put(OBR_IDLst.get(i), marketLst.get(i));
		}
		return OBR_IDAndMarketMap;
	}
	
	public String checkAndNormalizeToAirportCode(String code) throws SQLException {

		String dbQuery= new String();
		//Check what level is the code?
		if (code.length() == 4) {
			dbQuery = LocationType.AREA.getDBQuery(code);
		}
			
		else if (code.length() == 3) {
			return code;
		}
		else if (code.length() == 2)
			dbQuery = LocationType.COUNTRY.getDBQuery(code);
		
		DBConnectionHandler dbConnection = new DBConnectionHandler();
		return createConcetenatedString(dbConnection.executeDBQuery(dbQuery));

	}
	
	public String createConcetenatedString(ResultSet codes) throws SQLException {
		String commanSeperatedcodes = new String();
		while (codes.next()) {		
			  String str = codes.getString(1);
			  commanSeperatedcodes=str+","+commanSeperatedcodes;
			}
		return commanSeperatedcodes;
	}
	
	public String getCommaSeperatedValues(int orValue)
	{
		StringBuilder SB = new StringBuilder();
			while(orValue>0)
			{
				int x = orValue%10;
				orValue = orValue/10;
				SB.append(","+x);		
			}
		return SB.substring(1,SB.toString().length());	
	}
	
	public boolean nvl(String searchElement) {
		if (searchElement != null && searchElement.trim().length() > 0)
			return true;
		return false;

	}

}
