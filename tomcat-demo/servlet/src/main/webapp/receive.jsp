<%--
  Created by IntelliJ IDEA.
  User: nano
  Date: 2024/9/18
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="myapp.Message" %>
<!DOCTYPE html>
<html>
<head>
    <title>接收文本</title>
</head>
<body>
<h2>接收文本</h2>
<%
    String message = request.getParameter("message");
    Message msg = new Message();
    msg.setContent(message);
%>
<p>接收到的消息: <%= msg.getContent() %></p>
</body>
</html>
