package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.ButtonDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dao.UserDAO;
import dto.FrameVO;
import dto.ReceiverVO;
import dto.StuffVO;
import dto.UserVO;

public class OrderPage3 {

	//�ֹ����� Ȯ��������
	
	FrameDAO order3Frame;
	PanelDAO order3Layer;
	LabelDAO titleLabel, senderNameLabel;
	ButtonDAO cancelBtn, nextBtn;
	JTextArea orderArea;
	JScrollPane orderScrollPane;
	StuffVO sVO = StuffVO.getInstance();
	ReceiverVO rVO = ReceiverVO.getInstance();
	ArrayList<UserVO> userList = new ArrayList<UserVO>();
	
	public OrderPage3() {
		order3Frame = new FrameDAO();
		order3Frame.makeFrame();
		order3Layer = new PanelDAO();
		order3Layer.makeLayer();
		order3Frame.add(order3Layer);
		
		new PanelDAO().makeColorPanel(order3Layer);
		titleLabel = new LabelDAO("�ֹ�Ȯ��", FrameVO.font40, order3Layer, 1);
		titleLabel.makeTitleLabel();
		
		//�ֹ����� Ȯ��		
		orderArea = new JTextArea();
		orderScrollPane = new JScrollPane(orderArea);
		orderArea.setBounds(20, 140, 420, 480);
		orderScrollPane.setBounds(20, 140, 420, 480);
		orderArea.setFont(FrameVO.font18);
		orderArea.setLineWrap(true);
		order3Layer.add(orderScrollPane);
		orderArea.setEditable(false);
		
		//�ֹ��� ���� textArea�� ���
		UserVO uVO = new UserDAO().getInfo();
		orderArea.append("\r\n");
		orderArea.append("[�ֹ���]\r\n");
		orderArea.append(" �̸� : "+uVO.getName()+"\r\n");
		orderArea.append(" ��ȭ : "+uVO.getPhone()+"\r\n");
		orderArea.append(" �ּ� : "+uVO.getAddress()+"\r\n");
		//������ ���� textArea�� ���
		orderArea.append("\r\n");
		orderArea.append("[������]\r\n");
		orderArea.append(" �̸� : "+rVO.getName()+"\r\n");
		orderArea.append(" ��ȭ : "+rVO.getPhone()+"\r\n");
		orderArea.append(" �ּ� : "+rVO.getAddress()+"\r\n");
		orderArea.append("\r\n");
		//��ǰ ���� textArea�� ���
		orderArea.append("[�ֹ���ǰ]\r\n");
		orderArea.append(" ���� : "+sVO.getWeight()+"kg / ũ�� : "+sVO.getSize()+"cm\r\n");
		orderArea.append(" ��ǰ : "+sVO.getCode()+"/ "+sVO.getStuff()+"\r\n");
		orderArea.append(" �ڽ� : "+sVO.getBox()+"��\r\n");
		orderArea.append(" ��۽� ���ǻ��� : "+sVO.getNote());
		
		//���, ������ư
		cancelBtn = new ButtonDAO();
		cancelBtn.makeGrayButton("����ϱ�", order3Layer, 1);
		cancelBtn.setBounds(20, 640, 200, 50);
		cancelBtn.setFont(FrameVO.font30);
		
		nextBtn = new ButtonDAO();
		nextBtn.makeBlueButton("�����ܰ� >>", order3Layer, 1);
		nextBtn.setBounds(240, 640, 200, 50);
		
		//��ҹ�ư : �ֹ�3 -> ���������� �̵�
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				order3Frame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		});	
		
		//�����ܰ��ư : �ֹ�3 -> ���������� �̵�
		nextBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				order3Frame.dispose();
				new PayPage().payFrame.setVisible(true);
				
			}
		});
		
		//�������̹���
		new ImageDAO().showTitleIcon(order3Frame);
		order3Frame.setVisible(true);
		
	}		
}
