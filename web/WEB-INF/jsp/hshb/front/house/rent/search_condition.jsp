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
<c:if test="${params['f_'] ne null }">
	<c:set var="countyTwoId_cbdId" value="${fn:split(params['f_'],'_')}" />
</c:if>
<c:if test="${params['t_'] ne null }">
	<c:set var="subwayValues" value="${ fn:split(params['t_'],'_')}" />
</c:if>
<script type="text/javascript" src="${globalUrl}js/commonGround/search_condition.js"></script>
<!-- 显示查询条件 -->
<div class="x">
    <div id="searchMenuDiv" class="companyRight" style="width:100%; margin-top:12px;">
        <div id="searchMenuBar">
            <div class="itemMenu  ${subwayValues ne null ? '':'itemMenuSelected' } " onlyshowprivate="false" id="searchMenu29" menuid="29">区域</div>
            <div class="itemMenu  ${subwayValues ne null ? 'itemMenuSelected':'' } " onlyshowprivate="false" id="searchMenu30" menuid="30">地铁</div>
          <%--  <div class="itemMenu" onlyshowprivate="false" id="searchMenu31" menuid="31">车站</div>
            <div class="itemMenu" onlyshowprivate="false" id="searchMenu32" menuid="32">学校</div>
            <div class="itemMenu" onlyshowprivate="false" id="searchMenu33" menuid="33">医院</div>
             --%> 
        </div>
        <div class="clearDiv"></div>
        <div id="searchContent">
            <div id="searchItemContent">
                <div id="privateSearchItemContent">
                    <div id="privateSearchContent29" class=" ${subwayValues ne null ? 'hiddenPrivateDiv':'diaplayPrivateDiv' }">
                        <div class="itemLine">
                            <div class="searchLabelContainer">
                                <div class="searchLabelLeft"></div>
                                <div class="searchLabel" searchitemid="34">区域:</div>
                                <div class="searchLabelRight"></div>
                                <div class="clearDiv"></div>
                            </div>
                            <div class="searchFieldContentDiv" supermenuid="29" fieldscontentid="34">
                                <div class="searchField searchUnlimited ${h:containsKey(params, 'a_')?'':'selectedField'}" hassubvalue="true"
                                columnname="ddhb_one_community.region.erpId">
                                    <span><a href="<h:surl p='a__'/>">不限</a></span>
                                </div>
                                <!-- 城区 开始 -->
                                <c:if test="${hzzfSearchItems eq null }">
	                                <c:forEach var="items" items="${searchItems}" varStatus="s1" >
	                                  <c:if test="${items.id == 34 }">
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
	                            </c:if>
                                <!-- 城区 结束 -->
                                <!-- 杭州租房 区域开始  -->
                                <c:if test="${hzzfSearchItems ne null }">
                                	<c:forEach var="county" items="${hzzfSearchItems}" varStatus="s1" >
			                             <c:set var="erpId" value="${county.erpId}_" /> 	
				                         <c:set var="cssStyle" value="${h:containsEntry(params, 'f_', erpId)? 'selectedField' : ''}" />
				                         <c:if test="${countyTwoId_cbdId[0] ne null }" >
				                            <c:if test="${countyTwoId_cbdId[0] eq county.erpId }">
												<c:set var="cssStyle" value="selectedField" />		                              	
				                            </c:if>
				                         </c:if>
			                             <div class="searchField ${cssStyle }" fieldvalue="${county.erpId}">
			                                <span><a href="<h:surl p='f_${county.erpId}_' />"> ${county.countyName} </a></span><br>
			                                <img src="http://www.hshb.cn//images/search/narrow_yellow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
			                             </div>
	                                </c:forEach>
                                </c:if>
                                <!-- 杭州租房 区域结束  -->
                                <div class="clearDiv"></div>
                            </div>
                            <div class="clearDiv"></div>
                        </div>
                        <div class="subFielsDiv" subfieldscontainerid="34">
                        <c:if test="${hzzfSearchItems eq null }">
	                        <c:forEach var="items" items="${searchItems}" varStatus="s1">
	                        	<c:if test="${items.id == 34 }">
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
                        </c:if>
                        <c:if test="${hzzfSearchItems ne null }">
                        	<c:forEach var="county" items="${hzzfSearchItems}" varStatus="s1">
	                            		<!-- 杭州租房 商圈 开始 -->
			                		<c:set var="cssStyle" value="hiddenSubField" />
			                		<c:if test="${countyTwoId_cbdId[0] eq county.erpId }">
			                			<c:set var="cssStyle" value="displaySubField" />
			                		</c:if>                            	 
	                            	 <div class="${cssStyle }" subfileddivid="34_${county.erpId }">
		                                <div class="subFileAndKeyContainer">
												<!-- 二级循环 开始-->
											<c:forEach var="cbd" items="${county.cbdWebsiteList}" varStatus="s2">
												<div class="subFieldsKey">${cbd.initial}</div>
		                                   		 <div class="subFiledKeyValueDiv" subcontainerdiv="34_${county.erpId}_${cbd.initial}"></div>
		                                   			 <c:set var="cssStyle" value="" />
		                                   		   	 <c:if test="${countyTwoId_cbdId[1] eq cbd.websiteId }">
			                                   		   	 <c:set var="website_id" value="${countyTwoId_cbdId[0] }_${countyTwoId_cbdId[1] }"  />
					                                     <c:set var="cssStyle" value="${h:containsEntry(params, 'f_', website_id)? 'selectedField' : ''}" />
				                                     </c:if>
				                                    <div class="subFieldItemDiv ${cssStyle} " menuid="29" 
					                                    fieldvalue="${cbd.websiteId}">
				                                    		<a href="<h:surl p="f_${county.erpId}_${cbd.websiteId}"/>">${cbd.name}</a>
				                                    </div>
		                                    </c:forEach>
		                                        <!-- 二级循环结束 -->                            
		                                </div>  
		                              <div class="clearDiv"></div>
	                        	    </div>
	                        	    <div class="clearDiv"></div>
	                                <!-- 杭州租房 商圈 结束 -->                          
	                        </c:forEach>
                        </c:if>
                            <div class="clearDiv"></div>
                        </div>
                    </div>
                    <div id="privateSearchContent30" class="${subwayValues ne null ? 'diaplayPrivateDiv':'hiddenPrivateDiv' }">
                        <div class="itemLine">
                            <div class="searchLabelContainer">
                                <div class="searchLabelLeft"></div>
                                <div class="searchLabel" searchitemid="42">地铁:</div>
                                <div class="searchLabelRight"></div>
                                <div class="clearDiv"></div>
                            </div>
                            <div class="searchFieldContentDiv" supermenuid="30" fieldscontentid="42">
                                <div class="searchField searchUnlimited ${h:containsKey(params, 't_')?'':'selectedField'}" >
                                    <span><a href="<h:surl p='t__'/>">不限</a></span>
                                </div>
                                <!-- 地铁 开始 -->
                                <c:forEach var="items" items="${searchItems }" varStatus="s1">
	                                <c:if test="${items.id == 42 }">
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
                        <div class="subFielsDiv" subfieldscontainerid="42">
                        <!-- 地铁线路 开始 -->
                         <c:forEach var="items" items="${searchItems }" varStatus="s1">
	                         <c:if test="${items.id == 42 }">
	                            <c:forEach var="subway" items="${items.subway}" varStatus="s2">
		                        	<c:set var="cssStyle" value="hiddenSubField" />
		                			<c:if test="${subwayValues[0] eq subway.erpId }">
		                				<c:set var="cssStyle" value="displaySubField" />
		                			</c:if>
		                            <div class="${cssStyle }" subfileddivid="42_${subway.erpId}">
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
                                <div class="searchLabelLeft">
                                </div>
                                <div class="searchLabel" searchitemid="83">
                                   	 距离:
                                </div>
                                <div class="searchLabelRight"></div>
                                <div class="clearDiv"></div>
                            </div>
                            <div class="searchFieldContentDiv" supermenuid="30" fieldscontentid="83">
                                <div class="searchField searchUnlimited ${h:containsKey(params, 'd')?'':'selectedField'}" hassubvalue="false"
                                columnname="ddhb_two_distance">
                                   <span><a href="<h:surl p='d'/>">不限</a></span>
                                </div>
                                <!-- 地铁 距离 开始 -->
                                <c:forEach var="items" items="${searchItems}" varStatus="s1">
	                               <c:if test="${items.id==83}">
										<c:forEach var="field" items="${items.fields}" varStatus="s2">
								                <div class="searchField ${h:containsEntry(params, 'd', s2.count.toString())? 'selectedField' : ''} " 
								                isrange="true" 
								                isprivateitem="true" 
								                hassubvalue="false" 
								                columnname="ddhb_two_distance" 
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
                    <div id="privateSearchContent31" class="hiddenPrivateDiv">
                        <div class="itemLine">
                            <div class="searchLabelContainer">
                                <div class="searchLabelLeft"></div>
                                <div class="searchLabel" searchitemid="43">
                                   	 车站:
                                </div>
                                <div class="searchLabelRight"></div>
                                <div class="clearDiv"></div>
                            </div>
                            <div class="searchFieldContentDiv" supermenuid="31" fieldscontentid="43">
                                <div class="searchField searchUnlimited selectedField" hassubvalue="false"
                                columnname="ddhb_one_station.erpId">
                                    <span><a href="<h:surl p='c__'/>">不限</a> </span>
                                </div>
                                <!-- 车站 开始 -->
                                <c:forEach var="station" items="${stations}" varStatus="s1">
                                <div class="searchField" isprivateitem="true" hassubvalue="false" ismulty="false"
                                columnname="ddhb_one_station.erpId" fieldvalue="${station.erpId}">
                                    <span><a href="<h:surl p="t_${station.erpId}_"/>">${station.stationName}</a></span>
                                </div>
                                </c:forEach>
                                <!-- 车站 结束 -->
                                <div class="clearDiv"></div>
                            </div>
                            <div class="clearDiv"></div>
                        </div>
                        <div class="itemLine">
                            <div class="searchLabelContainer">
                                <div class="searchLabelLeft"></div>
                                <div class="searchLabel" searchitemid="84">
                                 	   距离:
                                </div>
                                <div class="searchLabelRight"></div>
                                <div class="clearDiv"></div>
                            </div>
                            <div class="searchFieldContentDiv" supermenuid="31" fieldscontentid="84">
                                <div class="searchField searchUnlimited selectedField" hassubvalue="false"
                                columnname="ddhb_two_distance">
                                    <span>不限</span>
                                </div>
                                <!-- 车站 距离 开始 -->
                              <c:forEach var="items" items="${searchItems}" varStatus="s1">
								<c:if test="${items.id==84}">
									<c:forEach var="fields" items="${items.fields}" varStatus="s2">
							                <div class="searchField" 
							                isrange="true" 
							                isprivateitem="true" 
							                hassubvalue="false" 
							                columnname="ddhb_two_distance" 
							                fieldvalue="${fields.minfieldvalue}@${fields.maxfieldvalue}"><span><a href="<h:surl p="d${dis.id}"/>">${dis.name}</a></span>
							                </div>
									</c:forEach>	
								</c:if>
							  </c:forEach>
                                <!-- 车站 距离 结束 -->
                                
                                <div class="clearDiv"></div>
                            </div>
                            <div class="clearDiv"></div>
                        </div>
                    </div>
                    <!-- 学校 医院 先放着 -->
                    <div id="privateSearchContent32" class="hiddenPrivateDiv">
                        <div class="itemLine">
                            <div class="searchLabelContainer">
                                <div class="searchLabelLeft">
                                </div>
                                <div class="searchLabel">
                                   	区域:
                                </div>
                                <div class="searchLabelRight"></div>
                                <div class="clearDiv"></div>
                            </div>
                            <div class="searchFieldContentDiv" supermenuid="32" fieldscontentid="44">
                                <div class="searchField searchUnlimited selectedField" hassubvalue="true"
                                columnname="ddhb_one_community.region.erpId">
                                    <span>不限</span>
                                </div>
                                <!-- 学校 城区 开始 -->
                                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_community.region.erpId"
                                fieldvalue="15">
                                    <span>拱墅</span>
                                    <br>
                                    <img src="http://www.hshb.cn//images/search/narrow_yellow.png" class="narrowIcon"
                                    style="display:none; width:auto; height:auto;">
                                </div>
                                <!-- 学校 城区 结束 -->
                            </div>
                            <div class="clearDiv"></div>
                        </div>
                        <div class="subFielsDiv" subfieldscontainerid="44">
                            <!-- 学校 开始循环 -->
                            <div class="hiddenSubField" subfileddivid="44_16" style="margin-bottom:5px;">
                                <div class="subFileAndKeyContainer">
                                    <div class="subFieldsKey">
                                       	江干:
                                    </div>
                                    <div class="subFiledKeyValueDiv" subcontainerdiv="44_16_">
                                    </div>
                                    <!-- 二级循环 -->
                                    <div class="subFieldItemDiv" menuid="32" parentcolunmname="ddhb_one_community.region.erpId"
                                    parentfieldvalue="16" supersubfielddivid="44_16_0" fieldcolumnname="ddhb_one_sztype.erpId"
                                    fieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74">
                                       	 幼儿园
                                    </div>
                                    <!-- 结束 -->
                                    <div class="clearDiv"></div>
                                </div>
                            </div>
                            <div class="hiddenSubField" subparentschoolgrade="44_16" subfileddivid="44_16_0">
                                <div class="subFileAndKeyContainer">
                                    <div class="subFieldsKey">
                                  		幼儿园:
                                    </div>
                                    <div class="subFiledKeyValueDiv" subcontainerdiv="44_16_0"></div>
                                    <!-- 学校 二级循环 -->
                                    <div class="subFieldItemDiv " menuid="32" parentcolunmname="ddhb_one_sztype.erpId"
                                    parentfieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74" supersubfielddivid="44_16"
                                    fieldcolumnname="ddhb_one_school.erpId" fieldvalue="25">
                                       	 杭州市京江幼儿园
                                    </div>
                                    <div class="subFieldItemDiv " menuid="32" parentcolunmname="ddhb_one_sztype.erpId"
                                    parentfieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74" supersubfielddivid="44_16"
                                    fieldcolumnname="ddhb_one_school.erpId" fieldvalue="26">
                                     	   杭州市兰苑幼儿园
                                    </div>
                                    <div class="clearDiv"></div>
                                </div>
                                <div class="clearDiv"></div>
                            </div>
                            <div class="clearDiv"></div>
                            <div class="clearDiv"></div>
                            <!-- 学校 结束循环 -->
                        </div>
                        <div class="itemLine">
                            <div class="searchLabelContainer">
                                <div class="searchLabelLeft"></div>
                                <div class="searchLabel" searchitemid="85">
                                    	距离:
                                </div>
                                <div class="searchLabelRight"></div>
                                <div class="clearDiv"></div>
                            </div>
                            <div class="searchFieldContentDiv" supermenuid="32" fieldscontentid="85">
                                <div class="searchField searchUnlimited selectedField" hassubvalue="false"
                                columnname="ddhb_two_distance">
                                    <span>不限 </span>
                                </div>
                                <!-- 学校 距离 开始-->
                                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false"
                                columnname="ddhb_two_distance" fieldvalue="0@500">
                                    <span>500米以内</span>
                                </div>
                                <!-- 学校 距离 结束 -->
                                <div class="clearDiv"></div>
                            </div>
                            <div class="clearDiv"></div>
                        </div>
                    </div>
                    <div id="privateSearchContent33" class="hiddenPrivateDiv">
                        <div class="itemLine">
                            <div class="searchLabelContainer">
                                <div class="searchLabelLeft"></div>
                                <div class="searchLabel" searchitemid="45">
                                    	医院:
                                </div>
                                <div class="searchLabelRight"></div>
                                <div class="clearDiv"></div>
                            </div>
                            <div class="searchFieldContentDiv" supermenuid="33" fieldscontentid="45">
                                <div class="searchField searchUnlimited selectedField" hassubvalue="false"
                                columnname="ddhb_one_hospital.hospitalName">
                                    <span>不限</span>
                                </div>
                                <div class="inputDiv" hassubvalue="false" style="width: 300px;">
                                    <div class="rangeContentDiv" style="width:250px;">
                                        <input type="text" privateinput="true" columnname="ddhb_one_hospital.hospitalName"
                                        class="inputElem" style="width:250px;">
                                        <span>
                                        </span>
                                    </div>
                                    <div class="toSearchIcon" unitname="" columnname="ddhb_one_hospital.hospitalName">
                                    </div>
                                </div>
                                <div class="clearDiv"></div>
                            </div>
                            <div class="clearDiv"></div>
                        </div>
                        <div class="itemLine">
                            <div class="searchLabelContainer">
                                <div class="searchLabelLeft"></div>
                                <div class="searchLabel" searchitemid="86">
                                   	 距离:
                                </div>
                                <div class="searchLabelRight"></div>
                                <div class="clearDiv"></div>
                            </div>
                            <div class="searchFieldContentDiv" supermenuid="33" fieldscontentid="86">
                                <div class="searchField searchUnlimited selectedField" hassubvalue="false"
                                columnname="ddhb_two_distance">
                                    <span>不限 </span>
                                </div>
                                <!-- 医院 距离 开始 -->
                                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false"
                                columnname="ddhb_two_distance" fieldvalue="0@500">
                                    <span>500米以内</span>
                                </div>
                                <!-- 医院 距离 结束 -->
                                <div class="clearDiv"></div>
                            </div>
                            <div class="clearDiv"></div>
                        </div>
                    </div>
                </div>
                <div id="publicSearchItemContent">
                    <div id="notHidden">
                        <div class="itemLine">
                            <div class="searchLabelContainer">
                                <div class="searchLabelLeft">
                                </div>
                                <div class="searchLabel" searchitemid="35">
                                    	租金:
                                </div>
                                <div class="searchLabelRight"></div>
                                <div class="clearDiv"></div>
                            </div>
                            <div class="searchFieldContentDiv" fieldscontentid="35">
                                <div class="searchField searchUnlimited ${h:containsKey(params, 'p')?'':'selectedField'}" hassubvalue="false"
                                columnname="ddhb_two_rentPrice">
                                    <span><a href="<h:surl p='p' />">不限</a></span>
                                </div>
                                <!-- 租金范围 开始 -->
                                <c:forEach var="items" items="${searchItems}" varStatus="s1">
									<c:if test="${items.id==35}">
										<c:forEach var="field" items="${items.fields}" varStatus="s2">
										<c:set var="_pirce" value="${field.minfieldvalue }-${field.maxfieldvalue}" />
			                                <div class="searchField ${h:containsEntry(params, 'p', _pirce)? 'selectedField' : ''}" 
			                                fieldvalue="${field.minfieldvalue}@${field.maxfieldvalue}">
			                                    <span><a href="<h:surl p="p${_pirce}"/>">${field.fieldname}</a></span>
			                                </div>
                                		</c:forEach>	
									</c:if>
								</c:forEach>
                                <!-- 租金范围结束 -->
                                 <div class="inputDiv" hassubvalue="false">
                                    <div class="rangeContentDiv">
                                        <input type="text" privateinput="false" columnname="ddhb_two_rentPrice"
                                        class="minValue">
                                        -
                                        <input type="text" privateinput="false" columnname="ddhb_two_rentPrice"
                                        class="maxValue">
                                        <span>元</span>
                                    </div>
                                    <div class="toSearchIcon" unitname="p" columnname="ddhb_two_rentPrice" jump="<h:surl p="p"/>" ></div>
                                </div>
                                <div class="clearDiv"></div>
                            </div>
                            <div class="clearDiv"></div>
                        </div>
                        <div class="itemLine">
                            <div class="searchLabelContainer">
                                <div class="searchLabelLeft"></div>
                                <div class="searchLabel" searchitemid="36">
                                    	面积:
                                </div>
                                <div class="searchLabelRight"></div>
                                <div class="clearDiv"></div>
                            </div>
                            <div class="searchFieldContentDiv" fieldscontentid="36">
                                <div class="searchField searchUnlimited ${h:containsKey(params, 'a')?'':'selectedField'}" hassubvalue="false"
                                columnname="ddhb_two_area">
                                    <span><a href="<h:surl p="a"/>">不限</a></span>
                                </div>
                                <!-- 面积 范围开始 -->
                                <c:forEach var="items" items="${searchItems}" varStatus="s1">
									<c:if test="${items.id==36}">
										<c:forEach var="field" items="${items.fields}" varStatus="s2">
                                		<c:set var="_area" value="${field.minfieldvalue}-${field.maxfieldvalue}" /> 
                                		<div class="searchField ${h:containsEntry(params, 'a', _area)? 'selectedField' : ''} " 
                                		columnname="ddhb_two_area" fieldvalue="${field.minfieldvalue}@${field.maxfieldvalue}">
		                                    <span><a href="<h:surl p="a${_area}"/>">${field.fieldname}</a></span>
		                                </div>
                                		</c:forEach>
									</c:if>
								</c:forEach>
                                <!-- 面积范围结束 -->
                                <div class="inputDiv" hassubvalue="false">
                                    <div class="rangeContentDiv">
                                        <input type="text" privateinput="false" columnname="ddhb_two_area" class="minValue">
                                        -
                                        <input type="text" privateinput="false" columnname="ddhb_two_area" class="maxValue">
                                        <span>平米</span>
                                    </div>
                                    <div class="toSearchIcon" unitname="a" columnname="ddhb_two_area" jump="<h:surl p="a"/>" ></div>
                                </div>
                                <div class="clearDiv"></div>
                            </div>
                            <div class="clearDiv"></div>
                        </div>
                        <div class="itemLine">
                            <div class="searchLabelContainer">
                                <div class="searchLabelLeft">
                                </div>
                                <div class="searchLabel" searchitemid="37">
                                    	居室:
                                </div>
                                <div class="searchLabelRight"></div>
                                <div class="clearDiv"></div>
                            </div>
                            <div class="searchFieldContentDiv" fieldscontentid="37">
                                <div class="searchField searchUnlimited ${h:containsKey(params, 'r')?'':'selectedField'}" hassubvalue="false"
                                columnname="ddhb_one_shi">
                                    <span><a href="<h:surl p="r"/>">不限</a></span>
                                </div>
                                <!-- 居室 范围 开始 -->
                               <c:forEach var="items" items="${searchItems}" varStatus="s1">
									<c:if test="${items.id==37}">
										<c:forEach var="field" items="${items.fields}" varStatus="s2">
											<c:set var="_shi" value="${field.id}-${field.id}" />
								                <div class="searchField ${h:containsEntry(params, 'r', _shi)? 'selectedField' : ''}" 
								                	isprivateitem="false" 
								                	hassubvalue="false" 
								                	ismulty="false" 
								                	columnname="ddhb_one_shi" 
								                	fieldvalue="${field.id}"><span><a href="<h:surl p="r${_shi}"/>">${field.fieldname}</a></span></div>
										</c:forEach>
									</c:if>
								</c:forEach>
                                <!-- 居室 范围 结束 -->
                                <div class="clearDiv"></div>
                            </div>
                            <div class="clearDiv"></div>
                        </div>
                    </div>
                    <div id="isSelectList">
                        <div class="searchLabelLeft"></div>
                        <div class="searchLabel" style="float:left; padding-right:5px;width:50px;margin-right:5px;text-align:right;">
                            	筛选:
                        </div>
                        <div class="itemLint xiala xl_w90" style="float:left; padding-bottom:15px;">
                            <select name="ddhb_two_community.startSaleDate" columnname="ddhb_two_community.startSaleDate"
                            searchitemid="38" id="38" >
                                <option value="<h:surl p='y'/>">房齡</option>
                                <!-- 房龄 年限 开始 -->
                                <c:forEach var="items" items="${searchItems}" varStatus="s1">
									<c:if test="${items.id==38}">
										<c:forEach var="field" items="${items.fields}" varStatus="s2">
										<option value="<h:surl p="y${s2.count}"/>" ${h:containsEntry(params, "y", s2.count.toString())? "selected='selected'" :""} >${field.fieldname}</option>
										</c:forEach>
									</c:if>
								</c:forEach>
                                <!-- 房龄 年限 结束 -->
                            </select>
                        </div>
                        <div class="itemLint xiala xl_w90" style="float:left; padding-bottom:15px;">
                            <select name="ddhb_one_orientations.id" 
                            columnname="ddhb_one_orientations.id"
                            searchitemid="39" id="39" >
                                <option value="<h:surl p='f'/>">朝向</option>
                                <!-- 朝向 开始 -->
                                <c:forEach var="items" items="${searchItems}" varStatus="s1">
									<c:if test="${items.id==39}">
										<c:forEach var="field" items="${items.fields}" varStatus="s2">
										<c:set var="count" value="${s2.count} " />
												<option value="<h:surl p="f${field.id}"/>" ${h:containsEntry(params, "f", count.trim())? "selected='selected'" :""} >${field.fieldname}</option>
										</c:forEach>
									</c:if>
								</c:forEach>
                                <!-- 朝向 结束 -->
                            </select>
                        </div>
                        <div class="itemLint xiala xl_w90" style="float:left; padding-bottom:15px;">
                            <select name="ddhb_one_decoration.erpId" columnname="ddhb_one_decoration.erpId"
                            searchitemid="40" id="40" >
                               <option value="<h:surl p="z${fields.id}"/>">装修</option>
                                <c:forEach var="items" items="${searchItems}" varStatus="s1">
									<c:if test="${items.id==40}">
										<c:forEach var="field" items="${items.fields}" varStatus="s2">
										<c:set var="count" value="${s2.count} " />
											<option value="<h:surl p="z${field.id}"/>" ${h:containsEntry(params, "z", count.trim())? "selected='selected'" :""} >${field.fieldname}</option>
										</c:forEach>
									</c:if>
								</c:forEach>
                            </select>
                        </div>
                        <div class="itemLint xiala xl_w90" style="float:left; padding-bottom:15px;">
                            <select name="ddhb_one_rentType.id" 
                            columnname="ddhb_one_rentType.id"
                            searchitemid="80" id="80" >
                                <option value="<h:surl p='xs' />">租赁形式</option>
                                 <c:forEach var="items" items="${searchItems}" varStatus="s1">
									<c:if test="${items.id==80}">
										<c:forEach var="field" items="${items.fields}" varStatus="s2">
										<c:set var="count" value="${s2.count} " />
											<option value="<h:surl p="xs${field.id}"/>" ${h:containsEntry(params, "xs", count.trim())? "selected='selected'" :""} >${field.fieldname}</option>
										</c:forEach>
									</c:if>
								</c:forEach>
                            </select>
                        </div>
                        <div class="itemLint xiala xl_w90" style="float:left; padding-bottom:15px;">
                            <select name="ddhb_one_furniture" columnname="ddhb_one_furniture" searchitemid="81"
                            id="81" >
                                <option value="<h:surl p='pz' />">配置</option>
                                <!-- 房屋配置信息 开始 -->
                                <c:forEach var="items" items="${searchItems}" varStatus="s1">
									<c:if test="${items.id==81}">
										<c:forEach var="field" items="${items.fields}" varStatus="s2">
										<c:set var="_peizhiId" value="${field.id}" />
											<option value="<h:surl p="pz${field.id}"/>" ${h:containsEntry(params, "pz", _peizhiId.toString())? "selected='selected'" :""} >${field.fieldname}</option>
										</c:forEach>
									</c:if>
								</c:forEach>
                                <!-- 房屋配置信息 结束 -->
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
						   	<a href="${ globalUrl}${item.minfieldvalue }"><img src='${globalUrl}/images/search/deleteButton.png' /></a>
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
                    id="saveContainer" condition="<h:surl p='e'/>" type="chuzu">
                        <div class="oprationIcon ${buttonDisable ne true?'saveIcon': 'disabledSaveIcon'}"></div>
                        <div class="operationLabel">保存</div>
                        <div class="clearDiv"></div>
                    </div>
                    <div class="operationItemContainer ${buttonDisable ne true?'anabledOperation': 'disableOperation'}" buttonDisable="${buttonDisable}"
                    id="clearContainer" jump="${globalUrl }chuzu">
                        <div class="oprationIcon ${buttonDisable ne true?'clearIcon': 'disabledClearIcon'}"></div>
                        <div class="operationLabel">清除</div>
                        <div class="clearDiv"></div>
                    </div>
                    <div class="clearDiv"></div>
                    <div id="sharePanel" style="height:30px; float:right; display:none;">
                        <div id="shareToMobile" style="float:left;">
                            <a title="分享到手机" style="margin-top:10px;" href="#">
                                <img src="http://www.hshb.cn/images/search/shareToMobile.png" style="margin-top: 5px;">
                            </a>
                        </div>
                        <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare" data="{&quot;text&quot;:"
                        '}'="">
                            <a class="bds_qzone" onmousedown="startToShare()" title="分享到QQ空间" href="#">
                            </a>
                            <a class="bds_tsina" onmousedown="startToShare()" title="分享到新浪微博" href="#">
                            </a>
                            <a class="bds_tqq" onmousedown="startToShare()" title="分享到腾讯微博" href="#">
                            </a>
                            <a class="bds_sqq" onmousedown="startToShare()" title="分享到QQ好友" href="#">
                            </a>
                        </div>
                    </div>
                </div>
                <div class="clearDiv"></div>
            </div>
            <div class="clearDiv"></div>
            <div style="clear:both;"></div>
        </div>
    </div>
</div>