package view;

import java.util.ArrayList;
import java.util.List;

import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dto.FrameVO;

public class OrderPage3 {

	//����������
	
	FrameDAO order3Frame;
	PanelDAO order3Layer;
	LabelDAO titleLabel;
	List<String> receiverInfo = new ArrayList<String>();
	
	public OrderPage3() {
		order3Frame = new FrameDAO();
		order3Frame.makeFrame();
		order3Layer = new PanelDAO();
		order3Layer.makeLayer();
		order3Frame.add(order3Layer);
		
		new PanelDAO().makeColorPanel(order3Layer);
		titleLabel = new LabelDAO("�ֹ�Ȯ��&����", FrameVO.font40, order3Layer, 1);
		titleLabel.makeTitleLabel();
		
		receiverInfo = new OrderPage().receiverInfo();		
			System.out.println(receiverInfo);
	
		
		//�������̹���
		new ImageDAO().showTitleIcon(order3Frame);
		
	}
}
