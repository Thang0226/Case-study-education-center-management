<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 08/12/2024
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Subject management</title>
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
            </div>
        </div>
    </nav>
    <div class="row d-flex justify-content-center align-items-center">

        <div class="col-sm-10 col-md-8 col-lg-6 mx-auto">
            <div class="mt-2 col-12">
                <form method="post" class="d-flex">
                    <button type="submit" class="btn btn-success col-3 me-2" name="action" value="create">
                        Add new subject
                    </button>
                    <input class="form-control" type="text" name="subject"
                           id="subject" placeholder="Enter subject name" required>
                </form>
            </div>
            <table class="table table-hover align-middle">
                <thead>
                <tr class="text-center">
                    <th class="col-sm-2 col-md-1 col-lg-1">ID</th>
                    <th class="col-sm-6 col-md-6 col-lg-4">Name</th>
                    <th colspan="2" class="col-sm-2 col-md-1 col-lg-1">Action</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="subject" items="${subjects}">
                        <tr>
                            <td class="text-center">${subject.id}</td>
                            <td class="text-left">${subject.name}</td>
                            <td>
                                <a href="#" class="btn btn-primary">Edit</a>
                            </td>
                            <td>
                                <form method="post" style="margin: 0">
                                    <input type="hidden" name="id" value="${subject.getId()}">
                                    <button class="btn btn-danger" name="action" value="delete">Delete</button>
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
