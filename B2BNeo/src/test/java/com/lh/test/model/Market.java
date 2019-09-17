package com.lh.test.model;

public class Market {
	private String marketOrgn;
	private String marketDstn;
	private boolean isAwayMarket;
	private boolean foundOnPLPScreen;
	
	public String getMarketOrgn() {
		return marketOrgn;
	}
	public void setMarketOrgn(String marketOrgn) {
		this.marketOrgn = marketOrgn;
	}
	public String getMarketDstn() {
		return marketDstn;
	}
	public void setMarketDstn(String marketDstn) {
		this.marketDstn = marketDstn;
	}
	public boolean isAwayMarket() {
		return isAwayMarket;
	}
	public void setAwayMarket(boolean isAwayMarket) {
		this.isAwayMarket = isAwayMarket;
	}
	public boolean isFoundOnPLPScreen() {
		return foundOnPLPScreen;
	}
	public void setFoundOnPLPScreen(boolean foundOnPLPScreen) {
		this.foundOnPLPScreen = foundOnPLPScreen;
	}
	@Override
	public String toString() {
		return "Market [marketOrgn=" + marketOrgn + ", marketDstn=" + marketDstn + "]";
	}

	

}
