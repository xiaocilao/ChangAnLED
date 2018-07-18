<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
 setTimeout("location.href='http://localhost:8080/Led/led'",6000);
 </script>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body {color:red}
table {border:1px solid #F00}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<img src="F:\LED图片上传\上传图片.jpg">
</body>
</html>


