package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.ButtonDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dao.OrderDAO;
import dao.UserDAO;
import dto.FrameVO;
import dto.MoneyVO;
import dto.ReceiverVO;
import dto.StuffVO;
import dto.UserVO;

public class PayPage {
	
	FrameDAO payFrame;
	PanelDAO payLayer;
	ButtonDAO loadMoneyBtn, payBtn, cancelBtn;
	LabelDAO titleLabel, basicChargeLabel, boxNumLabel, distanceLabel1, distanceLabel2, lineLabel,
				totalChargeLabel, loadMoneyLabel;
	MoneyVO mVO = MoneyVO.getInstance();
	StuffVO sVO = StuffVO.getInstance();
	ReceiverVO rVO = ReceiverVO.getInstance();
	ArrayList<UserVO> userList = new ArrayList<>();
	int basicCharge, boxNum, distance, totalCharge, loadMoney = 0;
	boolean check = false;
	int chance = 3; //비밀번호 오류 체크용 변수
	String trackingNum = "";
	
	public PayPage() {
		payFrame = new FrameDAO();
		payFrame.makeFrame();
		payLayer = new PanelDAO();
		payLayer.makeLayer();
		payFrame.add(payLayer);
		
		new PanelDAO().makeColorPanel(payLayer);
		titleLabel = new LabelDAO("결제하기", FrameVO.font40, payLayer, 1);
		titleLabel.makeTitleLabel();
		
		calCharge();
		
		basicChargeLabel = new LabelDAO("기본요금                                         "+basicCharge, FrameVO.font20, payLayer, 1);
		basicChargeLabel.setBounds(20, 250, 400, 30);
		basicChargeLabel.setHorizontalAlignment(JLabel.TRAILING);
		boxNumLabel = new LabelDAO("박스개수                               x          "+boxNum, FrameVO.font20, payLayer, 1);
		boxNumLabel.setBounds(20, 300, 400, 30);
		boxNumLabel.setHorizontalAlignment(JLabel.TRAILING);
		distanceLabel1 = new LabelDAO("거리                                     + ", FrameVO.font20, payLayer, 1);
		distanceLabel1.setBounds(65, 350, 290, 30);
		distanceLabel2 = new LabelDAO(""+distance, FrameVO.font20, payLayer, 1);
		distanceLabel2.setBounds(310, 350, 110, 30);
		distanceLabel2.setHorizontalAlignment(JLabel.TRAILING);
		lineLabel = new LabelDAO("----------------------------------------------------", FrameVO.font20, payLayer, 1);
		lineLabel.setBounds(20, 400, 400, 10);
		lineLabel.setHorizontalAlignment(JLabel.TRAILING);
		totalChargeLabel = new LabelDAO("총 결제금액   "+totalCharge+"원", FrameVO.font25B, payLayer, 1);
		totalChargeLabel.setBounds(20, 430, 400, 30);
		//JLabel내 text 색상 변경
		totalChargeLabel.setForeground(Color.RED);
		totalChargeLabel.setHorizontalAlignment(JLabel.TRAILING);
		
		//충전금액
		
		//내 충전금액 
		loadMoney = MoneyVO.getInstance().getTotalMoney();
		loadMoneyLabel = new LabelDAO("나의 충전잔액   "+loadMoney+"원", FrameVO.font20, payLayer, 1);
		loadMoneyLabel.setBounds(20, 590, 400, 30);
		loadMoneyLabel.setHorizontalAlignment(JLabel.TRAILING);
		loadMoneyLabel.setForeground(Color.BLUE);
		
		//취소, 결제버튼
		cancelBtn = new ButtonDAO();
		cancelBtn.makeGrayButton("취소하기", payLayer, 1);
		cancelBtn.setBounds(20, 640, 200, 50);
		cancelBtn.setFont(FrameVO.font30);
		
		///취소버튼 : 결제 -> 메인페이지 이동
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				payFrame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		});
		
		payBtn = new ButtonDAO();
		payBtn.makeBlueButton("결제하기", payLayer, 1);
		payBtn.setBounds(240, 640, 200, 50);
		//결제금액보다 충전금액이 적거나 충전금액이 0인 경우 '충전하기'버튼 보여지기
		if(totalCharge > loadMoney || loadMoney == 0) {
			payBtn.setVisible(false);
			loadMoneyBtn = new ButtonDAO();
			loadMoneyBtn.makeBlueButton("충전하기", payLayer, 1);
			loadMoneyBtn.setBounds(240, 640, 200, 50);
			
			//충전버튼 클릭 : 결제 -> 충전페이지 이동
			loadMoneyBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {					
					payFrame.setVisible(false);
					new LoadMoneyPage().loadMoneyFrame.setVisible(true);
				}
			});
		}else {//결제하기버튼 클릭시 
			payBtn.setVisible(true);
			payBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					checkPwd();	
					//송장번호 부여 후 택배신청내역 외부파일에 저장
					trackingNum = new OrderDAO().trackingNumber();
					saveDeliverInfo();
				}
			});
		}
		
		//아이콘이미지
		new ImageDAO().showTitleIcon(payFrame);
		payFrame.setVisible(true);

	}
	
	//결제금액 계산메서드
	public void calCharge() {		
		int dis = new OrderPage().senderZip - new OrderPage().receiverZip;
		int boxSize = (int)StuffVO.getInstance().getSize();
		int boxWeight = (int)StuffVO.getInstance().getWeight();
		int[] charge = {2500, 3000, 4000, 5500, 7000};
		
		//박스갯수
		boxNum = StuffVO.getInstance().getBox();
		//거리계산
		if(dis<0) {
			distance = -dis * 50;
		}else {
			distance = dis * 50;
		}
		//기본요금 계산
		if(boxSize <= 60) {
			if(0 <= boxWeight && boxWeight <= 2) {
				basicCharge = charge[0];
			}else if(2 < boxWeight && boxWeight <= 5) {
				basicCharge = charge[1];
			}else if(5 < boxWeight && boxWeight <= 10) {
				basicCharge = charge[2];
			}else if(10 < boxWeight && boxWeight <= 20) {
				basicCharge = charge[3];
			}else {
				basicCharge = charge[4];
			}
		}else if(boxSize <= 80) {
			if(0 <= boxWeight && boxWeight <= 5) {
				basicCharge = charge[1];
			}else if(5 < boxWeight && boxWeight <= 10) {
				basicCharge = charge[2];
			}else if(10 < boxWeight && boxWeight <= 20) {
				basicCharge = charge[3];
			}else {
				basicCharge = charge[4];
			}
		}else if(boxSize <= 100) {
			if(0 <= boxWeight && boxWeight <= 10) {
				basicCharge = charge[2];
			}else if(10 < boxWeight && boxWeight <= 20) {
				basicCharge = charge[3];
			}else {
				basicCharge = charge[4];
			}
		}else if(boxSize <= 120) {
			if(0 <= boxWeight && boxWeight <= 20) {
				basicCharge = charge[3];
			}else {
				basicCharge = charge[4];
			}
		}else {
			basicCharge = charge[4];
		}
		
		//총결제금액
		totalCharge = (basicCharge * boxNum) + distance;			
	}
	
	//비밀번호 체크 메서드
	public void checkPwd() {
		while(true) {
			String input = JOptionPane.showInputDialog(payFrame, 
					"비밀번호를 입력하세요(기회 "+chance+"번)", "결제하기", JOptionPane.DEFAULT_OPTION);
			userList = new JoinPage().getUserInfo();
			UserVO vo = new UserVO();
			String pwd = userList.get(vo.index).getPw();
			
			if(input.equals(pwd)) {
				mVO.setCharge(totalCharge);	
				mVO.setTotalMoney(loadMoney-totalCharge);
				payFrame.dispose();
				new PayPage2().pay2Frame.setVisible(true);
				break;
			}else if(chance != 1) {
				--chance;
				JOptionPane.showMessageDialog(payFrame, "비밀번호 오류입니다.\r\n다시 시도해 주세요", 
						"결제하기", JOptionPane.ERROR_MESSAGE);
			}else if(chance == 1) {
				--chance;
				JOptionPane.showMessageDialog(payFrame, "비밀번호 3회 오류입니다. 메인화면으로 이동합니다", 
						"결제하기", JOptionPane.ERROR_MESSAGE);
				payFrame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		}
	}
	
	//택배신청내용 외부파일에 저장 메서드
	public void saveDeliverInfo() {
		String folderName = new UserDAO().getInfo().getId();
		File folder = new File(new UserVO().getUserPath()+folderName+"/");
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		try {	
			//택배주문내역
			String path = new UserVO().getUserPath()+folderName+"/deliveryDetail.txt";
			File file = new File(path);
			file.createNewFile();
			
			FileWriter output = new FileWriter(file, true);
			//송장번호-날짜-물건-코드-크기-무게-박스개수-비고-결제요금-수령인이름-전화-주소
			output.write(trackingNum+"\t");
			output.write(sVO.getDate()+"\t");
			output.write(sVO.getStuff()+"\t");
			output.write(sVO.getCode()+"\t");
			output.write(sVO.getSize()+"\t");
			output.write(sVO.getWeight()+"\t");
			output.write(sVO.getBox()+"\t");
			output.write(sVO.getNote()+"\t");
			output.write(mVO.getCharge()+"\t");
			output.write(rVO.getName()+"\t");
			output.write(rVO.getPhone()+"\t");
			output.write(rVO.getAddress()+"\r\n");
			
			//충전내역
			mVO.setFilePath(folderName);
			File file1 = new File(mVO.getFilePath());
			file1.createNewFile();
			
			FileWriter output1 = new FileWriter(file1, true);
			output1.write(0+"\t");
			output1.write(mVO.getTotalMoney()+"\t");
			output1.write(mVO.getCharge()+"\t");
			output1.write(mVO.getConvertedDate()+"\r\n");
			
			output.close();
			output1.close();
			
		}catch(Exception e) {
			
		}
		
	}
}
