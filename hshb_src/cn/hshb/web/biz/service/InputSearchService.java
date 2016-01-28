package cn.hshb.web.biz.service;

public interface InputSearchService {
	
	/**
	 * 根据查询的关键字,进行模糊查询,拼接地址,并返回json数据
	 * @param searchKeyword	关键字
	 * @return
	 */
	public String getSearchJsonData( String searchKeyword);
}
