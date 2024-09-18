<%--
  Created by IntelliJ IDEA.
  User: nano
  Date: 2024/9/18
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>发送文本</title>
</head>
<body>
<h2>发送文本</h2>
<form action="receive.jsp" method="post">
    <label for="message">消息:</label>
    <input type="text" id="message" name="message" required>
    <button type="submit">发送</button>
</form>
</body>
</html>