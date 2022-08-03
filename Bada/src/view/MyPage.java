package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.ButtonDAO;
import dao.EventListenerDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.MoneyDAO;
import dao.PanelDAO;
import dao.OrderDAO;
import dao.TextDAO;
import dao.UserDAO;
import dto.FrameVO;
import dto.MoneyVO;
import dto.UserVO;

public class MyPage {
	FrameDAO mypageFrame;
	PanelDAO mypageLayer;
	LabelDAO titleLabel, nameLabel, phoneLabel, addressLabel, pwLabel, pwLabel2, moneyLabel,
			moneyDetailLabel;
	ButtonDAO infoBtn, pwBtn, changeBtn, changeBtn2, addressBtn, moneyBtn, moneyDetailBtn,
			cancelBtn;
	TextDAO phoneText, moneyText;
	JPasswordField pwField, pwField2;
	JTextArea addressArea, moneyDetailArea;
	JScrollPane addressScrollPane, moneyDetailScroll;
	ArrayList<UserVO> userList = new ArrayList<UserVO>();
	ArrayList<MoneyVO> moneyList;
	MoneyVO mVO = MoneyVO.getInstance();
	int click, click2 = 0;//��ư Ŭ������ form ��ۿ� ����
	
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
		
		//�����ܾ�
		moneyLabel = new LabelDAO("���� �����ܾ�", FrameVO.font18, mypageLayer, 1);
		moneyLabel.setBounds(20, 400, 120, 30);
		moneyText = new TextDAO(FrameVO.font20, mypageLayer, 1);
		moneyText.setBounds(150, 400, 160, 30);
		moneyText.setEditable(false);
		//�α��ε� ���̵��� �������� ���� ��������
		moneyList = new MoneyDAO().getMoneyList();
		int totalMoney = 0;
		if(moneyList.size()!=0) {
			totalMoney = moneyList.get(moneyList.size()-1).getTotalMoney();
		}
		moneyText.setText(""+totalMoney+"��");
		moneyText.setHorizontalAlignment(JLabel.RIGHT);
		moneyBtn = new ButtonDAO();
		moneyBtn.makeGrayButton("�����ϱ�", mypageLayer, 1);
		moneyBtn.setBounds(320, 400, 120, 30);
		
		//�����ϱ� ��ư : ���������� -> ����������
		moneyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mypageFrame.dispose();
				new LoadMoneyPage().loadMoneyFrame.setVisible(true);
			}
		});
		
		moneyDetailLabel = new LabelDAO("��������", FrameVO.font18, mypageLayer, 1);
		moneyDetailLabel.setBounds(20, 450, 150, 30);
		moneyDetailBtn = new ButtonDAO();
		moneyDetailBtn.makeGrayButton("����Ȯ��", mypageLayer, 1);
		moneyDetailBtn.setBounds(320, 450, 120, 30);
		moneyDetailArea = new JTextArea();
		moneyDetailScroll = new JScrollPane(moneyDetailArea);
		moneyDetailArea.setBounds(30, 500, 410, 120);
		moneyDetailScroll.setBounds(30, 500, 410, 120);
		moneyDetailArea.setFont(FrameVO.font18);
		moneyDetailArea.setLineWrap(true);
		mypageLayer.add(moneyDetailScroll);		
		cancelBtn = new ButtonDAO();
		cancelBtn.makeBlueButton("���ư���", mypageLayer, 1);
		cancelBtn.setBounds(30, 640, 410, 50);
		
		//���ư��� ��ư Ŭ�� : ���������� -> ����������
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mypageFrame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		});		
		
		//����Ȯ�� ��ư Ŭ�� �� �������� ����
		moneyDetailBtn.addActionListener(showDetail);
		
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
					if(click2%2 != 0) {
						removeArea2();
					}
					click2 = 0;
					
					click++;
					if(click%2==0) {
						removeArea1();
					}else {
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
					}
					break;
				case "��й�ȣ ����":
					//�⺻�������� panel ���ֱ�
					if(click%2 != 0) {
						removeArea1();
					}
					click = 0;
					
					click2++;
					if(click2%2==0) {
						removeArea2();
					}else {
						pwLabel = new LabelDAO("���ο� ��й�ȣ", FrameVO.font20, mypageLayer, 1);
						pwLabel.setBounds(20, 190, 150, 30);
						pwField = new JPasswordField();
						pwField.setBounds(180, 190, 260, 30);
						mypageLayer.add(pwField, new Integer(1));
						pwLabel2 = new LabelDAO("��й�ȣ Ȯ��", FrameVO.font20, mypageLayer, 1);
						pwLabel2.setBounds(20, 230, 150, 30);
						pwField2 = new JPasswordField();
						pwField2.setBounds(180, 230, 260, 30);
						mypageLayer.add(pwField2, new Integer(1));
						changeBtn2 = new ButtonDAO();
						changeBtn2.makeGrayButton("�����ϱ�", mypageLayer, 1);
						changeBtn2.setBounds(230, 270, 150, 30);
						changeBtn2.addActionListener(saveInfo);	
					}
					break;
			}
		}
	};	

	
	//�⺻�������� form �Ⱥ��̰� �ϱ� �޼���
	public void removeArea1() {		
		phoneLabel.setVisible(false);
		phoneText.setVisible(false);
		addressLabel.setVisible(false);
		addressBtn.setVisible(false);
		addressArea.setVisible(false);
		addressScrollPane.setVisible(false);
		changeBtn.setVisible(false);
	}
		
	//��й�ȣ���� form �Ⱥ��̰� �ϱ� �޼���
	public void removeArea2() {
		pwLabel.setVisible(false);
		pwField.setVisible(false);
		pwLabel2.setVisible(false);
		pwField2.setVisible(false);
		changeBtn2.setVisible(false);
	}
	
//�������� �޼���
ActionListener saveInfo = new ActionListener() {
		
	@Override
	public void actionPerformed(ActionEvent e) {
		
		userList = new JoinPage().getUserInfo();
		UserVO vo = userList.get(UserVO.index);
		int size = new UserDAO().getListSize();
		
		try {					
			if(e.getSource()==changeBtn) {
				//�α����� ȸ������ vo�� ���
				String phone = phoneText.getText().toString();
				String address = addressArea.getText().toString();
				if(phone.equals(vo.getPhone())&& address.equals(vo.getAddress())) {
					JOptionPane.showMessageDialog(mypageFrame, "��������� �����ϴ�", "����������", JOptionPane.INFORMATION_MESSAGE);
				}else if(!phone.equals(vo.getPhone()) || !address.equals(vo.getAddress())) {					
					vo.setPhone(phone);
					vo.setAddress(address);					
					JOptionPane.showMessageDialog(mypageFrame, "������ ����Ǿ����ϴ�", "����������", JOptionPane.PLAIN_MESSAGE);
				}
			}else if(e.getSource()==changeBtn2) {
				String password = new JoinPage().decryptPassword(pwField);
				String password2 = new JoinPage().decryptPassword(pwField2);
				if(!password.equals(password2)) {
					JOptionPane.showMessageDialog(mypageFrame, "��й�ȣ�� ��ġ���� �ʽ��ϴ�\r\n�ٽ� �õ����ּ���", "����������", JOptionPane.ERROR_MESSAGE);
					pwField.setText("");
					pwField2.setText("");
				}else {
					if(password.equals(vo.getPw())) {
						JOptionPane.showMessageDialog(mypageFrame, "��������� �����ϴ�", "����������", JOptionPane.INFORMATION_MESSAGE);
					}else {
						vo.setPw(password);
						JOptionPane.showMessageDialog(mypageFrame, "������ ����Ǿ����ϴ�", "����������", JOptionPane.PLAIN_MESSAGE);
					}
				}
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
			output.close();						
		}catch (Exception e1) {
				JOptionPane.showMessageDialog(mypageFrame, "������ �������� �ʽ��ϴ�", "����������", JOptionPane.ERROR_MESSAGE);
			}//catch	
	}//actionPerformed()
};//saveInfo()

//�������� �ҷ�����
ActionListener showDetail = new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String loginedId = new UserDAO().getInfo().getId();
		mVO.setFilePath(loginedId);
		String path = mVO.getFilePath();
		try {
			FileReader input = new FileReader(path);
			BufferedReader br = new BufferedReader(input);
			
			String line = "";
			String temp = "";
			
			while((line=br.readLine())!=null) {
				temp += line + "\r\n";
			}
			
			String[] arData = new String[10];//1�پ� ������ ����
			String[] moneylist = new String[10];//split(tab) ������ ����
			
			arData = temp.split("\r\n");
			
			moneyDetailArea.append("        ��¥\t     �����ݾ�\t      �����ݾ�\r\n");
			moneyDetailArea.append("--------------------------------------------------------\r\n");
			
			for (int i = 0; i < arData.length; i++) {
				moneylist = arData[i].split("\t");
				if(moneylist[2].equals("0")) {
					moneyDetailArea.append(" "+moneylist[3]+"\t      "+moneylist[0]+"\r\n");
				}else if(moneylist[0].equals("0")) {
					moneyDetailArea.append(" "+moneylist[3]+"\t      \t         -"+moneylist[2]+"\r\n");
				}else {
				moneyDetailArea.append(" "+moneylist[3]+"\t      "+moneylist[0]+"\t       -"+moneylist[2]+"\r\n");
				}
			}				
		} catch (Exception e1) {}
		
	}
};
}
