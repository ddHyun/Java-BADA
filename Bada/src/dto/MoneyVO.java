package dto;

import java.time.LocalDate;

public class MoneyVO {
	
	private int loadMoney, totalMoney, charge; //loadMoney(충전금액)/ totalMoney(총 잔액)/ charge(결제금액)
	
	private static MoneyVO instance = new MoneyVO();
	private MoneyVO() {}
	public static MoneyVO getInstance() {
		return instance;
	}
	
	public int getLoadMoney() {
		return loadMoney;
	}
	public void setLoadMoney(int loadMoney) {
		this.loadMoney = loadMoney;
	}
	public int getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}	
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	//현재날짜
	public LocalDate getDate() {
		return LocalDate.now();
	}	
}
