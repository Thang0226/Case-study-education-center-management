<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 06/12/2024
  Time: 4:44 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><link rel="stylesheet" href="../styles/bootstrap.min.css">
    <title>Tutor list</title>
</head>
<body>
<div class="container">
    <h1 class="my-4">All classes</h1>
    <table class="table table-hover">
        <tr>
            <th>ID</th>
            <th>Class Name</th>
            <th>Tutor ID</th>
            <th>Subject ID</th>
        </tr>
        <c:forEach var="clazz" items="${clazzList}">
            <tr>
                <td>${clazz.getId()}</td>
                <td>
                    <form action="/students" method="POST" style="display:inline;">
                        <input type="hidden" name="action" value="list_students_by_class">
                        <input type="hidden" name="class_name" value="${clazz.name}" >
                        <button type="submit" style="all: unset; color: blue; text-decoration: underline; cursor: pointer;">
                                ${clazz.getName()}
                        </button>
                    </form>
                </td>
                <td>${clazz.getTutorID()}</td>
                <td>${clazz.getSubjectID()}</td>
            </tr>
        </c:forEach>
    </table>
</div>
    <script src="../styles/bootstrap.bundle.min.js"></script>
</body>
</html>
