package com.huatek.hbwebsite.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RandomCode {
	private Font getsFont(Random random) {
		return new Font("Fixedsys", 1, 18);
	}

	private Color getRandColor(int fc, int bc, Random random) {
		if (fc > 255) {
			fc = 255;
		}

		if (bc > 255) {
			bc = 255;
		}

		int r = fc + random.nextInt(bc - fc - 6);
		int g = fc + random.nextInt(bc - fc - 4);
		int b = fc + random.nextInt(bc - fc - 8);
		return new Color(r, g, b);
	}

	public void getRandcode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.setProperty("java.awt.headless", "true");
		HttpSession session = request.getSession();
		byte width = 60;
		byte height = 22;
		BufferedImage image = new BufferedImage(width, height, 1);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.fillRect(1, 1, width, height);
		g.setFont(new Font("Times New Roman", 0, 18));
		g.setColor(this.getRandColor(111, 133, random));

		int sos;
		for (int sRand = 0; sRand < 11; ++sRand) {
			sos = random.nextInt(width);
			int rand = random.nextInt(height);
			int xl = random.nextInt(13);
			int yl = random.nextInt(15);
			g.drawLine(sos, rand, sos + xl, rand + yl);
		}

		g.setColor(this.getRandColor(130, 150, random));
		String var14 = "";

		for (sos = 0; sos < 4; ++sos) {
			g.setFont(this.getsFont(random));
			g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
			String var16 = String.valueOf(this.getRandomString(random.nextInt(10)));
			var14 = var14 + var16;
			g.translate(random.nextInt(3), random.nextInt(3));
			g.drawString(var16, 13 * sos, 16);
		}

		session.removeAttribute("Rand");
		session.setAttribute("Rand", var14);
		g.dispose();
		ServletOutputStream var15 = response.getOutputStream();
		ImageIO.write(image, "JPEG", var15);
		var15.close();
	}

	private String getRandomString(int num) {
		String randstring = "0123456789";
		return String.valueOf(randstring.charAt(num));
	}
}
