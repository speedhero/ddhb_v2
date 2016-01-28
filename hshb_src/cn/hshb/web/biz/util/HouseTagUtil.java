/**
 * 
 */
package cn.hshb.web.biz.util;

import java.util.ArrayList;
import java.util.List;

import cn.hshb.web.biz.mybatis.model.CommonFlag;
import cn.hshb.web.biz.mybatis.model.CommonLiveFacility;
import cn.hshb.web.house.enums.EnumHouseFurniture;
import cn.hshb.web.house.enums.EnumHouseTag;

/**
 * 房源标签工具类，配合EnumHouseType使用
 * 
 * @author ShengYoufu
 *
 */
public class HouseTagUtil {

	/**
	 * 根据传入的标签组合值取得对应的标签名称
	 * @param tagVal
	 * @return
	 */
	public static List<String> getTagNamesByValue(Integer tagVal){
		List<String> tagList = new ArrayList<String>();
		
		for(EnumHouseTag tag: EnumHouseTag.values()){
			if((tagVal & tag.value()) == tag.value())
				tagList.add(tag.getTitle());
		}
		return tagList;
	}
	/**
	 * 把标签枚举的值放入CommonFlagList的对象中
	 * @return
	 */
	public static List<CommonFlag> getTagNamesAndValue(){
		List<CommonFlag> tagList = new ArrayList<CommonFlag>();
		for(EnumHouseTag tag : EnumHouseTag.values()){
			CommonFlag flag = new CommonFlag();
			flag.setTagName(tag.getTitle());
			flag.setModuleId(tag.getValue());
			tagList.add(flag);
		}
		return tagList;
	}
	/**
	 * 根据标签组合值取得标签枚举对象
	 * @param tagVal 按位进行组合的房源标签组合值
	 * @return
	 */
	public static List<EnumHouseTag> getTagsByValue(Integer tagVal){
		List<EnumHouseTag> tagList = new ArrayList<EnumHouseTag>();
		for(EnumHouseTag tag: EnumHouseTag.values()){
			//按位判断包含哪些房源标签值
			if((tagVal & tag.value()) == tag.value())
				tagList.add(tag);
		}
		return tagList;
	}

	/**
	 * 根据值获取出租屋内设施枚举值
	 * @param furVal 按位进行组合的出租屋内设施组合值
	 * @return
	 */
	public static List<EnumHouseFurniture> getFurnitureByValue(Integer furVal){
		List<EnumHouseFurniture> furList = new ArrayList<EnumHouseFurniture>();
		for(EnumHouseFurniture fur: EnumHouseFurniture.values()){
			//按位判断包含哪些出租屋内设施值
			if((furVal & fur.value()) == fur.value())
				furList.add(fur);
		}
		return furList;
	}
	
	/**
	 * 获取出租屋内设施枚举,并放入出租屋对象列表中
	 * @return
	 */
	public static List<CommonLiveFacility> getFurnitureList(){
		List<CommonLiveFacility> furList = new ArrayList<CommonLiveFacility>();
		List<EnumHouseFurniture> EFurList = new ArrayList<EnumHouseFurniture>();
		for(EnumHouseFurniture fur : EnumHouseFurniture.values()){
			CommonLiveFacility commonLiveFacility = new CommonLiveFacility();
			commonLiveFacility.setClfName(fur.getTitle());
			commonLiveFacility.setErpId(String.valueOf(fur.getValue()));
			commonLiveFacility.setIconUrl(fur.getIconUri());
			commonLiveFacility.setDisableIconUrl(fur.getDisableIconUri());
			furList.add(commonLiveFacility);
		}
		return furList;
	}
	public static void main(String[] args){
		System.out.println("开始");
		getFurnitureByValue(2);
	}
}
