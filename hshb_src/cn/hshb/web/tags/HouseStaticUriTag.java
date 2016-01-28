/**
 * 
 */
package cn.hshb.web.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;

import cn.hshb.web.biz.util.HouseQueryStrParser;
import cn.hshb.web.partner.baidu.common.StringUtil;

/**
 * 静态URL生成标签
 * 	
 * @author Administrator
 *
 */
public class HouseStaticUriTag extends HshbBaseSimpleTag {
	//本标签位置对应的查询参数
	private String p;
	
	//URL根路径
	private String basePath;
	
	//原有查询参数
	private String params;
	
	//房源编号
	private String houseId;
	
	//小区编号
	private String communityId;
	
	//经纪人编号
	private String brokerId;
	
	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getBasePath() {
		return basePath==null? "basePath" :  basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getParams() {
		return params == null? "params" : params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public void doTag() throws JspException, IOException{
		String basePathVal = "";
		if(containsAttr(getBasePath()))
			basePathVal = (String)getRequest().getAttribute(getBasePath());
		if(StringUtil.isNotEmpty(basePath)) basePathVal = basePath;

		Map<String, String> params = new HashMap<String, String>();
		if(containsAttr(getParams())){
			Map<String, String>  ps = (Map<String, String>) getRequest().getAttribute(getParams());
			params = HouseQueryStrParser.parseQueryCondition(ps);
		}
		StringBuilder sbUrl = new StringBuilder("");
		if(basePathVal!=null)
			sbUrl.append(basePathVal);
		sbUrl.append("/");
		
		if(StringUtil.isNotEmpty(houseId)){
			//如果房源编号不为空，则生成房源详情页链接
			sbUrl.append(houseId);
		}else if(StringUtil.isNotEmpty(communityId)){
			//如果小区编号不为空，则生成房源详情页链接
			sbUrl.append(communityId);
		}else if(StringUtil.isNotEmpty(brokerId)){
			//如果经纪人编号不为空，则生成房源详情页链接
			sbUrl.append(brokerId);
		}else{
			//房源查询
			StringBuilder sbLevel1 = new StringBuilder();
			//处理一级条件
			if(p!=null && (p.startsWith("a_") || p.startsWith("t_") || p.startsWith("q_")|| p.startsWith("f_") || p.startsWith("v_")))
				sbLevel1.append(p);
			else{
				for(String key: params.keySet()){
					if(key.equalsIgnoreCase("a_") || key.equalsIgnoreCase("t_") || key.equalsIgnoreCase("q_") || key.equalsIgnoreCase("f_") || key.equalsIgnoreCase("v_")){
						//是城区商圈或地铁线路和站点参询参数，作为url的第一级
						sbLevel1.append(key).append(params.get(key));
					}
				}
			}
			if(sbLevel1.length()>0)
				sbLevel1.append("/");
			sbUrl.append(sbLevel1);


			//当P前缀只有一位字母时
			String pPrefix = "";
			String pPrefixTwoLetters = "";
			if(p!=null && !p.startsWith("a_") && !p.startsWith("t_") && !p.startsWith("q_") && !p.startsWith("f_") && !p.startsWith("v_")){
				pPrefix = p.substring(0,  1);
				if(p.length() > 1)
					pPrefixTwoLetters = p.substring(0,2);
			}
			
			//处理二级条件
			for(String key: params.keySet()){
				if(!key.equalsIgnoreCase("a_") && !key.equalsIgnoreCase("t_") && !key.equalsIgnoreCase("q_") && !key.equalsIgnoreCase("f_") && !key.equalsIgnoreCase("v_")){
					//其他参数，要以本标签位置的参数项代替原有的该参数项
					//FIXME: 由于时间关系，这里暂时只处理暂数名为一个字符的情况，以后要修改成可以处理多字符的参数名
					if(key.equals(pPrefix)){
						//p的参数名与原参数中某一参数相同时，忽略原参数
					}else if(key.equals(pPrefixTwoLetters)){
						//p的参数名与原参数中某一参数相同时，忽略原参数 
					}else{
						sbUrl.append(key).append(params.get(key));
					}
				}
			}
	
			//当参数长度大于1，表示参数有值，则把参数追加到url，否则不生成该参数
			//FIXME: 由于时间关系，这里暂时只处理暂数名为一个字符的情况，以后要修改成可以处理多字符的参数名
			
			if(pPrefix != "" && p.length()>1){
				//多字符的参数名处理
				String temporaryStr = p;
				Matcher f = Pattern.compile("\\d+").matcher(p);
				while(f.find()){
					temporaryStr = p.replace(f.group(), "");
				}
				if(p.length() > temporaryStr.length())
					sbUrl.append(p);	
			}
		}

		String globalUrl = (String)getRequest().getAttribute("globalUrl");
		if(StringUtil.isNotEmpty(globalUrl))
			sbUrl.insert(0, globalUrl).append("/");
		
		String url = sbUrl.toString();
		url = url.replaceAll("/{2,}", "/");
		url = url.replaceAll(":/", "://");
		if(url.endsWith("/"))
			url = url.substring(0, url.length()-1);
		try{
			write(url);

		} catch (Exception ex) { 
			throw new JspException(ex);
        } 
		if(getJspBody()!=null)
			getJspBody().invoke(null);
	}
}
