/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.RecruitCandidate;
import cn.hshb.web.biz.mybatis.model.RecruitPosition;
import cn.hshb.web.biz.mybatis.model.RecruitPositionType;

/**
 * @author ShengYoufu
 *
 */
public interface RecruitService {
	
	public List<RecruitPositionType> getAllRecruitPositionType();

	/**
	 * 根据职位ID获取招聘职位
	 * @param typeId	职位ID
	 * @param pageSize	每页记录数
	 * @param pageNum	页码
	 * @return
	 */
	public List<RecruitPosition> getRecruitPositionByType(Integer typeId, Integer pageSize, Integer pageNum);
	/**
	 * 根据职位ID获取招聘职位
	 * @param typeId	职位ID
	 * @param pageSize	每页记录数
	 * @param pageNumStr字符串  如:n1,将使用这正则替换"n([\\d]+)" 
	 * @return
	 */
	public List<RecruitPosition> getRecruitPositionByType(Integer typeId, Integer pageSize, String pageNumStr);
	/**
	 * 获取职位详情
	 * @param positionId	职位ID
	 * @return
	 */
	public RecruitPosition getPositionDetailByPositionId(Integer positionId);

	/**
	 * 检测应聘者是否存在
	 * @param positionId	职位ID
	 * @param name			应聘者姓名
	 * @param telephone		应聘者电话
	 * @return
	 */
	public Boolean getIsExist(Integer positionId, String name, String telephone);
	
	
	/**
	 * 保存应聘信息
	 * @param record	应聘信息
	 * @return
	 */
	public Boolean save(RecruitCandidate record);
}
