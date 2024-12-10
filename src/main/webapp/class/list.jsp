<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 09/12/2024
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Class List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <img src="https://static.topcv.vn/company_logos/0ZT9refQobeAkpzsYWBdyaki10IlbFB4_1655288503____f48c9fc932b36c4eec44ec23d223fa18.png"
                     alt="logo" class="img-fluid" style="max-height: 50px">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
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
    <div class="row">
        <form method="get" class="mt-2">
            <button type="submit" class="btn btn-success" name="action" value="create">
                Create new class
            </button>
        </form>
        <div class="col-sm-12 col-md-12 col-lg-12">
            <table class="table table-hover align-middle">
                <thead>
                    <tr>
                        <th class="text-center">Class Id</th>
                        <th>Class Name</th>
                        <th>Tutor Name</th>
                        <th>Subject Name</th>
                        <th class="text-center">Number of students</th>
                        <th class="text-center">Average score of all students</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="clazz" items="${classList}">
                        <tr>
                            <td class="text-center">${clazz.classId}</td>
                            <td>
                                <a href="students?action=list_students_by_class&class_name=${clazz.className}"
                                   class="link-dark link-offset-2 link-underline-opacity-0 link-underline-opacity-100-hover">
                                        ${clazz.className}
                                </a>
                            </td>
                            <td>${clazz.tutorName}</td>
                            <td>${clazz.subjectName}</td>
                            <td class="text-center">${clazz.studentNumber}</td>
                            <td class="text-center"><fmt:formatNumber value="${clazz.avgScore}" type="number" minFractionDigits="2" maxFractionDigits="2" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</body>
</html>
