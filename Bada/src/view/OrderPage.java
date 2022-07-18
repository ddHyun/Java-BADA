package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.ButtonDAO;
import dao.EventListenerDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dao.TextDAO;
import dto.FrameVO;
import dto.UserVO;

public class OrderPage {
	
	FrameDAO orderFrame;
	PanelDAO orderLayer;
	LabelDAO titleLabel, sendLabel, sendNameLabel, sendPhoneLabel, sendAddrLabel,
					receiveLabel, receiveNameLabel, receivePhoneLabel, receiveAddrLabel;
	TextDAO sendNameText, sendPhoneText, receiveNameText, receivePhoneText;
	ButtonDAO sendInfoBtn, addressBtn, nextBtn, cancelBtn;
	JTextArea sendAddrArea, receiveAddrArea;
	JScrollPane scrollPane, scrollPane2;
	ArrayList<UserVO> userList = new ArrayList<UserVO>();
	String jTitle = "�ù��ֹ� ��û";
	int senderZip, receiverZip;
	
	//�ù��ֹ� ��û ������
	public OrderPage() {
		orderFrame = new FrameDAO();
		orderFrame.makeFrame();
		orderLayer = new PanelDAO();
		orderLayer.makeLayer();
		orderFrame.add(orderLayer);
		
		new PanelDAO().makeColorPanel(orderLayer);
		titleLabel = new LabelDAO("�ù��ֹ� ��û", FrameVO.font40, orderLayer, 1);
		titleLabel.makeTitleLabel();
		
		//�ֹ��� ����
		sendLabel = new LabelDAO("�ֹ��� ����", FrameVO.font25, orderLayer, 1);
		sendLabel.setBounds(20, 140, 200, 30);
		sendLabel.setOpaque(true); //label�� ���� ���� �� setOpaque(true) -> ���� �־�� ��
		sendLabel.setBackground(FrameVO.grayColor);
		sendInfoBtn = new ButtonDAO();
		sendInfoBtn.makeGrayButton("���� �ҷ�����", orderLayer, 1);
		sendInfoBtn.setBounds(280, 150, 160, 30);
		sendNameLabel = new LabelDAO("�̸�", FrameVO.font20, orderLayer, 1);
		sendNameLabel.setBounds(20, 190, 100, 30);
		sendNameText = new TextDAO(FrameVO.font18, orderLayer, 1);
		sendNameText.setBounds(160, 190, 280, 30);
		sendPhoneLabel = new LabelDAO("�޴���ȭ", FrameVO.font20, orderLayer, 1);
		sendPhoneLabel.setBounds(20, 230, 100, 30);
		sendPhoneText = new TextDAO(FrameVO.font18, orderLayer, 1);
		sendPhoneText.setBounds(160, 230, 280, 30);
		sendAddrLabel = new LabelDAO("�ּ�", FrameVO.font20, orderLayer, 1);
		sendAddrLabel.setBounds(20, 270, 100, 30);
		
		sendAddrArea = new JTextArea();		
		scrollPane = new JScrollPane(sendAddrArea);
		sendAddrArea.setBounds(20, 310, 420, 60);
		scrollPane.setBounds(20, 310, 420, 60);
		orderLayer.add(scrollPane, new Integer(1));
		sendAddrArea.setFont(FrameVO.font18);
		sendAddrArea.setLineWrap(true); //�ڵ� �ٹٲ�
		
		//���� �ҷ����� ��ư Ŭ���� �α��� ���� �������� �̺�Ʈ
		sendInfoBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showOptionDialog(orderFrame, 
						"ȸ�� ������ �ֹ��ڿ� ���ٸ� '��'�� �����ּ���", jTitle, 0, JOptionPane.PLAIN_MESSAGE, null, null, null);
				
				if(choice==0) {
					userList = new JoinPage().getUserInfo();
					UserVO vo = userList.get(UserVO.index);
					sendNameText.setText(vo.getName());
					sendPhoneText.setText(vo.getPhone());
					//�����ȣ, �ּ� �޼��� ȣ��
					senderZip = getZip();		
					String address = getRestAddress();
					//textarea�� ���� �ֱ�
					sendAddrArea.append(senderZip+"\r\n");
					sendAddrArea.append(address);
					
					sendNameText.setEditable(false);
					sendPhoneText.setEditable(false);
					sendAddrArea.setEditable(false);
					sendInfoBtn.setEnabled(false);
				}
			}
		});
		
		//������ ����
		receiveLabel = new LabelDAO("������ ����", FrameVO.font25, orderLayer, 1);
		receiveLabel.setBounds(20, 400, 200, 30);
		receiveLabel.setOpaque(true);
		receiveLabel.setBackground(FrameVO.grayColor);
		receiveNameLabel = new LabelDAO("�̸�", FrameVO.font20, orderLayer, 1);
		receiveNameLabel.setBounds(20, 450, 100, 30);
		receiveNameText = new TextDAO(FrameVO.font18, orderLayer, 1);
		receiveNameText.setBounds(160, 450, 280, 30);
		receivePhoneLabel = new LabelDAO("�޴���ȭ", FrameVO.font20, orderLayer, 1);
		receivePhoneLabel.setBounds(20, 490, 100, 30);
		receivePhoneText = new TextDAO(FrameVO.font18, orderLayer, 1);
		receivePhoneText.setBounds(160, 490, 280, 30);
		receivePhoneText.setText("��)01012345678");
		new EventListenerDAO().removeText(receivePhoneText);
		receiveAddrLabel = new LabelDAO("�ּ�", FrameVO.font20, orderLayer, 1);
		receiveAddrLabel.setBounds(20, 530, 100, 30);
		addressBtn = new ButtonDAO();
		addressBtn.makeGrayButton("�ּ�ã��", orderLayer, 1);
		addressBtn.setLocation(325, 530);
		receiveAddrArea = new JTextArea();
		scrollPane2 = new JScrollPane(receiveAddrArea);
		receiveAddrArea.setBounds(20, 570, 420, 60);
		scrollPane2.setBounds(20, 570, 420, 60);
		receiveAddrArea.setFont(FrameVO.font18);
		receiveAddrArea.setLineWrap(true);
		orderLayer.add(scrollPane2);
		
		//��ҹ�ư
		cancelBtn = new ButtonDAO();
		cancelBtn.makeBlueButton("����ϱ�", orderLayer, 1);
		cancelBtn.setBounds(20, 660, 205, 50);
		//��ҹ�ư Ŭ���� ������������ �̵� �̺�Ʈ
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				orderFrame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		});
		
		//�����ܰ� ��ư
		nextBtn = new ButtonDAO();
		nextBtn.makeBlueButton("�����ܰ�", orderLayer, 1);
		nextBtn.setBounds(235, 660, 205, 50);
		nextBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(senderZip!=0 && receiverZip!=0) {
					System.out.println("clicked");
					//������ ���� �����ϱ�
					receiverInfo();
					orderFrame.dispose();
					new OrderPage2().order2Frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(orderFrame, "�׸��� �������� ä���ּ���", jTitle, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//	�ּ�ã�� ��ư�̺�Ʈ
		getAddrEvent(addressBtn, receiveAddrArea, orderFrame);
		
		//������ �̹���
		new ImageDAO().showTitleIcon(orderFrame);	
//		orderFrame.setVisible(true);
	}
	
	//������ ���� �����ϱ� �޼���
	public List<String> receiverInfo(){
		String name = receiveNameText.getText();
		String phone = receivePhoneText.getText();
		String address = receiveAddrArea.getText();
		
		List<String> list = new ArrayList<String>();
		list.add(name);
		list.add(phone);
		list.add(address);
		
		return list;
	}
	
	//�����ȣ int������ ������ �ֱ� �޼���(��� ���� �ʿ�)
	public int getZip() {
		String[] addr = new String[2];
		addr = userList.get(UserVO.index).getAddress().split("\\)");
		int zip = Integer.parseInt(addr[0].substring(1));
		return zip;
	}
	
	//���� �Է¹��� �ּҿ��� �����ȣ ���ϱ�
	public int getZip(JTextField addr1, JTextField addr2) {
		String address = addr1.getText() + " " + addr2.getText();
		String[] addr = new String[2];
		addr = address.split("\\)");
		int zip = Integer.parseInt(addr[0].substring(1));
		return zip;
	}
	
	//�����ȣ ������ ������ �ּ� �������� �޼���
	public String getRestAddress() {
		String[] addr = new String[2];
		addr = userList.get(UserVO.index).getAddress().split("\\)");
		String address = addr[1].trim();
		return address;
	}
	
	//���� �Է¹��� �ּҿ��� �����ȣ ������ ������ �ּ� ��������
	public String getRestAddress(JTextField addr1, JTextField addr2) {
		String address1 = addr1.getText() + " " + addr2.getText();
		String[] addr = new String[2];
		addr = address1.split("\\)");
		String address = addr[1].trim();
		return address;
	}
	
	//�ּ�ã�� ��ư �̺�Ʈ �� frame ���� �� �ּ�ã��
	public void getAddrEvent(JButton btn, JTextArea area, JFrame frame) {
		btn.addActionListener(new ActionListener() {
			FrameDAO addressFrame;
			PanelDAO addressLayer, bgPanelA;
			LabelDAO titleLabelA, zipLabel;
			TextDAO addressTextA, addressTextB, zipText;
			ButtonDAO addressBtnA, zipBtn, applyBtn, cancelBtn;
			JTextArea addressArea;
			int zip;
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
				addressTextA.setBounds(15, 150, 300, 30);
				addressTextA.setText("��)���̷� 15(���� �ʼ�)");
				new EventListenerDAO().removeText(addressTextA);
				addressBtnA = new ButtonDAO();
				addressBtnA.makeGrayButton("�ּҰ˻�", addressLayer, 1);
				addressBtnA.setLocation(330, 150);		
				
				//�˻���� ������ area
				addressArea = new JTextArea();
				JScrollPane scrollPanel = new JScrollPane(addressArea);
				addressArea.setBounds(15, 200, 430, 300);
				scrollPanel.setBounds(15, 200, 430, 300);
				addressLayer.add(scrollPanel, new Integer(1));
				addressArea.setFont(FrameVO.font18);
				addressArea.setBackground(FrameVO.grayColor);
				addressArea.setEditable(false);
				
				//�ּҼ��� combobox		
				zipLabel = new LabelDAO("�ּҸ� �����ϼ���", FrameVO.font20, addressLayer, 1);
				zipLabel.setBounds(15, 520, 200, 30);
				JComboBox<String> numBox = new JComboBox<String>();
				numBox.setBounds(240, 520, 80, 30);
				numBox.setFont(FrameVO.font18);
				numBox.setBackground(Color.WHITE);
				addressLayer.add(numBox);
				zipBtn = new ButtonDAO();
				zipBtn.makeGrayButton("����", addressLayer, 1);
				zipBtn.setLocation(330, 520);
				
				zipText = new TextDAO(FrameVO.font18, addressLayer, 1);
				zipText.setBounds(15, 560, 430, 30);
				zipText.setEditable(false);
				addressTextB = new TextDAO(FrameVO.font18, addressLayer, 1);
				addressTextB.setBounds(15, 600, 430, 30);
				addressTextB.setText("������ �ּҸ� �Է��� �ּ���");
				new EventListenerDAO().removeText(addressTextB);
				
				
				//�ּҰ˻� ��ư �̺�Ʈ
				addressBtnA.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new JoinPage().findZipCode(addressArea, addressFrame, addressTextA, numBox, zipBtn, zipText);
					}
				});		
				
				//����, ��ҹ�ư
				applyBtn =new ButtonDAO();
				applyBtn.makeGrayButton("����", addressLayer, 1);
				applyBtn.setLocation(100, 660);
				
				applyBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//�˻�â�� �˻�� �Է����� �ʾ��� ���
						if(zipText.getText().equals("")) {
							JOptionPane.showMessageDialog(addressFrame, "�ּҸ� ������ �ּ���", "�ּҰ˻�", JOptionPane.ERROR_MESSAGE);
						}else {
							//�˻� ��� �����ȣ�� ������ �ּҸ� ���� ����(�ù��� ����� ���� �ʿ�)
							zip = getZip(zipText, addressTextB);
							String address = getRestAddress(zipText, addressTextB);
							area.append(zip+"\r\n");
							area.append(address);
							receiverZip = zip;
							System.out.println("receiverZip:"+receiverZip);
							btn.setEnabled(false);
							addressFrame.dispose();
							frame.setVisible(true);
						}
					}
				});
				cancelBtn = new ButtonDAO();
				cancelBtn.makeGrayButton("���", addressLayer, 1);
				cancelBtn.setLocation(240, 660);
				new EventListenerDAO().movePage(cancelBtn, addressFrame, frame);
				
				addressFrame.setVisible(true);
				frame.setVisible(false);
				
				new ImageDAO().showTitleIcon(addressFrame);	
			}
		});
	}
}
