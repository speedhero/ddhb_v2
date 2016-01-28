<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="act-menu">
        <div class="mt-menu-tree">
            <dl class="mt-menu-item">
                <dt class="fs14">个人信息管理</dt>
                <dd><a href="#" onclick="getData('${globalUrl}memberInfo.do?actionMethod=editMemberInfo','','workspace');">基本信息</a></dd>
                <dd><a href="#" onclick="getData('${globalUrl}memberInfo.do?actionMethod=initModifyHeader','','workspace');">修改头像</a></dd>
                <dd><a href="#" onclick="getData('${globalUrl}memberInfo.do?actionMethod=changePassword','','workspace');">修改密码</a></dd>
            </dl>
            <dl class="mt-menu-item">
                <dt class="fs14"><a href="#" onclick="getData('${globalUrl}memberInfo.do?actionMethod=editMemberInfo','','workspace');">积分管理</a></dt>
            </dl>
            <dl class="mt-menu-item">
                <dt class="fs14"><a href="#" onclick="getData('${globalUrl}memberInfo.do?actionMethod=editMemberInfo','','workspace');">问答管理</a></dt>
            </dl>
            <dl class="mt-menu-item">
                <dt class="fs14"><a href="#" onclick="getData('${globalUrl}memberBespeak.do?actionMethod=getMemberBespeakList','','workspace');">预约管理</a></dt>
            </dl>
            <dl class="mt-menu-item">
                <dt class="fs14"><a href="#" onclick="getData('${globalUrl}memberInfo.do?actionMethod=editMemberInfo','','workspace');">置换价格订阅管理</a></dt>
            </dl>
           <dl class="mt-menu-item">
                <dt class="fs14"><a href="#" onclick="getData('${globalUrl}memberInfo.do?actionMethod=editMemberInfo','','workspace');">小区收藏夹管理</a></dt>
            </dl>
            <dl class="mt-menu-item">
                <dt class="fs14"><a href="#" onclick="getData('${globalUrl}memberInfo.do?actionMethod=editMemberInfo','','workspace');">小区浏览历史记录</a></dt>
            </dl>
            <dl class="mt-menu-item">
                <dt class="fs14"><a href="#" onclick="getData('${globalUrl}memberInfo.do?actionMethod=editMemberInfo','','workspace');">收藏夹管理</a></dt>
            </dl>
            <dl class="mt-menu-item">
                <dt class="fs14"><a href="#" onclick="getData('${globalUrl}memberInfo.do?actionMethod=editMemberInfo','','workspace');">浏览记录管理</a></dt>
            </dl>
            
        </div>
</div>
