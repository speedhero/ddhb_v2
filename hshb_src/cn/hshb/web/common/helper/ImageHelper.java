/**
 * 
 */
package cn.hshb.web.common.helper;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * @author Administrator
 *
 */
public class ImageHelper {
	private static final Log log = LogFactory.getLog(ImageHelper.class);
	
	public static void zoom(String srcFilePath, String targetFilePath, Float scale) throws IOException {
		zoom(srcFilePath, targetFilePath, scale, 1f);
	}
	/**
	 * 缩放图片
	 * @param srcFilePath		原始图片路径
	 * @param targetFilePath	目标图片路径 
	 * @param scale				缩放比例
	 * @param quality			输出质量
	 * @throws IOException
	 */
	public static void zoom(String srcFilePath, String targetFilePath, Float scale, Float quality) throws IOException {
		Float _quality = (quality==null || quality<=0) ? 1f : quality;
		Thumbnails.of(srcFilePath).scale(scale).outputQuality(_quality).toFile(targetFilePath);
	}
	
	public static void zoom(String srcFilePath, String targetFilePath, Integer width, Integer height) throws IOException {
		zoom(srcFilePath, targetFilePath, width, height, 1f);
	}
	
	/**
	 * 缩放图片
	 * @param srcFilePath		原始图片路径
	 * @param targetFilePath	目标图片路径 
	 * @param width				新宽
	 * @param height			新高
	 * @param quality			输出质量
	 * @throws IOException
	 */
	public static void zoom(String srcFilePath, String targetFilePath, Integer width, Integer height, Float quality) throws IOException {
		Float _quality = (quality==null || quality<=0) ? 1f : quality;
		Thumbnails.of(srcFilePath).size(width, height).outputQuality(_quality).toFile(targetFilePath);
	}
	
	/**
	 * 图片缩放
	 * @param srcFilePath		原始图片路径
	 * @param targetFilePath	目标图片路径 
	 * @param width				新宽
	 * @param height			新高
	 * @param resizeMode		缩放模式 
	 * @param quality			输出质量
	 * @throws IOException	
	 */
	public static void resize(String srcFilePath, String targetFilePath, Integer width, Integer height, EnumResizeMode resizeMode, Color padColor, Float quality) 
			throws IOException {
		
		File srcFile = new File(srcFilePath);
		if(!srcFile.exists()){
			throw new IOException("指定的原始图片["+srcFilePath+"]不存在!");
		}
		File destFile = new File(targetFilePath);
//		String destPath = destFile.getParentFile();
//		File destFolder = new File(destPath);
		if(!destFile.getParentFile().exists())
			throw new IOException("目标路径["+destFile.getParentFile().getPath()+"]不存在!");
		if(!destFile.getParentFile().isDirectory())
			throw new IOException("存在与["+destFile.getParentFile().getPath()+"]同名的文件，但不是路径!");
		
		Color _padColor = padColor==null? Color.WHITE : padColor;
		Float _quality = (quality==null || quality<=0) ? 1f : quality;
		BufferedImage img = ImageIO.read(srcFile);
		Integer srcW = img.getWidth();
		Integer srcH = img.getHeight();

		int nw = width;
		int nh = height;
		switch(resizeMode){
			case FIT_WIDTH:
				nh = height * nh / nw;
				Thumbnails.of(srcFilePath).size(nw, nh).outputQuality(_quality).toFile(targetFilePath);
				break;
			case FIT_HEIGHT:
				nw = width * nw / nh;
				Thumbnails.of(srcFilePath).size(nw, nh).outputQuality(_quality).toFile(targetFilePath);
				break;
			case FIT_MAX:
				Thumbnails.of(srcFilePath).size(nw, nh).outputQuality(_quality).toFile(targetFilePath);
				break;
			case FIT_MIN:
				if(srcW < srcH){
					nw = width * nw / nh;
				}else{
					nh = height * nh / nw;
				}
				Thumbnails.of(srcFilePath).size(nw, nh).outputQuality(_quality).toFile(targetFilePath);
				break;
			case STRETCH:
				Thumbnails.of(srcFilePath).size(nw, nh).keepAspectRatio(false).outputQuality(_quality).toFile(targetFilePath);
				break;
			case PADDING:
				//创建大的指定颜色背景图
		        BufferedImage bImg = new BufferedImage(nw, nh, BufferedImage.TYPE_INT_RGB);
		        Graphics2D g = bImg.createGraphics();
		        g.setColor(_padColor);
		        g.fillRect(0, 0, bImg.getWidth(), bImg.getHeight());
		        g.dispose();

		        //把原图以水印的方式加到新图上
		        Thumbnails.of(bImg).size(width, height).watermark(Positions.CENTER, Thumbnails.of(img).size(nw, nh).asBufferedImage(), 1f).outputQuality(_quality).toFile(targetFilePath);
				break;
			default:
				Thumbnails.of(srcFilePath).size(nw, nh).outputQuality(_quality).toFile(targetFilePath);
				break;
		}
	}
	
	/**
	 * 图片缩放枚举
	 * @author Administrator
	 *
	 */
	public enum EnumResizeMode{
		FIT_WIDTH("适应宽度，保持原始比例"),
		FIT_HEIGHT("适应高度，保持原始比例"),
		FIT_MAX("适应宽或高较大的那个，保持原始比例"),
		FIT_MIN("适应宽或高较小的那个，保持原始比例"),
		STRETCH("按给定的宽高拉伸"),
		PADDING("按给定宽高设置图片大小，空白处填充按给定颜色");
		
		private String description;
		EnumResizeMode(String description){
			this.description = description;
		}
		public String description() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	}

}
