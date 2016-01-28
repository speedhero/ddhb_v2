package com.huatek.hbwebsite.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

public final class TwoDimensionMaker {
	private static final int BLACK = -16777216;
	private static final int WHITE = -1;

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, 1);

		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				image.setRGB(x, y, matrix.get(x, y) ? -16777216 : -1);
			}
		}

		return image;
	}

	public static BufferedImage getFinalPic(String urlbuBuilder, String searchNo, String houseNo, int width, int height)
			throws WriterException {
		BufferedImage QRImage = getQRImage(urlbuBuilder, width, height);
		BufferedImage imgFinal = mergeimg(QRImage, drawleft(searchNo, houseNo, height, 170), width, height, 170);
		return imgFinal;
	}

	public static BufferedImage getQRImage(String urlbuBuilder, int width, int height) throws WriterException {
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.MARGIN, Integer.valueOf(0));
		BitMatrix bitMatrix = (new MultiFormatWriter()).encode(urlbuBuilder, BarcodeFormat.QR_CODE, width, height, hints);
		BufferedImage QRImage = toBufferedImage(bitMatrix);
		return QRImage;
	}

	public static BufferedImage getFinalPic(String urlbuBuilder) throws WriterException {
		short width = 300;
		short height = 300;
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.MARGIN, Integer.valueOf(0));
		BitMatrix bitMatrix = (new MultiFormatWriter()).encode(urlbuBuilder, BarcodeFormat.QR_CODE, width, height, hints);
		BufferedImage image = toBufferedImage(bitMatrix);
		return image;
	}

	public static BufferedImage getFinalPic(String urlbuBuilder, String communityNo, int width, int height)
			throws WriterException {
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.MARGIN, Integer.valueOf(0));
		BitMatrix bitMatrix = (new MultiFormatWriter()).encode(urlbuBuilder, BarcodeFormat.QR_CODE, width, height, hints);
		BufferedImage image = toBufferedImage(bitMatrix);
		BufferedImage imgFinal = mergeimg(image, drawcomleft(communityNo, height), width, height, 120);
		return imgFinal;
	}

	public static BufferedImage drawleft(String searchNo, String houseNo, int height, int width) {
		BufferedImage image = new BufferedImage(width, height, 1);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setBackground(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setPaint(Color.BLACK);
		g.drawString("查询编号", 0, 10);
		g.drawString(searchNo, 0, 30);
		g.drawLine(0, 40, 75, 40);
		g.drawString("房源编号", 0, 60);
		g.drawString(houseNo, 0, 80);
		g.dispose();
		return image;
	}

	public static BufferedImage drawcomleft(String communityNo, int height) {
		short width = 130;
		BufferedImage image = new BufferedImage(width, height, 1);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setBackground(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setPaint(Color.BLACK);
		g.drawString("小区编号", 0, 60);
		g.drawString(communityNo, 0, 90);
		g.dispose();
		return image;
	}

	private static BufferedImage mergeimg(BufferedImage QRImage, BufferedImage textImage, int QRWidth, int QRHeight,
			int textImageWidth) {
		int[] ImageArrayOne = new int[textImageWidth * QRHeight];
		ImageArrayOne = textImage.getRGB(0, 0, textImageWidth, QRHeight, ImageArrayOne, 0, textImageWidth);
		int[] ImageArrayTwo = new int[QRWidth * QRHeight];
		ImageArrayTwo = QRImage.getRGB(0, 0, QRWidth, QRHeight, ImageArrayTwo, 0, QRWidth);
		BufferedImage destImage = null;
		destImage = new BufferedImage(textImageWidth + QRWidth, QRHeight, 1);
		Graphics2D g = (Graphics2D) destImage.getGraphics();
		g.setPaint(Color.BLACK);
		destImage.setRGB(0, 0, textImageWidth, QRHeight, ImageArrayOne, 0, textImageWidth);
		destImage.setRGB(textImageWidth, 0, QRWidth, QRHeight, ImageArrayTwo, 0, QRWidth);
		return destImage;
	}

	public static BufferedImage getTDCPic(String urlbuBuilder) throws WriterException {
		short width = 135;
		short height = 135;
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.MARGIN, Integer.valueOf(0));
		BitMatrix bitMatrix = (new MultiFormatWriter()).encode(urlbuBuilder, BarcodeFormat.QR_CODE, width, height, hints);
		BufferedImage image = toBufferedImage(bitMatrix);
		return image;
	}
}
