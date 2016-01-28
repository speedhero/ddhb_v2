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
<c:if test="${params['q_'] ne null }">
	<c:set var="letters" value="${ fn:split(params['q_'],'_')}" />
</c:if>
<script type="text/javascript" src="${globalUrl}js/commonGround/search_condition.js"></script>
<c:set var="searchResult" value="" />
<div>
  <div class="x">
    <div id="searchMenuDiv" class="companyRight" style="width:100%;">
      <div id="searchMenuBar">
        <div class="itemMenu ${(subwayValues eq null && letters eq null)?'itemMenuSelected':'' }"  id="searchMenu47" menuid="47">区 域 </div>
        <div class="itemMenu ${subwayValues ne null?'itemMenuSelected':'' }"  id="searchMenu48" menuid="48">地 铁 </div>
        <div class="itemMenu ${letters ne null?'itemMenuSelected':''}"  id="searchMenu50" menuid="50">字母排序</div>
      </div>
      <div class="clearDiv">
      </div>
      <div id="searchContent">
        <div id="searchItemContent">
          <div id="privateSearchItemContent">
            <div id="privateSearchContent47" class="${(subwayValues eq null && letters eq null)?'diaplayPrivateDiv':'hiddenPrivateDiv' }">
              <div class="itemLine">
                <div class="searchLabelContainer">
                  <div class="searchLabelLeft">
                  </div>
                  <div class="searchLabel" searchitemid="51">区域:</div>
                  <div class="searchLabelRight"></div>
                  <div class="clearDiv"></div>
                </div>
                <div class="searchFieldContentDiv" supermenuid="47" fieldscontentid="51">
                  <div class="searchField searchUnlimited ${h:containsKey(params, 'a_')?'':'selectedField'}" hassubvalue="true"
                  columnname="ddhb_one_region.erpId">
                    <span><a href="<h:surl p='a__'/>">不限</a></span>
                  </div>
                  <!-- 城区 开始 -->
                  
                  <c:forEach var="items" items="${searchItems}" varStatus="s1" >
                  	<c:if test="${items.id == 51 }">
                  		<c:forEach var="county" items="${items.county }" varStatus="s2">
                  			<c:set var="erpId" value="${county.erpId}_" /> 
		                 	<c:set var="cssStyle" value="${h:containsEntry(params, 'a_', erpId)? 'selectedField' : ''}" />
			                    <c:if test="${countyId_cbdId[0] eq county.erpId }">
									<c:set var="cssStyle" value="selectedField" />		                              	
			                    </c:if>
		                    <div class="searchField ${cssStyle }">
		                    	<span><a href="<h:surl p='a_${county.erpId}_' />"> ${county.countyName} </a></span><br>
		                    	<img src="http://www.hshb.cn//images/search/narrow_green.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
		                    </div>
		                </c:forEach>
	                </c:if>
                  </c:forEach>
                  <!-- 城区 结束 -->
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="subFielsDiv" subfieldscontainerid="51">
                <!-- 商圈 一级循环 开始 -->
                <c:forEach var="items" items="${searchItems}" varStatus="s1">
                	<c:if test="${items.id == 51 }">
                		<c:forEach var="county" items="${items.county}" varStatus="s2">
                				<c:set var="cssStyle" value="hiddenSubField" />
                			<c:if test="${countyId_cbdId[0] eq county.erpId }">
                				<c:set var="cssStyle" value="displaySubField" />
                			</c:if>
				                <div class="${cssStyle }" ">
				                  <div class="subFileAndKeyContainer">
				                  <!-- 商圈 三级循环 开始 -->
					                  <c:forEach var="cbd" items="${county.websiteCBD}" varStatus="s3">
					                    <div class="subFieldsKey">${cbd.initial}</div>
					                    <div class="subFiledKeyValueDiv" subcontainerdiv="51_${county.erpId}_${cbd.initial}"></div>
					                    <c:set var="cssStyle" value="" />
	                                   		<c:if test="${countyId_cbdId[1] eq cbd.websiteId }">
		                                   		 <c:set var="website_id" value="${countyId_cbdId[0] }_${countyId_cbdId[1] }"  />
				                                 <c:set var="cssStyle" value="${h:containsEntry(params, 'a_', website_id)? 'selectedField' : ''}" />
			                                 </c:if>
					                    <div class="subFieldItemDiv ${cssStyle}" >
					                      	<a href="<h:surl p="a_${county.erpId}_${cbd.websiteId}"/>">${cbd.name}</a>
					                    </div>
					                  </c:forEach>
				                    <!-- 商圈  三级循环 结束 -->
				                    <div class="clearDiv"></div>
				                  </div>
				                  <div class="clearDiv"></div>
				                </div>
			            </c:forEach>
			                <div class="clearDiv"></div>
			    	</c:if>
                </c:forEach>
                <!-- 商圈 一级循环 结束 -->
              </div>
            </div>
            <div id="privateSearchContent48" class="${subwayValues ne null?'diaplayPrivateDiv':'hiddenPrivateDiv' }">
              <div class="itemLine">
                <div class="searchLabelContainer">
                  <div class="searchLabelLeft"></div>
                  <div class="searchLabel" searchitemid="55">地铁:</div>
                  <div class="searchLabelRight"></div>
                  <div class="clearDiv"></div>
                </div>
                <div class="searchFieldContentDiv" supermenuid="48" fieldscontentid="55">
                  <div class="searchField searchUnlimited ${h:containsKey(params, 't_')?'':'selectedField'}" >
                    <span><a href="<h:surl p='t__'/>">不限</a></span>
                  </div>
                  <!-- 地铁 线路 开始 -->
                  <c:forEach var="items" items="${searchItems }" varStatus="s1">
                  	<c:if test="${items.id == 55 }">
                  		<c:forEach var="subway" items="${items.subway}" varStatus="s2">
		        		  <c:set var="erpId" value="${subway.erpId}_" />
			              <c:set var="cssStyle" value="" />
			              <c:if test="${subwayValues[0] eq subway.erpId }">
			               	<c:set var="cssStyle" value="${h:containsEntry(params, 't_', params['t_'])? 'selectedField' : ''}" />
			              </c:if>          
		                  <div class="searchField ${cssStyle}"  fieldvalue="${subway.erpId}">
		                    <span><a href="<h:surl p="t_${subway.erpId}_"/>">${subway.subwayName}</a></span><br>
		                    <br>
		                    <img src="http://www.hshb.cn//images/search/narrow_green.png" class="narrowIcon"
		                    style="display:none; width:auto; height:auto;">
		                  </div>
		               	</c:forEach>
		            </c:if>
		          </c:forEach>
                  <!-- 地铁 线路 结束 -->
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="subFielsDiv" subfieldscontainerid="55">
                <!-- 线路 站点 开始 -->
                <c:forEach var="items" items="${searchItems }" varStatus="s1">
                	 <c:if test="${items.id == 55 }">
                	 	<c:forEach var="subway" items="${items.subway}" varStatus="s2">
				                <c:set var="cssStyle" value="hiddenSubField" />
		                		<c:if test="${subwayValues[0] eq subway.erpId }">
		                			<c:set var="cssStyle" value="displaySubField" />
		                		</c:if>
				                <div class="${cssStyle}" subfileddivid="55_${subway.erpId}">
				                  <div class="subFileAndKeyContainer">
				                    <div class="subFieldsKey"></div>
				                    <div class="subFiledKeyValueDiv" subcontainerdiv="55_${subway.erpId}_"></div>
				                     	<c:forEach var="station" items="${subway.subwayStation}" varStatus="s3">
				                     		<c:set var="cssStyle" value="" />
		                                    <c:if test="${subwayValues[1] eq station.erpId }">
		                                        <c:set var="cssStyle" value="selectedField" />
		                                    </c:if>
				                     		<div class="subFieldItemDiv ${cssStyle }" fieldvalue="${station.erpId}">
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
                <!-- 线路 站点 结束 -->
              </div>
              <div class="itemLine">
                <div class="searchLabelContainer">
                  <div class="searchLabelLeft"></div>
                  <div class="searchLabel" searchitemid="87">距离:</div>
                  <div class="searchLabelRight"></div>
                  <div class="clearDiv"></div>
                </div>
                <div class="searchFieldContentDiv" supermenuid="48" fieldscontentid="87">
                  <div class="searchField searchUnlimited ${h:containsKey(params, 'd')?'':'selectedField'}" hassubvalue="false"
                  columnname="ddhb_two_distance">
                    <span><a href="<h:surl p='d'/>">不限</a></span>
                  </div>
                  <!-- 距离 开始 -->
                  <c:forEach var="items" items="${searchItems}" varStatus="s1">
                  	<c:if test="${items.id==87}">
                  		<c:forEach var="fields" items="${items.fields}" varStatus="s2">
	                  	   <div class="searchField ${h:containsEntry(params, 'd', s2.count.toString())? 'selectedField' : ''}">
						  		<span><a href="<h:surl p="d${fields.id}"/>">${fields.fieldname}</a></span>
						  </div>
						</c:forEach>
					</c:if>
                  </c:forEach>

                  <!-- 距离 结束 -->
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
            </div>
        	         <!-- 字母 开始 -->
          			     
		<c:forEach var="items" items="${searchItems}" varStatus="s1" >
			<c:if test="${items.id eq 58 }">
          		<div id="privateSearchContent50" class="${letters ne null?'diaplayPrivateDiv':'hiddenPrivateDiv' }">
				  <div class="itemLine">
				    <div class="searchLabelContainer">
				      <div class="searchLabelLeft"></div>
				      <div class="searchLabel" searchitemid="58">字母:</div>
				      <div class="searchLabelRight"></div>
			    	  <div class="clearDiv"></div>
			    </div>
			    <div class="searchFieldContentDiv" supermenuid="50" fieldscontentid="58">
			      <div class="searchField searchUnlimited ${h:containsKey(params, 'q_')?'':'selectedField'}" >
			        <span><a href="<h:surl p='q__'/>">不限</a></span>
			      </div>

			       	<!-- 字母 循环 开始 -->
			      		<c:forEach var="inital" items="${items.initals }" varStatus="s2"> 
			             <c:set var="cssStyle" value="" />
			             <c:if test="${letters[0] eq inital }">
			                <c:set var="cssStyle" value="${h:containsEntry(params, 'q_', params['q_'])? 'selectedField' : ''}" />
			             </c:if>
						   <div class="searchField ${cssStyle}" fieldvalue="${inital }">
						      <span><a href="<h:surl p='q_${inital }_'/>">${fn:toUpperCase(inital) }</a></span><br>
						      <img src="http://www.hshb.cn//images/search/narrow_green.png" class="narrowIcon" style="display: none; width: auto; height: auto;">
						   </div>
						</c:forEach>	      
						      <!-- 字母 循环 结束 -->
						      <div class="clearDiv"></div>
						    </div>
						    <div class="clearDiv"></div>
						  </div>
						  <div class="subFielsDiv" subfieldscontainerid="58">
						  <c:forEach var="inital" items="${items.initals }" varStatus="s2"> 
								    <!-- 小区 循环 开始 -->
								    <c:set var="cssStyle" value="hiddenSubField" />
		                			<c:if test="${letters[0] eq inital }">
		                				<c:set var="cssStyle" value="displaySubField" />
		                			</c:if>
								    <div class="${cssStyle }" subfileddivid="58_${inital }">
								      <div class="subFileAndKeyContainer">
								        <div class="subFieldsKey"></div>
								        <div class="subFiledKeyValueDiv" subcontainerdiv="58_${inital }__"></div>
								        <!-- 小区 循环 二级 开始 -->
								        <c:forEach var="field" items="${items.fields }" > 
								        	<c:set var="lowercaseLetter" value="${field.maxfieldvalue}" />
								        	<c:if test="${lowercaseLetter eq inital }">
								        		<c:set var="cssStyle" value="" />
		                                        <c:if test="${letters[1] eq field.id }">
		                                        	<c:set var="cssStyle" value="selectedField" />
		                                        </c:if>
										        <div class="subFieldItemDiv ${cssStyle }" supersubfielddivid="58_${inital }" >
										      		  <a href="<h:surl p='q_${inital}_${field.id}'/>">${field.fieldname}</a>
										        </div>
										    </c:if>
									    </c:forEach>
								        <!-- 商圈 循环 二级 结束 -->
								        <div class="clearDiv"></div>
								      </div>
								      <div class="clearDiv"></div>
								    </div>
								    <div class="clearDiv"></div>
								    <!-- 商圈 循环 结束 -->
						    </c:forEach>
						  </div>
				</div>
			</c:if>
		</c:forEach>
          <!-- 字母结束 -->
          </div>
 
        </div>
        <c:set var="cssStyle" value="${params['q_'] ne null?'display:none;':'display:block;'}"  />
        <div id="publicSearchItemContent" style="${cssStyle}">
          <div id="notHidden">
            <div class="itemLine">
              <div class="searchLabelContainer">
                <div class="searchLabelLeft"></div>
                <div class="searchLabel" searchitemid="52">均价:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" fieldscontentid="52">
                <div class="searchField searchUnlimited  ${h:containsKey(params, 'p')?'':'selectedField'}" hassubvalue="false"
                columnname="ddhb_two_currentSHAvePrice">
                  <span><a href="<h:surl p='p' />">不限</a></span>
                </div>
                <!-- 均价循环开始 -->
				<c:forEach var="items" items="${searchItems}" varStatus="s1">
					<c:if test="${items.id==52}">
						<c:forEach var="fields" items="${items.fields}" varStatus="s2">
			                <div class="searchField ${h:containsEntry(params, 'p', s2.count.toString())? 'selectedField' : ''}"  fieldvalue="${fields.minfieldvalue}@${fields.maxfieldvalue}">
			                  
			                  <span><a href="<h:surl p="p${fields.minfieldvalue}-${fields.maxfieldvalue}"/>">${fields.fieldname}</a></span>
			                </div>
			        	</c:forEach>
			    	</c:if>
			    </c:forEach>
                <!-- 均价循环结束 -->
                <div class="inputDiv" >
                  <div class="rangeContentDiv">
                    <input type="text" class="minValue">
                    	-
                    <input type="text" class="maxValue">
                    <span>万元</span>
                  </div>
                  <div class="toSearchIcon" unitname="p" jump="<h:surl p="p"/>" ></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
            <div class="itemLine">
              <div class="searchLabelContainer">
                <div class="searchLabelLeft"></div>
                <div class="searchLabel" searchitemid="54">年代:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" fieldscontentid="54">
                <div class="searchField searchUnlimited ${h:containsKey(params, 'nd')?'':'selectedField'}" 
                columnname="ddhb_two_startSaleDate">
                  <span><a href="<h:surl p='nd' />">不限</a></span>
                </div>
                <!-- 年代 开始 -->
                  <c:forEach var="items" items="${searchItems}" varStatus="s1">
					<c:if test="${items.id==54}">
						<c:forEach var="fields" items="${items.fields}" varStatus="s2">
				        	<div class="searchField ${h:containsEntry(params, 'nd', s2.count.toString())? 'selectedField' : ''}">
								<span><a href="<h:surl p="nd${s2.count}"/>">${fields.fieldname}</a></span>
				            </div>
						</c:forEach>
					</c:if>
				</c:forEach>
                <!-- 年代结束 -->
                <div class="searchField" columnname="ddhb_two_startSaleDate" isrange="true" fieldvalue="0@3000-01-01">
                  <input class="yearBefore" columnname="ddhb_two_startSaleDate" jump="<h:surl p='nd' />" unitname="nd" >
                  <span>年以后</span>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
            <div class="clearDiv"></div>
      <div id="selectedSearchItemPanel">
        <div id="allSelectedTitle">本次找房条件:</div>
        <div id="allSelectedItemsDiv">&nbsp;
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
				id="saveContainer" condition="<h:surl p='e'/>" type="xiaoqu">
				<div class="oprationIcon ${buttonDisable ne true?'saveIcon': 'disabledSaveIcon'}"></div>
				<div class="operationLabel">保存</div>
				<div class="clearDiv"></div>
			</div>
			<div class="operationItemContainer ${buttonDisable ne true?'anabledOperation': 'disableOperation'}" buttonDisable="${buttonDisable}"
				id="clearContainer" jump="${globalUrl }xiaoqu">
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
            <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare" data="{'text':''}">
              <a class="bds_qzone" onmousedown="startToShare()" title="分享到QQ空间" href="#"></a>
              <a class="bds_tsina" onmousedown="startToShare()" title="分享到新浪微博" href="#"></a>
              <a class="bds_tqq" onmousedown="startToShare()" title="分享到腾讯微博" href="#"></a>
              <a class="bds_sqq" onmousedown="startToShare()" title="分享到QQ好友" href="#"></a>
            </div>
          </div>
        </div>
        <div class="clearDiv"></div>
      </div>
          </div>
          <div id="isSelectList">
            <div class="searchLabelLeft"></div>
          </div>
        </div>
      </div>
      <div id="hiddenButtonBar hiddenButtonBarClass">
        <div id="expandOrHiddenBar hiddenButtonBarClass"></div>
        <div class="clearDiv"></div>
      </div>
      <div class="clearDiv"></div>
      <div style="clear:both;"></div>
    </div>
  </div>
</div>
