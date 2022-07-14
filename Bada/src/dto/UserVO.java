package dto;

import dao.User;

public class UserVO implements User{
	private String id;
	private String pw;
	private String name;
	private String gender;
	private String birth;
	private String phone;
	private String address;
	private String userPath = "D:/BADA/User/";
	private String userPath2 = "userInfo.txt";
	private int number; //ȸ����ȣ
	private static int seq; //ȸ����ȣ �ڵ����� 1�� ��������
	public static int index; //�α����� ȸ�� index
	

	public UserVO() {}

	public UserVO(int number, String id, String pw, String name, String gender, String birth, 
			String phone, String address) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.phone = phone;
		this.address = address;
		this.number = ++seq;
	}

	
	public int getNumber() {
		return number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getUserPath() {
		return userPath;
	}	
		
	public String getUserPath2() {
		return userPath2;
	}

	public void showInfo() {
		System.out.printf("���̵� : %s\r\n��й�ȣ : %s\r\n�̸� : %s\r\n������� : %s\r\n"
				+ "���� : %s\r\n�޴���ȭ : %s\r\n�ּ� : %s\r\n",
				this.id, this.pw, this.name, this.birth, this.gender,
				this.phone, this.address);
	}		
	
}
