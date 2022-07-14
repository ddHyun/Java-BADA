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
		//프레임&레이어
		loginFrame = new FrameDAO();
		loginLayer = new PanelDAO();
		loginLayer.makeLayer();
		loginFrame.add(loginLayer);
		
		//타이틀
		new LabelDAO().getTitle(loginLayer);		
		//타이틀 배경panel
		new PanelDAO().makeColorPanel(loginLayer);
		
		//이미지panel
		JPanel imgPanel = new JPanel();
		imgPanel.setBounds(0, 280, 480, 185);
		imgPanel.setBackground(Color.WHITE);
		JLabel imgLabel = new JLabel();
		ImageIcon icon = new ImageIcon("image/loginImg.gif");
		imgLabel.setIcon(icon);
		imgPanel.add(imgLabel);
		loginLayer.add(imgPanel, new Integer(0));
		
		//로그인버튼
		loginBtn = new ButtonDAO();
		loginBtn.makeBlueButton("로그인", loginLayer, 0);
		loginBtn.setBounds(0, 550, 480, 50);
		
		//로그인버튼 클릭시 로그인페이지로 페이지이동 이벤트
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loginFrame.dispose();
				new LoginPage2().login2Frame.setVisible(true);
			}
		});
						
		//회원가입버튼
		joinBtn =new ButtonDAO();
		joinBtn.makeBlueButton("회원가입", loginLayer, 0);
		joinBtn.setBounds(0, 615, 480, 50);
		
		//회원가입버튼 클릭시 회원가입페이지로 페이지이동 이벤트		
		joinBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loginFrame.dispose();
				new JoinPage().joinFrame.setVisible(true);
			}
		});
		
		//나가기버튼
		quitBtn = new ButtonDAO();
		quitBtn.makeBlueButton("나가기", loginLayer, 0);
		quitBtn.setBounds(0, 680, 480, 50);
		
		//아이콘이미지
		new ImageDAO().showTitleIcon(loginFrame);
		
		//프로그램 종료 이벤트
		new EventListenerDAO().quit(quitBtn, loginFrame);
	}	
}
