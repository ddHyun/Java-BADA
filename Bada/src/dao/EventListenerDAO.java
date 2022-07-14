package dao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EventListenerDAO {

	public EventListenerDAO() {}
	
	//화면전환(버튼 이벤트)
	public void movePage(JButton button, JFrame frame1, JFrame frame2) {
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				frame2.setVisible(true);
			}
		});
	}
	
	//화면전환(마우스 이벤트)
	public void movePage(JFrame frame1, JFrame frame2) {
		frame1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame1.dispose();
				frame2.setVisible(true);
			}
		});
	}
	
		
	//프로그램 종료(버튼 이벤트)
	public void quit(JButton button, JFrame frame) {
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);			
			}
		});
	}
	
	//textField 클릭 시 안에 적힌 내용 삭제(마우스 이벤트)
	public void removeText(JTextField textfield) {
		textfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textfield.setText("");
			}
		});
	}
}
