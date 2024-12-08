<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>

<a href="/users">go to users management</a>
<br><br>
<form action="/students" method="POST" style="display:inline;">
    <input type="hidden" name="action" value="list_students_by_class" />
    <select name="class_name" id="class_name">
        <option value="JV101-HN" selected>JV101-HN</option>
        <option value="PHP102-DN">PHP102-DN</option>
        <option value="JV103-HCM">JV103-HCM</option>
    </select>
    <button type="submit">Show students in class</button>
</form>

<br><br>
<form action="/officer" method="POST" style="display:inline;">
    <input type="hidden" name="action" value="list_students" />
    <button type="submit">List students</button>
</form>

</body>
</html>