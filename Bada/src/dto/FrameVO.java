package dto;

import java.awt.*;
import java.io.File;

public class FrameVO{	
	
	public static String title = "BADA";
	public String titleApp1 = "�츮";
	public String titleApp2 = "����";
	public String titleApp = "�� �� �� ��";
	public String titleURL = "image/mainImg.jpg";
	public String titleIcon = "image/titleIcon.jpg";	
	public String titleGIF = "image/mainImg.gif";
	public Color panelColor = new Color(192,227,237);
	public static Color btnColor = new Color(173,216,230);
	public static Color grayColor = new Color(245,245,245);
	public static Font gugi60;
	public static Font font18 = new Font("�������", Font.PLAIN, 18);
	public static Font font20 = new Font("�������", Font.BOLD, 20);
	public static Font font25 = new Font("�������", Font.PLAIN, 25);
	public static Font font25B = new Font("�������", Font.BOLD, 25);
	public static Font font25I = new Font("�������", Font.ITALIC, 25);
	public static Font font30 = new Font("�������", Font.BOLD, 30);
	public static Font font40 = new Font("�������", Font.BOLD, 40);
	public static Font font60 = new Font("�������", Font.BOLD, 60);
	
	public FrameVO() {}
	
	public void font60() {
		try {
			gugi60 = Font.createFont(Font.TRUETYPE_FONT, new File("font/Gugi-Regular.ttf")).deriveFont(60f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/Gugi-Regular.ttf")));
		} catch (Exception e) {} 
	}
}

