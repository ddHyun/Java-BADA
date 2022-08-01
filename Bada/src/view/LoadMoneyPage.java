package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.ButtonDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dao.UserDAO;
import dto.FrameVO;
import dto.MoneyVO;
import dto.StuffVO;
import dto.UserVO;

public class LoadMoneyPage {
	//����������
	
	FrameDAO loadMoneyFrame;
	PanelDAO loadMoneyLayer;
	LabelDAO titleLabel, currentMoneyLabel, currentMoney2Label, loadMoneyLabel, loadMoney2Label,
			totalMoneyLabel, totalMoney2Label, messageLabel;
	ButtonDAO btn1000, btn10000, btn50000, btnC, yesBtn, noBtn, payBtn, cancelBtn;
	int currentMoney, loadMoney, totalLoadedMoney;
	ArrayList<UserVO> userList = new ArrayList<>();
	MoneyVO mVO = MoneyVO.getInstance();
	
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
		
		//���� -> '��''�ƴϿ�'��ư
		noBtn = new ButtonDAO();
		noBtn.makeBlueButton("�ƴϿ�", loadMoneyLayer, 1);
		noBtn.setBounds(20, 640, 200, 50);
		noBtn.setVisible(false);
		//'�ƴϿ�'��ư Ŭ�� : ���� -> ���������� �̵�
		noBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadMoneyFrame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		});
		
		yesBtn = new ButtonDAO();
		yesBtn.makeBlueButton("��", loadMoneyLayer, 1);
		yesBtn.setBounds(240, 640, 200, 50);
		yesBtn.setVisible(false);
		
		//'��'��ư Ŭ�� : �����ϱ�
		loadMoney();
				
		//1000, 10000, 50000����ư
		btn50000 = new ButtonDAO();
		btn50000.makeGrayButton("50000��", loadMoneyLayer, 1);
		btn50000.setBounds(40, 400, 120, 40);
		
		btn10000 = new ButtonDAO();
		btn10000.makeGrayButton("10000��", loadMoneyLayer, 1);
		btn10000.setBounds(170, 400, 120, 40);
		
		btn1000 = new ButtonDAO();
		btn1000.makeGrayButton("1000��", loadMoneyLayer, 1);
		btn1000.setBounds(300, 400, 120, 40);
		
		btnC = new ButtonDAO();
		btnC.makeGrayButton("�ʱ�ȭ", loadMoneyLayer, 1);
		btnC.setBounds(150, 460, 100, 30);
		
		//��ư Ŭ���� �ݾ� �߰�
		plusMoney(btn50000);
		plusMoney(btn10000);
		plusMoney(btn1000);
		plusMoney(btnC);
		
		//�����ݾ�
		loadMoneyLabel = new LabelDAO("�����ݾ�", FrameVO.font20, loadMoneyLayer, 1);
		loadMoneyLabel.setBounds(50, 460, 100, 30);
		loadMoney2Label = new LabelDAO(loadMoney+"��", FrameVO.font20, loadMoneyLayer, 1);
		loadMoney2Label.setBounds(250, 460, 150, 30);
		loadMoney2Label.setHorizontalAlignment(JLabel.TRAILING);
		loadMoney2Label.setForeground(Color.BLUE);		

		//�����ܾ� 
		currentMoneyLabel = new LabelDAO("�����ܾ�", FrameVO.font20, loadMoneyLayer, 1);
		currentMoneyLabel.setBounds(50, 510, 200, 30);
		System.out.println("mVO.getTotalMoney() : "+mVO.getTotalMoney());
		currentMoney = mVO.getTotalMoney();
		currentMoney2Label = new LabelDAO(currentMoney+"��", FrameVO.font20, loadMoneyLayer, 1);
		currentMoney2Label.setBounds(250, 510, 150, 30);
		currentMoney2Label.setHorizontalAlignment(JLabel.TRAILING);		
		
		//�������ܾ�
		totalMoneyLabel = new LabelDAO("���� �� �ܾ�", FrameVO.font20, loadMoneyLayer, 1);
		totalMoneyLabel.setBounds(50, 560, 200, 30);
		totalMoney2Label = new LabelDAO(totalLoadedMoney+"��", FrameVO.font20, loadMoneyLayer, 1);
		totalMoney2Label.setBounds(250, 560, 150, 30);
		totalMoney2Label.setHorizontalAlignment(JLabel.TRAILING);
		totalMoney2Label.setForeground(Color.RED);
		
		//��������		
		messageLabel = new LabelDAO("�����Ͻðڽ��ϱ�?", FrameVO.font18, loadMoneyLayer, 1);
		messageLabel.setBounds(20, 610, 400, 25);
		messageLabel.setHorizontalAlignment(JLabel.CENTER);
		messageLabel.setVisible(false);		
		
		//�����Ϸ� ���� ��ư
		payBtn = new ButtonDAO();
		payBtn.makeBlueButton("�����Ϸ� ����", loadMoneyLayer, 1);
		payBtn.setBounds(30, 640, 400, 50);
		payBtn.setVisible(false);
		
		cancelBtn = new ButtonDAO();
		cancelBtn.makeBlueButton("���ư���", loadMoneyLayer, 1);
		cancelBtn.setBounds(30, 640, 400, 50);
		cancelBtn.setVisible(false);
		
		//�����Ϸ� ���� ��ư : ���� -> ����3������
		payBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadMoneyFrame.dispose();
				new PayPage().payFrame.setVisible(true);
			}
		});
		
		//���ư��� ��ư : ���� -> ����������
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadMoneyFrame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		});
		
		
		//�������̹���
		new ImageDAO().showTitleIcon(loadMoneyFrame);
//		loadMoneyFrame.setVisible(true);
		
	}
	
	//��ư Ŭ���� �ݾ� �߰� �޼���
	public void plusMoney(JButton btn) {
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn50000) {
					loadMoney += 50000;
					messageLabel.setVisible(true);
					noBtn.setVisible(true);
					yesBtn.setVisible(true);
				}
				if(e.getSource()==btn10000) {
					loadMoney += 10000;
					messageLabel.setVisible(true);
					noBtn.setVisible(true);
					yesBtn.setVisible(true);
				}
				if(e.getSource()==btn1000) {
					loadMoney += 1000;
					messageLabel.setVisible(true);
					noBtn.setVisible(true);
					yesBtn.setVisible(true);
				}
				if(e.getSource()==btnC) {
					loadMoney = 0;
					messageLabel.setVisible(false);
					noBtn.setVisible(false);
					yesBtn.setVisible(false);
				}
				loadMoney2Label.setText(loadMoney+"��");
				totalLoadedMoney = loadMoney+mVO.getTotalMoney();
				totalMoney2Label.setText(totalLoadedMoney+"��");
			}
		});
	}	
	
	//'��'��ư Ŭ���� �����ϱ� �޼���
	public void loadMoney() {
		yesBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int chance = 3;
				
				while(chance>0) {					
					String input = 
							JOptionPane.showInputDialog(loadMoneyFrame, "��й�ȣ�� �Է��ϼ���(��ȸ "+chance+"��)", 
									"�����ϱ�", JOptionPane.DEFAULT_OPTION);
//					userList = new JoinPage().getUserInfo();
//					UserVO vo = userList.get(UserVO.index);
//					String pwd = vo.getPw();
					String pwd = new UserDAO().getInfo().getPw();
					if(input.equals(pwd)) {
						mVO.setLoadMoney(loadMoney);
						mVO.setTotalMoney(loadMoney+mVO.getTotalMoney());
						JOptionPane.showMessageDialog(loadMoneyFrame, "������ �Ϸ�Ǿ����ϴ�", 
								"�����ϱ�", JOptionPane.INFORMATION_MESSAGE);
						currentMoney2Label.setText(mVO.getTotalMoney()+"��");
						loadMoney = 0;
						loadMoney2Label.setText(loadMoney+"��");
						messageLabel.setVisible(false);
						yesBtn.setVisible(false);
						noBtn.setVisible(false);
						
						//�ܺ����Ͽ� �������� �����ϱ�
						saveContent();
						
						if(StuffVO.getInstance().getBox()==0) {//����������->������������ �Ѿ���� ��
							cancelBtn.setVisible(true);
						}else {//����3 ->������������ �Ѿ���� ��
							payBtn.setVisible(true);
						}
						
						break;
					}else if(chance > 1){
						--chance;
						JOptionPane.showMessageDialog(loadMoneyFrame, "��й�ȣ �����Դϴ�.\r\n�ٽ� �õ��� �ּ���", 
								"�����ϱ�", JOptionPane.ERROR_MESSAGE);					
					}else if(chance == 1) {
						--chance;
						JOptionPane.showMessageDialog(loadMoneyFrame, "��й�ȣ 3ȸ �����Դϴ�. ó������ �ٽ� �õ��� �ּ���", 
								"�����ϱ�", JOptionPane.ERROR_MESSAGE);
						loadMoneyFrame.dispose();
						new MainPage().mainFrame.setVisible(true);
					}
				}
			}
		});
	}
	
	//�������� �����ϱ� �޼���
	public void saveContent() {
		//�α��ε� ���̵�� ���� �����
		String folderName = new UserDAO().getInfo().getId();
		File folder = new File(new UserVO().getUserPath()+folderName+"/");
		if(!folder.exists()) {
			folder.mkdirs();
		}
		try {
			//���̵����� �ȿ� �������� ������� �����
//			String fileName = "loadMoneyDetail.txt"; 
			mVO.setFilePath(folderName);
			File file = new File(mVO.getFilePath());
			file.createNewFile();
			
			//�ܺ����Ͽ� ����
			FileWriter output = new FileWriter(file, true);
			//loadMoney(�����ݾ�)/ totalMoney(�� �ܾ�)/ charge(�����ݾ�)//������¥
			output.write(mVO.getLoadMoney()+"\t");
			output.write(mVO.getTotalMoney()+"\t");
			output.write(mVO.getCharge()+"\t");
			output.write(mVO.getDate()+"\r\n");
			output.close();
		}catch(Exception e) {}
	}
}
