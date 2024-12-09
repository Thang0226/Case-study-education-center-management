<%--
  Created by IntelliJ IDEA.
  User: thang
  Date: 08/12/24
  Time: 11:26
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
        <h2 class="text-center mb-3">
            List of Students
        </h2>
        <table class="table table-bordered table-light table-striped table-hover">
            <thead class="table-light">
            <tr>
                <th scope="col" class="col-1">User ID</th>
                <th scope="col" class="col-2">Full Name</th>
                <th scope="col" class="col-2">Email</th>
                <th scope="col" class="col-2">
                    <form action="/officer" method="POST">
                        <input type="hidden" name="action" value="list_students"/>
                        <div class="row align-items-center my-3">
                            <div class="col-8 p-1 ms-2">
                                <select name="clazz" id="clazz" class="form-select me-2">
                                    <option value="" selected>Class</option>
                                    <c:forEach items="${requestScope.clazzes}" var="clazz">
                                        <option value="${clazz.name}">${clazz.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-3 p-1">
                                <button type="submit" class="btn btn-secondary ms-0">Filter</button>
                            </div>
                        </div>
                    </form>
                </th>
                <th scope="col" class="col-2">Student Status</th>
                <th scope="col" class="col-1">Tuition Status</th>
                <th scope="col" class="col-1">Student Scores</th>
            </tr>
            </thead>
            <tbody class="table-group-divider">
            <c:forEach items="${requestScope.students}" var="student">
                <tr>
                    <th scope="row">${student.id}</th>
                    <td>${student.fullName}</td>
                    <td>${student.email}</td>
                    <td>${student.className}</td>
                    <td>
                        <div class="row">
                            <div class="col-8 d-flex align-items-center justify-content-center">
                                    ${student.studentStatus}
                            </div>
                            <div class="col-4">
                                <form action="/officer" method="POST" style="display:inline;">
                                    <input type="hidden" name="action" value="edit_student">
                                    <input type="hidden" name="id" value="${student.id}">
                                    <button type="submit" class="btn btn-primary">Edit</button>
                                </form>
                            </div>
                        </div>
                    </td>
                    <td>${student.tuitionStatus}</td>
                    <td>
                        <form action="/officer" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="edit_student_scores">
                            <input type="hidden" name="student_id" value="${student.id}">
                            <button type="submit" class="btn btn-success">Update</button>
                        </form>
                        <form action="/officer" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="add_student_scores">
                            <input type="hidden" name="student_id" value="${student.id}">
                            <button type="submit" class="btn btn-info">Create</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="../styles/bootstrap.bundle.min.js"></script>
</body>
</html>
