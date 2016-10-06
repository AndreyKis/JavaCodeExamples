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
<h3 align="center">Data to add to Database</h3>

<span class="container" align="center">
<form role="form" enctype="multipart/form-data" class="form-horizontal" action="/add" method="post">
    <table width="100%">
        <tr>
            <td width="30%">
                <textarea name = "txtArea" style="margin-left: 20px; padding-left: 20px; border: solid 1px lightgray; width: 100%; -webkit-box-sizing: border-box; -moz-box-sizing: border-box; box-sizing: border-box;"
                          rows="4" cols="50">Type the text you want to add to database here</textarea>
            </td>
            <td>
                <input style="margin-left: 23%;" name = "txtField1" type="text" size="15" value="Enter your name here!">
            </td>
            <td>
                <input style="margin-left: 20%;" name="txtField2" type="text" size="15" value="Enter your name here!">
            </td>
            <td><div style="margin-left: 10%;margin-right:10%;">
                <input type="radio" name="group1" value="var1">&nbsp;var1<br>
                <input type="radio" name="group1" value="var2" checked>&nbsp;var2<br>
                <input type="radio" name="group1" value="var3">&nbsp;var3<br>
                <input type="radio" name="group1" value="var4">&nbsp;var4<br>
                <input type="radio" name="group1" value="var5">&nbsp;var5<br>
            </div></td>
            <td>

                <div style="margin-left: 10%;">
                <select name = "Multi-Select" multiple size="4">
                    <option>I am the first variant</option>
                    <option>I am the second variant</option>
                    <option>I am the third variant</option>
                    <option>I am the fourth variant</option>
                </select>
            </div>

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
