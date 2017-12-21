<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Random"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="java.awt.Color"%>
<%@ page import="java.awt.Font"%>
<%@ page import="java.awt.Graphics"%>
<%@ page import="java.awt.image.BufferedImage"%>
<%@ page import="javax.imageio.ImageIO"%>
<%
	int width = 80;
	int height = 32;
	
	//创建一个内存图片
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	Graphics g = image.getGraphics();
	
	// 设置图片背景颜色
	g.setColor(new Color(0xDCDCDC));
	g.fillRect(0, 0, width, height);
	
	// 绘制图片边缘
	g.setColor(Color.black);
	g.drawRect(0, 0, width - 1, height - 1);
	
	// 获取一个随机数作为验证码
	Random rdm = new Random();
	
	//toHexString以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串
	String hash1 = Integer.toHexString(rdm.nextInt());
	
	// 制作验证码图片噪点
	for (int i = 0; i < 50; i++) {
		int x = rdm.nextInt(width);
		int y = rdm.nextInt(height);
		g.drawOval(x, y, 0, 0);
	}
	
	// 生成验证码图片
	String capstr = hash1.substring(0, 4);
	
	//将生成的随机验证码放入session域中
	session.setAttribute("key", capstr);
	g.setColor(new Color(0, 100, 0));
	g.setFont(new Font("Candara", Font.BOLD, 24));
	g.drawString(capstr, 8, 24);
	g.dispose();
	response.setContentType("image/jpeg");
	out.clear();
	out = pageContext.pushBody();
	OutputStream strm = response.getOutputStream();
	ImageIO.write(image, "jpeg", strm);
	strm.close();
%>