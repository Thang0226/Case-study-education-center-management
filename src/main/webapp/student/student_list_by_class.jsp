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
                <th scope="col" class="col-2">
                    <form action="students" method="POST" style="display:inline;">

                        <input type="hidden" name="action" value="list_students_by_status" />
                        <input type="hidden" name="class_name" value="${requestScope.class_name}" />

                        <select name="status_name" id="status_name">
                            <option value="" selected disabled>Status</option>
                            <c:forEach items="${requestScope.studentStatusList}" var="status">
                                <option value="${status.name}">${status.name}</option>
                            </c:forEach>
                        </select>
                        <button type="submit">Filter</button>
                    </form>
                </th>
            </tr>
            </thead>
            <tbody class="table-group-divider">
            <c:forEach items="${requestScope.students}" var="student">
                <tr>
                    <th scope="row">${student.id}</th>
                    <td>
                        <form action="students" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="view_student">
                            <input type="hidden" name="id" value="${student.id}">
                            <button type="submit"
                                    style="all: unset; color: black; text-decoration: none; cursor: pointer;">
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
