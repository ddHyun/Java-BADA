package dao;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

import dto.FrameVO;

public class ButtonDAO extends JButton{
	
	JButton button;
	
	public ButtonDAO() {}
	
	
	public void makeGrayButton(String text, JLayeredPane layer, int num) {
		this.setText(text);
		this.setFont(FrameVO.font20);
		this.setBackground(FrameVO.grayColor);
		this.setSize(115, 30);
		this.setFocusPainted(false);
		layer.add(this, new Integer(num));
	}
	
	public void makeBlueButton(String text, JLayeredPane layer, int num) {
		this.setText(text);
		this.setFont(FrameVO.font30);
		this.setBackground(FrameVO.btnColor);
		//��ư �� �ؽ�Ʈ �׵θ�
		this.setFocusPainted(false);
//		//�ձ� �𼭸�
		LineBorder line = new LineBorder(FrameVO.btnColor, 5, true);
		this.setBorder(line);
		layer.add(this, new Integer(num));
	}
	
	
}
