package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import dto.FrameVO;
import dto.UserVO;

public class OrderPage {
	
	FrameDAO orderFrame;
	PanelDAO orderLayer;
	LabelDAO titleLabel, sendLabel, sendNameLabel, sendPhoneLabel, sendAddrLabel,
					receiveLabel, receiveNameLabel, receivePhoneLabel, receiveAddrLabel;
	TextDAO sendNameText, sendPhoneText, receiveNameText, receivePhoneText;
	ButtonDAO sendInfoBtn, addressBtn, nextBtn, cancelBtn;
	JTextArea sendAddrArea, receiveAddrArea;
	JScrollPane scrollPane, scrollPane2;
	ArrayList<UserVO> userList = new ArrayList<UserVO>();
	String jTitle = "택배주문 신청";
	int senderZip, receiverZip;
	
	//택배주문 신청 페이지
	public OrderPage() {
		orderFrame = new FrameDAO();
		orderFrame.makeFrame();
		orderLayer = new PanelDAO();
		orderLayer.makeLayer();
		orderFrame.add(orderLayer);
		
		new PanelDAO().makeColorPanel(orderLayer);
		titleLabel = new LabelDAO("택배주문 신청", FrameVO.font40, orderLayer, 1);
		titleLabel.makeTitleLabel();
		
		//주문자 정보
		sendLabel = new LabelDAO("주문자 정보", FrameVO.font25, orderLayer, 1);
		sendLabel.setBounds(20, 140, 200, 30);
		sendLabel.setOpaque(true); //label에 배경색 넣을 땐 setOpaque(true) -> 배경색 넣어야 함
		sendLabel.setBackground(FrameVO.grayColor);
		sendInfoBtn = new ButtonDAO();
		sendInfoBtn.makeGrayButton("정보 불러오기", orderLayer, 1);
		sendInfoBtn.setBounds(280, 150, 160, 30);
		sendNameLabel = new LabelDAO("이름", FrameVO.font20, orderLayer, 1);
		sendNameLabel.setBounds(20, 190, 100, 30);
		sendNameText = new TextDAO(FrameVO.font18, orderLayer, 1);
		sendNameText.setBounds(160, 190, 280, 30);
		sendPhoneLabel = new LabelDAO("휴대전화", FrameVO.font20, orderLayer, 1);
		sendPhoneLabel.setBounds(20, 230, 100, 30);
		sendPhoneText = new TextDAO(FrameVO.font18, orderLayer, 1);
		sendPhoneText.setBounds(160, 230, 280, 30);
		sendAddrLabel = new LabelDAO("주소", FrameVO.font20, orderLayer, 1);
		sendAddrLabel.setBounds(20, 270, 100, 30);
		
		sendAddrArea = new JTextArea();		
		scrollPane = new JScrollPane(sendAddrArea);
		sendAddrArea.setBounds(20, 310, 420, 60);
		scrollPane.setBounds(20, 310, 420, 60);
		orderLayer.add(scrollPane, new Integer(1));
		sendAddrArea.setFont(FrameVO.font18);
		sendAddrArea.setLineWrap(true); //자동 줄바꿈
		
		//정보 불러오기 버튼 클릭시 로그인 정보 가져오기 이벤트
		sendInfoBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showOptionDialog(orderFrame, 
						"회원 정보가 주문자와 같다면 '예'를 눌러주세요", jTitle, 0, JOptionPane.PLAIN_MESSAGE, null, null, null);
				
				if(choice==0) {
					userList = new JoinPage().getUserInfo();
					UserVO vo = userList.get(UserVO.index);
					sendNameText.setText(vo.getName());
					sendPhoneText.setText(vo.getPhone());
					//우편번호, 주소 메서드 호출
					senderZip = getZip();		
					String address = getRestAddress();
					//textarea에 내용 넣기
					sendAddrArea.append(senderZip+"\r\n");
					sendAddrArea.append(address);
					
					sendNameText.setEditable(false);
					sendPhoneText.setEditable(false);
					sendAddrArea.setEditable(false);
					sendInfoBtn.setEnabled(false);
				}
			}
		});
		
		//수령인 정보
		receiveLabel = new LabelDAO("수령인 정보", FrameVO.font25, orderLayer, 1);
		receiveLabel.setBounds(20, 400, 200, 30);
		receiveLabel.setOpaque(true);
		receiveLabel.setBackground(FrameVO.grayColor);
		receiveNameLabel = new LabelDAO("이름", FrameVO.font20, orderLayer, 1);
		receiveNameLabel.setBounds(20, 450, 100, 30);
		receiveNameText = new TextDAO(FrameVO.font18, orderLayer, 1);
		receiveNameText.setBounds(160, 450, 280, 30);
		receivePhoneLabel = new LabelDAO("휴대전화", FrameVO.font20, orderLayer, 1);
		receivePhoneLabel.setBounds(20, 490, 100, 30);
		receivePhoneText = new TextDAO(FrameVO.font18, orderLayer, 1);
		receivePhoneText.setBounds(160, 490, 280, 30);
		receivePhoneText.setText("예)01012345678");
		new EventListenerDAO().removeText(receivePhoneText);
		receiveAddrLabel = new LabelDAO("주소", FrameVO.font20, orderLayer, 1);
		receiveAddrLabel.setBounds(20, 530, 100, 30);
		addressBtn = new ButtonDAO();
		addressBtn.makeGrayButton("주소찾기", orderLayer, 1);
		addressBtn.setLocation(325, 530);
		receiveAddrArea = new JTextArea();
		scrollPane2 = new JScrollPane(receiveAddrArea);
		receiveAddrArea.setBounds(20, 570, 420, 60);
		scrollPane2.setBounds(20, 570, 420, 60);
		receiveAddrArea.setFont(FrameVO.font18);
		receiveAddrArea.setLineWrap(true);
		orderLayer.add(scrollPane2);
		
		//취소버튼
		cancelBtn = new ButtonDAO();
		cancelBtn.makeBlueButton("취소하기", orderLayer, 1);
		cancelBtn.setBounds(20, 660, 205, 50);
		//취소버튼 클릭시 메인페이지로 이동 이벤트
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				orderFrame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		});
		
		//다음단계 버튼
		nextBtn = new ButtonDAO();
		nextBtn.makeBlueButton("다음단계", orderLayer, 1);
		nextBtn.setBounds(235, 660, 205, 50);
		nextBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(senderZip!=0 && receiverZip!=0) {
					System.out.println("clicked");
					//수령인 정보 저장하기
					receiverInfo();
					orderFrame.dispose();
					new OrderPage2().order2Frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(orderFrame, "항목을 빠짐없이 채워주세요", jTitle, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//	주소찾기 버튼이벤트
		getAddrEvent(addressBtn, receiveAddrArea, orderFrame);
		
		//아이콘 이미지
		new ImageDAO().showTitleIcon(orderFrame);	
//		orderFrame.setVisible(true);
	}
	
	//수령인 정보 저장하기 메서드
	public List<String> receiverInfo(){
		String name = receiveNameText.getText();
		String phone = receivePhoneText.getText();
		String address = receiveAddrArea.getText();
		
		List<String> list = new ArrayList<String>();
		list.add(name);
		list.add(phone);
		list.add(address);
		
		return list;
	}
	
	//우편번호 int형으로 가지고 있기 메서드(요금 계산시 필요)
	public int getZip() {
		String[] addr = new String[2];
		addr = userList.get(UserVO.index).getAddress().split("\\)");
		int zip = Integer.parseInt(addr[0].substring(1));
		return zip;
	}
	
	//직접 입력받은 주소에서 우편번호 빼니기
	public int getZip(JTextField addr1, JTextField addr2) {
		String address = addr1.getText() + " " + addr2.getText();
		String[] addr = new String[2];
		addr = address.split("\\)");
		int zip = Integer.parseInt(addr[0].substring(1));
		return zip;
	}
	
	//우편번호 제외한 나머지 주소 가져오기 메서드
	public String getRestAddress() {
		String[] addr = new String[2];
		addr = userList.get(UserVO.index).getAddress().split("\\)");
		String address = addr[1].trim();
		return address;
	}
	
	//직접 입력받은 주소에서 우편번호 제외한 나머지 주소 가져오기
	public String getRestAddress(JTextField addr1, JTextField addr2) {
		String address1 = addr1.getText() + " " + addr2.getText();
		String[] addr = new String[2];
		addr = address1.split("\\)");
		String address = addr[1].trim();
		return address;
	}
	
	//주소찾기 버튼 이벤트 시 frame 생성 및 주소찾기
	public void getAddrEvent(JButton btn, JTextArea area, JFrame frame) {
		btn.addActionListener(new ActionListener() {
			FrameDAO addressFrame;
			PanelDAO addressLayer, bgPanelA;
			LabelDAO titleLabelA, zipLabel;
			TextDAO addressTextA, addressTextB, zipText;
			ButtonDAO addressBtnA, zipBtn, applyBtn, cancelBtn;
			JTextArea addressArea;
			int zip;
			@Override
			public void actionPerformed(ActionEvent e) {

				addressFrame = new FrameDAO();
				addressFrame.makeFrame();
				addressLayer = new PanelDAO();
				addressLayer.makeLayer();
				addressFrame.add(addressLayer);
				bgPanelA = new PanelDAO();
				bgPanelA.makeColorPanel(addressLayer);
				titleLabelA = new LabelDAO("주소찾기", FrameVO.font40, addressLayer, 1);
				titleLabelA.makeTitleLabel();
				addressTextA = new TextDAO(FrameVO.font18, addressLayer, 1);
				addressTextA.setBounds(15, 150, 300, 30);
				addressTextA.setText("예)남촌로 15(띄어쓰기 필수)");
				new EventListenerDAO().removeText(addressTextA);
				addressBtnA = new ButtonDAO();
				addressBtnA.makeGrayButton("주소검색", addressLayer, 1);
				addressBtnA.setLocation(330, 150);		
				
				//검색결과 보여줄 area
				addressArea = new JTextArea();
				JScrollPane scrollPanel = new JScrollPane(addressArea);
				addressArea.setBounds(15, 200, 430, 300);
				scrollPanel.setBounds(15, 200, 430, 300);
				addressLayer.add(scrollPanel, new Integer(1));
				addressArea.setFont(FrameVO.font18);
				addressArea.setBackground(FrameVO.grayColor);
				addressArea.setEditable(false);
				
				//주소선택 combobox		
				zipLabel = new LabelDAO("주소를 선택하세요", FrameVO.font20, addressLayer, 1);
				zipLabel.setBounds(15, 520, 200, 30);
				JComboBox<String> numBox = new JComboBox<String>();
				numBox.setBounds(240, 520, 80, 30);
				numBox.setFont(FrameVO.font18);
				numBox.setBackground(Color.WHITE);
				addressLayer.add(numBox);
				zipBtn = new ButtonDAO();
				zipBtn.makeGrayButton("선택", addressLayer, 1);
				zipBtn.setLocation(330, 520);
				
				zipText = new TextDAO(FrameVO.font18, addressLayer, 1);
				zipText.setBounds(15, 560, 430, 30);
				zipText.setEditable(false);
				addressTextB = new TextDAO(FrameVO.font18, addressLayer, 1);
				addressTextB.setBounds(15, 600, 430, 30);
				addressTextB.setText("나머지 주소를 입력해 주세요");
				new EventListenerDAO().removeText(addressTextB);
				
				
				//주소검색 버튼 이벤트
				addressBtnA.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new JoinPage().findZipCode(addressArea, addressFrame, addressTextA, numBox, zipBtn, zipText);
					}
				});		
				
				//적용, 취소버튼
				applyBtn =new ButtonDAO();
				applyBtn.makeGrayButton("적용", addressLayer, 1);
				applyBtn.setLocation(100, 660);
				
				applyBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//검색창에 검색어를 입력하지 않았을 경우
						if(zipText.getText().equals("")) {
							JOptionPane.showMessageDialog(addressFrame, "주소를 선택해 주세요", "주소검색", JOptionPane.ERROR_MESSAGE);
						}else {
							//검색 결과 우편번호와 나머지 주소를 따로 저장(택배비용 계산을 위해 필요)
							zip = getZip(zipText, addressTextB);
							String address = getRestAddress(zipText, addressTextB);
							area.append(zip+"\r\n");
							area.append(address);
							receiverZip = zip;
							System.out.println("receiverZip:"+receiverZip);
							btn.setEnabled(false);
							addressFrame.dispose();
							frame.setVisible(true);
						}
					}
				});
				cancelBtn = new ButtonDAO();
				cancelBtn.makeGrayButton("취소", addressLayer, 1);
				cancelBtn.setLocation(240, 660);
				new EventListenerDAO().movePage(cancelBtn, addressFrame, frame);
				
				addressFrame.setVisible(true);
				frame.setVisible(false);
				
				new ImageDAO().showTitleIcon(addressFrame);	
			}
		});
	}
}
