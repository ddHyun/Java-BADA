package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.ButtonDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dto.FrameVO;
import dto.MoneyVO;
import dto.UserVO;

public class LoadMoneyPage {
	//충전페이지
	
	FrameDAO loadMoneyFrame;
	PanelDAO loadMoneyLayer;
	LabelDAO titleLabel, currentMoneyLabel, currentMoney2Label, loadMoneyLabel, loadMoney2Label,
			totalMoneyLabel, totalMoney2Label, messageLabel;
	ButtonDAO btn1000, btn10000, btn50000, btnC, yesBtn, noBtn, payBtn;
	int currentMoney, loadMoney, totalLoadedMoney;
	ArrayList<UserVO> userList = new ArrayList<>();
	MoneyVO mVO = MoneyVO.getInstance();
	
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
		
		//충전 -> '예''아니요'버튼
		noBtn = new ButtonDAO();
		noBtn.makeBlueButton("아니오", loadMoneyLayer, 1);
		noBtn.setBounds(20, 640, 200, 50);
		noBtn.setVisible(false);
		//'아니오'버튼 클릭 : 충전 -> 메인페이지 이동
		noBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadMoneyFrame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		});
		
		yesBtn = new ButtonDAO();
		yesBtn.makeBlueButton("네", loadMoneyLayer, 1);
		yesBtn.setBounds(240, 640, 200, 50);
		yesBtn.setVisible(false);
		
		//'네'버튼 클릭 : 충전하기
		loadMoney();
				
		//1000, 10000, 50000원버튼
		btn50000 = new ButtonDAO();
		btn50000.makeGrayButton("50000원", loadMoneyLayer, 1);
		btn50000.setBounds(40, 400, 120, 40);
		
		btn10000 = new ButtonDAO();
		btn10000.makeGrayButton("10000원", loadMoneyLayer, 1);
		btn10000.setBounds(170, 400, 120, 40);
		
		btn1000 = new ButtonDAO();
		btn1000.makeGrayButton("1000원", loadMoneyLayer, 1);
		btn1000.setBounds(300, 400, 120, 40);
		
		btnC = new ButtonDAO();
		btnC.makeGrayButton("초기화", loadMoneyLayer, 1);
		btnC.setBounds(150, 460, 100, 30);
		
		//버튼 클릭시 금액 추가
		plusMoney(btn50000);
		plusMoney(btn10000);
		plusMoney(btn1000);
		plusMoney(btnC);
		
		//충전금액
		loadMoneyLabel = new LabelDAO("충전금액", FrameVO.font20, loadMoneyLayer, 1);
		loadMoneyLabel.setBounds(50, 460, 100, 30);
		loadMoney2Label = new LabelDAO(loadMoney+"원", FrameVO.font20, loadMoneyLayer, 1);
		loadMoney2Label.setBounds(250, 460, 150, 30);
		loadMoney2Label.setHorizontalAlignment(JLabel.TRAILING);
		loadMoney2Label.setForeground(Color.BLUE);		

		//현재잔액 
		currentMoneyLabel = new LabelDAO("현재잔액", FrameVO.font20, loadMoneyLayer, 1);
		currentMoneyLabel.setBounds(50, 510, 200, 30);
		System.out.println("mVO.getTotalMoney() : "+mVO.getTotalMoney());
		currentMoney = mVO.getTotalMoney();
		currentMoney2Label = new LabelDAO(currentMoney+"원", FrameVO.font20, loadMoneyLayer, 1);
		currentMoney2Label.setBounds(250, 510, 150, 30);
		currentMoney2Label.setHorizontalAlignment(JLabel.TRAILING);		
		
		//충전후잔액
		totalMoneyLabel = new LabelDAO("충전 후 잔액", FrameVO.font20, loadMoneyLayer, 1);
		totalMoneyLabel.setBounds(50, 560, 200, 30);
		totalMoney2Label = new LabelDAO(totalLoadedMoney+"원", FrameVO.font20, loadMoneyLayer, 1);
		totalMoney2Label.setBounds(250, 560, 150, 30);
		totalMoney2Label.setHorizontalAlignment(JLabel.TRAILING);
		totalMoney2Label.setForeground(Color.RED);
		
		//충전문구		
		messageLabel = new LabelDAO("충전하시겠습니까?", FrameVO.font18, loadMoneyLayer, 1);
		messageLabel.setBounds(20, 610, 400, 25);
		messageLabel.setHorizontalAlignment(JLabel.CENTER);
		messageLabel.setVisible(false);		
		
		//충전하러 가기 버튼
		payBtn = new ButtonDAO();
		payBtn.makeBlueButton("결제하러 가기", loadMoneyLayer, 1);
		payBtn.setBounds(30, 640, 400, 50);
		payBtn.setVisible(false);
		
		//결제하러 가기 버튼 : 충전 -> 결제3페이지
		payBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadMoneyFrame.dispose();
				new PayPage().payFrame.setVisible(true);
			}
		});
		
		
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
					messageLabel.setVisible(true);
					noBtn.setVisible(true);
					yesBtn.setVisible(true);
				}
				if(e.getSource()==btn10000) {
					loadMoney += 10000;
					messageLabel.setVisible(true);
					noBtn.setVisible(true);
					yesBtn.setVisible(true);
				}
				if(e.getSource()==btn1000) {
					loadMoney += 1000;
					messageLabel.setVisible(true);
					noBtn.setVisible(true);
					yesBtn.setVisible(true);
				}
				if(e.getSource()==btnC) {
					loadMoney = 0;
					messageLabel.setVisible(false);
					noBtn.setVisible(false);
					yesBtn.setVisible(false);
				}
				loadMoney2Label.setText(loadMoney+"원");
				totalLoadedMoney = loadMoney+mVO.getTotalMoney();
				totalMoney2Label.setText(totalLoadedMoney+"원");
			}
		});
	}	
	
	//'네'버튼 클릭시 충전하기 메서드
	public void loadMoney() {
		yesBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int chance = 3;
				
				while(chance>0) {					
					String input = 
							JOptionPane.showInputDialog(loadMoneyFrame, "비밀번호를 입력하세요(기회 "+chance+"번)", 
									"충전하기", JOptionPane.DEFAULT_OPTION);
//					userList = new JoinPage().getUserInfo();
//					UserVO vo = userList.get(UserVO.index);
//					String pwd = vo.getPw();
					String pwd = new MainPage().getLoginUser().getPw();
					if(input.equals(pwd)) {
						mVO.setLoadMoney(loadMoney);
						mVO.setTotalMoney(loadMoney+mVO.getTotalMoney());
						JOptionPane.showMessageDialog(loadMoneyFrame, "충전이 완료되었습니다", 
								"충전하기", JOptionPane.INFORMATION_MESSAGE);
						currentMoney2Label.setText(mVO.getTotalMoney()+"원");
						loadMoney = 0;
						loadMoney2Label.setText(loadMoney+"원");
						messageLabel.setVisible(false);
						yesBtn.setVisible(false);
						noBtn.setVisible(false);
						payBtn.setVisible(true);
						break;
					}else if(chance > 1){
						--chance;
						JOptionPane.showMessageDialog(loadMoneyFrame, "비밀번호 오류입니다.\r\n다시 시도해 주세요", 
								"충전하기", JOptionPane.ERROR_MESSAGE);					
					}else if(chance == 1) {
						--chance;
						JOptionPane.showMessageDialog(loadMoneyFrame, "비밀번호 3회 오류입니다. 처음부터 다시 시도해 주세요", 
								"충전하기", JOptionPane.ERROR_MESSAGE);
						loadMoneyFrame.dispose();
						new MainPage().mainFrame.setVisible(true);
					}
				}
			}
		});
	}
}
