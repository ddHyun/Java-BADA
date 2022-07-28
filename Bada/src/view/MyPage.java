package view;

import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dto.FrameVO;

public class MyPage {
	FrameDAO mypageFrame;
	PanelDAO mypageLayer;
	LabelDAO titleLabel;
	
	public MyPage() {
		mypageFrame = new FrameDAO();
		mypageFrame.makeFrame();
		mypageLayer = new PanelDAO();
		mypageLayer.makeLayer();
		mypageFrame.add(mypageLayer);
		
		new PanelDAO().makeColorPanel(mypageLayer);
		String name = new MainPage().getIdxName();
		titleLabel = new LabelDAO(name+"님 마이페이지", FrameVO.font40, mypageLayer, 1);
		titleLabel.makeTitleLabel();
		
		//아이콘이미지
		new ImageDAO().showTitleIcon(mypageFrame);
		mypageFrame.setVisible(true);
	}

}
