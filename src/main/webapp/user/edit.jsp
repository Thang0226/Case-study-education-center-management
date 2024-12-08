<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 06/12/2024
  Time: 3:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Edit user</title>
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
                        <a class="nav-link active" aria-current="page" href="/home">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/users">User Management</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="row d-flex justify-content-center mt-2">
        <div class="col-sm-6 col-md-6 col-lg-6">
            <form method="post">
                <fieldset>
                    <legend class="text-center">Edit
                        <span>
                            <c:choose>
                                <c:when test="${user.roleID == 1}">Tutor</c:when>
                                <c:when test="${user.roleID == 2}">Officer</c:when>
                                <c:when test="${user.roleID == 3}">Admin</c:when>
                                <c:when test="${user.roleID == 4}">Student</c:when>
                                <c:otherwise>User</c:otherwise>
                            </c:choose>
                        </span>
                        information</legend>
                    <table class="table table-hover align-middle">
                        <tr>
                            <td><label for="email">Email <span style="color: red">*</span>: </label></td>
                            <td>
                                <input type="email" name="email" id="email" class="form-control" required
                                value="${user.email}">
                            </td>
                        </tr>
                        <tr>
                            <td><label for="password">Password <span style="color: red">*</span>: </label></td>
                            <td>
                                <input type="text" name="password" id="password" minlength="6" maxlength="20"
                                       class="form-control" required value="${user.password}">
                            </td>
                        </tr>
                        <tr>
                            <td><label for="phone">Phone <span style="color: red">*</span>: </label></td>
                            <td>
                                <input type="text" name="phone" id="phone" class="form-control" required
                                       value="${user.phone}">
                            </td>
                        </tr>
                        <tr>
                            <td><label for="fullName">Full name <span style="color: red">*</span>: </label></td>
                            <td>
                                <input type="text" name="fullName" id="fullName" class="form-control" required
                                       value="${user.fullName}">
                            </td>
                        </tr>
                        <tr>
                            <td><label for="dateOfBirth">Date of Birth <span style="color: red">*</span>: </label></td>
                            <td>
                                <input type="text" name="dateOfBirth" id="dateOfBirth" class="form-control" required
                                       pattern="(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\d{4}" placeholder="dd/mm/yyyy"
                                       value="${user.dateOfBirth}">
                            </td>
                        </tr>
                        <tr>
                            <td><label for="address">Address <span style="color: red">*</span>: </label></td>
                            <td>
                                <input type="text" name="address" id="address" class="form-control" required
                                       value="${user.address}">
                            </td>
                        </tr>
                        <tr>
                            <td><label for="identity">Identify card number <span style="color: red">*</span>: </label></td>
                            <td>
                                <input type="text" name="identity" id="identity" class="form-control" required
                                       value="${user.identity}">
                            </td>
                        </tr>
                        <c:if test="${user.roleID == 4}">
                            <tr>
                                <td>Student tuition status <span style="color: red">*</span>:</td>
                                <td>
                                    <select name="tuitionStatusID" id="tuitionStatusID" class="form-select"
                                            aria-label="Default select example" required>
                                        <c:forEach var="tuitionStatus" items="${tuitionStatuses}">
                                            <option value="${tuitionStatus.id}"
                                                ${student.tuitionStatusID == tuitionStatus.id ? 'selected' : ''}>
                                                    ${tuitionStatus.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Student status <span style="color: red">*</span>:</td>
                                <td>
                                    <select name="studentStatusID" id="studentStatusID" class="form-select"
                                            aria-label="Default select example" required>
                                        <c:forEach var="studentStatus" items="${studentStatuses}">
                                            <option value="${studentStatus.id}"
                                                ${student.studentStatusID == studentStatus.id ? 'selected' : ''}>
                                                    ${studentStatus.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Class <span style="color: red">*</span>:</td>
                                <td>
                                    <select name="classID" id="classID" class="form-select"
                                            aria-label="Default select example" required>
                                        <c:forEach var="clazz" items="${Classes}">
                                            <option value="${clazz.id}"
                                                ${student.classID == clazz.id ? 'selected' : ''}>
                                                    ${clazz.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </c:if>
                        <td>
                            <button type="submit" class="btn btn-primary">Update</button>
                        </td>
                        <td>
                            <c:if test='${requestScope["message"] != null}'>
                                <span class="message" style="color: green">${requestScope["message"]}</span>
                            </c:if>
                        </td>
                    </table>
                </fieldset>
            </form>
            <a href="${pageContext.request.contextPath}/users" class="btn btn-secondary">Back</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
