package dao;

import java.awt.Color;

import javax.swing.*;

import dto.FrameVO;

public class PanelDAO extends JLayeredPane{

	public PanelDAO() {}
	
	//���̾���
	public void makeLayer() {		
		setBounds(0, 0, 480, 800);
		setBackground(Color.WHITE);
		setLayout(null);
		//���̾� �����ϰ�
		setOpaque(true);
	}	
	
	//��� �����÷����
	public void makeColorPanel(JLayeredPane layer) {
		JPanel jpanel = new JPanel();
		jpanel.setBounds(0, 0, 480, 120);
		jpanel.setBackground(new FrameVO().panelColor);
		layer.add(jpanel, new Integer(0));
	}
	
}
