package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

import dao.ButtonDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dao.TextDAO;
import dto.FrameVO;

public class LoadMoneyPage {
	//����������
	
	FrameDAO loadMoneyFrame;
	PanelDAO loadMoneyLayer;
	LabelDAO titleLabel, currentMoneyLabel, currentMoney2Label, loadMoneyLabel, loadMoney2Label,
			totalMoneyLabel, totalMoney2Label;
	ButtonDAO btn1000, btn10000, btn50000, btnC;
	TextDAO text;
	int currentMoney, loadMoney, totalLoadedMoney;
	
	public LoadMoneyPage() {
		loadMoneyFrame = new FrameDAO();
		loadMoneyFrame.makeFrame();
		loadMoneyLayer = new PanelDAO();
		loadMoneyLayer.makeLayer();
		loadMoneyFrame.add(loadMoneyLayer);
		
		new PanelDAO().makeColorPanel(loadMoneyLayer);
		titleLabel = new LabelDAO("�����ϱ�", FrameVO.font40, loadMoneyLayer, 1);
		titleLabel.makeTitleLabel();
		
		ImageDAO cardImage = new ImageDAO();
		cardImage.showImage("image/card.png", 60, 150, 400, 240, loadMoneyFrame, loadMoneyLayer);
		
		//1000, 10000, 50000����ư
		btn50000 = new ButtonDAO();
		btn50000.makeGrayButton("50000��", loadMoneyLayer, 1);
		btn50000.setBounds(70, 400, 150, 40);
		
		btn10000 = new ButtonDAO();
		btn10000.makeGrayButton("10000��", loadMoneyLayer, 1);
		btn10000.setBounds(240, 400, 150, 40);
		
		btn1000 = new ButtonDAO();
		btn1000.makeGrayButton("1000��", loadMoneyLayer, 1);
		btn1000.setBounds(70, 450, 150, 40);
		
		btnC = new ButtonDAO();
		btnC.makeGrayButton("�ʱ�ȭ", loadMoneyLayer, 1);
		btnC.setBounds(240, 450, 150, 40);
		
		//��ư Ŭ���� �ݾ� �߰�
		plusMoney(btn50000);
		plusMoney(btn10000);
		plusMoney(btn1000);
		plusMoney(btnC);
		
		//�����ݾ�
		loadMoneyLabel = new LabelDAO("�����ݾ�", FrameVO.font20, loadMoneyLayer, 1);
		loadMoneyLabel.setBounds(70, 360, 200, 30);
		loadMoney2Label = new LabelDAO(loadMoney+"��", FrameVO.font20, loadMoneyLayer, 1);
		loadMoney2Label.setBounds(230, 360, 150, 30);
		loadMoney2Label.setHorizontalAlignment(JLabel.TRAILING);
		loadMoney2Label.setForeground(Color.BLUE);		

		//�����ܾ� 
		currentMoneyLabel = new LabelDAO("�����ܾ�", FrameVO.font20, loadMoneyLayer, 1);
		currentMoneyLabel.setBounds(70, 410, 200, 30);
		currentMoney2Label = new LabelDAO(currentMoney+"��", FrameVO.font20, loadMoneyLayer, 1);
		currentMoney2Label.setBounds(230, 410, 150, 30);
		currentMoney2Label.setHorizontalAlignment(JLabel.TRAILING);
		
		
		//�������ܾ�
		totalMoneyLabel = new LabelDAO("���� �� �ܾ�", FrameVO.font20, loadMoneyLayer, 1);
		totalMoneyLabel.setBounds(70, 460, 200, 30);
		totalMoney2Label = new LabelDAO(totalLoadedMoney+"��", FrameVO.font20, loadMoneyLayer, 1);
		totalMoney2Label.setBounds(230, 460, 150, 30);
		totalMoney2Label.setHorizontalAlignment(JLabel.TRAILING);
		totalMoney2Label.setForeground(Color.RED);
		
		
		//�������̹���
		new ImageDAO().showTitleIcon(loadMoneyFrame);
		loadMoneyFrame.setVisible(true);
		
	}
	
	//��ư Ŭ���� �ݾ� �߰� �޼���
	public void plusMoney(JButton btn) {
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn50000) {
					loadMoney += 50000;
				}
				if(e.getSource()==btn10000) {
					loadMoney += 10000;
				}
				if(e.getSource()==btn1000) {
					loadMoney += 1000;
				}
				if(e.getSource()==btnC) {
					loadMoney = 0;
				}
				loadMoney2Label.setText(loadMoney+"��");
			}
		});
	}		
}
