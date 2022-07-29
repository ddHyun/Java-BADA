package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

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
import dao.UserDAO;
import dto.FrameVO;
import dto.UserVO;

public class MyPage {
	FrameDAO mypageFrame;
	PanelDAO mypageLayer;
	LabelDAO titleLabel, nameLabel, phoneLabel, addressLabel;
	ButtonDAO infoBtn, pwBtn, changeBtn, addressBtn;
	TextDAO phoneText;
	JTextArea addressArea;
	JScrollPane addressScrollPane;
	ArrayList<UserVO> userList = new ArrayList<UserVO>();
	
	public MyPage() {
		mypageFrame = new FrameDAO();
		mypageFrame.makeFrame();
		mypageLayer = new PanelDAO();
		mypageLayer.makeLayer();
		mypageFrame.add(mypageLayer);
		
		new PanelDAO().makeColorPanel(mypageLayer);
		String name = new UserDAO().getInfo().getName();
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
//		mypageFrame.setVisible(true);
	}
	
	//���� ����
	ActionListener changeInfo = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
				case "�⺻���� ����":
					phoneLabel = new LabelDAO("�޴���ȭ", FrameVO.font20, mypageLayer, 1);
					phoneLabel.setBounds(20, 190, 100, 30);
					phoneText = new TextDAO(FrameVO.font18, mypageLayer, 1);
					phoneText.setBounds(130, 190, 310, 30);					
					String phone = new UserDAO().getInfo().getPhone();
					phoneText.setText(phone);
					new EventListenerDAO().removeText(phoneText);
					addressLabel = new LabelDAO("�ּ�", FrameVO.font20, mypageLayer, 1);
					addressLabel.setBounds(20, 230, 100, 30);
					addressBtn = new ButtonDAO();
					addressBtn.makeGrayButton("�ּ�ã��", mypageLayer, 1);
					addressBtn.setLocation(130, 230);
					addressArea = new JTextArea();
					addressScrollPane = new JScrollPane(addressArea);
					addressArea.setBounds(130, 270, 310, 70);
					addressScrollPane.setBounds(130, 270, 310, 70);
					addressArea.setFont(FrameVO.font18);
					addressArea.setLineWrap(true);
					mypageLayer.add(addressScrollPane);
					String address = new UserDAO().getInfo().getAddress();
					addressArea.setText(address);
					addressArea.setEditable(false);
					
					//�ּҰ˻���ư �̺�Ʈ
					new UserDAO().getAddrEvent(addressBtn, addressArea, mypageFrame);					
					
					//�����ϱ�
					changeBtn = new ButtonDAO();
					changeBtn.makeGrayButton("�����ϱ�", mypageLayer, 1);
					changeBtn.setBounds(200, 350, 150, 30);
					changeBtn.addActionListener(saveInfo);
					
					//�����ϱ� ��ư Ŭ���� ���� ����
					break;
				case "��й�ȣ ����":
					break;
			}
		}
	};
	
	
	//��������
	ActionListener saveInfo = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//�α����� ȸ������ vo�� ���
			userList = new JoinPage().getUserInfo();
			UserVO vo = userList.get(UserVO.index);
			String phone = phoneText.getText().toString();
			String address = addressArea.getText().toString();
			int size = new UserDAO().getListSize();
			
			try {
				
				if(!phone.equals(vo.getPhone())|| !address.equals(vo.getAddress())) {
					if(!phone.equals(vo.getPhone())) {
						vo.setPhone(phone);
						System.out.println("��ȭ��ȣ ���� : "+vo.getPhone());
					}
					if(!address.equals(vo.getAddress())) {
						vo.setAddress(address);
						System.out.println("�ּ� ���� : "+vo.getAddress());
					}
					
					
					FileWriter output = new FileWriter(new File(new UserVO().getUserPath()+new UserVO().getUserPath2()));
					for (int i = 0; i < size; i++) {

						UserVO uVO = userList.get(i);
						output.write(uVO.getNumber()+"\t");
						output.write(uVO.getId()+"\t");
						output.write(uVO.getPw()+"\t");
						output.write(uVO.getName()+"\t");
						output.write(uVO.getGender()+"\t");
						output.write(uVO.getBirth()+"\t");
						output.write(uVO.getPhone()+"\t");
						output.write(uVO.getAddress()+"\r\n");
					}
					JOptionPane.showMessageDialog(mypageFrame, "������ ����Ǿ����ϴ�", "����������", JOptionPane.PLAIN_MESSAGE);
					output.close();
					
				}else {
					JOptionPane.showMessageDialog(mypageFrame, "��������� �����ϴ�", "����������", JOptionPane.INFORMATION_MESSAGE);
				}
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(mypageFrame, "������ �������� �ʽ��ϴ�", "�α���", JOptionPane.ERROR_MESSAGE);
				}//catch
				
		}//actionPerformed()
	};//saveInfo()
	
	
	
//	public void changeInfo(JTextField text, JFrame frame) {
//		
//		try {
//			
//			getInfo().setPw(password);
//			
//			FileWriter output = new FileWriter(new File(new UserVO().getUserPath()+new UserVO().getUserPath2()));
//			for (int i = 0; i < userList.size(); i++) {
//				UserVO vo = userList.get(i);
//				output.write(vo.getNumber()+"\t");
//				output.write(vo.getId()+"\t");
//				output.write(vo.getPw()+"\t");
//				output.write(vo.getName()+"\t");
//				output.write(vo.getGender()+"\t");
//				output.write(vo.getBirth()+"\t");
//				output.write(vo.getPhone()+"\t");
//				output.write(vo.getAddress()+"\r\n");
//			}
//			output.close();
//			
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(frame, "������ �������� �ʽ��ϴ�", "�α���", JOptionPane.ERROR_MESSAGE);
//		}
//	}	
}
