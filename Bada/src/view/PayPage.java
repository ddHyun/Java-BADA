package view;

import dao.FrameDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dto.FrameVO;

public class PayPage {
	
	FrameDAO payFrame;
	PanelDAO payLayer;
	LabelDAO titleLabel;
	
	public PayPage() {
		payFrame = new FrameDAO();
		payFrame.makeFrame();
		payLayer = new PanelDAO();
		payLayer.makeLayer();
		payFrame.add(payLayer);
		
		new PanelDAO().makeColorPanel(payLayer);
		titleLabel = new LabelDAO("결제하기", FrameVO.font40, payLayer, 1);
		titleLabel.makeTitleLabel();

	}
	

}
