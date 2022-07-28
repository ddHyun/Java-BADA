package view;

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
	ButtonDAO orderResultBtn, trackingBtn;
	
	public PayPage2() {
		pay2Frame = new FrameDAO();
		pay2Frame.makeFrame();
		pay2Layer = new PanelDAO();
		pay2Layer.makeLayer();
		pay2Frame.add(pay2Layer);
		
		new PanelDAO().makeColorPanel(pay2Layer);
		titleLabel = new LabelDAO("", FrameVO.font40, pay2Layer, 1);
		titleLabel.makeTitleLabel();
		
		messageLabel = new LabelDAO("주문이 정상 처리되었습니다", FrameVO.font30, pay2Layer, 1);
		messageLabel.setBounds(40, 300, 400, 50);
		
		//주문내역 조회버튼
		
		//배송조회 버튼
		
		//아이콘이미지
		new ImageDAO().showTitleIcon(pay2Frame);
		pay2Frame.setVisible(true);
	}

}
