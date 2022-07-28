package view;

import dao.ButtonDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dto.FrameVO;

public class PayPage2 {
	//�����Ϸ�������
	
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
		
		messageLabel = new LabelDAO("�ֹ��� ���� ó���Ǿ����ϴ�", FrameVO.font30, pay2Layer, 1);
		messageLabel.setBounds(40, 300, 400, 50);
		
		//�ֹ����� ��ȸ��ư
		
		//�����ȸ ��ư
		
		//�������̹���
		new ImageDAO().showTitleIcon(pay2Frame);
		pay2Frame.setVisible(true);
	}

}
