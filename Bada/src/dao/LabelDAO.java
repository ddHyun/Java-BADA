package dao;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

import dto.FrameVO;

public class LabelDAO extends JLabel{

	JLabel label;
	
	public LabelDAO() {}
	
	public LabelDAO(String text, Font font, JLayeredPane layer, int num) {
		this.setText(text);
		this.setFont(font);	
		//padding 넣어주기
		this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		layer.add(this, new Integer(num));
	}
	
	public void makeTitleLabel() {
		this.setBounds(0, 15, 460, 100);
		this.setHorizontalAlignment(JLabel.CENTER);
	}
	
	public void getTitle(JLayeredPane layer) {
		//앱 이름
		JLabel title1 = new JLabel(new FrameVO().titleApp1);
		JLabel title2 = new JLabel(new FrameVO().titleApp2);
		JLabel title3 = new JLabel(new FrameVO().titleApp);
		title1.setBounds(60, 35, 60, 25);
		title1.setFont(FrameVO.font20);
		title2.setBounds(60, 60, 60, 25);
		title2.setFont(FrameVO.font20);
		title3.setBounds(105, 30, 300, 60);
		title3.setFont(FrameVO.font60);		
		layer.add(title1, new Integer(1));
		layer.add(title2, new Integer(1));
		layer.add(title3, new Integer(1));
	}
}
