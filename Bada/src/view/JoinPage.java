package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

import dao.ButtonDAO;
import dao.CertifSection;
import dao.EventListenerDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dao.TextDAO;
import dto.AddressVO;
import dto.FrameVO;
import dto.UserVO;

public class JoinPage {
	//ȸ������ ������
	FrameDAO joinFrame;
	PanelDAO joinLayer;	
	LabelDAO titleLabel, idLabel, pwLabel, pw2Label, nameLabel, phoneLabel, birthLabel, addressLabel, zipLabel;
	TextDAO idText, nameText, phoneText, birthText, addressText1, addressText2, zipText;
	JPasswordField pwText, pw2Text;
	ButtonDAO idCheckBtn, pwCheckBtn, phoneCheckBtn, addressFindBtn, termsBtn, agreeBtn, quitBtn, zipBtn,
						applyBtn, cancelBtn;
	
	JComboBox<String> genderBox;
	ArrayList<UserVO> userList = new ArrayList<UserVO>();
	boolean selected, selected2;
	String num; //������ȣ
	
	public JoinPage() {
		//������&���̾�
		joinFrame = new FrameDAO();
		joinLayer = new PanelDAO();
		joinLayer.makeLayer();
		joinFrame.add(joinLayer);	
		
		//��ܲٹ̱�
		PanelDAO colorPanel = new PanelDAO();
		colorPanel.makeColorPanel(joinLayer);
		titleLabel = new LabelDAO("ȸ������", FrameVO.font40, joinLayer, 1);
		//Label �� �ؽ�Ʈ �������
		titleLabel.makeTitleLabel();
		
		//ȸ�����Զ�
		//ID
		idLabel = new LabelDAO("���̵�", FrameVO.font20, joinLayer, 1);
		idLabel.setBounds(15, 125, 460, 30);
		idText = new TextDAO(FrameVO.font18, joinLayer, 1);
		idText.setBounds(20, 160, 300, 30);
		idCheckBtn = new ButtonDAO();
		idCheckBtn.makeGrayButton("�ߺ�Ȯ��", joinLayer, 3);
		idCheckBtn.setLocation(325, 160);
		
		//PW,PW2
		pwLabel = new LabelDAO("��й�ȣ", FrameVO.font20, joinLayer, 1);
		pwLabel.setBounds(15, 195, 460, 30);
		pwText = new JPasswordField();
		pwText.setBounds(20, 230, 300, 30);
		pwText.setFont(FrameVO.font18);
		pwText.setEchoChar('*');
		joinLayer.add(pwText, new Integer(1));
		pw2Label = new LabelDAO("��й�ȣ Ȯ��", FrameVO.font20, joinLayer, 1);
		pw2Label.setBounds(15, 265, 460, 30);
		pw2Text = new JPasswordField();
		pw2Text.setBounds(20, 300, 300, 30);
		pw2Text.setFont(FrameVO.font18);
		pw2Text.setEchoChar('*');		
		joinLayer.add(pw2Text, new Integer(1));
		pwCheckBtn = new ButtonDAO();
		pwCheckBtn.makeGrayButton("��ġȮ��", joinLayer, 1);
		pwCheckBtn.setLocation(325, 300);
		
		//Name
		nameLabel = new LabelDAO("�̸�", FrameVO.font20, joinLayer, 1);
		nameLabel.setBounds(15, 335, 460, 30);
		nameText = new TextDAO(FrameVO.font18, joinLayer, 1);
		nameText.setBounds(20, 370, 300, 30);
		nameText.setEnabled(false);
		
		//Gender
		String[] arGender = {"      ����", "      ����", "      ����"};
		genderBox = new JComboBox<String>(arGender);
		genderBox.setBounds(325, 370, 115, 30);
		genderBox.setFont(FrameVO.font18);
		genderBox.setBackground(Color.WHITE);
		joinLayer.add(genderBox);
		
		//Birth
		birthLabel = new LabelDAO("�������", FrameVO.font20, joinLayer, 1);
		birthLabel.setBounds(15, 405, 460, 30);
		birthText =new TextDAO(FrameVO.font18, joinLayer, 1);
		birthText.setText("��) 20000101");
		new EventListenerDAO().removeText(birthText);
		birthText.setBounds(20, 440, 300, 30);
		birthText.setEnabled(false);
		genderBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(genderBox.getSelectedItem().toString().equals("      ����")||
						genderBox.getSelectedItem().toString().equals("      ����")) {
					birthText.setEnabled(true);
				}
			}
		});
		
		//Phone
		phoneLabel = new LabelDAO("�޴���ȭ", FrameVO.font20, joinLayer, 1);
		phoneLabel.setBounds(15, 475, 460, 30);
		phoneText = new TextDAO(FrameVO.font18, joinLayer, 1);
		phoneText.setBounds(20, 510, 300, 30);
		phoneText.setEditable(false);
		phoneCheckBtn = new ButtonDAO();
		phoneCheckBtn.makeGrayButton("�����ޱ�", joinLayer, 1);
		phoneCheckBtn.setLocation(325, 510);
		
		//Address
		addressLabel = new LabelDAO("�ּ�", FrameVO.font20, joinLayer, 1);
		addressLabel.setBounds(15, 545, 460, 30);
		addressText1 = new TextDAO(FrameVO.font18, joinLayer, 1);
		addressText1.setBounds(20, 580, 300, 30);
		addressText1.setEditable(false);
		addressFindBtn = new ButtonDAO();
		addressFindBtn.makeGrayButton("�ּ�ã��", joinLayer, 1);
		addressFindBtn.setLocation(325, 580);
		addressText2 = new TextDAO(FrameVO.font18, joinLayer, 1);
		addressText2.setBounds(20, 615, 300, 30);
		addressText2.setText("������ �ּ�");
		new EventListenerDAO().removeText(addressText2);
		
		//���
		JCheckBox termsBox = new JCheckBox("�̿����� �����մϴ�");
		termsBox.setBounds(15, 650, 300, 30);
		termsBox.setBackground(Color.WHITE);
		joinLayer.add(termsBox, new Integer(1));
		termsBtn = new ButtonDAO();
		termsBtn.makeGrayButton("�������", joinLayer, 1);
		termsBtn.setLocation(325, 650);
		
		//���checkbox �����̺�Ʈ
		termsBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(termsBtn.isEnabled()==true) {
					System.out.println("IN!!");
					JOptionPane.showMessageDialog(joinFrame, "��� Ȯ�� �� üũ�� �ּ���", "ȸ������", JOptionPane.ERROR_MESSAGE);
				}
				
				if(e.getStateChange()==ItemEvent.SELECTED) {
					selected2 = true;
					System.out.println("selected");
				}else {
					selected2 = false;
					System.out.println("deselected");
				}
			}
		});
		
		
		//������� ��ư �̺�Ʈ
		termsBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(addressText2.getText().equals("")) {
					JOptionPane.showMessageDialog(joinFrame, "������ �ּҸ� �Է����ּ���", "ȸ������", JOptionPane.ERROR_MESSAGE);
				}else {
					showTerms(joinFrame);
					termsBtn.setEnabled(false);
				}
			}
		});
		
		//���Թ�ư&����ϱ��ư
		agreeBtn = new ButtonDAO();
		agreeBtn.makeBlueButton("�����ϱ�", joinLayer, 1);
		agreeBtn.setBounds(20, 690, 205, 50);
		quitBtn = new ButtonDAO();
		quitBtn.makeBlueButton("����ϱ�", joinLayer, 1);
		quitBtn.setBounds(235, 690, 205, 50);
			
		quitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				joinFrame.dispose();
				new LoginPage().loginFrame.setVisible(true);
			}
		});
	
		//ȸ������ �������� �̺�Ʈ
		agreeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selected2) {
					if(idCheckBtn.isEnabled()==false&&pwCheckBtn.isEnabled()==false&&
							genderBox.getSelectedIndex()!=0&&phoneCheckBtn.isEnabled()==false&&
							addressFindBtn.isEnabled()==false&&termsBtn.isEnabled()==false) {
						saveInfo();
						JOptionPane.showMessageDialog(joinFrame, "��ȸ�����ԡڼ�����", "ȸ������", JOptionPane.PLAIN_MESSAGE);
											
						joinFrame.dispose();
						new LoginPage2().login2Frame.setVisible(true);
						
					}else {
						JOptionPane.showMessageDialog(joinFrame, "�Է��� �� �� ���� �ֽ��ϴ�.", "ȸ������", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(joinFrame, "����� �������ּ���", "ȸ������", JOptionPane.ERROR_MESSAGE);
				}
			}
		});		
		
		//���̵� �ߺ�Ȯ�� ��ư �̺�Ʈ
		idCheckBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				checkID(idText.getText().toString(), joinFrame, idCheckBtn, idText);
				for (int i = 0; i < userList.size(); i++) {
					userList.get(i).showInfo();
				}
			}
		});
		
		
		//��й�ȣ ��ȿȭ
		
		//��й�ȣ ��ġȮ�� ��ư �̺�Ʈ
		pwCheckBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String pw1 = decryptPassword(pwText);
				String pw2 = decryptPassword(pw2Text);
				
				if(!pw1.equals("")) {
					if(pw1.equals(pw2)){
						JOptionPane.showMessageDialog(joinFrame, "��й�ȣ�� ��ġ�մϴ�.", "��й�ȣ Ȯ��", JOptionPane.PLAIN_MESSAGE);
						pwCheckBtn.setEnabled(false);
						pwText.setEditable(false);
						pw2Text.setEditable(false);
						nameText.setEnabled(true);
					}else {
						JOptionPane.showMessageDialog(joinFrame, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.\r\n�ٽ� Ȯ���� �ּ���.", 
								"��й�ȣ Ȯ��", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(joinFrame, "��й�ȣ�� �Է����ּ���", 
							"��й�ȣ Ȯ��", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//�޴���ȭ ���� ��ư �̺�Ʈ
		phoneCheckBtn.addActionListener(new ActionListener() {
			FrameDAO phoneFrame;
			PanelDAO phoneLayer, bluePanel;
			LabelDAO titleLabelP, nameLabelP, birthLabelP, dashLabelP, xLabelP;
			TextDAO nameTextP, birthTextP, birthText2P, phoneTextP, certifTextP;
			ButtonDAO certifBtn, certifBtn2, okBtn, cancelBtn;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//����ȭ�� Frame&Layer
				phoneFrame = new FrameDAO();
				phoneLayer = new PanelDAO();
				phoneLayer.makeLayer();
				phoneFrame.add(phoneLayer);
				//����������
				bluePanel = new PanelDAO();
				bluePanel.makeColorPanel(phoneLayer);				
				titleLabelP = new LabelDAO("�޴���ȭ ����", FrameVO.font40, phoneLayer, 1);
				titleLabelP.makeTitleLabel();
				//�̸�
				nameLabelP = new LabelDAO("�̸�", FrameVO.font20, phoneLayer, 1);
				nameLabelP.setBounds(15, 140, 460, 30);
				nameTextP = new TextDAO(FrameVO.font18, phoneLayer, 1);
				nameTextP.setBounds(20, 175, 420, 30);
				//�ֹι�ȣ
				birthLabelP = new LabelDAO("�ֹε�Ϲ�ȣ", FrameVO.font20, phoneLayer, 1);
				birthLabelP.setBounds(15, 210, 460, 30);
				birthTextP = new TextDAO(FrameVO.font18, phoneLayer, 1);
				birthTextP.setBounds(20, 245, 200, 30);
				dashLabelP = new LabelDAO("��", FrameVO.font20, phoneLayer, 1);
				dashLabelP.setBounds(225, 245, 40, 30);
				birthText2P = new TextDAO(FrameVO.font18, phoneLayer, 1);
				birthText2P.setBounds(270, 245, 40, 30);
				xLabelP = new LabelDAO("XXXXXX", FrameVO.font20, phoneLayer, 1);
				xLabelP.setBounds(305, 245, 100, 30);
				//��ȭ��ȣ
				String[] serviceList = {"��Ż�", "SKT", "KT", "LG U+"};
				JComboBox<String> serviceBox = new JComboBox<String>(serviceList);
				serviceBox.setBounds(20, 295, 115, 30);
				serviceBox.setFont(FrameVO.font18);
				serviceBox.setBackground(Color.WHITE);
				phoneLayer.add(serviceBox, new Integer(1));
				phoneTextP = new TextDAO(FrameVO.font18, phoneLayer, 1);
				phoneTextP.setBounds(140, 295, 300, 30);
				phoneTextP.setText("��) 01012345678");
				new EventListenerDAO().removeText(phoneTextP);
				//������ȣ �Է�
				certifTextP = new TextDAO(FrameVO.font18, phoneLayer, 1);
				certifTextP.setBounds(20, 345, 290, 30);
				certifTextP.setText("������ȣ�� �Է����ּ���");
				certifTextP.setEnabled(false);
				new EventListenerDAO().removeText(certifTextP);
				certifBtn = new ButtonDAO();
				certifBtn.makeGrayButton("������ȣ", phoneLayer, 1);
				certifBtn.setLocation(320, 345);
				
				certifBtn2 = new ButtonDAO();
				certifBtn2.makeGrayButton("����Ȯ��", phoneLayer, 1);
				certifBtn2.setLocation(320, 345);
				
				//�ʼ����� checkbox
				JCheckBox agreeBoxP = new JCheckBox();
				agreeBoxP.setText("���� �̿��� ���� �ʼ� �׸� ��� �����մϴ�.");
				agreeBoxP.setBounds(15, 380, 440, 30);
				agreeBoxP.setBackground(Color.WHITE);
				phoneLayer.add(agreeBoxP, new Integer(1));
				
				//������ư ������ ������ȣ �̺�Ʈ
				certifBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//������ȣ ����, �����ϱ�
						new CertifSection(phoneFrame, certifTextP, certifBtn, certifBtn2);
					}
				});				
				
				//Ȯ�ι�ư
				okBtn = new ButtonDAO();
				okBtn.makeGrayButton("Ȯ��", phoneLayer, 1);
				okBtn.setLocation(100, 425);
				okBtn.setFont(FrameVO.font20);
				
				
				//������ �� textfield �Է� �� ��ư Ŭ�� �� 'Ȯ��'��ư Ȱ��ȭ��Ű��
				agreeBoxP.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange()==ItemEvent.SELECTED) {
							selected = true;
						}else {
							selected = false;
						}
					}
				});
				
				//Ȯ�ι�ư Ŭ���� JoinPage�� ��ȯ�̺�Ʈ
				okBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(selected) {
							if(!(nameTextP.getText().equals("")&&birthTextP.getText().equals("")&&birthText2P.getText().equals("")&&
									serviceBox.getSelectedItem().toString().equals("��Ż�")&&phoneTextP.getText().equals("��) 01012345678")
									&&certifTextP.getText().equals("������ȣ�� �Է����ּ���"))) {
								phoneFrame.dispose();
								joinFrame.setVisible(true);
								phoneCheckBtn.setEnabled(false);
								phoneText.setText(phoneTextP.getText());
							}else {
								JOptionPane.showMessageDialog(phoneFrame, "�Է��� �� �� ���� �ֽ��ϴ�", "����Ȯ��", JOptionPane.ERROR_MESSAGE);
							}
						}else {
							System.out.println(selected);
							JOptionPane.showMessageDialog(phoneFrame, "���� �̿� ���ǿ� üũ�� �ּ���", "����Ȯ��", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				
				//��ҹ�ư
				cancelBtn = new ButtonDAO();
				cancelBtn.makeGrayButton("���", phoneLayer, 1);
				cancelBtn.setLocation(240, 425);
				cancelBtn.setFont(FrameVO.font20);
				
				//��ҹ�ư Ŭ���� ���������� ������ �̺�Ʈ
				new EventListenerDAO().movePage(cancelBtn, phoneFrame, joinFrame);
				
				//�������̹���
				new ImageDAO().showTitleIcon(phoneFrame);
				
				joinFrame.setVisible(false);
				phoneFrame.setVisible(true);
				
			}
		});
		//�ּ�ã�� ��ư �̺�Ʈ
		addressFindBtn.addActionListener(new ActionListener() {
			FrameDAO addressFrame;
			PanelDAO addressLayer, bgPanelA;
			LabelDAO titleLabelA;
			TextDAO addressTextA;
			ButtonDAO addressBtnA;
			JTextArea addressArea;
			@Override
			public void actionPerformed(ActionEvent e) {
				addressFrame = new FrameDAO();
				addressFrame.makeFrame();
				addressLayer = new PanelDAO();
				addressLayer.makeLayer();
				addressFrame.add(addressLayer);
				bgPanelA = new PanelDAO();
				bgPanelA.makeColorPanel(addressLayer);
				titleLabelA = new LabelDAO("�ּ�ã��", FrameVO.font40, addressLayer, 1);
				titleLabelA.makeTitleLabel();
				addressTextA = new TextDAO(FrameVO.font18, addressLayer, 1);
				addressTextA.setBounds(15, 200, 300, 30);
				addressTextA.setText("��)���̷� 15(���� �ʼ�)");
				new EventListenerDAO().removeText(addressTextA);
				addressBtnA = new ButtonDAO();
				addressBtnA.makeGrayButton("�ּҰ˻�", addressLayer, 1);
				addressBtnA.setLocation(330, 200);		
				
				//�˻���� ������ area
				addressArea = new JTextArea();
				JScrollPane scrollPanel = new JScrollPane(addressArea);
				addressArea.setBounds(15, 250, 430, 300);
				scrollPanel.setBounds(15, 250, 430, 300);
				addressLayer.add(scrollPanel, new Integer(1));
				addressArea.setFont(FrameVO.font18);
				addressArea.setBackground(FrameVO.grayColor);
				addressArea.setEditable(false);
				
				//�ּҼ��� combobox		
				zipLabel = new LabelDAO("�ּҸ� �����ϼ���", FrameVO.font20, addressLayer, 1);
				zipLabel.setBounds(15, 560, 200, 30);
				JComboBox<String> numBox = new JComboBox<String>();
				numBox.setBounds(240, 560, 80, 30);
				numBox.setFont(FrameVO.font18);
				numBox.setBackground(Color.WHITE);
				addressLayer.add(numBox);
				zipBtn = new ButtonDAO();
				zipBtn.makeGrayButton("����", addressLayer, 1);
				zipBtn.setLocation(330, 560);
				
				zipText = new TextDAO(FrameVO.font18, addressLayer, 1);
				zipText.setBounds(15, 600, 430, 30);
				zipText.setEditable(false);
				
				//�ּҰ˻� ��ư �̺�Ʈ
				addressBtnA.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						findZipCode(addressArea, addressFrame, addressTextA, numBox, zipBtn, zipText);
					}
				});		
				
				//����, ��ҹ�ư
				applyBtn =new ButtonDAO();
				applyBtn.makeGrayButton("����", addressLayer, 1);
				applyBtn.setLocation(100, 660);
				
				applyBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(zipText.getText().equals("")) {
							JOptionPane.showMessageDialog(addressFrame, "�ּҸ� ������ �ּ���", "�ּҰ˻�", JOptionPane.ERROR_MESSAGE);
						}else {
							addressText1.setText(zipText.getText());
							addressFindBtn.setEnabled(false);
							addressFrame.dispose();
							joinFrame.setVisible(true);
						}
					}
				});
				cancelBtn = new ButtonDAO();
				cancelBtn.makeGrayButton("���", addressLayer, 1);
				cancelBtn.setLocation(240, 660);
				new EventListenerDAO().movePage(cancelBtn, addressFrame, joinFrame);
				
				
				joinFrame.setVisible(false);
				addressFrame.setVisible(true);
				
				new ImageDAO().showTitleIcon(addressFrame);
			}
		});
		
				
		//�������̹���
		new ImageDAO().showTitleIcon(joinFrame);
	}
		
	
	//��й�ȣ *** Ǯ�� �޼���
	public String decryptPassword(JPasswordField pwfield) {		
		String pw = "";
		char[] secretPw = pwfield.getPassword();
		for(char cha : secretPw) {
			Character.toString(cha);
			pw += cha;
		}
		return pw;		
	}		
	
	//�ּ�ã�� �޼���
	public void findZipCode(JTextArea textarea, JFrame frame, JTextField textfield, JComboBox<String> combobox, 
											JButton btn, JTextField resultfield) {
				
		ArrayList<AddressVO> addressList = new ArrayList<AddressVO>();		
		String zipCode = "D:/BADA/ZipCode/zipcode.txt";	
		
		try {
			//�ܺ����Ͽ��� �����ȣ ���� �ҷ�����
			FileReader input = new FileReader(new File(zipCode));
			BufferedReader br = new BufferedReader(input);
			
			//���پ� �о� temp�� ���ϳ��� ����
			String line = "";
			String temp = "";
			try {
				while((line=br.readLine())!= null){
					temp += line + "\r\n";
				}
			
			//temp�����͸� ����������� ���� �迭�� ����, tab�������� ���� �Ǵٸ� �迭�� ����
			String[] arAdd = new String[5000];
			String[] addrList = new String[5000];
			arAdd = temp.split("\n");				
			
			for (int i = 0; i < arAdd.length; i++) {
				//AddressVO��ü ���� �� ������ ����
				AddressVO vo = new AddressVO();
				addrList = arAdd[i].split("\t");
				vo.setZipcode(addrList[0]);
				vo.setSi(addrList[1]);
				vo.setGu(addrList[2]);
				vo.setRo(addrList[3]);
				vo.setBun1(addrList[4]);
				vo.setBun2(addrList[5]);
				//ArrayList�� ��ü���·� ����
				addressList.add(vo);				
			}		
			
			//text�� �Է°��� ���� ��
			if(textfield.getText().equals("")|| textfield.getText().equals("��)���̷� 15(���� �ʼ�)")) {
				JOptionPane.showMessageDialog(frame, "�˻��� �ּҸ� �Է��� �ּ���", 
						"�ּҰ˻�", JOptionPane.ERROR_MESSAGE);
			}else {			
					//text�� ���� keyword�� �ּ� �˻�
				String keyword1, keyword2;
				String[] keywords = new String[2];
				keywords= textfield.getText().trim().toString().split(" ");
				keyword1 = keywords[0];
				keyword2 = keywords[1];				
			
			//��ġ���� ���� ��
			int seq = 1;
			ArrayList<AddressVO> arResult = new ArrayList<AddressVO>();
			for (int i = 0; i < addressList.size(); i++) {
				AddressVO av = addressList.get(i);
				if(av.getRo().contains(keyword1)&& av.getBun1().contains(keyword2)) {
					AddressVO vo = new AddressVO();
					vo.setZipcode(av.getZipcode());
					vo.setSi(av.getSi());
					vo.setGu(av.getGu());
					vo.setRo(av.getRo());
					vo.setBun1(av.getBun1());
					vo.setBun2(av.getBun2());
					
					arResult.add(vo);
					
					String temp1 = seq + "  (" + av.getZipcode() +") " + av.getSi() +" "+ av.getGu() +" "+av.getRo() +" "+ 
											av.getBun1() + 	" "+ av.getBun2();					
					textarea.append(temp1);		
					textarea.append("\r\n");
					seq++;
					
					//��ġ���� ���� ��
					if(temp1.equals("")) {
						JOptionPane.showMessageDialog(frame, "�˻������ �����ϴ�. �ٽ� �Է��� �ּ���.", 
								"�ּҰ˻�", JOptionPane.ERROR_MESSAGE);								
					}					
				}
			}
			seq--;
			//��� �� ���ϴ� �ּ� ���� 
			String[] resultNum = new String[seq+1];
			for (int i = 1; i < seq+1; i++) {
				resultNum[i] ="" + i;				
				combobox.addItem("    "+resultNum[i]);
			}
			
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					AddressVO vo = arResult.get(combobox.getSelectedIndex());
					String zipResult = "("+vo.getZipcode()+") "+vo.getSi()+" "+vo.getGu()+" "
					+vo.getRo()+" "+vo.getBun1()+" "+vo.getBun2();
					resultfield.setText(zipResult);					
				}
			});			
			}
			input.close();
			br.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(frame, "�˻��� �ּҰ� �����ϴ�.\r\n�ٽ� �õ��� �ּ���",
						"�ּҰ˻�", JOptionPane.ERROR_MESSAGE);
			}
		} catch (FileNotFoundException e) {			
			JOptionPane.showMessageDialog(frame, "������ �������� �ʽ��ϴ�", "�ּҰ˻�", JOptionPane.ERROR_MESSAGE);
		}
	}	
	
	//ȸ������ ���� �޼���
	public void saveInfo() {
		//ȸ������ ���� ���� �� ���� ����
		File folder = new File(new UserVO().getUserPath());
		if(!(folder.exists())) {
			folder.mkdirs();
			}
			try {
				File file = new File(new UserVO().getUserPath()+new UserVO().getUserPath2());
				file.createNewFile();
				//textField ������ UserVO, ArrayList�� ����, ���� ����(trim())		
				UserVO user, temp;
				
				//��й�ȣ ��ȣȭ
				String pw = "";
				char[] secretPw = pwText.getPassword();
				for(char cha : secretPw) {
					Character.toString(cha);
					pw += cha;
				}
				
				temp = new UserVO();
				temp.setId(idText.getText().toString().trim());						
				temp.setPw(pw.trim());
				temp.setName(nameText.getText().toString().trim());
				temp.setGender(genderBox.getSelectedItem().toString().trim());
				temp.setBirth(birthText.getText().toString().trim());
				temp.setPhone(phoneText.getText().toString().trim());
				String address = addressText1.getText().toString().trim()+ " "+addressText2.getText().toString();
				temp.setAddress(address);
				
				user = new UserVO(temp.getNumber(), temp.getId(), temp.getPw(), temp.getName(), 
						temp.getGender(), temp.getBirth(), temp.getPhone(), temp.getAddress());
				
				//�ܺ� ���Ͽ� ����
				FileWriter output = new FileWriter(file, true);
				
				output.write(user.getNumber() + "\t");
				output.write(user.getId().toString() + "\t");
				output.write(user.getPw() + "\t");
				output.write(user.getName() + "\t");
				output.write(user.getGender() + "\t");
				output.write(user.getBirth() + "\t");
				output.write(user.getPhone() + "\t");
				output.write(user.getAddress()+"\r\n");
				output.close();
				
			} catch (Exception e1) {
				System.out.println("Error");					
		}
	}
	
	
	//ȸ������ �ҷ�����
	public ArrayList<UserVO> getUserInfo() {
		try {
			FileReader input = new FileReader(new File(new UserVO().getUserPath()+new UserVO().getUserPath2()));
			BufferedReader br = new BufferedReader(input);
			
			String line = "";
			String temp = "";
			while((line=br.readLine())!= null) {
				temp += line + "\r\n";
			}
			
			String[] list = new String[100];
			String[] arList = new String[100];
			
			list = temp.split("\r\n");
			
			for (int i = 0; i < list.length; i++) {
				UserVO vo = new UserVO();
				arList = list[i].split("\t");
				vo.setId(arList[1]);
				vo.setPw(arList[2]);
				vo.setName(arList[3]);
				vo.setGender(arList[4]);
				vo.setBirth(arList[5]);
				vo.setPhone(arList[6]);
				vo.setAddress(arList[7]);
				userList.add(vo);
			}			
			br.close();
			input.close();
		} catch (Exception e) {}	
		return userList;
	}
	
	
	
	//���̵� �ߺ�üũ �޼���
	public void checkID(String id, JFrame frame, JButton btn, JTextField text) {
		userList = getUserInfo();
		int cnt = 0;
		for (int i = 0; i < userList.size(); i++) {				
			if(userList.get(i).getId().equals(id)) {
				cnt++;
			}
		}
		if(id.equals("")) {
			JOptionPane.showMessageDialog(frame, "���̵� �Է����ּ���", "ȸ������", JOptionPane.ERROR_MESSAGE);
		}else {
			if(cnt==0) {
				int choice = JOptionPane.showOptionDialog(frame, "��밡���� ���̵��Դϴ�.\r\n����Ͻðڽ��ϱ�?", "ȸ������", 
						0, JOptionPane.PLAIN_MESSAGE, null, null, null);
				if(choice==0) {
					btn.setEnabled(false);
					text.setEditable(false);
				}
			}else if(cnt>=1) {
				JOptionPane.showMessageDialog(frame, "�ߺ��� ���̵��Դϴ�. �ٽ� �õ��� �ּ���", "ȸ������", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//������� �޼���
	public void showTerms(JFrame frame) {
		String termsMsg = "��1�� (����)\r\n" + 
				"�� ����� �ù����ڿ� ��(��ȭ��) ���� ������ �ù�ŷ��� ���Ͽ�\r\n�� ��������� ������ �������� �մϴ�.\r\n"+
				"��2�� (������� ��)\r\n" + 
				"�� ����� �������� ���� ���׿� ���Ͽ��� ȭ���ڵ�����������, ��� ����\r\n���Կ� ������ �Ϲݰ����� �����ϴ�.\r\n"+
				"��3�� (����� ����)\r\n" + 
				" - ������ : �����ȣ���� ��\r\n"+
				"��4�� (�����)\r\n" + 
				"����ڴ� ����� ü���ϴ� ���� ���� �� ȣ�� ������ ������ �������\r\n�����Ͽ� ��(��ȭ��)���� �����մϴ�.\r\n" + 
				" - ������� ��ȣ, ��ǥ�ڸ�, �ּ� �� ��ȭ��ȣ, ����� �̸�, ����� ��ȣ\r\n"+
				"��5�� (������ û��)\r\n" + 
				" - ���� �� ������� -\r\n"+
				"���� �� �ȿ����� ��� �����մϴ�.\r\n"+
				"�⺻���� : ����������� ������������ ���� ���\r\n                     2000�� (��)21797->21797)\r\n"+ 
				"�߰����� : ����������� ������������ �ٸ� ���\r\n                     500���� �߰� (�������� �þ ������ �ΰ�)\r\n"+
				" - ���Կ� ũ�⿡ ���� �� �׸� �� �ִ� 1000���� �߰����� �ο��˴ϴ�.\r\n";
		JOptionPane.showMessageDialog(frame, termsMsg, "ȸ������", JOptionPane.PLAIN_MESSAGE);
	}
}
