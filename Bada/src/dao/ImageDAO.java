package dao;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import dto.FrameVO;

public class ImageDAO {

	BufferedImage img = null;
	
	public ImageDAO() {}
	
	public void showTitleIcon(JFrame frame) {
		ImageIcon titleIcon = new ImageIcon(new FrameVO().titleIcon);
		frame.setIconImage(titleIcon.getImage());
	}
	
	public void showImage(String imgPath, int x, int y, int width, int height, JFrame frame, JLayeredPane layer) {
		try {
			img = ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			System.out.println("Image loading failure");
		}
		DrawImage painting = new DrawImage();
		painting.setBounds(x, y, width, height);
//		painting.setSize(width, height);
		frame.add(layer);
		layer.add(painting, new Integer(1));
	}
	
	public void showMovingImg(String imgPath, JLayeredPane jpanel, int width, int height) {
		JPanel panel = new JPanel();
		panel.setSize(width, height);
		JLabel label = new JLabel();
		label.setSize(width, height);
		ImageIcon icon = new ImageIcon(imgPath);
		label.setIcon(icon);
		jpanel.add(label, new Integer(0));
	}
	
	class DrawImage extends JPanel{
		@Override
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}
}
