<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="x">
  <div id="searchMenuDiv" class="companyRight" style="width:100%; margin-top:12px;">
    <div id="searchMenuBar">
      <div class="itemMenu itemMenuSelected" onlyshowprivate="false" id="searchMenu12" menuid="12">区域</div>
      <div class="itemMenu" onlyshowprivate="false" id="searchMenu13" menuid="13">地铁</div>
      <div class="itemMenu" onlyshowprivate="false" id="searchMenu14" menuid="14">学区</div>
      <div class="itemMenu" onlyshowprivate="false" id="searchMenu15" menuid="15">预算</div>
    </div>
    <div class="clearDiv"></div>
    <div id="searchContent">
      <div id="searchItemContent">
        <div id="privateSearchItemContent">
          <div id="privateSearchContent12" class="diaplayPrivateDiv">
            <div class="itemLine">
              <div class="searchLabelContainer">
                <div class="searchLabelLeft"></div>
                <div class="searchLabel" searchitemid="16">区域:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" supermenuid="12" fieldscontentid="16">
                <div class="searchField searchUnlimited selectedField" hassubvalue="true" columnname="ddhb_one_community.region.erpId">
                  <span>不限</span>
                </div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_community.region.erpId" fieldvalue="15">
                  <span>拱墅</span><br>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_community.region.erpId" fieldvalue="16">
                  <span>江干</span><br>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_community.region.erpId" fieldvalue="18">
                  <span>上城</span><br/>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_community.region.erpId" fieldvalue="20">
                  <span>西湖</span><br/>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_community.region.erpId" fieldvalue="21">
                  <span>下城</span><br/>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_community.region.erpId" fieldvalue="13">
                  <span>滨江</span><br/>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_community.region.erpId" fieldvalue="24">
                  <span>余杭</span><br/>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_community.region.erpId" fieldvalue="22">
                  <span>萧山</span><br/>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
            <div class="subFielsDiv" subfieldscontainerid="16">
              <div class="hiddenSubField" subfileddivid="16_15">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">B</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_15_B"></div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="14">白马</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="15">半道红</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="16">半山</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="17">北景园</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="18">不显示</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">C</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_15_C">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="40">长乐</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="41">长征桥</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="19">仓基新村</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">D</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_15_D">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="21">大浒</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="22">大桥西</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="23">稻香园</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="24">德胜</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="25">都市水乡</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="20">大关</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">G</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_15_G">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="26">拱宸桥</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">H</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_15_H">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="27">和睦</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">J</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_15_J">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="28">嘉泰馨庭</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="29">锦昌文华</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">M</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_15_M">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="30">米市巷</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">Q</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_15_Q">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="31">浅水湾</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="32">清水公寓</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">S</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_15_S">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="34">树人大学</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="33">申花</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">T</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_15_T">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="35">塘河</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">X</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_15_X">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="37">信义坊</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="36">小河</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">Y</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_15_Y">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="38">杨家门</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="16_15" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="39">元都新景</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subfileddivid="16_16">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">B</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_16_B">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="42">不显示</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">C</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_16_C">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="43">采荷</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="44">城东新城</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">D</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_16_D">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="45">丁桥</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="46">东方红街</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="47">东茂苑</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">H</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_16_H">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="48">航海路</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">J</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_16_J">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="49">笕桥</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="50">金泊林</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="51">金沙湖</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="52">景芳</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="53">九堡</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">L</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_16_L">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="54">丽江</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">M</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_16_M">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="55">魅力</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">N</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_16_N">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="56">南肖埠</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">P</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_16_P">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="57">濮家</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">Q</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_16_Q">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="58">钱江新城</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="59">庆春银泰</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">S</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_16_S">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="60">三里家园</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="61">三里亭</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">T</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_16_T">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="63">天都城</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="62">天城</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">W</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_16_W">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="64">物美</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">X</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_16_X">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="65">下沙</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="66">下沙沿江北</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">Z</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_16_Z">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="67">闸弄口</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="16_16" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="68">总管塘</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subfileddivid="16_18">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">C</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_18_C">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="69">春江花月</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">F</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_18_F">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="70">凤凰苑</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="71">复兴</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">G</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_18_G">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="72">鼓楼</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="73">观音塘</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">H</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_18_H">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="74">河坊街</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">J</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_18_J">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="75">近江家园</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">K</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_18_K">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="76">葵巷</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">L</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_18_L">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="77">老浙大</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">M</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_18_M">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="79">美政</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="78">马市</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">N</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_18_N">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="80">南星桥</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">P</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_18_P">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="81">平海</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">Q</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_18_Q">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="82">钱江新城</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="83">清波门</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">S</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_18_S">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="84">胜利小学</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="85">始板桥</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">W</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_18_W">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="86">望江</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="16_18" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="87">吴山</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subfileddivid="16_20">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">B</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_20_B">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="88">保小</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">C</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_20_C">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="89">城北商贸园</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="90">城西别墅</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="91">翠苑</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">F</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_20_F">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="93">府苑新村</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="92">枫华府第</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">G</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_20_G">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="94">古荡</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="95">桂花城</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">H</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_20_H">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="99">黄龙</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="98">湖畔花园</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="97">和家园</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">J</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_20_J">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="105">九莲</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="104">竞舟</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="103">金地自在城</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="102">蒋村</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="101">嘉绿景苑</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="100">嘉绿</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">K</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_20_K">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="106">康乐</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">L</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_20_L">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="107">莲花港</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="108">留下</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">Q</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_20_Q">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="110">求是小学</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="109">庆丰</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">R</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_20_R">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="111">润达花园</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">S</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_20_S">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="115">世纪新城</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="112">三墩</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="113">山水人家</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="114">申花</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">T</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_20_T">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="116">天苑</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">W</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_20_W">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="117">文三</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="118">文一</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">X</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_20_X">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="125">新星小区</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="126">星洲</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="127">学军</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="96">行知</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="124">小和山</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="123">香樟公寓</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="122">西溪里</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="121">西溪</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="120">西湖小学</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="119">西荡苑</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">Y</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_20_Y">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="128">亚洲城</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">Z</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_20_Z">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="16_20" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="129">转塘</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subfileddivid="16_21">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">A</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_21_A">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="130">安吉路</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">B</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_21_B">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="131">宝善</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">C</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_21_C">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="132">朝晖</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="133">潮鸣</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="150">长庆</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="151">长寿桥</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">D</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_21_D">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="134">德胜</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="135">东河</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="136">东新园</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">L</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_21_L">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="138">绿洲花园</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="137">流水苑</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">M</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_21_M">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="139">民航</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">Q</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_21_Q">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="140">庆春</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">S</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_21_S">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="144">四园</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="143">水印康庭</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="142">施家桥</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="141">三塘</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">T</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_21_T">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="145">体东</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="146">天水</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">W</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_21_W">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="147">万家星城</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">X</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_21_X">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="148">现代城</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="149">新华</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">Z</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_21_Z">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="16_21" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="152">中北桥</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subfileddivid="16_13">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">A</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_13_A">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="16_13" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="1">奥体</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">B</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_13_B">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="16_13" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="2">白金海岸</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="16_13" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="3">柏悦轩</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">C</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_13_C">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="16_13" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="4">彩虹城</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">D</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_13_D">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="16_13" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="5">东方郡</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">F</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_13_F">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="16_13" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="7">风雅钱塘</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="16_13" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="6">风景蝶院</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">J</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_13_J">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="16_13" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="8">江南豪园</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="16_13" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="9">江南文苑</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">M</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_13_M">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="16_13" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="10">明月江南</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">Q</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_13_Q">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="16_13" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="11">钱江湾花园</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">S</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_13_S">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="16_13" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="12">水晶城</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">X</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_13_X">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="16_13" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="13">西兴</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subfileddivid="16_24">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">C</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_24_C"></div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="24" supersubfielddivid="16_24" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="154">崇贤</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">F</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_24_F">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="24" supersubfielddivid="16_24" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="155">翡翠城</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">J</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_24_J">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="24" supersubfielddivid="16_24" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="156">爵士风情</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">L</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_24_L">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="24" supersubfielddivid="16_24" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="157">老余杭</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="24" supersubfielddivid="16_24" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="158">良渚文化村</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">Q</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_24_Q">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="24" supersubfielddivid="16_24" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="160">亲亲家园</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="24" supersubfielddivid="16_24" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="159">乔司</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">T</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_24_T">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="24" supersubfielddivid="16_24" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="161">桃花源</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="24" supersubfielddivid="16_24" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="162">天阳棕榈湾</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">X</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_24_X">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="24" supersubfielddivid="16_24" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="163">闲林</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="24" supersubfielddivid="16_24" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="164">闲林山水</div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="24" supersubfielddivid="16_24" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="165">新西湖小镇</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subfileddivid="16_22">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">X</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="16_22_X">
                  </div>
                  <div class="subFieldItemDiv" menuid="12" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="22" supersubfielddivid="16_22" fieldcolumnname="ddhb_one_community.cbd.parentCBD.websiteId" fieldvalue="153">萧山</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
          </div>
          <div id="privateSearchContent13" class="hiddenPrivateDiv">
            <div class="itemLine">
              <div class="searchLabelContainer">
                <div class="searchLabelLeft"></div>
                <div class="searchLabel" searchitemid="24">地铁:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" supermenuid="13" fieldscontentid="24">
                <div class="searchField searchUnlimited selectedField" hassubvalue="true"columnname="ddhb_one_subwayline.erpId"><span>不限</span></div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_subwayline.erpId" fieldvalue="1">
                  <span>杭州地铁1号线</span><br/>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_subwayline.erpId" fieldvalue="2">
                  <span>杭州地铁2号线</span><br/>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_subwayline.erpId" fieldvalue="3">
                  <span>杭州地铁3号线</span><br/>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
            <div class="subFielsDiv" subfieldscontainerid="24">
              <div class="hiddenSubField" subfileddivid="24_1">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">
                  </div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="24_1__">
                  </div>
                  <div class="clearDiv">
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="1">湘湖站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="10">翁梅站（支线）</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="11">余杭高铁站（支线）</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="12">龙翔桥站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="13">凤起路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="14">武林广场站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="15">西湖文化广场站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="16">打铁关站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="17">闸弄口站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="18">火车东站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="19">彭埠站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="2">滨康路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="20">南苑站（支线）</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="25">七堡站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="26">九和路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="27">九堡站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="28">客运中心站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="29">下沙西站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="3">西兴站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="30">金沙湖站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="31">高沙路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="32">文泽路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="33">乔司南站（支线）</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="34">乔司站（支线）</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="35">临平站（支线）</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="4">滨和路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="5">江陵路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="6">近江站</div>   <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="7">婺江路站</div>   <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="8">城站站</div><div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="1" supersubfielddivid="24_1" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="9">定安路站</div></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subfileddivid="24_2">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey"></div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="24_2__"></div>
                  <div class="clearDiv">
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="36">丰潭路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="37">古翠路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="38">学院路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="39">下宁桥站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="40">沈塘桥站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="41">武林门站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="42">凤起路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="43">中河北路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="44">建国北路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="45">庆菱路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="46">庆春广场站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="47">钱江世纪城站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="48">盈丰路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="49">飞虹路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="50">钱江路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="51">振宁路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="52">建设三路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="53">建设一路站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="54">人民广场站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="55">杭发厂站</div>
                    <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="56">人民路站</div>   <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="57">潘水站</div>   <div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="58">曹家桥站</div><div class="subFieldItemDiv" menuid="13" parentcolunmname="ddhb_one_subwayline.erpId" parentfieldvalue="2" supersubfielddivid="24_2" fieldcolumnname="ddhb_one_subwaystation.erpId" fieldvalue="59">朝阳村站</div></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subfileddivid="24_3">
                <div class="clearDiv"></div>
              </div>
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
                <div class="searchField searchUnlimited selectedField" hassubvalue="false" columnname="ddhb_two_distance"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="ddhb_two_distance" fieldvalue="0@500"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="ddhb_two_distance" fieldvalue="500@1000"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="ddhb_two_distance" fieldvalue="1000@1500"><span>(.+?)</span></div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
          </div>
          <div id="privateSearchContent14" class="hiddenPrivateDiv">
            <div class="itemLine">
              <div class="searchLabelContainer">
                <div class="searchLabelLeft"></div>
                <div class="searchLabel">区域:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" supermenuid="14" fieldscontentid="25">
                <div class="searchField searchUnlimited selectedField" hassubvalue="true" columnname="ddhb_one_community.region.erpId"><span>(.+?)</span></div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_community.region.erpId" fieldvalue="15">
                  <span>拱墅</span><br/>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_community.region.erpId" fieldvalue="16">
                  <span>江干</span><br/>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_community.region.erpId" fieldvalue="18">
                  <span>上城</span><br/>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_community.region.erpId" fieldvalue="20">
                  <span>西湖</span><br/>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_community.region.erpId" fieldvalue="21">
                  <span>下城</span><br/>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_community.region.erpId" fieldvalue="13">
                  <span>滨江</span><br/>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_community.region.erpId" fieldvalue="24">
                  <span>余杭</span><br/>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
                <div class="searchField" hassubvalue="true" isprivateitem="true" columnname="ddhb_one_community.region.erpId" fieldvalue="22">
                  <span>萧山</span><br/>
                  <img src="http://www.hshb.cn/images/search/narrow.png" class="narrowIcon" style="display:none; width:auto; height:auto;">
                </div>
              </div>
              <div class="clearDiv"></div>
            </div>
            <div class="subFielsDiv" subfieldscontainerid="25">
              <div class="hiddenSubField" subfileddivid="25_15" style="margin-bottom:5px;">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">拱墅:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_15_">
                  </div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="25_15_0" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74">幼儿园</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="25_15_1" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="628916a7-07dc-4c24-becd-ee68b84a6007">初中</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="25_15_2" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="ce4d47e4-fed4-4cd4-87f0-76dfd3ebae80">高中</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="25_15_3" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="e921a3b6-2f61-432a-9dfa-8788f8cd04e2">大学</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="15" supersubfielddivid="25_15_4" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98">小学</div>
                  <div class="clearDiv"></div>
                </div>
              </div>
              <div class="hiddenSubField" subparentschoolgrade="25_15" subfileddivid="25_15_0">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">幼儿园:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_15_0">
                  </div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="13">紫荆幼儿园</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="8">红缨幼儿园</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_15" subfileddivid="25_15_1">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">初中:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_15_1">
                  </div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="628916a7-07dc-4c24-becd-ee68b84a6007" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="117">大关中学教育集团董家校区</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="628916a7-07dc-4c24-becd-ee68b84a6007" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="119">杭州和睦中学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="628916a7-07dc-4c24-becd-ee68b84a6007" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="120">杭州育新高级中学</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_15" subfileddivid="25_15_2">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">高中:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_15_2">
                  </div>该片区暂未录入高中
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_15" subfileddivid="25_15_3">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">大学:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_15_3">
                  </div>该片区暂未录入大学
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_15" subfileddivid="25_15_4">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">小学:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_15_4">
                  </div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2024">人民小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2032">半山实验小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2033">卖鱼桥小学（文津小学）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2034">卖鱼桥小学(文澜校区)</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2035">卖鱼桥小学（湖墅霞湾校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2040">和睦小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2044">外语实验小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2045">大关中学附属小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2046">大关小学申花校区</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2065">建新小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2069">德胜小学（德胜校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2072">拱宸桥小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2077">文三小学教育集团（文三街小学）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2080">文海实验学校</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2081">文渊小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2084">景成实验学校</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2093">杭州拱墅附校</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2101">求知小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2103">江心岛小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2120">省教科院附属小学（田园校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2130">莫干山路小学（北站校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2137">转塘小学（方家路校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2149">长青小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2152">青蓝青华实验小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="24">杭州和睦小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="29">杭州市大关中学附属小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_15" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="30">育才小学</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subfileddivid="25_16" style="margin-bottom:5px;">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">江干:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_16_">
                  </div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="25_16_0" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74">幼儿园</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="25_16_1" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="628916a7-07dc-4c24-becd-ee68b84a6007">初中</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="25_16_2" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="ce4d47e4-fed4-4cd4-87f0-76dfd3ebae80">高中</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="25_16_3" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="e921a3b6-2f61-432a-9dfa-8788f8cd04e2">大学</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="16" supersubfielddivid="25_16_4" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98">小学</div>
                  <div class="clearDiv"></div>
                </div>
              </div>
              <div class="hiddenSubField" subparentschoolgrade="25_16" subfileddivid="25_16_0">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">幼儿园:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_16_0">
                  </div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="25">杭州市京江幼儿园</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="26">杭州市兰苑幼儿园</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_16" subfileddivid="25_16_1">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">初中:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_16_1">
                  </div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="628916a7-07dc-4c24-becd-ee68b84a6007" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="121">东城中学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="628916a7-07dc-4c24-becd-ee68b84a6007" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="122">景芳中学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="628916a7-07dc-4c24-becd-ee68b84a6007" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="128">景华中学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="628916a7-07dc-4c24-becd-ee68b84a6007" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2025">保俶塔实验学校（松木场校区）</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_16" subfileddivid="25_16_2">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">高中:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_16_2">
                  </div>该片区暂未录入高中
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_16" subfileddivid="25_16_3">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">大学:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_16_3"></div>该片区暂未录入大学
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_16" subfileddivid="25_16_4">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">小学:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_16_4"></div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2018">下沙第一小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2019">下沙第二小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2027">凯旋教育集团（南肖埠小学）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2028">凯旋教育集团（春芽实验学校）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2029">凯旋教育集团（景华小学）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2030">凯旋教育集团（茅以升实验学校）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2038">听涛小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2041">四季青小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2042">回族穆兴小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2043">夏衍小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2049">大成实验学校</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2052">天地实验小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2053">天杭教育集团</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2060">学林街小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2061">学正小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2067">彭埠第二小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2085">景苑小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2086">望小学江门</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2089">杭州师范大学东城二小</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2090">杭州师范大学东城实验学校</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2091">杭州师范大学东城小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2092">杭州师范大学附属丁兰实验学校</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2096">杭师附小（钱江校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2097">永天实验小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2104">江湾小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2111">滨江一小</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2112">濮家小学（万家校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2114">濮家小学（笕新小学）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2126">胜利实验学校</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2139">采荷一小（采荷校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2140">采荷一小（钱江苑校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2141">采荷三小（本部）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2142">采荷三小（江锦小学）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2143">采荷二小（丁荷小学）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2144">采荷二小（本部）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2145">采荷二小（笕桥小学）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2147">长寿桥小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="32">杭州采荷第三小学教育集团</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="33">杭州采荷第一小学教育集团</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="34">杭州采荷第一小学教育集团</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="35">杭州采荷第一小学教育集团</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="37">杭州采荷第一小学教育集团</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="38">杭州采荷二小教育集团丁荷小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="39">杭州春芽实验小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="41">杭州师范大学东城第二小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="42">杭州师范大学东城实验教育集团</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="43">杭州师范大学东城小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="44">杭州师范大学附属丁兰小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="45">杭州师范大学教育集团</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="47">杭州市景华小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="52">文博小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="55">阳光小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="58">四季青小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_16" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="59">下沙第二小学</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subfileddivid="25_18" style="margin-bottom:5px;">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">上城:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_18_"></div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="25_18_0" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74">幼儿园</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="25_18_1" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="628916a7-07dc-4c24-becd-ee68b84a6007">初中</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="25_18_2" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="ce4d47e4-fed4-4cd4-87f0-76dfd3ebae80">高中</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="25_18_3" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="e921a3b6-2f61-432a-9dfa-8788f8cd04e2">大学</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="18" supersubfielddivid="25_18_4" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98">小学</div>
                  <div class="clearDiv"></div>
                </div>
              </div>
              <div class="hiddenSubField" subparentschoolgrade="25_18" subfileddivid="25_18_0">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">幼儿园:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_18_0"></div>
                  该片区暂未录入幼儿园
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_18" subfileddivid="25_18_1">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">初中:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_18_1">
                  </div>该片区暂未录入初中
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_18" subfileddivid="25_18_2">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">高中:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_18_2">
                  </div>该片区暂未录入高中
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_18" subfileddivid="25_18_3">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">大学:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_18_3">
                  </div>该片区暂未录入大学
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_18" subfileddivid="25_18_4">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">小学:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_18_4">
                  </div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2017">上教院附小</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2021">东园小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2031">刀茅巷小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2048">大学路小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2055">天长小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2056">始版桥小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2063">宗文小营实验学校</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2064">市教科所附小</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2071">抚宁巷小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2094">杭师附小（吴山校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2095">杭师附小（秋涛路校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2109">清泰实验学校</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2122">紫阳小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2127">胜利小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2146">金都天长小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2153">饮马井巷小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2155">高银巷小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="72">杭州师范大学附属第二小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="73">杭州师范大学附属小学钱江分校</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_18" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="81">杭州紫阳小学</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subfileddivid="25_20" style="margin-bottom:5px;">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">西湖:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_20_">
                  </div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="25_20_0" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74">幼儿园</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="25_20_1" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="628916a7-07dc-4c24-becd-ee68b84a6007">初中</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="25_20_2" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="ce4d47e4-fed4-4cd4-87f0-76dfd3ebae80">高中</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="25_20_3" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="e921a3b6-2f61-432a-9dfa-8788f8cd04e2">大学</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="20" supersubfielddivid="25_20_4" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98">小学</div>
                  <div class="clearDiv"></div>
                </div>
              </div>
              <div class="hiddenSubField" subparentschoolgrade="25_20" subfileddivid="25_20_0">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">幼儿园:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_20_0">
                  </div>
                  该片区暂未录入幼儿园
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_20" subfileddivid="25_20_1">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">初中:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_20_1">
                  </div>该片区暂未录入初中
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_20" subfileddivid="25_20_2">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">高中:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_20_2">
                  </div>该片区暂未录入高中
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_20" subfileddivid="25_20_3">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">大学:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_20_3">
                  </div>该片区暂未录入大学
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_20" subfileddivid="25_20_4">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">小学:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_20_4">
                  </div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="100">杭州文三教育集团</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2016">三墩小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2022">九莲小学（文三教育）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2026">保俶塔实验学校（申花路校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2037">古荡一小</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2050">大禹路小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2057">学军小学（之江校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2058">学军小学（求智校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2059">学军小学（紫金港校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2062">安吉路实验学校</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2070">德胜小学（都市水乡校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2073">文一街小学（政苑校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2074">文一街小学（文一校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2075">文一街小学（秀水校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2076">文三小学教育集团（嘉绿苑小学）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2078">文三小学教育集团（文理小学）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2079">文三小学教育集团（文苑校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2099">求是教育集团（浙大附小）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2100">求是教育集团（竞舟小学）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2118">省府路小学秋水苑校区</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2119">省教研室附属小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2123">翠苑一小（文华校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2124">翠苑一小（翠苑校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2125">翠苑二小</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2131">行知小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2133">西湖小学教育集团（府苑小学）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2134">西湖小学教育集团（文新小学）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2136">西溪实验学校</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2138">转塘小学（象山校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2156">龙坞小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="95">杭州市行知小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_20" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="96">西湖小学教育集团</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subfileddivid="25_21" style="margin-bottom:5px;">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">下城:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_21_">
                  </div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="25_21_0" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74">幼儿园</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="25_21_1" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="628916a7-07dc-4c24-becd-ee68b84a6007">初中</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="25_21_2" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="ce4d47e4-fed4-4cd4-87f0-76dfd3ebae80">高中</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="25_21_3" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="e921a3b6-2f61-432a-9dfa-8788f8cd04e2">大学</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="21" supersubfielddivid="25_21_4" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98">小学</div>
                  <div class="clearDiv"></div>
                </div>
              </div>
              <div class="hiddenSubField" subparentschoolgrade="25_21" subfileddivid="25_21_0">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">幼儿园:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_21_0">
                  </div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="19">杭州安华幼儿园教育集团</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="21">三塘实验幼儿园</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="27">安吉路早期教育研究发展中心三塘园区</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="36">杭州市朝晖幼儿园朝晖一区园区</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_21" subfileddivid="25_21_1">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">初中:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_21_1">
                  </div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="628916a7-07dc-4c24-becd-ee68b84a6007" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="133">杭州风帆中学教育集团</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="628916a7-07dc-4c24-becd-ee68b84a6007" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="134">杭州市风帆中学</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_21" subfileddivid="25_21_2">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">高中:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_21_2">
                  </div>该片区暂未录入高中
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_21" subfileddivid="25_21_3">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">大学:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_21_3">
                  </div>该片区暂未录入大学
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_21" subfileddivid="25_21_4">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">小学:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_21_4">
                  </div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="101">杭州市长青小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="108">京都教育集团</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="109">京都苑小学流水校区</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="111">现代实验小学朝晖二区校区</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2023">京都小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2039">启源小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2054">天水小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2068">德天实验小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2082">文龙巷小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2083">新华小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2087">朝晖实验小学（一区校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2088">朝晖实验小学（五区校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2105">浙师大附属丁蕙实验小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2115">现代实验小学（二区校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2116">现代实验小学（六区校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2117">现代实验小学（现代校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2128">胜蓝实验学校（东新园校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2129">胜蓝实验学校（阳光校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2151">青蓝小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="67">杭州市朝晖教育集团</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="90">长青小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="91">德天实验小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="92">杭州大成实验学校</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="97">朝晖新村七区小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="98">杭州青蓝青华小学教育集团</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_21" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="99">杭州求知小学教育集团</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subfileddivid="25_13" style="margin-bottom:5px;">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">滨江:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_13_">
                  </div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="25_13_0" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74">幼儿园</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="25_13_1" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="628916a7-07dc-4c24-becd-ee68b84a6007">初中</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="25_13_2" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="ce4d47e4-fed4-4cd4-87f0-76dfd3ebae80">高中</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="25_13_3" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="e921a3b6-2f61-432a-9dfa-8788f8cd04e2">大学</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="13" supersubfielddivid="25_13_4" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98">小学</div>
                  <div class="clearDiv"></div>
                </div>
              </div>
              <div class="hiddenSubField" subparentschoolgrade="25_13" subfileddivid="25_13_0">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">幼儿园:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_13_0">
                  </div>
                  该片区暂未录入幼儿园
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_13" subfileddivid="25_13_1">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">初中:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_13_1">
                  </div>该片区暂未录入初中
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_13" subfileddivid="25_13_2">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">高中:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_13_2">
                  </div>该片区暂未录入高中
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_13" subfileddivid="25_13_3">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">大学:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_13_3">
                  </div>该片区暂未录入大学
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_13" subfileddivid="25_13_4">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">小学:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_13_4">
                  </div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_13" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2036">博文小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_13" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2066">彩虹城小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_13" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2102">江南实验学校</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_13" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2106">浦沿小学（山二分校）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_13" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2107">浦沿小学（浦沿校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_13" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2108">浦沿小学（滨文校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_13" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2110">滨兴学校</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_13" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2132">西兴实验小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_13" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2148">长河小学（长河校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_13" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2150">闻涛小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_13" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2154">高新实验学校</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subfileddivid="25_24" style="margin-bottom:5px;">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">余杭:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_24_">
                  </div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="24" supersubfielddivid="25_24_0" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74">幼儿园</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="24" supersubfielddivid="25_24_1" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="628916a7-07dc-4c24-becd-ee68b84a6007">初中</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="24" supersubfielddivid="25_24_2" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="ce4d47e4-fed4-4cd4-87f0-76dfd3ebae80">高中</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="24" supersubfielddivid="25_24_3" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="e921a3b6-2f61-432a-9dfa-8788f8cd04e2">大学</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="24" supersubfielddivid="25_24_4" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98">小学</div>
                  <div class="clearDiv"></div>
                </div>
              </div>
              <div class="hiddenSubField" subparentschoolgrade="25_24" subfileddivid="25_24_0">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">幼儿园:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_24_0">
                  </div> 该片区暂未录入幼儿园
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_24" subfileddivid="25_24_1">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">初中:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_24_1">
                  </div>该片区暂未录入初中
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_24" subfileddivid="25_24_2">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">高中:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_24_2">
                  </div>该片区暂未录入高中
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_24" subfileddivid="25_24_3">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">大学:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_24_3">
                  </div>该片区暂未录入大学
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_24" subfileddivid="25_24_4">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">小学:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_24_4">
                  </div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_24" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2051">大禹路小学甲来路校区</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subfileddivid="25_22" style="margin-bottom:5px;">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">萧山:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_22_">
                  </div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="22" supersubfielddivid="25_22_0" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="2668da90-04e4-42a8-9faf-d7390aa09f74">幼儿园</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="22" supersubfielddivid="25_22_1" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="628916a7-07dc-4c24-becd-ee68b84a6007">初中</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="22" supersubfielddivid="25_22_2" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="ce4d47e4-fed4-4cd4-87f0-76dfd3ebae80">高中</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="22" supersubfielddivid="25_22_3" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="e921a3b6-2f61-432a-9dfa-8788f8cd04e2">大学</div>
                  <div class="subFieldItemDiv" menuid="14" parentcolunmname="ddhb_one_community.region.erpId" parentfieldvalue="22" supersubfielddivid="25_22_4" fieldcolumnname="ddhb_one_sztype.erpId" fieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98">小学</div>
                  <div class="clearDiv"></div>
                </div>
              </div>
              <div class="hiddenSubField" subparentschoolgrade="25_22" subfileddivid="25_22_0">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">幼儿园:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_22_0">
                  </div>该片区暂未录入幼儿园
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_22" subfileddivid="25_22_1">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">初中:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_22_1">
                  </div>该片区暂未录入初中
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_22" subfileddivid="25_22_2">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">高中:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_22_2">
                  </div>该片区暂未录入高中
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_22" subfileddivid="25_22_3">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">大学:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_22_3">
                  </div>该片区暂未录入大学
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="hiddenSubField" subparentschoolgrade="25_22" subfileddivid="25_22_4">
                <div class="subFileAndKeyContainer">
                  <div class="subFieldsKey">小学:</div>
                  <div class="subFiledKeyValueDiv" subcontainerdiv="25_22_4">
                  </div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_22" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2020">东冠小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_22" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2047">大关苑第一小学</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_22" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2098">求是教育集团（星洲小学）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_22" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2113">濮家小学（濮家校区）</div>
                  <div class="subFieldItemDiv " menuid="14" parentcolunmname="ddhb_one_sztype.erpId" parentfieldvalue="ff2ee5cd-76e9-4682-8a24-3607791b9f98" supersubfielddivid="25_22" fieldcolumnname="ddhb_one_school.erpId" fieldvalue="2121">省科教院附属实验学校</div>
                  <div class="clearDiv"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
              <div class="clearDiv"></div>
            </div>
            <div class="itemLine">
              <div class="searchLabelContainer">
                <div class="searchLabelLeft"></div>
                <div class="searchLabel" searchitemid="82">距离:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" supermenuid="14" fieldscontentid="82">
                <div class="searchField searchUnlimited selectedField" hassubvalue="false" columnname="ddhb_two_distance"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="ddhb_two_distance" fieldvalue="0@500"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="ddhb_two_distance" fieldvalue="500@1000"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="ddhb_two_distance" fieldvalue="1000@1500"><span>(.+?)</span></div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
          </div>
          <div id="privateSearchContent15" class="hiddenPrivateDiv">
            <div class="itemLine">
              <div class="searchLabelContainer">
                <div class="searchLabelLeft"></div>
                <div class="searchLabel" searchitemid="26">首付:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" supermenuid="15" fieldscontentid="26">
                <div class="searchField searchUnlimited selectedField" hassubvalue="false" columnname="firstpay"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="firstpay" fieldvalue="200000@300000"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="firstpay" fieldvalue="300000@400000"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="firstpay" fieldvalue="400000@500000"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="firstpay" fieldvalue="500000@800000"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="firstpay" fieldvalue="800000@1100000"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="firstpay" fieldvalue="1100000@1600000"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="firstpay" fieldvalue="1600000@2147483647"><span>(.+?)</span></div>
                <div class="inputDiv" hassubvalue="false">
                  <div class="rangeContentDiv">
                    <input type="text" privateinput="true" columnname="firstpay" class="minValue">
                    -
                    <input type="text" privateinput="true" columnname="firstpay" class="maxValue">
                    <span>万元</span>
                  </div>
                  <div class="toSearchIcon" unitname="万元" columnname="firstpay">
                  </div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
            <div class="itemLine">
              <div class="searchLabelContainer">
                <div class="searchLabelLeft"></div>
                <div class="searchLabel" searchitemid="27">月供:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" supermenuid="15" fieldscontentid="27">
                <div class="searchField searchUnlimited selectedField" hassubvalue="false" columnname="monthpay"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="monthpay" fieldvalue="0@2000"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="monthpay" fieldvalue="2000@3000"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="monthpay" fieldvalue="3000@4000"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="monthpay" fieldvalue="4000@5000"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="monthpay" fieldvalue="5000@6000"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="monthpay" fieldvalue="6000@7000"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="monthpay" fieldvalue="7000@2147483647"><span>(.+?)</span></div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
            <div class="itemLine">
              <div class="searchLabelContainer">
                <div class="searchLabelLeft"></div>
                <div class="searchLabel" searchitemid="28">年限:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" supermenuid="15" fieldscontentid="28">
                <div class="searchField searchUnlimited selectedField" hassubvalue="false" columnname="yearcount"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="yearcount" fieldvalue="0@5"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="yearcount" fieldvalue="5@10"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="yearcount" fieldvalue="10@20"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="yearcount" fieldvalue="20@30"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="yearcount" fieldvalue="30@40"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="true" hassubvalue="false" columnname="yearcount" fieldvalue="40@60"><span>(.+?)</span></div>
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
                <div class="searchLabelLeft"></div>
                <div class="searchLabel" searchitemid="17">价格:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" fieldscontentid="17">
                <div class="searchField searchUnlimited selectedField" hassubvalue="false" columnname="ddhb_two_price"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_price" fieldvalue="0@50"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_price" fieldvalue="50@80"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_price" fieldvalue="80@100"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_price" fieldvalue="100@150"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_price" fieldvalue="150@200"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_price" fieldvalue="200@300"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_price" fieldvalue="300@500"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_price" fieldvalue="500@2147483647"><span>(.+?)</span></div>
                <div class="inputDiv" hassubvalue="false">
                  <div class="rangeContentDiv">
                    <input type="text" privateinput="false" columnname="ddhb_two_price" class="minValue">
                    -
                    <input type="text" privateinput="false" columnname="ddhb_two_price" class="maxValue">
                    <span>万元</span>
                  </div>
                  <div class="toSearchIcon" unitname="万元" columnname="ddhb_two_price">
                  </div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
            <div class="itemLine">
              <div class="searchLabelContainer">
                <div class="searchLabelLeft"></div>
                <div class="searchLabel" searchitemid="18">面积:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" fieldscontentid="18">
                <div class="searchField searchUnlimited selectedField" hassubvalue="false" columnname="ddhb_two_area"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_area" fieldvalue="0@40"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_area" fieldvalue="40@50"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_area" fieldvalue="50@60"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_area" fieldvalue="60@70"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_area" fieldvalue="70@85"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_area" fieldvalue="85@95"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_area" fieldvalue="95@120"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_area" fieldvalue="120@150"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_area" fieldvalue="150@300"><span>(.+?)</span></div>
                <div class="searchField" isrange="true" isprivateitem="false" hassubvalue="false" columnname="ddhb_two_area" fieldvalue="300@2147483647"><span>(.+?)</span></div>
                <div class="inputDiv" hassubvalue="false">
                  <div class="rangeContentDiv">
                    <input type="text" privateinput="false" columnname="ddhb_two_area" class="minValue">
                    -
                    <input type="text" privateinput="false" columnname="ddhb_two_area" class="maxValue"><span>平米</span>
                    </div>
                  <div class="toSearchIcon" unitname="平米" columnname="ddhb_two_area"></div>
                </div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
            <div class="itemLine">
              <div class="searchLabelContainer">
                <div class="searchLabelLeft"></div>
                <div class="searchLabel" searchitemid="19">居室:</div>
                <div class="searchLabelRight"></div>
                <div class="clearDiv"></div>
              </div>
              <div class="searchFieldContentDiv" fieldscontentid="19">
                <div class="searchField searchUnlimited selectedField" hassubvalue="false" columnname="ddhb_one_shi"><span>不限</span></div>
                <div class="searchField" isprivateitem="false" hassubvalue="false" ismulty="false" columnname="ddhb_one_shi" fieldvalue="1"><span>一居</span></div>
                <div class="searchField" isprivateitem="false" hassubvalue="false" ismulty="false" columnname="ddhb_one_shi" fieldvalue="2"><span>二居</span></div>
                <div class="searchField" isprivateitem="false" hassubvalue="false" ismulty="false" columnname="ddhb_one_shi" fieldvalue="3"><span>三居</span></div>
                <div class="searchField" isprivateitem="false" hassubvalue="false" ismulty="false" columnname="ddhb_one_shi" fieldvalue="4"><span>四居</span></div>
                <div class="searchField" isprivateitem="false" hassubvalue="false" ismulty="false" columnname="ddhb_one_shi" fieldvalue="5"><span>五居</span></div>
                <div class="searchField" isprivateitem="false" hassubvalue="false" ismulty="false" columnname="ddhb_one_shi" fieldvalue="6"><span>六居</span></div>
                <div class="clearDiv"></div>
              </div>
              <div class="clearDiv"></div>
            </div>
          </div>
          <div id="isSelectList">
            <div class="searchLabelLeft"></div>
            <div class="searchLabel" style="float:left; padding-right:5px;width:50px;margin-right:5px;text-align:right;">筛选:</div>
            <div class="itemLint xiala xl_w90" style="float:left; padding-bottom:15px;">
              <select name="ddhb_two_community.startSaleDate" columnname="ddhb_two_community.startSaleDate" searchitemid="20" id="20" onchange="searchCondition('ddhb_two_community.startSaleDate');">
                <option value="">房齡</option>
                <option value="1000-01-01@1995-01-01">1995年前</option>
                <option value="1995-01-01@2000-01-01">1995-2000年</option>
                <option value="2000-01-01@2005-01-01">2000-2005年</option>
                <option value="2005-01-01@2010-01-01">2005-2010年</option>
                <option value="2010-01-01@3000-01-01">2010年后</option>
              </select>
            </div>
            <div class="itemLint xiala xl_w90" style="float:left; padding-bottom:15px;">
              <select name="ddhb_one_orientations.id" columnname="ddhb_one_orientations.id" searchitemid="21" id="21" onchange="searchCondition('ddhb_one_orientations.id');">
                <option value="">朝向</option>
                <option value="1">东</option>
                <option value="2">西</option>
                <option value="3">南</option>
                <option value="4">北</option>
                <option value="5">东南</option>
                <option value="6">西南</option>
                <option value="7">东北</option>
                <option value="8">西北</option>
                <option value="9">东西</option>
                <option value="10">南北</option>
              </select>
            </div>
            <div class="itemLint xiala xl_w90" style="float:left; padding-bottom:15px;">
              <select name="ddhb_one_decoration.erpId" columnname="ddhb_one_decoration.erpId" searchitemid="22" id="22" onchange="searchCondition('ddhb_one_decoration.erpId');">
                <option value="">装修</option>
                <option value="1">精装</option>
                <option value="2">简装</option>
                <option value="3">毛坯</option>
                <option value="4">豪装</option>
                <option value="5">中装</option>
              </select>
            </div>
            <div class="itemLint xiala xl_w90" style="float:left; padding-bottom:15px;">
              <select name="ddhb_one_tags" columnname="ddhb_one_tags" searchitemid="23" id="23" onchange="searchCondition('ddhb_one_tags');">
                <option value="">卖点</option>
                <option value="030d8373-c5df-4326-92c3-1e453c115e73">学区房</option>
                <option value="29577b85-6d3e-43e5-a887-c8b3b0326953">投资保值</option>
                <option value="33fd041d-c444-4f83-9b77-426aac50cb3b">刚需小户</option>
                <option value="6d98f9e0-b3da-4296-9021-099c9925ee57">超值性价</option>
                <option value="80f9e697-a857-423a-b8e0-9e115699268f">次新房</option>
                <option value="8155a1ca-559d-460f-b379-9bf56eeec6b7">经济适用房</option>
                <option value="8a0d896d-a6d5-409f-8e92-1f9cd6480896">婚房</option>
                <option value="b91edfdd-5d11-48c0-af89-211ec3ae53fc">免双税</option>
                <option value="d4ca07b2-d470-49c1-8020-7259e2daef92">免营</option>
                <option value="d5f3120d-57b3-4b9a-b7e6-0c4dfd7845c5">免双</option>
                <option value="da8441f0-5238-42d2-96af-ccf28c67a376">经济用房</option>
                <option value="ebd3955b-7a26-49fe-8b8a-94bbb7d4f02c">免营业税</option>
              </select>
            </div>
          </div>
        </div>
      </div>
      <div class="clearDiv"></div>
      <div id="selectedSearchItemPanel">
        <div id="allSelectedTitle">本次找房条件:</div>
        <div id="allSelectedItemsDiv">&nbsp;</div>
        <div id="operationDiv">
          <div class="operationItemContainer disableOperation" diableflag="false" id="shareContainer">
            <div class="oprationIcon disabledShareIcon"></div>
            <div class="operationLabel">分享</div>
            <div class="clearDiv"></div>
          </div>
          <div class="operationItemContainer disableOperation" diableflag="false" id="saveContainer">
            <div class="oprationIcon disabledSaveIcon"></div>
            <div class="operationLabel">保存</div>
            <div class="clearDiv"></div>
          </div>
          <div class="operationItemContainer disableOperation" diableflag="false" id="clearContainer">
            <div class="oprationIcon disabledClearIcon"></div>
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

