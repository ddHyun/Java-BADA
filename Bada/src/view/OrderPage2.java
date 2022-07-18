package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.ButtonDAO;
import dao.FrameDAO;
import dao.ImageDAO;
import dao.LabelDAO;
import dao.PanelDAO;
import dao.TextDAO;
import dto.FrameVO;
import dto.StuffVO;

public class OrderPage2 {
	
	//물품 정보 기입페이지
	FrameDAO order2Frame;
	PanelDAO order2Layer;
	LabelDAO titleLabel, parcelLabel, pLabel, sizeLabel, sizeLabel2, weightLabel, weightLabel2,
					stuffCodeLabel, stuffLabel, boxLabel, boxLabel2, noteLabel;
	TextDAO sizeText, weightText, stuffText, boxText, noteText;	
	JComboBox<String> codeBox, noteBox;
	ButtonDAO notPermittedItemBtn, cancelBtn, nextBtn;
	JTextArea noteArea;
	
	public OrderPage2() {
		
		order2Frame = new FrameDAO();
		order2Frame.makeFrame();
		order2Layer = new PanelDAO();
		order2Layer.makeLayer();
		order2Frame.add(order2Layer);
//		String message = "주문가능한 택배박스의 크기는\r\n[가로+세로+높이]의 합이 160cm 이내입니다.";
//		JOptionPane.showMessageDialog(order2Frame, message, "택배주문 신청", JOptionPane.INFORMATION_MESSAGE);
		
		new PanelDAO().makeColorPanel(order2Layer);
		titleLabel = new LabelDAO("택배주문 신청", FrameVO.font40, order2Layer, 1);
		titleLabel.makeTitleLabel();
		
		//물품 정보
		pLabel = new LabelDAO("물품 정보", FrameVO.font25, order2Layer, 1);
		pLabel.setBounds(20, 140, 200, 30);
		pLabel.setOpaque(true);
		pLabel.setBackground(FrameVO.grayColor);
		//박스크기 및 무게
		parcelLabel = new LabelDAO("· 박스 크기(가로+세로+높이의 합) / 무게 ", FrameVO.font20, order2Layer, 1);
		parcelLabel.setBounds(20, 190, 440, 30);
		sizeLabel = new LabelDAO("크기 :", FrameVO.font18, order2Layer, 1);
		sizeLabel.setBounds(30, 230, 60, 30);
		sizeText = new TextDAO(FrameVO.font18, order2Layer, 1);
		sizeText.setBounds(90, 230, 50, 30);
		sizeLabel2 = new LabelDAO("cm", FrameVO.font18, order2Layer, 1);
		sizeLabel2.setBounds(135, 230, 50, 30);
		weightLabel = new LabelDAO("무게 :", FrameVO.font18, order2Layer, 1);
		weightLabel.setBounds(200, 230, 60, 30);
		weightText = new TextDAO(FrameVO.font18, order2Layer, 1);
		weightText.setBounds(260, 230, 50, 30);
		weightLabel2 = new LabelDAO("kg", FrameVO.font18, order2Layer, 1);
		weightLabel2.setBounds(305, 230, 50, 30);
		//내용물
		stuffCodeLabel = new LabelDAO("· 내용품 코드", FrameVO.font20, order2Layer, 1);
		stuffCodeLabel.setBounds(20, 290, 150, 30);
		String[] code = {"", "농/수/축산물(일반)", "농/수/축산물(냉장/냉동)", "전자제품", "서적", "의류/패션잡화",
									"미용/화장품", "의료/건강식품", "생활용품", "기타"};
		codeBox = new JComboBox<String>(code);
		codeBox.setBounds(180, 290, 260, 30);
		codeBox.setFont(FrameVO.font18);
		order2Layer.add(codeBox, new Integer(1));		
		
		stuffLabel = new LabelDAO("  ㄴ내용물", FrameVO.font20, order2Layer, 1);
		stuffLabel.setBounds(20, 330, 100, 30);
		stuffText = new TextDAO(FrameVO.font18, order2Layer, 1);
		stuffText.setBounds(180, 330, 260, 30);
		//박스 수량
		boxLabel = new LabelDAO("· 박스 수량 : ", FrameVO.font20, order2Layer, 1);
		boxLabel.setBounds(20, 390, 140, 30);
		boxText = new TextDAO(FrameVO.font18, order2Layer, 1);
		boxText.setBounds(150, 390, 50, 30);
		boxLabel2 = new LabelDAO("개", FrameVO.font18, order2Layer, 1);
		boxLabel2.setBounds(195, 390, 40, 30);
		
		//취급제한 품목 안내
		notPermittedItemBtn = new ButtonDAO();
		notPermittedItemBtn.makeGrayButton("취급제한 품목", order2Layer, 1);
		notPermittedItemBtn.setBounds(280, 390, 160, 30);
		//버튼 클릭시 취급제한 품목 보여짐
		avoidStuff();
		
		//특이사항
		noteLabel = new LabelDAO("· 배송시 특이사항", FrameVO.font20, order2Layer, 1);
		noteLabel.setBounds(20, 450, 200, 30);
		String[] msg = {"", "파손(깨짐)주의", "경비실에 맡겨주세요", "문 앞에 놓아주세요",
								"무인우편물보관함에 넣어 주세요", "배송전 연락 부탁드립니다", "직접 입력"};
		noteBox = new JComboBox<String>(msg);
		noteBox.setBounds(20, 490, 420, 30);
		noteBox.setFont(FrameVO.font18);
		order2Layer.add(noteBox, new Integer(1));
		//직접 입력
		noteArea = new JTextArea();
		JScrollPane scrollPanel = new JScrollPane(noteArea);
		noteArea.setBounds(20, 530, 420, 70);
		scrollPanel.setBounds(20, 530, 420, 70);
		order2Layer.add(scrollPanel, new Integer(1));
		noteArea.setFont(FrameVO.font18);
		noteArea.setBackground(FrameVO.grayColor);
		noteArea.setLineWrap(true);
		
		noteBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					noteArea.setText((String)noteBox.getSelectedItem());
					noteArea.setEditable(false);
					if(noteBox.getSelectedItem().equals("직접 입력")) {
						noteArea.setEditable(true);
						noteArea.setText("");
					}
				}
			}
		});
		
		//취소, 다음 버튼				
		cancelBtn = new ButtonDAO();
		cancelBtn.makeGrayButton("취소하기", order2Layer, 1);
		cancelBtn.setBounds(20, 640, 200, 50);
		cancelBtn.setFont(FrameVO.font30);
		
		nextBtn = new ButtonDAO();
		nextBtn.makeBlueButton("다음단계 >>", order2Layer, 1);
		nextBtn.setBounds(240, 640, 200, 50);
		
		//취소버튼 : 주문2 -> 메인페이지 이동
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				order2Frame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		});		
		
		//다음버튼 : 결제페이지 이동
		nextBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				order2Frame.dispose();
				setStuffInfo();
				new OrderPage3().order3Frame.setVisible(true);
			}
		});
		
		//아이콘 이미지
		new ImageDAO().showTitleIcon(order2Frame);
		order2Frame.setVisible(true);
		
	}
	
	//취급제한 품목 안내 메서드
	public void avoidStuff() {
		notPermittedItemBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "- [우편법 제17조 제1항]의 규정에 의하여 우편금지 물품으로 고시한 물품\r\n"
						+"- 기타 접수가 곤란한 물품\r\n\r\n"
						+ "  ============================ 구분 ============================\r\n"
						+ "  · 현금 및 유가증권\r\n"
						+ "    - 현금, 어음, 수표, 상품권 등 현금화가 가능한 물품\r\n"
						+ "  · 변질 및 부패성 물품\r\n"
						+ "    - 활어, 동물, 동물사체, 화훼류 등\r\n"
						+ "  · 전자제품\r\n"
						+ "    - 컴퓨터 본체, 프린터기, 모니터, 노트북 등\r\n"
						+ "  · 파손 우려 물품\r\n"
						+ "    - 유리 및 유리제품, 항아리, 도자기 등\r\n"
						+ "  · 대형가구\r\n"
						+ "    - 침대, 장롱, 책상, 소파 등(1인 운반 불가 물품)\r\n"
						+ "  · 대형물품\r\n"
						+ "    - 자전거, 오토바이, 철제품 등(1인 운반 불가 물품)\r\n"
						+ "  · 기타\r\n"
						+ "    - 포장하기 곤란하거나 상품가격이 300만원을 초과하는 물품\r\n\r\n"
						+ "※ 중량은 30kg 이하이고, 한변의 최대 길이는 100cm이내에 한해 취급함\r\n"
						+ "※ 가로 + 세로 + 높이 = 160cm 이내";
				JOptionPane.showMessageDialog(order2Frame, message, "취급제한 품목", JOptionPane.PLAIN_MESSAGE);
			}
		});
	}
	
	//주문물품 정보 저장 메서드
	public void setStuffInfo() {
		int size = Integer.parseInt(sizeText.getText().toString());
		int weight = Integer.parseInt(weightText.getText());
		String code = codeBox.getSelectedItem().toString(); 
		String stuff = stuffText.getText();
		int box = Integer.parseInt(boxText.getText());
		String note = noteArea.getText();	
		
		StuffVO stuffVO = StuffVO.getInstance();
		
		stuffVO.setSize(size);
		stuffVO.setWeight(weight);		
		stuffVO.setCode(code);
		stuffVO.setStuff(stuff);
		stuffVO.setBox(box);
		stuffVO.setNote(note);
	}
}
