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
        <h2 class="text-center mb-3">
            List of Students
        </h2>
        <table class="table table-bordered table-light table-striped table-hover">
            <thead class="table-light">
            <tr>
                <th scope="col" class="col-1">Student ID</th>
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
                                <button type="submit" class="btn btn-secondary ms-0" id="class_filter">
                                    Filter
                                </button>
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
                                    <input type="hidden" name="student_id" value="${student.id}">
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
                        <hr class="my-1">
                        <form action="/officer" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="create_student_scores">
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
<% String needClick = request.getParameter("click");
    if (needClick.equals("yes")) {%>
<script>
    document.getElementById("class_filter").click();
</script>
<%}%>
</body>
</html>