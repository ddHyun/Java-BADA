package view;

import java.util.ArrayList;
import java.util.List;

import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dto.FrameVO;
import dto.StuffVO;

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
//		StuffVO stuffVO = new OrderPage2().getStuffInfo();
//		
//			System.out.println("������ �̸�: "+receiverInfo.get(0));
//			System.out.println("������ ��ȭ��ȣ: "+receiverInfo.get(1));
//			System.out.println("������ �ּ�: "+receiverInfo.get(2));
//			System.out.println("��ǰ ũ��: "+stuffVO.getSize());
//			System.out.println("��ǰ ����: "+stuffVO.getWeight());
//			System.out.println("��ǰ �ڵ�: "+stuffVO.getCode());
//			System.out.println("��ǰ ���빰: "+stuffVO.getStuff());
//			System.out.println("��ǰ �ڽ�����: "+stuffVO.getBox());
//			System.out.println("��ǰ ���ǻ���: "+stuffVO.getNote());
			
			
	
		
		//�������̹���
		new ImageDAO().showTitleIcon(order3Frame);
		
	}
}
