<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 06/12/2024
  Time: 3:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create User</title>
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
                        <a class="nav-link active" aria-current="page" href="http://localhost:8080/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/users">Users Management</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="row d-flex justify-content-center">
        <div class="col-sm-6 col-md-6 col-lg-6">
            <form method="post">
                <fieldset>
                    <legend class="text-center mt-2">Users information</legend>
                    <table class="table table-hover align-middle">
                        <tr>
                            <td><label for="email">Email <span style="color: red">*</span>: </label></td>
                            <td><input type="email" name="email" id="email" class="form-control" required></td>
                        </tr>
                        <tr>
                            <td><label for="password">Password <span style="color: red">*</span>: </label></td>
                            <td>
                                <input type="password" name="password" id="password" minlength="6" maxlength="20"
                                       class="form-control" required>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="re_password">Re-enter Password <span style="color: red">*</span>: </label></td>
                            <td><input type="password" name="re_password" id="re_password" class="form-control" required></td>
                        </tr>
                        <tr>
                            <td><label for="phone">Phone <span style="color: red">*</span>: </label></td>
                            <td><input type="text" name="phone" id="phone" class="form-control" required></td>
                        </tr>
                        <tr>
                            <td><label for="fullName">Full name <span style="color: red">*</span>: </label></td>
                            <td><input type="text" name="fullName" id="fullName" class="form-control" required></td>
                        </tr>
                        <tr>
                            <td><label for="dateOfBirth">Date of Birth <span style="color: red">*</span>: </label></td>
                            <td>
                                <input type="text" name="dateOfBirth" id="dateOfBirth" class="form-control" required
                                       pattern="(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\d{4}" placeholder="dd/mm/yyyy">
                            </td>
                        </tr>
                        <tr>
                            <td><label for="address">Address <span style="color: red">*</span>: </label></td>
                            <td><input type="text" name="address" id="address" class="form-control" required></td>
                        </tr>
                        <tr>
                            <td><label for="identity">Identify card number <span style="color: red">*</span>: </label></td>
                            <td><input type="text" name="identity" id="identity" class="form-control" required></td>
                        </tr>
                        <tr>
                            <td><label for="role">Role <span style="color: red">*</span>: </label></td>
                            <td>
                                <select name="roleId" id="role" class="form-select"
                                        aria-label="Default select example" required>
                                    <option selected>Chose role</option>
                                    <c:forEach var="role" items="${roles}">
                                        <option value="${role.id}">${role.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button type="submit" class="btn btn-success">Create</button>
                            </td>
                            <td>
                                <c:if test='${requestScope["message"] != null}'>
                                    <span class="message" style="color: green">${requestScope["message"]}</span>
                                </c:if>
                            </td>
                        </tr>
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
