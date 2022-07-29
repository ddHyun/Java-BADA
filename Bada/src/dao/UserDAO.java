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
	
	//회원목록 가져오기
	public ArrayList<UserVO> getList(){
		return userList;
	}
	
	//로그인한 객체 정보 가져오기 
	public UserVO getInfo() {
		vo = userList.get(UserVO.index);
		return vo;
	}
	
	//userList 사이즈
	public int getListSize() {
		int size = userList.size();
		return size;
	}
	
	//주소찾기 버튼 이벤트 시 frame 생성 및 주소찾기
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
				titleLabelA = new LabelDAO("주소찾기", FrameVO.font40, addressLayer, 1);
				titleLabelA.makeTitleLabel();
				addressTextA = new TextDAO(FrameVO.font18, addressLayer, 1);
				addressTextA.setBounds(15, 150, 300, 30);
				addressTextA.setText("예)남촌로 15(띄어쓰기 필수)");
				new EventListenerDAO().removeText(addressTextA);
				addressBtnA = new ButtonDAO();
				addressBtnA.makeGrayButton("주소검색", addressLayer, 1);
				addressBtnA.setLocation(330, 150);		
				
				//검색결과 보여줄 area
				addressArea = new JTextArea();
				JScrollPane scrollPanel = new JScrollPane(addressArea);
				addressArea.setBounds(15, 200, 430, 300);
				scrollPanel.setBounds(15, 200, 430, 300);
				addressLayer.add(scrollPanel, new Integer(1));
				addressArea.setFont(FrameVO.font18);
				addressArea.setBackground(FrameVO.grayColor);
				addressArea.setEditable(false);
				
				//주소선택 combobox		
				zipLabel = new LabelDAO("주소를 선택하세요", FrameVO.font20, addressLayer, 1);
				zipLabel.setBounds(15, 520, 200, 30);
				JComboBox<String> numBox = new JComboBox<String>();
				numBox.setBounds(240, 520, 80, 30);
				numBox.setFont(FrameVO.font18);
				numBox.setBackground(Color.WHITE);
				addressLayer.add(numBox);
				zipBtn = new ButtonDAO();
				zipBtn.makeGrayButton("선택", addressLayer, 1);
				zipBtn.setLocation(330, 520);
				
				zipText = new TextDAO(FrameVO.font18, addressLayer, 1);
				zipText.setBounds(15, 560, 430, 30);
				zipText.setEditable(false);
				addressTextB = new TextDAO(FrameVO.font18, addressLayer, 1);
				addressTextB.setBounds(15, 600, 430, 30);
				addressTextB.setText("나머지 주소를 입력해 주세요");
				new EventListenerDAO().removeText(addressTextB);
				
				
				//주소검색 버튼 이벤트
				addressBtnA.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new JoinPage().findZipCode(addressArea, addressFrame, addressTextA, numBox, zipBtn, zipText);
					}
				});		
				
				//적용, 취소버튼
				applyBtn =new ButtonDAO();
				applyBtn.makeGrayButton("적용", addressLayer, 1);
				applyBtn.setLocation(100, 660);
				
				applyBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//검색창에 검색어를 입력하지 않았을 경우
						if(zipText.getText().equals("")) {
							JOptionPane.showMessageDialog(addressFrame, "주소를 선택해 주세요", "주소검색", JOptionPane.ERROR_MESSAGE);
						}else {
							//검색 결과 우편번호와 나머지 주소를 따로 저장(택배비용 계산을 위해 필요)
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
				cancelBtn.makeGrayButton("취소", addressLayer, 1);
				cancelBtn.setLocation(240, 660);
				new EventListenerDAO().movePage(cancelBtn, addressFrame, frame);
				
				addressFrame.setVisible(true);
				frame.setVisible(false);
				
				new ImageDAO().showTitleIcon(addressFrame);	
			}
		});
	}
	
	//직접 입력받은 주소에서 우편번호 빼니기
	public int getZip(JTextField addr1, JTextField addr2) {
		String address = addr1.getText() + " " + addr2.getText();
		String[] addr = new String[2];
		addr = address.split("\\)");
		int zip = Integer.parseInt(addr[0].substring(1));
		return zip;
	}
	
	//직접 입력받은 주소에서 우편번호 제외한 나머지 주소 가져오기
	public String getRestAddress(JTextField addr1, JTextField addr2) {
		String address1 = addr1.getText() + " " + addr2.getText();
		String[] addr = new String[2];
		addr = address1.split("\\)");
		String address = addr[1].trim();
		return address;
	}
	
	
}
