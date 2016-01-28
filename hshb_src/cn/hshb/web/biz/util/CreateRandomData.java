package cn.hshb.web.biz.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import cn.hshb.web.common.util.DateUtil;

/**
 * 创建随机数据
 * @author hejianbo
 *	2015年9月23日
 */
public class CreateRandomData {

	/**
	 * 创建一个随机数
	 * @param max	随机数最大范围
	 * @param min	随机数最小范围
	 * @return
	 */
	public long createRandomNumber(int max, int min){
		return Math.round(Math.random()*(max-min)+min);
	}
	public long createRandomNumber(int max){
		return new Random().nextInt(max);
	}
	
	
	public static void main(String[] args){
//		CreateRandomData cr = new CreateRandomData();
//		for(int i = 1; i < 110; i++){
////			System.out.print(cr.createRandomNumber(1, 30)+"-" );
//			System.out.print(cr.createRandomNumber(60)+"-" );
//			if(i == 50)System.out.println();
//			if(i == 100)System.out.println();
//		}
//		Random r1 = new Random();
		//取出一个随机数
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒SSS毫秒");
		
		System.out.println("改之前的之间:" + sd.format(date));
		long days = new CreateRandomData().createRandomNumber(1, 30);
		System.out.print(" 增加天数:" + days);
		date = DateUtil.addDay(date, (int)days);
		long hours = new CreateRandomData().createRandomNumber(24);
		System.out.print(" 增加小时:" + hours);
		date = DateUtil.addHour(date, (int)hours);
		long minutes = new CreateRandomData().createRandomNumber(60);
		System.out.print(" 增加分钟:" + minutes);
		date = DateUtil.addMinute(date, (int)minutes);
		long seconds = new CreateRandomData().createRandomNumber(60);
		System.out.println(" 增加秒:" + seconds);
		date = DateUtil.addSecond(date, (int)seconds);
		System.out.println("改之前的之后:" + sd.format(date));
		System.out.println();
		
	}
}
