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

<!-- Back to Home Link -->
<div class="text-center my-4">
    <form action="/officer" method="POST" style="display:inline;">
        <input type="hidden" name="action" value="list_students" />
        <button type="submit" class="btn btn-success">Back to Student List</button>
    </form>
</div>

<!-- Message Section -->
<p class="text-center">
    <c:if test='${requestScope.message != null}'>
        <span class="text-success fw-bold">${requestScope.message}</span>
    </c:if>
</p>

<!-- Form Section -->
<div class="container">
    <form action="/officer" method="post" class="p-4 bg-white border rounded col-md-12 col-lg-6 mx-auto">
        <input type="hidden" name="action" value="update_student" />
        <fieldset>
            <legend class="text-primary">Student Information</legend>
            <div class="table-responsive">
                <table class="table table-bordered table-light table-striped table-hover">
                    <tr>
                        <th>Student ID:</th>
                        <td>
                            ${requestScope.student.id}
                            <input type="hidden" name="id" value="${requestScope.student.id}">
                        </td>
                    </tr>
                    <tr>
                        <th>Full Name:</th>
                        <td>${requestScope.user.fullName}</td>
                    </tr>
                    <tr>
                        <th>Email:</th>
                        <td>${requestScope.user.email}</td>
                    </tr>
                    <tr>
                        <th>Phone number:</th>
                        <td>${requestScope.user.phone}</td>
                    </tr>
                    <tr>
                        <th>Class:</th>
                        <td>${requestScope.clazz.name}</td>
                    </tr>
                    <tr>
                        <th>Tuition Status:</th>
                        <td>${requestScope.tuition_status.name}</td>
                    </tr>
                    <tr>
                        <th>
                            <label for="student_status_id" class="form-label">Student Status:</label>
                        </th>
                        <td>
                            <select class="form-select" name="student_status_id" id="student_status_id">
                                <c:forEach items="${requestScope.student_status_list}" var="status">
                                    <option value="${status.id}" class="text-center"
                                        <c:if test="${status.id == requestScope.student.studentStatusID}">
                                            selected</c:if>>
                                            ${status.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
        </fieldset>
        <div class="text-center">
            <input type="submit" value="Update Information" class="btn btn-primary">
        </div>
    </form>
</div>
<script src="../styles/bootstrap.bundle.min.js"></script>
</body>
</html>
