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
    <script>
        function copyScores() {
            const in_theory_score = document.getElementById("in_theory_score");
            const theory_score = document.getElementById("theory_score");
            const in_practical_score = document.getElementById("in_practical_score");
            const practical_score = document.getElementById("practical_score");
            theory_score.value = in_theory_score.value;
            practical_score.value = in_practical_score.value;
        }
    </script>
</head>

<body>

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
    <h2 class="text-primary">Student Information</h2>
    <div class="table-responsive">
        <table class="table table-bordered table-light table-striped table-hover">
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
                <th>Class:</th>
                <td>${requestScope.clazz.name}</td>
            </tr>
            <tr>
                <th>Scores:</th>
                <td>
                    <table class="table table-bordered table-light table-striped table-hover">
                        <thead class="table-light">
                        <tr>
                            <th scope="col" class="col-1">Session ID</th>
                            <th scope="col" class="col-2">Theory Score</th>
                            <th scope="col" class="col-2">Practical Score</th>
                            <th scope="col" class="col-2">Average Score</th>
                            <th scope="col" class="col-2">Update Scores</th>
                        </tr>
                        </thead>
                        <tbody class="table-group-divider">
                        <c:forEach items="${requestScope.exam_results}" var="result">
                            <tr>
                                <th scope="row">${result.examSessionID}</th>
                                <td>
                                    <input type="text" id="in_theory_score" oninput="copyScores()"
                                           value="${result.theoryScore}">
                                </td>
                                <td>
                                    <input type="text" id="in_practical_score" oninput="copyScores()"
                                            value="${result.practicalScore}">
                                </td>
                                <td>${result.averageScore}</td>
                                <td>
                                    <form action="/officer" method="POST" style="display:inline;">
                                        <input type="hidden" name="action" value="update_student_scores"/>
                                        <input type="hidden" name="student_id" value="${result.studentID}">
                                        <input type="hidden" name="session_id"
                                               value="${result.examSessionID}">
                                        <input type="hidden" id="theory_score" name="theory_score">
                                        <input type="hidden" id="practical_score" name="practical_score">
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
<script src="../styles/bootstrap.bundle.min.js"></script>
</body>
</html>
