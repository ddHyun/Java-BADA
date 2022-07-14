package dao;

import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dto.FrameVO;

public class TextDAO extends JTextField{
	
//	JTextField textfield;		
	
	public TextDAO(Font font, JLayeredPane layer, int num) {
		this.setFont(font);
		layer.add(this, new Integer(num));
	}		

}
