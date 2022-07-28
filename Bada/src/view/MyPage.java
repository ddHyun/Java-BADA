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
		titleLabel = new LabelDAO("마이페이지", FrameVO.font40, mypageLayer, 1);
		titleLabel.makeTitleLabel();
		
		//회원정보 수정
		nameLabel = new LabelDAO(name+"님", FrameVO.font18, mypageLayer, 1);
		nameLabel.setBounds(20, 140, 80, 30);
		infoBtn = new ButtonDAO();
		infoBtn.makeGrayButton("기본정보 변경", mypageLayer, 1);
		infoBtn.setBounds(110, 140, 160, 30);
		pwBtn = new ButtonDAO();
		pwBtn.makeGrayButton("비밀번호 변경", mypageLayer, 1);
		pwBtn.setBounds(280, 140, 160, 30);
		
		infoBtn.addActionListener(changeInfo);
		pwBtn.addActionListener(changeInfo);
		
		
		//아이콘이미지
		new ImageDAO().showTitleIcon(mypageFrame);
		mypageFrame.setVisible(true);
	}
	
	//기본정보 변경
	ActionListener changeInfo = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
				case "기본정보 변경":
					phoneLabel = new LabelDAO("휴대전화", FrameVO.font20, mypageLayer, 1);
					phoneLabel.setBounds(20, 190, 100, 30);
					phoneText = new TextDAO(FrameVO.font18, mypageLayer, 1);
					phoneText.setBounds(130, 190, 250, 30);
//					UserVO vo = new MainPage().getLoginUser();
					
//					String phone = new MainPage().getLoginUser().getPhone();
//					System.out.println(new MainPage().getLoginUser().getPhone());
//					phoneText.setText(phone);
					changeBtn = new ButtonDAO();
					changeBtn.makeGrayButton("변경", mypageLayer, 1);
					changeBtn.setBounds(380, 190, 60, 30);
					addressLabel = new LabelDAO("주소", FrameVO.font20, mypageLayer, 1);
					addressLabel.setBounds(20, 230, 100, 30);
					addressText = new TextDAO(FrameVO.font18, mypageLayer, 1);
					addressText.setBounds(130, 230, 250, 30);
					break;
				case "비밀번호 변경":
					break;
			}
		}
	};
	

}
