<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Application project</title>
  <style>
    <%@ include file="style.css" %>
  </style>
</head>
<body>
<%
  request.setCharacterEncoding("UTF-8");
%>
<div class="container">
  <h3>User choice DataBase</h3>
  <form style="margin-top: 5px" class="form-inline" role="form" action="/search" method="post">
    <input style="width: 250px;" size="20px" type="text" class="form-control" name="pattern" placeholder="Search according to Text Field №1">
    <input type="submit" class="btn btn-default" value="Search">
  </form>
  <table class="table table-striped">
    <head>
    <tr>
      <td><b>Назва</b></td>
      <td><b>Автор</b></td>
      <td><b>Жанр</b></td>
    </tr>
    </head>
    <c:forEach items="${books}" var="book">
      <tr>
        <td>${book.bookName}</td>
        <td>${book.bookAuthor}</td>
        <td>${book.bookGenre}</td>
        <td><a href="/delete?bookId=${book.bookId}">Delete</a></td>
      </tr>
    </c:forEach>
  </table>

<span class="container" align="center">
    <form class="form-inline" role="form" action="/add_page" method="post">
      <input type="submit" class="btn btn-default" value="Fill info to add">
    </form>
</span>
</div>
</body>
</html>
