package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dao.ButtonDAO;
import dao.EventListenerDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dto.FrameVO;

public class LoginPage {

	FrameDAO loginFrame;
	PanelDAO loginLayer;
	ButtonDAO joinBtn, loginBtn, quitBtn;
	
	public LoginPage() {
		//������&���̾�
		loginFrame = new FrameDAO();
		loginLayer = new PanelDAO();
		loginLayer.makeLayer();
		loginFrame.add(loginLayer);
		
		//Ÿ��Ʋ
		new LabelDAO().getTitle(loginLayer);		
		//Ÿ��Ʋ ���panel
		new PanelDAO().makeColorPanel(loginLayer);
		
		//�̹���panel
		JPanel imgPanel = new JPanel();
		imgPanel.setBounds(0, 280, 480, 185);
		imgPanel.setBackground(Color.WHITE);
		JLabel imgLabel = new JLabel();
		ImageIcon icon = new ImageIcon("image/loginImg.gif");
		imgLabel.setIcon(icon);
		imgPanel.add(imgLabel);
		loginLayer.add(imgPanel, new Integer(0));
		
		//�α��ι�ư
		loginBtn = new ButtonDAO();
		loginBtn.makeBlueButton("�α���", loginLayer, 0);
		loginBtn.setBounds(0, 550, 480, 50);
		
		//�α��ι�ư Ŭ���� �α����������� �������̵� �̺�Ʈ
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loginFrame.dispose();
				new LoginPage2().login2Frame.setVisible(true);
			}
		});
						
		//ȸ�����Թ�ư
		joinBtn =new ButtonDAO();
		joinBtn.makeBlueButton("ȸ������", loginLayer, 0);
		joinBtn.setBounds(0, 615, 480, 50);
		
		//ȸ�����Թ�ư Ŭ���� ȸ�������������� �������̵� �̺�Ʈ		
		joinBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loginFrame.dispose();
				new JoinPage().joinFrame.setVisible(true);
			}
		});
		
		//�������ư
		quitBtn = new ButtonDAO();
		quitBtn.makeBlueButton("������", loginLayer, 0);
		quitBtn.setBounds(0, 680, 480, 50);
		
		//�������̹���
		new ImageDAO().showTitleIcon(loginFrame);
		
		//���α׷� ���� �̺�Ʈ
		new EventListenerDAO().quit(quitBtn, loginFrame);
	}	
}
