package top.aisdanny.dao;

public class SelectVoteNumber {
	public int okNumber;
	public int noNumber;
	public int undefNumber;
	public SelectVoteNumber(int okNumber,int noNumber,int undefNumber) {
		// TODO Auto-generated constructor stub
		this.okNumber=okNumber;
		this.noNumber=noNumber;
		this.undefNumber=undefNumber;
	}
	public int getOkNumber() {
		return okNumber;
	}
	public void setOkNumber(int okNumber) {
		this.okNumber = okNumber;
	}
	public int getNoNumber() {
		return noNumber;
	}
	public void setNoNumber(int noNumber) {
		this.noNumber = noNumber;
	}
	public int getUndefNumber() {
		return undefNumber;
	}
	public void setUndefNumber(int undefNumber) {
		this.undefNumber = undefNumber;
	}
	
}
