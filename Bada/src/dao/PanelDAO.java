package dao;

import java.awt.Color;

import javax.swing.*;

import dto.FrameVO;

public class PanelDAO extends JLayeredPane{

	public PanelDAO() {}
	
	//레이어만들기
	public void makeLayer() {		
		setBounds(0, 0, 480, 800);
		setBackground(Color.WHITE);
		setLayout(null);
		//레이어 투명하게
		setOpaque(true);
	}	
	
	//상단 메인컬러페널
	public void makeColorPanel(JLayeredPane layer) {
		JPanel jpanel = new JPanel();
		jpanel.setBounds(0, 0, 480, 120);
		jpanel.setBackground(new FrameVO().panelColor);
		layer.add(jpanel, new Integer(0));
	}
	
}
