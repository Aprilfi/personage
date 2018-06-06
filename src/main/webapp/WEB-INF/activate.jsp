<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/5 0005
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2><font color="green">${username}，您好！</font></h2>
<p>用户激活页面</p>
<p>您的账号${content}</p>
<a href="${path}/index.jsp">返回登录</a>
</body>
</html>
