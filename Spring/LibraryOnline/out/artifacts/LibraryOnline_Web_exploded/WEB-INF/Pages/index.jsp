<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Application project</title>
  <style>
    <%@ include file="style.css" %>
  </style>
</head>
<body>
<div class="container">
  <h3>User choice DataBase</h3>
  <form style="margin-top: 5px" class="form-inline" role="form" action="/search" method="post">
    <input style="width: 250px;" size="20px" type="text" class="form-control" name="pattern" placeholder="Search according to Text Field №1">
    <input type="submit" class="btn btn-default" value="Search">
  </form>
  <table class="table table-striped">
    <thead>
    <tr>
      <td><b>Text Area</b></td>
      <td><b>Text Field №1</b></td>
      <td><b>Text Field №2</b></td>
    </tr>
    </thead>
    <c:forEach items="${books}" var="book">
      <tr>
        <td>${book.Name}</td>
        <td>${book.Author}</td>
        <td>${book.Genre}</td>
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
