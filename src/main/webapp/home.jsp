<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 08/12/2024
  Time: 1:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home Page</title>
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
    <div class="row">
        <a href="users">Users Management</a>
        <a href="subjects">Subject Management</a>
        <a href="tutors">Tutor List</a>
        <a href="classes">Class List</a>
    </div>
    <form action="students" method="POST" style="display:inline;">
        <input type="hidden" name="action" value="list_students_by_class" />
        <select name="class_name" id="class_name">
            <option value="JV101-HN" selected>JV101-HN</option>
            <option value="PHP102-DN">PHP102-DN</option>
            <option value="JV103-HCM">JV103-HCM</option>
        </select>
        <button type="submit">Show students in class</button>
    </form>

    <br><br>
    <form action="officer" method="POST" style="display:inline;">
        <input type="hidden" name="action" value="list_students" />
        <button type="submit">List students</button>
    </form>

</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</html>
