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
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
        <h2 class="text-center">
            Student Profile
        </h2>
        <table class="table table-bordered table-light table-striped table-hover">
            <thead class="table-light">
            <tr>
                <th scope="col" class="col-1">Student ID</th>
                <th scope="col" class="col-2">Full Name</th>
                <th scope="col" class="col-2">Email</th>
                <th scope="col" class="col-2">Date of Birth</th>
                <th scope="col" class="col-2">Address</th>
                <th scope="col" class="col-2">Phone number</th>
                <th scope="col" class="col-2">Status</th>

            </tr>
            </thead>
            <tbody class="table-group-divider">
            <tr>
                <td>${requestScope.student.id}</td>
                <td>${requestScope.student.fullName}</td>
                <td>${requestScope.student.email}</td>
                <td>${requestScope.student.dateOfBirth}</td>
                <td>${requestScope.student.address}</td>
                <td>${requestScope.student.phoneNumber}</td>
                <td>${requestScope.student.studentStatus}</td>
            </tr>
            </tbody>
        </table>
        <table class="table table-bordered table-light table-striped table-hover">
            <thead class="table-light">
            <tr>
                <th scope="col" class="col-3">Exam Session Name</th>
                <th scope="col" class="col-2">Theory Score</th>
                <th scope="col" class="col-2">Practice Score</th>
                <th scope="col" class="col-2">Average Score</th>
            </tr>
            </thead>
            <tbody class="table-group-divider">
            <c:forEach var="examResult" items="${examResults}">
            <tr>
                <c:forEach var="examSession" items="${examSession}">
                    <c:if test="${examSession.id == examResult.examSessionID}">
                        <td>${examSession.name}</td>
                    </c:if>
                </c:forEach>
                <td>${examResult.theoryScore}</td>
                <td>${examResult.practicalScore}</td>
                <td>${examResult.averageScore}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="col-6 mx-auto">
        <h3 class="text-center">Biểu đồ điểm trung bình của học viên</h3>
        <canvas id="scoreChart" width="600" height="500"></canvas>
    </div>

</div>
<script src="../styles/bootstrap.bundle.min.js"></script>
<script>
    const ctx = document.getElementById('scoreChart').getContext('2d');
    const scoreChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: [
                <c:forEach var="examResult" items="${examResults}">
                    <c:forEach var="examSession" items="${examSession}">
                        <c:if test="${examSession.id == examResult.examSessionID}">
                        '${examSession.name}',
                        </c:if>
                    </c:forEach>
                </c:forEach>

            ],
            datasets: [
                {
                    label: 'Điểm trung bình (Bar)',
                    type: 'bar', // Đây là dataset dạng cột
                    data: [
                        <c:forEach var="examResult" items="${examResults}">
                        <c:if test="${examResult.averageScore >= 50 && examResult.averageScore <= 100}">
                        ${examResult.averageScore},
                        </c:if>
                        </c:forEach>
                    ],
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                },
                {
                    label: 'Điểm trung bình (Line)',
                    type: 'line', // Đây là dataset dạng đường
                    data: [
                        <c:forEach var="examResult" items="${examResults}">
                        <c:if test="${examResult.averageScore >= 50 && examResult.averageScore <= 100}">
                        ${examResult.averageScore},
                        </c:if>
                        </c:forEach>
                    ],
                    borderColor: 'rgba(255, 99, 132, 1)',
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    tension: 0.4, // Tạo độ cong cho đường
                    fill: false, // Không tô màu dưới đường
                    pointBackgroundColor: 'rgba(255, 99, 132, 1)',
                    pointBorderColor: 'rgba(255, 99, 132, 1)'
                }
            ]
        },
        options: {
            barThickness:30,
            scales: {
                y: {
                    beginAtZero: false,
                    min: 70,
                    max:100,
                }
            }
        }
    });
</script>
</body>
</html>
