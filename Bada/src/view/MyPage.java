package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.ButtonDAO;
import dao.EventListenerDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.MoneyDAO;
import dao.PanelDAO;
import dao.OrderDAO;
import dao.TextDAO;
import dao.UserDAO;
import dto.FrameVO;
import dto.MoneyVO;
import dto.UserVO;

public class MyPage {
	FrameDAO mypageFrame;
	PanelDAO mypageLayer;
	LabelDAO titleLabel, nameLabel, phoneLabel, addressLabel, pwLabel, pwLabel2, moneyLabel,
			moneyDetailLabel;
	ButtonDAO infoBtn, pwBtn, changeBtn, changeBtn2, addressBtn, moneyBtn, moneyDetailBtn,
			cancelBtn;
	TextDAO phoneText, moneyText;
	JPasswordField pwField, pwField2;
	JTextArea addressArea, moneyDetailArea;
	JScrollPane addressScrollPane, moneyDetailScroll;
	ArrayList<UserVO> userList = new ArrayList<UserVO>();
	ArrayList<MoneyVO> moneyList;
	MoneyVO mVO = MoneyVO.getInstance();
	int click, click2 = 0;//버튼 클릭으로 form 토글용 변수
	
	public MyPage() {
		mypageFrame = new FrameDAO();
		mypageFrame.makeFrame();
		mypageLayer = new PanelDAO();
		mypageLayer.makeLayer();
		mypageFrame.add(mypageLayer);
		
		new PanelDAO().makeColorPanel(mypageLayer);
		String name = new UserDAO().getInfo().getName();
		titleLabel = new LabelDAO("마이페이지", FrameVO.font40, mypageLayer, 1);
		titleLabel.makeTitleLabel();
		
		//회원정보 수정
		nameLabel = new LabelDAO(name+"님", FrameVO.font18, mypageLayer, 1);
		nameLabel.setBounds(20, 140, 80, 30);
		infoBtn = new ButtonDAO();
		infoBtn.makeGrayButton("기본정보 변경", mypageLayer, 1);
		infoBtn.setBounds(110, 140, 160, 30);
		pwBtn = new ButtonDAO();
		pwBtn.makeGrayButton("비밀번호 변경", mypageLayer, 1);
		pwBtn.setBounds(280, 140, 160, 30);
		
		infoBtn.addActionListener(changeInfo);
		pwBtn.addActionListener(changeInfo);
		
		//충전잔액
		moneyLabel = new LabelDAO("나의 충전잔액", FrameVO.font18, mypageLayer, 1);
		moneyLabel.setBounds(20, 400, 120, 30);
		moneyText = new TextDAO(FrameVO.font20, mypageLayer, 1);
		moneyText.setBounds(150, 400, 160, 30);
		moneyText.setEditable(false);
		//로그인된 아이디의 충전관련 정보 가져오기
		moneyList = new MoneyDAO().getMoneyList();
		int totalMoney = 0;
		if(moneyList.size()!=0) {
			totalMoney = moneyList.get(moneyList.size()-1).getTotalMoney();
		}
		moneyText.setText(""+totalMoney+"원");
		moneyText.setHorizontalAlignment(JLabel.RIGHT);
		moneyBtn = new ButtonDAO();
		moneyBtn.makeGrayButton("충전하기", mypageLayer, 1);
		moneyBtn.setBounds(320, 400, 120, 30);
		
		//충전하기 버튼 : 마이페이지 -> 충전페이지
		moneyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mypageFrame.dispose();
				new LoadMoneyPage().loadMoneyFrame.setVisible(true);
			}
		});
		
		moneyDetailLabel = new LabelDAO("충전내역", FrameVO.font18, mypageLayer, 1);
		moneyDetailLabel.setBounds(20, 450, 150, 30);
		moneyDetailBtn = new ButtonDAO();
		moneyDetailBtn.makeGrayButton("내역확인", mypageLayer, 1);
		moneyDetailBtn.setBounds(320, 450, 120, 30);
		moneyDetailArea = new JTextArea();
		moneyDetailScroll = new JScrollPane(moneyDetailArea);
		moneyDetailArea.setBounds(30, 500, 410, 120);
		moneyDetailScroll.setBounds(30, 500, 410, 120);
		moneyDetailArea.setFont(FrameVO.font18);
		moneyDetailArea.setLineWrap(true);
		mypageLayer.add(moneyDetailScroll);		
		cancelBtn = new ButtonDAO();
		cancelBtn.makeBlueButton("돌아가기", mypageLayer, 1);
		cancelBtn.setBounds(30, 640, 410, 50);
		
		//돌아가기 버튼 클릭 : 마이페이지 -> 메인페이지
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mypageFrame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		});		
		
		//내역확인 버튼 클릭 시 충전내역 띄우기
		moneyDetailBtn.addActionListener(showDetail);
		
		//아이콘이미지
		new ImageDAO().showTitleIcon(mypageFrame);
//		mypageFrame.setVisible(true);
	}
	
	//정보 변경
	ActionListener changeInfo = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
				case "기본정보 변경":
					if(click2%2 != 0) {
						removeArea2();
					}
					click2 = 0;
					
					click++;
					if(click%2==0) {
						removeArea1();
					}else {
						phoneLabel = new LabelDAO("휴대전화", FrameVO.font20, mypageLayer, 1);
						phoneLabel.setBounds(20, 190, 100, 30);
						phoneText = new TextDAO(FrameVO.font18, mypageLayer, 1);
						phoneText.setBounds(130, 190, 310, 30);					
						String phone = new UserDAO().getInfo().getPhone();
						phoneText.setText(phone);
						new EventListenerDAO().removeText(phoneText);
						addressLabel = new LabelDAO("주소", FrameVO.font20, mypageLayer, 1);
						addressLabel.setBounds(20, 230, 100, 30);
						addressBtn = new ButtonDAO();
						addressBtn.makeGrayButton("주소찾기", mypageLayer, 1);
						addressBtn.setLocation(130, 230);
						addressArea = new JTextArea();
						addressScrollPane = new JScrollPane(addressArea);
						addressArea.setBounds(130, 270, 310, 70);
						addressScrollPane.setBounds(130, 270, 310, 70);
						addressArea.setFont(FrameVO.font18);
						addressArea.setLineWrap(true);
						mypageLayer.add(addressScrollPane);
						String address = new UserDAO().getInfo().getAddress();
						addressArea.setText(address);
						addressArea.setEditable(false);
						
						//주소검색버튼 이벤트
						new UserDAO().getAddrEvent(addressBtn, addressArea, mypageFrame);					
						
						//변경하기
						changeBtn = new ButtonDAO();
						changeBtn.makeGrayButton("변경하기", mypageLayer, 1);
						changeBtn.setBounds(200, 350, 150, 30);
						changeBtn.addActionListener(saveInfo);								
					}
					break;
				case "비밀번호 변경":
					//기본정보수정 panel 없애기
					if(click%2 != 0) {
						removeArea1();
					}
					click = 0;
					
					click2++;
					if(click2%2==0) {
						removeArea2();
					}else {
						pwLabel = new LabelDAO("새로운 비밀번호", FrameVO.font20, mypageLayer, 1);
						pwLabel.setBounds(20, 190, 150, 30);
						pwField = new JPasswordField();
						pwField.setBounds(180, 190, 260, 30);
						mypageLayer.add(pwField, new Integer(1));
						pwLabel2 = new LabelDAO("비밀번호 확인", FrameVO.font20, mypageLayer, 1);
						pwLabel2.setBounds(20, 230, 150, 30);
						pwField2 = new JPasswordField();
						pwField2.setBounds(180, 230, 260, 30);
						mypageLayer.add(pwField2, new Integer(1));
						changeBtn2 = new ButtonDAO();
						changeBtn2.makeGrayButton("변경하기", mypageLayer, 1);
						changeBtn2.setBounds(230, 270, 150, 30);
						changeBtn2.addActionListener(saveInfo);	
					}
					break;
			}
		}
	};	

	
	//기본정보수정 form 안보이게 하기 메서드
	public void removeArea1() {		
		phoneLabel.setVisible(false);
		phoneText.setVisible(false);
		addressLabel.setVisible(false);
		addressBtn.setVisible(false);
		addressArea.setVisible(false);
		addressScrollPane.setVisible(false);
		changeBtn.setVisible(false);
	}
		
	//비밀번호수정 form 안보이게 하기 메서드
	public void removeArea2() {
		pwLabel.setVisible(false);
		pwField.setVisible(false);
		pwLabel2.setVisible(false);
		pwField2.setVisible(false);
		changeBtn2.setVisible(false);
	}
	
//정보수정 메서드
ActionListener saveInfo = new ActionListener() {
		
	@Override
	public void actionPerformed(ActionEvent e) {
		
		userList = new JoinPage().getUserInfo();
		UserVO vo = userList.get(UserVO.index);
		int size = new UserDAO().getListSize();
		
		try {					
			if(e.getSource()==changeBtn) {
				//로그인한 회원정보 vo에 담기
				String phone = phoneText.getText().toString();
				String address = addressArea.getText().toString();
				if(phone.equals(vo.getPhone())&& address.equals(vo.getAddress())) {
					JOptionPane.showMessageDialog(mypageFrame, "변경사항이 없습니다", "마이페이지", JOptionPane.INFORMATION_MESSAGE);
				}else if(!phone.equals(vo.getPhone()) || !address.equals(vo.getAddress())) {					
					vo.setPhone(phone);
					vo.setAddress(address);					
					JOptionPane.showMessageDialog(mypageFrame, "정보가 변경되었습니다", "마이페이지", JOptionPane.PLAIN_MESSAGE);
				}
			}else if(e.getSource()==changeBtn2) {
				String password = new JoinPage().decryptPassword(pwField);
				String password2 = new JoinPage().decryptPassword(pwField2);
				if(!password.equals(password2)) {
					JOptionPane.showMessageDialog(mypageFrame, "비밀번호가 일치하지 않습니다\r\n다시 시도해주세요", "마이페이지", JOptionPane.ERROR_MESSAGE);
					pwField.setText("");
					pwField2.setText("");
				}else {
					if(password.equals(vo.getPw())) {
						JOptionPane.showMessageDialog(mypageFrame, "변경사항이 없습니다", "마이페이지", JOptionPane.INFORMATION_MESSAGE);
					}else {
						vo.setPw(password);
						JOptionPane.showMessageDialog(mypageFrame, "정보가 변경되었습니다", "마이페이지", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}					
			FileWriter output = new FileWriter(new File(new UserVO().getUserPath()+new UserVO().getUserPath2()));
			for (int i = 0; i < size; i++) {
				
				UserVO uVO = userList.get(i);
				output.write(uVO.getNumber()+"\t");
				output.write(uVO.getId()+"\t");
				output.write(uVO.getPw()+"\t");
				output.write(uVO.getName()+"\t");
				output.write(uVO.getGender()+"\t");
				output.write(uVO.getBirth()+"\t");
				output.write(uVO.getPhone()+"\t");
				output.write(uVO.getAddress()+"\r\n");
			}
			output.close();						
		}catch (Exception e1) {
				JOptionPane.showMessageDialog(mypageFrame, "파일이 존재하지 않습니다", "마이페이지", JOptionPane.ERROR_MESSAGE);
			}//catch	
	}//actionPerformed()
};//saveInfo()

//충전내역 불러오기
ActionListener showDetail = new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
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
			
			moneyDetailArea.append("        날짜\t     충전금액\t      결제금액\r\n");
			moneyDetailArea.append("--------------------------------------------------------\r\n");
			
			for (int i = 0; i < arData.length; i++) {
				moneylist = arData[i].split("\t");
				if(moneylist[2].equals("0")) {
					moneyDetailArea.append(" "+moneylist[3]+"\t      "+moneylist[0]+"\r\n");
				}else if(moneylist[0].equals("0")) {
					moneyDetailArea.append(" "+moneylist[3]+"\t      \t         -"+moneylist[2]+"\r\n");
				}else {
				moneyDetailArea.append(" "+moneylist[3]+"\t      "+moneylist[0]+"\t       -"+moneylist[2]+"\r\n");
				}
			}				
		} catch (Exception e1) {}
		
	}
};
}
