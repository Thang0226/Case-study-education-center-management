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
            <thead class="table-light">
            <tr>
                <th scope="col" class="col-1">Student ID</th>
                <th scope="col" class="col-3">Full Name</th>
                <th scope="col" class="col-2">Email</th>
                <th scope="col" class="col-2">Date of Birth</th>
                <th scope="col" class="col-2">Address</th>
                <th scope="col" class="col-2">Phone number</th>
                <th scope="col" class="col-2">Status</th>
            </tr>
            </thead>
            <tbody class="table-group-divider">
                <tr>
                    <td>${requestScope.student.id}</td>
                    <td>${requestScope.student.fullName}</td>
                    <td>${requestScope.student.email}</td>
                    <td>${requestScope.student.dateOfBirth}</td>
                    <td>${requestScope.student.address}</td>
                    <td>${requestScope.student.phoneNumber}</td>
                    <td>${requestScope.student.studentStatus}</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<script src="../styles/bootstrap.bundle.min.js"></script>
</body>
</html>
