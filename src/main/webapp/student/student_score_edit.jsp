<%--
  Created by IntelliJ IDEA.
  User: thang
  Date: 09/12/24
  Time: 08:08
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
            vertical-align: middle;
        }

        table td {
            text-align: center;
        }

        table.form th {
            text-align: left;
        }

        table.data th {
            text-align: center;
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
    <script>
        function copyScores(x) {
            const in_theory_score = document.getElementById("in_theory_score_" + x);
            const theory_score = document.getElementById("theory_score_" + x);
            const in_practical_score = document.getElementById("in_practical_score_" + x);
            const practical_score = document.getElementById("practical_score_" + x);
            theory_score.value = in_theory_score.value;
            practical_score.value = in_practical_score.value;
        }
    </script>
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
    <!-- Back to Home Link -->
    <div class="text-center my-4">
        <form action="/officer" method="POST" style="display:inline;">
            <input type="hidden" name="action" value="list_students"/>
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
        <h2 class="text-primary">Student Scores</h2>
        <div class="table-responsive">
            <table class="table table-bordered table-light table-striped table-hover form">
                <tr>
                    <th>User ID:</th>
                    <td>${requestScope.user.id}</td>
                </tr>
                <tr>
                    <th>Full Name:</th>
                    <td>${requestScope.user.fullName}</td>
                </tr>
                <tr>
                    <th>Class:</th>
                    <td>${requestScope.clazz.name}</td>
                </tr>
                <tr>
                    <th>Subject:</th>
                    <td>${requestScope.subject.name}</td>
                </tr>
                <tr>
                    <th>Scores:</th>
                    <td>
                        <table class="table table-bordered table-light table-striped table-hover data">
                            <thead class="table-light">
                            <tr>
                                <th scope="col" class="col-1">Session ID</th>
                                <th scope="col" class="col-2">Theory Score</th>
                                <th scope="col" class="col-2">Practical Score</th>
                                <th scope="col" class="col-2">Average Score</th>
                                <th scope="col" class="col-1">Update Scores</th>
                            </tr>
                            </thead>
                            <tbody class="table-group-divider">
                            <c:forEach items="${requestScope.exam_results}" var="result">
                                <tr>
                                    <th scope="row">${result.examSessionID}</th>
                                    <td>
                                        <input type="text" id="in_theory_score_${result.examSessionID}"
                                               oninput="copyScores(${result.examSessionID})"
                                               value="${result.theoryScore}">
                                    </td>
                                    <td>
                                        <input type="text" id="in_practical_score_${result.examSessionID}"
                                               oninput="copyScores(${result.examSessionID})"
                                               value="${result.practicalScore}">
                                    </td>
                                    <td>${result.averageScore}</td>
                                    <td>
                                        <form action="/officer" method="POST" style="display:inline;">
                                            <input type="hidden" name="action" value="update_student_scores"/>
                                            <input type="hidden" name="student_id" value="${result.studentID}">
                                            <input type="hidden" name="session_id"
                                                   value="${result.examSessionID}">
                                            <input type="hidden" id="theory_score_${result.examSessionID}"
                                                   name="theory_score"
                                                   value="${result.theoryScore}">
                                            <input type="hidden" id="practical_score_${result.examSessionID}"
                                                   name="practical_score"
                                                   value="${result.practicalScore}">
                                            <button type="submit" class="btn btn-primary">
                                                Update
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

<script src="../styles/bootstrap.bundle.min.js"></script>
</body>
</html>
