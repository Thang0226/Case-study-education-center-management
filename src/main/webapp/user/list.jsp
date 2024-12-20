<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 06/12/2024
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Management</title>
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
                        <a class="nav-link active" aria-current="page" href="home">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/users">User Management</a>
                    </li>
                </ul>
                <form action="../login.jsp" method="POST" class="ms-auto my-auto">
                    <button type="submit" class="btn btn-secondary">Log Out</button>
                </form>
            </div>
        </div>
    </nav>
    <div class="row">
        <div class="mt-2 col-lg-12">
            <form class="d-flex" role="search" method="get">
                <input type="hidden" name="action" value="search">
                <input class="form-control me-2" name="name" id="search" placeholder="User Name" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
        <form method="get">
            <button type="submit" class="btn btn-success" name="action" value="create">
                Create new user
            </button>
        </form>
        <div class="col-sm-12 col-md-12 col-lg-12">
            <table class="table table-hover align-middle">
                <thead>
                <tr>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th class="text-center">Phone</th>
                    <th class="text-center">Date Of Birth</th>
                    <th>Address</th>
                    <th>Role</th>
                    <th colspan="2" class="text-center">Action</th>

                </tr>
                </thead>
                <tbody>
                <h2>User List</h2>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>
                            <a href="users?action=view&id=${user.getId()}"
                               class="link-dark link-offset-2 link-underline-opacity-0 link-underline-opacity-100-hover">
                                    ${user.getFullName()}
                            </a>
                        </td>
                        <td>${user.email}</td>
                        <td class="text-center">${user.phone}</td>
                        <td class="text-center">${user.dateOfBirth}</td>
                        <td>${user.address}</td>
                        <td>
                            <c:forEach var="role" items="${roles}">
                                <c:if test="${user.roleID == role.id}">
                                    ${role.name}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td class="text-center">
                            <a href="users?action=edit&id=${user.getId()}" class="btn btn-primary">Edit</a>
                        </td>
                        <td class="text-center">
                            <form method="post" style="margin: 0">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${user.getId()}">
                                <button class="btn btn-danger">Delete</button>
                            </form>
                        </td>
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
