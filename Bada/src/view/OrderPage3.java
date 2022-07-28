package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.ButtonDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dto.FrameVO;
import dto.ReceiverVO;
import dto.StuffVO;
import dto.UserVO;

public class OrderPage3 {

	//주문정보 확인페이지
	
	FrameDAO order3Frame;
	PanelDAO order3Layer;
	LabelDAO titleLabel, senderNameLabel;
	ButtonDAO cancelBtn, nextBtn;
	JTextArea orderArea;
	JScrollPane orderScrollPane;
	StuffVO stuffVO = StuffVO.getInstance();
	ReceiverVO receiverVO = ReceiverVO.getInstance();
	ArrayList<UserVO> userList = new ArrayList<UserVO>();
	
	public OrderPage3() {
		order3Frame = new FrameDAO();
		order3Frame.makeFrame();
		order3Layer = new PanelDAO();
		order3Layer.makeLayer();
		order3Frame.add(order3Layer);
		
		new PanelDAO().makeColorPanel(order3Layer);
		titleLabel = new LabelDAO("주문확인", FrameVO.font40, order3Layer, 1);
		titleLabel.makeTitleLabel();
		
		//주문정보 확인		
		orderArea = new JTextArea();
		orderScrollPane = new JScrollPane(orderArea);
		orderArea.setBounds(20, 140, 420, 480);
		orderScrollPane.setBounds(20, 140, 420, 480);
		orderArea.setFont(FrameVO.font18);
		orderArea.setLineWrap(true);
		order3Layer.add(orderScrollPane);
		orderArea.setEditable(false);
		
		//주문자 정보 textArea에 담기
		UserVO uVO = getSenderInfo();
		orderArea.append("\r\n");
		orderArea.append("[주문자]\r\n");
		orderArea.append(" 이름 : "+uVO.getName()+"\r\n");
		orderArea.append(" 전화 : "+uVO.getPhone()+"\r\n");
		orderArea.append(" 주소 : "+uVO.getAddress()+"\r\n");
		//수령자 정보 textArea에 담기
		orderArea.append("\r\n");
		orderArea.append("[수령인]\r\n");
		orderArea.append(" 이름 : "+receiverVO.getName()+"\r\n");
		orderArea.append(" 전화 : "+receiverVO.getPhone()+"\r\n");
		orderArea.append(" 주소 : "+receiverVO.getAddress()+"\r\n");
		orderArea.append("\r\n");
		//물품 정보 textArea에 담기
		orderArea.append("[주문물품]\r\n");
		orderArea.append(" 무게 : "+stuffVO.getWeight()+"kg / 크기 : "+stuffVO.getSize()+"cm\r\n");
		orderArea.append(" 물품 : "+stuffVO.getCode()+"/ "+stuffVO.getStuff()+"\r\n");
		orderArea.append(" 박스 : "+stuffVO.getBox()+"개\r\n");
		orderArea.append(" 배송시 유의사항 : "+stuffVO.getNote());
	
		//취소, 결제버튼
		cancelBtn = new ButtonDAO();
		cancelBtn.makeGrayButton("취소하기", order3Layer, 1);
		cancelBtn.setBounds(20, 640, 200, 50);
		cancelBtn.setFont(FrameVO.font30);
		
		nextBtn = new ButtonDAO();
		nextBtn.makeBlueButton("결제단계 >>", order3Layer, 1);
		nextBtn.setBounds(240, 640, 200, 50);
		
		//취소버튼 : 주문3 -> 메인페이지 이동
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				order3Frame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		});	
		
		//결제단계버튼 : 주문3 -> 결제페이지 이동
		nextBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				order3Frame.dispose();
				new PayPage().payFrame.setVisible(true);
				
			}
		});
		
		//아이콘이미지
		new ImageDAO().showTitleIcon(order3Frame);
		order3Frame.setVisible(true);
		
	}
	
	//주문자 정보 가져오기
	public UserVO getSenderInfo() {
		userList = new JoinPage().getUserInfo();
		UserVO vo = userList.get(UserVO.index);
		return vo;
	}
}
