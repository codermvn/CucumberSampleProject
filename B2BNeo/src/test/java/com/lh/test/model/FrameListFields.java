package com.lh.test.model;

public class FrameListFields {

	private String webElement;
	private int noOfScroll;
	private boolean isEditable;

	
	public FrameListFields(String webElement, int noOfScroll,boolean isEditable) {
		super();
		
		this.webElement = webElement;
		this.noOfScroll = noOfScroll;
		this.isEditable = isEditable;
	}

	public String getWebElement() {
		return webElement;
	}
	public void setWebElement(String webElement) {
		this.webElement = webElement;
	}
	public int getNoOfScroll() {
		return noOfScroll;
	}
	public void setNoOfScroll(int noOfScroll) {
		this.noOfScroll = noOfScroll;
	}

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isEditable ? 1231 : 1237);
		result = prime * result + noOfScroll;
		result = prime * result + ((webElement == null) ? 0 : webElement.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FrameListFields other = (FrameListFields) obj;
		if (isEditable != other.isEditable)
			return false;
		if (noOfScroll != other.noOfScroll)
			return false;
		if (webElement == null) {
			if (other.webElement != null)
				return false;
		} else if (!webElement.equals(other.webElement))
			return false;
		return true;
	}
}
