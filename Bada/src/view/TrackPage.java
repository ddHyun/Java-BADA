package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.ButtonDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.OrderDAO;
import dao.PanelDAO;
import dao.TextDAO;
import dto.FrameVO;
import dto.OrderVO;

public class TrackPage {
	//주문내역, 배송조회 페이지
	
	FrameDAO trackFrame;
	PanelDAO trackLayer;
	LabelDAO titleLabel, orderDetailLabel, trackingLabel, resultLabel, result2Label;
	TextDAO trackingText;
	JTextArea orderDetailArea;
	JScrollPane orderDetailScroll;
	ButtonDAO listBtn, detailBtn, trackingBtn, backBtn;
	ArrayList<OrderVO> orderList;
	OrderVO vo;
	int trackingIndex;//외부파일에 있는 주문내역에서 입력한 송장번호와 일치하는 index 저장용 변수

	public TrackPage() {
		trackFrame = new FrameDAO();
		trackFrame.makeFrame();
		trackLayer = new PanelDAO();
		trackLayer.makeLayer();
		trackFrame.add(trackLayer);
		
		new PanelDAO().makeColorPanel(trackLayer);
		titleLabel = new LabelDAO("주문배송조회", FrameVO.font40, trackLayer, 1);
		titleLabel.makeTitleLabel();
		
		//주문내역조회
		orderDetailLabel = new LabelDAO("주문내역", FrameVO.font20, trackLayer, 1);
		orderDetailLabel.setBounds(20, 140, 150, 30);
		orderDetailArea = new JTextArea();
		orderDetailScroll = new JScrollPane(orderDetailArea);
		orderDetailArea.setBounds(30, 180, 410, 200);
		orderDetailScroll.setBounds(30, 180, 410, 200);
		orderDetailArea.setFont(FrameVO.font18);
		orderDetailArea.setLineWrap(true);
		trackLayer.add(orderDetailScroll);		
		orderDetailArea.setEditable(false);
		
		listBtn = new ButtonDAO();
		listBtn.makeGrayButton("내역보기", trackLayer, 1);
		listBtn.setBounds(330, 140, 110, 30);
		detailBtn = new ButtonDAO();
		detailBtn.makeGrayButton("상세보기", trackLayer, 1);
		detailBtn.setBounds(210, 140, 110, 30);
		detailBtn.setVisible(false);
		
		//주문내역 확인
		listBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				orderList = new OrderDAO().showOrderDetail();
				if(orderList.size()>0) {
					detailBtn.setVisible(true);
					orderDetailArea.append("     송장번호\t            날짜\t          물품\r\n");
					orderDetailArea.append("---------------------------------------------------------\r\n");
					for (int i = 0; i < orderList.size(); i++) {
						vo = orderList.get(i);
						orderDetailArea.append("    "+vo.getTrackingNumber()+"\t     "+vo.getDate()+"\t        "+vo.getStuff()+"\r\n");
					}
					
				}else {
					JOptionPane.showMessageDialog(trackFrame, "조회결과가 없습니다", "주문배송조회", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//상세보기 버튼 클릭 시 : 상세내역 보기
		detailBtn.addActionListener(showOrder);
		
		//배송조회
		trackingLabel = new LabelDAO("배송조회", FrameVO.font20, trackLayer, 1);
		trackingLabel.setBounds(20, 420, 100, 30);
		trackingText = new TextDAO(FrameVO.font18, trackLayer, 1);
		trackingText.setBounds(130, 420, 180, 30);
		trackingBtn = new ButtonDAO();
		trackingBtn.makeGrayButton("조회하기", trackLayer, 1);
		trackingBtn.setBounds(320, 420, 120, 30);
		
		trackingBtn.addActionListener(trackOrder);
		
		backBtn = new ButtonDAO();
		backBtn.makeBlueButton("돌아가기", trackLayer, 1);
		backBtn.setBounds(30, 640, 400, 50);
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				trackFrame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		});
		
		//아이콘이미지
		new ImageDAO().showTitleIcon(trackFrame);
//		trackFrame.setVisible(true);
	}
	
	//송장번호와 일치하는 index찾기
	public int trackingIndex(String trackingNum) {
		trackingIndex = -1;
		for (int i = 0; i < orderList.size(); i++) {
			if(orderList.get(i).getTrackingNumber().equals(trackingNum)) {
				trackingIndex = i;
			}	
		}
		return trackingIndex;
	}
	
	//주문 상세내역 보기
	ActionListener showOrder = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String trackingNum = JOptionPane.showInputDialog(trackFrame, "송장번호를 입력하세요").trim();

			trackingIndex = trackingIndex(trackingNum);
			if(trackingIndex==-1) {
				JOptionPane.showMessageDialog(trackFrame, "조회결과가 없습니다", "주문배송조회", JOptionPane.ERROR_MESSAGE);
			}else {	//송장번호-날짜-물건-코드-크기-무게-박스개수-비고-결제요금-수령인이름-전화-주소
				vo = orderList.get(trackingIndex);
				String detail = "송장번호 : "+ vo.getTrackingNumber()+"\r\n"
								+"주문날짜 : "+ vo.getDate()+"\r\n"
								+"내용물 : "+vo.getStuff()+"\r\n"
								+"크기 / 무게 : "+ vo.getSize()+"cm / "+ vo.getWeight()+"kg"+"\r\n"
								+"박스개수 : "+vo.getBox()+"개"+"\r\n"
								+"요청사항 : "+vo.getNote()+"\r\n"
								+"결제요금 : "+vo.getCharge()+"\r\n"
								+"수령자 이름 : "+vo.getName()+"\r\n"
								+"수령자 전화 : "+vo.getPhone()+"\r\n"
								+"수령자 주소 : \r\n"+vo.getAddress()+"\r\n";
				JOptionPane.showMessageDialog(trackFrame, detail, "주문배송조회", JOptionPane.PLAIN_MESSAGE);
			}			
		}
	};
	
	//배송조회
	ActionListener trackOrder = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String trackingNum = trackingText.getText().toString().trim();
			trackingIndex = trackingIndex(trackingNum);
			String[] arMsg = {"배송중입니다", "조금만 기다려주세요!", "배송이 완료되었습니다", "다음에 또 이용해주세요"};
			
			if(trackingIndex == -1) {
				JOptionPane.showMessageDialog(trackFrame, "조회결과가 없습니다", "주문배송조회", JOptionPane.ERROR_MESSAGE);
			}else {
				vo = orderList.get(trackingIndex);
				resultLabel = new LabelDAO("", FrameVO.font18, trackLayer, 1);
				resultLabel.setBounds(220, 510, 200, 30);
				resultLabel.setHorizontalAlignment(JLabel.CENTER);
				result2Label = new LabelDAO("", FrameVO.font18, trackLayer, 1);
				result2Label.setBounds(220, 540, 200, 30);
				result2Label.setHorizontalAlignment(JLabel.CENTER);				
				
				//주문날짜와 현재날짜가 동일하면 '상품준비중', +1이면 '배송중', +2면 '배송완료'
				//주문날짜
				String[] orderDate = vo.getDate().split("-");
				int month1 = Integer.parseInt(orderDate[1]);
				int day1 = Integer.parseInt(orderDate[2]);
				
				//현재날짜
				String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String[] nowDate = now.split("-");
				int month2 = Integer.parseInt(nowDate[1]);
				int day2 = Integer.parseInt(nowDate[2]);
				
				if(month1<=month2) {
					if(day1==day2) {
						//이미지panel
						JPanel imgPanel = new JPanel();
						imgPanel.setBounds(20, 440, 200, 200);
						imgPanel.setBackground(Color.WHITE);
						JLabel imgLabel = new JLabel();
						ImageIcon icon = new ImageIcon("image/truck.gif");
						imgLabel.setIcon(icon);
						imgPanel.add(imgLabel);
						trackLayer.add(imgPanel, new Integer(0));
						resultLabel.setText(arMsg[0]);
						result2Label.setText(arMsg[1]);
					}else if((day2-day1)==1) {
						//이미지panel
						JPanel imgPanel1 = new JPanel();
						imgPanel1.setBounds(20, 440, 200, 200);
						imgPanel1.setBackground(Color.WHITE);
						JLabel imgLabel1 = new JLabel();
						ImageIcon icon1 = new ImageIcon("image/arrival.gif");
						imgLabel1.setIcon(icon1);
						imgPanel1.add(imgLabel1);
						trackLayer.add(imgPanel1, new Integer(0));
						resultLabel.setText(arMsg[2]);
						result2Label.setText(arMsg[3]);
					}
				}
			}
		}
	};
}
