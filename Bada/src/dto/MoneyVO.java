package dto;

import java.time.LocalDate;

public class MoneyVO {
	
	private int loadMoney, totalMoney, charge; //loadMoney(�����ݾ�)/ totalMoney(�� �ܾ�)/ charge(�����ݾ�)
	
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
	//���糯¥
	public LocalDate getDate() {
		return LocalDate.now();
	}	
}
