package view;

import java.util.ArrayList;

import dao.ButtonDAO;
import dao.EventListenerDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dto.FrameVO;
import dto.UserVO;

public class MainPage {

	//�α��� �� �������� ����������
	FrameDAO mainFrame;
	PanelDAO mainLayer;
	LabelDAO titleLabel, welcomeLabel, menuLabel1, menuLabel2, menuLabel3, menuLabel4;
	ButtonDAO menuBtn1, menuBtn2, menuBtn3, menuBtn4, logoutBtn;
	ArrayList<UserVO> userList = new ArrayList<UserVO>();
	String imgPath1 = "image/mypage.png";
	String imgPath2 = "image/info.png";
	String imgPath3 = "image/order.png";
	String imgPath4 = "image/tracking.png";
	
	public MainPage() {
		mainFrame = new FrameDAO();
		mainFrame.makeFrame();
		mainLayer = new PanelDAO();
		mainLayer.makeLayer();
		mainFrame.add(mainLayer);
		
		//Ÿ��Ʋ
		new LabelDAO().getTitle(mainLayer);
		new PanelDAO().makeColorPanel(mainLayer);
		
		welcomeLabel = new LabelDAO(getIdxName()+"�� ȯ���մϴ�!",	FrameVO.font20, mainLayer, 1);
		welcomeLabel.setBounds(20, 130, 300, 30);
		
		//�α׾ƿ���ư
		logoutBtn = new ButtonDAO();
		logoutBtn.makeGrayButton("�α׾ƿ�", mainLayer, 1);
		logoutBtn.setBounds(300, 130, 140, 30);
		
		//�α׾ƿ� ��ư Ŭ�� �� �α���ȭ������ �̵�
		new EventListenerDAO().movePage(logoutBtn, mainFrame, new LoginPage2().login2Frame);
		
		//menu1 ����������		
		new ImageDAO().showImage(imgPath1, 20, 190, 200, 240, mainFrame, mainLayer);		
		menuBtn1 = new ButtonDAO();
		menuBtn1.makeGrayButton("����������", mainLayer, 1);
		menuBtn1.setBounds(20, 400, 200, 40);
		
		//menu2 �̿�ȳ�
		//������, ����
		new ImageDAO().showImage(imgPath2, 240, 190, 200, 240, mainFrame, mainLayer);
		menuBtn2 = new ButtonDAO();
		menuBtn2.makeGrayButton("�̿�ȳ�", mainLayer, 1);
		menuBtn2.setBounds(240, 400, 200, 40);
		
		
		//menu3 �ù��ֹ���û
		new ImageDAO().showImage(imgPath3, 20, 460, 200, 240, mainFrame, mainLayer);		
		menuBtn3 = new ButtonDAO();
		menuBtn3.makeGrayButton("�ù��ֹ� ��û", mainLayer, 2);
		menuBtn3.setBounds(20, 660, 200, 40);
		//�ù��ֹ���û �������� �̵�
		new EventListenerDAO().movePage(menuBtn3, mainFrame, new OrderPage().orderFrame);
		
		//menu4 �ֹ���� ��ȸ
		new ImageDAO().showImage(imgPath4, 240, 460, 200, 240, mainFrame, mainLayer);
		menuBtn4 = new ButtonDAO();
		menuBtn4.makeGrayButton("�ֹ���� ��ȸ", mainLayer, 2);
		menuBtn4.setBounds(240, 660, 200, 40);
		
		//�������̹���
		new ImageDAO().showTitleIcon(mainFrame);
		mainFrame.setVisible(true);
	}
	
	//�α����� ȸ�� �̸� �������� �޼���
	public String getIdxName() {
		userList = new JoinPage().getUserInfo();
		UserVO vo = userList.get(UserVO.index);
		String name = vo.getName();
		
		return name;
	}
		
}
