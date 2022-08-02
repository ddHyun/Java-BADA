package dao;

import java.util.Random;

public class StuffDAO {
	
	public StuffDAO() {}
	
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

}
