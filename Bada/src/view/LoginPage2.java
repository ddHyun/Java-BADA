package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import dao.ButtonDAO;
import dao.CertifSection;
import dao.EventListenerDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dao.TextDAO;
import dto.FrameVO;
import dto.UserVO;

public class LoginPage2 {
	
	FrameDAO login2Frame;
	PanelDAO login2Layer;
	LabelDAO idLabel, pwLabel;
	TextDAO idText;
	ButtonDAO idBtn, pwBtn, loginBtn, quitBtn;
	ArrayList<UserVO> arUser;
	int idx; // ���̵� ã��� idx
	ArrayList<UserVO> userList = new ArrayList<UserVO>();
	
	public LoginPage2() {
		//frame&layer
		login2Frame = new FrameDAO();
		login2Frame.makeFrame();
		login2Layer = new PanelDAO();
		login2Layer.makeLayer();
		login2Frame.add(login2Layer);
				
		//Ÿ��Ʋ
		new LabelDAO().getTitle(login2Layer);
		new PanelDAO().makeColorPanel(login2Layer);
		
		//ID&PW
		idLabel = new LabelDAO("  ���̵�", FrameVO.font25, login2Layer, 1);
		idLabel.setBounds(15, 250, 120, 30);
		idText = new TextDAO(FrameVO.font25, login2Layer, 1);
		idText.setBounds(145, 250, 290, 35);
		pwLabel = new LabelDAO("��й�ȣ", FrameVO.font25, login2Layer, 1);
		pwLabel.setBounds(15, 310, 120, 30);
		JPasswordField pwText = new JPasswordField();
		pwText.setBounds(145, 310, 290, 35);
		pwText.setFont(FrameVO.font25);
		login2Layer.add(pwText, new Integer(1));
		
		//���̵�, ��й�ȣ ã��
		idBtn = new ButtonDAO();
		idBtn.makeGrayButton("���̵� ã��", login2Layer, 1);
		idBtn.setBounds(70, 370, 155, 30);
		
		//���̵�ã�� ��ư Ŭ���� ��ư�̺�Ʈ
		idBtn.addActionListener(new ActionListener() {
			FrameDAO idSearchFrame;
			PanelDAO idSearchLayer, bgPanel;
			LabelDAO titleLabel, nameLabel, birthLabel;
			TextDAO nameText, birthText, phoneText, certifText;
			ButtonDAO certifBtn, certifBtn2, searchBtn, cancelBtn;
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				
				idSearchFrame = new FrameDAO();
				idSearchFrame.makeFrame();
				idSearchLayer = new PanelDAO();
				idSearchLayer.makeLayer();
				idSearchFrame.add(idSearchLayer);
				bgPanel = new PanelDAO();
				bgPanel.makeColorPanel(idSearchLayer);
				titleLabel = new LabelDAO("���̵� ã��", FrameVO.font40, idSearchLayer, 1);
				titleLabel.makeTitleLabel();
				
				nameLabel = new LabelDAO("  �̸�", FrameVO.font20, idSearchLayer, 1);
				nameLabel.setBounds(20, 170, 100, 30);
				nameText = new TextDAO(FrameVO.font18, idSearchLayer, 1);
				nameText.setBounds(130, 170, 310, 30);
				
				birthLabel = new LabelDAO("�������", FrameVO.font20, idSearchLayer, 1);
				birthLabel.setBounds(20, 220, 100, 30);
				birthText = new TextDAO(FrameVO.font18, idSearchLayer, 1);
				birthText.setBounds(130, 220, 310, 30);
				birthText.setText(" ��) 20000101");
				new EventListenerDAO().removeText(birthText);
				
				//����text
				String[] serviceList = {"��Ż�", "SKT", "KT", "LG U+"};
				JComboBox<String> serviceBox = new JComboBox<String>(serviceList);
				serviceBox.setBounds(20, 270, 115, 30);
				serviceBox.setFont(FrameVO.font18);
				serviceBox.setBackground(Color.WHITE);
				idSearchLayer.add(serviceBox, new Integer(1));
				phoneText = new TextDAO(FrameVO.font18, idSearchLayer, 1);
				phoneText.setBounds(140, 270, 300, 30);
				phoneText.setText("��) 01012345678");
				new EventListenerDAO().removeText(phoneText);
				
				certifText = new TextDAO(FrameVO.font18, idSearchLayer, 1);
				certifText.setBounds(20, 320, 290, 30);
				certifText.setText("������ȣ�� �Է����ּ���");
				certifText.setEnabled(false);
				new EventListenerDAO().removeText(certifText);
				certifBtn = new ButtonDAO();
				certifBtn.makeGrayButton("������ȣ", idSearchLayer, 1);
				certifBtn.setLocation(325, 320);
				
				certifBtn2 = new ButtonDAO();
				certifBtn2.makeGrayButton("����Ȯ��", idSearchLayer, 1);
				certifBtn2.setLocation(325, 320);
				
				//������ȣ �ޱ� �̺�Ʈ
				certifBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						userList = new JoinPage().getUserInfo();
						//������ġ Ȯ�ο� ����
						int cnt = 0;
						for (int i = 0; i < userList.size(); i++) {
							UserVO vo = userList.get(i);
							if(nameText.getText().equals(vo.getName())) {
								cnt++;
								idx = i;
								if(birthText.getText().equals(vo.getBirth())) {
									cnt++;
									if(phoneText.getText().equals(vo.getPhone())) {
										cnt++;
									}
								}
							}
						}
						
						if(cnt==3) {
							//�̸�, ����, �޴���ȭ��ȣ ��ġ�ϸ� ������ȣ �ο� & �����ϱ�
							new CertifSection(idSearchFrame, certifText, certifBtn, certifBtn2);
							
						}else {
							JOptionPane.showMessageDialog(idSearchFrame, 
									"ȸ�� ������ ��ġ���� �ʽ��ϴ�", "�α���", JOptionPane.ERROR_MESSAGE);
						}						
					}
				});		
			
				
				searchBtn = new ButtonDAO();
				searchBtn.makeGrayButton("���̵� ã��", idSearchLayer, 1);
				searchBtn.setBounds(60, 370, 155, 30);
				
				//���̱� ��ȸ��� �����ֱ� �̺�Ʈ
				searchBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//���̵� ��ȸ
						String id = searchID();
						String message = "���̵�� [ " + id + " ] �Դϴ�.\r\n�α����Ͻðڽ��ϱ�?";
						int choice = JOptionPane.showOptionDialog(idSearchFrame, message, "�α���", 
								0, JOptionPane.PLAIN_MESSAGE, null, null, null);
						//'��' ���ý�
						if(choice==0) {
							idSearchFrame.dispose();
							login2Frame.setVisible(true);
						}
					}
				});
				
				
				cancelBtn = new ButtonDAO();
				cancelBtn.makeGrayButton("����ϱ�", idSearchLayer, 1);
				cancelBtn.setBounds(240, 370, 155, 30);
				new EventListenerDAO().movePage(cancelBtn, idSearchFrame, login2Frame);
				
				login2Frame.setVisible(false);
				idSearchFrame.setVisible(true);
				
				//�������̹���
				new ImageDAO().showTitleIcon(login2Frame);				
			}
		});
		
		//��й�ȣ ã�� page
		pwBtn = new ButtonDAO();
		pwBtn.makeGrayButton("��й�ȣ ã��", login2Layer, 1);
		pwBtn.setBounds(240, 370, 155, 30);
		
		pwBtn.addActionListener(new ActionListener() {
			FrameDAO pwSearchFrame;
			PanelDAO pwSearchLayer, bgLayer;
			LabelDAO titleLabel, nameLabel, idLabel, pwLabel, pwLabel2;
			TextDAO nameText, idText, phoneText, certifText;
			ButtonDAO certifBtn, certifBtn2, pwChangeBtn, cancelBtn, pwCheckBtn, pwChangeBtn2, cancelBtn2;
			JPasswordField pwText, pwText2;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pwSearchFrame = new FrameDAO();
				pwSearchFrame.makeFrame();
				pwSearchLayer = new PanelDAO();
				pwSearchLayer.makeLayer();
				pwSearchFrame.add(pwSearchLayer);
				bgLayer = new PanelDAO();
				bgLayer.makeColorPanel(pwSearchLayer);
				titleLabel = new LabelDAO("��й�ȣ ã��", FrameVO.font40, pwSearchLayer, 1);
				titleLabel.makeTitleLabel();
				
				nameLabel = new LabelDAO(" �̸�", FrameVO.font20, pwSearchLayer, 1);
				nameLabel.setBounds(20, 170, 100, 30);
				nameText = new TextDAO(FrameVO.font18, pwSearchLayer, 1);
				nameText.setBounds(130, 170, 310, 30);
				
				idLabel = new LabelDAO("���̵�", FrameVO.font20, pwSearchLayer, 1);
				idLabel.setBounds(20, 220, 100, 30);
				idText = new TextDAO(FrameVO.font18, pwSearchLayer, 1);
				idText.setBounds(130, 220, 310, 30);
				
				//����text
				String[] serviceList = {"��Ż�", "SKT", "KT", "LG U+"};
				JComboBox<String> serviceBox = new JComboBox<String>(serviceList);
				serviceBox.setBounds(20, 270, 115, 30);
				serviceBox.setFont(FrameVO.font18);
				serviceBox.setBackground(Color.WHITE);
				pwSearchLayer.add(serviceBox, new Integer(1));
				phoneText = new TextDAO(FrameVO.font18, pwSearchLayer, 1);
				phoneText.setBounds(140, 270, 300, 30);
				phoneText.setText("��) 01012345678");
				new EventListenerDAO().removeText(phoneText);
				
				certifText = new TextDAO(FrameVO.font18, pwSearchLayer, 1);
				certifText.setBounds(20, 320, 290, 30);
				certifText.setText("������ȣ�� �Է����ּ���");
				certifText.setEnabled(false);
				new EventListenerDAO().removeText(certifText);
				certifBtn = new ButtonDAO();
				certifBtn.makeGrayButton("������ȣ", pwSearchLayer, 1);
				certifBtn.setLocation(325, 320);
				
				certifBtn2 = new ButtonDAO();
				certifBtn2.makeGrayButton("����Ȯ��", pwSearchLayer, 1);
				certifBtn2.setLocation(325, 320);
				
				//�����ϱ�
				certifBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						userList = new JoinPage().getUserInfo();
						//������ġ Ȯ�ο� ����
						int cnt = 0;
						for (int i = 0; i < userList.size(); i++) {
							UserVO vo = userList.get(i);
							if(nameText.getText().equals(vo.getName())) {
								cnt++;
								idx = i;
								if(idText.getText().equals(vo.getId())) {
									cnt++;
									if(phoneText.getText().equals(vo.getPhone())) {
										cnt++;
									}
								}
							}
						}
						
						if(cnt==3) {
							new CertifSection(pwSearchFrame, certifText, certifBtn, certifBtn2);
						}else {
							JOptionPane.showMessageDialog(pwSearchFrame, 
									"ȸ�� ������ ��ġ���� �ʽ��ϴ�", "�α���", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				
				pwChangeBtn = new ButtonDAO();
				pwChangeBtn.makeGrayButton("��й�ȣ ã��", pwSearchLayer, 1);
				pwChangeBtn.setBounds(60, 370, 155, 30);
				
				cancelBtn = new ButtonDAO();
				cancelBtn.makeGrayButton("����ϱ�", pwSearchLayer, 1);
				cancelBtn.setBounds(240, 370, 155, 30);
				new EventListenerDAO().movePage(cancelBtn, pwSearchFrame, login2Frame);
				
				//��й�ȣ ���� ��ư �̺�Ʈ
				pwChangeBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						int choice = JOptionPane.showOptionDialog(pwSearchFrame, 
								"��й�ȣ�� ���游 �����մϴ�.\r\n�����Ͻðڽ��ϱ�?", "�α���", 0, JOptionPane.PLAIN_MESSAGE, null, null, null);
						
						if(choice==0) {
							pwChangeBtn.setVisible(false);
							cancelBtn.setVisible(false);
							pwLabel = new LabelDAO("���ο� ��й�ȣ", FrameVO.font20, pwSearchLayer, 1);
							pwLabel.setBounds(15, 370, 200, 30);
							pwText = new JPasswordField();
							pwText.setBounds(20, 410, 290, 30);
							pwSearchLayer.add(pwText, new Integer(1));
							pwLabel2 = new LabelDAO("��й�ȣ Ȯ��", FrameVO.font20, pwSearchLayer, 1);
							pwLabel2.setBounds(15, 450, 200, 30);
							pwText2 = new JPasswordField();
							pwText2.setBounds(20, 490, 290, 30);
							pwSearchLayer.add(pwText2, new Integer(1));
							pwCheckBtn = new ButtonDAO();
							pwCheckBtn.makeGrayButton("��ġȮ��", pwSearchLayer, 1);
							pwCheckBtn.setLocation(325, 490);
							
							//��й�ȣ ��ġ/����ġ Ȯ�� �̺�Ʈ
							pwCheckBtn.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									String pw1 = new JoinPage().decryptPassword(pwText);
									String pw2 = new JoinPage().decryptPassword(pwText2);
									
									if(!pw1.equals("")) {
										if(pw1.equals(pw2)){
											JOptionPane.showMessageDialog(pwSearchFrame, "��й�ȣ�� ��ġ�մϴ�.", "�α���", JOptionPane.PLAIN_MESSAGE);
											pwCheckBtn.setEnabled(false);
											pwText.setEnabled(false);
											pwText2.setEnabled(false);
										}else {
											JOptionPane.showMessageDialog(pwSearchFrame, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.\r\n�ٽ� Ȯ���� �ּ���.", 
													"�α���", JOptionPane.ERROR_MESSAGE);
										}					
									}else {
										JOptionPane.showMessageDialog(pwSearchFrame, "��й�ȣ�� �Է����ּ���", 
												"�α���", JOptionPane.ERROR_MESSAGE);
									}
								}
							});
							
							pwChangeBtn2 = new ButtonDAO();
							pwChangeBtn2.makeGrayButton("��й�ȣ ����", pwSearchLayer, 1);
							pwChangeBtn2.setBounds(60, 540, 155, 30);
							
							//��ư Ŭ�� �� ȸ������ �� ��й�ȣ ����, ���� �̺�Ʈ
							pwChangeBtn2.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
										changePassword(pwText, pwSearchFrame);		
										JOptionPane.showMessageDialog(pwSearchFrame, "��й�ȣ�� ����Ǿ����ϴ�", "�α���", JOptionPane.PLAIN_MESSAGE);
										pwSearchFrame.dispose();
										login2Frame.setVisible(true);
								}
							});
							
							cancelBtn2 = new ButtonDAO();
							cancelBtn2.makeGrayButton("����ϱ�", pwSearchLayer, 1);
							cancelBtn2.setBounds(240, 540, 155, 30);
							new EventListenerDAO().movePage(cancelBtn2, pwSearchFrame, login2Frame);
						}
					}
				});
				
				login2Frame.setVisible(false);
				pwSearchFrame.setVisible(true);
				
				new ImageDAO().showTitleIcon(pwSearchFrame);
			}
		});
		
		//�α��ι�ư
		loginBtn = new ButtonDAO();
		loginBtn.makeBlueButton("�α���", login2Layer, 1);
		loginBtn.setBounds(0, 440, 480, 50);		

		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//ȸ������ ��ġȮ�ο� ����
				int cnt = 0;
				userList = new JoinPage().getUserInfo();
				
				for (int i = 0; i < userList.size(); i++) {
					if(userList.get(i).getId().equals(idText.getText())) {
						cnt++;
						String password = new JoinPage().decryptPassword(pwText);
						if(userList.get(i).getPw().equals(password)) {
							cnt++;
							UserVO.index = i;
						}
					}
				}
				
				//id, ��й�ȣ ��ġ�� �α���
				if(cnt==2) {
					login2Frame.dispose();
					new MainPage().mainFrame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(login2Frame, "���̵� ��й�ȣ�� Ȯ���� �ּ���", "�α���", JOptionPane.ERROR_MESSAGE);
					new EventListenerDAO().removeText(idText);
					new EventListenerDAO().removeText(pwText);
				}
			}
		});
		
		
		
		//�������ư
		quitBtn = new ButtonDAO();
		quitBtn.makeBlueButton("������", login2Layer, 1);
		quitBtn.setBounds(0, 510, 480, 50);
		
		//�������ư Ŭ�� �� ���α׷� ���� �̺�Ʈ
		new EventListenerDAO().quit(quitBtn, login2Frame);
		
		//������ �̹���
		new ImageDAO().showTitleIcon(login2Frame);
	}
	
		//���̵� ã�� �޼���
	public String searchID() {
		userList = new JoinPage().getUserInfo();
		String userId = userList.get(idx).getId();
		return userId;
	}
	
	//��й�ȣ ����� ���Ͽ� ���� �޼���
	public void changePassword(JPasswordField pwfield, JFrame frame) {
		
		try {
			String password = new JoinPage().decryptPassword(pwfield);
			userList.get(idx).setPw(password);
			
			FileWriter output = new FileWriter(new File(new UserVO().getUserPath()+new UserVO().getUserPath2()));
			for (int i = 0; i < userList.size(); i++) {
				UserVO vo = userList.get(i);
				output.write(vo.getNumber()+"\t");
				output.write(vo.getId()+"\t");
				output.write(vo.getPw()+"\t");
				output.write(vo.getName()+"\t");
				output.write(vo.getGender()+"\t");
				output.write(vo.getBirth()+"\t");
				output.write(vo.getPhone()+"\t");
				output.write(vo.getAddress()+"\r\n");
			}
			output.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "������ �������� �ʽ��ϴ�", "�α���", JOptionPane.ERROR_MESSAGE);
		}
	}	
}
