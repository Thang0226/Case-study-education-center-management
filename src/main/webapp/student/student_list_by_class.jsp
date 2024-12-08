<%--
  Created by IntelliJ IDEA.
  User: thang
  Date: 07/12/24
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student List</title>
    <link rel="stylesheet" href="../styles/bootstrap.min.css">
    <style>
        table th, table td {
            text-align: center;
            vertical-align: middle;
        }
        td a {
            color: black;
        }
        button a {
            color: white;
            text-decoration: none;
            text-transform: capitalize;
        }
    </style>
</head>

<body>

<div class="container" style="margin-top:100px;">
    <div class="table-responsive">
        <h2 class="text-center">
            List of Students in class ${requestScope.class_name}
        </h2>
        <table class="table table-bordered table-light table-striped table-hover">
            <thead class="table-light">
            <tr>
                <th scope="col" class="col-1">Student ID</th>
                <th scope="col" class="col-3">Full Name</th>
                <th scope="col" class="col-2">Email</th>
                <th scope="col" class="col-2">Phone number</th>
                <th scope="col" class="col-2">Status</th>
            </tr>
            </thead>
            <tbody class="table-group-divider">
            <c:forEach items="${requestScope.students}" var="student">
                <tr>
                    <th scope="row">${student.id}</th>
                    <td>
                        <form action="/students" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="view_student">
                            <input type="hidden" name="id" value="${student.id}">
                            <button type="submit" style="all: unset; color: blue; text-decoration: underline; cursor: pointer;">
                                    ${student.fullName}
                            </button>
                        </form>
                    </td>
                    <td>${student.email}</td>
                    <td>${student.phoneNumber}</td>
                    <td>${student.studentStatus}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="../styles/bootstrap.bundle.min.js"></script>
</body>
</html>
