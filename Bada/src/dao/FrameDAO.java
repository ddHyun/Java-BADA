package dao;

import javax.swing.JFrame;

import dto.FrameVO;

public class FrameDAO extends JFrame{

	public FrameDAO() {
		makeFrame();
	}
	
	public void makeFrame() {
		setTitle(FrameVO.title);
//		setLocationRelativeTo(null);
		setSize(480, 800);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
