<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.03.2016
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html meta http-equiv='Content-Type' content='text/html; charset=UTF-8' >
<head>
    <title>Title</title>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Application Project</title>
    <style>
        <%@ include file="style.css" %>
    </style>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
%>
<h3 align="center">Data to add to Database</h3>

<span class="container" align="center">
<form role="form" class="form-horizontal" action="/add" method="post">
    <table width="100%">
        <tr>
            <td>
                <input style="margin-left: 23%;" name = "bookName" type="text" size="15" value="Enter your name here!">
            </td>
            <td>
                <input style="margin-left: 23%;" name = "bookAuthor" type="text" size="15" value="Enter your name here!">
            </td>
            <td>
                <input style="margin-left: 20%;" name="bookGenre" type="text" size="15" value="Enter your name here!">
            </td>
        </tr>
    </table>
    <input style="margin-left: 5px;" type="submit" class="btn btn-default" value="Add info to DB">
</form>
</span>
<span class="container" align="center">
    <form class="form-inline" role="form" action="/showDB" method="post">
        <input style="margin-left: 5px;" type="submit" class="btn btn-default" value="Back to DB">
    </form>
</span>
</body>
</html>

</body>
</html>
