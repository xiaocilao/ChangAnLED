package entity;

public class DsPpEntity {
	private Integer shiftCount;
	
	private String roductSeries;

	public Integer getShiftCount() {
		return shiftCount;
	}

	public void setShiftCount(Integer shiftCount) {
		this.shiftCount = shiftCount;
	}

	public String getRoductSeries() {
		return roductSeries;
	}

	public void setRoductSeries(String roductSeries) {
		this.roductSeries = roductSeries;
	}

	@Override
	public String toString() {
		return "DsPpEntity [shiftCount=" + shiftCount + ", roductSeries="
				+ roductSeries + "]";
	}
	
	

}
