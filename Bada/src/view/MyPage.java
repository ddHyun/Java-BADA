package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import dao.ButtonDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dao.TextDAO;
import dto.FrameVO;
import dto.UserVO;

public class MyPage {
	FrameDAO mypageFrame;
	PanelDAO mypageLayer;
	LabelDAO titleLabel, nameLabel, phoneLabel, addressLabel;
	ButtonDAO infoBtn, pwBtn, changeBtn;
	TextDAO phoneText, addressText;
	
	public MyPage() {
		mypageFrame = new FrameDAO();
		mypageFrame.makeFrame();
		mypageLayer = new PanelDAO();
		mypageLayer.makeLayer();
		mypageFrame.add(mypageLayer);
		
		new PanelDAO().makeColorPanel(mypageLayer);
		String name = new MainPage().getLoginUser().getName();
		titleLabel = new LabelDAO("����������", FrameVO.font40, mypageLayer, 1);
		titleLabel.makeTitleLabel();
		
		//ȸ������ ����
		nameLabel = new LabelDAO(name+"��", FrameVO.font18, mypageLayer, 1);
		nameLabel.setBounds(20, 140, 80, 30);
		infoBtn = new ButtonDAO();
		infoBtn.makeGrayButton("�⺻���� ����", mypageLayer, 1);
		infoBtn.setBounds(110, 140, 160, 30);
		pwBtn = new ButtonDAO();
		pwBtn.makeGrayButton("��й�ȣ ����", mypageLayer, 1);
		pwBtn.setBounds(280, 140, 160, 30);
		
		infoBtn.addActionListener(changeInfo);
		pwBtn.addActionListener(changeInfo);
		
		
		//�������̹���
		new ImageDAO().showTitleIcon(mypageFrame);
		mypageFrame.setVisible(true);
	}
	
	//�⺻���� ����
	ActionListener changeInfo = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
				case "�⺻���� ����":
					phoneLabel = new LabelDAO("�޴���ȭ", FrameVO.font20, mypageLayer, 1);
					phoneLabel.setBounds(20, 190, 100, 30);
					phoneText = new TextDAO(FrameVO.font18, mypageLayer, 1);
					phoneText.setBounds(130, 190, 250, 30);
//					UserVO vo = new MainPage().getLoginUser();
					
//					String phone = new MainPage().getLoginUser().getPhone();
//					System.out.println(new MainPage().getLoginUser().getPhone());
//					phoneText.setText(phone);
					changeBtn = new ButtonDAO();
					changeBtn.makeGrayButton("����", mypageLayer, 1);
					changeBtn.setBounds(380, 190, 60, 30);
					addressLabel = new LabelDAO("�ּ�", FrameVO.font20, mypageLayer, 1);
					addressLabel.setBounds(20, 230, 100, 30);
					addressText = new TextDAO(FrameVO.font18, mypageLayer, 1);
					addressText.setBounds(130, 230, 250, 30);
					break;
				case "��й�ȣ ����":
					break;
			}
		}
	};
	

}
