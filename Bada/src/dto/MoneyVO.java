package dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dao.UserDAO;

public class MoneyVO {
	
	private int loadMoney, totalMoney, charge; //loadMoney(충전금액)/ totalMoney(총 잔액)/ charge(결제금액)
	private String date;
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
	public String getConvertedDate() {
		String convertedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return convertedDate;
	}	
	
	//저장된 날짜 가져오기
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
}
