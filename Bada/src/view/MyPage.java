package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.ButtonDAO;
import dao.EventListenerDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dao.TextDAO;
import dao.UserDAO;
import dto.FrameVO;
import dto.UserVO;

public class MyPage {
	FrameDAO mypageFrame;
	PanelDAO mypageLayer;
	LabelDAO titleLabel, nameLabel, phoneLabel, addressLabel;
	ButtonDAO infoBtn, pwBtn, changeBtn, addressBtn;
	TextDAO phoneText;
	JTextArea addressArea;
	JScrollPane addressScrollPane;
	ArrayList<UserVO> userList = new ArrayList<UserVO>();
	
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
					
					//변경하기 버튼 클릭시 내용 수정
					break;
				case "비밀번호 변경":
					break;
			}
		}
	};
	
	
	//정보수정
	ActionListener saveInfo = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//로그인한 회원정보 vo에 담기
			userList = new JoinPage().getUserInfo();
			UserVO vo = userList.get(UserVO.index);
			String phone = phoneText.getText().toString();
			String address = addressArea.getText().toString();
			int size = new UserDAO().getListSize();
			
			try {
				
				if(!phone.equals(vo.getPhone())|| !address.equals(vo.getAddress())) {
					if(!phone.equals(vo.getPhone())) {
						vo.setPhone(phone);
						System.out.println("전화번호 변경 : "+vo.getPhone());
					}
					if(!address.equals(vo.getAddress())) {
						vo.setAddress(address);
						System.out.println("주소 변경 : "+vo.getAddress());
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
					JOptionPane.showMessageDialog(mypageFrame, "정보가 변경되었습니다", "마이페이지", JOptionPane.PLAIN_MESSAGE);
					output.close();
					
				}else {
					JOptionPane.showMessageDialog(mypageFrame, "변경사항이 없습니다", "마이페이지", JOptionPane.INFORMATION_MESSAGE);
				}
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(mypageFrame, "파일이 존재하지 않습니다", "로그인", JOptionPane.ERROR_MESSAGE);
				}//catch
				
		}//actionPerformed()
	};//saveInfo()
	
	
	
//	public void changeInfo(JTextField text, JFrame frame) {
//		
//		try {
//			
//			getInfo().setPw(password);
//			
//			FileWriter output = new FileWriter(new File(new UserVO().getUserPath()+new UserVO().getUserPath2()));
//			for (int i = 0; i < userList.size(); i++) {
//				UserVO vo = userList.get(i);
//				output.write(vo.getNumber()+"\t");
//				output.write(vo.getId()+"\t");
//				output.write(vo.getPw()+"\t");
//				output.write(vo.getName()+"\t");
//				output.write(vo.getGender()+"\t");
//				output.write(vo.getBirth()+"\t");
//				output.write(vo.getPhone()+"\t");
//				output.write(vo.getAddress()+"\r\n");
//			}
//			output.close();
//			
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(frame, "파일이 존재하지 않습니다", "로그인", JOptionPane.ERROR_MESSAGE);
//		}
//	}	
}
