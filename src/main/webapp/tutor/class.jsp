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
<head>
    <link rel="stylesheet" href="../styles/bootstrap.min.css">
    <title>Tutor list</title>
    <style>
        table {
            border-collapse: collapse;
        }
        table th, td {
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <img src="https://static.topcv.vn/company_logos/0ZT9refQobeAkpzsYWBdyaki10IlbFB4_1655288503____f48c9fc932b36c4eec44ec23d223fa18.png"
                     alt="logo" class="img-fluid" style="max-height: 50px">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">User Management</a>
                    </li>
                </ul>
                <form action="../login.jsp" method="POST" class="ms-auto my-auto">
                    <button type="submit" class="btn btn-secondary">Log Out</button>
                </form>
            </div>
        </div>
    </nav>

    <h1 class="my-4">All tutor</h1>
    <table class="table table-hover">
        <tr>
            <th>ID</th>
            <th>
                <form action="tutors" method="POST" style="display: inline">
                    <div class="row align-items-center my-3">
                        <div class="col-8 p-1 ms-2">
                            <select name="tutor_id" id="tutor_id" class="form-select me-2">
                                <option value="" selected>Tutor Name</option>
                                <c:forEach items="${requestScope.tutors}" var="tutor">
                                    <option value="${tutor.id}">
                                        <c:forEach var="user" items="${requestScope.users}">
                                            <c:if test="${tutor.userID == user.id}">
                                                ${user.fullName}
                                            </c:if>
                                        </c:forEach>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-3 p-1">
                            <button type="submit" class="btn btn-secondary ms-0" id="class_filter">
                                Filter
                            </button>
                        </div>
                    </div>
                </form>
            </th>
            <th>Class Names</th>
            <th>Subject Name</th>
            <th>Number of students teaching</th>
        </tr>
        <c:forEach var="tutor" items="${requestScope.tutorList}">
            <tr>
                <td>${tutor.getId()}</td>
                <td>
                    <c:forEach var="user" items="${requestScope.users}">
                        <c:if test="${tutor.userID == user.id}">
                            ${user.fullName}
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <form action="students" method="GET" style="display:inline;">
                        <input type="hidden" name="action" value="list_students_by_class">
                        <c:forEach var="clazz" items="${requestScope.clazzList}">
                            <c:if test="${clazz.tutorID == tutor.id}">
                                <button type="submit"
                                        style="all: unset; color: blue; text-decoration: underline; cursor: pointer;">
                                        ${clazz.getName()}
                                    <input type="hidden" name="class_name" value="${clazz.name}">
                                </button>
                            </c:if>
                        </c:forEach>

                    </form>
                </td>
                <td>
                    <c:forEach var="subject" items="${subjects}">
                        <c:forEach var="clazz" items="${clazzList}">
                            <c:if test="${clazz.tutorID == tutor.id}">
                                <c:if test="${clazz.subjectID == subject.id}">
                                    ${subject.name}
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
