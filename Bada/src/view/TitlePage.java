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
		//프레임 & 레이어
		titleFrame = new FrameDAO();
		titleLayer = new PanelDAO();
		titleLayer.makeLayer();		
		titleFrame.add(titleLayer);
		titleFrame.setVisible(true);
		
		//배경이미지(타이틀)
		new ImageDAO().showMovingImg(new FrameVO().titleGIF, titleLayer, 480, 800);		
		
		//아이콘이미지
		new ImageDAO().showTitleIcon(titleFrame);
		
		//화면전환 이벤트	
		new EventListenerDAO().movePage(titleFrame, new LoginPage().loginFrame);
		
	}
}
