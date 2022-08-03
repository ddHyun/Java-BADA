package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.ButtonDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.OrderDAO;
import dao.PanelDAO;
import dao.TextDAO;
import dto.FrameVO;
import dto.OrderVO;

public class TrackPage {
	//�ֹ�����, �����ȸ ������
	
	FrameDAO trackFrame;
	PanelDAO trackLayer;
	LabelDAO titleLabel, orderDetailLabel, trackingLabel, resultLabel, result2Label;
	TextDAO trackingText;
	JTextArea orderDetailArea;
	JScrollPane orderDetailScroll;
	ButtonDAO listBtn, detailBtn, trackingBtn, backBtn;
	ArrayList<OrderVO> orderList;
	OrderVO vo;
	int trackingIndex;//�ܺ����Ͽ� �ִ� �ֹ��������� �Է��� �����ȣ�� ��ġ�ϴ� index ����� ����

	public TrackPage() {
		trackFrame = new FrameDAO();
		trackFrame.makeFrame();
		trackLayer = new PanelDAO();
		trackLayer.makeLayer();
		trackFrame.add(trackLayer);
		
		new PanelDAO().makeColorPanel(trackLayer);
		titleLabel = new LabelDAO("�ֹ������ȸ", FrameVO.font40, trackLayer, 1);
		titleLabel.makeTitleLabel();
		
		//�ֹ�������ȸ
		orderDetailLabel = new LabelDAO("�ֹ�����", FrameVO.font20, trackLayer, 1);
		orderDetailLabel.setBounds(20, 140, 150, 30);
		orderDetailArea = new JTextArea();
		orderDetailScroll = new JScrollPane(orderDetailArea);
		orderDetailArea.setBounds(30, 180, 410, 200);
		orderDetailScroll.setBounds(30, 180, 410, 200);
		orderDetailArea.setFont(FrameVO.font18);
		orderDetailArea.setLineWrap(true);
		trackLayer.add(orderDetailScroll);		
		orderDetailArea.setEditable(false);
		
		listBtn = new ButtonDAO();
		listBtn.makeGrayButton("��������", trackLayer, 1);
		listBtn.setBounds(330, 140, 110, 30);
		detailBtn = new ButtonDAO();
		detailBtn.makeGrayButton("�󼼺���", trackLayer, 1);
		detailBtn.setBounds(210, 140, 110, 30);
		detailBtn.setVisible(false);
		
		//�ֹ����� Ȯ��
		listBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				orderList = new OrderDAO().showOrderDetail();
				if(orderList.size()>0) {
					detailBtn.setVisible(true);
					orderDetailArea.append("     �����ȣ\t            ��¥\t          ��ǰ\r\n");
					orderDetailArea.append("---------------------------------------------------------\r\n");
					for (int i = 0; i < orderList.size(); i++) {
						vo = orderList.get(i);
						orderDetailArea.append("    "+vo.getTrackingNumber()+"\t     "+vo.getDate()+"\t        "+vo.getStuff()+"\r\n");
					}
					
				}else {
					JOptionPane.showMessageDialog(trackFrame, "��ȸ����� �����ϴ�", "�ֹ������ȸ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//�󼼺��� ��ư Ŭ�� �� : �󼼳��� ����
		detailBtn.addActionListener(showOrder);
		
		//�����ȸ
		trackingLabel = new LabelDAO("�����ȸ", FrameVO.font20, trackLayer, 1);
		trackingLabel.setBounds(20, 420, 100, 30);
		trackingText = new TextDAO(FrameVO.font18, trackLayer, 1);
		trackingText.setBounds(130, 420, 180, 30);
		trackingBtn = new ButtonDAO();
		trackingBtn.makeGrayButton("��ȸ�ϱ�", trackLayer, 1);
		trackingBtn.setBounds(320, 420, 120, 30);
		
		trackingBtn.addActionListener(trackOrder);
		
		backBtn = new ButtonDAO();
		backBtn.makeBlueButton("���ư���", trackLayer, 1);
		backBtn.setBounds(30, 640, 400, 50);
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				trackFrame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		});
		
		//�������̹���
		new ImageDAO().showTitleIcon(trackFrame);
//		trackFrame.setVisible(true);
	}
	
	//�����ȣ�� ��ġ�ϴ� indexã��
	public int trackingIndex(String trackingNum) {
		trackingIndex = -1;
		for (int i = 0; i < orderList.size(); i++) {
			if(orderList.get(i).getTrackingNumber().equals(trackingNum)) {
				trackingIndex = i;
			}	
		}
		return trackingIndex;
	}
	
	//�ֹ� �󼼳��� ����
	ActionListener showOrder = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String trackingNum = JOptionPane.showInputDialog(trackFrame, "�����ȣ�� �Է��ϼ���").trim();

			trackingIndex = trackingIndex(trackingNum);
			if(trackingIndex==-1) {
				JOptionPane.showMessageDialog(trackFrame, "��ȸ����� �����ϴ�", "�ֹ������ȸ", JOptionPane.ERROR_MESSAGE);
			}else {	//�����ȣ-��¥-����-�ڵ�-ũ��-����-�ڽ�����-���-�������-�������̸�-��ȭ-�ּ�
				vo = orderList.get(trackingIndex);
				String detail = "�����ȣ : "+ vo.getTrackingNumber()+"\r\n"
								+"�ֹ���¥ : "+ vo.getDate()+"\r\n"
								+"���빰 : "+vo.getStuff()+"\r\n"
								+"ũ�� / ���� : "+ vo.getSize()+"cm / "+ vo.getWeight()+"kg"+"\r\n"
								+"�ڽ����� : "+vo.getBox()+"��"+"\r\n"
								+"��û���� : "+vo.getNote()+"\r\n"
								+"������� : "+vo.getCharge()+"\r\n"
								+"������ �̸� : "+vo.getName()+"\r\n"
								+"������ ��ȭ : "+vo.getPhone()+"\r\n"
								+"������ �ּ� : \r\n"+vo.getAddress()+"\r\n";
				JOptionPane.showMessageDialog(trackFrame, detail, "�ֹ������ȸ", JOptionPane.PLAIN_MESSAGE);
			}			
		}
	};
	
	//�����ȸ
	ActionListener trackOrder = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String trackingNum = trackingText.getText().toString().trim();
			trackingIndex = trackingIndex(trackingNum);
			String[] arMsg = {"������Դϴ�", "���ݸ� ��ٷ��ּ���!", "����� �Ϸ�Ǿ����ϴ�", "������ �� �̿����ּ���"};
			
			if(trackingIndex == -1) {
				JOptionPane.showMessageDialog(trackFrame, "��ȸ����� �����ϴ�", "�ֹ������ȸ", JOptionPane.ERROR_MESSAGE);
			}else {
				vo = orderList.get(trackingIndex);
				resultLabel = new LabelDAO("", FrameVO.font18, trackLayer, 1);
				resultLabel.setBounds(220, 510, 200, 30);
				resultLabel.setHorizontalAlignment(JLabel.CENTER);
				result2Label = new LabelDAO("", FrameVO.font18, trackLayer, 1);
				result2Label.setBounds(220, 540, 200, 30);
				result2Label.setHorizontalAlignment(JLabel.CENTER);				
				
				//�ֹ���¥�� ���糯¥�� �����ϸ� '��ǰ�غ���', +1�̸� '�����', +2�� '��ۿϷ�'
				//�ֹ���¥
				String[] orderDate = vo.getDate().split("-");
				int month1 = Integer.parseInt(orderDate[1]);
				int day1 = Integer.parseInt(orderDate[2]);
				
				//���糯¥
				String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String[] nowDate = now.split("-");
				int month2 = Integer.parseInt(nowDate[1]);
				int day2 = Integer.parseInt(nowDate[2]);
				
				if(month1<=month2) {
					if(day1==day2) {
						//�̹���panel
						JPanel imgPanel = new JPanel();
						imgPanel.setBounds(20, 440, 200, 200);
						imgPanel.setBackground(Color.WHITE);
						JLabel imgLabel = new JLabel();
						ImageIcon icon = new ImageIcon("image/truck.gif");
						imgLabel.setIcon(icon);
						imgPanel.add(imgLabel);
						trackLayer.add(imgPanel, new Integer(0));
						resultLabel.setText(arMsg[0]);
						result2Label.setText(arMsg[1]);
					}else if((day2-day1)==1) {
						//�̹���panel
						JPanel imgPanel1 = new JPanel();
						imgPanel1.setBounds(20, 440, 200, 200);
						imgPanel1.setBackground(Color.WHITE);
						JLabel imgLabel1 = new JLabel();
						ImageIcon icon1 = new ImageIcon("image/arrival.gif");
						imgLabel1.setIcon(icon1);
						imgPanel1.add(imgLabel1);
						trackLayer.add(imgPanel1, new Integer(0));
						resultLabel.setText(arMsg[2]);
						result2Label.setText(arMsg[3]);
					}
				}
			}
		}
	};
}
