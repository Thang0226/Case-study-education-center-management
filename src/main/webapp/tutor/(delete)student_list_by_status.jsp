<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 08/12/2024
  Time: 3:31 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <link rel="stylesheet" href="../styles/bootstrap.min.css">
    <title>Student list by status</title>

  </head>
  <body>
    <div class="container">
      <h1 class="my-4">
        Students who ${requestScope.status} from class ${requestScope.class_name}
      </h1>
      <table class="table table-hover">
        <tr>
          <th>Student Name</th>
          <th>Status</th>
        </tr>
        <c:forEach items="${studentStatus}" var="student">
          <tr>
            <td>${student.id}</td>
            <td>${student.studentStatus}</td>
          </tr>
        </c:forEach>
      </table>
    </div>
  <script src="../styles/bootstrap.bundle.min.js"></script>
  </body>
</html>
