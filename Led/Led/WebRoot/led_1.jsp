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
<script type="text/javascript" src="WEB-INF/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	function changeColor(){
	TA1=document.getElementById("TA1");
	TA2=document.getElementById("TA2");
	TA3=document.getElementById("TA3");
	TA4=document.getElementById("TA4");
	CA1=document.getElementById("CA1");
	CA2=document.getElementById("CA2");
	CA3=document.getElementById("CA3");
	FA1=document.getElementById("FA1");
	FA2=document.getElementById("FA2");
	IP=document.getElementById("IP");
	DA=document.getElementById("DA");
	PT=document.getElementById("PT");


	
	document.getElementById("C301_plan").innerHTML=${C301_plan};
	document.getElementById("S201_plan").innerHTML=${S201_plan};
	document.getElementById("V301_plan").innerHTML=${V301_plan};
	document.getElementById("V301s_plan").innerHTML=${V301s_plan};
	document.getElementById("planCount").innerHTML=${planCount};
	
	document.getElementById("C301_finshed").innerHTML=${finsh_1};
	document.getElementById("S201_finshed").innerHTML=${finsh_2};
	document.getElementById("V301_finshed").innerHTML=${finsh_3};
	document.getElementById("V301s_finshed").innerHTML=${finsh_4};
	document.getElementById("total_finshed").innerHTML=${finsh_5};
	
	document.getElementById("C301_differ").innerHTML=${diff_1};
	document.getElementById("S201_differ").innerHTML=${diff_2};
	document.getElementById("V301_differ").innerHTML=${diff_3};
	document.getElementById("V301s_differ").innerHTML=${diff_4};
	document.getElementById("total_differ").innerHTML=${diff_5};
	
	document.getElementById("jph").innerHTML=${JPH};
	
	
	planCount=document.getElementById("planCount");
	
		if(0==${TA1}){
			TA1.style.color="#00FF00";
		}else if(1==${TA1}){
			TA1.style.color="yellow";
		}else if(2==${TA1}){
			TA1.style.color="red";
		}
		if(0==${TA2}){
			TA2.style.color="#00FF00";
		}else if(1==${TA2}){
			TA2.style.color="yellow";
		}else if(2==${TA2}){
			TA2.style.color="red";
		}
		if(0==${TA3}){
			TA3.style.color="#00FF00";
		}else if(1==${TA3}){
			TA3.style.color="yellow";
		}else if(2==${TA1}){
			TA3.style.color="red";
		}
		if(0==${TA4}){
			TA4.style.color="#00FF00";
		}else if(1==${TA4}){
			TA4.style.color="yellow";
		}else if(2==${TA4}){
			TA4.style.color="red";
		}
		if(0==${CA1}){
			CA1.style.color="#00FF00";
		}else if(1==${CA1}){
			CA1.style.color="yellow";
		}else if(2==${CA1}){
			CA1.style.color="red";
		}
		if(0==${CA2}){
			CA2.style.color="#00FF00";
		}else if(1==${CA2}){
			CA2.style.color="yellow";
		}else if(2==${CA2}){
			CA2.style.color="red";
		}
		if(0==${CA3}){
			CA3.style.color="#00FF00";
		}else if(1==${CA3}){
			CA3.style.color="yellow";
		}else if(2==${CA3}){
			CA3.style.color="red";
		}
		if(0==${FA1}){
			FA1.style.color="#00FF00";
		}else if(1==${FA1}){
			FA1.style.color="yellow";
		}else if(2==${FA1}){
			FA1.style.color="red";
		}
		if(0==${FA2}){
			FA2.style.color="#00FF00";
		}else if(1==${FA2}){
			FA2.style.color="yellow";
		}else if(2==${FA2}){
			FA2.style.color="red";
		}
		if(0==${IP}){
			IP.style.color="#00FF00";
		}else if(1==${IP}){
			IP.style.color="yellow";
		}else if(2==${IP}){
			IP.style.color="red";
		}
		if(0==${DA}){
			DA.style.color="#00FF00";
		}else if(1==${DA}){
			DA.style.color="yellow";
		}else if(2==${DA}){
			DA.style.color="red";
		}
		if(0==${PT}){
			PT.style.color="#00FF00";
		}else if(1==${PT}){
			PT.style.color="yellow";
		}else if(2==${PT}){
			PT.style.color="red";
		}

		
		
	}
	window.onload=changeColor;
	setTimeout("location.href='http://localhost:8080/Led/led'",3000);
	
  </script>
<style type="text/css">
body {color:#FF0033}
table {border:3px solid #FF3333}
div{
font-size:55px;
font-weight:bold;
}
span{
font-size:41px;
font-weight:600;
}
td{
width：10 px;
height: 10 px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>乘用车鱼嘴基地总装车间生产总览</title>
</head>
<body>
	<table border="0px" width="1250px" height="700px" cellspacing="1" cellpadding="4" bgcolor="000000" 
	       bordercolor="green" align="center">
	<tr><td colspan="6" align="center" style="border:0px solid green"><div><font face="微软雅黑"  size="8">乘用车鱼嘴基地总装车间生产总览</font></div></td></tr>
	<tr><td colspan="6" style="border:0px solid red"><span style="float:left" id="times"><font face="微软雅黑"  size="8">当前班次：</font><s:property value="number"/></span><span style="float:right" id="now_time"><font face="微软雅黑"  size="8">当前时间：</font><s:property value="time"/></span></td></tr>
	<tr><td><div><font color="#00FF00" size="8" face="微软雅黑" >车型&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</font></div></td><td><div><font color="#00FF00" size="8" face="微软雅黑">计划量</font></div></td><td><div><font color="#00FF00" size="8" face="微软雅黑">完成量</font></div></td><td><div><font color="#00FF00" size="8" face="微软雅黑">差异</font></div></td><td><div><font color="#00FF00" size="8" face="微软雅黑">JPH&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</font></div></td><td><div><font color="#00FF00" size="8" face="微软雅黑">FTT</font></div></td></tr>
	
	<tr><td><div id="C301_id" ><s:property value="audi_1" /></div></td><td><div id="C301_plan"></div></td><td><div id="C301_finshed"></div></td><td><div id="C301_differ"></div></td><td rowspan="5"><div id="jph"></div></td><td><div id="C301_ftt"><s:property value="FTT_1"/></div></td></tr>
	<tr><td><div id="S201_id"><s:property value="audi_2" /></div></td><td><div id="S201_plan"></div></td><td><div id="S201_finshed"></div></td><td><div id="S201_differ"></div></td><td><div id="S201_ftt"><s:property value="FTT_2"/></div></td></tr>
	<tr><td><div id="V301_id"><s:property value="audi_3" /></div></td><td><div id="V301_plan"></div></td><td><div id="V301_finshed"></div></td><td><div id="V301_differ"></div></td><td><div id="V301_ftt"><s:property value="FTT_3"/></div></td></tr>
	<tr><td><div id="V301s_id"><s:property value="audi_4" /></div></td><td><div id="V301s_plan"></div></td><td><div id="V301s_finshed"></div></td><td><div id="V301s_differ"></div></td><td><div id="V301s_ftt"><s:property value="FTT_4"/></div></td></tr>
	<tr><td><div><font face="微软雅黑"  size="7">合计</font></div></td><td><div id="planCount"></div></td><td><div id="total_finshed"></div></td><td><div id="total_differ"></div></td><td><div id="total_ftt"><s:property value="FTT_5"/></font></div></td></tr>
	<tr><td colspan="6">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<span id="TA1">TA1&nbsp</span> <span id="TA2">TA2&nbsp</span> <span id="TA3">TA3&nbsp</span> <span id="TA4">TA4&nbsp</span> <span id="CA1">CA1&nbsp</span> <span id="CA2">CA2&nbsp</span> <span id="CA3">CA3&nbsp</span><span id="FA1">FA1&nbsp</span> <span id="FA2">FA2&nbsp</span> <span id="IP">IP&nbsp</span> <span id="DA">DA&nbsp</span> <span id="PT">PT&nbsp</span></td></tr>
	
	</table>
	<button onclick="location.href='http://localhost:8080/Led/ueditor1_4_3/index.html'">车间文化</button>&nbsp&nbsp<button onclick="location.href='http://localhost:8080/Led/top10'">质量top10问题</button>
	<button onclick="location.href='http://localhost:8080/Led/stoptop10'">停线top10问题</button>
	<s:iterator value="listPlanProduct" status="st" id="singleLevel">
	<s:property value="#request.listPlanProduct.size()"  />
	<s:property value="#singleLevel[1]" />
	<s:property value="#singleLevel[0]" />
	</s:iterator>
</body>
</html>