<%--
  Created by IntelliJ IDEA.
  User: thang
  Date: 08/12/24
  Time: 09:26
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
            Class ${requestScope.student.className}
        </h2>
        <table class="table table-bordered table-light table-striped table-hover">
            <tr>
                <th>Student ID</th>
                <td>${requestScope.student.id}</td>
            </tr>
            <tr>
                <th>Full Name</th>
                <td>${requestScope.student.fullName}</td>
            </tr>
            <tr>
                <th>Email</th>
                <td>${requestScope.student.email}</td>
            </tr>
            <tr>
                <th>Date of Birth</th>
                <td>${requestScope.student.dateOfBirth}</td>
            </tr>
            <tr>
                <th>Address</th>
                <td>${requestScope.student.address}</td>
            </tr>
            <tr>
                <th>Phone number</th>
                <td>${requestScope.student.phoneNumber}</td>
            </tr>
            <tr>
                <th>Status</th>
                <td>${requestScope.student.studentStatus}</td>
            </tr>
        </table>
    </div>
</div>
<script src="../styles/bootstrap.bundle.min.js"></script>
</body>
</html>
