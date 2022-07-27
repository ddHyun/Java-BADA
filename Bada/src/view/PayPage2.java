package view;

import dao.FrameDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dto.FrameVO;

public class PayPage2 {
	//결제완료페이지
	
	FrameDAO pay2Frame;
	PanelDAO pay2Layer;
	LabelDAO titleLabel;
	
	public PayPage2() {
		pay2Frame = new FrameDAO();
		pay2Frame.makeFrame();
		pay2Layer = new PanelDAO();
		pay2Layer.makeLayer();
		pay2Frame.add(pay2Layer);
		
		new PanelDAO().makeColorPanel(pay2Layer);
		titleLabel = new LabelDAO("결제완료", FrameVO.font40, pay2Layer, 1);
		titleLabel.makeTitleLabel();
	}

}
