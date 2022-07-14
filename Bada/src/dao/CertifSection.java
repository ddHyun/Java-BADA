package dao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CertifSection{
	//������ȣ ���� �� ���� ��ġ,����ġ �޼���
	
	String num;
	
	public CertifSection(JFrame frame, JTextField textfield, JButton btn1, JButton btn2) {
		textfield.setEnabled(true);
		make6Num(frame, btn1, btn2);
		
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("������ȣȮ�� �޼��� :"+num);
				if(!textfield.getText().equals(num)) {
					JOptionPane.showMessageDialog(frame, "��ȣ�� ��ġ���� �ʽ��ϴ�.\r\n�ٽ� �õ����ּ���.", 
							"�޴���ȭ ����", JOptionPane.ERROR_MESSAGE);	
					new EventListenerDAO().removeText(textfield);
					
					num = "";
					for (int i = 0; i < 6; i++) {
						int rnd = new Random().nextInt(9);
						num += ""+rnd;
					}
					
					JOptionPane.showMessageDialog(frame, "������ȣ [ "+num+" ]�� ȭ�鿡 �Է����ּ���.", 
							"�޴���ȭ ����", JOptionPane.PLAIN_MESSAGE);		
				}else {
					JOptionPane.showMessageDialog(frame, "������ �Ϸ�Ǿ����ϴ�.", 
							"�޴���ȭ ����", JOptionPane.PLAIN_MESSAGE);	
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
		
		JOptionPane.showMessageDialog(frame, "������ȣ [ "+num+" ]�� ȭ�鿡 �Է����ּ���.", 
				"�޴���ȭ ����", JOptionPane.PLAIN_MESSAGE);			
		
		System.out.println("����������ȣ(num): "+num);
		btn1.setVisible(false);
		btn2.setVisible(true);
	}
	
}
