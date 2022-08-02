package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;

import dto.MoneyVO;

public class MoneyDAO {

	MoneyVO mVO = MoneyVO.getInstance();
	ArrayList<MoneyVO> moneyList;
	
	public MoneyDAO() {
		moneyList = new ArrayList<MoneyVO>();
	}
	
	//로그인아이디의 충전내역 외부에서 가져오기
	public ArrayList<MoneyVO> getMoneyList(){
		String loginedId = new UserDAO().getInfo().getId();
		mVO.setFilePath(loginedId);
		String path = mVO.getFilePath();
		try {
			FileReader input = new FileReader(path);
			BufferedReader br = new BufferedReader(input);
			
			String line = "";
			String temp = "";
			
			while((line=br.readLine())!=null) {
				temp += line + "\r\n";
			}
			
			String[] arData = new String[10];//1줄씩 저장할 공간
			String[] moneylist = new String[10];//split(tab) 저장할 공간
			
			arData = temp.split("\r\n");
			for (int i = 0; i < arData.length; i++) {
				moneylist = arData[i].split("\t");
				mVO.setLoadMoney(Integer.parseInt(moneylist[0]));
				mVO.setTotalMoney(Integer.parseInt(moneylist[1]));
				mVO.setCharge(Integer.parseInt(moneylist[2]));
				mVO.setDate(moneylist[3]);
				
				moneyList.add(mVO);
			}	
		} catch (Exception e1) {}
		return moneyList;
	}
}
