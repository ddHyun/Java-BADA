package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.ButtonDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dto.FrameVO;

public class PayPage2 {
	//결제완료페이지
	
	FrameDAO pay2Frame;
	PanelDAO pay2Layer;
	LabelDAO titleLabel, messageLabel;
	ButtonDAO backBtn;
	
	public PayPage2() {
		pay2Frame = new FrameDAO();
		pay2Frame.makeFrame();
		pay2Layer = new PanelDAO();
		pay2Layer.makeLayer();
		pay2Frame.add(pay2Layer);
		
		new PanelDAO().makeColorPanel(pay2Layer);
		titleLabel = new LabelDAO("결제완료", FrameVO.font40, pay2Layer, 1);
		titleLabel.makeTitleLabel();
		
		ImageDAO checkImg = new ImageDAO();
		checkImg.showImage("image/check.jpg", 130, 200, 200, 200, pay2Frame, pay2Layer);
		
		messageLabel = new LabelDAO("주문이 정상 접수되었습니다", FrameVO.font30, pay2Layer, 1);
		messageLabel.setBounds(40, 430, 400, 50);
		
		backBtn = new ButtonDAO();
		backBtn.makeBlueButton("메인페이지로 이동", pay2Layer, 1);
		backBtn.setBounds(30, 500, 400, 50);
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pay2Frame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		});
		
		//아이콘이미지
		new ImageDAO().showTitleIcon(pay2Frame);
//		pay2Frame.setVisible(true);
	}

}
