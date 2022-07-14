package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import dao.EventListenerDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.PanelDAO;
import dto.FrameVO;

public class TitlePage{
	
	FrameDAO titleFrame;	
	PanelDAO titleLayer;
	
	public TitlePage() {
		//������ & ���̾�
		titleFrame = new FrameDAO();
		titleLayer = new PanelDAO();
		titleLayer.makeLayer();		
		titleFrame.add(titleLayer);
		titleFrame.setVisible(true);
		
		//����̹���(Ÿ��Ʋ)
		new ImageDAO().showMovingImg(new FrameVO().titleGIF, titleLayer, 480, 800);		
		
		//�������̹���
		new ImageDAO().showTitleIcon(titleFrame);
		
		//ȭ����ȯ �̺�Ʈ	
		new EventListenerDAO().movePage(titleFrame, new LoginPage().loginFrame);
		
	}
}
