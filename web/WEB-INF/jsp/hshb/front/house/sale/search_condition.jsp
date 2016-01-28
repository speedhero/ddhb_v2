<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="h" uri="/WEB-INF/tld/hshb.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${params['a_'] ne null }">
	<c:set var="countyId_cbdId" value="${fn:split(params['a_'],'_')}" />
</c:if>
<c:if test="${params['t_'] ne null }">
	<c:set var="subwayValues" value="${ fn:split(params['t_'],'_')}" />
</c:if>
<%-- 小区,二手房,租赁 共同所需要的js --%>
<script type="text/javascript" src="${globalUrl}js/commonGround/search_condition.js"></script>
<div class="x">
  <div id="searchMenuDiv" class="companyRight" style="width:100%; margin-top:12px;">
    <div id="searchMenuBar">
      <div class="itemMenu ${subwayValues ne null ? '':'itemMenuSelected' }" onlyshowprivate="false" id="searchMenu12" menuid="12">区域</div>
      <div class="itemMenu ${subwayValues ne null ? 'itemMenuSelected':'' }" onlyshowprivate="false" id="searchMenu13" menuid="13">地铁</div>
     <%--  <div class="itemMenu" onlyshowprivate="false" id="searchMenu14" menuid="14">学区</div>
      <div class="itemMenu" onlyshowprivate="false" id="searchMenu15" menuid="15">预算</div>
       --%>
    </div>
    <div class="clearDiv"></div>
    <div id="searchContent">
      <div id="searchItemContent">
        <div id="privateSearchItemContent">
        
        	<%--区域 --%>
          <div id="privateSearchContent12" class="${subwayValues ne null ? 'hiddenPrivateDiv':'diaplayPrivateDiv' }">
            <div class="itemLine">
              <div class="searchLabelContainer">
                <div class="searchLabelLeft"></div>
                <div class="searchLabel" searchitemid="16">区域:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" supermenuid="12" fieldscontentid="16">
                <div class="searchField searchUnlimited ${h:containsKey(params, 'a_')?'':'selectedField'}" hassubvalue="true" columnname="ddhb_one_community.region.erpId">
                  <span><a href="<h:surl p="a__"/>">不限</a></span>
                </div>
				<c:forEach var="items" items="${searchItems}" varStatus="s1" >
                    <c:if test="${items.id == 16 }">
                       <c:forEach var="county" items="${items.county }" varStatus="s2">
		                   <c:set var="erpId" value="${county.erpId}_" /> 	
			                 <c:set var="cssStyle" value="${h:containsEntry(params, 'a_', erpId)? 'selectedField' : ''}" />
							 <c:if test="${countyId_cbdId[0] ne null }" >
							 <c:if test="${countyId_cbdId[0] eq county.erpId }">
							 <c:set var="cssStyle" value="selectedField" />		                              	
			                 </c:if>
			                 </c:if>
		                     <div class="searchField ${cssStyle }" 
		                     fieldvalue="${county.erpId}">
		                     <span><a href="<h:surl p='a_${county.erpId}_' />"> ${county.countyName} </a></span><br>
		                     <img src="http://www.hshb.cn//images/search/narrow_yellow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
		                     </div>
	                         </c:forEach>
	                         </c:if>
                             </c:forEach>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
            <%-- 商圈 --%>
            <div class="subFielsDiv" subfieldscontainerid="16"> <%--这里的 16是原来表中定义查询条件大类ID，本次改成不用数据库定义查询条件 --%>
                        <c:forEach var="items" items="${searchItems}" varStatus="s1">
                        	<c:if test="${items.id == 16 }">
                           
                            		<!-- 商圈 开始 -->
                            	<c:forEach var="county" items="${items.county}" varStatus="s2">
		                				<c:set var="cssStyle" value="hiddenSubField" />
		                			<c:if test="${countyId_cbdId[0] eq county.erpId }">
		                				<c:set var="cssStyle" value="displaySubField" />
		                			</c:if>                            	 
                            	 <div class="${cssStyle }" subfileddivid="34_${county.erpId }">
	                                <div class="subFileAndKeyContainer">
											<!-- 二级循环 开始-->
										<c:forEach var="cbd" items="${county.websiteCBD}" varStatus="s2">
											<div class="subFieldsKey">${cbd.initial}</div>
	                                   		 <div class="subFiledKeyValueDiv" subcontainerdiv="34_${county.erpId}_${cbd.initial}"></div>
	                                   			 <c:set var="cssStyle" value="" />
	                                   		   	 <c:if test="${countyId_cbdId[1] eq cbd.websiteId }">
		                                   		   	 <c:set var="website_id" value="${countyId_cbdId[0] }_${countyId_cbdId[1] }"  />
				                                     <c:set var="cssStyle" value="${h:containsEntry(params, 'a_', website_id)? 'selectedField' : ''}" />
			                                     </c:if>
			                                    <div class="subFieldItemDiv ${cssStyle} " menuid="29" 
				                                    fieldvalue="${cbd.websiteId}">
			                                    		<a href="<h:surl p="a_${county.erpId}_${cbd.websiteId}"/>">${cbd.name}</a>
			                                    </div>
	                                    </c:forEach>
	                                        <!-- 二级循环结束 -->                            
	                                </div>  
	                              <div class="clearDiv"></div>
                        	    </div>
                        	    <div class="clearDiv"></div>
                                </c:forEach> 
                                <!-- 商圈 结束 -->                          
                              
                            </c:if>
                        </c:forEach>
                            <div class="clearDiv"></div>
            </div>
          </div>
          <%--区域结束 --%>
          
          <%--地铁 --%>
          <div id="privateSearchContent13" class="${subwayValues ne null ? 'diaplayPrivateDiv':'hiddenPrivateDiv' }">
            <div class="itemLine">
              <div class="searchLabelContainer">
                <div class="searchLabelLeft"></div>
                <div class="searchLabel" searchitemid="24">地铁:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" supermenuid="13" fieldscontentid="24">
                <div class="searchField searchUnlimited ${h:containsKey(params, 't_')?'':'selectedField'}" hassubvalue="true" columnname="ddhb_one_subwayline.erpId">
                	<span><a href="<h:surl p="t__"/>">不限</a></span>
                </div>
<!-- 地铁 开始 -->
                                <c:forEach var="items" items="${searchItems }" varStatus="s1">
	                                <c:if test="${items.id == 24 }">
		                                <c:forEach var="subway" items="${items.subway}" varStatus="s2">
			                                <c:set var="cssStyle" value="" />
			                                <c:if test="${subwayValues[0] eq subway.erpId }">
			                                	<c:set var="cssStyle" value="${h:containsEntry(params, 't_', params['t_'])? 'selectedField' : ''}" />
			                                </c:if>
			                                <div class="searchField  ${cssStyle }" hassubvalue="true" fieldvalue="${subway.erpId}">
			                                    <span><a href="<h:surl p="t_${subway.erpId}_"/>">${subway.subwayName}</a></span><br>
			                                    <img src="http://www.hshb.cn//images/search/narrow_yellow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
			                                </div>
		                                </c:forEach>
	                                </c:if>
                                </c:forEach>
                                <!-- 地铁 结束 -->
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
            <div class="subFielsDiv" subfieldscontainerid="24"> <%--这里的24是原来表中定义查询条件大类ID，本次改成不用数据库定义查询条件 --%>
				                        <!-- 地铁线路 开始 -->
                         <c:forEach var="items" items="${searchItems }" varStatus="s1">
	                         <c:if test="${items.id == 24 }">
	                            <c:forEach var="subway" items="${items.subway}" varStatus="s2">
		                        	<c:set var="cssStyle" value="hiddenSubField" />
		                			<c:if test="${subwayValues[0] eq subway.erpId }">
		                				<c:set var="cssStyle" value="displaySubField" />
		                			</c:if>
		                            <div class="${cssStyle }" subfileddivid="24_${subway.erpId}">
		                                <div class="subFileAndKeyContainer">
		                                    <div class="subFieldsKey"></div>
		                                    <div class="subFiledKeyValueDiv" subcontainerdiv="42_${subway.erpId}__"></div>
		                                       <c:forEach var="station" items="${subway.subwayStation}" varStatus="s3">
		                                        <c:set var="cssStyle" value="" />
		                                        <c:if test="${subwayValues[1] eq station.erpId }">
		                                        	<c:set var="cssStyle" value="selectedField" />
		                                        </c:if>
		                                        <div class="subFieldItemDiv ${cssStyle }"  fieldvalue="${station.erpId}">
		                                           	 <a href="<h:surl p="t_${subway.erpId}_${station.erpId}"/>">${station.stationName}</a>
		                                        </div>
		                                        </c:forEach>
		                                    <div class="clearDiv"></div>
		                                </div>
		                                <div class="clearDiv"></div>
		                            </div>
	                           		 <div class="clearDiv"></div>
	                            </c:forEach>
	                          </c:if>
	                          
                         </c:forEach>
                         <!-- 地铁线路结束 -->
              <div class="clearDiv"></div>
            </div>
            
            <div class="itemLine">
              <div class="searchLabelContainer">
                <div class="searchLabelLeft"></div>
                <div class="searchLabel" searchitemid="79">距离:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" supermenuid="13" fieldscontentid="79">
                <div class="searchField searchUnlimited ${h:containsKey(params, 'd')?'':'selectedField'}" 
                hassubvalue="false" 
                columnname="ddhb_two_distance"><span><a href="<h:surl p='d'/>">不限</a></span></div>
 <!-- 地铁 距离 开始 -->
                                <c:forEach var="items" items="${searchItems}" varStatus="s1">
	                               <c:if test="${items.id==79}">
										<c:forEach var="field" items="${items.fields}" varStatus="s2">
								                <div class="searchField ${h:containsEntry(params, 'd', s2.count.toString())? 'selectedField' : ''} " 
								                fieldvalue="${field.minfieldvalue}@${field.maxfieldvalue}"><span><a href="<h:surl p="d${s2.count}"/>">${field.fieldname}</a></span>
								                </div>
										</c:forEach>	
									</c:if>
                                </c:forEach>
                               <!-- 地铁 距离 结束 -->
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
          </div>
          <%--地铁结束 --%>
          
					<%--学区条件，暂时不处理! --%>
          
          <%--贷款预算条件暂不处理!  --%>
        </div>
        <%-- privateSearchItemContent结束 --%>
        
        
        <div id="publicSearchItemContent">
          <div id="notHidden">

          	<%--价格 --%>
            <div class="itemLine">
              <div class="searchLabelContainer">
                <div class="searchLabelLeft"></div>
                <div class="searchLabel" searchitemid="17">价格:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" fieldscontentid="17">
                <div class="searchField searchUnlimited ${h:containsKey(params, 'p')?'':'selectedField'}" 
                hassubvalue="false" 
								olumnname="ddhb_two_price"><span><a href="<h:surl p="p"/>">不限</a></span></div>
<c:forEach var="items" items="${searchItems}" varStatus="s1">
	<c:if test="${items.id==17}">
		<c:forEach var="fields" items="${items.fields}" varStatus="s2">
								<c:set var="_price" value="${fields.minfieldvalue }-${fields.maxfieldvalue}" />
									<c:if test="${fields.minfieldvalue eq 500}">
									<c:set var="_price" value="${fields.minfieldvalue }-" />
								</c:if>
								<div class="searchField ${h:containsEntry(params, 'p', _price)? 'selectedField' : ''}" 
									isrange="true" 
									isprivateitem="false" 
									hassubvalue="false" 
									columnname="ddhb_two_price" 
									fieldvalue="${fields.minfieldvalue}@${fields.maxfieldvalue}">
									<span><a href="<h:surl p="p${_price}"/>">${fields.fieldname}</a></span>
								</div>		
		</c:forEach>	
	</c:if>
</c:forEach>
                <div class="inputDiv" hassubvalue="false">
                  <div class="rangeContentDiv">
                    <input type="text" privateinput="false" columnname="ddhb_two_price" class="minValue">-<input type="text" privateinput="false" columnname="ddhb_two_price" class="maxValue">
                    <span onclick="setPriceRange();">万元</span>
                  </div>
                  <div class="toSearchIcon" unitname="p" jump="<h:surl p="p"/>" columnname="ddhb_two_price">
                  </div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
            <%--价格结束 --%>
            
            <%--面积 --%>
            <div class="itemLine">
              <div class="searchLabelContainer">
                <div class="searchLabelLeft"></div>
                <div class="searchLabel" searchitemid="18">面积:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" fieldscontentid="18">
                <div class="searchField searchUnlimited ${h:containsKey(params, 'a')?'':'selectedField'} " 
                hassubvalue="false" 
                columnname="ddhb_two_area"><span><a href="<h:surl p="a"/>">不限</a></span></div>
<c:forEach var="items" items="${searchItems}" varStatus="s1">
	<c:if test="${items.id==18}">
		<c:forEach var="fields" items="${items.fields}" varStatus="s2">
            	<c:set var="_area" value="${fields.minfieldvalue}-${fields.maxfieldvalue}" />
                <c:if test="${fields.minfieldvalue eq 300 }">
                	<c:set var="_area" value="${fields.minfieldvalue}-" />
                </c:if>    
                <div class="searchField ${h:containsEntry(params, 'a', _area)? 'selectedField' : ''} " 
                	isrange="true" 
                	isprivateitem="false" 
                	hassubvalue="false" 
                	columnname="ddhb_two_area" 
                	fieldvalue="${fields.minfieldvalue}@${fields.maxfieldvalue}">
                	<span>
                		<a href="<h:surl p="a${_area}"/>">${fields.fieldname}</a>
                	</span></div>
		</c:forEach>
	</c:if>
</c:forEach>
                <div class="inputDiv" hassubvalue="false">
                  <div class="rangeContentDiv">
                    <input type="text" privateinput="false" columnname="ddhb_two_area" class="minValue"> - <input type="text" privateinput="false" columnname="ddhb_two_area" class="maxValue"><span>平米</span>
                    </div>
                  <div class="toSearchIcon"  unitname="a" jump="<h:surl p="a"/>" columnname="ddhb_two_area"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
            
            <%--居室 --%>
            <div class="itemLine">
              <div class="searchLabelContainer">
                <div class="searchLabelLeft"></div>
                <div class="searchLabel" searchitemid="19">居室:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" fieldscontentid="19">
                <div class="searchField searchUnlimited ${h:containsKey(params, 'r')?'':'selectedField'}" 
                	hassubvalue="false" 
                	columnname="ddhb_one_shi"><span><a href="<h:surl p="r"/>">不限</a></span></div>
<c:forEach var="items" items="${searchItems}" varStatus="s1">
	<c:if test="${items.id==19}">
		<c:forEach var="fields" items="${items.fields}" varStatus="s2">
				<c:set var="_shi" value="${fields.id}-${fields.id}" />
                <div class="searchField ${h:containsEntry(params, 'r', _shi)? 'selectedField' : ''}" 
                	isprivateitem="false" 
                	hassubvalue="false" 
                	ismulty="false" 
                	columnname="ddhb_one_shi" 
                	fieldvalue="${fields.id}"><span><a href="<h:surl p="r${_shi}"/>">${fields.fieldname}</a></span></div>
		</c:forEach>
	</c:if>
</c:forEach>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
          </div>
          
          <div id="isSelectList">
            <div class="searchLabelLeft"></div>
            <div class="searchLabel" style="float:left; padding-right:5px;width:50px;margin-right:5px;text-align:right;">筛选:</div>
            
            <div class="itemLint xiala xl_w90" style="float:left; padding-bottom:15px;">
              <select name="ddhb_two_community.startSaleDate" 
              	columnname="ddhb_two_community.startSaleDate" 
              	searchitemid="20" id="20" >
                <option value="<h:surl p="y"/>">房龄</option>
					<c:forEach var="items" items="${searchItems}" varStatus="s1">
						<c:if test="${items.id==20}">
							<c:forEach var="fields" items="${items.fields}" varStatus="s2">
								<option value="<h:surl p="y${s2.count}"/>" ${h:containsEntry(params, "y", s2.count.toString())? "selected='selected'" :""} >${fields.fieldname}</option>
							</c:forEach>
						</c:if>
					</c:forEach>
              </select>
            </div>
            
            <div class="itemLint xiala xl_w90" style="float:left; padding-bottom:15px;">
              <select name="ddhb_one_orientations.id" 
              	columnname="ddhb_one_orientations.id" 
              	searchitemid="21" id="21" >
                <option value="<h:surl p="f"/>">朝向</option>
					<c:forEach var="items" items="${searchItems}" varStatus="s1">
						<c:if test="${items.id==21}">
							<c:forEach var="fields" items="${items.fields}" varStatus="s2">
								<option value="<h:surl p="f${fields.id}"/>" ${h:containsEntry(params, "f", s2.count.toString())? "selected='selected'" :""}>${fields.fieldname}</option>
							</c:forEach>
						</c:if>
					</c:forEach>
              </select>
            </div>

            <div class="itemLint xiala xl_w90" style="float:left; padding-bottom:15px;">
              <select name="ddhb_one_decoration.erpId" 
	              columnname="ddhb_one_decoration.erpId" 
	              searchitemid="22" id="22" >
                <option value="<h:surl p="z${fields.id}"/>">装修</option>
				<c:forEach var="items" items="${searchItems}" varStatus="s1">
					<c:if test="${items.id==22}">
						<c:forEach var="fields" items="${items.fields}" varStatus="s2">
							<option value="<h:surl p="z${fields.id}"/>" ${h:containsEntry(params, "z", s2.count.toString())? "selected='selected'" :""} >${fields.fieldname}</option>
						</c:forEach> 
					</c:if>
				</c:forEach>
              </select>
            </div>
            <div class="itemLint xiala xl_w90" style="float:left; padding-bottom:15px;">
              <select name="ddhb_one_tags" 
	              columnname="ddhb_one_tags" 
	              searchitemid="23" id="23" >
                <option value="<h:surl p="t"/>">卖点</option>
				<c:forEach var="items" items="${searchItems}" varStatus="s1">
					<c:if test="${items.id==23}">
						<c:forEach var="fields" items="${items.fields}" varStatus="s2">
							<option value="<h:surl p="t${fields.id}"/>" ${h:containsEntry(params, "t", fields.id.toString())? "selected='selected'" :""} >
							${fields.fieldname}</option>
						</c:forEach>
					</c:if>
				</c:forEach>
              </select>
            </div>
          </div>
        </div>
      </div>
      
      <div class="clearDiv"></div>
      <div id="selectedSearchItemPanel">
        <div id="allSelectedTitle">本次找房条件:</div>
        <div id="allSelectedItemsDiv">&nbsp;
         <%-- buttonDisable: 用于清除/分享/保存  按钮是否为可用 true禁用、false可以用 --%>
            <c:set var="buttonDisable" value="true" />
			<c:forEach items="${searchResults}" var="item" varStatus="s1">
                <c:set var="buttonDisable" value="false" />
                <div class="selectedSearchItemDiv">
					<div class="columnDiv" >${item.fieldname}</div>
					<div class="removeColumnDiv">
						 <a href="${globalUrl}${item.minfieldvalue }"><img src='${globalUrl}/images/search/deleteButton.png' /></a>
					</div>
					<div class="clearDiv"></div>
				</div>
			</c:forEach>      
		</div>
        <div id="operationDiv">
   			<div class="operationItemContainer ${buttonDisable ne true?'anabledOperation': 'disableOperation'} " buttonDisable="${buttonDisable}"
                id="shareContainer" >
				<div class="oprationIcon ${buttonDisable ne true?'shareIcon': 'disabledShareIcon'}"></div>
				<div class="operationLabel">分享 </div>
				<div class="clearDiv"></div>
			</div>
			<div class="operationItemContainer ${buttonDisable ne true?'anabledOperation': 'disableOperation'}" buttonDisable="${buttonDisable}"
				id="saveContainer" condition="<h:surl p='e'/>" type="chushou">
				<div class="oprationIcon ${buttonDisable ne true?'saveIcon': 'disabledSaveIcon'}"></div>
				<div class="operationLabel">保存</div>
				<div class="clearDiv"></div>
			</div>
			<div class="operationItemContainer ${buttonDisable ne true?'anabledOperation': 'disableOperation'}" buttonDisable="${buttonDisable}"
				id="clearContainer" jump="${globalUrl }chushou">
				<div class="oprationIcon ${buttonDisable ne true?'clearIcon': 'disabledClearIcon'}"></div>
				<div class="operationLabel">清除</div>
				<div class="clearDiv"></div>
			</div>      
          <div class="clearDiv"></div>
          <div id="sharePanel" style="height:30px; float:right; display:none;">
            <div id="shareToMobile" style="float:left;">
              <a title="分享到手机" style="margin-top:10px;" href="#">
                <img src="${globalUrl}/images/search/shareToMobile.png" style="margin-top: 5px;">
              </a>
            </div>
            <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare" data="{&quot;text&quot;:"'}'="">
              <a class="bds_qzone" onmousedown="startToShare()" title="分享到QQ空间" href="#"></a>
              <a class="bds_tsina" onmousedown="startToShare()" title="分享到新浪微博" href="#"></a>
              <a class="bds_tqq" onmousedown="startToShare()" title="分享到腾讯微博" href="#"></a>
              <a class="bds_sqq" onmousedown="startToShare()" title="分享到QQ好友" href="#"></a>
            </div>
          </div>
        </div>
        <div class="clearDiv"></div>
      </div>
      <div class="clearDiv"></div>
      <div style="clear:both;">
      </div>
    </div>
  </div>
</div>

