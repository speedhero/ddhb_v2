<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%!
/**
 * 
 * @throws IOException
 * @brief 生成缩略图简单实例
 * 
 */
public static void zoom(String srcFile, String destFile, int width, int height) throws java.io.IOException {
	net.coobird.thumbnailator.Thumbnails.of(srcFile)
	.size(width, height)
	.outputFormat("jpg")
	.outputQuality(1.0f)
	.toFile(destFile);
}

/**
 * 缩放多次测试
 * @param srcFile
 * @param destFile
 * @param width
 * @param height
 * @param count
 * @return
 * @throws IOException
 */
public static long zoom(String srcFile, String destFile, int width, int height, int count) throws java.io.IOException {
	Long l = System.currentTimeMillis();
	for(int ii=0; ii<count; ii++){
		zoom(srcFile, destFile, width, height);
		java.io.File f = new java.io.File(destFile);
		f.delete();
	}
	return System.currentTimeMillis() - l;
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试页面</title>
</head>

<body>
<%
String name = request.getParameter("name");
if(name != null){
	out.print("欢迎您，"+name+"！");
}else{
	out.print("请赐名！");
}
%>
<br/>
${MyName}<br/>
${globalUrl}<br/>
我的房源：<br/>
<a href='${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=HWYC1412080652&brokerId=259bef23-cee9-459f-8e57-97ab1fce58d5'>测试1</a><br/>
<a href='${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=QJB002163&brokerId=40296993-5ca0-41f7-ad10-06046fb493d6'>测试2</a><br/>
<a href='${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=CH018402&brokerId=bc71fc92-3bdd-4b20-9c31-5b6fee6450e0'>测试2</a><br/>
<a href='${globalUrl}sso.show?actionMethod=logout'>测试4</a><br/>
<a href='${globalUrl}houseNew.show?actionMethod=dimquery'>测试5</a><br/>
图片缩放测试：<br/>
<%
String srcFile = "/web/HBWebsitePictures/community/2015/03/3006_20150324230235560.JPG";
String destFile = "/web/HBWebsitePictures/3006_20150324230235560_400x300.JPG";
int width = 400;
int height = 300;
long times = cn.hshb.web.test.ThumbnailatorTest.zoom(srcFile, destFile, width, height, 100);
out.println("100次耗时: " + times + "<br/>");

times = cn.hshb.web.test.ThumbnailatorTest.zoom(srcFile, destFile, width, height, 1000);
out.println("1000次耗时: " + times + "<br/>");

times = cn.hshb.web.test.ThumbnailatorTest.zoom(srcFile, destFile, width, height, 10000);
out.println("10000次耗时: " + times + "<br/>");

//times = cn.hshb.web.test.ThumbnailatorTest.zoom(srcFile, destFile, width, height, 100000);
//out.println("100000次耗时: " + times + "<br/>");

%>
</body>
</html>