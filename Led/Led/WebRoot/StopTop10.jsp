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
<link rel="stylesheet" href="css/stoptop10.css" type="text/css" />
<style type="text/css">
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Top10展示</title>
<script type="text/javascript">
setTimeout("location.href='http://localhost:8080/Led/stoptop10'",10000);
</script>
</head>
<body>	
	<div id="all">
		<div id="table_title">
			<div id="title">鱼嘴基地总装车间TOP问题数据展示</div>
			<div id="shift">&nbsp&nbsp&nbsp&nbsp班次： A班</div>
			<div id="time">时间：${nowTime} &nbsp</div>
		</div>	
		<div id="right_table">
			<div class="second_title">当班停线top问题</div>
			<table>
				<tr><td>序号</td><td>生产线</td><td>工位</td><td>停线时间</td><td>停线原因</td></tr>
				<tbody id="tbody">
				<c:forEach items="${dataList}" var="dataItem" varStatus="rowStatus" >
				<tr id="content" class="StopLine10">
				<td class="one">${ rowStatus.index+1}</td>
				<td class="two">${dataItem.PLINE_NO}</td>
				<td class="three">${dataItem.STATION_NO}</td>
				<td class="four">${dataItem.TIME}</td>
				<td class="five">${dataItem.REMARK}</td>
				
				</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<button onclick="location.href='http://localhost:8080/Led/led'">总装车间生产总览</button>&nbsp&nbsp<button onclick="location.href='http://localhost:8080/Led/ueditor1_4_3/index.html'">车间文化</button>
	<button onclick="location.href='http://localhost:8080/Led/top10'">质量top10</button>
</body>
</html>