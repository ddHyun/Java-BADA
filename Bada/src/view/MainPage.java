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
		
		welcomeLabel = new LabelDAO(getIdxName()+"님 환영합니다!",	FrameVO.font20, mainLayer, 1);
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
		//택배주문신청 페이지로 이동
		new EventListenerDAO().movePage(menuBtn3, mainFrame, new OrderPage().orderFrame);
		
		//menu4 주문배송 조회
		new ImageDAO().showImage(imgPath4, 240, 460, 200, 240, mainFrame, mainLayer);
		menuBtn4 = new ButtonDAO();
		menuBtn4.makeGrayButton("주문배송 조회", mainLayer, 2);
		menuBtn4.setBounds(240, 660, 200, 40);
		
		//아이콘이미지
		new ImageDAO().showTitleIcon(mainFrame);
		mainFrame.setVisible(true);
	}
	
	//로그인한 회원 이름 가져오기 메서드
	public String getIdxName() {
		userList = new JoinPage().getUserInfo();
		UserVO vo = userList.get(UserVO.index);
		String name = vo.getName();
		
		return name;
	}
		
}
