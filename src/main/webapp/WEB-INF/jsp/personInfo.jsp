<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <title>Person List</title>
</head>
<body>
    <h1>List Of Person Information</h1>

    <!-- Display error message if present -->
    <c:if test="${not empty error}">
        <div style="color: red;">
            ${error}
        </div>
    </c:if>

    <!-- Display general message if present -->
    <c:if test="${not empty message}">
        <div style="color: blue;">
            ${message}
        </div>
    </c:if>

    <!-- Display person list if present -->
    <c:if test="${not empty person}">
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Age</th>
                    <!-- Add more fields as necessary -->
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${person}">
                    <tr>
                        <td>${person.id}</td>
                        <td>${person.name}</td>
                        <td>${person.age}</td>
                        <!-- Add more fields as necessary -->
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- Handle case where no persons are found and no error message -->
    <c:if test="${empty person and empty error and empty message}">
        <div>
            <p>No persons found and no message available.</p>
        </div>
    </c:if>
</body>
</html>
