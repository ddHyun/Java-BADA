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

	//결제페이지
	
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
		titleLabel = new LabelDAO("주문확인&결제", FrameVO.font40, order3Layer, 1);
		titleLabel.makeTitleLabel();
		
		receiverInfo = new OrderPage().receiverInfo();	
//		StuffVO stuffVO = new OrderPage2().getStuffInfo();
//		
//			System.out.println("수령자 이름: "+receiverInfo.get(0));
//			System.out.println("수령자 전화번호: "+receiverInfo.get(1));
//			System.out.println("수령자 주소: "+receiverInfo.get(2));
//			System.out.println("물품 크기: "+stuffVO.getSize());
//			System.out.println("물품 무게: "+stuffVO.getWeight());
//			System.out.println("물품 코드: "+stuffVO.getCode());
//			System.out.println("물품 내용물: "+stuffVO.getStuff());
//			System.out.println("물품 박스갯수: "+stuffVO.getBox());
//			System.out.println("물품 주의사항: "+stuffVO.getNote());
			
			
	
		
		//아이콘이미지
		new ImageDAO().showTitleIcon(order3Frame);
		
	}
}
