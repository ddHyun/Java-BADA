package dao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dto.FrameVO;
import dto.UserVO;
import view.JoinPage;

public class UserDAO extends UserVO implements User{

	ArrayList<UserVO> userList;
	UserVO vo;
	
	public UserDAO() {
		userList = new ArrayList<UserVO>();
		userList = new JoinPage().getUserInfo();
	}
	
	//ȸ����� ��������
	public ArrayList<UserVO> getList(){
		return userList;
	}
	
	//�α����� ��ü ���� �������� 
	public UserVO getInfo() {
		vo = userList.get(UserVO.index);
		return vo;
	}
	
	//userList ������
	public int getListSize() {
		int size = userList.size();
		return size;
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
							area.setText("");
							area.append("("+zip+") ");
							area.append(address);
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
	
	//���� �Է¹��� �ּҿ��� �����ȣ ���ϱ�
	public int getZip(JTextField addr1, JTextField addr2) {
		String address = addr1.getText() + " " + addr2.getText();
		String[] addr = new String[2];
		addr = address.split("\\)");
		int zip = Integer.parseInt(addr[0].substring(1));
		return zip;
	}
	
	//���� �Է¹��� �ּҿ��� �����ȣ ������ ������ �ּ� ��������
	public String getRestAddress(JTextField addr1, JTextField addr2) {
		String address1 = addr1.getText() + " " + addr2.getText();
		String[] addr = new String[2];
		addr = address1.split("\\)");
		String address = addr[1].trim();
		return address;
	}
	
	
}
