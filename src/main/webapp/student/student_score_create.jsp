<%--
  Created by IntelliJ IDEA.
  User: thang
  Date: 09/12/24
  Time: 14:29
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
        function inputScores() {
            const in_theory_score = document.getElementById("in_theory_score");
            const theory_score = document.getElementById("theory_score");
            const in_practical_score = document.getElementById("in_practical_score");
            const practical_score = document.getElementById("practical_score");
            const average_score = document.getElementById("average_score");
            const in_session_id = document.getElementById("in_session_id");
            const session_id = document.getElementById("session_id");

            theory_score.value = in_theory_score.value;
            practical_score.value = in_practical_score.value;

            // Convert string values to numbers
            let theoryValue = parseFloat(in_theory_score.value) || 0; // Default to 0 if input is empty or invalid
            let practicalValue = parseFloat(in_practical_score.value) || 0;
            // Calculate average
            let averageValue = (theoryValue + practicalValue) / 2;
            // Display the average
            average_score.innerHTML = averageValue.toFixed(2); // Format to 2 decimal places

            session_id.value = in_session_id.value;
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
    <h2 class="text-primary">Insert New Score</h2>
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
                            <th scope="col" class="col-1">Exam Session</th>
                            <th scope="col" class="col-2">Theory Score</th>
                            <th scope="col" class="col-2">Practical Score</th>
                            <th scope="col" class="col-2">Average Score</th>
                            <th scope="col" class="col-1">Insert Scores</th>
                        </tr>
                        </thead>
                        <tbody class="table-group-divider">
                            <tr>
                                <th scope="row">
                                    <select name="in_session_id" id="in_session_id">
                                        <c:forEach items="${requestScope.sessions}" var="session">
                                            <option value="${session.id}">${session.name}</option>
                                        </c:forEach>
                                    </select>
                                </th>
                                <td>
                                    <input type="number" id="in_theory_score" oninput="inputScores()">
                                </td>
                                <td>
                                    <input type="number" id="in_practical_score" oninput="inputScores()">
                                </td>
                                <td><p id="average_score"></p></td>
                                <td>
                                    <form action="officer" method="POST" style="display:inline;">
                                        <input type="hidden" name="action" value="add_student_scores"/>
                                        <input type="hidden" name="student_id" value="${requestScope.student.id}">
                                        <input type="hidden" name="session_id" id="session_id">
                                        <input type="hidden" id="theory_score" name="theory_score">
                                        <input type="hidden" id="practical_score" name="practical_score">
                                        <button type="submit" class="btn btn-primary">
                                            Insert
                                        </button>
                                    </form>
                                </td>
                            </tr>
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
