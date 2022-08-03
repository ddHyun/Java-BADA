package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import dto.OrderVO;
import dto.ReceiverVO;
import dto.StuffVO;
import dto.UserVO;

public class OrderDAO {
	
	public OrderDAO() {}
	
	//송장번호 부여
	public String trackingNumber() {
		int n1, n2, n3, n4;
		char c1, c2, c3;
		
		n1 = new Random().nextInt(9);
		n2 = new Random().nextInt(9);
		n3 = new Random().nextInt(9);
		n4 = new Random().nextInt(9);
		c1 = (char)(new Random().nextInt(26)+97);
		c2 = (char)(new Random().nextInt(26)+97);
		c3 = (char)(new Random().nextInt(26)+65);
		
		String trackingNum = ""+c1+n1+c2+n2+c3+n4+n3;
		return trackingNum;
	}
	
	//외부장치에서 주문내역 불러오기
	public ArrayList<OrderVO> showOrderDetail() {
		ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
		OrderVO vo = new OrderVO();
		int charge = 0;
		
		String id = new UserDAO().getInfo().getId();
		String path = new UserVO().getUserPath()+id+"/deliveryDetail.txt";
		
		try {
			FileReader input = new FileReader(path);
			BufferedReader br = new BufferedReader(input);
			
			String line = "";
			String temp = "";
			
			while((line=br.readLine())!=null) {
				temp += line +"\r\n";
			}
			
			String[] arData = new String[30];
			String[] arList = new String[30];
			
			arData = temp.split("\r\n");
			for (int i = 0; i < arData.length; i++) {
				arList = arData[i].split("\t");
				//송장번호-날짜-물건-코드-크기-무게-박스개수-비고-결제요금-수령인이름-전화-주소
				vo.setTrackingNumber(arList[0]);
				vo.setDate(arList[1]);
				vo.setStuff(arList[2]);
				vo.setCode(arList[3]);
				vo.setSize(Float.parseFloat(arList[4]));
				vo.setWeight(Float.parseFloat(arList[5]));
				vo.setBox(Integer.parseInt(arList[6]));
				vo.setNote(arList[7]);
				vo.setCharge(Integer.parseInt(arList[8]));
				vo.setName(arList[9]);
				vo.setPhone(arList[10]);
				vo.setAddress(arList[11]);
				
				orderList.add(vo);
			}
			
		} catch (Exception e) {}
		
		return orderList;
		
	}

}
