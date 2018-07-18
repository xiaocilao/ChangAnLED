<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<link rel="stylesheet" href="css/top10.css" type="text/css" />
<style type="text/css">
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Top10展示</title>
<script type="text/javascript">
setTimeout("location.href='http://localhost:8080/Led/top10'",10000);
</script>
</head>
<body>	
	<div id="all">
		<div id="table_title">
			<div id="title">鱼嘴基地总装车间TOP问题数据展示</div>
			<div id="shift">&nbsp&nbsp&nbsp&nbsp班次： A班</div>
			<div id="time">时间：${nowTime}&nbsp</div>
		</div>	
		<div id="left_table">
			<div class="second_title">当班质量top问题</div>
			<table>
				<tr><td>序号</td><td>车型</td><td>问题</td><td>数量</td></tr>
				<c:forEach items="${dataList}" var="dataItem" varStatus="rowStatus" >
				<tr id="content" class="StopLine10">
				<td class="one">${ rowStatus.index+1}</td>
				<td class="two">${dataItem.MQSC_CMCODE}</td>
				<td class="three">${dataItem.MQSC_ICC}</td>
				<td class="four">${dataItem.NUM}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<div style="clear:both"></div>
	<button onclick="location.href='http://localhost:8080/Led/led'">总装车间生产总览</button>&nbsp&nbsp<button onclick="location.href='http://localhost:8080/Led/ueditor1_4_3/index.html'">车间文化</button>
	<button onclick="location.href='http://localhost:8080/Led/stoptop10'">停线top</button>&nbsp&nbsp
</body>
</html>