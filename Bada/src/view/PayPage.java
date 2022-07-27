package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import dao.ButtonDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dto.FrameVO;
import dto.MoneyVO;
import dto.StuffVO;

public class PayPage {
	
	FrameDAO payFrame;
	PanelDAO payLayer;
	ButtonDAO loadMoneyBtn, payBtn, cancelBtn;
	LabelDAO titleLabel, basicChargeLabel, boxNumLabel, distanceLabel1, distanceLabel2, lineLabel,
				totalChargeLabel, loadMoneyLabel;
	int basicCharge, boxNum, distance, totalCharge, loadMoney = 0;
	
	public PayPage() {
		payFrame = new FrameDAO();
		payFrame.makeFrame();
		payLayer = new PanelDAO();
		payLayer.makeLayer();
		payFrame.add(payLayer);
		
		new PanelDAO().makeColorPanel(payLayer);
		titleLabel = new LabelDAO("결제하기", FrameVO.font40, payLayer, 1);
		titleLabel.makeTitleLabel();
		
		calCharge();
		
		basicChargeLabel = new LabelDAO("기본요금                                         "+basicCharge, FrameVO.font20, payLayer, 1);
		basicChargeLabel.setBounds(20, 250, 400, 30);
		basicChargeLabel.setHorizontalAlignment(JLabel.TRAILING);
		boxNumLabel = new LabelDAO("박스개수                               x          "+boxNum, FrameVO.font20, payLayer, 1);
		boxNumLabel.setBounds(20, 300, 400, 30);
		boxNumLabel.setHorizontalAlignment(JLabel.TRAILING);
		distanceLabel1 = new LabelDAO("거리                                     + ", FrameVO.font20, payLayer, 1);
		distanceLabel1.setBounds(65, 350, 290, 30);
		distanceLabel2 = new LabelDAO(""+distance, FrameVO.font20, payLayer, 1);
		distanceLabel2.setBounds(310, 350, 110, 30);
		distanceLabel2.setHorizontalAlignment(JLabel.TRAILING);
		lineLabel = new LabelDAO("----------------------------------------------------", FrameVO.font20, payLayer, 1);
		lineLabel.setBounds(20, 400, 400, 10);
		lineLabel.setHorizontalAlignment(JLabel.TRAILING);
		totalChargeLabel = new LabelDAO("총 결제금액   "+totalCharge+"원", FrameVO.font25B, payLayer, 1);
		totalChargeLabel.setBounds(20, 430, 400, 30);
		//JLabel내 text 색상 변경
		totalChargeLabel.setForeground(Color.RED);
		totalChargeLabel.setHorizontalAlignment(JLabel.TRAILING);
		
		//충전금액
		
		//내 충전금액 
		loadMoneyLabel = new LabelDAO("나의 충전잔액   "+loadMoney+"원", FrameVO.font20, payLayer, 1);
		loadMoneyLabel.setBounds(20, 590, 400, 30);
		loadMoneyLabel.setHorizontalAlignment(JLabel.TRAILING);
		loadMoneyLabel.setForeground(Color.BLUE);
		
		//취소, 결제버튼
		cancelBtn = new ButtonDAO();
		cancelBtn.makeGrayButton("취소하기", payLayer, 1);
		cancelBtn.setBounds(20, 640, 200, 50);
		cancelBtn.setFont(FrameVO.font30);
		
		///취소버튼 : 결제 -> 메인페이지 이동
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				payFrame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		});
		
		payBtn = new ButtonDAO();
		payBtn.makeBlueButton("결제하기", payLayer, 1);
		payBtn.setBounds(240, 640, 200, 50);
		//결제금액보다 충전금액이 적거나 충전금액이 0인 경우 '충전하기'버튼 보여지기
		if(totalCharge > loadMoney || loadMoney == 0) {
			payBtn.setVisible(false);
			loadMoneyBtn = new ButtonDAO();
			loadMoneyBtn.makeBlueButton("충전하기", payLayer, 1);
			loadMoneyBtn.setBounds(240, 640, 200, 50);
			
			//충전버튼 클릭 : 결제 -> 충전페이지 이동
			loadMoneyBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					payFrame.setVisible(false);
					new LoadMoneyPage().loadMoneyFrame.setVisible(true);
				}
			});
		}
		
		//아이콘이미지
		new ImageDAO().showTitleIcon(payFrame);
//		payFrame.setVisible(true);

	}
	
	//결제금액 계산메서드
	public void calCharge() {		
		int dis = new OrderPage().senderZip - new OrderPage().receiverZip;
		int boxSize = (int)StuffVO.getInstance().getSize();
		int boxWeight = (int)StuffVO.getInstance().getWeight();
		int[] charge = {2500, 3000, 4000, 5500, 7000};
		
		//박스갯수
		boxNum = StuffVO.getInstance().getBox();
		//거리계산
		if(dis<0) {
			distance = -dis * 50;
		}else {
			distance = dis * 50;
		}
		//기본요금 계산
		if(boxSize <= 60) {
			if(0 <= boxWeight && boxWeight <= 2) {
				basicCharge = charge[0];
			}else if(2 < boxWeight && boxWeight <= 5) {
				basicCharge = charge[1];
			}else if(5 < boxWeight && boxWeight <= 10) {
				basicCharge = charge[2];
			}else if(10 < boxWeight && boxWeight <= 20) {
				basicCharge = charge[3];
			}else {
				basicCharge = charge[4];
			}
		}else if(boxSize <= 80) {
			if(0 <= boxWeight && boxWeight <= 5) {
				basicCharge = charge[1];
			}else if(5 < boxWeight && boxWeight <= 10) {
				basicCharge = charge[2];
			}else if(10 < boxWeight && boxWeight <= 20) {
				basicCharge = charge[3];
			}else {
				basicCharge = charge[4];
			}
		}else if(boxSize <= 100) {
			if(0 <= boxWeight && boxWeight <= 10) {
				basicCharge = charge[2];
			}else if(10 < boxWeight && boxWeight <= 20) {
				basicCharge = charge[3];
			}else {
				basicCharge = charge[4];
			}
		}else if(boxSize <= 120) {
			if(0 <= boxWeight && boxWeight <= 20) {
				basicCharge = charge[3];
			}else {
				basicCharge = charge[4];
			}
		}else {
			basicCharge = charge[4];
		}
		
		//총결제금액
		totalCharge = (basicCharge * boxNum) + distance;
		
		//결제내역조회를 위해 결제금액을 MoneyVO에 저장
		MoneyVO vo = MoneyVO.getInstance();
		vo.setCharge(totalCharge);		
	}
}
