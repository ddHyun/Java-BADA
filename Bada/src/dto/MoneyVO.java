package dto;

import java.time.LocalDate;

import dao.UserDAO;

public class MoneyVO {
	
	private int loadMoney, totalMoney, charge; //loadMoney(충전금액)/ totalMoney(총 잔액)/ charge(결제금액)
	private LocalDate date = LocalDate.now();
	private String filePath;
	private String fileName;
	
	private static MoneyVO instance = new MoneyVO();
	private MoneyVO() {}
	public static MoneyVO getInstance() {
		return instance;
	}
	
	public String getFileName() {
		return "/loadMoneyDetail.txt";
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String id) {
		this.filePath = new UserVO().getUserPath()+id+getFileName();
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
		return date;
	}	
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
