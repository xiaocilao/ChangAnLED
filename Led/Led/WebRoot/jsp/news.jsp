<%@ page language="java" contentType="text/html; charset=utf-8"  
    pageEncoding="utf-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title>news</title>  
<style type="text/css">  
.news {  
    width: 800px;  
    margin: 0 auto;  
}  
</style>  
</head>  
<body>  
    <%  
        request.setCharacterEncoding("utf-8");  
        String newsbody = request.getParameter("editorValue");  
    %>  
  
    <div class="news">  
        <%=newsbody%>  
  
    </div>  
</body>  
</html>  