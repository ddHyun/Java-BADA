package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import dao.ButtonDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dto.FrameVO;
import dto.ReceiverVO;
import dto.StuffVO;

public class PayPage {
	
	FrameDAO payFrame;
	PanelDAO payLayer;
	ButtonDAO loadMoneyBtn, payBtn, cancelBtn;
	LabelDAO titleLabel, basicChargeLabel, boxNumLabel, distanceLabel1, distanceLabel2, lineLabel,
				totalChargeLabel, loadMoneyLabel;
	int basicCharge, boxNum, distance, totalCharge, loadMoney = 0;
	
	public PayPage() {
		payFrame = new FrameDAO();
		payFrame.makeFrame();
		payLayer = new PanelDAO();
		payLayer.makeLayer();
		payFrame.add(payLayer);
		
		new PanelDAO().makeColorPanel(payLayer);
		titleLabel = new LabelDAO("�����ϱ�", FrameVO.font40, payLayer, 1);
		titleLabel.makeTitleLabel();
		
		calCharge();
		
		basicChargeLabel = new LabelDAO("�⺻���                                         "+basicCharge, FrameVO.font20, payLayer, 1);
		basicChargeLabel.setBounds(20, 250, 400, 30);
		basicChargeLabel.setHorizontalAlignment(JLabel.TRAILING);
		boxNumLabel = new LabelDAO("�ڽ�����                               x          "+boxNum, FrameVO.font20, payLayer, 1);
		boxNumLabel.setBounds(20, 300, 400, 30);
		boxNumLabel.setHorizontalAlignment(JLabel.TRAILING);
		distanceLabel1 = new LabelDAO("�Ÿ�                                     + ", FrameVO.font20, payLayer, 1);
		distanceLabel1.setBounds(65, 350, 290, 30);
		distanceLabel2 = new LabelDAO(""+distance, FrameVO.font20, payLayer, 1);
		distanceLabel2.setBounds(310, 350, 110, 30);
		distanceLabel2.setHorizontalAlignment(JLabel.TRAILING);
		lineLabel = new LabelDAO("----------------------------------------------------", FrameVO.font20, payLayer, 1);
		lineLabel.setBounds(20, 400, 400, 10);
		lineLabel.setHorizontalAlignment(JLabel.TRAILING);
		totalChargeLabel = new LabelDAO("�� �����ݾ�   "+totalCharge+"��", FrameVO.font25B, payLayer, 1);
		totalChargeLabel.setBounds(20, 430, 400, 30);
		//JLabel�� text ���� ����
		totalChargeLabel.setForeground(Color.RED);
		totalChargeLabel.setHorizontalAlignment(JLabel.TRAILING);
		
		//�����ݾ�
		
		//�� �����ݾ� 
		loadMoneyLabel = new LabelDAO("���� �����ݾ�   "+loadMoney+"��", FrameVO.font20, payLayer, 1);
		loadMoneyLabel.setBounds(20, 590, 400, 30);
		loadMoneyLabel.setHorizontalAlignment(JLabel.TRAILING);
		loadMoneyLabel.setForeground(Color.BLUE);
		
		//���, ������ư
		cancelBtn = new ButtonDAO();
		cancelBtn.makeGrayButton("����ϱ�", payLayer, 1);
		cancelBtn.setBounds(20, 640, 200, 50);
		cancelBtn.setFont(FrameVO.font30);
		
		///��ҹ�ư : ���� -> ���������� �̵�
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				payFrame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		});
		
		payBtn = new ButtonDAO();
		payBtn.makeBlueButton("�����ϱ�", payLayer, 1);
		payBtn.setBounds(240, 640, 200, 50);
		//�����ݾ׺��� �����ݾ��� ���ų� �����ݾ��� 0�� ��� '�����ϱ�'��ư ��������
		if(totalCharge > loadMoney || loadMoney == 0) {
			payBtn.setVisible(false);
			loadMoneyBtn = new ButtonDAO();
			loadMoneyBtn.makeBlueButton("�����ϱ�", payLayer, 1);
			loadMoneyBtn.setBounds(240, 640, 200, 50);
		}
		
		//�������̹���
		new ImageDAO().showTitleIcon(payFrame);
		payFrame.setVisible(true);

	}
	
	//�����ݾ� ���޼���
	public void calCharge() {		
		int dis = new OrderPage().senderZip - new OrderPage().receiverZip;
		int boxSize = (int)StuffVO.getInstance().getSize();
		int boxWeight = (int)StuffVO.getInstance().getWeight();
		int[] charge = {2500, 3000, 4000, 5500, 7000};
		
		//�ڽ�����
		boxNum = StuffVO.getInstance().getBox();
		//�Ÿ����
		if(dis<0) {
			distance = -dis * 50;
		}else {
			distance = dis * 50;
		}
		//�⺻��� ���
		if(boxSize <= 60) {
			if(0 <= boxWeight && boxWeight <= 2) {
				basicCharge = charge[0];
			}else if(2 < boxWeight && boxWeight <= 5) {
				basicCharge = charge[1];
			}else if(5 < boxWeight && boxWeight <= 10) {
				basicCharge = charge[2];
			}else if(10 < boxWeight && boxWeight <= 20) {
				basicCharge = charge[3];
			}else {
				basicCharge = charge[4];
			}
		}else if(boxSize <= 80) {
			if(0 <= boxWeight && boxWeight <= 5) {
				basicCharge = charge[1];
			}else if(5 < boxWeight && boxWeight <= 10) {
				basicCharge = charge[2];
			}else if(10 < boxWeight && boxWeight <= 20) {
				basicCharge = charge[3];
			}else {
				basicCharge = charge[4];
			}
		}else if(boxSize <= 100) {
			if(0 <= boxWeight && boxWeight <= 10) {
				basicCharge = charge[2];
			}else if(10 < boxWeight && boxWeight <= 20) {
				basicCharge = charge[3];
			}else {
				basicCharge = charge[4];
			}
		}else if(boxSize <= 120) {
			if(0 <= boxWeight && boxWeight <= 20) {
				basicCharge = charge[3];
			}else {
				basicCharge = charge[4];
			}
		}else {
			basicCharge = charge[4];
		}
		
		//�Ѱ����ݾ�
		totalCharge = (basicCharge * boxNum) + distance;
		
	}
	

}