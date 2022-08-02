package view;

import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dto.FrameVO;

public class TrackPage {
	//�ֹ�����, �����ȸ ������
	
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
		titleLabel = new LabelDAO("�����ȸ", FrameVO.font40, trackLayer, 1);
		titleLabel.makeTitleLabel();
		
		
		//�������̹���
		new ImageDAO().showTitleIcon(trackFrame);
		trackFrame.setVisible(true);
	}
}
