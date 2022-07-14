package dao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CertifSection{
	//인증번호 생성 후 인증 일치,불일치 메서드
	
	String num;
	
	public CertifSection(JFrame frame, JTextField textfield, JButton btn1, JButton btn2) {
		textfield.setEnabled(true);
		make6Num(frame, btn1, btn2);
		
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("인증번호확인 메서드 :"+num);
				if(!textfield.getText().equals(num)) {
					JOptionPane.showMessageDialog(frame, "번호가 일치하지 않습니다.\r\n다시 시도해주세요.", 
							"휴대전화 인증", JOptionPane.ERROR_MESSAGE);	
					new EventListenerDAO().removeText(textfield);
					
					num = "";
					for (int i = 0; i < 6; i++) {
						int rnd = new Random().nextInt(9);
						num += ""+rnd;
					}
					
					JOptionPane.showMessageDialog(frame, "인증번호 [ "+num+" ]를 화면에 입력해주세요.", 
							"휴대전화 인증", JOptionPane.PLAIN_MESSAGE);		
				}else {
					JOptionPane.showMessageDialog(frame, "인증이 완료되었습니다.", 
							"휴대전화 인증", JOptionPane.PLAIN_MESSAGE);	
					btn2.setEnabled(false);
					textfield.setEnabled(false);
				}		
			}
		});
		
	}
	
	public void make6Num(JFrame frame, JButton btn1, JButton btn2) {
		num = "";
		for (int i = 0; i < 6; i++) {
			int rnd = new Random().nextInt(9);
			num += ""+rnd;
		}
		
		JOptionPane.showMessageDialog(frame, "인증번호 [ "+num+" ]를 화면에 입력해주세요.", 
				"휴대전화 인증", JOptionPane.PLAIN_MESSAGE);			
		
		System.out.println("랜덤인증번호(num): "+num);
		btn1.setVisible(false);
		btn2.setVisible(true);
	}
	
}
