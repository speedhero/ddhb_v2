/**
 * 
 */
package cn.hshb.web.test;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0 http://www.hshb.cn
 * 
 */
public class ThumbnailatorTest {
	/**
	 * 
	 * @throws IOException
	 * @brief 生成缩略图简单实例
	 * 
	 */
	public static void simple() throws IOException {
		// 需要转换的文件为桌面上的1.png
		Thumbnails.of("D:/TEMP/test.jpg")
		/*
		 * forceSize,size和scale必须且只能调用一个
		 */
		// .forceSize(400, 400) //生成的图片一定为400*400
			/*
			 * 若图片横比200小，高比300小，不变 若图片横比200小，高比300大，高缩小到300，图片比例不变
			 * 若图片横比200大，高比300小，横缩小到200，图片比例不变 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
			 */
			// .size(200, 300)
			.scale(4)
			.outputFormat("png") // 生成图片的格式为png
			.outputQuality(0.8f) // 生成质量为80%
			// .scale(0.5f) //缩小50%
			// 输出到桌面5文件
			.toFile("D:/TEMP/test_4");
		
		Long l = System.currentTimeMillis();
		Thumbnails.of("D:/TEMP/test.jpg.jpg")
			.scale(0.5)
			.outputFormat("jpg") // 生成图片的格式为png
			.outputQuality(1.0f) // 生成质量为80%
			.toFile("D:/TEMP/test_0.5");
		System.out.println(System.currentTimeMillis() - l);
	}

	/**
	 * 
	 * @throws IOException
	 * @brief 生成旋转的缩略图
	 * 
	 */
	public static void rotate() throws IOException {
		Long l = System.currentTimeMillis();
		Thumbnails.of("D:/TEMP/test.jpg")
				// 顺时针旋转90度
				.rotate(90)
				.scale(0.25f)
				.toFile("D:/TEMP/test_0.25.jpg");
		System.out.println(System.currentTimeMillis() - l);
	}

	/**
	 * 
	 * @brief 生成带水印的图片
	 * 
	 * @throws IOException
	 */
	public static void watermark() throws IOException {
		Thumbnails
				.of("D:/TEMP/test.jpg")
				// 水印在右下角，50%透明度,水印图片为桌面上的logo.gif
				.watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File("C:/Documents and Settings/Administrator/桌面/logo.gif")), 0.5f)
				.scale(0.8f)
				.toFile("D:/TEMP/test_watermark.jpg");
	}
	
	
	/**
	 * 
	 * @throws IOException
	 * @brief 生成缩略图简单实例
	 * 
	 */
	public static void zoom(String srcFile, String destFile, int width, int height) throws IOException {
		Thumbnails.of(srcFile)
		.size(width, height)
		.outputFormat("jpg")
		.outputQuality(1.0f)
		.toFile(destFile);
	}
	
	/**
	 * 缩放多次测试
	 * @param srcFile
	 * @param destFile
	 * @param width
	 * @param height
	 * @param count
	 * @return
	 * @throws IOException
	 */
	public static long zoom(String srcFile, String destFile, int width, int height, int count) throws IOException {
		Thumbnails.of(srcFile)
			.size(width, height)
			.outputFormat("jpg")
			.outputQuality(1.0f)
			.toFile(destFile);

		Long l = System.currentTimeMillis();
		for(int ii=0; ii<count; ii++){
			Thumbnails.of(srcFile)
			.size(width, height)
			.outputFormat("jpg")
			.outputQuality(1.0f)
			.toFile(destFile);
			File f = new File(destFile);
			f.delete();
		}
		return System.currentTimeMillis() - l;
	}
	
	public static void main(String[] args){
		try{
			ThumbnailatorTest.simple();
			ThumbnailatorTest.rotate();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
}
