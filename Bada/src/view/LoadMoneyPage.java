package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

import dao.ButtonDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dao.TextDAO;
import dto.FrameVO;

public class LoadMoneyPage {
	//충전페이지
	
	FrameDAO loadMoneyFrame;
	PanelDAO loadMoneyLayer;
	LabelDAO titleLabel, currentMoneyLabel, currentMoney2Label, loadMoneyLabel, loadMoney2Label,
			totalMoneyLabel, totalMoney2Label;
	ButtonDAO btn1000, btn10000, btn50000, btnC;
	TextDAO text;
	int currentMoney, loadMoney, totalLoadedMoney;
	
	public LoadMoneyPage() {
		loadMoneyFrame = new FrameDAO();
		loadMoneyFrame.makeFrame();
		loadMoneyLayer = new PanelDAO();
		loadMoneyLayer.makeLayer();
		loadMoneyFrame.add(loadMoneyLayer);
		
		new PanelDAO().makeColorPanel(loadMoneyLayer);
		titleLabel = new LabelDAO("충전하기", FrameVO.font40, loadMoneyLayer, 1);
		titleLabel.makeTitleLabel();
		
		ImageDAO cardImage = new ImageDAO();
		cardImage.showImage("image/card.png", 60, 150, 400, 240, loadMoneyFrame, loadMoneyLayer);
		
		//1000, 10000, 50000원버튼
		btn50000 = new ButtonDAO();
		btn50000.makeGrayButton("50000원", loadMoneyLayer, 1);
		btn50000.setBounds(70, 400, 150, 40);
		
		btn10000 = new ButtonDAO();
		btn10000.makeGrayButton("10000원", loadMoneyLayer, 1);
		btn10000.setBounds(240, 400, 150, 40);
		
		btn1000 = new ButtonDAO();
		btn1000.makeGrayButton("1000원", loadMoneyLayer, 1);
		btn1000.setBounds(70, 450, 150, 40);
		
		btnC = new ButtonDAO();
		btnC.makeGrayButton("초기화", loadMoneyLayer, 1);
		btnC.setBounds(240, 450, 150, 40);
		
		//버튼 클릭시 금액 추가
		plusMoney(btn50000);
		plusMoney(btn10000);
		plusMoney(btn1000);
		plusMoney(btnC);
		
		//충전금액
		loadMoneyLabel = new LabelDAO("충전금액", FrameVO.font20, loadMoneyLayer, 1);
		loadMoneyLabel.setBounds(70, 360, 200, 30);
		loadMoney2Label = new LabelDAO(loadMoney+"원", FrameVO.font20, loadMoneyLayer, 1);
		loadMoney2Label.setBounds(230, 360, 150, 30);
		loadMoney2Label.setHorizontalAlignment(JLabel.TRAILING);
		loadMoney2Label.setForeground(Color.BLUE);		

		//현재잔액 
		currentMoneyLabel = new LabelDAO("현재잔액", FrameVO.font20, loadMoneyLayer, 1);
		currentMoneyLabel.setBounds(70, 410, 200, 30);
		currentMoney2Label = new LabelDAO(currentMoney+"원", FrameVO.font20, loadMoneyLayer, 1);
		currentMoney2Label.setBounds(230, 410, 150, 30);
		currentMoney2Label.setHorizontalAlignment(JLabel.TRAILING);
		
		
		//충전후잔액
		totalMoneyLabel = new LabelDAO("충전 후 잔액", FrameVO.font20, loadMoneyLayer, 1);
		totalMoneyLabel.setBounds(70, 460, 200, 30);
		totalMoney2Label = new LabelDAO(totalLoadedMoney+"원", FrameVO.font20, loadMoneyLayer, 1);
		totalMoney2Label.setBounds(230, 460, 150, 30);
		totalMoney2Label.setHorizontalAlignment(JLabel.TRAILING);
		totalMoney2Label.setForeground(Color.RED);
		
		
		//아이콘이미지
		new ImageDAO().showTitleIcon(loadMoneyFrame);
		loadMoneyFrame.setVisible(true);
		
	}
	
	//버튼 클릭시 금액 추가 메서드
	public void plusMoney(JButton btn) {
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn50000) {
					loadMoney += 50000;
				}
				if(e.getSource()==btn10000) {
					loadMoney += 10000;
				}
				if(e.getSource()==btn1000) {
					loadMoney += 1000;
				}
				if(e.getSource()==btnC) {
					loadMoney = 0;
				}
				loadMoney2Label.setText(loadMoney+"원");
			}
		});
	}		
}
