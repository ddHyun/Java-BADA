package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

import dao.ButtonDAO;
import dao.CertifSection;
import dao.EventListenerDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dao.TextDAO;
import dto.AddressVO;
import dto.FrameVO;
import dto.UserVO;

public class JoinPage {
	//회원가입 페이지
	FrameDAO joinFrame;
	PanelDAO joinLayer;	
	LabelDAO titleLabel, idLabel, pwLabel, pw2Label, nameLabel, phoneLabel, birthLabel, addressLabel, zipLabel;
	TextDAO idText, nameText, phoneText, birthText, addressText1, addressText2, zipText;
	JPasswordField pwText, pw2Text;
	ButtonDAO idCheckBtn, pwCheckBtn, phoneCheckBtn, addressFindBtn, termsBtn, agreeBtn, quitBtn, zipBtn,
						applyBtn, cancelBtn;
	
	JComboBox<String> genderBox;
	ArrayList<UserVO> userList = new ArrayList<UserVO>();
	boolean selected, selected2;
	String num; //인증번호
	
	public JoinPage() {
		//프레임&레이어
		joinFrame = new FrameDAO();
		joinLayer = new PanelDAO();
		joinLayer.makeLayer();
		joinFrame.add(joinLayer);	
		
		//상단꾸미기
		PanelDAO colorPanel = new PanelDAO();
		colorPanel.makeColorPanel(joinLayer);
		titleLabel = new LabelDAO("회원가입", FrameVO.font40, joinLayer, 1);
		//Label 내 텍스트 가운데정렬
		titleLabel.makeTitleLabel();
		
		//회원가입란
		//ID
		idLabel = new LabelDAO("아이디", FrameVO.font20, joinLayer, 1);
		idLabel.setBounds(15, 125, 460, 30);
		idText = new TextDAO(FrameVO.font18, joinLayer, 1);
		idText.setBounds(20, 160, 300, 30);
		idCheckBtn = new ButtonDAO();
		idCheckBtn.makeGrayButton("중복확인", joinLayer, 3);
		idCheckBtn.setLocation(325, 160);
		
		//PW,PW2
		pwLabel = new LabelDAO("비밀번호", FrameVO.font20, joinLayer, 1);
		pwLabel.setBounds(15, 195, 460, 30);
		pwText = new JPasswordField();
		pwText.setBounds(20, 230, 300, 30);
		pwText.setFont(FrameVO.font18);
		pwText.setEchoChar('*');
		joinLayer.add(pwText, new Integer(1));
		pw2Label = new LabelDAO("비밀번호 확인", FrameVO.font20, joinLayer, 1);
		pw2Label.setBounds(15, 265, 460, 30);
		pw2Text = new JPasswordField();
		pw2Text.setBounds(20, 300, 300, 30);
		pw2Text.setFont(FrameVO.font18);
		pw2Text.setEchoChar('*');		
		joinLayer.add(pw2Text, new Integer(1));
		pwCheckBtn = new ButtonDAO();
		pwCheckBtn.makeGrayButton("일치확인", joinLayer, 1);
		pwCheckBtn.setLocation(325, 300);
		
		//Name
		nameLabel = new LabelDAO("이름", FrameVO.font20, joinLayer, 1);
		nameLabel.setBounds(15, 335, 460, 30);
		nameText = new TextDAO(FrameVO.font18, joinLayer, 1);
		nameText.setBounds(20, 370, 300, 30);
		nameText.setEnabled(false);
		
		//Gender
		String[] arGender = {"      성별", "      남성", "      여성"};
		genderBox = new JComboBox<String>(arGender);
		genderBox.setBounds(325, 370, 115, 30);
		genderBox.setFont(FrameVO.font18);
		genderBox.setBackground(Color.WHITE);
		joinLayer.add(genderBox);
		
		//Birth
		birthLabel = new LabelDAO("생년월일", FrameVO.font20, joinLayer, 1);
		birthLabel.setBounds(15, 405, 460, 30);
		birthText =new TextDAO(FrameVO.font18, joinLayer, 1);
		birthText.setText("예) 20000101");
		new EventListenerDAO().removeText(birthText);
		birthText.setBounds(20, 440, 300, 30);
		birthText.setEnabled(false);
		genderBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(genderBox.getSelectedItem().toString().equals("      남성")||
						genderBox.getSelectedItem().toString().equals("      여성")) {
					birthText.setEnabled(true);
				}
			}
		});
		
		//Phone
		phoneLabel = new LabelDAO("휴대전화", FrameVO.font20, joinLayer, 1);
		phoneLabel.setBounds(15, 475, 460, 30);
		phoneText = new TextDAO(FrameVO.font18, joinLayer, 1);
		phoneText.setBounds(20, 510, 300, 30);
		phoneText.setEditable(false);
		phoneCheckBtn = new ButtonDAO();
		phoneCheckBtn.makeGrayButton("인증받기", joinLayer, 1);
		phoneCheckBtn.setLocation(325, 510);
		
		//Address
		addressLabel = new LabelDAO("주소", FrameVO.font20, joinLayer, 1);
		addressLabel.setBounds(15, 545, 460, 30);
		addressText1 = new TextDAO(FrameVO.font18, joinLayer, 1);
		addressText1.setBounds(20, 580, 300, 30);
		addressText1.setEditable(false);
		addressFindBtn = new ButtonDAO();
		addressFindBtn.makeGrayButton("주소찾기", joinLayer, 1);
		addressFindBtn.setLocation(325, 580);
		addressText2 = new TextDAO(FrameVO.font18, joinLayer, 1);
		addressText2.setBounds(20, 615, 300, 30);
		addressText2.setText("나머지 주소");
		new EventListenerDAO().removeText(addressText2);
		
		//약관
		JCheckBox termsBox = new JCheckBox("이용약관에 동의합니다");
		termsBox.setBounds(15, 650, 300, 30);
		termsBox.setBackground(Color.WHITE);
		joinLayer.add(termsBox, new Integer(1));
		termsBtn = new ButtonDAO();
		termsBtn.makeGrayButton("약관보기", joinLayer, 1);
		termsBtn.setLocation(325, 650);
		
		//약관checkbox 상태이벤트
		termsBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(termsBtn.isEnabled()==true) {
					System.out.println("IN!!");
					JOptionPane.showMessageDialog(joinFrame, "약관 확인 후 체크해 주세요", "회원가입", JOptionPane.ERROR_MESSAGE);
				}
				
				if(e.getStateChange()==ItemEvent.SELECTED) {
					selected2 = true;
					System.out.println("selected");
				}else {
					selected2 = false;
					System.out.println("deselected");
				}
			}
		});
		
		
		//약관보기 버튼 이벤트
		termsBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(addressText2.getText().equals("")) {
					JOptionPane.showMessageDialog(joinFrame, "나머지 주소를 입력해주세요", "회원가입", JOptionPane.ERROR_MESSAGE);
				}else {
					showTerms(joinFrame);
					termsBtn.setEnabled(false);
				}
			}
		});
		
		//가입버튼&취소하기버튼
		agreeBtn = new ButtonDAO();
		agreeBtn.makeBlueButton("가입하기", joinLayer, 1);
		agreeBtn.setBounds(20, 690, 205, 50);
		quitBtn = new ButtonDAO();
		quitBtn.makeBlueButton("취소하기", joinLayer, 1);
		quitBtn.setBounds(235, 690, 205, 50);
			
		quitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				joinFrame.dispose();
				new LoginPage().loginFrame.setVisible(true);
			}
		});
	
		//회원정보 파일저장 이벤트
		agreeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selected2) {
					if(idCheckBtn.isEnabled()==false&&pwCheckBtn.isEnabled()==false&&
							genderBox.getSelectedIndex()!=0&&phoneCheckBtn.isEnabled()==false&&
							addressFindBtn.isEnabled()==false&&termsBtn.isEnabled()==false) {
						saveInfo();
						JOptionPane.showMessageDialog(joinFrame, "★회원가입★성공★", "회원가입", JOptionPane.PLAIN_MESSAGE);
											
						joinFrame.dispose();
						new LoginPage2().login2Frame.setVisible(true);
						
					}else {
						JOptionPane.showMessageDialog(joinFrame, "입력이 안 된 곳이 있습니다.", "회원가입", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(joinFrame, "약관에 동의해주세요", "회원가입", JOptionPane.ERROR_MESSAGE);
				}
			}
		});		
		
		//아이디 중복확인 버튼 이벤트
		idCheckBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				checkID(idText.getText().toString(), joinFrame, idCheckBtn, idText);
				for (int i = 0; i < userList.size(); i++) {
					userList.get(i).showInfo();
				}
			}
		});
		
		
		//비밀번호 유효화
		
		//비밀번호 일치확인 버튼 이벤트
		pwCheckBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String pw1 = decryptPassword(pwText);
				String pw2 = decryptPassword(pw2Text);
				
				if(!pw1.equals("")) {
					if(pw1.equals(pw2)){
						JOptionPane.showMessageDialog(joinFrame, "비밀번호가 일치합니다.", "비밀번호 확인", JOptionPane.PLAIN_MESSAGE);
						pwCheckBtn.setEnabled(false);
						pwText.setEditable(false);
						pw2Text.setEditable(false);
						nameText.setEnabled(true);
					}else {
						JOptionPane.showMessageDialog(joinFrame, "비밀번호가 일치하지 않습니다.\r\n다시 확인해 주세요.", 
								"비밀번호 확인", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(joinFrame, "비밀번호를 입력해주세요", 
							"비밀번호 확인", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//휴대전화 인증 버튼 이벤트
		phoneCheckBtn.addActionListener(new ActionListener() {
			FrameDAO phoneFrame;
			PanelDAO phoneLayer, bluePanel;
			LabelDAO titleLabelP, nameLabelP, birthLabelP, dashLabelP, xLabelP;
			TextDAO nameTextP, birthTextP, birthText2P, phoneTextP, certifTextP;
			ButtonDAO certifBtn, certifBtn2, okBtn, cancelBtn;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//인증화면 Frame&Layer
				phoneFrame = new FrameDAO();
				phoneLayer = new PanelDAO();
				phoneLayer.makeLayer();
				phoneFrame.add(phoneLayer);
				//페이지제목
				bluePanel = new PanelDAO();
				bluePanel.makeColorPanel(phoneLayer);				
				titleLabelP = new LabelDAO("휴대전화 인증", FrameVO.font40, phoneLayer, 1);
				titleLabelP.makeTitleLabel();
				//이름
				nameLabelP = new LabelDAO("이름", FrameVO.font20, phoneLayer, 1);
				nameLabelP.setBounds(15, 140, 460, 30);
				nameTextP = new TextDAO(FrameVO.font18, phoneLayer, 1);
				nameTextP.setBounds(20, 175, 420, 30);
				//주민번호
				birthLabelP = new LabelDAO("주민등록번호", FrameVO.font20, phoneLayer, 1);
				birthLabelP.setBounds(15, 210, 460, 30);
				birthTextP = new TextDAO(FrameVO.font18, phoneLayer, 1);
				birthTextP.setBounds(20, 245, 200, 30);
				dashLabelP = new LabelDAO("─", FrameVO.font20, phoneLayer, 1);
				dashLabelP.setBounds(225, 245, 40, 30);
				birthText2P = new TextDAO(FrameVO.font18, phoneLayer, 1);
				birthText2P.setBounds(270, 245, 40, 30);
				xLabelP = new LabelDAO("XXXXXX", FrameVO.font20, phoneLayer, 1);
				xLabelP.setBounds(305, 245, 100, 30);
				//전화번호
				String[] serviceList = {"통신사", "SKT", "KT", "LG U+"};
				JComboBox<String> serviceBox = new JComboBox<String>(serviceList);
				serviceBox.setBounds(20, 295, 115, 30);
				serviceBox.setFont(FrameVO.font18);
				serviceBox.setBackground(Color.WHITE);
				phoneLayer.add(serviceBox, new Integer(1));
				phoneTextP = new TextDAO(FrameVO.font18, phoneLayer, 1);
				phoneTextP.setBounds(140, 295, 300, 30);
				phoneTextP.setText("예) 01012345678");
				new EventListenerDAO().removeText(phoneTextP);
				//인증번호 입력
				certifTextP = new TextDAO(FrameVO.font18, phoneLayer, 1);
				certifTextP.setBounds(20, 345, 290, 30);
				certifTextP.setText("인증번호를 입력해주세요");
				certifTextP.setEnabled(false);
				new EventListenerDAO().removeText(certifTextP);
				certifBtn = new ButtonDAO();
				certifBtn.makeGrayButton("인증번호", phoneLayer, 1);
				certifBtn.setLocation(320, 345);
				
				certifBtn2 = new ButtonDAO();
				certifBtn2.makeGrayButton("인증확인", phoneLayer, 1);
				certifBtn2.setLocation(320, 345);
				
				//필수동의 checkbox
				JCheckBox agreeBoxP = new JCheckBox();
				agreeBoxP.setText("서비스 이용을 위한 필수 항목에 모두 동의합니다.");
				agreeBoxP.setBounds(15, 380, 440, 30);
				agreeBoxP.setBackground(Color.WHITE);
				phoneLayer.add(agreeBoxP, new Integer(1));
				
				//인증버튼 누르면 인증번호 이벤트
				certifBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//인증번호 생성, 인증하기
						new CertifSection(phoneFrame, certifTextP, certifBtn, certifBtn2);
					}
				});				
				
				//확인버튼
				okBtn = new ButtonDAO();
				okBtn.makeGrayButton("확인", phoneLayer, 1);
				okBtn.setLocation(100, 425);
				okBtn.setFont(FrameVO.font20);
				
				
				//페이지 내 textfield 입력 및 버튼 클릭 후 '확인'버튼 활성화시키기
				agreeBoxP.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange()==ItemEvent.SELECTED) {
							selected = true;
						}else {
							selected = false;
						}
					}
				});
				
				//확인버튼 클릭시 JoinPage로 전환이벤트
				okBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(selected) {
							if(!(nameTextP.getText().equals("")&&birthTextP.getText().equals("")&&birthText2P.getText().equals("")&&
									serviceBox.getSelectedItem().toString().equals("통신사")&&phoneTextP.getText().equals("예) 01012345678")
									&&certifTextP.getText().equals("인증번호를 입력해주세요"))) {
								phoneFrame.dispose();
								joinFrame.setVisible(true);
								phoneCheckBtn.setEnabled(false);
								phoneText.setText(phoneTextP.getText());
							}else {
								JOptionPane.showMessageDialog(phoneFrame, "입력이 안 된 곳이 있습니다", "인증확인", JOptionPane.ERROR_MESSAGE);
							}
						}else {
							System.out.println(selected);
							JOptionPane.showMessageDialog(phoneFrame, "서비스 이용 동의에 체크해 주세요", "인증확인", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				
				//취소버튼
				cancelBtn = new ButtonDAO();
				cancelBtn.makeGrayButton("취소", phoneLayer, 1);
				cancelBtn.setLocation(240, 425);
				cancelBtn.setFont(FrameVO.font20);
				
				//취소버튼 클릭시 인증페이지 없어짐 이벤트
				new EventListenerDAO().movePage(cancelBtn, phoneFrame, joinFrame);
				
				//아이콘이미지
				new ImageDAO().showTitleIcon(phoneFrame);
				
				joinFrame.setVisible(false);
				phoneFrame.setVisible(true);
				
			}
		});
		//주소찾기 버튼 이벤트
		addressFindBtn.addActionListener(new ActionListener() {
			FrameDAO addressFrame;
			PanelDAO addressLayer, bgPanelA;
			LabelDAO titleLabelA;
			TextDAO addressTextA;
			ButtonDAO addressBtnA;
			JTextArea addressArea;
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
				addressTextA.setBounds(15, 200, 300, 30);
				addressTextA.setText("예)남촌로 15(띄어쓰기 필수)");
				new EventListenerDAO().removeText(addressTextA);
				addressBtnA = new ButtonDAO();
				addressBtnA.makeGrayButton("주소검색", addressLayer, 1);
				addressBtnA.setLocation(330, 200);		
				
				//검색결과 보여줄 area
				addressArea = new JTextArea();
				JScrollPane scrollPanel = new JScrollPane(addressArea);
				addressArea.setBounds(15, 250, 430, 300);
				scrollPanel.setBounds(15, 250, 430, 300);
				addressLayer.add(scrollPanel, new Integer(1));
				addressArea.setFont(FrameVO.font18);
				addressArea.setBackground(FrameVO.grayColor);
				addressArea.setEditable(false);
				
				//주소선택 combobox		
				zipLabel = new LabelDAO("주소를 선택하세요", FrameVO.font20, addressLayer, 1);
				zipLabel.setBounds(15, 560, 200, 30);
				JComboBox<String> numBox = new JComboBox<String>();
				numBox.setBounds(240, 560, 80, 30);
				numBox.setFont(FrameVO.font18);
				numBox.setBackground(Color.WHITE);
				addressLayer.add(numBox);
				zipBtn = new ButtonDAO();
				zipBtn.makeGrayButton("선택", addressLayer, 1);
				zipBtn.setLocation(330, 560);
				
				zipText = new TextDAO(FrameVO.font18, addressLayer, 1);
				zipText.setBounds(15, 600, 430, 30);
				zipText.setEditable(false);
				
				//주소검색 버튼 이벤트
				addressBtnA.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						findZipCode(addressArea, addressFrame, addressTextA, numBox, zipBtn, zipText);
					}
				});		
				
				//적용, 취소버튼
				applyBtn =new ButtonDAO();
				applyBtn.makeGrayButton("적용", addressLayer, 1);
				applyBtn.setLocation(100, 660);
				
				applyBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(zipText.getText().equals("")) {
							JOptionPane.showMessageDialog(addressFrame, "주소를 선택해 주세요", "주소검색", JOptionPane.ERROR_MESSAGE);
						}else {
							addressText1.setText(zipText.getText());
							addressFindBtn.setEnabled(false);
							addressFrame.dispose();
							joinFrame.setVisible(true);
						}
					}
				});
				cancelBtn = new ButtonDAO();
				cancelBtn.makeGrayButton("취소", addressLayer, 1);
				cancelBtn.setLocation(240, 660);
				new EventListenerDAO().movePage(cancelBtn, addressFrame, joinFrame);
				
				
				joinFrame.setVisible(false);
				addressFrame.setVisible(true);
				
				new ImageDAO().showTitleIcon(addressFrame);
			}
		});
		
				
		//아이콘이미지
		new ImageDAO().showTitleIcon(joinFrame);
	}
		
	
	//비밀번호 *** 풀기 메서드
	public String decryptPassword(JPasswordField pwfield) {		
		String pw = "";
		char[] secretPw = pwfield.getPassword();
		for(char cha : secretPw) {
			Character.toString(cha);
			pw += cha;
		}
		return pw;		
	}		
	
	//주소찾기 메서드
	public void findZipCode(JTextArea textarea, JFrame frame, JTextField textfield, JComboBox<String> combobox, 
											JButton btn, JTextField resultfield) {
				
		ArrayList<AddressVO> addressList = new ArrayList<AddressVO>();		
		String zipCode = "D:/BADA/ZipCode/zipcode.txt";	
		
		try {
			//외부파일에서 우편번호 파일 불러오기
			FileReader input = new FileReader(new File(zipCode));
			BufferedReader br = new BufferedReader(input);
			
			//한줄씩 읽어 temp에 파일내용 저장
			String line = "";
			String temp = "";
			try {
				while((line=br.readLine())!= null){
					temp += line + "\r\n";
				}
			
			//temp데이터를 개행기준으로 끊어 배열에 저장, tab기준으로 끊어 또다른 배열에 저장
			String[] arAdd = new String[5000];
			String[] addrList = new String[5000];
			arAdd = temp.split("\n");				
			
			for (int i = 0; i < arAdd.length; i++) {
				//AddressVO객체 생성 후 변수에 저장
				AddressVO vo = new AddressVO();
				addrList = arAdd[i].split("\t");
				vo.setZipcode(addrList[0]);
				vo.setSi(addrList[1]);
				vo.setGu(addrList[2]);
				vo.setRo(addrList[3]);
				vo.setBun1(addrList[4]);
				vo.setBun2(addrList[5]);
				//ArrayList에 객체형태로 저장
				addressList.add(vo);				
			}		
			
			//text에 입력값이 없을 때
			if(textfield.getText().equals("")|| textfield.getText().equals("예)남촌로 15(띄어쓰기 필수)")) {
				JOptionPane.showMessageDialog(frame, "검색할 주소를 입력해 주세요", 
						"주소검색", JOptionPane.ERROR_MESSAGE);
			}else {			
					//text에 들어온 keyword로 주소 검색
				String keyword1, keyword2;
				String[] keywords = new String[2];
				keywords= textfield.getText().trim().toString().split(" ");
				keyword1 = keywords[0];
				keyword2 = keywords[1];				
			
			//일치값이 있을 때
			int seq = 1;
			ArrayList<AddressVO> arResult = new ArrayList<AddressVO>();
			for (int i = 0; i < addressList.size(); i++) {
				AddressVO av = addressList.get(i);
				if(av.getRo().contains(keyword1)&& av.getBun1().contains(keyword2)) {
					AddressVO vo = new AddressVO();
					vo.setZipcode(av.getZipcode());
					vo.setSi(av.getSi());
					vo.setGu(av.getGu());
					vo.setRo(av.getRo());
					vo.setBun1(av.getBun1());
					vo.setBun2(av.getBun2());
					
					arResult.add(vo);
					
					String temp1 = seq + "  (" + av.getZipcode() +") " + av.getSi() +" "+ av.getGu() +" "+av.getRo() +" "+ 
											av.getBun1() + 	" "+ av.getBun2();					
					textarea.append(temp1);		
					textarea.append("\r\n");
					seq++;
					
					//일치값이 없을 때
					if(temp1.equals("")) {
						JOptionPane.showMessageDialog(frame, "검색결과가 없습니다. 다시 입력해 주세요.", 
								"주소검색", JOptionPane.ERROR_MESSAGE);								
					}					
				}
			}
			seq--;
			//결과 중 원하는 주소 선택 
			String[] resultNum = new String[seq+1];
			for (int i = 1; i < seq+1; i++) {
				resultNum[i] ="" + i;				
				combobox.addItem("    "+resultNum[i]);
			}
			
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					AddressVO vo = arResult.get(combobox.getSelectedIndex());
					String zipResult = "("+vo.getZipcode()+") "+vo.getSi()+" "+vo.getGu()+" "
					+vo.getRo()+" "+vo.getBun1()+" "+vo.getBun2();
					resultfield.setText(zipResult);					
				}
			});			
			}
			input.close();
			br.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(frame, "검색된 주소가 없습니다.\r\n다시 시도해 주세요",
						"주소검색", JOptionPane.ERROR_MESSAGE);
			}
		} catch (FileNotFoundException e) {			
			JOptionPane.showMessageDialog(frame, "파일이 존재하지 않습니다", "주소검색", JOptionPane.ERROR_MESSAGE);
		}
	}	
	
	//회원정보 저장 메서드
	public void saveInfo() {
		//회원정보 저장 폴더 및 파일 생성
		File folder = new File(new UserVO().getUserPath());
		if(!(folder.exists())) {
			folder.mkdirs();
			}
			try {
				File file = new File(new UserVO().getUserPath()+new UserVO().getUserPath2());
				file.createNewFile();
				//textField 정보를 UserVO, ArrayList로 저장, 공백 제거(trim())		
				UserVO user, temp;
				
				//비밀번호 복호화
				String pw = "";
				char[] secretPw = pwText.getPassword();
				for(char cha : secretPw) {
					Character.toString(cha);
					pw += cha;
				}
				
				temp = new UserVO();
				temp.setId(idText.getText().toString().trim());						
				temp.setPw(pw.trim());
				temp.setName(nameText.getText().toString().trim());
				temp.setGender(genderBox.getSelectedItem().toString().trim());
				temp.setBirth(birthText.getText().toString().trim());
				temp.setPhone(phoneText.getText().toString().trim());
				String address = addressText1.getText().toString().trim()+ " "+addressText2.getText().toString();
				temp.setAddress(address);
				
				user = new UserVO(temp.getNumber(), temp.getId(), temp.getPw(), temp.getName(), 
						temp.getGender(), temp.getBirth(), temp.getPhone(), temp.getAddress());
				
				//외부 파일에 저장
				FileWriter output = new FileWriter(file, true);
				
				output.write(user.getNumber() + "\t");
				output.write(user.getId().toString() + "\t");
				output.write(user.getPw() + "\t");
				output.write(user.getName() + "\t");
				output.write(user.getGender() + "\t");
				output.write(user.getBirth() + "\t");
				output.write(user.getPhone() + "\t");
				output.write(user.getAddress()+"\r\n");
				output.close();
				
			} catch (Exception e1) {
				System.out.println("Error");					
		}
	}
	
	
	//회원정보 불러오기
	public ArrayList<UserVO> getUserInfo() {
		try {
			FileReader input = new FileReader(new File(new UserVO().getUserPath()+new UserVO().getUserPath2()));
			BufferedReader br = new BufferedReader(input);
			
			String line = "";
			String temp = "";
			while((line=br.readLine())!= null) {
				temp += line + "\r\n";
			}
			
			String[] list = new String[100];
			String[] arList = new String[100];
			
			list = temp.split("\r\n");
			
			for (int i = 0; i < list.length; i++) {
				UserVO vo = new UserVO();
				arList = list[i].split("\t");
				vo.setId(arList[1]);
				vo.setPw(arList[2]);
				vo.setName(arList[3]);
				vo.setGender(arList[4]);
				vo.setBirth(arList[5]);
				vo.setPhone(arList[6]);
				vo.setAddress(arList[7]);
				userList.add(vo);
			}			
			br.close();
			input.close();
		} catch (Exception e) {}	
		return userList;
	}
	
	
	
	//아이디 중복체크 메서드
	public void checkID(String id, JFrame frame, JButton btn, JTextField text) {
		userList = getUserInfo();
		int cnt = 0;
		for (int i = 0; i < userList.size(); i++) {				
			if(userList.get(i).getId().equals(id)) {
				cnt++;
			}
		}
		if(id.equals("")) {
			JOptionPane.showMessageDialog(frame, "아이디를 입력해주세요", "회원가입", JOptionPane.ERROR_MESSAGE);
		}else {
			if(cnt==0) {
				int choice = JOptionPane.showOptionDialog(frame, "사용가능한 아이디입니다.\r\n사용하시겠습니까?", "회원가입", 
						0, JOptionPane.PLAIN_MESSAGE, null, null, null);
				if(choice==0) {
					btn.setEnabled(false);
					text.setEditable(false);
				}
			}else if(cnt>=1) {
				JOptionPane.showMessageDialog(frame, "중복된 아이디입니다. 다시 시도해 주세요", "회원가입", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//약관보기 메서드
	public void showTerms(JFrame frame) {
		String termsMsg = "제1조 (목적)\r\n" + 
				"이 약관은 택배사업자와 고객(송화인) 간의 공정한 택배거래를 위하여\r\n그 계약조건을 정함을 목적으로 합니다.\r\n"+
				"제2조 (적용법규 등)\r\n" + 
				"이 약관에 규정되지 않은 사항에 대하여는 화물자동차운수사업법, 상법 등의\r\n법규와 공정한 일반관습에 따릅니다.\r\n"+
				"제3조 (용어의 정의)\r\n" + 
				" - 행정동 : 우편번호상의 동\r\n"+
				"제4조 (운송장)\r\n" + 
				"사업자는 계약을 체결하는 때에 다음 각 호의 사항을 기재한 운송장을\r\n마련하여 고객(송화인)에게 교부합니다.\r\n" + 
				" - 사업자의 상호, 대표자명, 주소 및 전화번호, 담당자 이름, 운송장 번호\r\n"+
				"제5조 (운임의 청구)\r\n" + 
				" - 운임 및 할증요금 -\r\n"+
				"같은 구 안에서만 배송 가능합니다.\r\n"+
				"기본운임 : 출발행정동과 도착행정동이 같은 경우\r\n                     2000원 (예)21797->21797)\r\n"+ 
				"추가운임 : 출발행정동과 도착행정동이 다른 경우\r\n                     500원씩 추가 (행정동이 늘어날 때마다 부과)\r\n"+
				" - 무게와 크기에 따라 각 항목 당 최대 1000원의 추가금이 부여됩니다.\r\n";
		JOptionPane.showMessageDialog(frame, termsMsg, "회원가입", JOptionPane.PLAIN_MESSAGE);
	}
}
