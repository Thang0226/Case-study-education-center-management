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
    <h1 class="my-4">All tutor</h1>
    <table class="table table-hover">
        <tr>
            <th>ID</th>
            <th>Tutor Name</th>
            <th>Class Name</th>
            <th>Subject Name</th>
            <th>Number of student teaching</th>
        </tr>
        <c:forEach var="tutor" items="${tutors}">
            <tr>
                <td>${tutor.getId()}</td>
                <td>
                    <c:forEach var="user" items="${users}">
                        <c:if test="${tutor.userID == user.id}">
                            ${user.fullName}
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <form action="students" method="POST" style="display:inline;">
                        <input type="hidden" name="action" value="list_students_by_class">
                        <input type="hidden" name="class_name" value="${clazz.name}" >
                        <button type="submit" style="all: unset; color: blue; text-decoration: underline; cursor: pointer;">
                            <c:forEach var="clazz" items="${clazzList}">
                                <c:if test="${clazz.tutorID == tutor.id}">
                                    ${clazz.getName()}
                                </c:if>
                            </c:forEach>
                        </button>
                    </form>
                </td>
                <td>
                    <c:forEach var="subjet" items="${subjects}">
                        <c:forEach var="clazz" items="${clazzList}">
                            <c:if test="${clazz.tutorID == tutor.id}">
                                <c:if test="${clazz.subjectID == subjet.id}">
                                    ${subjet.name}
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="entry" items="${map}">
                        <c:if test="${tutor.id == entry.key}">
                            ${entry.value}
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
    <script src="../styles/bootstrap.bundle.min.js"></script>
</body>
</html>
