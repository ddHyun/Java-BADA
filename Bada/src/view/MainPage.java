package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import dao.ButtonDAO;
import dao.EventListenerDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dao.UserDAO;
import dto.FrameVO;
import dto.UserVO;

public class MainPage {

	//로그인 후 보여지는 메인페이지
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
		
		//타이틀
		new LabelDAO().getTitle(mainLayer);
		new PanelDAO().makeColorPanel(mainLayer);
		
		String name = new UserDAO().getInfo().getName();
		welcomeLabel = new LabelDAO(name+"님 환영합니다!",	FrameVO.font20, mainLayer, 1);
		welcomeLabel.setBounds(20, 130, 300, 30);
		
		//로그아웃버튼
		logoutBtn = new ButtonDAO();
		logoutBtn.makeGrayButton("로그아웃", mainLayer, 1);
		logoutBtn.setBounds(300, 130, 140, 30);
		
		//로그아웃 버튼 클릭 시 로그인화면으로 이동
		new EventListenerDAO().movePage(logoutBtn, mainFrame, new LoginPage2().login2Frame);
		
		//menu1 마이페이지		
		new ImageDAO().showImage(imgPath1, 20, 190, 200, 240, mainFrame, mainLayer);		
		menuBtn1 = new ButtonDAO();
		menuBtn1.makeGrayButton("마이페이지", mainLayer, 1);
		menuBtn1.setBounds(20, 400, 200, 40);
		
		//menu2 이용안내
		//사이즈, 가격
		new ImageDAO().showImage(imgPath2, 240, 190, 200, 240, mainFrame, mainLayer);
		menuBtn2 = new ButtonDAO();
		menuBtn2.makeGrayButton("이용안내", mainLayer, 1);
		menuBtn2.setBounds(240, 400, 200, 40);		
		
		//menu3 택배주문신청
		new ImageDAO().showImage(imgPath3, 20, 460, 200, 240, mainFrame, mainLayer);		
		menuBtn3 = new ButtonDAO();
		menuBtn3.makeGrayButton("택배주문 신청", mainLayer, 2);
		menuBtn3.setBounds(20, 660, 200, 40);		
		
		//menu4 주문배송 조회
		new ImageDAO().showImage(imgPath4, 240, 460, 200, 240, mainFrame, mainLayer);
		menuBtn4 = new ButtonDAO();
		menuBtn4.makeGrayButton("주문배송 조회", mainLayer, 2);
		menuBtn4.setBounds(240, 660, 200, 40);
		
		//페이지 이동 클릭이벤트
		menuBtn1.addActionListener(movePage);
		menuBtn2.addActionListener(movePage);
		menuBtn3.addActionListener(movePage);
		menuBtn4.addActionListener(movePage);
		
		//아이콘이미지
		new ImageDAO().showTitleIcon(mainFrame);
//		mainFrame.setVisible(true);
	}	
	
	//버튼 클릭 시 페이지 이동
	ActionListener movePage = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			switch(e.getActionCommand()) {
			case "마이페이지":
				mainFrame.dispose();
				new MyPage().mypageFrame.setVisible(true);
				break;
			case "이용안내":
				break;
			case "택배주문 신청":
				mainFrame.dispose();
				new OrderPage().orderFrame.setVisible(true);
				break;
			case "주문배송 조회":
				break;
			}
		}
	};
		
}
