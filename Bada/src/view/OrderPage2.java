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
	
	//��ǰ ���� ����������
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
//		String message = "�ֹ������� �ù�ڽ��� ũ���\r\n[����+����+����]�� ���� 160cm �̳��Դϴ�.";
//		JOptionPane.showMessageDialog(order2Frame, message, "�ù��ֹ� ��û", JOptionPane.INFORMATION_MESSAGE);
		
		new PanelDAO().makeColorPanel(order2Layer);
		titleLabel = new LabelDAO("�ù��ֹ� ��û", FrameVO.font40, order2Layer, 1);
		titleLabel.makeTitleLabel();
		
		//��ǰ ����
		pLabel = new LabelDAO("��ǰ ����", FrameVO.font25, order2Layer, 1);
		pLabel.setBounds(20, 140, 200, 30);
		pLabel.setOpaque(true);
		pLabel.setBackground(FrameVO.grayColor);
		//�ڽ�ũ�� �� ����
		parcelLabel = new LabelDAO("�� �ڽ� ũ��(����+����+������ ��) / ���� ", FrameVO.font20, order2Layer, 1);
		parcelLabel.setBounds(20, 190, 440, 30);
		sizeLabel = new LabelDAO("ũ�� :", FrameVO.font18, order2Layer, 1);
		sizeLabel.setBounds(30, 230, 60, 30);
		sizeText = new TextDAO(FrameVO.font18, order2Layer, 1);
		sizeText.setBounds(90, 230, 50, 30);
		sizeLabel2 = new LabelDAO("cm", FrameVO.font18, order2Layer, 1);
		sizeLabel2.setBounds(135, 230, 50, 30);
		weightLabel = new LabelDAO("���� :", FrameVO.font18, order2Layer, 1);
		weightLabel.setBounds(200, 230, 60, 30);
		weightText = new TextDAO(FrameVO.font18, order2Layer, 1);
		weightText.setBounds(260, 230, 50, 30);
		weightLabel2 = new LabelDAO("kg", FrameVO.font18, order2Layer, 1);
		weightLabel2.setBounds(305, 230, 50, 30);
		//���빰
		stuffCodeLabel = new LabelDAO("�� ����ǰ �ڵ�", FrameVO.font20, order2Layer, 1);
		stuffCodeLabel.setBounds(20, 290, 150, 30);
		String[] code = {"", "��/��/��깰(�Ϲ�)", "��/��/��깰(����/�õ�)", "������ǰ", "����", "�Ƿ�/�м���ȭ",
									"�̿�/ȭ��ǰ", "�Ƿ�/�ǰ���ǰ", "��Ȱ��ǰ", "��Ÿ"};
		codeBox = new JComboBox<String>(code);
		codeBox.setBounds(180, 290, 260, 30);
		codeBox.setFont(FrameVO.font18);
		order2Layer.add(codeBox, new Integer(1));		
		
		stuffLabel = new LabelDAO("  �����빰", FrameVO.font20, order2Layer, 1);
		stuffLabel.setBounds(20, 330, 100, 30);
		stuffText = new TextDAO(FrameVO.font18, order2Layer, 1);
		stuffText.setBounds(180, 330, 260, 30);
		//�ڽ� ����
		boxLabel = new LabelDAO("�� �ڽ� ���� : ", FrameVO.font20, order2Layer, 1);
		boxLabel.setBounds(20, 390, 140, 30);
		boxText = new TextDAO(FrameVO.font18, order2Layer, 1);
		boxText.setBounds(150, 390, 50, 30);
		boxLabel2 = new LabelDAO("��", FrameVO.font18, order2Layer, 1);
		boxLabel2.setBounds(195, 390, 40, 30);
		
		//������� ǰ�� �ȳ�
		notPermittedItemBtn = new ButtonDAO();
		notPermittedItemBtn.makeGrayButton("������� ǰ��", order2Layer, 1);
		notPermittedItemBtn.setBounds(280, 390, 160, 30);
		//��ư Ŭ���� ������� ǰ�� ������
		avoidStuff();
		
		//Ư�̻���
		noteLabel = new LabelDAO("�� ��۽� Ư�̻���", FrameVO.font20, order2Layer, 1);
		noteLabel.setBounds(20, 450, 200, 30);
		String[] msg = {"", "�ļ�(����)����", "���ǿ� �ð��ּ���", "�� �տ� �����ּ���",
								"���ο��������Կ� �־� �ּ���", "����� ���� ��Ź�帳�ϴ�", "���� �Է�"};
		noteBox = new JComboBox<String>(msg);
		noteBox.setBounds(20, 490, 420, 30);
		noteBox.setFont(FrameVO.font18);
		order2Layer.add(noteBox, new Integer(1));
		//���� �Է�
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
					if(noteBox.getSelectedItem().equals("���� �Է�")) {
						noteArea.setEditable(true);
						noteArea.setText("");
					}
				}
			}
		});
		
		//���, ���� ��ư				
		cancelBtn = new ButtonDAO();
		cancelBtn.makeGrayButton("����ϱ�", order2Layer, 1);
		cancelBtn.setBounds(20, 640, 200, 50);
		cancelBtn.setFont(FrameVO.font30);
		
		nextBtn = new ButtonDAO();
		nextBtn.makeBlueButton("�����ܰ� >>", order2Layer, 1);
		nextBtn.setBounds(240, 640, 200, 50);
		
		//��ҹ�ư : �ֹ�2 -> ���������� �̵�
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				order2Frame.dispose();
				new MainPage().mainFrame.setVisible(true);
			}
		});		
		
		//������ư : ���������� �̵�
		nextBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				order2Frame.dispose();
				setStuffInfo();
				new OrderPage3().order3Frame.setVisible(true);
			}
		});
		
		//������ �̹���
		new ImageDAO().showTitleIcon(order2Frame);
		order2Frame.setVisible(true);
		
	}
	
	//������� ǰ�� �ȳ� �޼���
	public void avoidStuff() {
		notPermittedItemBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "- [����� ��17�� ��1��]�� ������ ���Ͽ� ������� ��ǰ���� ����� ��ǰ\r\n"
						+"- ��Ÿ ������ ����� ��ǰ\r\n\r\n"
						+ "  ============================ ���� ============================\r\n"
						+ "  �� ���� �� ��������\r\n"
						+ "    - ����, ����, ��ǥ, ��ǰ�� �� ����ȭ�� ������ ��ǰ\r\n"
						+ "  �� ���� �� ���м� ��ǰ\r\n"
						+ "    - Ȱ��, ����, ������ü, ȭ�ѷ� ��\r\n"
						+ "  �� ������ǰ\r\n"
						+ "    - ��ǻ�� ��ü, �����ͱ�, �����, ��Ʈ�� ��\r\n"
						+ "  �� �ļ� ��� ��ǰ\r\n"
						+ "    - ���� �� ������ǰ, �׾Ƹ�, ���ڱ� ��\r\n"
						+ "  �� ��������\r\n"
						+ "    - ħ��, ���, å��, ���� ��(1�� ��� �Ұ� ��ǰ)\r\n"
						+ "  �� ������ǰ\r\n"
						+ "    - ������, �������, ö��ǰ ��(1�� ��� �Ұ� ��ǰ)\r\n"
						+ "  �� ��Ÿ\r\n"
						+ "    - �����ϱ� ����ϰų� ��ǰ������ 300������ �ʰ��ϴ� ��ǰ\r\n\r\n"
						+ "�� �߷��� 30kg �����̰�, �Ѻ��� �ִ� ���̴� 100cm�̳��� ���� �����\r\n"
						+ "�� ���� + ���� + ���� = 160cm �̳�";
				JOptionPane.showMessageDialog(order2Frame, message, "������� ǰ��", JOptionPane.PLAIN_MESSAGE);
			}
		});
	}
	
	//�ֹ���ǰ ���� ���� �޼���
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
