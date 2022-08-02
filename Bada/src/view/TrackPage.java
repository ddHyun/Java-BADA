package view;

import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dto.FrameVO;

public class TrackPage {
	//주문내역, 배송조회 페이지
	
	FrameDAO trackFrame;
	PanelDAO trackLayer;
	LabelDAO titleLabel;

	public TrackPage() {
		trackFrame = new FrameDAO();
		trackFrame.makeFrame();
		trackLayer = new PanelDAO();
		trackLayer.makeLayer();
		trackFrame.add(trackLayer);
		
		new PanelDAO().makeColorPanel(trackLayer);
		titleLabel = new LabelDAO("배송조회", FrameVO.font40, trackLayer, 1);
		titleLabel.makeTitleLabel();
		
		
		//아이콘이미지
		new ImageDAO().showTitleIcon(trackFrame);
		trackFrame.setVisible(true);
	}
}
